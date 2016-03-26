package cn.zsmy.akm.profile;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.utils.Utils;

/**
 * 字段修改
 * Created by qinwei on 2015/11/24 17:18
 */
public class ValueEditorActivity extends BaseTitleActivity implements View.OnClickListener {

    private EditText mUserNameEdt;
    private int type;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_change_name);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        findViewById(R.id.clear_edt).setOnClickListener(this);
        mUserNameEdt = ((EditText) findViewById(R.id.user_name));
    }

    @Override
    protected void initializeData() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getIntExtra("type", -1);
            String value = intent.getStringExtra("value");
            if (value.equals("请输入")) {
                mUserNameEdt.setHint("请输入");
            } else {
                mUserNameEdt.setText(value);
                mUserNameEdt.setSelection(value.length());
            }
            switch (type) {
                case 0:
                    setTitle("修改昵称");
                    break;
                case 1:
                    setTitle("修改电话");
                    mUserNameEdt.setInputType(InputType.TYPE_CLASS_PHONE);
                    break;
            }
        }
    }

    /**
     * in ValueEditorActivity
     *
     * @param context
     * @param type    修改字段类型
     * @param value   回显字段值
     * @return
     */
    public static Intent getIntent(Context context, int type, String value) {
        Intent intent = new Intent(context, ValueEditorActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("value", value);
        return intent;
    }

    public void updateValue() {

    }

    @Override
    public void onClick(View v) {
        mUserNameEdt.setText(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_change_name, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, UserInfoActivity.class);
        String value = mUserNameEdt.getText().toString();
        if (item.getItemId() == R.id.change_name) {
            switch (type) {
                case 1:
                    if (value.length() < 11 || !Utils.isMobileNum(value)) {
                        Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    } else {
                        intent.putExtra("value", value);
                        setResult(1, intent);
                        finish();
                    }
                    break;
                case 0:
                    if (!TextUtils.isEmpty(value)) {
                        intent.putExtra("value", value);
                        setResult(1, intent);
                        finish();
                    } else {
                        Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onRetry() {
//        super.onRetry();
//        setResult(1, getIntentResult());
//    }


//    public Intent getIntentResult() {
//        Intent intent = new Intent(this, UserInfoActivity.class);
//        String value = mUserNameEdt.getText().toString();
//        intent.putExtra("value", value);
//        return intent;
//    }
}
