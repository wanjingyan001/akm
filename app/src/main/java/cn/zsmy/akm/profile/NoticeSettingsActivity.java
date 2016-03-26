package cn.zsmy.akm.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.utils.Constants;

/**
 * 通知设置
 * Created by qinwei on 2015/11/24 17:21
 */
public class NoticeSettingsActivity extends BaseTitleActivity implements CompoundButton.OnCheckedChangeListener {
    private CheckBox mReplyBtn;
    private CheckBox mAdvisoryBtn;
    private CheckBox mSpreadBtn;
    private SharedPreferences preferences;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_profile_notice_setting);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mReplyBtn = ((CheckBox) findViewById(R.id.reply_switch_btn));
        mAdvisoryBtn = ((CheckBox) findViewById(R.id.advisory_switch_btn));
        mSpreadBtn = ((CheckBox) findViewById(R.id.spread_switch_btn));
        mReplyBtn.setOnCheckedChangeListener(this);
        mAdvisoryBtn.setOnCheckedChangeListener(this);
        mSpreadBtn.setOnCheckedChangeListener(this);
        preferences = getSharedPreferences("notice_setting", MODE_PRIVATE);
    }


    @Override
    protected void onStart() {
        super.onStart();
        boolean mReply = preferences.getBoolean("mReplyBtn", true);
        boolean mAdvisory = preferences.getBoolean("mAdvisoryBtn", true);
        boolean mSpread = preferences.getBoolean("mSpreadBtn", true);
        mReplyBtn.setChecked(mReply);
        mAdvisoryBtn.setChecked(mAdvisory);
        mSpreadBtn.setChecked(mSpread);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("mReplyBtn", mReplyBtn.isChecked());
        edit.putBoolean("mAdvisoryBtn", mAdvisoryBtn.isChecked());
        edit.putBoolean("mSpreadBtn", mSpreadBtn.isChecked());
        edit.commit();
    }

    @Override
    protected void initializeData() {
        setTitle(getString(R.string.mProfileNoticeSettingLabel));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("TAG", buttonView.getId() + "------" + isChecked);
        Intent intent = new Intent();
        intent.setAction(Constants.RECEIVE_NOTIFICATION);
        switch (buttonView.getId()) {
            case R.id.reply_switch_btn:
                //  通知消息中心是否接受回复通知
                if (isChecked) {
                    intent.putExtra("TYPE", Constants.OPEN_REPLY_NOTICE);
                } else {
                    intent.putExtra("TYPE", Constants.CLOSE_REPLY_NOTICE);
                }
                break;
            case R.id.advisory_switch_btn:
                //  通知消息中心是否接受咨询消息
                if (isChecked) {
                    intent.putExtra("TYPE", Constants.OPEN_ADVISORY_NOTICE);
                } else {
                    intent.putExtra("TYPE", Constants.CLOSE_ADVISORY_NOTICE);
                }
                break;
            case R.id.spread_switch_btn:
                //  通知消息中心是否接受推广消息
                if (isChecked) {
                    intent.putExtra("TYPE", Constants.OPEN_SPREAD_NOTICE);
                } else {
                    intent.putExtra("TYPE", Constants.CLOSE_SPREAD_NOTICE);
                }
                break;
        }
        sendBroadcast(intent);
    }
}
