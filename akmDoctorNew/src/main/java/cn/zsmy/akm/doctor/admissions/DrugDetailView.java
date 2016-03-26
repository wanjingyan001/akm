package cn.zsmy.akm.doctor.admissions;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import cn.zsmy.akm.doctor.admissions.bean.DrugDetail;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.MoreTextView;
import cn.zsmy.doctor.R;

/**
 * Created by Administrator on 2016/2/15.
 */
public class DrugDetailView extends BaseTitleActivity {

    private ImageView mDurgImg;//药品图片
    private TextView mDurgName;//药品名称
    private TextView mSpecification;//药品规格
    private MoreTextView moreText;
    private TextView textView;//药品描述
    private DrugDetail drugDetail;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_drug_detail_view);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mDurgImg = ((ImageView) findViewById(R.id.drug_img));
        mDurgName = ((TextView) findViewById(R.id.drug_name));
        mSpecification = ((TextView) findViewById(R.id.specification));
        moreText = ((MoreTextView) findViewById(R.id.more_text_view));
    }

    @Override
    protected void initializeData() {
        setTitle("药品详情");
        Intent intent = getIntent();
        String durgId = intent.getStringExtra("durgId");
        getDrugDetail(durgId);

    }

    //获取药品详情
    private void getDrugDetail(String durgId) {
        Request request = new Request(UrlHelper.DRUG_DETAIL + durgId, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DrugDetail drugDetail = JsonParser.deserializeFromJson(result, DrugDetail.class);
                DrugDetail.DataEntity dataEntity = drugDetail.getData();
                mDurgName.setText(dataEntity.getName());
                moreText.setText(dataEntity.getCureMainly() + "\n" + dataEntity.getHeedItems());
                mSpecification.setText(dataEntity.getStandard());

            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }
}
