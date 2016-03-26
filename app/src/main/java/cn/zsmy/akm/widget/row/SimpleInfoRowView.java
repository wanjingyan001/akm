package cn.zsmy.akm.widget.row;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.row.tool.RowClassEnum;
import de.hdodenhof.circleimageview.CircleImageView;

public class SimpleInfoRowView extends BaseRowView implements OnClickListener {
    protected ImageLoader imageLoader;
    protected DisplayImageOptions options;
    private static CircleImageView mWidgetRowIconImg;
    private OnRowClickListener listener;
    private SimpleInfoRowDescriptor rowDescriptor;
    private static TextView mInfoAccountLabel;
    private ImageView mChangePan;
    private RelativeLayout editLayout;

    public SimpleInfoRowView(Context context, RowClassEnum clazz) {
        super(context, clazz);
        initializeView(context);
    }

    public SimpleInfoRowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    public SimpleInfoRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public SimpleInfoRowView(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_simple_info_row, this);
        mWidgetRowIconImg = (CircleImageView) findViewById(R.id.mWidgetRowIconImg);
        mInfoAccountLabel = (TextView) findViewById(R.id.mInfoAccountLabel);
        mChangePan = ((ImageView) findViewById(R.id.change_user_info));
        editLayout = ((RelativeLayout) findViewById(R.id.edit_layout));
        if (MyApplication.getProfile() != null && MyApplication.getProfile().getUserId() != null) {
            mChangePan.setVisibility(VISIBLE);
        } else {
            mChangePan.setVisibility(GONE);
        }
    }

    @Override
    public void initializeData(BaseRowDescriptor baseRowDescriptor, OnRowClickListener listener) {
        imageLoader = ImageLoader.getInstance();
        this.listener = listener;
        this.rowDescriptor = (SimpleInfoRowDescriptor) baseRowDescriptor;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_doctor_head_images) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.ic_doctor_head_images)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ic_doctor_head_images) // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
                .bitmapConfig(Config.RGB_565)// 设置图片的解码类型//
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(600))// 是否图片加载好后渐入的动画时间
                .build();// 构建完成
    }

    @Override
    public void notifyDataChanged() {
        if (rowDescriptor != null) {
            if (rowDescriptor.info == null) {
                mInfoAccountLabel.setText("登录/注册");
                mWidgetRowIconImg.setImageResource(R.drawable.ic_doctor_head_images);
            } else {
                mInfoAccountLabel.setText(rowDescriptor.info.account);
                imageLoader.displayImage(rowDescriptor.info.iconUrl, mWidgetRowIconImg, options);
            }
            setBackgroundResource(R.drawable.widgets_general_row_select);
            mWidgetRowIconImg.setOnClickListener(this);
            editLayout.setOnClickListener(this);
        } else {
            setVisibility(View.GONE);
        }
    }

    public static void setUserHead(String path) {
        Log.d("TAG+++++++++", path);
        ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + path, mWidgetRowIconImg);
    }


    public static void setUserName(String name) {
        mInfoAccountLabel.setText(name);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onRowClick(this, rowDescriptor.action);
        }
    }

}
