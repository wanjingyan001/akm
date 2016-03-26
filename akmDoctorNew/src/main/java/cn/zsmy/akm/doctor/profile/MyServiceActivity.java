package cn.zsmy.akm.doctor.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.bean.Profile;
import cn.zsmy.akm.doctor.bean.ServiceInfo;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * 我的服务收费
 * Created by Administrator on 2015/12/28.
 */
public class MyServiceActivity extends BaseTitleActivity implements View.OnClickListener {
    private static final int SERVICE_MESSAGE = 0;
    private TextView serviceTimeNumber;
    private TextView servicePriceNumber;
    private HashMap<String, String> map;
    private Profile profile;
    private SharedPreferences preferences;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_my_service);
        MyApplication.getInstance().addActivity(this);
    }


    @Override
    protected void initializeView() {
        super.initializeView();
        serviceTimeNumber = ((TextView) findViewById(R.id.service_time_numer));
        servicePriceNumber = ((TextView) findViewById(R.id.service_price_number));
        findViewById(R.id.service_time).setOnClickListener(this);
        findViewById(R.id.service_price).setOnClickListener(this);
        profile = MyApplication.getProfile();
        getService();
        map = new HashMap<>();
    }

    @Override
    protected void initializeData() {
        setTitle("我的服务收费");
        getServiceList();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MyServiceActivity.class);
    }

    @Override
    public void onClick(View v) {
        Intent intent = ServiceTimeActivity.getIntent(this, map);
        switch (v.getId()) {
            case R.id.service_time:
                intent.putExtra("type", 0);
                break;
            case R.id.service_price:
                intent.putExtra("type", 1);
                break;
        }
        startActivityForResult(intent, SERVICE_MESSAGE);
    }

    /**
     * 获取服务时间/价格列表
     */
    private void getServiceList() {
        Request request = new Request(UrlHelper.PRICE_LIST + "?doctorId=" + MyApplication.getProfile().getUserId(), this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                ServiceInfo serviceInfo = JsonParser.deserializeFromJson(result, ServiceInfo.class);
                List<ServiceInfo.DataEntity> data = serviceInfo.getData();
                for (ServiceInfo.DataEntity service : data) {
                    map.put(service.getUnit(), service.getAmt());
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    /**
     * 获取医生的服务价格/时间
     */
    private void getMyServicePrice() {
        Request request = new Request(UrlHelper.LOOK_OVER_SERVICE_PRICE, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {


            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    //修改医生服务费用
    private void changePrice() {
        Request request = new Request(UrlHelper.EDIT_PRICE, Request.RequestMethod.POST, this);
        request.put("id", profile.getUserId());
        request.put("doctorId", profile.getUserId());
        String value = servicePriceNumber.getText().toString();
        request.put("amt", value.replace("元", ""));
        request.put("unit", serviceTimeNumber.getText().toString());
        request.put("zType", String.valueOf(2));//问诊类型:1图文2电话3视频
        request.put("payType", String.valueOf(2));//支付类型:1积分2网银
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                saveService();
                finish();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    private void saveService() {
        preferences = getSharedPreferences(MyApplication.getProfile().getUserId(), MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("unit", servicePriceNumber.getText().toString());
        edit.putString("amt", serviceTimeNumber.getText().toString());
        edit.commit();
    }

    private void getService() {
        preferences = getSharedPreferences(MyApplication.getProfile().getUserId(), MODE_PRIVATE);
        String unit = preferences.getString("unit", "10元");
        String amt = preferences.getString("amt", "5分钟");
        servicePriceNumber.setText(unit);
        serviceTimeNumber.setText(amt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_submit_service, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.submit_service) {
            //提交修改
            changePrice();
        } else if (itemId == android.R.id.home) {
            //不保存修改
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == SERVICE_MESSAGE) {
                String serviceMessage = data.getStringExtra("serviceMessage");
                int type = data.getIntExtra("type", -1);
                Log.d("TAG", serviceMessage);
                switch (type) {
                    case 0:
                        serviceTimeNumber.setText(serviceMessage);
//                        String MapValue = map.get(serviceMessage);
//                        servicePriceNumber.setText(MapValue);
                        break;
                    case 1:
                        servicePriceNumber.setText(serviceMessage);
//                        for (Map.Entry<String, String> service : map.entrySet()) {
//                            if (service.getValue().equals(serviceMessage)) {
//                                serviceTimeNumber.setText(service.getKey());
//                            }
//                        }
                        break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
