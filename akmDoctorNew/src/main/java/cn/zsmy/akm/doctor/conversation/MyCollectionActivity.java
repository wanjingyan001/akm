package cn.zsmy.akm.doctor.conversation;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.doctor.base.BaseTitleListActivity;
import cn.zsmy.akm.doctor.chat.utils.Trace;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.learning.bean.PatientCase;
import cn.zsmy.akm.doctor.utils.DateUtils;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * 我的收藏
 * Created by Administrator on 2015/12/18.
 */
public class MyCollectionActivity extends BaseTitleListActivity {
    private int i = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                showContent(EmptyView.State.empty, 1);
            } else {
                showContent(EmptyView.State.ing, 1);
            }

        }
    };

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_my_collection);
        MyApplication.getInstance().addActivity(this);
    }


    @Override
    protected void initializeView() {
        super.initializeView();
        setTitle(getText(R.string.illness_collection));
    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        getPostList(i);
    }

    private void getPostList(int i) {
        Request request = new Request(UrlHelper.GET_POST_LIST + "?page=" + i + "&size=5&flag=1&shareFlag=1", this);
        Trace.d(UrlHelper.GET_POST_LIST + "?page=" + i + "&size=5&flag=1&shareFlag=1");
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("code").equals("NO_DATA")) {
                        handler.sendEmptyMessageDelayed(0, 1000);
                    } else if (object.getString("code").equals("SUCC")) {
                        modules.clear();
                        PatientCase patientCase = JsonParser.deserializeFromJson(result, PatientCase.class);
                        List<PatientCase.DataEntity> data = patientCase.getData();
                        modules.addAll(data);
                        if (modules.size() > 0) {
                            handler.sendEmptyMessageDelayed(1, 1000);
                        } else {
                            handler.sendEmptyMessageDelayed(0, 1000);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                getPostList(++i);
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MyCollectionActivity.class);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        // TODO: 跳转至帖子详情界面
        PatientCase.DataEntity entity = (PatientCase.DataEntity) modules.get(position - 1);
        Intent intent = PostDetailActivity.getIntent(this, entity);
        startActivity(intent);
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.item_collection_post, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {

        private TextView cPostTitle;
        private TextView cTime;

        @Override
        public void initializeView(View view) {
            cPostTitle = ((TextView) view.findViewById(R.id.collect_post_title));
            cTime = ((TextView) view.findViewById(R.id.collection_time));
        }

        /**
         * @param position
         */
        @Override
        public void initializeData(int position) {
            PatientCase.DataEntity entity = (PatientCase.DataEntity) modules.get(position);
            cPostTitle.setText(entity.getTitle());
            cTime.setText(DateUtils.getDateToString(entity.getCreateTime(), 3));
        }
    }
}
