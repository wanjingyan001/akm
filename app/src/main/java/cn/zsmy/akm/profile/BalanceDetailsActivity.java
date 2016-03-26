package cn.zsmy.akm.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.BalanceDetail;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * 余额明细
 * Created by qinwei on 2015/11/24 17:15
 */
public class BalanceDetailsActivity extends BaseTitleListActivity {
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
        setContentView(R.layout.activity_profile_balance_detail_list);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeData() {
        setTitle(getString(R.string.mProfileBalanceDetailLabel));
        getBalanceDetails();
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    public static Intent newIntetn(Context context) {
        return new Intent(context, BalanceDetailsActivity.class);
    }


    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                getBalanceDetails();
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);

    }


    /**
     * 获取余额详情
     */
    public void getBalanceDetails() {
        Request request = new Request(UrlHelpper.ACCOUNT_DETAILS, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                modules.clear();
                BalanceDetail balanceDetail = JsonParser.deserializeFromJson(result, BalanceDetail.class);
                modules.addAll(balanceDetail.getData());
                if (modules.size()==0){
                    handler.sendEmptyMessageDelayed(0,1000);
                }else {
                    handler.sendEmptyMessageDelayed(1,1000);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.layout_integral_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private TextView mBalcance;
        private TextView mSpendingTime;
        private TextView mBalanceChange;
        private TextView mSpendType;

        @Override
        public void initializeView(View view) {
            mBalcance = ((TextView) view.findViewById(R.id.user_balance));
            mSpendingTime = ((TextView) view.findViewById(R.id.spending_time));
            mBalanceChange = ((TextView) view.findViewById(R.id.balance_change));
            mSpendType = ((TextView) view.findViewById(R.id.consumption_type));
        }

        @Override
        public void initializeData(int position) {
            BalanceDetail.DataEntity dataEntity = (BalanceDetail.DataEntity) modules.get(position);
            int endAmount = dataEntity.getEndAmount();
            int amount = dataEntity.getAmount();
            long createTime = dataEntity.getCreateTime();
            String zType = dataEntity.getZType();
            mBalcance.setText("余额："+endAmount);
            mSpendingTime.setText(DateUtils.getDateToString(createTime, 2));
            if (zType.equals("1")) {
                mSpendType.setText("问诊消费");
                mBalanceChange.setText("-" + amount);
            } else if (zType.equals("0")) {
                mSpendType.setText("充值");
                mBalanceChange.setText("+" + amount);
            }
        }
    }
}
