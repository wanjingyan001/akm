package cn.zsmy.akm.profile;

import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;

/**
 * 用户帮助
 * Created by wanjingyan
 * on 2015/12/11 11:34.
 */
public class UserHelpActivity extends BaseTitleActivity {
    private WebView mWebView;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_help);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    public void initializeData() {
        setTitle("帮助详情");

    }

    @Override
    public void initializeView() {
        super.initializeView();
        mWebView = (WebView) findViewById(R.id.activityWebView);
        initializeWebView();
        mWebView.loadUrl("file:///android_asset/huanzhebangzhu.html");

    }

    private void initializeWebView() {
        mWebView.setWebChromeClient(new MyWebViewClient());
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

    }

    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
