package cn.zsmy.akm.doctor.messageCenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.RadioGroup;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.utils.LoginUtils;
import cn.zsmy.akm.doctor.widget.dialog.ChooseDialog;
import cn.zsmy.doctor.R;

/**
 * 设置界面
 * Created by wanjingyan
 * on 2015/12/15 17:09.
 */
public class SettingActivity extends BaseTitleActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private RadioGroup inquiry;
    private SharedPreferences setting;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_setting);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    public boolean hasAppKilled() {

        return false;
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        findViewById(R.id.push_setting).setOnClickListener(this);
        findViewById(R.id.sign_out).setOnClickListener(this);
        inquiry = ((RadioGroup) findViewById(R.id.inquiry_notice_setting));
        inquiry.setOnCheckedChangeListener(this);
        setting = getSharedPreferences("Doctor_setting", MODE_PRIVATE);
    }

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.setting));
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, SettingActivity.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int notice_remind = setting.getInt("notice_remind", R.id.inquiry);
        inquiry.check(notice_remind);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.push_setting:
                startActivity(PushSettingActivity.getIntent(SettingActivity.this));
                break;
            case R.id.sign_out:
                LoginUtils.saveLoginInfo(this, null, null);
                ChooseDialog dialog = new ChooseDialog(this, 1);
                dialog.show();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor edit = setting.edit();
        edit.putInt("notice_remind", inquiry.getCheckedRadioButtonId());
        edit.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}
