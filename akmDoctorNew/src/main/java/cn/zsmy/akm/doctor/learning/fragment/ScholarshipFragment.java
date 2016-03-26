package cn.zsmy.akm.doctor.learning.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.wei.library.fragment.BaseFragment;
import cn.zsmy.akm.doctor.conversation.PostDetailActivity;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.learning.adapter.ScholarshipAdapter;
import cn.zsmy.akm.doctor.learning.bean.Area;
import cn.zsmy.akm.doctor.learning.bean.PatientCase;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/18 15:49
 */
public class ScholarshipFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private PullToRefreshListView refreshListView;
    private List<PatientCase.DataEntity> datas;
    private ScholarshipAdapter adapter;
    private Area title;
    private String TAG = this.getClass().getSimpleName();
    private String url;
    private int i = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = (Area) getArguments().getSerializable("AREA");
        }
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_scholarship;
    }

    @Override
    protected void initializeView(View v) {
        refreshListView = (PullToRefreshListView) v.findViewById(R.id.generalPullToRefreshLsv);
        datas = new ArrayList<>();
        adapter = new ScholarshipAdapter(getActivity(), datas);
        refreshListView.setAdapter(adapter);
        refreshListView.setOnItemClickListener(this);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //下拉刷新，获取新帖子；上拉加载，获取更多
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

    private void getPostList(final int page) {
        String status = null;
        if (title.getStatus() == 0) {
            url = UrlHelper.GET_POST_LIST + "?flag=0" + "&forumPlateId=" + title.getId() + "&page=" + page + "&size=10&forumPlateFlag=1";
        } else if (title.getStatus() == 1) {
            status = null;
            url = UrlHelper.GET_POST_LIST + "?flag=0" + "&page=" + page + "&size=10&forumPlateFlag=1";
        } else {
            status = String.valueOf(title.getStatus());
            url = UrlHelper.GET_POST_LIST + "?flag=0" + "&status=" + status + "&page=" + page + "&size=10&forumPlateFlag=1";
        }
        Log.i(TAG, url);
        Request request = new Request(url, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                if (page == 0) {
                    datas.clear();
                }
                PatientCase post = JsonParser.deserializeFromJson(result, PatientCase.class);
                Log.i(TAG, post.getData().get(0).getContent());
                datas.addAll(post.getData());
                Log.i(TAG, datas.get(0).getTitle());
                adapter.notifyDataSetChanged();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PatientCase.DataEntity data = (PatientCase.DataEntity) datas.get(position - 1);
        startActivity(PostDetailActivity.getIntent(getActivity(), data));
    }

    @Override
    public void onStart() {
        super.onStart();
        getPostList(0);
    }


}
