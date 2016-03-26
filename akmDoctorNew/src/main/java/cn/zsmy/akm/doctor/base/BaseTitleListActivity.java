package cn.zsmy.akm.doctor.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import cn.wei.library.activity.BaseListActivity;
import cn.zsmy.akm.doctor.home.MainActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.doctor.R;

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
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.KEY_PROTECT_APP, true);
        startActivity(intent);
        finish();
    }

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
