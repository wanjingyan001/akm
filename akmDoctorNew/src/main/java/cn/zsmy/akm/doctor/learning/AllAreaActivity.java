package cn.zsmy.akm.doctor.learning;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.doctor.base.BaseTitleListActivity;
import cn.zsmy.akm.doctor.bean.Area;
import cn.zsmy.akm.doctor.conversation.PublishedActivity;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * 所有版块
 * Created by Administrator on 2016/1/9.
 */
public class AllAreaActivity extends BaseTitleListActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    private String className;

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_all_area);
    }


    @Override
    protected void initializeView() {
        super.initializeView();

    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        setTitle("所有版块");
        Intent intent = getIntent();
        className = intent.getStringExtra("ClassName");
    }

    public static Intent getIntent(Context context, String ClassName) {
        Intent intent = new Intent(context, AllAreaActivity.class);
        intent.putExtra("ClassName", ClassName);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllArea();
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);

        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                getAllArea();
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }


    private void getAllArea() {
        Request request = new Request(UrlHelper.ALL_AREA + "?flag=1", this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                modules.clear();
                Area area = JsonParser.deserializeFromJson(result, Area.class);
                modules.addAll(area.getData());
                showContent();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        String title = ((Area.DataEntity) modules.get(position - 1)).getTitle();
        String areaid = ((Area.DataEntity) modules.get(position - 1)).getId();
        Intent intent = new Intent();
        intent.putExtra("AreaId", areaid);
        intent.putExtra("AreaName", title);
        if (className.equals("CaseList")) {
            intent.setClass(this, CaseListActivity.class);
        } else if (className.equals("Published")) {
            intent.setClass(this, PublishedActivity.class);
        }
        setResult(10, intent);
        finish();
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(android.R.layout.simple_list_item_1, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private TextView text1;

        @Override
        public void initializeView(View view) {
            text1 = ((TextView) view.findViewById(android.R.id.text1));
        }

        @Override
        public void initializeData(int position) {
            Area.DataEntity dataEntity = (Area.DataEntity) modules.get(position);
            text1.setText(dataEntity.getTitle());
        }
    }
}
