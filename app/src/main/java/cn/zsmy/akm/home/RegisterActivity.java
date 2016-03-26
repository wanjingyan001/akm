package cn.zsmy.akm.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import cn.wei.library.activity.BaseActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.fragment.InputSecurityCodeFragment;
import cn.zsmy.akm.fragment.RegisteredFragment;
import cn.zsmy.akm.fragment.SettingPwdFragment;
import cn.zsmy.akm.utils.Constants;

/**
 * 注册
 * Created by qinwei on 2015/11/16 17:18
 */
public class RegisterActivity extends BaseActivity implements RegisteredFragment.ReplaceFromRegister, InputSecurityCodeFragment.ReplaceInputCode {
    private FragmentTransaction transaction;
    private InputSecurityCodeFragment codeFragment;
    private SettingPwdFragment pwdFragment;
    private RegisteredFragment registeredFragment;


    @Override
    public boolean hasAppKilled() {
        return false;
    }

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=RegisterActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);

    }

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_registered);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    @Override
    protected void initializeView() {
        registeredFragment = new RegisteredFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.registered_layout, registeredFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack("register1");
        transaction.commit();
    }

    @Override
    protected void initializeData() {

    }

    /**
     * 通知栏变色处理
     *
     * @param on
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    //注册界面获取验证码按钮点击时的回调,切换至输入验证码界面
    @Override
    public void replaceToInputCode(String phone) {

        if (codeFragment == null) {
            codeFragment = InputSecurityCodeFragment.newInstance(Constants.KEY_FROM_REGISTER, phone);
            codeFragment.setInputCodeToReception(this);
        }
        FragmentTransaction cft = getSupportFragmentManager().beginTransaction().hide(registeredFragment);
        cft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cft.add(R.id.registered_layout, codeFragment);
        cft.addToBackStack("register2").commit();
    }

    //填写验证码界面点击完成按钮时的回调，切换至设置密码界面
    @Override
    public void replaceToSetPwd(String phone, String securityCode) {

        if (pwdFragment == null) {
            pwdFragment = SettingPwdFragment.newInstance(phone, securityCode);
        }
        FragmentTransaction pft = getSupportFragmentManager().beginTransaction().hide(codeFragment);
        pft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        pft.add(R.id.registered_layout, pwdFragment)
                .addToBackStack("register3")
                .commit();
    }

    @Override
    public void replaceToResetPad(String phone, String securityCode) {

    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG", getSupportFragmentManager().getBackStackEntryCount() + ">>>>>");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 67) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
