package cn.zsmy.akm.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.UserInfo;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.FileCallback;
import cn.zsmy.akm.http.FileEntity;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.salon.activity.choosePhoto.Bimp;
import cn.zsmy.akm.salon.activity.choosePhoto.FileUtils;
import cn.zsmy.akm.utils.ImageUtils;
import cn.zsmy.akm.utils.StringUtils;
import cn.zsmy.akm.utils.SystemPhoto;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.pop.PhotoPopupWindow;
import cn.zsmy.akm.widget.row.ContainerView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的资料
 * Created by wanjingyan
 * on 2015/12/9 17:06.
 */
public class UserInfoActivity extends BaseTitleActivity implements View.OnClickListener, PhotoPopupWindow.onPopupWindowItemClickListener {
    private ContainerView mWidgetContainerView;
    private TextView mUserName;
    private TextView mUserPhone;
    private TextView mUserCity;
    private CircleImageView mUserHead;
    private Uri tempUri;
    private UserInfo.DataEntity data;
    private final int DEPT_RESULT_CODE = 50;
    public static final int CHANGE_NAME = 0;
    public static final int CHANGE_PHONE = 11;
    private static final int CHANGE_CITY = 2;
    private SharedPreferences save_city;
    private PhotoPopupWindow bottomPopupOption;
    private String picturePath;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            finish();
        }
    };

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_user_info);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        setTitle(getText(R.string.mProfileUserDetailLabel));
        findViewById(R.id.user_name).setOnClickListener(this);
        findViewById(R.id.user_head).setOnClickListener(this);
        findViewById(R.id.user_phone).setOnClickListener(this);
        findViewById(R.id.choose_city).setOnClickListener(this);
        mUserName = ((TextView) findViewById(R.id.user_name_text));
        mUserPhone = ((TextView) findViewById(R.id.user_phone_number));
        mUserHead = ((CircleImageView) findViewById(R.id.user_head_img));
        mUserCity = ((TextView) findViewById(R.id.please_choose_city));
    }


    @Override
    protected void initializeData() {
        data = new UserInfo.DataEntity();
        getUserInfo();
    }

    private void getUserInfo() {
        Request request = new Request(UrlHelpper.USER_INFO, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                UserInfo userInfo = JsonParser.deserializeFromJson(result, UserInfo.class);
                data = userInfo.getData();
                String nickname = data.getNickname();
                String phone = data.getPhone();
                if (!TextUtils.isEmpty(nickname)) {
                    mUserName.setText(nickname);
                } else mUserName.setText("请输入");
                if (!TextUtils.isEmpty(phone)) {
                    mUserPhone.setText(phone);
                } else mUserPhone.setText("请输入");
                String offset = data.getOffset();
                if (!TextUtils.isEmpty(offset)) {
                    mUserCity.setText(offset);
                } else mUserCity.setText("请选择");
                ImageLoader imageLoader = ImageLoader.getInstance();
                if (data.getAvatar() != null) {
                    imageLoader.displayImage(UrlHelpper.FILE_IP + data.getAvatar(), mUserHead);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        return intent;
    }

    @Override
    protected void onStart() {
        super.onStart();
        save_city = getSharedPreferences("user_info", MODE_PRIVATE);
        String cityName = save_city.getString("user_city", data.getOffset());
        mUserCity.setText(cityName);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        String str;
        switch (v.getId()) {
            case R.id.user_head:
                bottomPopupOption = new PhotoPopupWindow(this);
                bottomPopupOption.setItemText("拍照", "选择相册");
                bottomPopupOption.showPopupWindow();
                bottomPopupOption.setItemClickListener(this);
                break;
            case R.id.user_name:
                str = mUserName.getText().toString();
                Intent intent = ValueEditorActivity.getIntent(this, 0, str);
                startActivityForResult(intent, CHANGE_NAME);
                break;
            case R.id.user_phone:
                str = mUserPhone.getText().toString();
                Intent it = ValueEditorActivity.getIntent(this, 1, str);
                startActivityForResult(it, CHANGE_PHONE);
                break;
            case R.id.choose_city:
                Intent city = SelectCityActivity.getIntent(this);
                startActivityForResult(city, CHANGE_CITY);
                break;

        }
    }

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
                startActivityForResult(openCameraIntent, 7);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {

            submitChange();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 修改用户个人信息
     */
    private void submitChange() {
        Request request = new Request(UrlHelpper.SUBMIT_USER_INFO, Request.RequestMethod.POST, this);
        request.put("id", MyApplication.getProfile().getUserId());
        request.put("nickname", mUserName.getText().toString());
        request.put("phone", mUserPhone.getText().toString());
        request.put("Offset", mUserCity.getText().toString());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("code").equals("SUCC")) {
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    /**
     * 修改头像
     */
    private void loadImage(String uri) {
        Request request = new Request(UrlHelpper.SUBMIT_USER_HEAD, Request.RequestMethod.POST, this);
        ArrayList<FileEntity> fileEntities = new ArrayList<FileEntity>();
        Bitmap bm = null;
        try {
            bm = Bimp.revitionImageSize(uri);
            String newStr = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
            FileUtils.saveBitmap(bm, "" + newStr);
            String path = FileUtils.SDPATH + newStr + ".JPEG";
            long fileSize = Bimp.getFileSize(new File(path));
            Log.d("TAG", "大小为：" + Bimp.bytes2kb((int) fileSize));
            fileEntities.add(new FileEntity(new File(path).getName(), "", path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.fileEntities = fileEntities;
        request.put("", "");
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(UserInfoActivity.this, "修改头像成功", Toast.LENGTH_SHORT).show();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_OK && null != data) {
//            return;
//        }
        Uri uri = null;
        if (data != null && data.getData() != null) {
            uri = data.getData();
            Log.d("TAG", "URI:" + uri.toString());
        }
        switch (requestCode) {
            case 7:
                if (uri == null) {
                    uri = tempUri;
                }
                picturePath = SystemPhoto.getFilePathFromUri(this, uri);
                break;
            case DEPT_RESULT_CODE:
                picturePath = StringUtils.getFilePath(this, data);
                break;
            case CHANGE_NAME:
                if (data != null) {
                    if (!TextUtils.isEmpty(data.getStringExtra("value"))) {
                        mUserName.setText(data.getStringExtra("value"));
                    }
                }
                break;
            case CHANGE_PHONE:
                if (data != null) {
                    if (!TextUtils.isEmpty(data.getStringExtra("value"))) {
                        mUserPhone.setText(data.getStringExtra("value"));
                    }
                }
                break;
            case CHANGE_CITY:
                if (data != null) {
                    if (!TextUtils.isEmpty(data.getStringExtra("value"))) {
                        mUserCity.setText(data.getStringExtra("value"));
                    }
                }
                break;
        }
        if (picturePath != null) {
            Log.i("TAG", "paht:" + picturePath);
            loadImage(picturePath);
            Bitmap bitmap = null;
            try {
                bitmap = Bimp.revitionImageSize(picturePath);
                if (bitmap != null) {
                    mUserHead.setImageBitmap(null);
                    mUserHead.setImageBitmap(bitmap);
                    bottomPopupOption.dismiss();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Bitmap bitmap = ImageUtils.zoomDrawable(ImageUtils.resizePhoto(this, picturePath), mUserHead.getWidth(), mUserHead.getHeight());
        } else {
            Log.d("TAG", "picturePath" + picturePath);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageUtils.releaseImageResouce(mUserHead);
    }
}
