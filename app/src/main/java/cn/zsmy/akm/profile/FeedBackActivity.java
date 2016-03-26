package cn.zsmy.akm.profile;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * 意见反馈
 * Created by wanjingyan
 * on 2015/12/10 12:01.
 */
public class FeedBackActivity extends BaseTitleActivity implements View.OnClickListener {
    private EditText mUserFeedEdt;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_feed_back);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
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

    public static Intent newIntent(Context context) {
        return new Intent(context, FeedBackActivity.class);
    }

    @Override
    public void onClick(View v) {
        String str = mUserFeedEdt.getText().toString();
        if (TextUtils.isEmpty(str) || str.equals(getText(R.string.user_feed_content))) {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Request request = new Request(UrlHelpper.FEED_BACK, Request.RequestMethod.POST, this);
            request.put("content", str);
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    Toast.makeText(FeedBackActivity.this, "感谢使用，我们的客服将尽快为您处理。", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        }
    }
}
