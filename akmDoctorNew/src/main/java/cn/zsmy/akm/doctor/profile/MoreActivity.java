package cn.zsmy.akm.doctor.profile;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.messageCenter.FeedbackActivity;
import cn.zsmy.akm.doctor.profile.adapter.MAdapter;
import cn.zsmy.doctor.R;

/**
 * Created by Administrator on 2015/12/28.
 */
public class MoreActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener {
    private ListView listview;
    private List<Map<String, Integer>> maps;

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_more);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        listview = ((ListView) findViewById(R.id.more_list));
        listview.setOnItemClickListener(this);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MoreActivity.class);
    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        setTitle("更多");
        maps = new ArrayList<>();
        String[] strings = {"关于我们", "服务协议", "帮助", "意见反馈"};
        int[] imgs = {R.mipmap.ic_about_us, R.mipmap.ic_service, R.mipmap.ic_help, R.mipmap.ic_feedback};
        for (int i = 0; i < strings.length; i++) {
            Map<String, Integer> map = new HashMap<>();
            map.put(strings[i], imgs[i]);
            maps.add(map);
        }
        MAdapter mAdapter = new MAdapter(this, maps);
        listview.setAdapter(mAdapter);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(this, AboutUsActivity.class);
                break;
            case 1:
                intent.setClass(this, UserAgreementActivity.class);
                break;
            case 2:
                intent.setClass(this, UserHelpActivity.class);
                break;
            case 3:
                intent.setClass(this, FeedbackActivity.class);
                break;
        }
        startActivity(intent);
    }
}
