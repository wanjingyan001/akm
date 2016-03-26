package cn.zsmy.akm.doctor.profile;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.bean.DoctorCenter;
import cn.zsmy.akm.doctor.bean.Profile;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.home.ShareQRCode;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.profile.adapter.ProfileCenterAdapter;
import cn.zsmy.akm.doctor.profile.view.ProfileCenterTopView;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/19 17:17
 */
public class ProfileCenterActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private List<String> datas;
    private ProfileCenterAdapter adapter;
    private DoctorCenter.DataEntity data;
    private Profile profile;
    private ProfileCenterTopView topView;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ProfileCenterActivity.class);
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_profile_center);
        MyApplication.getInstance().addActivity(this);
        Log.i("TAG", this.getComponentName() + "");
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        listView = (ListView) findViewById(R.id.profile_list);
        listView.setOnItemClickListener(this);
        topView = new ProfileCenterTopView(this);
        datas = new ArrayList<>();
        Log.i("TAG", this.getComponentName() + "1");

    }

    @Override
    protected void initializeData() {
        setTitle("个人中心");
        profile = MyApplication.getProfile();
        if (profile != null) {
            getDoctorCenter();
        }
        String[] profile_s = getResources().getStringArray(R.array.profile_list);
        for (String s : profile_s) {
            datas.add(s);
        }
        int[] drawable = {R.drawable.icon_profile_person_info, R.drawable.icon_profile_money, R.drawable.icon_profile_integral,
                R.drawable.icon_profile_service_fees, R.drawable.icon_profile_invitation_patients,
                R.drawable.icon_profile_invite_doctors_partners, R.drawable.icon_profile_more
        };
        List<Integer> drawableDatas = new ArrayList<>();
        for (int s : drawable) {
            drawableDatas.add(s);
        }
        adapter = new ProfileCenterAdapter(datas, drawableDatas, this);
        listView.addHeaderView(topView);
        listView.setAdapter(adapter);
        Log.i("TAG", this.getComponentName() + "2");
    }

    public void getDoctorCenter() {
        Request request = new Request(UrlHelper.DOCTOR_PERSONAL_CENTER, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {

                DoctorCenter doctorCenter = JsonParser.deserializeFromJson(result, DoctorCenter.class);
                data = doctorCenter.getData();
                topView.initializeData(data);

            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        IntentActivity(position);
    }

    private void IntentActivity(int position) {
        switch (position) {
            case 1:
                startActivity(ProfileInfoActivity.getIntent(this, String.valueOf(data.getAuthFlag())));
                break;
            case 2:
                Intent intent1 = MyIncomeActivity.getIntent(this);
                if (data != null) {
                    intent1.putExtra("balance", String.valueOf(data.getBalance()));
                    intent1.putExtra("avail", String.valueOf(data.getAvail()));
                    startActivity(intent1);
                } else {
                    Toast.makeText(this, "未获取到数据", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                Intent intent2 = MyIntegralActivity.getIntent(this);
                if (data != null) {
                    intent2.putExtra("score", data.getScore());
                    startActivity(intent2);
                } else {
                    Toast.makeText(this, "未获取到数据", Toast.LENGTH_SHORT).show();
                }

                break;
            case 4:
                startActivity(MyServiceActivity.getIntent(this));
                break;
            case 5:
                startActivity(ShareQRCode.getIntent(this, 0));
                break;
            case 6:
                startActivity(ShareQRCode.getIntent(this, 1));
                break;
            case 7:
                startActivity(MoreActivity.getIntent(this));
                break;
            case 8:
                break;
        }
    }
}
