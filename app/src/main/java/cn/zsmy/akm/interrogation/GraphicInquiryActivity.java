package cn.zsmy.akm.interrogation;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.DoctorDetail;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.profile.RechargeActivity;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.UrlHelpper;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 图文问诊界面
 * Created by Administrator on 2016/1/27.
 */
public class GraphicInquiryActivity extends BaseTitleActivity implements View.OnClickListener {
    private CircleImageView doctorHead;
    private TextView doctorName;
    private TextView inquiryPrice;
    private TextView priceNumber;
    private TextView balance;
    private Button confirmPurchase;
    private DoctorDetail.DataEntity doctor;
    private int validAmount;
    private String amt;
    private String s;

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_graphic_inquiry);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        doctorHead = ((CircleImageView) findViewById(R.id.doctoe_head_image));
        doctorName = ((TextView) findViewById(R.id.doctor_name));
        inquiryPrice = ((TextView) findViewById(R.id.inquiry_price));
        priceNumber = ((TextView) findViewById(R.id.price_number));
        balance = ((TextView) findViewById(R.id.user_balance_graphic));
        findViewById(R.id.recharge).setOnClickListener(this);
        confirmPurchase = ((Button) findViewById(R.id.confirm_purchase));
        confirmPurchase.setOnClickListener(this);
    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        setTitle("图文问诊");
        doctorHead.setBorderWidth(1);
        doctorHead.setBorderColor(R.color.sub_title);
        doctor = ((DoctorDetail.DataEntity) getIntent().getSerializableExtra("doctor"));
        doctorName.setText(doctor.getName());
        amt = doctor.getPrice().get(0).getAmt();
        s = amt.replace(amt.substring(amt.indexOf('.'), amt.length()), "元");
        priceNumber.setText(s);
        validAmount = MyApplication.getProfileDetails().getValidAmount();
        balance.setText(String.valueOf(validAmount));
        List<DoctorDetail.DataEntity.DoctorAuthEntity> doctorAuth = doctor.getDoctorAuth();
        if (doctorAuth != null && doctorAuth.size() > 0) {
            for (DoctorDetail.DataEntity.DoctorAuthEntity auth : doctorAuth) {
                if (auth.getZType().equals("4")) {
                    if (auth.getAuthPic() != null) {
                        ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + auth.getAuthPic(), doctorHead);
                    }
                }
            }
        }
    }

    /**
     * 从医生详情进入
     *
     * @param context
     * @param entity  医生
     * @return
     */
    public static Intent getIntent(Context context, DoctorDetail.DataEntity entity) {
        Intent intent = new Intent(context, GraphicInquiryActivity.class);
        intent.putExtra("doctor", entity);
        return intent;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recharge:
                startActivity(RechargeActivity.newIntent(this));
                break;
            case R.id.confirm_purchase:
                //等待问诊开始
                if (validAmount >= Integer.valueOf(amt.substring(0, amt.indexOf('.')))) {
                    Intent intent1 = InterrogationInputActivity.getIntent(this, 1, doctor.getId(), Constants.CHAT_FLAG_OF_CONSTACTS_PHOTO_INFORMATION, Constants.CHAT_TYPES_OF_VIP);
                    startActivity(intent1);
                } else {
                    Toast.makeText(this, "您的余额不足，请充值", Toast.LENGTH_SHORT).show();
                }
//                loadCreatCase();
                break;
        }
    }
}
