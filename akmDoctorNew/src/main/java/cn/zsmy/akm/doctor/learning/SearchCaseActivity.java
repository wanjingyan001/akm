package cn.zsmy.akm.doctor.learning;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.conversation.PostDetailActivity;
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
 * Created by sanz on 2016/1/25 16:05
 */
public class SearchCaseActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private PullToRefreshListView refreshListView;
    private CaseAdapter adapter;
    private ArrayList<PatientCase.DataEntity> datas;
    private String url;
    private EditText seacrh_edit;
    private TextView to_search;
    private String className;

    public static Intent getIntent(Context context, String className) {
        Intent intent = new Intent(context, SearchCaseActivity.class);
        intent.putExtra("ClassName", className);
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_search_area);
        MyApplication.getInstance().addActivity(this);
        className = getIntent().getStringExtra("ClassName");
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        refreshListView = (PullToRefreshListView) findViewById(R.id.generalPullToRefreshLsv);
        refreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
        seacrh_edit = (EditText) findViewById(R.id.seacrh_edit);
        to_search = (TextView) findViewById(R.id.search_tex);
        to_search.setOnClickListener(this);
        datas = new ArrayList<>();
        refreshListView.setOnItemClickListener(this);
        adapter = new CaseAdapter(SearchCaseActivity.this, datas);
        refreshListView.setAdapter(adapter);

    }

    @Override
    protected void initializeData() {
        showContent();
    }

    @Override
    public void onClick(View v) {
        getPostList();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PatientCase.DataEntity dataEntity = datas.get(position - 1);
        startActivity(PostDetailActivity.getIntent(this,dataEntity));
    }

    private void getPostList() {
        String title = null;
        try {
            title = URLEncoder.encode(seacrh_edit.getText().toString(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (title != "") {
            url = UrlHelper.GET_POST_LIST + "?flag=1" + "&title=" + title+"&size=10";
            Log.i("TAG", url);
            Request request = new Request(url, this);
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    datas.clear();
                    PatientCase patientCase = JsonParser.deserializeFromJson(result, PatientCase.class);
                    List<PatientCase.DataEntity> data = patientCase.getData();
                    datas.addAll(data);
                    adapter.notifyDataSetChanged();
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        } else {
            Toast.makeText(SearchCaseActivity.this, "请输入搜索字", Toast.LENGTH_SHORT).show();
        }
    }
}
