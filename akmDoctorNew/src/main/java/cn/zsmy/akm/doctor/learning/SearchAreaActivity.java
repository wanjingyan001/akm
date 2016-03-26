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
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.learning.adapter.ScholarshipAdapter;
import cn.zsmy.akm.doctor.learning.bean.PatientCase;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2016/1/16 13:22
 */
public class SearchAreaActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener,View.OnClickListener{
    private PullToRefreshListView refreshListView;
    private List<PatientCase.DataEntity> datas;
    private ScholarshipAdapter adapter;
    private EditText seacrh_edit;
    private TextView to_search;
    private String TAG=this.getClass().getSimpleName();
    public static Intent getIntent(Context context) {
        return new Intent(context, SearchAreaActivity.class);
    }
    @Override
    protected void setContentView() {
          setContentView(R.layout.activity_search_area);
    }
    @Override
    protected void initializeView() {
        super.initializeView();
        refreshListView = (PullToRefreshListView)findViewById(R.id.generalPullToRefreshLsv);
        refreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
        seacrh_edit=(EditText)findViewById(R.id.seacrh_edit);
        to_search=(TextView)findViewById(R.id.search_tex);
        to_search.setOnClickListener(this);
        datas=new ArrayList<>();
        adapter = new ScholarshipAdapter(this, datas);
        refreshListView.setAdapter(adapter);
        refreshListView.setOnItemClickListener(this);

    }


    @Override
    protected void initializeData() {
        showContent();
    }
    private void getPostList() {
        String name = null;
        try {
           name= URLEncoder.encode(seacrh_edit.getText().toString(),"UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (name!=""){
            Log.i(TAG,UrlHelper.GET_POST_LIST + "?title="+name+"&flag=0");
            Request request = new Request(UrlHelper.GET_POST_LIST + "?title="+name+"&flag=0",this);
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    PatientCase topicsList= JsonParser.deserializeFromJson(result, PatientCase.class);
                    datas.addAll(topicsList.getData());
                    adapter.notifyDataSetChanged();
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        }else {
            Toast.makeText(getApplicationContext(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PatientCase.DataEntity dataEntity = datas.get(position - 1);
        startActivity(PostDetailActivity.getIntent(this, dataEntity));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_tex:
                getPostList();
                break;
        }

    }

}
