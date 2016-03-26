package cn.zsmy.akm.doctor.login.fragment;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import cn.zsmy.akm.doctor.admissions.view.MaxLengthWatcher;
import cn.zsmy.akm.doctor.base.BaseThirdPartyLoginSupportFragment;
import cn.zsmy.akm.doctor.bean.Profile;
import cn.zsmy.akm.doctor.home.HomeActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.login.activity.RegisterActivity;
import cn.zsmy.akm.doctor.utils.LoginUtils;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;


/**
 * Created by qinwei on 2015/11/16.
 */
public class LoginFragment extends BaseThirdPartyLoginSupportFragment {
    private EditText mLoginCodeEdt;
    private EditText mLoginPwdEdt;
    private ReplaceFromLogin fromLogin;


    @Override
    protected void initializeView(View view) {
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
            case R.id.fast_registered:
                startActivity(RegisterActivity.getIntent(getActivity()));
                break;
            case R.id.login_btn:
                // 登录
                String phone = mLoginCodeEdt.getText().toString();
                String pwd = mLoginPwdEdt.getText().toString();
                MyApplication.getInstance().clearProfile();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
                    if (phone.length() != 11 && (pwd.length() < 6 || pwd.length() > 12)) {
                        showToast("手机号为11位，密码为6到12位，请确认");
                    } else {
                        LoginUtils.login(getActivity(), phone, pwd);
                    }
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

    public void login(Context context, String phone,String pwd) {
        Request request = new Request(UrlHelper.LOGIN, Request.RequestMethod.POST, context);
        try {
            JSONObject obj = new JSONObject();
            obj.put("username", phone);
            obj.put("password", pwd);
            obj.put("platform", "doctor");
            request.postContent = obj.toString();
            request.addHeader("Content-Type", "application/json;charset=UTF-8");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Profile profile = JsonParser.deserializeFromJson(result, Profile.class);
                MyApplication.setProfile(profile);
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                getActivity().finish();
            }
        });
        RequestManager.getInstance().execute(context.toString(), request);
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
}
