package cn.zsmy.akm.doctor.admissions;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import cn.zsmy.akm.doctor.admissions.bean.ExamineDetail;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.MoreTextView;
import cn.zsmy.doctor.R;


/**
 * 检验详情
 * Created by Administrator on 2016/2/15.
 */
public class ExamineDetailActivity extends BaseTitleActivity {
    private MoreTextView mExamineDetail;
    private TextView Precautions;

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_examine_detail);
    }


    @Override
    protected void initializeView() {
        super.initializeView();
        mExamineDetail = ((MoreTextView) findViewById(R.id.more_text_view));
        Precautions = ((TextView) findViewById(R.id.not_content));
    }


    public static Intent getInent(Context context, String testItemId) {
        Intent intent = new Intent(context, ExamineDetailActivity.class);
        intent.putExtra("TESTITEMID", testItemId);
        return intent;
    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        String testItemId = getIntent().getStringExtra("TESTITEMID");
        getData(testItemId);
    }

    private void getData(String testItemId) {
        Request request = new Request(UrlHelper.INSPECT_DETAIL + "?id=" + testItemId, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.i("TAG", result);
                ExamineDetail examineDetail = JsonParser.deserializeFromJson(result, ExamineDetail.class);
                mExamineDetail.setText(examineDetail.getData().getCode());
                Precautions.setText(examineDetail.getData().getHeedItem());
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }
}
