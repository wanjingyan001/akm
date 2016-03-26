package cn.zsmy.akm.salon;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;

/**
 * Created by sanz on 2016/1/21 14:19
 */
public class FunctionDetailActivity extends BaseTitleActivity {
    private WebView webView;
    private String url;
    private String title;
    private String TAG=this.getClass().getSimpleName();
    public static Intent getIntent(Context context,String url,String title){
        Intent intent=new Intent(context,FunctionDetailActivity.class);
        intent.putExtra("URL", url);
        intent.putExtra("TITLE", title);
        return intent;
    }
    @Override
    protected void setContentView() {
    setContentView(R.layout.activity_function_detail);
    }
    @Override
    protected void initializeView() {
        super.initializeView();
        url=getIntent().getStringExtra("URL");
        title=getIntent().getStringExtra("TITLE");
        if(!url.contains("http")){
            url="http://"+url+"/";
        }
        Log.i(TAG, url);
        webView=(WebView)findViewById(R.id.function_detail_web);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setHorizontalScrollbarOverlay(false);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.requestFocus();
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url); //在当前的webview中跳转到新的url
                return true;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        webView.loadUrl(url);
    }
    @Override
    protected void initializeData() {
        setTitle(title);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            Log.i("info", webView.canGoBack() + "");
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.stopLoading();
        webView.removeAllViews();
        webView.destroy();
    }
}
