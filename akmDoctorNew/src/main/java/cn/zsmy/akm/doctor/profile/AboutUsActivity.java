package cn.zsmy.akm.doctor.profile;

import android.content.pm.PackageManager;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.bean.VersionCode;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;


/**
 * 关于我们
 * Created by qinwei on 2015/11/24 17:22
 */
public class AboutUsActivity extends BaseTitleActivity implements View.OnClickListener {
    private PopupWindow popupWindow;
    private TextView mCurrentVersion;
    private TextView mCurrentVersionPubTime;

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
        mCurrentVersionPubTime = ((TextView) findViewById(R.id.current_version_pub_time));
    }

    @Override
    protected void initializeData() {
        setTitle("关于我们");
        try {
            mCurrentVersion.setText("当前版本："+this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName);
            if (UrlHelper.IP.contains("192")){
                mCurrentVersionPubTime.setText("发布时间:"+UrlHelper.IP);
            }else {
                mCurrentVersionPubTime.setText("发布时间：2016.2.1");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//        getVersionCode();
    }

    private void getVersionCode() {
        Request request = new Request(UrlHelper.VERSION_CODE, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                VersionCode versionCode = JsonParser.deserializeFromJson(result, VersionCode.class);
                VersionCode.DataEntity data = versionCode.getData();
                mCurrentVersion.setText("当前版本："+data.getVersion());
                mCurrentVersionPubTime.setText("发布时间：2016.2.1");
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
        }
    }
}
