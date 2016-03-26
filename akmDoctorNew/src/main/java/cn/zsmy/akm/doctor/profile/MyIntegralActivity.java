package cn.zsmy.akm.doctor.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handmark.pulltorefresh.PullToRefreshListView;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.doctor.base.BaseTitleListActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.profile.adapter.BalanceDetailAdapter;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/22 16:54
 */
public class MyIntegralActivity extends BaseTitleListActivity implements View.OnClickListener {
    private BalanceDetailAdapter adapter;
    private PullToRefreshListView refreshListView;
    private TextView mIntegralNumber;
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

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MyIntegralActivity.class);
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_profile_integral);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        refreshListView = (PullToRefreshListView) findViewById(R.id.generalPullToRefreshLsv);
        mIntegralNumber = ((TextView) findViewById(R.id.integral_number));
        findViewById(R.id.exchange_integral).setOnClickListener(this);
    }


    @Override
    protected void initializeData() {
        setTitle("我的积分");
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        if (mIntegralNumber != null) {
            double s = score / 1.00;
            mIntegralNumber.setText(s + "");
        } else {
            mIntegralNumber.setText(null);
        }
        getMyIntegral();
        showContent();
    }

    private void getMyIntegral() {
        Request request = new Request(UrlHelper.USER_INTEGRAL, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", result);

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
        Holder holdView;
        if (convertView == null || convertView.getTag() == null) {
            holdView = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.item_profile_integral, null);
            holdView.initializeView(convertView);
            convertView.setTag(holdView);
        } else {
            holdView = (Holder) convertView.getTag();
        }
        holdView.initializeData(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exchange_integral:
                //兑换积分
                break;
        }
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
