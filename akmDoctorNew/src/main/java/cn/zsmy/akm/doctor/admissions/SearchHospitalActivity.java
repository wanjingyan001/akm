package cn.zsmy.akm.doctor.admissions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.doctor.admissions.bean.Drugs;
import cn.zsmy.akm.doctor.admissions.bean.TestItem;
import cn.zsmy.akm.doctor.admissions.bean.TestSuggest;
import cn.zsmy.akm.doctor.base.BaseTitleListActivity;
import cn.zsmy.akm.doctor.bean.Hosptials;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * 搜索
 * Created by sanz on 2015/12/17 16:10
 */
public class SearchHospitalActivity extends BaseTitleListActivity implements View.OnClickListener {
    private TextView search_tex;
    private EditText search_edit;
    private String type;
    private SharedPreferences pregerence;
    private String cacheName;
    private List<Hosptials.DataEntity> data;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    showContent(EmptyView.State.ing,1);
                    break;
                case 1:
                    showContent(EmptyView.State.empty,1);
                    break;
            }
        }
    };

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_search);
        MyApplication.getInstance().addActivity(this);

    }

    @Override
    protected void initializeView() {
        super.initializeView();
        search_tex = (TextView) findViewById(R.id.search_tex);
        search_edit = (EditText) findViewById(R.id.seacrh_edit);
        showContent();
        search_tex.setOnClickListener(this);

    }


    public static Intent getIntent(Context context) {
        return new Intent(context, SearchHospitalActivity.class);
    }

    @Override
    protected void initializeData() {
        Intent intent = getIntent();
        type = intent.getStringExtra("TYPE");
        cacheName = intent.getStringExtra("cacheName");
        pregerence = getSharedPreferences(cacheName, MODE_PRIVATE);
        TextView text = new TextView(this);
        text.setText("搜索记录");
//        search_lis.addHeaderView(text);
        Button button = new Button(this);
        button.setText("清空搜索记录");
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
        button.setGravity(Gravity.CENTER);
        button.setPadding(20, 10, 20, 20);
        button.setTextColor(getResources().getColor(R.color.font_color));
        button.setLayoutParams(params);
        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_search_hosptital_button_blue));
        sreach();
        showContent();
    }

    /**
     * 搜索医院
     */
    public void seacherHospital(String value) {
        String url = "";
        if (TextUtils.isEmpty(value)) {
            url = UrlHelper.SEARCH_HOSPITAL;
        } else {
            url = UrlHelper.SEARCH_HOSPITAL + "?name=" + value;
        }

        if (value!=""){
            Request request = new Request(url, this);
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    Log.d("TAG", result);
                    modules.clear();
                    Hosptials hosptials = JsonParser.deserializeFromJson(result, Hosptials.class);
                    data = hosptials.getData();
                    modules.addAll(data);
                    adapter.notifyDataSetChanged();
                    if (adapter.getCount()==0){
                        mHandler.sendEmptyMessage(1);
                    }else {
                        mHandler.sendEmptyMessage(0);
                    }
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        }else {
            Toast.makeText(getApplicationContext(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 检验项目
     */
    private void seacherTest(String value) {
        String url = "";
        if (TextUtils.isEmpty(value)) {
            url = UrlHelper.INSPECTIONITEM;
        } else {
            url = UrlHelper.INSPECTIONITEM + "?name=" + value;
        }
        if (value!=""){
            Request request = new Request(url, this);
            Log.d("TAG", url);
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    Log.d("TAG", result);
                    modules.clear();
                    TestItem testItem = JsonParser.deserializeFromJson(result, TestItem.class);
                    List<TestItem.DataEntity> data = testItem.getData();
                    modules.addAll(data);
                    adapter.notifyDataSetChanged();
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        }else {
            Toast.makeText(getApplicationContext(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 药品搜索
     */
    private void seachDrug(String value) {
        String url = "";
        if (TextUtils.isEmpty(value)) {
            url = UrlHelper.DRUG_LIST;
        } else {
            url = UrlHelper.DRUG_LIST + "?name=" + value;
        }
        if (value!=""){
            Request request = new Request(url, this);
            Log.d("TAG", url);
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    Log.d("TAG", result);
                    modules.clear();
                    Drugs drugs = JsonParser.deserializeFromJson(result, Drugs.class);
                    modules.addAll(drugs.getData());
                    adapter.notifyDataSetChanged();

                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        }else {
            Toast.makeText(getApplicationContext(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        Intent intent = new Intent();
//        intent.setAction(et.getText().toString().trim());
        SharedPreferences.Editor edit = pregerence.edit();
        switch (type) {
            case Constants.SREACH_HOSPITAL_VALUES:
                //搜索医院
                Hosptials.DataEntity value = ((Hosptials.DataEntity) modules.get(position - 1));
                edit.putString("hospitalName", value.getName());
                edit.putString("hospitalId", value.getId());
                intent.setClass(this, DoctorSuggestActivity.class);
                intent.putExtra("result", value.getName());
                intent.putExtra("RETURNTYPE", Constants.return_Hospital);
                Log.d("TAG", "返回医院名");
                break;
            case Constants.SREACH_TESTITEM_VALUES:
                //搜索检验项目
                TestItem.DataEntity dataEntity = (TestItem.DataEntity) modules.get(position - 1);
                Iterator<TestSuggest> iterator = DoctorSuggestActivity.tests.iterator();
                boolean b = false;
                while (iterator.hasNext()) {
                    TestSuggest next = iterator.next();
                    if (next.getTestId().equals(dataEntity.getId())) {
                        b = true;
                    }
                }
                if (!b) {
                    DoctorSuggestActivity.tests.add(new TestSuggest(dataEntity.getId(), dataEntity.getName()));
//                    submitTestItem(dataEntity.getName(), dataEntity.getId());
                } else {
                    Toast.makeText(this, "已选择该检验项目，无法重复选择", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.setClass(this, DoctorSuggestActivity.class);
                intent.putExtra("result", dataEntity.getName());
                intent.putExtra("testItemId", dataEntity.getId());
                edit.putString("testName", dataEntity.getName());
                edit.putString("testItemId", dataEntity.getId());
                Log.d("TAG", "返回检验项目");
                break;
            case Constants.SREACH_DRUG_VALUES:
                //搜素药品
                Drugs.DataEntity entity = (Drugs.DataEntity) modules.get(position - 1);
                intent.setClass(this, DrugDetailActivity.class);
                intent.putExtra("drugId", entity.getId());
                intent.putExtra("cacheName", cacheName);
                intent.putExtra("from", "search");
                break;
        }
        startActivity(intent);
        edit.commit();
        finish();
    }


    private void submitTestItem(String name, String id) {
        Request request = new Request(UrlHelper.ADD_CASE_CHECK, Request.RequestMethod.POST, this);
        request.put("caseId", cacheName);
        request.put("inspectionId", id);
        request.put("inspection", name);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", "检验项目：" + result);
                Intent intent = new Intent(SearchHospitalActivity.this, DoctorSuggestActivity.class);
                setResult(11, intent);
                finish();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.item_select_hospital, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }


    @Override
    public void onClick(View v) {
        sreach();
    }

    private void sreach() {
        String str = search_edit.getText().toString();
        String value = "";
        try {
            Pattern p = Pattern.compile(".*");
            if (p.matcher(str).matches()) {
                value = URLEncoder.encode(str, "UTF-8");
            } else {
                value = str;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        switch (type) {
            case Constants.SREACH_HOSPITAL_VALUES:
                //搜索医院
                seacherHospital(value);
                break;
            case Constants.SREACH_TESTITEM_VALUES:
                //搜索检验项目
                seacherTest(value);
                break;
            case Constants.SREACH_DRUG_VALUES:
                //搜素药品
                seachDrug(value);
                break;
        }
    }


    class Holder extends QBaseViewHolder {
        private TextView listTitle;

        @Override
        public void initializeView(View view) {
            listTitle = ((TextView) view.findViewById(R.id.hospital_name_tex));
        }

        @Override
        public void initializeData(int position) {
            switch (type) {
                case Constants.SREACH_HOSPITAL_VALUES:
                    //搜索医院
                    Hosptials.DataEntity dataEntity = (Hosptials.DataEntity) modules.get(position);
                    listTitle.setText(dataEntity.getName());
                    break;
                case Constants.SREACH_TESTITEM_VALUES:
                    //搜索检验项目
                    TestItem.DataEntity dataEntity1 = (TestItem.DataEntity) modules.get(position);
                    listTitle.setText(dataEntity1.getName());
                    break;
                case Constants.SREACH_DRUG_VALUES:
                    //搜素药品
                    Drugs.DataEntity dataEntity2 = (Drugs.DataEntity) modules.get(position);
                    listTitle.setText(dataEntity2.getName());
                    break;
            }
        }
    }
}
