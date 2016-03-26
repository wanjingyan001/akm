package cn.zsmy.akm.doctor.admissions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

import cn.zsmy.akm.doctor.admissions.bean.DrugDetail;
import cn.zsmy.akm.doctor.admissions.bean.DrugSuggest;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.MoreTextView;
import cn.zsmy.doctor.R;

/**
 * Created by qinwei on 2015/12/18 14:17
 */
public class DrugDetailActivity extends BaseTitleActivity implements View.OnClickListener {
    private ImageView mDrugImg;
    private TextView mDrugName;
    private TextView mDrugSpecification;
    private TextView mIssugDrugCount;//开具数量
    private TextView mSuggestNumber;//建议数量
    private MoreTextView mDrugDetailContent;
    private View mShowMore;
    private DrugDetail.DataEntity data;
    private SharedPreferences drug_info;
    private RadioGroup instructions;
    private RadioButton oral;
    private RadioButton externalUse;
    private String cacheName;
    private String from;
    private ImageButton issugLess;
    private ImageButton less;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_drug_detail);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mDrugImg = ((ImageView) findViewById(R.id.drug_img));
        mDrugName = ((TextView) findViewById(R.id.drug_name));
        mDrugSpecification = ((TextView) findViewById(R.id.drug_specification));
        mIssugDrugCount = ((TextView) findViewById(R.id.issug_drug_count));
        mSuggestNumber = ((TextView) findViewById(R.id.drug_count));
        mDrugDetailContent = ((MoreTextView) findViewById(R.id.drug_detail_content));
        oral = ((RadioButton) findViewById(R.id.Oral));
        externalUse = ((RadioButton) findViewById(R.id.external_use));
        findViewById(R.id.drug_add_sub).setOnClickListener(this);
        mDrugDetailContent.setOnClickListener(this);
        mShowMore = findViewById(R.id.show_more_layout);
        mShowMore.setOnClickListener(this);
        issugLess = ((ImageButton) findViewById(R.id.issug_less));
        issugLess.setOnClickListener(this);
        findViewById(R.id.issug_add).setOnClickListener(this);
        less = ((ImageButton) findViewById(R.id.less));
        less.setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.drug_detail).setOnClickListener(this);
    }

    @Override
    protected void initializeData() {
        setTitle("药品详情");
        Intent intent = getIntent();
        String drugId = intent.getStringExtra("drugId");
        cacheName = intent.getStringExtra("cacheName");
        from = intent.getStringExtra("from");
        getDrugDetail(drugId);
    }

    private void getDrugDetail(String drugId) {
        Request request = new Request(UrlHelper.DRUG_DETAIL + drugId, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DrugDetail drugDetail = JsonParser.deserializeFromJson(result, DrugDetail.class);
                data = drugDetail.getData();
                if (data.getZUsage().equals("口服")) {
                    oral.setChecked(true);
                } else {
                    externalUse.setChecked(true);
                }
                mDrugName.setText(data.getName());
                mDrugSpecification.setText(data.getStandard());
                StringBuffer buffer = new StringBuffer();
                mDrugDetailContent.setText(buffer.append(data.getName() + "\n" + data.getCommonName() + "\n" + data.getCureMainly() + "\n" + data.getDosage()));
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public void onClick(View v) {
        String iDrugCount = mIssugDrugCount.getText().toString();
        String sDrugCount = mSuggestNumber.getText().toString();
        Integer integer = Integer.valueOf(iDrugCount);
        Integer integer1 = Integer.valueOf(sDrugCount);
        switch (v.getId()) {
            case R.id.issug_add:
                if (Integer.valueOf(iDrugCount) < 100) {
                    mIssugDrugCount.setText(String.valueOf(integer + 1));
                    issugLess.setImageDrawable(getResources().getDrawable(R.drawable.icon_less_drug_true));
                }
                break;
            case R.id.issug_less:
                if (Integer.valueOf(iDrugCount) > 1) {
                    issugLess.setImageDrawable(getResources().getDrawable(R.drawable.icon_less_drug_true));
                    mIssugDrugCount.setText(String.valueOf(integer - 1));
                } else {
                    issugLess.setImageDrawable(getResources().getDrawable(R.drawable.icon_less_drug));
                }
                break;
            case R.id.less:
                if (Integer.valueOf(sDrugCount) > 0) {
                    mSuggestNumber.setText(String.valueOf(integer1 - 1));
                    less.setImageDrawable(getResources().getDrawable(R.drawable.icon_less_drug_true));
                } else less.setImageDrawable(getResources().getDrawable(R.drawable.icon_less_drug));
                break;
            case R.id.add:
                if (Integer.valueOf(sDrugCount) < 100) {
                    less.setImageDrawable(getResources().getDrawable(R.drawable.icon_less_drug_true));
                    mSuggestNumber.setText(String.valueOf(integer1 + 1));
                }
                break;
            case R.id.drug_detail_content:
                break;
            case R.id.show_more_layout:
                break;
            case R.id.drug_add_sub:
                if (oral.isChecked() || externalUse.isChecked()) {
                    int issueNumber = Integer.valueOf(mIssugDrugCount.getText().toString());
                    int usageAmount = Integer.valueOf(mSuggestNumber.getText().toString());
                    String usageMethod = "";
                    if (oral.isChecked()) {
                        usageMethod = "内服";
                    } else if (externalUse.isChecked()) {
                        usageMethod = "外用";
                    }
                    if (data != null) {
                        Iterator<DrugSuggest> iterator = DoctorSuggestActivity.maps.iterator();
                        boolean b = false;
                        while (iterator.hasNext()) {
                            DrugSuggest next = iterator.next();
                            if (next.getDrugId().equals(data.getId())) {
                                b = true;
                            }
                        }
                        if (!b) {
                            saveDrugInfo(data.getName(), data.getId(), usageAmount, issueNumber, usageMethod);
//                            submitDrug(cacheName, data.getName(), data.getId(), usageAmount, issueNumber, usageMethod);
                        } else {
                            Toast.makeText(this, "已选择该药品，无法重复选择", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(this, "请选择使用方法", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_doctor_suggest, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.to_doctor_suggest) {
            startActivity(DoctorSuggestActivity.getIntent(this, null));
            finish();
        } else if (itemId == android.R.id.home) {
            back();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void back() {
        switch (from) {
            case "select":
                Intent intent = new Intent(this, SelectDrugActivity.class);
                intent.putExtra("cacheName", cacheName);
                startActivity(intent);
                break;
            case "search":
                Intent intent1 = new Intent(this, SearchHospitalActivity.class);
                intent1.putExtra("TYPE", Constants.SREACH_DRUG_VALUES);
                intent1.putExtra("cacheName", cacheName);
                startActivity(intent1);
                break;
            case "recommend":
                finish();
                break;
        }
    }

    private void saveDrugInfo(String drugName, String drugId, int drugNum, int issueNumber, String usageMethod) {
        DrugSuggest drugSuggest = new DrugSuggest(drugId, drugName, issueNumber, drugNum, usageMethod);
        DoctorSuggestActivity.maps.add(drugSuggest);
        drug_info = getSharedPreferences(cacheName, MODE_PRIVATE);
        SharedPreferences.Editor edit = drug_info.edit();
        edit.putString("drugId", drugId);//药品id
        edit.putString("drugName", drugName);//药品名称
        edit.putInt("drugNum", drugNum);//使用数量
        edit.putInt("issueNumber", issueNumber);//开具数量
        edit.putString("usageMethod", usageMethod);//使用方法
        edit.commit();
        Intent intent = getIntent();
        intent.setClass(DrugDetailActivity.this, DoctorSuggestActivity.class);
        startActivity(intent);
        finish();
    }


    private void submitDrug(String caseId, String drugName, String drugId, int drugNum, int issueNumber, String usageMethod) {

        Request request = new Request(UrlHelper.ADD_CASE_MEDICINE, Request.RequestMethod.POST, this);
        request.put("caseId", caseId);
        request.put("medicineId", drugId);
        request.put("medicineName", drugName);
        request.put("issueNumber", String.valueOf(issueNumber));
        request.put("usageAmount", String.valueOf(drugNum));
        request.put("usageMethod", usageMethod);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", "用药建议：" + result);

            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }
}
