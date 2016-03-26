package cn.zsmy.akm.doctor.profile;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handmark.pulltorefresh.PullToRefreshListView;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.doctor.base.BaseTitleListActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.profile.adapter.BalanceDetailAdapter;
import cn.zsmy.akm.doctor.profile.bean.BalanceDetail;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/22 16:47
 * 收支明细
 */
public class BalanceDetailsActivity extends BaseTitleListActivity {
    private BalanceDetailAdapter adapter;
    private PullToRefreshListView refreshListView;
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


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_balance_detail);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
    }

    @Override
    protected void initializeData() {
        setTitle("收支明细");
        getBalanceDetails();
    }

    /**
     * 获取余额详情
     */
    public void getBalanceDetails() {
        Request request = new Request(UrlHelper.ACCOUNT_DETAILS, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                modules.clear();
                BalanceDetail balanceDetail = JsonParser.deserializeFromJson(result, BalanceDetail.class);
                modules.addAll(balanceDetail.getData());

            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
        if (modules.size() > 0) {
            handler.sendEmptyMessageDelayed(1, 1000);
        } else {
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.item_profile_integral, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private TextView consumptionType;
        private TextView balance;
        private TextView spendingTime;
        private TextView balanceChange;

        @Override
        public void initializeView(View view) {
            consumptionType = ((TextView) view.findViewById(R.id.consumption_type));
            balance = ((TextView) view.findViewById(R.id.balance));
            spendingTime = ((TextView) view.findViewById(R.id.spending_time));
            balanceChange = ((TextView) view.findViewById(R.id.balance_change));
        }

        @Override
        public void initializeData(int position) {

        }
    }
}
