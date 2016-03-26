package cn.zsmy.akm.doctor.login.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.login.fragment.InputSecurityCodeFragment;
import cn.zsmy.akm.doctor.login.fragment.LoginFragment;
import cn.zsmy.akm.doctor.login.fragment.ResetPwdFragment;
import cn.zsmy.akm.doctor.login.fragment.RetreveFragment;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.doctor.R;

/**
 * Created by qinwei on 2015/12/8 10:01
 */
public class LoginActivity extends BaseTitleActivity implements
        RetreveFragment.ReplaceFromRetreve, LoginFragment.ReplaceFromLogin,
        InputSecurityCodeFragment.ReplaceInputCode, ResetPwdFragment.ReplaceFragment {

    private FragmentTransaction transaction;
    private RetreveFragment retreveFragment;
    private InputSecurityCodeFragment securityCodeFragment;
    private ResetPwdFragment resetPwdFragment;
    private LoginFragment loginFragment;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_registered);
        Log.i("TAG", "==================" + "===========" + !isTaskRoot());
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void initializeView() {
        super.initializeView();
//        if (!isTaskRoot()) {
//            finish();
//            return;
//        }

        transaction = getSupportFragmentManager().beginTransaction();
        loginFragment = new LoginFragment();
        transaction.add(R.id.registered_layout, loginFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    @Override
    protected void initializeData() {

    }

    //切换至找回密码界面
    @Override
    public void replaceToRetreve() {
        if (retreveFragment == null) {
            retreveFragment = new RetreveFragment();
            retreveFragment.setToInputCode(this);
        }
        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.registered_layout, retreveFragment)
                .addToBackStack("retrevepwd")
                .commit();
    }

    //切换至输入验证码界面
    @Override
    public void replaceToInputCode(String phone) {
        if (securityCodeFragment == null) {
            securityCodeFragment = InputSecurityCodeFragment.newInstance(Constants.KEY_FROM_LOGIN, phone);
            securityCodeFragment.setInputCodeToReception(this);
        }
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.registered_layout, securityCodeFragment)
                .addToBackStack("retrevepwd")
                .commit();
    }

    @Override
    public void replaceToSetPwd(String phone, String securityCode) {

    }

    @Override
    public void replaceToResetPad(String phone, String securityCode) {
        //切换到重置密码界面
        if (resetPwdFragment == null) {
            resetPwdFragment = ResetPwdFragment.newInstance(phone, securityCode);
            resetPwdFragment.setInterface(this);
        }
        getSupportFragmentManager().beginTransaction().
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.registered_layout, resetPwdFragment)
                .addToBackStack("retrevepwd")
                .commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 67) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void toLogin() {
        if (loginFragment == null) {
            loginFragment = new LoginFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.registered_layout, loginFragment)
                .commit();
    }
}
