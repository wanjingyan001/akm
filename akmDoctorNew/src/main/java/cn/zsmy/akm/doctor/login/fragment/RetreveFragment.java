package cn.zsmy.akm.doctor.login.fragment;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cn.wei.library.fragment.BaseFragment;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.utils.Utils;
import cn.zsmy.doctor.R;

/**
 * Created by wanjingyan on 2015/11/18.
 * 找回密码界面
 */
public class RetreveFragment extends BaseFragment implements View.OnClickListener {
    private EditText retrieve_pwd_edit;
    private ReplaceFromRetreve fromRetreve;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_login_retrieve_password;
    }

    @Override
    protected void initializeView(View v) {
        retrieve_pwd_edit = ((EditText) v.findViewById(R.id.retrieve_pwd_edit));
        v.findViewById(R.id.retrieve_pwd_cancel).setOnClickListener(this);
        v.findViewById(R.id.retrieve_get_code).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retrieve_pwd_cancel:
                getActivity().onBackPressed();
                break;
            case R.id.retrieve_get_code:
                String phone = retrieve_pwd_edit.getText().toString();
                //跳转到填写验证码界面
                String phoneNum = retrieve_pwd_edit.getText().toString();
                if (TextUtils.isEmpty(phoneNum)) {
                    showToast("请输入手机号码");
                } else if (!Utils.isMobileNum(phoneNum)) {
                    showToast("你输入的手机号码有误");
                }else {
                    loadFindMiMa(phoneNum);
                }
        }
    }


    /**
     * 加载手机号码提交
     **/
    public void loadFindMiMa(final String phoneNum) {
        String url = UrlHelper.FIND_MIMA_CODE_URL + phoneNum + "&flag=" + Constants.SEND_VOICE_SECURITY_CODE+"&platform=doctor";
        Request request = new Request(url, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.i("TAG", result);
                fromRetreve.replaceToInputCode(phoneNum);
            }

        });
        RequestManager.getInstance().execute(this.toString(), request);

    }

    public void setToInputCode(ReplaceFromRetreve fromRetreve) {
        this.fromRetreve = fromRetreve;
    }

    public interface ReplaceFromRetreve {
        void replaceToInputCode(String phone);
    }
}
