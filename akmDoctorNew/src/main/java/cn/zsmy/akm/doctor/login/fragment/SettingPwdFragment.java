package cn.zsmy.akm.doctor.login.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.wei.library.fragment.BaseFragment;
import cn.zsmy.akm.doctor.admissions.view.MaxLengthWatcher;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.login.activity.LoginActivity;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by wanjingyan on 2015/11/18.
 */
public class SettingPwdFragment extends BaseFragment implements View.OnClickListener {
    private EditText mInputPwd;
    private EditText mConfirPwd;
    private String phone;
    private String securityCode;
    private static SettingPwdFragment settingPwdFragment;


    public static SettingPwdFragment newInstance(String phone, String securityCode) {
        settingPwdFragment = new SettingPwdFragment();
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);
        bundle.putString("securityCode", securityCode);
        settingPwdFragment.setArguments(bundle);
        return settingPwdFragment;
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_register_setting_your_password;
    }

    @Override
    protected void initializeView(View v) {
        v.findViewById(R.id.setting_yours_password_cancel).setOnClickListener(this);
        mInputPwd = ((EditText) v.findViewById(R.id.input_yours_pwd));
        mConfirPwd = ((EditText) v.findViewById(R.id.confirm_yours_pwd));
        mInputPwd.addTextChangedListener(new MaxLengthWatcher(12, mInputPwd, getActivity()));
        mConfirPwd.addTextChangedListener(new MaxLengthWatcher(12, mConfirPwd, getActivity()));
        v.findViewById(R.id.confirm_register).setOnClickListener(this);
        Bundle bundle = getArguments();
        phone = bundle.getString("phone");
        securityCode = bundle.getString("securityCode");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_yours_password_cancel:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.confirm_register:
                String pwd = mInputPwd.getText().toString();
                String conPwd = mConfirPwd.getText().toString();
                if (pwd.equals(conPwd)) {
                    if (pwd.length() < 6 || pwd.length() > 12) {
                        Toast.makeText(getActivity(), "请输入6到12位的密码", Toast.LENGTH_SHORT).show();
                    } else {
                        //注册成功
                        Register(phone, pwd);
                    }
                } else {
                    showToast("请确认密码是否一致");
                }
                break;
        }
    }

    public void Register(final String phone, final String pwd) {
        String url = UrlHelper.REGISTER + "username=" + phone + "&password=" + pwd + "&platform=" + "doctor";
        Request request = new Request(url, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                startActivity(LoginActivity.getIntent(getActivity()));
                getActivity().finish();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
