package cn.zsmy.akm.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import cn.zsmy.akm.BaseThirdPartyLoginSupportFragment;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.home.RegisterActivity;
import cn.zsmy.akm.salon.activity.MaxLengthWatcher;
import cn.zsmy.akm.utils.LoginUtils;

/**
 * 登录(18512155695    123456)
 * Created by qinwei on 2015/11/16.
 */
public class LoginFragment extends BaseThirdPartyLoginSupportFragment {
    private EditText mLoginCodeEdt;
    private EditText mLoginPwdEdt;
    private ReplaceFromLogin fromLogin;
//    private UMSocialService social;

    @Override
    protected void initializeView(View view) {
        super.initializeView(view);
        Log.i("TAG", ">>>>>>>>>>>>>>>");
        view.findViewById(R.id.login_cancel).setOnClickListener(this);
        view.findViewById(R.id.fast_registered).setOnClickListener(this);
        mLoginCodeEdt = ((EditText) view.findViewById(R.id.register_input_mobile));
        mLoginCodeEdt.addTextChangedListener(new MaxLengthWatcher(11, mLoginCodeEdt, getActivity()));
        mLoginPwdEdt = ((EditText) view.findViewById(R.id.login_pwd));
        mLoginPwdEdt.addTextChangedListener(new MaxLengthWatcher(12, mLoginPwdEdt, getActivity()));
        view.findViewById(R.id.login_btn).setOnClickListener(this);
        view.findViewById(R.id.forgot_password).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login_cancel:
                getActivity().finish();
                break;
            case R.id.fast_registered:
                startActivity(RegisterActivity.getIntent(getContext()));
                break;
            case R.id.login_btn:
                // 登录
                String phone = mLoginCodeEdt.getText().toString();
                String pwd = mLoginPwdEdt.getText().toString();
                MyApplication.getInstance().clearProfile();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
                    LoginUtils.login(getActivity(), phone, pwd);
                } else {
                    showToast("手机号或密码不能为空");
                }
                break;
            case R.id.forgot_password:
                // 点击忘记密码切换至找回密码界面
                if (getActivity() instanceof ReplaceFromLogin) {
                    ((ReplaceFromLogin) getActivity()).replaceToRetreve();
                }
                break;
        }
    }


    @Override
    public void doPlatformLogin(String openId, int platform, String icon, String nick) {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_login;
    }

    //接口回调，在Activity中切换fragment界面
    public interface ReplaceFromLogin {
        void replaceToRetreve();
    }

    public void setFromLogin(ReplaceFromLogin fromLogin) {
        this.fromLogin = fromLogin;
    }

//    public void setSocial(UMSocialService social){
//        this.social = social;
//    }
}
