package cn.zsmy.akm.doctor.admissions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import cn.zsmy.akm.doctor.admissions.fragemnt.SelecHospitalFragment;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.widget.SlideTabView;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/17 14:00
 */
public class SelectHospitalActivity extends BaseTitleActivity implements SlideTabView.OnTabClickListener, View.OnClickListener, SendResult {
    private SlideTabView slideTabView;
    private SlideTabView.TabConfig tabConfig;
    private ImageView search;
    private SharedPreferences hospital;
    private String cacheName;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_select_hospital);
        MyApplication.getInstance().addActivity(this);


    }

    @Override
    protected void initializeView() {
        super.initializeView();
        cacheName = getIntent().getStringExtra("cacheName");
        slideTabView = (SlideTabView) findViewById(R.id.select_hospital_slide);
        search = (ImageView) findViewById(R.id.search_bar);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("患者所在城市");
        labels.add("全部城市");
        ArrayList<Fragment> clazz = new ArrayList<Fragment>();
        Log.d("TAG", cacheName);
        SelecHospitalFragment selecHos = SelecHospitalFragment.newInstance(cacheName,1);
        SelecHospitalFragment allhos = SelecHospitalFragment.newInstance(cacheName,0);
        clazz.add(selecHos);
        clazz.add(allhos);
        tabConfig = new SlideTabView.TabConfig(labels, clazz);
        tabConfig.textSize = 16;
        tabConfig.normalItemColor = R.color.black;
        tabConfig.selectedItemColor = R.color.black;
        tabConfig.itemBackground = R.color.white;
        slideTabView.initializeData(getSupportFragmentManager(), tabConfig);
        slideTabView.notifyDataChanged();
        slideTabView.setOnTabClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    protected void initializeData() {
        setTitle("选择医院");
    }

    @Override
    public void onTabClick(int index) {


    }

    @Override
    public void onChange(int index) {
        switch (index) {
            case 0:
                break;
            case 1:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_bar:
                //搜索医院
                Intent intent = new Intent(this, SearchHospitalActivity.class);
                Log.d("TAG", "前往医院搜索");
                intent.putExtra("TYPE", Constants.SREACH_HOSPITAL_VALUES);
                intent.putExtra("cacheName", cacheName);
                startActivityForResult(intent, Constants.RESULT_CDDE_SREACH_HOSPITAL);
                break;
        }
    }

    @Override
    public void result(String name, String hospitalId) {
        Intent intent = new Intent(this, DoctorSuggestActivity.class);
        saveChooseHospital(name, hospitalId);
        Log.d("TAG", "返回医院名");
        setResult(Constants.return_Hospital, intent);
        finish();
    }


    private void saveChooseHospital(String name, String hospitalId) {
        hospital = getSharedPreferences(cacheName, MODE_PRIVATE);
        SharedPreferences.Editor edit = hospital.edit();
        edit.putString("hospitalName", name);
        edit.putString("hospitalId", hospitalId);
        edit.commit();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Constants.SREACH_HOSPITAL) {
//            if (resultCode == Constants.return_Hospital) {
//                Hosptials.DataEntity result = (Hosptials.DataEntity) data.getSerializableExtra("result");
//                String name = result.getName();
//                Log.d("wjy", name);
//                Intent intent = new Intent(this, DoctorSuggestActivity.class);
//                intent.putExtra("hospitalName", name);
//                setResult(Constants.return_Hospital, intent);
//                finish();
//            }
//        }
//    }
}
