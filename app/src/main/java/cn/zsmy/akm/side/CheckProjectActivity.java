package cn.zsmy.akm.side;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.interrogation.InterrogationSuggestActivity;
import cn.zsmy.akm.side.bean.TestItem;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * 检验项目
 * Created by sanz on 2015/12/17 16:10
 */
public class CheckProjectActivity extends BaseTitleListActivity {
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_search);
        MyApplication.getInstance().addActivity(this);

    }

    @Override
    protected void initializeView() {
        super.initializeView();

    }

    public static Intent getIntent(Context context) {
        return new Intent(context, CheckProjectActivity.class);
    }

    @Override
    protected void initializeData() {
        setTitle("阿克曼皮肤检验科");
        seacherTest();
    }

    /**
     * 检验项目
     */
    private void seacherTest() {
        Request request = new Request(UrlHelpper.TEXT_ITEMS_LIST, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                modules.clear();
                TestItem testItem = JsonParser.deserializeFromJson(result, TestItem.class);
                List<TestItem.DataEntity> data = testItem.getData();
                modules.addAll(data);
                adapter.notifyDataSetChanged();
                showContent();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                seacherTest();
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        TestItem.DataEntity dataEntity = (TestItem.DataEntity) modules.get(position - 1);
        Intent intent = InterrogationSuggestActivity.getIntent(this, dataEntity.getId());
        startActivity(intent);
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.item_check_project, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private TextView listTitle;

        @Override
        public void initializeView(View view) {
            listTitle = ((TextView) view.findViewById(R.id.hospital_name_tex));
        }

        @Override
        public void initializeData(int position) {
            //搜索检验项目
            TestItem.DataEntity dataEntity1 = (TestItem.DataEntity) modules.get(position);
            listTitle.setText(dataEntity1.getName());
        }
    }
}
