package cn.zsmy.akm.doctor.admissions;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.handmark.pulltorefresh.PullToRefreshExpandableListView;

import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.doctor.admissions.adapter.MyexpandableListAdapter;
import cn.zsmy.akm.doctor.admissions.bean.Contact;
import cn.zsmy.akm.doctor.admissions.view.ContactOfficeTopView;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.chat.ChatActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/16 13:11
 */
public class ContactOfficeActivity extends BaseTitleActivity implements ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener, PullToRefreshBase.OnRefreshListener<ExpandableListView> {
    private String url;
    private int type;
    private ContactOfficeTopView topView;
    private FrameLayout topLayout;
    private Contact.DataEntity dataEntity;
    private PullToRefreshExpandableListView pullToRefreshExpandableListView;
    private ExpandableListView expandableListView;
    private ArrayList<String> groupList;
    private ArrayList<List<Contact.DataEntity>> childList;
    private MyexpandableListAdapter adapter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (topView != null) {
                topView.setVisibility(View.GONE);
            }
        }
    };

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ContactOfficeActivity.class);
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_contact_office);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        groupList = new ArrayList<>();
        childList = new ArrayList<>();
        groupList.add("待接诊");
        groupList.add("已接诊");
        pullToRefreshExpandableListView = (PullToRefreshExpandableListView) findViewById(R.id.expandablelist);
        pullToRefreshExpandableListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        expandableListView = pullToRefreshExpandableListView.getRefreshableView();
        adapter = new MyexpandableListAdapter(this, childList, groupList);
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupClickListener(this);
        expandableListView.setOnChildClickListener(this);
        pullToRefreshExpandableListView.setOnRefreshListener(this);
        topLayout = (FrameLayout) findViewById(R.id.contact_office_top_view);
    }

    @Override
    protected void initializeData() {
        topView = new ContactOfficeTopView(this);
        topLayout.addView(topView);
        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE", -1);
        topView.remind(type);
        switch (type) {
            case 0:
                setTitle("普通接诊室");
                url = UrlHelper.ORDINARY_ADMISSIONS;
                break;
            case 1:
                setTitle("VIP接诊室");
                url = UrlHelper.VIP_CONTACT_OFFICE;
                break;
        }
        handler.sendEmptyMessageDelayed(0, 30 * 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh(pullToRefreshExpandableListView);
    }

    private void loadContactOfficeInfo(String url) {
        Request request = new Request(url, Request.RequestMethod.GET, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Contact contact = JsonParser.deserializeFromJson(result, Contact.class);
                childList.clear();
                List<Contact.DataEntity> data = contact.getData();
                ArrayList<Contact.DataEntity> child_datas_No = new ArrayList<Contact.DataEntity>();
                ArrayList<Contact.DataEntity> child_datas_Yes = new ArrayList<Contact.DataEntity>();
                if (data != null) {
                    for (Contact.DataEntity con : data) {
                        Log.i("TAG", con.getStatus());
                        if (con.getStatus().equals("0")) {
                            child_datas_No.add(con);

                        } else if (!con.getStatus().equals("0")) {
                            child_datas_Yes.add(con);

                        }
                    }
                    childList.add(child_datas_No);
                    childList.add(child_datas_Yes);
                    adapter.notifyDataSetChanged();
                    pullToRefreshExpandableListView.onRefreshComplete();

                } else {
                    childList.add(null);
                }
                // 展开所有group
                for (int i = 0, count = expandableListView.getCount(); i < count; i++) {
                    expandableListView.expandGroup(i);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);

    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        if (childList == null || childList.get(0) == null) {
            expandableListView.setClickable(false);
        } else {
            dataEntity = childList.get(groupPosition).get(childPosition);
            if (dataEntity != null && dataEntity.getId() != null && dataEntity.getInquirer() != null && dataEntity.getStatus() != null) {
                Intent intent = ChatActivity.getIntent(this, dataEntity);
                intent.putExtra("TYPE", type);
                startActivity(intent);
            } else {
                Toast.makeText(this, "无此病例", Toast.LENGTH_LONG).show();
            }
        }
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

        return true;
    }

    @Override
    public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
        pullToRefreshExpandableListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (childList.size() != 1) {
                    loadContactOfficeInfo(url);
                }
                pullToRefreshExpandableListView.onRefreshComplete();
            }
        }, 1000);

    }
}
