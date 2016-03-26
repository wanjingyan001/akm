package cn.zsmy.akm.doctor.messageCenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.doctor.R;

/**
 * 通知中心
 * Created by wanjingyan
 * on 2015/12/15 17:30.
 */
public class NoticeCenter extends BaseTitleActivity implements View.OnClickListener {

    private ImageView patientRed;
    private ImageView systemRed;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_notice_center);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        MyApplication.getInstance().addActivity(this);
        findViewById(R.id.patient_message_layout).setOnClickListener(this);
        findViewById(R.id.system_messages_layout).setOnClickListener(this);
        patientRed = ((ImageView) findViewById(R.id.patient_red_pation));
        systemRed = ((ImageView) findViewById(R.id.system_red_pation));
    }

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.notice_center));
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, NoticeCenter.class);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent intent = NoticeDetail.getIntent(NoticeCenter.this);
        switch (v.getId()) {
            case R.id.patient_message_layout:
                intent.putExtra("TYPE", 0);
                patientRed.setVisibility(View.GONE);
                break;
            case R.id.system_messages_layout:
                intent.putExtra("TYPE", 1);
                systemRed.setVisibility(View.GONE);
                break;
        }
        startActivity(intent);
    }
}
