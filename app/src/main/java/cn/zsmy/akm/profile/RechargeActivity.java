package cn.zsmy.akm.profile;

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
import cn.zsmy.akm.entity.Recharge;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * 充值界面
 * Created by wanjingyan
 * on 2015/12/9 09:37.
 */
public class RechargeActivity extends BaseTitleListActivity {
    private TextView mCurrentBalance;
    private List<Recharge.DataEntity> data;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_recharge);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        setTitle(getText(R.string.mBalanceInLabel));
        mCurrentBalance = ((TextView) findViewById(R.id.current_balance));
    }

    @Override
    protected void initializeData() {
        Request request = new Request(UrlHelpper.RECHARGE, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Recharge recharge = JsonParser.deserializeFromJson(result, Recharge.class);
                data = recharge.getData();
                modules.clear();
                modules.addAll(data);
                showContent();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RechargeActivity.class);
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.layout_recharge_list_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private TextView mRechargeAmount;//充值金额
        private TextView mSendMoneyAmount;//赠送金额
        private TextView mSpendAmount;//花费金额

        @Override
        public void initializeView(View view) {
            mRechargeAmount = ((TextView) view.findViewById(R.id.recharge_amount));
            mSendMoneyAmount = ((TextView) view.findViewById(R.id.send_money_amount));
            mSpendAmount = ((TextView) view.findViewById(R.id.spend_amount));
        }

        @Override
        public void initializeData(int position) {
            Recharge.DataEntity entity = (Recharge.DataEntity) modules.get(position);
            mRechargeAmount.setText("充值" + entity.getTitle() + "元");
            mSendMoneyAmount.setText("赠送" + entity.getContent() + "元");
            mSpendAmount.setText("¥" + entity.getTitle());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = RechargeModeActivity.getIntent(RechargeActivity.this);
        Recharge.DataEntity dataEntity = data.get(position - 1);
        intent.putExtra("id", dataEntity.getId());
        intent.putExtra("recharge", dataEntity.getTitle());
        intent.putExtra("spend", dataEntity.getContent());
        System.out.println("ID:"+dataEntity.getId());
        startActivity(intent);
        finish();
        super.onItemClick(parent, view, position, id);
    }
}
