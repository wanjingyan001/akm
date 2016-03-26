package cn.zsmy.akm.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import cn.zsmy.akm.R;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.LoginUtils;


public class SplashActivity extends Activity {
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Intent intent = (Intent) msg.obj;
                    startActivity(intent);
                    finish();
                    break;
                case 1:
                    autoLogin();
                    break;
            }
        }
    };
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=SplashActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_splash);
        MyApplication.getInstance().setAppState(MyApplication.APP_STATE_STARTED);
        Log.d("wei", "app started");
//		TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
//		Toast.makeText(this, tm.getDeviceId(), 1000).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences("IsFirstLogin", MODE_PRIVATE);
        Message message = Message.obtain();
        if (preferences.getBoolean("IsFirstLogin", true)) {
            preferences.edit().putBoolean("IsFirstLogin", false).commit();
            Intent intent = new Intent(this, NavigationActivity.class);
            message.obj = intent;
            message.what = 0;
        } else {
            message.what = 1;
        }
        mHandler.sendMessageDelayed(message,2000);
    }

    private void autoLogin() {
         sharedPreferences = this.getSharedPreferences(Constants.LOGIN_INFO, MODE_PRIVATE);
        if (sharedPreferences != null) {
            String userName = sharedPreferences.getString("userName", null);
            String passWord = sharedPreferences.getString("passWord", null);
            if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
                Message message = Message.obtain();
                message.what = 1;
                LoginUtils.login(this, userName, passWord);
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
