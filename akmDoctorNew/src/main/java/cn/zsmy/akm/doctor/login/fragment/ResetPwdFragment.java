package cn.zsmy.akm.doctor.login.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.wei.library.fragment.BaseFragment;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by wanjingyan on 2015/11/18.
 * 重置密码界面
 */
public class ResetPwdFragment extends BaseFragment implements View.OnClickListener, TextWatcher {
    private static ResetPwdFragment resetPwdFragment;
    private EditText mInputPwd;
    private EditText mConfirmPwd;
    private Button mResetBtn;
    private boolean isNull;
    private boolean againIsNull;
    private static String TAG = "MyApplication";
    private String phone;
    private String securityCode;
    private ReplaceFragment anInterface;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_login_reset_your_password;
    }

    public static ResetPwdFragment newInstance(String phone, String securityCode) {
        resetPwdFragment = new ResetPwdFragment();
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);
        bundle.putString("securityCode", securityCode);
        resetPwdFragment.setArguments(bundle);
        return resetPwdFragment;
    }


    @Override
    protected void initializeView(View v) {
        v.findViewById(R.id.reset_pwd_cencel).setOnClickListener(this);
        mInputPwd = ((EditText) v.findViewById(R.id.input_pwd_first));
        mConfirmPwd = ((EditText) v.findViewById(R.id.input_your_pwd_again));
        mResetBtn = ((Button) v.findViewById(R.id.reset_pwd_btn));
        mResetBtn.setEnabled(false);
        mResetBtn.setOnClickListener(this);
        mInputPwd.addTextChangedListener(this);
        mConfirmPwd.addTextChangedListener(this);
        isNull = TextUtils.isEmpty(mInputPwd.getText().toString());
        againIsNull = TextUtils.isEmpty(mConfirmPwd.getText().toString());
        Bundle bundle = getArguments();
        phone = bundle.getString("phone");
        securityCode = bundle.getString("securityCode");
    }

    @Override
    public void onClick(View v) {
        String pwd = mInputPwd.getText().toString();
        String confirmPwd = mConfirmPwd.getText().toString();
        switch (v.getId()) {
            case R.id.reset_pwd_cencel:
                getActivity().onBackPressed();
                break;
            case R.id.reset_pwd_btn:
                if (pwd.equals(confirmPwd)) {
                    if (pwd.length() < 6 || pwd.length() > 12) {
                        Toast.makeText(getActivity(), "请输入6到12位的密码", Toast.LENGTH_SHORT).show();
                    } else {
                        resetPwd(pwd);
                    }
                } else {
                    Toast.makeText(getActivity(), "请确认密码是否一致", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


    private void resetPwd(final String pwd) {
        String url = UrlHelper.EDIT_PASSWORD + "?username=" + phone + "&password=" + pwd + "&verifyCode=" + securityCode + "&platform=doctor";
        Request request = new Request(url, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                mInputPwd.setText(null);
                mConfirmPwd.setText(null);
                anInterface.toLogin();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    public interface ReplaceFragment {
        void toLogin();
    }

    public void setInterface(ReplaceFragment anInterface) {
        this.anInterface = anInterface;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        isNull = TextUtils.isEmpty(mInputPwd.getText().toString());
        againIsNull = TextUtils.isEmpty(mConfirmPwd.getText().toString());
        if (!isNull && !againIsNull) {
            mResetBtn.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        isNull = TextUtils.isEmpty(mInputPwd.getText().toString());
        againIsNull = TextUtils.isEmpty(mConfirmPwd.getText().toString());
        if (isNull && againIsNull) {
            mResetBtn.setEnabled(false);
        }
    }
}
