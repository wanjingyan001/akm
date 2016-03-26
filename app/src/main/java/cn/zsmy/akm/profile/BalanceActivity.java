package cn.zsmy.akm.profile;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;

/**
 * 我的余额
 * Created by qinwei on 2015/11/24 17:12
 */
public class BalanceActivity extends BaseTitleActivity implements View.OnClickListener {
    private TextView mValidAmount;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_profile_balance);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        findViewById(R.id.user_recharge).setOnClickListener(this);
        findViewById(R.id.withdraw).setOnClickListener(this);
        mValidAmount = ((TextView) findViewById(R.id.validAmount));
    }

    @Override
    protected void initializeData() {
        setTitle(getResources().getString(R.string.mBalanceLabel));
        Intent intent = getIntent();
        int validAmount = intent.getIntExtra("validAmount", -1);
        mValidAmount.setText(validAmount + "元");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_balance_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_balance_detail) {
            goBalanceDetail();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goBalanceDetail() {
        startActivity(BalanceDetailsActivity.newIntetn(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_recharge:
                startActivity(RechargeActivity.newIntent(BalanceActivity.this));
                break;
            case R.id.withdraw:

                break;
        }

    }
}
