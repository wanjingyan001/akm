package cn.zsmy.akm.doctor.login.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cn.zsmy.akm.doctor.admissions.view.MaxLengthWatcher;
import cn.zsmy.akm.doctor.base.BaseThirdPartyLoginSupportFragment;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.login.activity.LoginActivity;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.utils.Utils;
import cn.zsmy.doctor.R;

/**
 * Created by wanjingyan on 2015/11/17.
 * 使用手机号注册界面
 */
public class RegisteredFragment extends BaseThirdPartyLoginSupportFragment {
    private EditText mInputMobile;
    private ReplaceFromRegister replaceFromRegister;
//    private RequestQueue requestQueue;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_register_get_security_code;
    }

    @Override
    protected void initializeView(View v) {
        v.findViewById(R.id.get_security_code_login).setOnClickListener(this);
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
            case R.id.get_security_code_login:
                startActivity(LoginActivity.getIntent(getActivity()));
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
        Log.i("TAG",phone);
        String url = UrlHelper.SEND_REGCODE + phone + "&flag=" + Constants.SEND_VOICE_SECURITY_CODE+"&platform=doctor";
        Log.i("TAG",url);
//        loadFindPassWord(url,phone);
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
    private void loadFindPassWord(String url, final String phone){
//        requestQueue= Volley.newRequestQueue(getActivity());
//        StringRequest stringRequest=new StringRequest(com.android.volley.Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                Log.i("TAG<<<<<<<<<<<<<<<<",s);
//                if (getActivity() instanceof ReplaceFromRegister) {
//                    ((ReplaceFromRegister) getActivity()).replaceToInputCode(phone);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.i("TAG>>>>>>>>>>>>>>>>>",volleyError.getMessage());
//            }
//        });
//        requestQueue.add(stringRequest);
    }
    public void setRegisterToReception(ReplaceFromRegister replaceFromRegister) {
        this.replaceFromRegister = replaceFromRegister;
    }
}
