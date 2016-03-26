package cn.zsmy.akm.doctor.utils;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wanjingyan
 * on 2015/12/3 14:30.
 */
public class MyCountTime extends CountDownTimer {
    private TextView textView;
    private TextView voiceCode;

    public MyCountTime(long millisInFuture, long countDownInterval, TextView textView, TextView voiceCode) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
        this.voiceCode = voiceCode;
    }

    public void onStart() {
        textView.setVisibility(View.VISIBLE);
        voiceCode.setVisibility(View.GONE);
        start();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        textView.setText((millisUntilFinished / 1000) + "秒后重新获取验证码");
    }

    @Override
    public void onFinish() {
        textView.setVisibility(View.GONE);
        voiceCode.setVisibility(View.VISIBLE);
        textView.setText("60秒后重新获取验证码");
    }
}
