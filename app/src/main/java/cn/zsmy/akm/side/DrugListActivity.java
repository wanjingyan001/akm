package cn.zsmy.akm.side;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.interrogation.DrugDetailActivity;
import cn.zsmy.akm.side.adapter.DrugAdapter;
import cn.zsmy.akm.side.bean.Drugs;
import cn.zsmy.akm.utils.UrlHelpper;


/**
 * Created by sanz on 2015/12/18 10:14
 */
public class DrugListActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener {
    private PullToRefreshListView refreshListView;
    private DrugAdapter adapter;
    private List<Drugs.DataEntity> datas;
    private int i = 1;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, DrugListActivity.class);
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_drug_list);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        refreshListView = (PullToRefreshListView) findViewById(R.id.generalPullToRefreshLsv);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListView.setOnItemClickListener(this);
        refreshListView.setOnRefreshListener(this);
        datas = new ArrayList<>();

    }

    @Override
    protected void initializeData() {
        setTitle("阿克曼皮肤药房");
        getDrugList(0);
    }

    private void getDrugList(final int page) {
        Request request = new Request(UrlHelpper.DRUG_LIST + "&page=" + page+"&size="+10, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Drugs drugs = JsonParser.deserializeFromJson(result, Drugs.class);
                if (page == 0) {
                    datas.clear();
                }
                datas.addAll(drugs.getData());
                adapter = new DrugAdapter(DrugListActivity.this, datas);
                refreshListView.setAdapter(adapter);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DrugDetailActivity.class);
        intent.putExtra("durgId", datas.get(position - 1).getId());
        startActivity(intent);
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        refreshListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshListView.onRefreshComplete();
                getDrugList(i++);
            }
        }, 1000);
    }
}
