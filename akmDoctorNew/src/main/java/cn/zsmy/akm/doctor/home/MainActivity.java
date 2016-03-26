package cn.zsmy.akm.doctor.home;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import cn.zsmy.akm.doctor.login.activity.LoginActivity;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.LoginUtils;
import cn.zsmy.doctor.R;

public class MainActivity extends AppCompatActivity {
    private String IS_FIRST_OPEN = "first_open_app";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case 1:
                    autoLogin();

                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApplication.getInstance().setAppState(MyApplication.APP_STATE_STARTED);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences(IS_FIRST_OPEN, MODE_PRIVATE);
        if (sharedPreferences != null) {
            boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (isFirstRun) {
                edit.putBoolean("isFirstRun", false);
                edit.commit();
                handler.sendEmptyMessageDelayed(0, 2000);
            } else {
                if (MyApplication.getInstance().getmList() != null && MyApplication.getInstance().getmList().size() != 0) {
                    finish();
                } else {
                    handler.sendEmptyMessageDelayed(1, 2000);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void autoLogin() {
        SharedPreferences login = this.getSharedPreferences(Constants.LOGIN_INFO, MODE_PRIVATE);
        if (login != null) {
            String userName = login.getString("userName", null);
            String passWord = login.getString("passWord", null);
            if (userName != null && passWord != null) {
                LoginUtils.login(this, userName, passWord);
            } else {
                Intent log = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(log);
                finish();
            }
        }
    }
}
