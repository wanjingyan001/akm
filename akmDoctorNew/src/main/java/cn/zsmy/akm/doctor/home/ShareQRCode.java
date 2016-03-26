package cn.zsmy.akm.doctor.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.bean.DoctorCenter;
import cn.zsmy.akm.doctor.bean.DoctorInfo;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 分享二维码
 * Created by wanjingyan
 * on 2015/12/15 10:53.
 */
public class ShareQRCode extends BaseTitleActivity implements View.OnClickListener {
    private ImageView QRCode;
    private CircleImageView mHeadImg;
    private TextView mName;
    private TextView mHospital;
    private String doctorName;
    private DoctorCenter doctor;
    private TextView checkReward;
    private TextView friendReward;
    private int type;
    private TextView shareQRTitle;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home_share);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        findViewById(R.id.share_my_qr).setOnClickListener(this);
        shareQRTitle = ((TextView) findViewById(R.id.share_qr_title));
        checkReward = ((TextView) findViewById(R.id.check_in_reward));
        checkReward.setOnClickListener(this);
        friendReward = ((TextView) findViewById(R.id.friend_reward));
        friendReward.setOnClickListener(this);
        QRCode = ((ImageView) findViewById(R.id.qr_code));
        QRCode.setOnClickListener(this);
        mHeadImg = ((CircleImageView) findViewById(R.id.my_head_image));
        mName = ((TextView) findViewById(R.id.my_name));
        mHospital = ((TextView) findViewById(R.id.my_hospital));
    }

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.my_QR));
        try {
            getDoctorInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        type = getIntent().getIntExtra("TYPE", -1);
        switch (type) {
            case 0:
                checkReward.setVisibility(View.VISIBLE);
                friendReward.setVisibility(View.GONE);
                QRCode.setImageResource(R.mipmap.ic_akm_qr);
                break;
            case 1:
                checkReward.setVisibility(View.GONE);
                friendReward.setVisibility(View.VISIBLE);
                QRCode.setImageResource(R.mipmap.ic_doctor_qr);
                shareQRTitle.setText("让医生伙伴扫您的二维码向您报到");
                break;
            default:
                checkReward.setVisibility(View.VISIBLE);
                friendReward.setVisibility(View.VISIBLE);
                QRCode.setImageResource(R.mipmap.ic_akm_qr);
                break;
        }
    }

    public static Intent getIntent(Context context, int type) {
        Intent intent = new Intent(context, ShareQRCode.class);
        intent.putExtra("TYPE", type);
        return intent;
    }


    private void getDoctorInfo() {
        DoctorInfo.DataEntity docInfo = MyApplication.getDocInfo();
        mName.setText(docInfo.getName());
        mHospital.setText(docInfo.getHospital());
        if (docInfo != null && docInfo.getDoctorAuth().size() != 0) {
            List<DoctorInfo.DataEntity.DoctorAuthEntity> doctorAuth = docInfo.getDoctorAuth();
            for (DoctorInfo.DataEntity.DoctorAuthEntity auth : doctorAuth) {
                if (auth.getZType().equals("4")) {
                    ImageLoader.getInstance().displayImage(UrlHelper.IP + auth.getAuthPic(), mHeadImg);
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_my_qr:
                // TODO: 第三方分享

                break;
            case R.id.check_in_reward:

                break;
            case R.id.friend_reward:

                break;

        }
    }
}
