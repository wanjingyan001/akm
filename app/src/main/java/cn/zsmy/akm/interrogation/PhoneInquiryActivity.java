package cn.zsmy.akm.interrogation;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.DoctorDetail;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.profile.RechargeActivity;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.UrlHelpper;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/1/27.
 */
public class PhoneInquiryActivity extends BaseTitleActivity implements View.OnClickListener {
    private CircleImageView doctorHead;
    private TextView doctorName;
    private TextView phoneNumber;
    private TextView druation;
    private TextView calls;
    private TextView unitPrice;
    private TextView balance;
    private DoctorDetail.DataEntity doctor;
    private int validAmount;
    private String amt;
    private TextView inquiryPrice;

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_phone_inquiry);
    }


    @Override
    protected void initializeView() {
        super.initializeView();
        doctorHead = ((CircleImageView) findViewById(R.id.doctoe_head_image));
        doctorName = ((TextView) findViewById(R.id.doctor_name));
        phoneNumber = ((TextView) findViewById(R.id.phone_number));
        inquiryPrice = ((TextView) findViewById(R.id.inquiry_price));
        druation = ((TextView) findViewById(R.id.druation));
        calls = ((TextView) findViewById(R.id.calls));
        unitPrice = ((TextView) findViewById(R.id.unit_price));
        balance = ((TextView) findViewById(R.id.user_balance_phone));
        findViewById(R.id.recharge).setOnClickListener(this);
        findViewById(R.id.confirm_purchase).setOnClickListener(this);
    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        setTitle("直拨电话");
        doctorHead.setBorderWidth(1);
        doctorHead.setBorderColor(R.color.sub_title);
        doctor = ((DoctorDetail.DataEntity) getIntent().getSerializableExtra("doctor"));
        if (doctor != null) {
            init(doctor);
        } else {
            String doctorId = getIntent().getStringExtra("doctorId");
            getDocInfo(doctorId);
        }

    }

    private void init(DoctorDetail.DataEntity doctor) {
        doctorName.setText(doctor.getName());
        phoneNumber.setText(doctor.getPhone());
        DoctorDetail.DataEntity.PriceEntity priceEntity = doctor.getPrice().get(0);
        amt = priceEntity.getAmt();
        String s = amt.replace(amt.substring(amt.indexOf('.'), amt.length()), "元");
        druation.setText(priceEntity.getUnit());
        calls.setText(s);
        inquiryPrice.setText(s);
        int i = Integer.parseInt(amt.substring(0, amt.indexOf('.'))) / Integer.parseInt(priceEntity.getUnit().substring(0, 1));
        unitPrice.setText(i + "元/分钟");
        List<DoctorDetail.DataEntity.DoctorAuthEntity> doctorAuth = doctor.getDoctorAuth();
        if (doctorAuth != null && doctorAuth.size() > 0) {
            for (DoctorDetail.DataEntity.DoctorAuthEntity auth : doctorAuth) {
                if (auth.getZType()!=null&&auth.getZType().equals("4")) {
                    if (auth.getAuthPic() != null) {
                        ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + auth.getAuthPic(), doctorHead);
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        validAmount = MyApplication.getProfileDetails().getValidAmount();
        balance.setText(String.valueOf(validAmount));
    }

    public static Intent getIntent(Context context, DoctorDetail.DataEntity entity) {
        Intent intent = new Intent(context, PhoneInquiryActivity.class);
        intent.putExtra("doctor", entity);
        return intent;
    }

    private void getDocInfo(String doctorId) {
        String url = UrlHelpper.DOCTOR_DETAILS + "?id=" + doctorId;
        Request request = new Request(url, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DoctorDetail doctorDetail = JsonParser.deserializeFromJson(result, DoctorDetail.class);
                doctor = doctorDetail.getData();
                init(doctor);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recharge:
                startActivity(RechargeActivity.newIntent(this));
                break;
            case R.id.confirm_purchase:
                if (validAmount >= Integer.valueOf(amt.substring(0, amt.indexOf('.')))) {
                    Intent intent2 = InterrogationInputActivity.getIntent(this, 1, doctor.getId(), Constants.CHAT_FLAG_OF_CONSTACTS_PHONE_INFORMATION, Constants.CHAT_TYPES_OF_VIP);
                    startActivity(intent2);
                } else {
                    Toast.makeText(this, "您的余额不足，请充值", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
