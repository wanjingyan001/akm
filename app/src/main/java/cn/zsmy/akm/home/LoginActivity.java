package cn.zsmy.akm.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import cn.wei.library.activity.BaseActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.fragment.InputSecurityCodeFragment;
import cn.zsmy.akm.fragment.LoginFragment;
import cn.zsmy.akm.fragment.ResetPwdFragment;
import cn.zsmy.akm.fragment.RetreveFragment;
import cn.zsmy.akm.utils.Constants;

/**
 * Created by qinwei on 2015/11/16 17:18
 */
public class LoginActivity extends BaseActivity implements
        RetreveFragment.ReplaceFromRetreve, LoginFragment.ReplaceFromLogin,
        InputSecurityCodeFragment.ReplaceInputCode, ResetPwdFragment.ReplaceFragment {
    private FragmentTransaction transaction;
    private RetreveFragment retreveFragment;
    private InputSecurityCodeFragment securityCodeFragment;
    private ResetPwdFragment resetPwdFragment;
    private LoginFragment loginFragment;
//    private UMQQSsoHandler qqSsoHandler;
//    private UMSocialService mController;

    @Override
    public boolean hasAppKilled() {
        return false;
    }

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_registered);
//        mController = UMServiceFactory.getUMSocialService("com.umeng.login");
    }

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=LoginActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
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


    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void initializeView() {
        transaction = getSupportFragmentManager().beginTransaction();
        loginFragment = new LoginFragment();
//        loginFragment.setSocial(mController);
        transaction.add(R.id.registered_layout, loginFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    @Override
    protected void initializeData() {
//参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
//        qqSsoHandler = new UMQQSsoHandler(this, "1104951523", "OP79211VX8GmWAGW");
//        qqSsoHandler.addToSocialSDK();
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
