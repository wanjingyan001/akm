package cn.zsmy.akm.doctor.messageCenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * 留言中心
 * Created by wanjingyan
 * on 2015/12/16 16:23.
 */
public class FeedbackActivity extends BaseTitleActivity implements View.OnClickListener {
    private EditText mUserFeedEdt;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_feed_back);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        MyApplication.getInstance().addActivity(this);
        mUserFeedEdt = ((EditText) findViewById(R.id.user_feed));
        findViewById(R.id.commit_user_feed).setOnClickListener(this);
        mUserFeedEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (mUserFeedEdt.getText().toString().equals(getText(R.string.user_feed_content))) {
                        mUserFeedEdt.setText(null);
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.feed_back));
    }


    public static Intent getIntent(Context context) {
        return new Intent(context, FeedbackActivity.class);
    }

    @Override
    public void onClick(View v) {
        String value = mUserFeedEdt.getText().toString();
        if (TextUtils.isEmpty(value) || value.equals(getText(R.string.user_feed_content))) {
            Toast.makeText(this, "请填写您的评价", Toast.LENGTH_SHORT).show();
        } else {
            Request request = new Request(UrlHelper.FEED_BACK, Request.RequestMethod.POST, this);
            request.put("content", value);
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    Log.d("TAG", result);
                    Toast.makeText(FeedbackActivity.this, "感谢使用，我们的客服将尽快为您处理。", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        }
    }
}
