package cn.zsmy.akm.doctor.conversation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.wei.library.widget.ProgressDialogUtils;
import cn.zsmy.akm.doctor.adapter.MyGridAdapter;
import cn.zsmy.akm.doctor.admissions.bean.DoctorSuggest;
import cn.zsmy.akm.doctor.admissions.view.MaxLengthWatcher;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.conversation.bean.POST;
import cn.zsmy.akm.doctor.conversation.choosePhoto.AlbumActivity;
import cn.zsmy.akm.doctor.conversation.choosePhoto.Bimp;
import cn.zsmy.akm.doctor.conversation.choosePhoto.ImageItem;
import cn.zsmy.akm.doctor.conversation.choosePhoto.Res;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.FileCallback;
import cn.zsmy.akm.doctor.http.FileEntity;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.learning.AllAreaActivity;
import cn.zsmy.akm.doctor.utils.FileUtils;
import cn.zsmy.akm.doctor.utils.SystemPhoto;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.pop.PhotoPopupWindow;
import cn.zsmy.doctor.R;

/**
 * 发新帖
 * Created by wanjingyan
 * on 2015/12/16 17:56.
 */
public class PublishedActivity extends BaseTitleActivity implements View.OnClickListener, AdapterView.OnItemClickListener, PhotoPopupWindow.onPopupWindowItemClickListener {
    private EditText postTitleEdt;
    private EditText postContentEdt;
    private GridView ImageGrid;
    private Uri tempUri;
    private List<Bitmap> bitmaps = new ArrayList<>();
    private MyGridAdapter adapter;
    private String picturePath;
    private static final int TAKE_PICTURE = 10;
    public static Bitmap bimap;
    private int heightDifference;
    private View featuresLayout;
    private TextView choose_plate;
    private String areaId;
    private PhotoPopupWindow photoPopupWindow;
    private CheckBox caseChooseRB;
    private String caseId;
    private static String post_uri;
    private ImageView view;
    private ImageItem imageItem;
    private String type;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_publish);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        setTitle(getText(R.string.post));
        Res.init(this);
        bimap = BitmapFactory.decodeResource(
                getResources(),
                R.mipmap.ic_add_photo);
        postTitleEdt = ((EditText) findViewById(R.id.post_title_edt));
        postTitleEdt.addTextChangedListener(new MaxLengthWatcher(20, postTitleEdt, this));
        postContentEdt = ((EditText) findViewById(R.id.post_content_edt));
        postContentEdt.addTextChangedListener(new MaxLengthWatcher(300, postContentEdt, this));
        ImageGrid = ((GridView) findViewById(R.id.noScrollgridview));

        caseChooseRB = ((CheckBox) findViewById(R.id.case_choose_rb));
        findViewById(R.id.photograph).setOnClickListener(this);
        findViewById(R.id.gallery).setOnClickListener(this);
        choose_plate = ((TextView) findViewById(R.id.choose_plate));
        choose_plate.setOnClickListener(this);
        postTitleEdt.setFocusable(true);
        postTitleEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
    }

    @Override
    protected void initializeData() {
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "from_case_list":
                caseChooseRB.setChecked(true);
                caseChooseRB.setClickable(false);
                break;
            case "from_scholarship":
                caseChooseRB.setChecked(false);
                break;
            case "from_share_cast":
                caseChooseRB.setChecked(true);
                caseChooseRB.setClickable(false);
                break;
        }
        caseId = getIntent().getStringExtra("caseId");
        adapter = new MyGridAdapter(this);
        adapter.update();
        ImageGrid.setAdapter(adapter);
        ImageGrid.setOnItemClickListener(this);

    }

    //发表病例
    private void postCase() {
        Request request = new Request(UrlHelper.CURRENT_CASE + "?caseId=" + caseId, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DoctorSuggest doctorSuggest = JsonParser.deserializeFromJson(result, DoctorSuggest.class);
                DoctorSuggest.DataEntity data = doctorSuggest.getData();
                StringBuffer buffer = new StringBuffer();
                buffer.append("患者问诊内容：" + data.getContent());
                view = (ImageView) ImageGrid.getChildAt(0).findViewById(R.id.item_grida_image);
                post_uri = UrlHelper.IP + data.getCasePic();
                ImageLoader.getInstance().displayImage(post_uri, view);
                Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();
                Log.d("TAG", String.valueOf(bitmap.getByteCount()));
                String newStr = post_uri.substring(post_uri.lastIndexOf("/") + 1,
                        post_uri.length());
                FileUtils.saveBitmap(bitmap, "" + newStr);
                imageItem = new ImageItem();
                imageItem.setImagePath(FileUtils.SDPATH + newStr + ".JPEG");
                imageItem.setBitmap(bitmap);
                Log.d("TAG", "病例图片：" + post_uri + "；存储图片：" + FileUtils.SDPATH + newStr + ".JPEG");
                if (data.getCaseAdvices() != null && data.getCaseAdvices().size() != 0) {
                    List<DoctorSuggest.DataEntity.CaseAdvicesEntity> advices = data.getCaseAdvices();
                    buffer.append("\n诊断结果：" + advices.get(0).getAeger());
                }
                postContentEdt.setText(buffer);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    protected void onRestart() {
        adapter.update();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        if (caseId != null) {
            postCase();
        }
    }

    public static Intent getIntent(Context context, String type) {
        Intent intent = new Intent(context, PublishedActivity.class);
        intent.putExtra("type", type);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_issue, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.issue_post) {
            String title = postTitleEdt.getText().toString();
            String content = postContentEdt.getText().toString();
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                submitPost(title, content);
            } else {
                Toast.makeText(this, "请将内容填写完整", Toast.LENGTH_SHORT).show();
            }
        } else if (itemId == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void submitPost(String title, String content) {
        if (areaId != null) {
            Request request = new Request(UrlHelper.COMMINT_POST, Request.RequestMethod.POST, this);
            ProgressDialogUtils.showProgressDialog(this, "提交中");
            request.put("title", title);
            request.put("content", content);
            request.put("forumPlateId", areaId);
            request.put("flag", caseChooseRB.isChecked() ? "1" : "0");
            request.put("shareFlag", type.equals("from_share_cast") ? "1" : "0");
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    POST post = JsonParser.deserializeFromJson(result, POST.class);
                    String id = post.getData().getId();
                    Iterator<ImageItem> iterator = Bimp.tempSelectBitmap.iterator();
                    if (Bimp.tempSelectBitmap.size() == 0) {
                        if (caseId != null) {
                            Bimp.tempSelectBitmap.add(imageItem);
                            submitPic(id, 0);
                        } else {
                            //没有图片就直接退出界面
                            ProgressDialogUtils.closeProgressDialog();
                            finish();
                        }
                    } else {
                        //有图片就上传
                        submitPic(id, 0);
                    }
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        } else {
            Toast.makeText(this, "请选择板块，否则无法发帖", Toast.LENGTH_SHORT).show();
        }

    }

    private void submitPic(final String postId, final int i) {
        Request request = new Request(UrlHelper.ADD_POST_PIC + "id=" + postId, Request.RequestMethod.POST, this);
        List<String> cacheImages = getCacheImages();
        String uri = cacheImages.get(i);
        try {
            long fileSize = Bimp.getFileSize(new File(uri));
            Log.d("TAG", postId + "========" + uri + ",大小为：" + Bimp.bytes2kb((int) fileSize));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<FileEntity> fileEntities = new ArrayList<FileEntity>();
        fileEntities.add(new FileEntity(new File(uri).getName(), "", uri));
        request.fileEntities = fileEntities;
        request.put("id", postId);
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                if (i < Bimp.tempSelectBitmap.size() - 1) {
                    submitPic(postId, i + 1);
                } else {
                    ProgressDialogUtils.closeProgressDialog();
                    finish();
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    public ArrayList<String> getCacheImages() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
            String str = Bimp.tempSelectBitmap.get(i).getImagePath();
            String Str = str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf("."));
            list.add(FileUtils.SDPATH + Str + ".JPEG");
        }
        return list;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photograph:
                //拍照
                photo();
                break;
            case R.id.gallery:
                //相册
                Intent intent = new Intent(this, AlbumActivity.class);
                startActivityForResult(intent, 15);
                break;
            case R.id.choose_plate:
                //选择板块
                startActivityForResult(AllAreaActivity.getIntent(this, "Published"), 100);
                break;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = null;
        if (data != null && data.getData() != null) {
            uri = data.getData();
        }
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {
                    if (uri == null) {
                        uri = tempUri;
                    }
                    String str = SystemPhoto.getFilePathFromUri(this, uri);
                    Log.d("TAG", str + "++++++");
                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setImagePath(str);
                    Bimp.tempSelectBitmap.add(takePhoto);
                    adapter.notifyDataSetChanged();
                }
                break;
            case 100:
                if (data != null) {
                    String areaName = data.getStringExtra("AreaName");
                    areaId = data.getStringExtra("AreaId");
                    choose_plate.setText(areaName);
                }
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        photoPopupWindow = new PhotoPopupWindow(this);
        photoPopupWindow.setItemText("拍照", "选择相册");
        photoPopupWindow.showPopupWindow();
        photoPopupWindow.setItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        photoPopupWindow.dismiss();
        switch (position) {
            case 0:
                //拍照
                photo();
                break;
            case 1:
                Intent intent = new Intent(this, AlbumActivity.class);
                startActivityForResult(intent, 15);
                break;
        }
    }

    private void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + "/"
                + String.valueOf(System.currentTimeMillis()) + ".jpg");
        tempUri = Uri.fromFile(file);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Bimp.tempSelectBitmap.size() == 0) {
        } else {
            Bimp.tempSelectBitmap.clear();
            Bimp.max = 0;
        }
    }
}
