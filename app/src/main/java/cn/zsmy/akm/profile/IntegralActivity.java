package cn.zsmy.akm.profile;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.Integral;
import cn.zsmy.akm.entity.Profile;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * 我的积分
 * Created by qinwei on 2015/11/24 17:19
 */
public class IntegralActivity extends BaseTitleListActivity {
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
    private Profile profile;
    private TextView mCurrentBalance;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_profile_integral);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mCurrentBalance = ((TextView) findViewById(R.id.current_balance));
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void initializeData() {
        setTitle(getString(R.string.mProfileIntegralLabel));
        profile = MyApplication.getProfile();
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", -1);
        mCurrentBalance.setText(String.valueOf(score));
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadMyIntegral();
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        loadMyIntegral();
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    //获取积分变动信息
    private void loadMyIntegral() {
        Request request = new Request(UrlHelpper.MY_INTEGRAL_URL, Request.RequestMethod.GET, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.i("TAG", result);
                Integral integral = JsonParser.deserializeFromJson(result, Integral.class);
                List<Integral.DataEntity> data = integral.getData();
                modules.clear();
                modules.addAll(data);
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
        private TextView mConsumptionType;

        @Override
        public void initializeView(View view) {
            mConsumptionType = ((TextView) view.findViewById(R.id.consumption_type));
            mBalcance = ((TextView) view.findViewById(R.id.user_balance));
            mSpendingTime = ((TextView) view.findViewById(R.id.spending_time));
            mBalanceChange = ((TextView) view.findViewById(R.id.balance_change));
        }

        @Override
        public void initializeData(int position) {
            Integral.DataEntity integralInfo = (Integral.DataEntity) modules.get(position);
            mBalcance.setText("余额：" + integralInfo.getEndAmount());
            long createTime = integralInfo.getCreateTime();
            String dateTime = DateUtils.getDateToString(createTime, 0);
            mSpendingTime.setText(dateTime);
            String zType = integralInfo.getZType();
            if (zType.equals("0")) {
                mBalanceChange.setText("+" + integralInfo.getAmount());
            } else if (zType.equals("1")) {
                mBalanceChange.setText("-" + integralInfo.getAmount());
            }
        }
    }
}
