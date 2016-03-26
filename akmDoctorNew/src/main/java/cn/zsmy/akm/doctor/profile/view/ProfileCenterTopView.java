package cn.zsmy.akm.doctor.profile.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.zsmy.akm.doctor.bean.DoctorCenter;
import cn.zsmy.akm.doctor.bean.DoctorInfo;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.profile.ProfileInfoActivity;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sanz on 2016/1/4 14:02
 */
public class ProfileCenterTopView extends LinearLayout implements View.OnClickListener {
    private Context context;
    private TextView mDoctorIntegral, mDoctorIncome, mDoctorName, mDoctorAccount, authenticateState;
    private CircleImageView head_pic;
    private LinearLayout mProfileInfo_lin;
    private ImageView phone_call;
    private DoctorCenter.DataEntity data;

    public ProfileCenterTopView(Context context) {
        super(context);
        this.context = context;
        initializeView();
    }

    public ProfileCenterTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProfileCenterTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //    public ProfileCenterTopView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    private void initializeView() {
        LayoutInflater.from(context).inflate(R.layout.layout_profile_center_top, this);
        mProfileInfo_lin = ((LinearLayout) findViewById(R.id.doctor_head_portrait));
        phone_call = (ImageView) findViewById(R.id.phone_customer);
        mDoctorIntegral = ((TextView) findViewById(R.id.doctor_integral));
        mDoctorIncome = ((TextView) findViewById(R.id.doctor_income));
        authenticateState = ((TextView) findViewById(R.id.authenticate_state));
        mDoctorName = ((TextView) findViewById(R.id.doctor_name));
        mDoctorAccount = ((TextView) findViewById(R.id.doctor_account));
        head_pic = (CircleImageView) findViewById(R.id.head_pic);
        mProfileInfo_lin.setOnClickListener(this);
        phone_call.setOnClickListener(this);
        head_pic.setBorderWidth(5);
        head_pic.setBorderColor(getResources().getColor(R.color.whit_transparent));
    }

    public void initializeData(DoctorCenter.DataEntity data) {
        this.data = data;
        if (!TextUtils.isEmpty(data.getName())) {
            mDoctorName.setText(data.getName());
        } else {
            mDoctorName.setText(null);
        }
        if (data == null) {
            mDoctorIntegral.setText("0");
            mDoctorIncome.setText("0");
        } else {
            mDoctorIntegral.setText(String.valueOf(data.getScore()));
            int money = (int) data.getBalance();
            mDoctorIncome.setText(String.valueOf(money));
        }
        if (MyApplication.getProfile() != null && MyApplication.getProfile().getData() != null && MyApplication.getProfile().getData().getUsername() != null) {
            mDoctorAccount.setText("医生账号：" + MyApplication.getProfile().getData().getUsername());
        } else if (data.getName() != null) {
            mDoctorAccount.setText("医生账号：" + data.getName());
        } else {
            mDoctorAccount.setText("医生账号：");
        }
        if (data.getAuthFlag() != null) {
            String authFlag = String.valueOf(data.getAuthFlag());
            int flag = Integer.valueOf(authFlag);
            switch (flag) {
                case 3:
                    authenticateState.setText("已认证");
                    break;
                default:
                    authenticateState.setText("未认证");
                    break;
            }
        } else {
            authenticateState.setText("未认证");
        }
        DoctorInfo.DataEntity docInfo = MyApplication.getDocInfo();
        if (docInfo != null && docInfo.getDoctorAuth().size() != 0) {
            List<DoctorInfo.DataEntity.DoctorAuthEntity> doctorAuth = docInfo.getDoctorAuth();
            for (DoctorInfo.DataEntity.DoctorAuthEntity auth : doctorAuth) {
                if (auth.getZType().equals("4")) {
                    ImageLoader.getInstance().displayImage(UrlHelper.IP + auth.getAuthPic(), head_pic);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.doctor_head_portrait:
                context.startActivity(ProfileInfoActivity.getIntent(context, String.valueOf(data.getAuthFlag())));
                break;
            case R.id.phone_customer:
//                intent.setAction(Intent.ACTION_CALL);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "400-671-8821"));
                TelephonyManager phone = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                PackageManager pkm = context.getPackageManager();
                boolean b = PackageManager.PERMISSION_GRANTED == pkm.checkPermission("android.permission.CALL_PHONE", context.getPackageName());
                if (b) {
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "未开启电话权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
