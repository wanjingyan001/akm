package cn.zsmy.akm;

import android.view.View;

import cn.wei.library.fragment.BaseFragment;

/**
 * Created by qinwei on 2015/11/16 17:52
 */
public abstract class BaseThirdPartyLoginSupportFragment extends BaseFragment implements View.OnClickListener {
    public static final int platform_qq = 1;
    public static final int platform_sina = 2;
    public static final int platform_winxin = 3;
//    private UMSocialService social;

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
//                    doLoginForQQ(social);
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
//    protected void doLoginForQQ(final UMSocialService social) {
////        TODO qq授权
//        social.doOauthVerify(getActivity(), SHARE_MEDIA.QQ, new SocializeListeners.UMAuthListener() {
//            @Override
//            public void onStart(SHARE_MEDIA share_media) {
//                Toast.makeText(getActivity(), "授权开始", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onComplete(Bundle bundle, SHARE_MEDIA share_media) {
//                Toast.makeText(getActivity(), "授权完成", Toast.LENGTH_SHORT).show();
//                social.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, new SocializeListeners.UMDataListener() {
//                    @Override
//                    public void onStart() {
//                        Toast.makeText(getActivity(), "获取平台数据开始...", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onComplete(int status, Map<String, Object> info) {
//                        if (status == 200 && info != null) {
//                            StringBuilder sb = new StringBuilder();
//                            Set<String> keys = info.keySet();
//                            for (String key : keys) {
//                                sb.append(key + "=" + info.get(key).toString() + "\r\n");
//                            }
//                            Log.d("TestData", sb.toString());
//                        } else {
//                            Log.d("TestData", "发生错误：" + status);
//                        }
//                    }
//                });
//            }
//
//            @Override
//            public void onError(SocializeException e, SHARE_MEDIA share_media) {
//                Toast.makeText(getActivity(), "授权错误", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancel(SHARE_MEDIA share_media) {
//                Toast.makeText(getActivity(), "授权取消", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }
}
