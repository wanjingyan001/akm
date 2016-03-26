package cn.zsmy.akm.doctor.messageCenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.doctor.R;

/**
 * 推送设置
 * Created by wanjingyan
 * on 2015/12/15 18:07.
 */
public class PushSettingActivity extends BaseTitleActivity implements CompoundButton.OnCheckedChangeListener {
    private CheckBox replyRemind;
    private CheckBox studyRemind;
    private SharedPreferences setting;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_push);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        replyRemind = ((CheckBox) findViewById(R.id.reply_remind));
        studyRemind = ((CheckBox) findViewById(R.id.study_remind));
        replyRemind.setOnCheckedChangeListener(this);
        studyRemind.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.message_notification));
        setting = getSharedPreferences("Doctor_setting", MODE_PRIVATE);
        replyRemind.setChecked(setting.getBoolean("replyRemind", true));
        studyRemind.setChecked(setting.getBoolean("studyRemind", true));

    }

    public static Intent getIntent(Context context) {
        return new Intent(context, PushSettingActivity.class);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor edit = setting.edit();
        edit.putBoolean("replyRemind", replyRemind.isChecked());
        edit.putBoolean("studyRemind", studyRemind.isChecked());
        edit.commit();
    }

    /**
     * Called when the checked state of a compound button has changed.
     *
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Intent intent = new Intent();
        intent.setAction(Constants.RECEIVE_NOTIFICATION);
        switch (buttonView.getId()) {
            case R.id.reply_remind:
                if (isChecked) {
                    intent.putExtra("TYPE", Constants.OPEN_REPLY_NOTICE);
                } else {
                    intent.putExtra("TYPE", Constants.CLOSE_REPLY_NOTICE);
                }
                break;
            case R.id.study_remind:
                if (isChecked) {
                    intent.putExtra("TYPE", Constants.OPEN_STUDY_NOTICE);
                } else {
                    intent.putExtra("TYPE", Constants.CLOSE_STUDY_NOTICE);
                }
                break;
        }
        sendBroadcast(intent);
    }
}
