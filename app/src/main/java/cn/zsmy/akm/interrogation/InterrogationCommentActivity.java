package cn.zsmy.akm.interrogation;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * Created by qinwei on 2015/11/24 13:13
 */
public class InterrogationCommentActivity extends BaseTitleActivity implements View.OnClickListener {
    private LinearLayout mEvaluationStars;
    private EditText mEvaluationEdt;
    private RatingBar mRating;
    private String caseID;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_doctor_comment);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mRating = ((RatingBar) findViewById(R.id.ratingbar));
        mEvaluationEdt = ((EditText) findViewById(R.id.evaluation_content));
        findViewById(R.id.submit_comment).setOnClickListener(this);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, InterrogationCommentActivity.class);
    }

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.comment_for_doctor));
        Intent intent = getIntent();
        caseID = intent.getStringExtra("caseID");
    }

    private void submitEvaluate() {
        int value = (int) (mRating.getRating() * 10);
        String string = mEvaluationEdt.getText().toString();
        if (value != 0 && !TextUtils.isEmpty(string)) {
            Request request = new Request(UrlHelpper.SUBMIT_EVALUATE, Request.RequestMethod.POST, this);
            request.put("id", caseID);
            request.put("evaluateScore", String.valueOf(value));
            request.put("evaluate", string);
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    setResult(2);
                    finish();
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        } else {
            Toast.makeText(this, "请对本次问诊进行评价", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        submitEvaluate();
    }
}
