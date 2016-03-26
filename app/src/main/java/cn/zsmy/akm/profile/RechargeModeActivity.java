package cn.zsmy.akm.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.text.DecimalFormat;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.chat.utils.Constants;
import cn.zsmy.akm.entity.PayInfo;
import cn.zsmy.akm.entity.ProfileDetails;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.FileCallback;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * 充值方式
 * Created by qinwei on 2015/11/24 17:17
 */
public class RechargeModeActivity extends BaseTitleActivity implements View.OnClickListener {
    private TextView mRechargeAmount;
    private TextView mAmountSpent;
    private String recharge;
    private String spend;
    private TextView mSpentMoney;
    private String id;
    private static final int SDK_PAY_FLAG = 1;

    private static final int SDK_CHECK_FLAG = 2;

//    private IWXAPI mWXAPI;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    Log.d("TAG", payResult.toString());
                    if (TextUtils.equals(resultStatus, "9000")) {
                        //再次获取用户个人中心信息
                        getUserCenter();
                        Toast.makeText(RechargeModeActivity.this, "支付成功",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(RechargeModeActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            if (TextUtils.equals(resultStatus, "4000")) {
                                Toast.makeText(RechargeModeActivity.this, "未检测到支付宝应用程序", Toast.LENGTH_SHORT).show();
                                Uri uri = Uri.parse("https://www.alipay.com/");
                                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                            } else
                                Toast.makeText(RechargeModeActivity.this, "支付失败",
                                        Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(RechargeModeActivity.this, "检查结果为：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_profile_recharge_mode);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        setTitle(getString(R.string.mProfileRechargeModeLabel));
        mRechargeAmount = ((TextView) findViewById(R.id.choose_recharge));
        mAmountSpent = ((TextView) findViewById(R.id.send_money_amount));
        mSpentMoney = ((TextView) findViewById(R.id.amount_spent));
        findViewById(R.id.play_with_zhifubao).setOnClickListener(this);
        findViewById(R.id.play_with_weixin).setOnClickListener(this);
    }

    @Override
    protected void initializeData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        recharge = intent.getStringExtra("recharge");
        spend = intent.getStringExtra("spend");
        mRechargeAmount.setText("充值" + recharge + "元");
        mAmountSpent.setText("赠送" + spend + "元");
        mSpentMoney.setText("支付金额：" + changeData(recharge) + "元");
//        mWXAPI= WXAPIFactory.createWXAPI(this, Constants.APP_ID);
//        mWXAPI.registerApp(Constants.APP_ID);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, RechargeModeActivity.class);
    }

    private String changeData(String data) {
        DecimalFormat df = new DecimalFormat("###.00");
        String format = df.format(Double.valueOf(data));
        return format;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_with_zhifubao:
                zhifubao(id);
                break;
            case R.id.play_with_weixin:
//                weixin();
                Toast.makeText(this, "暂未开通，敬请期待", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    //支付宝充值
    private void zhifubao(String id) {
        Request request = new Request(UrlHelpper.USER_RECHARGE + id, this);
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                System.out.println("result=" + result);
                PayInfo pay = JsonParser.deserializeFromJson(result, PayInfo.class);
                final String payInfo = pay.getData().getPayInfo();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(RechargeModeActivity.this);
                        // 调用支付接口，获取支付结果
                        String result = alipay.pay(payInfo, true);

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    //微信充值
    private void weixin() {
        String url= "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";
//        PayReq req=new PayReq();
//        req.appId=Constants.APP_ID;//应用ID
//        req.partnerId="1299960801";//商户号
//        req.prepayId="wx2016030714135299d102b6150120791664";//微信返回的支付交易会话ID
//        req.packageValue="Sign=WXPay";
//        req.nonceStr="6WijE1Zmx9m2syDF";//随机字符串
//        req.timeStamp=String.valueOf(DateUtils.getCurrentTime());//时间戳
//        req.sign=Constants.APP_SIGN;//应用签名
//        req.extData = "app data";
//        mWXAPI.sendReq(req);

        Request requset=new Request(url,this);
        requset.put("appid",Constants.APP_ID);
        requset.put("partnerid","1299960801");
        requset.put("prepayid","wx2016030714135299d102b6150120791664");
        requset.put("package","Sign=WXPay");
        requset.put("noncestr","6WijE1Zmx9m2syDF");
        requset.put("timestamp",String.valueOf(DateUtils.getCurrentTime()));
        requset.put("sign",Constants.APP_SIGN);
        requset.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                System.out.println("result="+result);

            }
        });
        RequestManager.getInstance().execute(this.toString(), requset);

    }


    private void getUserCenter() {
        Request request = new Request(UrlHelpper.PERSON_PROFILE, this);
        Log.d("TAG", UrlHelpper.PERSON_PROFILE);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                ProfileDetails profileDetails = JsonParser.deserializeFromJson(result, ProfileDetails.class);
                ProfileDetails.DataEntity profileDate = profileDetails.getData();
                if (profileDate != null) {
                    MyApplication.setProfileDetails(profileDate);
                    finish();
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }
}
