package cn.zsmy.akm.doctor.home;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.update.UmengUpdateAgent;

import cn.wei.library.activity.BaseActivity;
import cn.zsmy.akm.doctor.adapter.MyExpandableAdapter;
import cn.zsmy.akm.doctor.admissions.ContactOfficeActivity;
import cn.zsmy.akm.doctor.bean.DoctorCenter;
import cn.zsmy.akm.doctor.bean.DoctorInfo;
import cn.zsmy.akm.doctor.chat.im.IMPushManager;
import cn.zsmy.akm.doctor.conversation.MyCollectionActivity;
import cn.zsmy.akm.doctor.conversation.MyPatientActivity;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.learning.CaseListActivity;
import cn.zsmy.akm.doctor.learning.ScholarshipActivity;
import cn.zsmy.akm.doctor.messageCenter.NoticeCenter;
import cn.zsmy.akm.doctor.messageCenter.SettingActivity;
import cn.zsmy.akm.doctor.profile.ProfileCenterActivity;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.dialog.ChooseDialog;
import cn.zsmy.doctor.R;

/**
 * 主界面
 * Created by wanjingyan
 * on 2015/12/11 17:13.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private TextView doctorClinic, mBalance, mIntegral;
    private ExpandableListView mDoctorsOperation;
    private MyExpandableAdapter adapter;
    private DoctorCenter doctorCenter;

    @Override
    public boolean hasAppKilled() {
        return false;
    }

    @Override
    protected void setContentView() {
        UmengUpdateAgent.setUpdateOnlyWifi(false);//所有网络环境下都提醒
        UmengUpdateAgent.update(this);//设置自动更新
        UmengUpdateAgent.setUpdateCheckConfig(false);//是否显示调试信息
        UmengUpdateAgent.setDeltaUpdate(false);//设置全量更新
        setContentView(R.layout.activity_home);
        MyApplication.getInstance().addActivity(this);

    }

    @Override
    protected void initializeView() {
        findViewById(R.id.setting).setOnClickListener(this);
        findViewById(R.id.message_remind).setOnClickListener(this);
        findViewById(R.id.qr_code).setOnClickListener(this);
        doctorClinic = ((TextView) findViewById(R.id.doctor_clinic));
        mBalance = ((TextView) findViewById(R.id.balance));
        mIntegral = ((TextView) findViewById(R.id.integral));
        mDoctorsOperation = ((ExpandableListView) findViewById(R.id.doctors_operation));
        mDoctorsOperation.setGroupIndicator(null);
        mDoctorsOperation.setDivider(null);
    }

    @Override
    protected void initializeData() {
        adapter = new MyExpandableAdapter(this);
        mDoctorsOperation.setAdapter(adapter);
        mDoctorsOperation.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return TurnActivity(groupPosition, childPosition);
            }
        });
        mDoctorsOperation.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition == 4) {
                    TurnActivity(groupPosition, 0);
                }

                for (int i = 0; i < adapter.getGroupCount(); i++) {
                    if (groupPosition != i) {
                        mDoctorsOperation.collapseGroup(i);
                    }
                }
            }

        });
        mDoctorsOperation.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (groupPosition == 4) {
                    startActivity(ProfileCenterActivity.getIntent(HomeActivity.this));
                    return true;
                } else {
                    return false;
                }
            }
        });
        getDoctorInfo();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (MyApplication.getProfile() != null) {
            if (MyApplication.getProfile().getUserId() != null) {
                IMPushManager.getInstance(getApplicationContext()).startPush();
                IMPushManager.getInstance(getApplicationContext()).setTags(
                        MyApplication.getProfile().getUserId());
            }
        }
    }

    /**
     * Activity跳转
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    private boolean TurnActivity(int groupPosition, int childPosition) {
        Intent intent = ContactOfficeActivity.getIntent(HomeActivity.this);
        switch (groupPosition) {
            case 0:
                //接诊室
                switch (childPosition) {
                    case 0:
                        //普通患者
                        intent.putExtra("TYPE", 0);
                        startActivity(intent);
                        break;
                    case 1:
                        //VIP患者
                        intent.putExtra("TYPE", 1);
                        startActivity(intent);
                        break;
                    case 2:
                        //留言中心
//                        startActivity(FeedbackActivity.getIntent(HomeActivity.this));
                        Toast.makeText(this, "此功能还没开放", Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case 1:
                //病历库
                switch (childPosition) {
                    case 0:
                        //我的病人
                        startActivity(MyPatientActivity.getIntent(HomeActivity.this));
                        break;
                    case 1:
                        //收藏病例
                        startActivity(MyCollectionActivity.getIntent(HomeActivity.this));
                        break;
                }
                break;
            case 2:
                //互动学习
                switch (childPosition) {
                    case 0:
                        //病例
                        startActivity(CaseListActivity.getIntent(this));
                        break;
                    case 1:
                        //学术圈
                        startActivity(ScholarshipActivity.getIntent(this));
                        break;
                }
                break;
            case 3:
                //日程安排
                break;
            case 4:
                //个人中心
                Log.i("TAG", groupPosition + "");
                startActivity(ProfileCenterActivity.getIntent(this));
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting:
                startActivity(SettingActivity.getIntent(this));
                break;
            case R.id.message_remind:
                startActivity(NoticeCenter.getIntent(this));
                break;
            case R.id.qr_code:
                Intent intent = ShareQRCode.getIntent(this, -1);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProfileInfo();
    }

    /**
     * 获取积分和余额信息
     */
    private void getProfileInfo() {
        DoctorCenter.DataEntity entity = MyApplication.getEntity();
        if (entity != null) {
            int balance = (int) entity.getBalance();
            int score = entity.getScore();
            mBalance.setText(String.valueOf(balance));
            mIntegral.setText(String.valueOf(score));
            String name = entity.getName();
            if (name != null) {
                doctorClinic.setText(name + "医生的个人诊所");
            } else {
                doctorClinic.setText("医生的个人诊所");
            }
        } else {
            mBalance.setText("0");
            mIntegral.setText("0");
        }
    }


    private void getDoctorInfo() {
        Request request = new Request(UrlHelper.DOCTOR_PERSONAL_INFO, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DoctorInfo doctorInfo = JsonParser.deserializeFromJson(result, DoctorInfo.class);
                DoctorInfo.DataEntity data = doctorInfo.getData();
                MyApplication.setDocInfo(data);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ChooseDialog dialog = new ChooseDialog(this, 2);
            dialog.show();
            //退出程序的代码
            return true;
        }
        return true;
    }
}
