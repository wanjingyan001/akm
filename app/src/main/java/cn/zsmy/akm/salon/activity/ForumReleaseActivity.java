package cn.zsmy.akm.salon.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.SystemPhoto;
import cn.zsmy.akm.utils.TextUtil;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.pop.PhotoPopupWindow;

/**
 * 发新帖
 * Created by wanjingyan
 * on 2015/12/1 11:04.
 */
public class ForumReleaseActivity extends BaseTitleActivity implements View.OnClickListener, PhotoPopupWindow.onPopupWindowItemClickListener {
    private LinearLayout mAddPhoto;
    private int COMMENT_POST = 0;//提交帖子
    private Uri tempUri;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_forum_release);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeData() {

    }

    public static Intent getIntent(Context context) {
        return new Intent(context, ForumReleaseActivity.class);
    }

    @Override
    public void initializeView() {
        super.initializeView();
        setTitle(getText(R.string.posted));
        mAddPhoto = ((LinearLayout) findViewById(R.id.add_photos));
        mAddPhoto.setOnClickListener(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case SystemPhoto.CAMERA_IMAGE_CODE:
                addImages(tempUri);
                break;
            case SystemPhoto.FILE_IMAGE_CODE:
                addImages(data.getData());
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addImages(Uri uri) {
        mAddPhoto.setVisibility(View.GONE);
        String type = getContentResolver().getType(uri);
        String path = "";
        if (TextUtil.isValidate(type)) {
            path = SystemPhoto.getFilePathFromUri(this, uri);
        } else {
            path = uri.getPath();
        }
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setResult(COMMENT_POST, PlatformListActivity.getIntent(this));
        finish();
        Request request = new Request(UrlHelpper.COMMINT_POST, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", result);

            }
        });
        RequestManager.getInstance().execute(this.toString(), request);

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        PhotoPopupWindow bottomPopupOption = new PhotoPopupWindow(this);
        bottomPopupOption.setItemText("拍照", "选择相册");
        bottomPopupOption.showPopupWindow();
        bottomPopupOption.setItemClickListener(this);
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
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                startActivityForResult(openCameraIntent, SystemPhoto.CAMERA_IMAGE_CODE);
                break;
            case 1:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SystemPhoto.FILE_IMAGE_CODE);

                break;
        }
    }
}
