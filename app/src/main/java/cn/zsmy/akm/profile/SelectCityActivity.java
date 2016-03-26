package cn.zsmy.akm.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.baidu.location.LocationClient;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.baidu.getLocation;
import cn.zsmy.akm.entity.Cityies;
import cn.zsmy.akm.entity.Province;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * Created by wanjingyan
 * on 2015/12/17 15:55.
 */
public class SelectCityActivity extends BaseTitleListActivity implements AdapterView.OnItemClickListener {
    private TextView mCityName;
    public LocationClient mLocationClient = null;
    private getLocation getLocation;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            EmptyView.State state = null;
            switch (msg.what) {
                case 0:
                    state = EmptyView.State.ing;
                    break;
                case 1:
                    break;
                case 2:

            }
            showContent();
        }
    };


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_select_city);
        MyApplication.getInstance().addActivity(this);
        mLocationClient = new LocationClient(getApplicationContext());
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mCityName = ((TextView) findViewById(R.id.city_name));
        getLocation = new getLocation(mLocationClient, mCityName);
    }

    @Override
    protected void initializeData() {
        setTitle("选择城市");
        getProvinces();
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SelectCityActivity.class);
        return intent;
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);

    }

    //获得省份列表
    public void getProvinces() {
        Request request = new Request(UrlHelpper.GET_PROVINCE, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                modules.clear();
                Province province = JsonParser.deserializeFromJson(result, Province.class);
                List<Province.DataEntity> data = province.getData();
                modules.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }


    //获取城市列表
    public void getCities(String id) {
        Request request = new Request(UrlHelpper.GET_CITIES + "?parentId=" + id, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                modules.clear();
                Cityies cityies = JsonParser.deserializeFromJson(result, Cityies.class);
                List<Cityies.DataEntity> data = cityies.getData();
                modules.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object obj = modules.get(position - 1);
        if (obj instanceof Province.DataEntity) {
            String id1 = ((Province.DataEntity) obj).getId();
            getCities(id1);
        } else if (obj instanceof Cityies.DataEntity) {
            String value = ((Cityies.DataEntity) obj).getName();
            mCityName.setText(value);
            saveCity(value);
            Intent intent = new Intent(this, UserInfoActivity.class);
            intent.putExtra("value", mCityName.getText().toString());
            setResult(1, intent);
            finish();
        }
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(android.R.layout.simple_list_item_1, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private TextView text;

        @Override
        public void initializeView(View view) {
            text = ((TextView) view.findViewById(android.R.id.text1));
        }

        /**
         * @param position
         */
        @Override
        public void initializeData(int position) {
            Object obj = modules.get(position);
            if (obj instanceof Province.DataEntity) {
                String name = ((Province.DataEntity) obj).getName();
                text.setText(name);
            } else if (obj instanceof Cityies.DataEntity) {
                String name = ((Cityies.DataEntity) obj).getName();
                text.setText(name);
            }
        }
    }


    private void saveCity(String cityName) {
        SharedPreferences save_city = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor edit = save_city.edit();
        edit.putString("user_city", cityName);
        edit.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, UserInfoActivity.class);
            intent.putExtra("value", mCityName.getText().toString());
            setResult(100, intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLocation.unRegisterLocationListener();
    }

}

