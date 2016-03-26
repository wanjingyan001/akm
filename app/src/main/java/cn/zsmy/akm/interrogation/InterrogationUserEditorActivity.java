package cn.zsmy.akm.interrogation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePopupWindow;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.wei.library.widget.ProgressDialogUtils;
import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.Patient;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.pop.PhotoPopupWindow;

/**
 * 问诊人信息编辑(add or update)
 * Created by qinwei on 2015/11/24 13:10
 */
public class InterrogationUserEditorActivity extends BaseTitleActivity implements View.OnClickListener, PhotoPopupWindow.onPopupWindowItemClickListener, TimePopupWindow.OnTimeSelectListener {
    private RelativeLayout name_rela, sex_rela, brithday_rela;
    private TextView name_text, sex_text, brithday_text;
    private Button submit;
    private final int RESULT_CDDE = 50;
    private PhotoPopupWindow pop;
    private TimePopupWindow timePicker;
    private View view;
    private int type;
    private Patient.DataEntity dataEntity;

    public static Intent getIntent(Context context, Patient.DataEntity patient, int type) {
        Intent intent = new Intent(context, InterrogationUserEditorActivity.class);
        Bundle bundle = new Bundle();
        if (type == 0) {
            bundle.putInt(Constants.KEY_MARK_TYPE, type);
        } else {
            bundle.putInt(Constants.KEY_MARK_TYPE, type);
            bundle.putSerializable(Constants.KEY_PATIENT, patient);
        }
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void setContentView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_interrogation_user_editor, null);
        MyApplication.getInstance().addActivity(this);
        setContentView(view);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        name_rela = (RelativeLayout) findViewById(R.id.chenghu_rela);
        sex_rela = (RelativeLayout) findViewById(R.id.gender_rela);
        brithday_rela = (RelativeLayout) findViewById(R.id.brithday_rela);
        name_text = (TextView) findViewById(R.id.tv_chenghu);
        sex_text = (TextView) findViewById(R.id.tv_gender);
        brithday_text = (TextView) findViewById(R.id.tv_brithday);
        submit = (Button) findViewById(R.id.mDiseaseCommitBtn);
        submit.setOnClickListener(this);
        name_rela.setOnClickListener(this);
        sex_rela.setOnClickListener(this);
        brithday_rela.setOnClickListener(this);


    }

    @Override
    protected void initializeData() {
        type = getIntent().getExtras().getInt(Constants.KEY_MARK_TYPE);
        dataEntity = (Patient.DataEntity) getIntent().getExtras().getSerializable(Constants.KEY_PATIENT);
        if (type == 0) {
            setTitle("添加问诊人");
        } else {
            setTitle("修改问诊人");
            if (null != dataEntity) {
                name_text.setText(dataEntity.getName());
                String sex = dataEntity.getGender();
                if (!TextUtils.isEmpty(sex)) {
                    if (sex.equals("1")) {
                        sex = "男";
                    } else if (sex.equals("0")) {
                        sex = "女";
                    }
                }
                sex_text.setText(sex);
                brithday_text.setText(DateUtils.getDateToString(dataEntity.getBirthday(), 0));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chenghu_rela:
                setName_Rela();
                break;
            case R.id.gender_rela:
                if (timePicker != null && timePicker.isShowing()) {
                    timePicker.dismiss();
                } else {
                    setSexText();
                }
                break;
            case R.id.brithday_rela:
                if (pop != null && pop.isShow()) {
                    pop.dismiss();
                } else {
                    timePicker = new TimePopupWindow(this, TimePopupWindow.Type.YEAR_MONTH_DAY);
                    timePicker.setRange(1900, Integer.valueOf(DateUtils.getDateToString(DateUtils.getCurrentTime(), -1)));
                    timePicker.setTime(new Date());
                    timePicker.showAtLocation(view, Gravity.BOTTOM, 0, 0, new Date());
                    timePicker.setOnTimeSelectListener(this);
                }
                break;
            case R.id.mDiseaseCommitBtn:
                String name = name_text.getText().toString();
                String sex = sex_text.getText().toString();
                String brithday = brithday_text.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sex) && !TextUtils.isEmpty(brithday)
                        && !name.equals("请输入") && !sex.equals("请选择") && !brithday.equals("请选择")) {
                    String url = null;
                    if (is_submit()) {
                        if (type == 0) {
                            url = UrlHelpper.ADD_PERSON_URL;

                        } else {
                            url = UrlHelpper.EDIT_PERSON_URL;
                        }
                        loadUpDataPersonInfo(url);
                    }
                } else {
                    Toast.makeText(this, "请填写完整的信息", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_CDDE:
                    String name = data.getAction();
                    if (!TextUtils.isEmpty(name)) {
                        name_text.setText(name);
                    }
                    break;
                default:
                    break;
            }
        }

    }

    /***
     * 跳转到名字编辑界面
     **/
    public void setName_Rela() {
        Intent intent = new Intent(this, AddNewPersonActivity.class);
        startActivityForResult(intent, RESULT_CDDE);
    }

    private void setSexText() {
        pop = new PhotoPopupWindow(this);
        pop.setItemText("男", "女");
        pop.setItemClickListener(this);
        pop.showPopupWindow();
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                sex_text.setText("男");
                break;
            case 1:
                sex_text.setText("女");
                break;
        }
        pop.dismiss();
    }

    //时间选择后回调
    @Override
    public void onTimeSelect(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        long birthday = DateUtils.getStringToDate(dateFormat.format(date));
        long curTime = DateUtils.getCurrentTime();
        if (birthday > curTime) {
            brithday_text.setText(DateUtils.getCurrentDate());
        } else {
            brithday_text.setText(dateFormat.format(date));
        }

    }

    @Override
    protected boolean isCanFinish() {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.putExtras(getBundle());
        setResult(RESULT_OK, intent);
        return true;
    }

    public void setIntentActivtyResult() {
        Intent intent = new Intent();
        intent.putExtras(getBundle());
        setResult(RESULT_OK, intent);
        finish();
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("SEX", sex_text.getText().toString().trim());
        bundle.putString("NAME", name_text.getText().toString().trim());
        String trim = brithday_text.getText().toString().trim();
        bundle.putString("AGE", changeDateType(trim));
        return bundle;
    }

    public void loadUpDataPersonInfo(String url) {
        ProgressDialogUtils.showProgressDialog(this, "保存中");
        Request request = new Request(url, Request.RequestMethod.POST, this);
        request.put("inquirerID", MyApplication.getProfile().getUserId());
        request.put("name", name_text.getText().toString());
        request.put("gender", sex_text.getText().toString());
        request.put("birthday", changeDateType(brithday_text.getText().toString()));
        if (null != dataEntity) {
            request.put("caseNum", dataEntity.getCaseNum());
            request.put("id", dataEntity.getId());
        }
//            object.put("accessToken",MyApplication.getProfile().getAccessToken());
//            request.addHeader("Content-Type", "application/json;charset=UTF-8");
//            request.addHeader("Cookie", "JSESSIONID=" +MyApplication.getProfile().getAccessToken());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                ProgressDialogUtils.closeProgressDialog();
                finish();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    /***
     * 判断是否提交并提醒
     */
    public boolean is_submit() {
        String name = name_text.getText().toString();
        String sex = sex_text.getText().toString();
        String brithday = brithday_text.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_LONG).show();
            return false;
        } else if (TextUtils.isEmpty(sex)) {
            Toast.makeText(this, "请输入性别", Toast.LENGTH_LONG).show();
            return false;
        } else if (TextUtils.isEmpty(brithday)) {
            Toast.makeText(this, "请输入出生日期", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private String changeDateType(String date) {
        String replace = date.replace("年", "-").replace("月", "-").replace("日", "");
        return replace;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            return true;
        }
        return true;
    }
}
