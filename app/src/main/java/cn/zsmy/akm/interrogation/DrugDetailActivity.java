package cn.zsmy.akm.interrogation;

import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.DrugDetail;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.row.MoreTextView;

/**
 * 药品详情
 * Created by qinwei on 2015/11/24 13:19
 */
public class DrugDetailActivity extends BaseTitleActivity {
    private ImageView mDurgImg;//药品图片
    private TextView mDurgName;//药品名称
    private TextView mSpecification;//药品规格
    private MoreTextView moreText;
    private TextView textView;//药品描述
    private DrugDetail drugDetail;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_drug_detail);
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
        setTitle(getText(R.string.drug_detail));
        Intent intent = getIntent();
        String durgId = intent.getStringExtra("durgId");
        getDrugDetail(durgId);

    }

    //获取药品详情
    private void getDrugDetail(String durgId) {
        Request request = new Request(UrlHelpper.DRUG_DETAIL + "?id=" + durgId, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DrugDetail drugDetail = JsonParser.deserializeFromJson(result, DrugDetail.class);
                DrugDetail.DataEntity dataEntity = drugDetail.getData();
                mDurgName.setText(dataEntity.getName());
                if (dataEntity.getPics() != null) {
                    ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + dataEntity.getPics(), mDurgImg);
                }
                if (dataEntity.getComposition() != null) {
                    // TODO: 显示药品详细信息
                    SpannableString string = new SpannableString("注意事项：");
                    ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
                    string.setSpan(span, 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    moreText.setContent(dataEntity.getComposition() + "\n " + string + "\n" + dataEntity.getHeedItems());
                }
                if (dataEntity.getStandard() != null) {
                    mSpecification.setText(String.valueOf(dataEntity.getStandard()));
                } else {
                    mSpecification.setText(null);
                }
                if (dataEntity.getPics() != null) {
                    ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + dataEntity.getPics(), mDurgImg);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }
}
