package cn.zsmy.akm.interrogation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.InspectDetail;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.row.MoreTextView;

/**
 * 检验详情
 * Created by qinwei on 2015/11/24 13:20
 */
public class InterrogationSuggestActivity extends BaseTitleActivity {
    private LinearLayout mNote;
    private MoreTextView moreText;
    private TextView mNoteContent;
    private InspectDetail.DataEntity data = new InspectDetail.DataEntity();

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_examine_detail);
        MyApplication.getInstance().addActivity(this);
    }


    @Override
    protected void initializeView() {
        super.initializeView();
        moreText = ((MoreTextView) findViewById(R.id.more_text_view));
        mNoteContent = ((TextView) findViewById(R.id.not_content));
    }

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.checking_note));
        String inspectId = getIntent().getStringExtra("inspectId");
        getData(inspectId);
    }

    private void getData(String inspectId) {
        Request request = new Request(UrlHelpper.INSPECT_DETAIL + "?id=" + inspectId, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", result);
                InspectDetail detail = JsonParser.deserializeFromJson(result, InspectDetail.class);
                data = detail.getData();
                moreText.setContent(data.getName());
                mNoteContent.setText(data.getHeedItem());
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    public static Intent getIntent(Context context, String inspectId) {
        Intent intent = new Intent(context, InterrogationSuggestActivity.class);
        intent.putExtra("inspectId", inspectId);
        return intent;
    }


}
