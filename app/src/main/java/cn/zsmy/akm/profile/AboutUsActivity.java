package cn.zsmy.akm.profile;

import android.content.pm.PackageManager;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.VersionCode;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * 关于我们
 * Created by qinwei on 2015/11/24 17:22
 */
public class AboutUsActivity extends BaseTitleActivity implements View.OnClickListener {
    private PopupWindow popupWindow;
    private TextView mCurrentVersion;
    private TextView apkTime;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_profile_aboutus);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        findViewById(R.id.share_akm).setOnClickListener(this);
        mCurrentVersion = ((TextView) findViewById(R.id.current_version));
        apkTime = ((TextView) findViewById(R.id.apk_time));
    }

    @Override
    protected void initializeData() {
        setTitle(getString(R.string.mProfileAboutUsLabel));
        try {
            mCurrentVersion.setText("当前版本：" + this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName);
            if (UrlHelpper.FILE_IP.contains("192")){
                apkTime.setText("发布时间：" + "192.168.1.188");
            }else {
                apkTime.setText("发布时间：2016.2.1");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//        getVersionCode();
    }

    private void getVersionCode() {
        Request request = new Request(UrlHelpper.VERSION_CODE, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                VersionCode versionCode = JsonParser.deserializeFromJson(result, VersionCode.class);
                VersionCode.DataEntity data = versionCode.getData();
                mCurrentVersion.setText("当前版本：" + data.getVersion());
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.share_akm:
                //弹出分享菜单
                break;
            case R.id.share_with_sina:

                break;
            case R.id.share_with_weixin:

                break;
            case R.id.share_with_qq:

                break;
        }
    }
}
