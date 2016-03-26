package cn.zsmy.akm.doctor.profile.edit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import cn.wei.library.activity.SystemBarTintManager;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.doctor.R;

/**
 * Created by Administrator on 2016/3/3.
 */
public class VideoActivity extends Activity{

    private GridView mVideoGrid;//网格显示缩略图

    private TextView mEditor;
    private ImageView mDown;

    private final int VIDEO_OPEN=1;//打开视频标记

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        //通知栏变色处理
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(true);
            SystemBarTintManager tintManager=new SystemBarTintManager(VideoActivity.this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.black);
        }
        //锁定屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView();
        initializeData();

    }


    protected void setContentView() {
        setContentView(R.layout.activity_profile_video);
        MyApplication.getInstance().addActivity(this);
    }


    protected void initializeData() {
        mDown= (ImageView) findViewById(R.id.profile_down);
        mEditor= (TextView) findViewById(R.id.profile_editor);
        mVideoGrid= (GridView) findViewById(R.id.profile_add_video);

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

}
