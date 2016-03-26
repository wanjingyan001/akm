package cn.zsmy.akm;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import cn.wei.library.activity.BaseListActivity;
import cn.wei.library.activity.SystemBarTintManager;
import cn.zsmy.akm.home.HomeActivity;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.utils.Constants;

/**
 * Created by qinwei on 2015/10/22 22:03
 * email:qinwei_it@163.com
 */
public abstract class BaseTitleListActivity extends BaseListActivity {
    private TextView mToolBarTitleLabel;
    private Toolbar toolbar;
    private CharSequence title;

    @Override
    public boolean hasAppKilled() {
        if (MyApplication.getInstance().getAppState() == -1) {
            return true;
        }
        return false;
    }

    @Override
    public void protectApp() {
        super.protectApp();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(Constants.KEY_PROTECT_APP, true);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        //通知栏变色处理
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//            SystemBarTintManager tintManager = new SystemBarTintManager(BaseTitleListActivity.this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(R.color.title);//通知栏所需颜色
//        }
    }

//    /**
//     * 通知栏变色处理
//     *
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
    protected void initializeView() {
        super.initializeView();
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
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(Constants.KEY_TITLE, title.toString());
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
