package cn.zsmy.akm.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import cn.zsmy.akm.BaseThirdPartyLoginSupportFragment;
import cn.zsmy.akm.R;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.salon.activity.MaxLengthWatcher;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.utils.Utils;

/**
 * Created by wanjingyan on 2015/11/17.
 * 使用手机号注册界面
 */
public class RegisteredFragment extends BaseThirdPartyLoginSupportFragment {
    private EditText mInputMobile;
    private ReplaceFromRegister replaceFromRegister;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_register_get_security_code;
    }

    @Override
    protected void initializeView(View v) {
        super.initializeView(v);
        v.findViewById(R.id.get_security_code_cancel).setOnClickListener(this);
        mInputMobile = ((EditText) v.findViewById(R.id.register_input_mobile));
        mInputMobile.addTextChangedListener(new MaxLengthWatcher(11, mInputMobile, getActivity()));
        v.findViewById(R.id.get_security_code_btn).setOnClickListener(this);
    }

    @Override
    public void doPlatformLogin(String openId, int platform, String icon, String nick) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.get_security_code_cancel:
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().finish();
                break;
            case R.id.get_security_code_btn:
                //前往填写验证码界面
                String phone = mInputMobile.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToast("手机号不能为空");
                } else if (!Utils.isMobileNum(phone)) {
                    showToast("手机号码不正确");
                } else {
                    sendCode(phone);
                }
                break;
        }
    }

    public void sendCode(final String phone) {
        String url = UrlHelpper.SEND_REGCODE + "?username=" + phone + "&flag=" + Constants.SEND_VOICE_SECURITY_CODE + "&platform=akeman";
        Request request = new Request(url, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                if (getActivity() instanceof ReplaceFromRegister) {
                    ((ReplaceFromRegister) getActivity()).replaceToInputCode(phone);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    public interface ReplaceFromRegister {
        void replaceToInputCode(String phone);
    }

    public void setRegisterToReception(ReplaceFromRegister replaceFromRegister) {
        this.replaceFromRegister = replaceFromRegister;
    }
}
