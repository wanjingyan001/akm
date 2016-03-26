package cn.zsmy.akm.doctor.shared;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.chat.utils.Constants;
import cn.zsmy.akm.doctor.chat.utils.ImageUtils;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;

public class ChatPictureLookActivity extends BaseTitleActivity implements OnPhotoTapListener {

    private PhotoView mPicturePV;
    private ProgressBar mPictureLoadingPb;
    private String imageUrl;
    private String imagePath;
    private ImageLoader imageLoader;
    protected DisplayImageOptions options;

    public static Intent getIntent(Context context, String imageUrl, String imagePath) {
        Intent intent = new Intent(context, ChatPictureLookActivity.class);
        if (imageUrl != null) {
            intent.putExtra(Constants.KEY_IMAGE_URL, imageUrl);
        }
        if (imagePath != null) {
            intent.putExtra(Constants.KEY_IMAGE_PATH, imagePath);
        }
        return intent;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_look_picture);
        initializeConfig();
    }

    @Override
    public void initializeView() {
        mPicturePV = (PhotoView) findViewById(R.id.mPicturePV);
        mPictureLoadingPb = (ProgressBar) findViewById(R.id.mPictureLoadingPb);
        mPicturePV.setOnPhotoTapListener(this);
    }

    @Override
    public void initializeData() {
        String image = "";
        imageUrl = getIntent().getStringExtra(Constants.KEY_IMAGE_URL);
        imagePath = getIntent().getStringExtra(Constants.KEY_IMAGE_PATH);
        if (imageUrl != null) {
            image = UrlHelper.IP + imageUrl;
        }
        if (imagePath != null) {
            image = "file://" + imagePath;
        }
        Log.i("TAG", image);
        ImageLoader.getInstance().displayImage(image, mPicturePV);
//		mPicturePV.setImageBitmap(loadImageSync);
        mPictureLoadingPb.setVisibility(View.GONE);
    }

    @Override
    public void onPhotoTap(View view, float x, float y) {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public Bitmap loadImageSync(String uri, DisplayImageOptions options) {
        if (uri.contains("http://") || uri.contains("https://") || uri.contains("file:///")) {
            return imageLoader.loadImageSync(uri, options);
        } else {

            return imageLoader.loadImageSync(UrlHelper.IP + uri, options);

        }
    }

    private void initializeConfig() {
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.bg_transparent) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.bg_transparent)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.bg_transparent) // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .build();// 构建完成
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageUtils.releaseImageResouce(mPicturePV);
    }
}
