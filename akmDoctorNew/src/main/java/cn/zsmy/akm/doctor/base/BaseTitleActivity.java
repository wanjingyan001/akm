package cn.zsmy.akm.doctor.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import cn.wei.library.activity.BaseActivity;
import cn.zsmy.akm.doctor.home.MainActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.doctor.R;


/**
 * Created by qinwei on 2015/10/22 22:26
 * email:qinwei_it@163.com
 */
public abstract class BaseTitleActivity extends BaseActivity {
    protected TextView mToolBarTitleLabel;
    protected Toolbar toolbar;
    private CharSequence title;


    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        //通知栏变色处理
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setNavigationBarTintResource(R.color.app_main_color);
//        }
    }


//    /**
//     * 通知栏变色处理
//     * @param on
//     */
//    @TargetApi(19)
//    private void setTranslucentStatus(boolean on) {
//        Window win = getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }

    @Override
    public boolean hasAppKilled() {
        if (MyApplication.getInstance().getAppState() != -1) {
            return false;
        }
        return true;
    }

    @Override
    public void protectApp() {
        super.protectApp();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.KEY_PROTECT_APP, true);
        startActivity(intent);
        finish();
    }

    @Override
    protected void initializeView() {
        if (findViewById(R.id.toolbar) != null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (isCanBack()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            mToolBarTitleLabel = (TextView) findViewById(R.id.mToolBarTitleLabel);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        if (mToolBarTitleLabel != null && isCenter()) {
            mToolBarTitleLabel.setText(title);
            super.setTitle("");
        } else {
            super.setTitle(title);
        }
    }

    @Override
    protected void recoveryState(Bundle saveInstance) {
        super.recoveryState(saveInstance);
        if (findViewById(R.id.toolbar) != null) {
            setTitle(saveInstance.getString(Constants.KEY_TITLE));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!TextUtils.isEmpty(title)) {
            outState.putString(Constants.KEY_TITLE, title.toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
