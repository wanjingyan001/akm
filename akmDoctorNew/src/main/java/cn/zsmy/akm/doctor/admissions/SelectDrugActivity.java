package cn.zsmy.akm.doctor.admissions;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.zsmy.akm.doctor.admissions.adapter.DrugAdapter;
import cn.zsmy.akm.doctor.admissions.bean.Disease;
import cn.zsmy.akm.doctor.admissions.bean.Drugs;
import cn.zsmy.akm.doctor.admissions.bean.Sections;
import cn.zsmy.akm.doctor.admissions.view.TabChooseView;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.pop.TowListPopWindow;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/18 10:14
 */
public class SelectDrugActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener, TabChooseView.OnItemChooseClickListener, View.OnClickListener, TowListPopWindow.onPopupWindowItemClickListener {
    private PullToRefreshListView refreshListView;
    private DrugAdapter adapter;
    private List<Drugs.DataEntity> datas;
    private TabChooseView tabChoose;
    private ImageView search;
    private String cacheName;
    private List<Disease.DataEntity> Disease;
    private List<String> secs = new ArrayList<>();
    private List<Sections.DataEntity> data;
    private List<String> disease = new ArrayList<>();
    private Map<String, String> allSec = new HashMap<>();//存放<二级科室名，一级科室名>
    private List<String> twoSec = new ArrayList<>();//二级科室名
    private List<Map<String, String>> idAndName = new ArrayList<>();//存放所有科室名和id
    private String drugId;//科室id
    private String symId;//病症id
    private int i = 1;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_select_drug);
        MyApplication.getInstance().addActivity(this);

    }

    @Override
    protected void initializeView() {
        super.initializeView();
        refreshListView = (PullToRefreshListView) findViewById(R.id.generalPullToRefreshLsv);
        datas = new ArrayList<>();
        search = (ImageView) findViewById(R.id.search_bar);
        search.setOnClickListener(this);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListView.setOnItemClickListener(this);
        tabChoose = (TabChooseView) findViewById(R.id.tabchoose);
        tabChoose.setOnItemChooseChlickListener(this);
        List<String> titles = new ArrayList<>();
        titles.add("全部科室");
        titles.add("全部病症");
        tabChoose.initializeView(titles, this);
        adapter = new DrugAdapter(SelectDrugActivity.this, datas);
        refreshListView.setAdapter(adapter);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    getDrugList(drugId, symId, 0);
                } else if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                    getDrugList(drugId, symId, i++);
                    i = i++;
                }
                refreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshListView.onRefreshComplete();
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void initializeData() {
        setTitle("选择药品");
        cacheName = getIntent().getStringExtra("cacheName");
        getDrugList(drugId, symId, 0);


    }

    private void getDrugList(String deptId, String symptomId, final int page) {
        String url = "";
        if (deptId == null && symptomId == null) {
            url = UrlHelper.DRUG_LIST + "?page=" + page+"&size=10";
        } else if (deptId != null && symptomId == null) {
            url = UrlHelper.DRUG_LIST + "?deptId=" + deptId + "&page=" + page;
        } else if (deptId == null && symptomId != null) {
            url = UrlHelper.DRUG_LIST + "?symptomId=" + symptomId + "&page=" + page;
        } else {
            url = UrlHelper.DRUG_LIST + "?deptId=" + deptId + "&symptomId=" + symptomId + "&page=" + page;
        }
        Request request = new Request(url, this);
        Log.d("TAG", url);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Drugs drugs = JsonParser.deserializeFromJson(result, Drugs.class);
                if (page == 0) {
                    datas.clear();
                }
                datas.addAll(drugs.getData());
                adapter.notifyDataSetChanged();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = getIntent();
        intent.setClass(this, DrugDetailActivity.class);
        intent.putExtra("drugId", datas.get(position - 1).getId());
        intent.putExtra("cacheName", cacheName);
        intent.putExtra("from", "select");
        startActivity(intent);
        finish();
    }


    @Override
    public void OnChooseChlickListener(int id) {
        switch (id) {
            case 0:
                getSectionName();
                TowListPopWindow popWindow = new TowListPopWindow(this, secs, twoSec, ((ImageView) tabChoose.findViewWithTag(2)), 0);
                popWindow.showPopupWindow(tabChoose);
                popWindow.setItemClickListener(this);
                break;
            case 1:
                getDisease();
                TowListPopWindow rightWindow = new TowListPopWindow(this, null, disease, ((ImageView) tabChoose.findViewWithTag(3)), 1);
                rightWindow.showPopupWindow(tabChoose);
                rightWindow.setItemClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_bar:
                Intent intent = new Intent(this, SearchHospitalActivity.class);
                intent.putExtra("TYPE", Constants.SREACH_DRUG_VALUES);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 2 && resultCode == Constants.return_drug) {
            Log.d("TAG", "传递药品信息");
            setResult(resultCode, data);
            finish();
        }
    }

    //获取科室列表
    private void getSectionName() {
        Request request = new Request(UrlHelper.GET_SECTIONS_LIST, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                secs.clear();
                Sections sections = JsonParser.deserializeFromJson(result, Sections.class);
                data = sections.getData();
                for (Sections.DataEntity sec : data) {
                    Map<String, String> map = new HashMap<String, String>();
                    secs.add(sec.getName());
                    map.put(sec.getName(), sec.getId());
                    idAndName.add(map);
                    List<Sections.DataEntity.SubsEntity> subs = sec.getSubs();
                    for (Sections.DataEntity.SubsEntity sub : subs) {
                        Map<String, String> child = new HashMap<String, String>();
                        child.put(sub.getName(), sub.getId());
                        idAndName.add(child);
                        allSec.put(sub.getName(), sec.getName());
                        Log.d("TAG", sub.getName() + "::::" + sec.getName());
                    }
                }
                twoSec = getRightData(secs.get(0));
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    //获取病症用药列表
    private void getDisease() {
        Request request = new Request(UrlHelper.GET_DISEASE, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                disease.clear();
                Disease disEntity = JsonParser.deserializeFromJson(result, Disease.class);
                Disease = disEntity.getData();
                for (cn.zsmy.akm.doctor.admissions.bean.Disease.DataEntity Dis : Disease) {
                    disease.add(Dis.getName());
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public void onItemClick(int position, String str, int type) {
        switch (type) {
            case 0:
//                String id = data.get(position).getId();
//                getSectionDrugName(id);
                getRightData(str);
                break;
            case 1:
                tabChoose.setText(str, 0);
                tabChoose.changeArrow(true, 2);
//                idAndName.
                for (Map<String, String> idandname : idAndName) {
                    Iterator<Map.Entry<String, String>> iterator = idandname.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> next = iterator.next();
                        if (str.equals(next.getKey())) {
                            drugId = next.getValue();
                        }
                    }
                }
                getDrugList(drugId, symId, 0);
                break;
            case 2:
                tabChoose.setText(str, 1);
                tabChoose.changeArrow(true, 3);
                symId = Disease.get(position).getId();
                getDrugList(drugId, symId, 0);
                break;
        }
    }

    private List<String> getRightData(String str) {
        twoSec.clear();
        Set<Map.Entry<String, String>> entries = allSec.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String value = next.getValue();
            if (value != null) {
                if (str.equals(value)) {
                    twoSec.add(next.getKey());
                }
            }
        }
        return twoSec;
    }
}
