package cn.zsmy.akm.profile;

import android.webkit.WebSettings;
import android.webkit.WebView;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;

/**
 * 用户协议
 * Created by wanjingyan
 * on 2015/12/11 13:05.
 */
public class UserAgreementActivity extends BaseTitleActivity {
    private WebView userAgreement;
    private WebSettings setting;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_user_agreement);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        userAgreement = ((WebView) findViewById(R.id.user_agreement));
    }

    @Override
    protected void initializeData() {
        setTitle("用户协议");
        setting = userAgreement.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setBuiltInZoomControls(true);
        setting.setDatabaseEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setAppCacheMaxSize(1024 * 1024 * 8);
        setting.setDomStorageEnabled(true);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        setting.setAppCachePath(appCachePath);
        setting.setAllowFileAccess(true);
        setting.setAppCacheEnabled(true);
        setting.setPluginState(WebSettings.PluginState.ON);
        setting.setUserAgentString(setting.getUserAgentString() + " Zozoms");
        setting.setAllowFileAccess(true);
        userAgreement.loadUrl("file:///android_asset/yonghu.html");
    }

}
