package cn.zsmy.akm.doctor.admissions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.zsmy.akm.doctor.admissions.adapter.DrugAdapter;
import cn.zsmy.akm.doctor.admissions.adapter.HospitalAdapter;
import cn.zsmy.akm.doctor.admissions.bean.Examine;
import cn.zsmy.akm.doctor.admissions.bean.TestSuggest;
import cn.zsmy.akm.doctor.admissions.view.TabChooseView;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * 选择检验项目
 * Created by Administrator on 2016/1/15.
 */
public class SelectExamineActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener {
    private PullToRefreshListView refreshListView;
    private DrugAdapter adapter;
    private TabChooseView tabChoose;
    private ImageView search;
    private ListView listLeft;
    private ListView listRight;
    private List<Examine.DataEntity> data;
    private List<String> examine1, examine2;
    private HospitalAdapter lefrAdapter, rightAdapter;
    private Map<String, String> map;
    private String cacheName;
    private Map<String, String> examineTwo;

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_select_examine);
        MyApplication.getInstance().addActivity(this);
    }


    @Override
    protected void initializeView() {
        super.initializeView();
        setTitle("检验项目选择");
        listLeft = ((ListView) findViewById(R.id.left));
        listRight = ((ListView) findViewById(R.id.right));
        listLeft.setOnItemClickListener(this);
        listRight.setOnItemClickListener(this);
        examine1 = new ArrayList<>();
        examine2 = new ArrayList<>();
        map = new HashMap<>();
        examineTwo = new HashMap<>();
    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        getTestItem();
        rightAdapter = new HospitalAdapter(examine2, this, 1);
        listRight.setAdapter(rightAdapter);
        cacheName = getIntent().getStringExtra("cacheName");
    }

    //获取检验项目列表
    private void getTestItem() {
        Request request = new Request(UrlHelper.TEXT_ITEMS_LIST, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                examine1.clear();
                Examine examine = JsonParser.deserializeFromJson(result, Examine.class);
                data = examine.getData();
                for (Examine.DataEntity ex : data) {
                    examineTwo.put(ex.getId(), ex.getName());
                }
                getLeftData();
                getRightData(0);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    //获取左边列表数据
    private void getLeftData() {
        for (Examine.DataEntity ex : data) {
            String inspectName = ex.getInspectName();
            String name = ex.getName();
            map.put(name, inspectName);
        }
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            if (!examine1.contains(next.getValue())) {
                examine1.add(next.getValue());
            }
        }
        lefrAdapter = new HospitalAdapter(examine1, SelectExamineActivity.this, 0);
        listLeft.setAdapter(lefrAdapter);
    }

    //获取右边列表数据
    private void getRightData(int position) {
        String s1 = examine1.get(position);
        examine2.clear();
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            if (next.getValue().equals(s1)) {
                examine2.add(next.getKey());
            }
        }
        rightAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == listLeft.getId()) {
            lefrAdapter.setSelectItem(position);
            lefrAdapter.notifyDataSetChanged();
            getRightData(position);
        } else if (parent.getId() == listRight.getId()) {
            String testName = examine2.get(position);
            String testId = "";
            Iterator<Map.Entry<String, String>> entryIterator = examineTwo.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, String> next = entryIterator.next();
                if (next.getValue().equals(testName)) {
                    testId = next.getKey();
                }
            }
            Iterator<TestSuggest> iterator = DoctorSuggestActivity.tests.iterator();
            boolean b = false;
            while (iterator.hasNext()) {
                TestSuggest next = iterator.next();
                if (next.getTestId().equals(testId)) {
                    b = true;
                }
            }
            if (!b) {
                saveTestItem(testName, testId);
//                submitTestItem(testName, data.get(position).getId());
            } else {
                Toast.makeText(this, "已选择该检验项目，无法重复选择", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, DoctorSuggestActivity.class);
            intent.putExtra("result", testName);
            setResult(11, intent);
            finish();
        }
    }


    private void saveTestItem(String name, String id) {
        Log.d("TAG", "检验项目id" + id);
        Log.d("TAG", "检验项目名称" + name);
        DoctorSuggestActivity.tests.add(new TestSuggest(id, name));
        SharedPreferences preferences = getSharedPreferences(cacheName, MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("testName", name);
        edit.putString("testId", id);
        edit.commit();
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
                Intent intent = new Intent(SelectExamineActivity.this, DoctorSuggestActivity.class);
                setResult(11, intent);
                finish();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sreach_examine, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.sreach_examine) {
            Intent intent = new Intent(this, SearchHospitalActivity.class);
            Log.d("TAG", "前往检验项目搜索");
            intent.putExtra("TYPE", Constants.SREACH_TESTITEM_VALUES);
            intent.putExtra("cacheName", cacheName);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
