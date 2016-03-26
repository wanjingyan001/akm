package cn.zsmy.akm.interrogation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.FileCallback;
import cn.zsmy.akm.http.FileEntity;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.Trace;
import cn.zsmy.akm.salon.activity.choosePhoto.Bimp;
import cn.zsmy.akm.salon.activity.choosePhoto.FileUtils;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.ImageUtils;
import cn.zsmy.akm.utils.Is_Login;
import cn.zsmy.akm.utils.StringUtils;
import cn.zsmy.akm.utils.SystemPhoto;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.dialog.ProgressDialogUtils;
import cn.zsmy.akm.widget.pop.PhotoPopupWindow;

/**
 * 问诊信息填写
 * Created by sanz on 2015/11/24 11:35
 */
public class InterrogationInputActivity extends BaseTitleActivity implements View.OnClickListener, TextWatcher, PhotoPopupWindow.onPopupWindowItemClickListener {
    public static final int INTERROGATION_TYPE_DOCTOR = 1;//指定医生问诊
    public static final int INTERROGATION_TYPE_RANDOM = 2;//系统分配
    public int interrogation_type;//当前问诊类型
    public String doctorId, ztype, flag;
    private ImageView photo;
    private Button next;
    private EditText explanation;
    protected Uri tempUri;
    private final int DEPT_RESULT_CODE = 50;
    private TextView mDiseaseDesSizeLabel;
    private String picturePath;
    private String id;
    private PhotoPopupWindow photoPopupWindow;

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_interrogation);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        //        TODO init view
        photo = (ImageView) findViewById(R.id.interrogation_imagview_photoID);
        explanation = (EditText) findViewById(R.id.interrogation_edittext_explanationID);
        next = (Button) findViewById(R.id.interrogation_button_nextID);

        mDiseaseDesSizeLabel = (TextView) findViewById(R.id.interrogation_textview_wordnumID);

        photo.setOnClickListener(this);
        next.setOnClickListener(this);
        explanation.addTextChangedListener(this);


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Integer num = s.toString().length();
        if (num == 300) {
            mDiseaseDesSizeLabel.setTextColor(getResources().getColor(
                    R.color.red));
        } else {
            mDiseaseDesSizeLabel.setTextColor(getResources().getColor(
                    R.color.black));
        }
        mDiseaseDesSizeLabel.setText(num + "/" + 300);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.interrogation_imagview_photoID:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
                photoPopupWindow = new PhotoPopupWindow(this);
                photoPopupWindow.setItemText("拍照", "选择相册");
                photoPopupWindow.showPopupWindow();
                photoPopupWindow.setItemClickListener(this);
                break;
            case R.id.interrogation_button_nextID:
                if (explanation.getText().length() < 10) {
                    Toast.makeText(this, "最少输入10个字", Toast.LENGTH_LONG).show();
                } else {
                    if (picturePath != null) {
                        boolean loginStatus = Is_Login.getLoginStatus(this);
                        if (loginStatus) {
                            ArrayList<String> urlDatas = new ArrayList<>();
                            urlDatas.clear();
                            urlDatas.add(picturePath);
                            loadCreatCaseId(urlDatas);
                        }
                    } else {
                        Toast.makeText(this, "请加入图片", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    private void loadCreatCaseId(ArrayList<String> urlDatas) {
        ArrayList<FileEntity> fileEntities = new ArrayList<FileEntity>();
        ArrayList<String> list = new ArrayList<String>();
        if (urlDatas != null && urlDatas.size() > 0) {
            try {
                for (String str : urlDatas) {
                    Bitmap bm = Bimp.revitionImageSize(str);
                    String newStr = str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf("."));
                    FileUtils.saveBitmap(bm, "" + newStr);
                    list.add(FileUtils.SDPATH + newStr + ".JPEG");
                    long fileSize = Bimp.getFileSize(new File(FileUtils.SDPATH + newStr + ".JPEG"));
                    Log.d("TAG", "大小为：" + Bimp.bytes2kb((int) fileSize));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (String filepath : list) {
            fileEntities.add(new FileEntity(new File(filepath).getName(), "", filepath));
        }
        Request request = new Request(UrlHelpper.UPLOAD_IMAGE + "zType=" + ztype, Request.RequestMethod.POST, this);
        Log.i("TAG", UrlHelpper.UPLOAD_IMAGE + "zType=" + ztype);
        request.fileEntities = fileEntities;
        request.put("patientId", MyApplication.getProfile().getUserId());
        if (!TextUtils.isEmpty(doctorId)) {
            request.put("doctorId", doctorId);
        }
//        if (!TextUtils.isEmpty(ztype)) {
//            request.put("zType", ztype);
//        }
        if (!TextUtils.isEmpty(flag)) {
            request.put("flag", flag);
        }
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    JSONObject obj = object.optJSONObject("data");
                    String id = obj.optString("id");
                    if (!TextUtils.isEmpty(doctorId)) {
                        startActivity(InterrogationUserChoiceActivity.getIntent(InterrogationInputActivity.this, explanation.getText().toString(), id, ztype, flag, doctorId));
                    } else {
                        startActivity(InterrogationUserChoiceActivity.getIntent(InterrogationInputActivity.this, explanation.getText().toString(), id, ztype, flag));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    protected void initializeData() {
        setTitle("问诊信息");
        interrogation_type = getIntent().getIntExtra("type", -1);
        doctorId = getIntent().getStringExtra(Constants.KEY_DOCTOR_ID);
        ztype = getIntent().getStringExtra(Constants.KEY_TYPE_ID);
        flag = getIntent().getStringExtra(Constants.KEY_FLAG_ID);
    }

    /**
     * 从医生详情进入
     *
     * @param context
     * @param type     问诊类型
     * @param doctorId 医生id
     * @param zType    病历类型(0:普通病历、1：vip)
     * @param flag     问诊类型（1图文问诊 2电话问诊3视频问诊）
     * @return
     */
    public static Intent getIntent(Context context, int type, String doctorId, String flag, String zType) {
        Intent intent = new Intent(context, InterrogationInputActivity.class);
        intent.putExtra("type", type);
        if (doctorId != null) {
            intent.putExtra(Constants.KEY_DOCTOR_ID, doctorId);
        }
        if (zType != null) {
            intent.putExtra(Constants.KEY_TYPE_ID, zType);
        }
        if (flag != null) {
            intent.putExtra(Constants.KEY_FLAG_ID, flag);
        }
        return intent;
    }

    /**
     * 从首页进入
     *
     * @param context
     * @return
     */
    public static Intent getIntent(Context context) {
        return getIntent(context, INTERROGATION_TYPE_RANDOM, null, null, null);
    }

    public void goChoicePerson(String picture, String content) {

    }

    /**
     * 实现poppupwindow接口，通过position判断当前button
     *
     * @param0 拍照
     * @param1 选择相册
     ****/
    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/"
                        + String.valueOf(System.currentTimeMillis()) + ".jpg");
                tempUri = Uri.fromFile(file);
                Log.d("TAG", "fielName:" + tempUri);
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                startActivityForResult(openCameraIntent, SystemPhoto.CAMERA_IMAGE_CODE);
                break;
            case 1:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, DEPT_RESULT_CODE);

                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK && null != data) {
            return;
        }
        Uri uri = null;
        if (data != null && data.getData() != null) {
            uri = data.getData();
            Log.i("TAG", "URI:" + uri.getPath());
        }

        switch (requestCode) {
            case SystemPhoto.CAMERA_IMAGE_CODE:
                if (uri == null) {
                    uri = tempUri;
                }
                picturePath = SystemPhoto.getFilePathFromUri(this, uri);
                break;
            case DEPT_RESULT_CODE:
                picturePath = StringUtils.getFilePath(this, data);
                break;
        }
        if (picturePath != null) {
            try {
                Bitmap bitmap = Bimp.revitionImageSize(picturePath);
                String newStr = picturePath.substring(picturePath.lastIndexOf("/") + 1, picturePath.lastIndexOf("."));
                FileUtils.saveBitmap(bitmap, "" + newStr);
                picturePath = FileUtils.SDPATH + newStr + ".JPEG";
                long fileSize = Bimp.getFileSize(new File(picturePath));
                Trace.d(Bimp.bytes2kb((int) fileSize));
                if (bitmap != null) {
                    photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    photo.setImageBitmap(null);
                    photo.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    photoPopupWindow.dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ProgressDialogUtils.closeProgressDialog();
            finish();
        }

        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageUtils.releaseImageResouce(photo);
    }
}
