package cn.zsmy.akm.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.wei.library.fragment.BaseFragment;
import cn.zsmy.akm.R;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.MyCountTime;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * Created by wanjingyan on 2015/11/18.
 * 填写验证码
 */
public class InputSecurityCodeFragment extends BaseFragment implements View.OnClickListener, TextWatcher {
    private EditText mInputCodeEdt;
    private ReplaceInputCode replaceInputCode;
    private static InputSecurityCodeFragment codeFragment;
    private int code;
    private Button mInputBtn;
    private String phone;
    private static String TAG = "MyApplication";
    private TextView mCountDown;
    private MyCountTime myCountTime;
    private TextView mVoiceCode;
    private String url = null;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_register_input_security_code;
    }

    //通过传递一个值判断是要进行设置密码操作还是重置密码操作
    public static InputSecurityCodeFragment newInstance(int code, String phone) {
        codeFragment = new InputSecurityCodeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("code", code);
        bundle.putString("phone", phone);
        codeFragment.setArguments(bundle);
        return codeFragment;
    }


    @Override
    protected void initializeView(View v) {
        v.findViewById(R.id.input_security_code_cancel).setOnClickListener(this);
        mInputCodeEdt = ((EditText) v.findViewById(R.id.security_code_edit));
        mInputBtn = ((Button) v.findViewById(R.id.input_code_finish));
        mCountDown = ((TextView) v.findViewById(R.id.countdown));
        mVoiceCode = ((TextView) v.findViewById(R.id.send_voice_security_code));
        mInputBtn.setOnClickListener(this);
        mVoiceCode.setOnClickListener(this);
        mInputCodeEdt.setFocusable(true);
        mInputCodeEdt.addTextChangedListener(this);
        code = getArguments().getInt("code");
        phone = getArguments().getString("phone");
        myCountTime = new MyCountTime(60000, 1000, mCountDown, mVoiceCode);
        myCountTime.start();
        mInputBtn.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        String securityCode = mInputCodeEdt.getText().toString();
        switch (v.getId()) {
            case R.id.input_security_code_cancel:
                //返回上一页面
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.send_voice_security_code:
                myCountTime.onStart();
                switch (code){
                    case 0:
                        url = UrlHelpper.SEND_REGCODE + "?username=" + phone + "&flag=" + Constants.SEND_VOICE_SECURITY_CODE + "&platform=akeman";
                        break;
                    case 1:
                        url = UrlHelpper.FIND_MIMA_CODE_URL+"?username=" + phone + "&flag=" + Constants.SEND_VOICE_SECURITY_CODE + "&platform=akeman";
                        break;
                }
                getCode(url);
                break;
            case R.id.input_code_finish:

                if (replaceInputCode != null) {
                    switch (code) {
                        case Constants.KEY_FROM_REGISTER:
                            url = UrlHelpper.VERIFICATION +"?username="+ phone + "&verifyCode=" + securityCode + "&platform=akeman";
                            break;
                        case Constants.KEY_FROM_LOGIN:
                            url = UrlHelpper.RESET_PWD_VERIFICATION+"?username=" + phone + "&verifyCode=" + securityCode + "&platform=akeman";
                            break;
                    }
                    verification(url, securityCode, code);
                }
                break;
        }
    }

    /**
     * 验证验证码
     *
     * @param url
     * @param securityCode
     * @param formCode
     */
    private void verification(String url, final String securityCode, final int formCode) {
        final Request request = new Request(url, getActivity());
        Log.d("TAG", url);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                //判断是重置密码还是设置密码
                //传过来的是0就启动设置密码界面,1就启动重置密码
                switch (formCode) {
                    case Constants.KEY_FROM_REGISTER:
                        replaceInputCode.replaceToSetPwd(phone, securityCode);
                        break;
                    case Constants.KEY_FROM_LOGIN:
                        replaceInputCode.replaceToResetPad(phone, securityCode);
                        break;
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    /**
     * 获取语音验证码
     *
     * @param url
     */
    private void getCode(String url) {
        Request request = new Request(url, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {

            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(mInputCodeEdt.getText().toString())) {
            mInputBtn.setEnabled(false);
        } else if (mInputCodeEdt.getText().toString().length() < 4) {
            mInputBtn.setEnabled(false);
        } else {
            mInputBtn.setEnabled(true);
//            myCountTime.onFinish();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    public interface ReplaceInputCode {
        void replaceToSetPwd(String phone, String securityCode);

        void replaceToResetPad(String phone, String securityCode);
    }

    public void setInputCodeToReception(ReplaceInputCode replaceInputCode) {
        this.replaceInputCode = replaceInputCode;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onStop() {
        super.onStop();
//        myCountTime.cancel();
    }
}
