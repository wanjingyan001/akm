package cn.zsmy.akm.doctor.admissions;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.doctor.admissions.adapter.DrugListAdapter;
import cn.zsmy.akm.doctor.admissions.adapter.TestListAdapter;
import cn.zsmy.akm.doctor.admissions.bean.DoctorSuggest;
import cn.zsmy.akm.doctor.admissions.bean.DrugSuggest;
import cn.zsmy.akm.doctor.admissions.bean.TestSuggest;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.DateUtils;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;


/**
 * 就医建议
 * Created by Administrator on 2015/11/26.
 */
public class DoctorsRecommend extends BaseTitleActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private RelativeLayout check_item_rela;
    private String caseId;
    private ListView drugList;
    private List<DrugSuggest> drugs;
    private List<TestSuggest> tests;
    private DrugListAdapter drugListAdapter;
    private TestListAdapter testAdapter;
    private TextView inquiryInfo;
    private TextView resultContent;
    private TextView suggest;
    private TextView doctor;
    private ListView testList;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_interrogation_doctorsrecommend);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        check_item_rela = (RelativeLayout) findViewById(R.id.check_item_relaID);
        drugList = ((ListView) findViewById(R.id.drug_list));
        inquiryInfo = ((TextView) findViewById(R.id.inquiry_info));
        resultContent = ((TextView) findViewById(R.id.result));
        suggest = ((TextView) findViewById(R.id.suggest));
        doctor = ((TextView) findViewById(R.id.doctor_suggest));
        testList = ((ListView) findViewById(R.id.test_list));
        drugs = new ArrayList<>();
        tests = new ArrayList<>();
    }

    @Override
    protected void initializeData() {
        setTitle("就医建议");
        Intent intent = getIntent();
        caseId = intent.getStringExtra("CASEID");
        getSuggest();
        drugList.setOnItemClickListener(this);
        testList.setOnItemClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    private void getSuggest() {
        String url = UrlHelper.CURRENT_CASE + "?caseId=" + caseId;
        Log.d("TAG", url);
        Request request = new Request(url, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DoctorSuggest doctorSuggest = JsonParser.deserializeFromJson(result, DoctorSuggest.class);
                DoctorSuggest.DataEntity dataEntity = doctorSuggest.getData();
                SetPatientInfo(dataEntity);
                DoctorSuggest.DataEntity.CaseAdvicesEntity advicesEntity = dataEntity.getCaseAdvices().get(0);
                resultContent.setText(advicesEntity.getAeger());
                String content = advicesEntity.getContent();
                if (!TextUtils.isEmpty(content)) {
                    doctor.setText(content);
                }
                if (advicesEntity.getIfInquiry().equals("0")) {
                    suggest.setText(dataEntity.getCaseAdvices().get(0).getHospitalName());
                } else {
                    suggest.setText(dataEntity.getDoctor().getHospital() + "——" + dataEntity.getDoctor().getName());
                }
                setDrugList(dataEntity);
                setTestList(dataEntity);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    private void setTestList(DoctorSuggest.DataEntity dataEntity) {
        List<DoctorSuggest.DataEntity.CaseChecksEntity> caseChecks = dataEntity.getCaseChecks();
        if (caseChecks.size() != 0) {
            for (DoctorSuggest.DataEntity.CaseChecksEntity check : caseChecks) {
                tests.add(new TestSuggest(check.getInspectionId(), check.getInspection()));
            }
            testAdapter = new TestListAdapter(DoctorsRecommend.this, tests);
            testAdapter.notifyDataSetChanged();
            testList.setAdapter(testAdapter);
            setPullLvHeight(testList);
        }
    }

    private void setDrugList(DoctorSuggest.DataEntity dataEntity) {
        List<DoctorSuggest.DataEntity.CaseMedicinesEntity> caseMedicines = dataEntity.getCaseMedicines();
        if (caseMedicines.size() > 0) {
            for (DoctorSuggest.DataEntity.CaseMedicinesEntity drug : caseMedicines) {
                drugs.add(new DrugSuggest(drug.getMedicineId(), drug.getMedicineName(), drug.getIssueNumber(), drug.getUsageAmount(), drug.getUsegeMethod()));
            }
            drugListAdapter = new DrugListAdapter(this, drugs);
            drugListAdapter.notifyDataSetChanged();
            drugList.setAdapter(drugListAdapter);
            setPullLvHeight(drugList);
        }
    }


    private void SetPatientInfo(DoctorSuggest.DataEntity dataEntity) {
        String name = dataEntity.getPatient().getName();
        String gender = dataEntity.getPatient().getGender();
        String age = DateUtils.getAge(dataEntity.getPatient().getBirthday());
        inquiryInfo.setText(name + "  \t" + gender + "  \t" + age + "岁");
    }


    /**
     * 动态改变ListView高度
     *
     * @param pull
     */
    private void setPullLvHeight(ListView pull) {
        ListAdapter listAdapter = pull.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, pull);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = pull.getLayoutParams();
        params.height = totalHeight
                + (pull.getDividerHeight() * (listAdapter.getCount() - 1));
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        pull.setLayoutParams(params);
    }

    public static Intent getIntent(Context context, String caseId) {
        Intent intent = new Intent(context, DoctorsRecommend.class);
        intent.putExtra("CASEID", caseId);
        return intent;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.check_item_relaID:

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.drug_list) {
            DrugSuggest drugSuggest = drugs.get(position);
            Intent intent = new Intent(this, DrugDetailView.class);
            intent.putExtra("durgId", drugSuggest.getDrugId());
            startActivity(intent);
        } else if (parent.getId() == R.id.test_list) {
            TestSuggest testSuggest = tests.get(position);
            Intent inent = ExamineDetailActivity.getInent(this, testSuggest.getTestId());
            startActivity(inent);
        }
    }
}
