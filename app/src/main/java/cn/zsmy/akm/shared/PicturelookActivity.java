package cn.zsmy.akm.shared;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;

import cn.wei.library.activity.SystemBarTintManager;
import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.chat.utils.Constants;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * 图片滑动查看
 * @author yutaotao
 * Created by Administrator on 2016/3/4.
 */
public class PicturelookActivity extends BaseTitleActivity {

    private ViewPager mForumPager;//页卡内容

    private String imageUrl;
    private String imagePath;
    private String imagePics;//图片url地址

    private ProgressBar mPictureLoadingPb;

    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    private LayoutInflater inflater;
    private PictureAdapter mAdapter;
    private ImageView mPicturePV;
    private int index=0;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(PicturelookActivity.this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.black);
        }
    }

    /**
     * 通知栏变色处理
     *
     * @param on
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public static Intent getIntent(Context context, String imageUrl, String imagePath, String pics, int position) {
        Intent intent = new Intent(context, PicturelookActivity.class);
        if (imageUrl != null) {
            intent.putExtra(Constants.KEY_IMAGE_URL, imageUrl);
        }
        if (imagePath != null) {
            intent.putExtra(Constants.KEY_IMAGE_PATH, imagePath);
        }
        if (pics != null) {
            intent.putExtra(Constants.KEY_IMAGE_PICS, pics);
        }
        if (position < 0) {
            intent.putExtra(Constants.KEY_IMAGE_INDEX, position);
        }
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_forum_picture);
        initializeConfig();
    }

    @Override
    protected void initializeView() {
        mForumPager = (ViewPager) findViewById(R.id.mViewPager);
    }

    @Override
    protected void initializeData() {
        imageUrl = getIntent().getStringExtra(Constants.KEY_IMAGE_URL);
        imagePath = getIntent().getStringExtra(Constants.KEY_IMAGE_PATH);
        imagePics = getIntent().getStringExtra(Constants.KEY_IMAGE_PICS);
        final String[] picPaths = imagePics.split(",");
        List<View> list = new ArrayList<>();
        inflater = LayoutInflater.from(this);
        //将view添加到list中去
        for (int i = 0; i < picPaths.length; i++) {
            View item = inflater.inflate(R.layout.item_picture, null);
            mPicturePV = (ImageView) item.findViewById(R.id.mPicturePV);
            String url = UrlHelpper.FILE_IP + picPaths[i];
            ImageLoader.getInstance().displayImage(url, mPicturePV);
            list.add(item);
        }
        mForumPager.setAdapter(new PictureAdapter(list));
        mForumPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("查看的第" + position + "张图片");
                index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initializeConfig() {
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_loading_img) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.bg_transparent)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ic_not_network) // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .build();// 构建完成
    }

    private class PictureAdapter extends PagerAdapter {

        private List<View> mList;

        public PictureAdapter(List<View> list) {
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position), 0);
            return mList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }

}
