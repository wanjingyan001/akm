package cn.zsmy.akm.doctor.learning;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.chat.utils.ProgressDialogUtils;
import cn.zsmy.akm.doctor.conversation.PostDetailActivity;
import cn.zsmy.akm.doctor.conversation.PublishedActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.learning.adapter.CaseAdapter;
import cn.zsmy.akm.doctor.learning.bean.PatientCase;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/18 15:46
 * 病例
 */
public class CaseListActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private PullToRefreshListView refreshListView;
    private CaseAdapter adapter;
    private ArrayList<PatientCase.DataEntity> datas;
    private RadioButton classic_btn, new_btn;
    private String url;
    private String status = "4";
    private int i = 1;
    private RadioGroup choosePlate;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, CaseListActivity.class);
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_case_list);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        refreshListView = (PullToRefreshListView) findViewById(R.id.generalPullToRefreshLsv);
        findViewById(R.id.search_case).setOnClickListener(this);
        findViewById(R.id.edit_case).setOnClickListener(this);
        choosePlate = ((RadioGroup) findViewById(R.id.choose_plate));
        choosePlate.setOnCheckedChangeListener(this);
        classic_btn = ((RadioButton) findViewById(R.id.classic_case));
        new_btn = ((RadioButton) findViewById(R.id.new_case));
        classic_btn.setOnClickListener(this);
        new_btn.setOnClickListener(this);
        datas = new ArrayList<>();
        refreshListView.setOnItemClickListener(this);
        adapter = new CaseAdapter(CaseListActivity.this, datas);
        refreshListView.setAdapter(adapter);
    }

    @Override
    protected void initializeData() {
        setTitle("病例");
        getPostList(0);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    getPostList(0);
                } else if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                    getPostList(i++);
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
    protected void onResume() {
        super.onResume();

    }

    private void getPostList(int page) {
        url = UrlHelper.GET_POST_LIST + "?flag=1" + "&status=" + status + "&page=" + page + "&forumPlateFlag=1";
        Log.i("TAG", url);
        Request request = new Request(url, this);
        ProgressDialogUtils.showProgressDialog(this, "正在加载");
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                datas.clear();
                PatientCase patientCase = JsonParser.deserializeFromJson(result, PatientCase.class);
                List<PatientCase.DataEntity> data = patientCase.getData();
                datas.addAll(data);
                adapter.notifyDataSetChanged();
                ProgressDialogUtils.closeProgressDialog(CaseListActivity.this);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PatientCase.DataEntity dataEntity = datas.get(position - 1);
        startActivity(PostDetailActivity.getIntent(this, dataEntity));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_case:
                startActivity(SearchCaseActivity.getIntent(this, "CaseList"));
                break;
            case R.id.edit_case:
                startActivity(PublishedActivity.getIntent(this, "from_case_list"));
                break;
            case R.id.classic_case:
                status = "4";
                getPostList(0);
                break;
            case R.id.new_case:
                status = "5";
                getPostList(0);
                break;
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.classic_case) {
            status = "4";
        } else if (checkedId == R.id.new_case) {
            status = "5";
        }
    }
}
