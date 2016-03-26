package cn.zsmy.akm.doctor.base;

import android.view.View;

import cn.wei.library.fragment.BaseFragment;
import cn.zsmy.doctor.R;


/**
 * Created by qinwei on 2015/11/16 17:52
 */
public abstract class BaseThirdPartyLoginSupportFragment extends BaseFragment implements View.OnClickListener {
    public static final int platform_qq = 1;
    public static final int platform_sina = 2;
    public static final int platform_winxin = 3;

    @Override
    protected void initializeView(View v) {
        v.findViewById(R.id.mLoginForQQ).setOnClickListener(this);
        v.findViewById(R.id.mLoginForSINA).setOnClickListener(this);
        v.findViewById(R.id.mLoginForWINXI).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLoginForQQ:
                doLoginForQQ();
                break;
            case R.id.mLoginForSINA:
                doLoginForSINA();
                break;
            case R.id.mLoginForWINXI:
                doLoginForWINXIN();
                break;
        }
    }

    protected void doLogin(String account, String password) {

    }

    /**
     * 第三方平台授权登录
     *
     * @param openId
     * @param platform 平台类型
     * @param icon     用户头像
     * @param nick     用户昵称
     */
    public abstract void doPlatformLogin(String openId, int platform, String icon, String nick);

    /**
     * 微信授权登录
     */
    protected void doLoginForWINXIN() {
//        TODO 微信登录
    }

    /**
     * 新浪微博授权登录
     */
    protected void doLoginForSINA() {
//        TODO 新浪微博授权登录
    }

    /**
     * qq授权
     */
    protected void doLoginForQQ() {
//        TODO qq授权
    }
}
