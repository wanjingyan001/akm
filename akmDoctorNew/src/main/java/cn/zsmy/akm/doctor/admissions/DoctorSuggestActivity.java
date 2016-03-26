package cn.zsmy.akm.doctor.admissions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.wei.library.widget.ProgressDialogUtils;
import cn.zsmy.akm.doctor.admissions.adapter.MedicationAdapter;
import cn.zsmy.akm.doctor.admissions.adapter.TestAdapter;
import cn.zsmy.akm.doctor.admissions.bean.DoctorSuggest;
import cn.zsmy.akm.doctor.admissions.bean.DrugSuggest;
import cn.zsmy.akm.doctor.admissions.bean.TestSuggest;
import cn.zsmy.akm.doctor.admissions.view.DoctorSuggestView;
import cn.zsmy.akm.doctor.admissions.view.MaxLengthWatcher;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.DocSugScroll;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/17 9:45
 */
public class DoctorSuggestActivity extends BaseTitleActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private LinearLayout doctor_lin;
    private View drug_next, check_next;
    private TextView testItems;
    private ImageView testImg;
    private TextView hospitalName;
    private DoctorSuggestView doctorSuggestView;
    private Button submitBtn;
    private EditText resultContent;
    private EditText contactDoctorSpeak;
    private String caseId;
    private CheckBox designationMe;
    private CheckBox designationHospital;
    private String result;
    private SharedPreferences suggestCache;
    private Map<String, String> durgEntity;
    private String chooseHosName;
    private String testItem;
    private String looksuggest;
    private SwipeMenuListView medicationList;
    public static List<DrugSuggest> maps;
    public static List<TestSuggest> tests;
    private MedicationAdapter medicationAdapter;
    private String testItemId;
    private String testName;
    private SwipeMenuListView testList;//选中的检验项目列表
    private TestAdapter testAdapter;
    private DocSugScroll docScroll;
    private String hospitalId;
    private ArrayList<String> drugIds;
    private ArrayList<String> drugNames;
    private ArrayList<Integer> issueNumbers;
    private ArrayList<Integer> usageAmount;
    private ArrayList<String> usageMethod;
    private ArrayList<String> testIds;
    private ArrayList<String> testNames;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_doctor_suggest);
        MyApplication.getInstance().addActivity(this);

    }

    public static Intent getIntent(Context context, String caseId) {
        Intent intent = new Intent(context, DoctorSuggestActivity.class);
        intent.putExtra("caseId", caseId);
        return intent;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initializeView() {
        super.initializeView();
        doctor_lin = (LinearLayout) findViewById(R.id.Doctor_Suggest_content);
        drug_next = findViewById(R.id.select_drug_next);
        check_next = findViewById(R.id.select_check_next);
        resultContent = ((EditText) findViewById(R.id.result_content));
        contactDoctorSpeak = ((EditText) findViewById(R.id.contact_doctor_speak));
        docScroll = ((DocSugScroll) findViewById(R.id.doc_scroll));
        resultContent.addTextChangedListener(new MaxLengthWatcher(150, resultContent, this));
        initList();
        submitBtn = ((Button) findViewById(R.id.contact_submit));
        testItems = ((TextView) check_next.findViewById(R.id.drug_title));
        testImg = ((ImageView) check_next.findViewById(R.id.drug_pic));
        testItems.setText("检验");
        testImg.setBackground(null);
        testImg.setBackground(getResources().getDrawable(R.mipmap.ic_test));
        submitBtn.setOnClickListener(this);
        drug_next.setOnClickListener(this);
        check_next.setOnClickListener(this);
        addDesignationView();
    }

    //对药品列表和检验项目列表实例化
    private void initList() {
        medicationList = ((SwipeMenuListView) findViewById(R.id.medication_list));
        testList = ((SwipeMenuListView) findViewById(R.id.test_list));
        maps = new ArrayList<>();
        tests = new ArrayList<>();
        medicationAdapter = new MedicationAdapter(this, maps);
        testAdapter = new TestAdapter(this, tests);
        medicationList.setAdapter(medicationAdapter);
        testList.setAdapter(testAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu swipeMenu) {
                creatItem(swipeMenu);
            }
        };
        medicationList.setMenuCreator(creator);
        testList.setMenuCreator(creator);
        medicationList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                maps.remove(position);
                medicationAdapter.notifyDataSetChanged();
                setPullLvHeight(medicationList);
                return false;
            }
        });
        testList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                tests.remove(position);
                testAdapter.notifyDataSetChanged();
                setPullLvHeight(testList);
                return false;
            }
        });
    }

    //创建侧滑删除的菜单项
    private void creatItem(SwipeMenu swipeMenu) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                0x3F, 0x25)));
        deleteItem.setTitle("删除");
        deleteItem.setTitleSize(16);
        deleteItem.setTitleColor(Color.WHITE);
        deleteItem.setWidth(widthPixels / 4);
        swipeMenu.addMenuItem(deleteItem);
    }

    /**
     * 指定我/医院问诊View
     */
    private void addDesignationView() {
        doctorSuggestView = new DoctorSuggestView(this);
        designationMe = ((CheckBox) doctorSuggestView.findViewById(R.id.doctor_suggest_select_doctor_status));
        designationHospital = ((CheckBox) doctorSuggestView.findViewById(R.id.doctor_suggest_select_hospital_status));
        hospitalName = ((TextView) doctorSuggestView.findViewById(R.id.hospital_name));
        designationMe.setOnCheckedChangeListener(this);
        designationHospital.setOnCheckedChangeListener(this);
        doctorSuggestView.setClickable(false);
        doctor_lin.addView(doctorSuggestView);
        if (designationMe.isChecked()){
            designationMe.setChecked(false);
        }
    }


    @Override
    protected void initializeData() {
        setTitle("就医建议");
        Intent intent = getIntent();
        caseId = intent.getStringExtra("caseId");
        doctorSuggestView.setCacheName(caseId);
        if (!designationMe.isChecked() && !designationHospital.isChecked()) {
            designationMe.setChecked(true);
        }
        getDocSuggest(caseId);

    }

    //查看病例详情（就医建议）
    private void getDocSuggest(String caseId) {
        Request request = new Request(UrlHelper.CURRENT_CASE + "?caseId=" + caseId, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DoctorSuggest doctorSuggest = JsonParser.deserializeFromJson(result, DoctorSuggest.class);
                long veriftyTime = doctorSuggest.getData().getVeriftyTime();
                Log.d("TAG", veriftyTime + "=========");
                if (veriftyTime != 0) {
                    setNotEditable();
                    DoctorSuggest.DataEntity entity = doctorSuggest.getData();
                    DoctorSuggest.DataEntity.CaseAdvicesEntity advicesEntity = entity.getCaseAdvices().get(0);
                    resultContent.setText(advicesEntity.getAeger());
                    contactDoctorSpeak.setText(advicesEntity.getContent());
                    String ifInquiry = advicesEntity.getIfInquiry();
                    if (ifInquiry.equals("0")) {
                        designationMe.setChecked(false);
                        designationHospital.setChecked(true);
                        hospitalName.setText(advicesEntity.getHospitalName());
                    } else {
                        designationHospital.setChecked(false);
                        designationMe.setChecked(true);
                        hospitalName.setVisibility(View.GONE);
                    }

                    List<DoctorSuggest.DataEntity.CaseChecksEntity> checks = entity.getCaseChecks();
                    List<DoctorSuggest.DataEntity.CaseMedicinesEntity> caseMedicines = entity.getCaseMedicines();
                    testAdapter.setName(checks.get(0).getInspection());
                    maps.clear();
                    for (DoctorSuggest.DataEntity.CaseMedicinesEntity medicine : caseMedicines) {
                        DrugSuggest suggest = new DrugSuggest(medicine.getMedicineId(), medicine.getMedicineName(), medicine.getIssueNumber(),
                                medicine.getUsageAmount(), medicine.getUsegeMethod());
                        maps.add(suggest);
                    }
                    medicationAdapter.notifyDataSetChanged();
                    setPullLvHeight(medicationList);
                    tests.clear();
                    for (DoctorSuggest.DataEntity.CaseChecksEntity check : checks) {
                        TestSuggest suggest = new TestSuggest(check.getInspectionId(), check.getInspection());
                        tests.add(suggest);
                    }
                    testAdapter.notifyDataSetChanged();
                    setPullLvHeight(testList);

                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    /**
     * 设置界面不可编辑
     */
    private void setNotEditable() {
        submitBtn.setEnabled(false);
        drug_next.setEnabled(false);
        check_next.setEnabled(false);
        designationMe.setEnabled(false);
        resultContent.setEnabled(false);
        contactDoctorSpeak.setEnabled(false);
        designationHospital.setEnabled(false);
        doctorSuggestView.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("cacheName", caseId);
        switch (v.getId()) {
            case R.id.select_drug_next:
                //选择药品
                intent.setClass(this, SelectDrugActivity.class);
                Log.d("TAG", "前往药品选择" + maps.size());
                startActivityForResult(intent, 2);
                break;
            case R.id.select_check_next:
                //选择检验项目
                intent.putExtra("TYPE", Constants.SREACH_TESTITEM_VALUES);
                intent.setClass(this, SelectExamineActivity.class);
                Log.d("TAG", "前往检验项目选择");
                startActivityForResult(intent, 0);
                break;
            case R.id.contact_submit:
                if (!TextUtils.isEmpty(resultContent.getText().toString())) {
                    if (designationHospital.isChecked() && hospitalName.getText().toString().equals("请选择")) {
                        Toast.makeText(this, "请选择医院", Toast.LENGTH_SHORT).show();
                    } else {
                        initSubmitData();
                    }
                } else {
                    Toast.makeText(this, "诊断结果为必填项目", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    private void initSubmitData() {
        drugIds = new ArrayList<>();
        drugNames = new ArrayList<>();
        issueNumbers = new ArrayList<>();
        usageAmount = new ArrayList<>();
        usageMethod = new ArrayList<>();
        testIds = new ArrayList<>();
        testNames = new ArrayList<>();
        for (DrugSuggest drug : maps) {
            drugIds.add(drug.getDrugId());
            drugNames.add(drug.getDrugName());
            issueNumbers.add(drug.getIssueNumber());
            usageAmount.add(drug.getUsageAmount());
            usageMethod.add(drug.getUsageMethod());
        }
        for (TestSuggest test : tests) {
            testIds.add(test.getTestId());
            testNames.add(test.getTestName());
        }
        if (maps.size() == 0) {
            if (tests.size() == 0) {
                submitAdvice();
            } else {
                submitTestItem(0);
            }
        } else {
            submitDrug(0);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        caseId = intent.getStringExtra("caseId");
        Log.i("TAG", caseId + "");
//        result = intent.getStringExtra("result");
        medicationAdapter.notifyDataSetChanged();
        testAdapter.notifyDataSetChanged();
        setPullLvHeight(medicationList);
        setPullLvHeight(testList);
        getDataFromCache();
    }

    private void getDataFromCache() {
        suggestCache = getSharedPreferences(caseId, MODE_PRIVATE);
        chooseHosName = suggestCache.getString("hospitalName", null);
        hospitalId = suggestCache.getString("hospitalId", null);
        if (chooseHosName != null) {
            hospitalName.setText(chooseHosName);
        }
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


    /**
     * 提交就医建议
     */
    private void submitAdvice() {
        Request request = new Request(UrlHelper.ADD_CASE_ADVICE, Request.RequestMethod.POST, this);
        Log.d("TAG", caseId + "," + contactDoctorSpeak.getText().toString() + "," + resultContent.getText().toString());
        request.put("caseId", caseId);
        request.put("content", contactDoctorSpeak.getText().toString());
        request.put("hospitalId", suggestCache.getString("hospitalId", null));
        request.put("hospitalName", hospitalName.getText().toString());
        request.put("ifInquiry", designationMe.isChecked() ? "1" : "0");
        request.put("aeger", resultContent.getText().toString());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", "就医建议：" + result);
                finish();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    /**
     * 添加检验项目
     */
    private void submitTestItem(final int i) {
        Request request = new Request(UrlHelper.ADD_CASE_CHECK, Request.RequestMethod.POST, this);
        request.put("caseId", caseId);
        request.put("inspectionId", testIds.get(i));
        Log.d("TAG", "上传检验项目id" + testIds.get(i));
        request.put("inspection", testNames.get(i));
        Log.d("TAG", "上传检验项目名字" + testNames.get(i));
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", "检验项目：" + result);
                if (i < tests.size() - 1) {
                    submitTestItem(i + 1);
                } else {
                    submitAdvice();
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    /**
     * 添加用药建议
     */
    private void submitDrug(final int i) {
        ProgressDialogUtils.showProgressDialog(this, "提交中");
        Request request = new Request(UrlHelper.ADD_CASE_MEDICINE, Request.RequestMethod.POST, this);
        request.put("caseId", caseId);
        request.put("medicineId", drugIds.get(i));
        request.put("medicineName", drugNames.get(i));
        request.put("issueNumber", String.valueOf(issueNumbers.get(i)));
        request.put("usageAmount", String.valueOf(usageAmount.get(i)));
        request.put("usageMethod", usageMethod.get(i));
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                if (i < maps.size() - 1) {
                    submitDrug(i + 1);
                } else {
                    submitTestItem(0);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TAG", data + "=======" + requestCode + ">>>>>>" + resultCode);
        if (data != null) {
            switch (requestCode) {
                case 0:
                    //获取检验项目
//                    result = data.getStringExtra("result");
//                    testItemId = data.getStringExtra("testItemId");
//                    Log.d("TAG", "检验项目返回");
//                    checkName.setText(result);
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (id == R.id.doctor_suggest_select_doctor_status && isChecked) {
            designationMe.setClickable(false);
            designationHospital.setClickable(true);
            designationHospital.setChecked(false);
            doctorSuggestView.setClickable(false);
            hospitalName.setVisibility(View.GONE);
        } else if (id == R.id.doctor_suggest_select_hospital_status && isChecked) {
            designationHospital.setClickable(false);
            designationMe.setClickable(true);
            designationMe.setChecked(false);
            doctorSuggestView.setClickable(true);
            hospitalName.setVisibility(View.VISIBLE);
            chooseHosName = suggestCache.getString("hospitalName", null);
            if (chooseHosName != null) {
                hospitalName.setText(chooseHosName);
            }
        } else {
            doctorSuggestView.setClickable(false);
            hospitalName.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "DoctorSuggest onDestroy");
        ProgressDialogUtils.closeProgressDialog();
        suggestCache.edit().putString("hospitalName", null).commit();
        maps.clear();
        tests.clear();
    }
}
