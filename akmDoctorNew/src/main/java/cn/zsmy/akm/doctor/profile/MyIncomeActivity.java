package cn.zsmy.akm.doctor.profile;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.doctor.R;

/**
 * Created by sanz  on 2015/12/22 16:03
 */
public class MyIncomeActivity extends BaseTitleActivity implements View.OnClickListener {
    private Button button;
    private TextView Balance;
    private TextView availableBalance;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MyIncomeActivity.class);

        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_my_income);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        button = (Button) findViewById(R.id.next_balance_detail);
        Balance = ((TextView) findViewById(R.id.all_money_text));
        availableBalance = ((TextView) findViewById(R.id.available_balance));
        button.setOnClickListener(this);
    }

    @Override
    protected void initializeData() {
        setTitle("我的收益");
        Intent intent = getIntent();
        String balance = intent.getStringExtra("balance");
        String avail = intent.getStringExtra("avail");
        Balance.setText(balance);
        availableBalance.setText(avail);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_balance_detail:
                Intent intent = new Intent(this, BalanceDetailsActivity.class);
                startActivity(intent);
                break;

        }
    }
}
