package cn.zsmy.akm.doctor.profile;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.bean.ServiceInfo;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.profile.adapter.MyServiceAdapter;
import cn.zsmy.doctor.R;

/**
 * 服务时间/价格
 * Created by Administrator on 2015/12/28.
 */
public class ServiceTimeActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener {
    private List<String> strings;
    private ListView mServiceList;
    private MyServiceAdapter adapter;
    private List<ServiceInfo.DataEntity> data;
    private int type;
    private HashMap<String, String> serviceMap;

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_service_content);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mServiceList = ((ListView) findViewById(R.id.service_content_list));
        strings = new ArrayList<>();

    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", -1);
        serviceMap = ((HashMap<String, String>) intent.getSerializableExtra("ServiceMap"));
        switch (type) {
            case 0:
                setTitle("服务时间");
//                for (String mapKey : serviceMap.keySet()) {
//                    strings.add(mapKey);
//                }
                strings.clear();
                strings.add("5分钟");
                strings.add("4分钟");
                strings.add("3分钟");
                strings.add("2分钟");
                strings.add("1分钟");
                adapter = new MyServiceAdapter(this, strings);
                mServiceList.setAdapter(adapter);
                break;
            case 1:
                setTitle("服务价格");
//                for (String mapValues : serviceMap.values()) {
//                    strings.add(mapValues);
//                }
                strings.clear();
                strings.add("5元");
                strings.add("10元");
                strings.add("15元");
                strings.add("20元");
                adapter = new MyServiceAdapter(this, strings);
                mServiceList.setAdapter(adapter);
                break;
        }
        mServiceList.setOnItemClickListener(this);
    }


    public static Intent getIntent(Context context, HashMap<String, String> map) {
        Intent intent = new Intent(context, ServiceTimeActivity.class);
        intent.putExtra("ServiceMap", map);
        return intent;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = strings.get(position);
        Intent intent = new Intent(ServiceTimeActivity.this, MyServiceActivity.class);
        Log.d("TAG", item);
        intent.putExtra("serviceMessage", item);
        intent.putExtra("type", type);
        setResult(1, intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
