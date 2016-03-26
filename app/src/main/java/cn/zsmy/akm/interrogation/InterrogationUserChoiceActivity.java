package cn.zsmy.akm.interrogation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.handmark.pulltorefresh.PullToRefreshBase;

import java.util.ArrayList;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.baidu.getLocation;
import cn.zsmy.akm.entity.CreatCase;
import cn.zsmy.akm.entity.Patient;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.Is_Login;
import cn.zsmy.akm.utils.StringUtils;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.pop.PhotoPopupWindow;

/**
 * 问诊患者选择
 * 为谁问诊
 * Created by sanz on 2015/11/24 13:09
 */
public class InterrogationUserChoiceActivity extends BaseTitleListActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener, PhotoPopupWindow.onPopupWindowItemClickListener {
    private Button add_new_patient, submit;
    private int selectedPosition;
    private PhotoPopupWindow pop;
    private String content, caseID, zType, flag, doctorId;
    private ArrayList<String> urlDatas;
    private Patient patient;
    public LocationClient mLocationClient = null;
    private cn.zsmy.akm.baidu.getLocation getLocation;

    public static Intent getIntent(Context context, String content, String caseID, String zType, String flag) {
        Intent intent = new Intent(context, InterrogationUserChoiceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("PATIENT_INFO", content);
        bundle.putString("CASEID", caseID);
        bundle.putString(Constants.KEY_TYPE_ID, zType);
        bundle.putString(Constants.KEY_FLAG_ID, flag);
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent getIntent(Context context, String content, String caseID, String zType, String flag, String doctorId) {
        Intent intent = new Intent(context, InterrogationUserChoiceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("PATIENT_INFO", content);
        bundle.putString("CASEID", caseID);
        bundle.putString(Constants.KEY_TYPE_ID, zType);
        bundle.putString(Constants.KEY_FLAG_ID, flag);
        bundle.putSerializable("DOCTORID", doctorId);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_interrogation_user_choice);
        MyApplication.getInstance().addActivity(this);
        mLocationClient = new LocationClient(getApplicationContext());
        getLocation = new getLocation(mLocationClient);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        add_new_patient = (Button) findViewById(R.id.add_new_patient_button_ID);
        submit = (Button) findViewById(R.id.mDiseaseNextBtn);
        submit.setOnClickListener(this);
        add_new_patient.setOnClickListener(this);
        mPullToRefreshLsv.setOnItemClickListener(this);
        mPullToRefreshLsv.getRefreshableView().setOnItemLongClickListener(this);
        mPullToRefreshLsv.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    protected void initializeData() {
        setTitle("为谁问诊");
        content = getIntent().getExtras().getString("PATIENT_INFO");
        caseID = getIntent().getExtras().getString("CASEID");
        doctorId = getIntent().getExtras().getString("DOCTORID");
        zType = getIntent().getExtras().getString(Constants.KEY_TYPE_ID);
        flag = getIntent().getExtras().getString(Constants.KEY_FLAG_ID);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadInquiryPersonInfo();
    }

    /***
     * 加载问诊人列表
     **/
    public void loadInquiryPersonInfo() {
        if (Is_Login.getLoginStatus(this)) {
            String url = UrlHelpper.INTERROGATION_LIST + "?inquirerId=" + MyApplication.getProfile().getUserId();
            Request request = new Request(url, Request.RequestMethod.GET, this);
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    patient = JsonParser.deserializeFromJson(result, Patient.class);
                    modules.clear();
                    modules.addAll(patient.getData());
                    adapter.notifyDataSetChanged();
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        }
    }

    /**
     * 点击事件
     **/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_new_patient_button_ID:
                add_Pat(Constants.VALUS_ADD_PERSON);
                break;
            case R.id.mDiseaseNextBtn:
                if (modules.size() == 0) {
                    Toast.makeText(InterrogationUserChoiceActivity.this, "请添加问诊人", Toast.LENGTH_LONG).show();
                } else {
                    loadCreatCase();
                }

                break;
            default:
                break;
        }
    }

    private void loadCreatCase() {
        Request request = new Request(UrlHelpper.CEART_CHAT_URL, Request.RequestMethod.POST, this);
        if (patient != null) {
            request.put("patientId", patient.getData().get(selectedPosition).getId());
        }
        request.put("content", content);
        if (getLocation.location != null) {
            request.put("offsetX", String.valueOf(getLocation.location.getLatitude()));
            request.put("offsetY", String.valueOf(getLocation.location.getLongitude()));
            request.put("offset", getLocation.location.getCity());
            Log.d("TAG", "城市：" + getLocation.location.getCity() + "坐标：" + String.valueOf(getLocation.location.getLatitude()) + "," + String.valueOf(getLocation.location.getLongitude()));
        }
        request.put("memo", "");
        request.put("creator", "");
        request.put("id", caseID);
        if (!TextUtils.isEmpty(zType)) {
            request.put("zType", zType);
        }
        if (!TextUtils.isEmpty(flag)) {
            request.put("flag", flag);
        }
        if (doctorId != null) {
            request.put("doctorId", doctorId);
        }
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                CreatCase creatCase = JsonParser.deserializeFromJson(result, CreatCase.class);
                startActivity(InterrogationChatActivity.getIntent(InterrogationUserChoiceActivity.this, caseID, creatCase.getData().getCreator()));
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    /**
     * 列表点击事件
     **/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        selectedPosition = position - 1;
        adapter.notifyDataSetChanged();

    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.adapter_patient_info, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }


    class Holder extends QBaseViewHolder implements View.OnClickListener {
        private TextView nameLabel, sex_age;
        private LinearLayout next;

        @Override
        public void initializeView(View view) {
            nameLabel = (TextView) view.findViewById(R.id.mPersonItemNameLabel);
            next = (LinearLayout) view.findViewById(R.id.mPersonItemEditorImg);
            next.setOnClickListener(this);
            sex_age = (TextView) view.findViewById(R.id.mPersonItemSex_AgeLabel);

        }

        @Override
        public void initializeData(int position) {
            Patient.DataEntity patient = (Patient.DataEntity) modules.get(position);
            Log.i("TAG", modules.size() + ">>>>>>" + patient.getGender());
            if (position == selectedPosition) {
                nameLabel.setTextColor(getResources().getColor(R.color.red));
            } else {
                nameLabel.setTextColor(getResources().getColor(R.color.black));
            }
            String birthday = DateUtils.getAge(patient.getBirthday());
            String sex = patient.getGender();
            if (!TextUtils.isEmpty(sex)) {
                if (sex.equals("1")) {
                    sex = "男";
                } else if (sex.equals("0")) {
                    sex = "女";
                }
            }
            String[] data = {"(", sex, ",", birthday, "岁", ")"};
            String personInfo = StringUtils.getString(data);
            nameLabel.setText(patient.getName());
            sex_age.setText(personInfo);
        }

        @Override
        public void onClick(View v) {
            edit_Pat(Constants.VALUS_EDIT_PERSON, selectedPosition);
        }
    }


    /*
    * 编辑问诊人信息
    * **/
    public void edit_Pat(int type, int position) {
        startActivityForResult(InterrogationUserEditorActivity.getIntent(this, (Patient.DataEntity) modules.get(position), type), 100);
    }

    /****
     * 增加问诊人信息
     **/
    public void add_Pat(int type) {
        startActivityForResult(InterrogationUserEditorActivity.getIntent(this, null, type), 100);
    }

    /***
     * 长按列表出现PoppupWindow
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        selectedPosition = position - 1;
        pop = new PhotoPopupWindow(this);
        pop.setItemText("删除", "编辑");
        pop.setItemClickListener(this);
        pop.showPopupWindow();
        return true;
    }

    /***
     * PoppupWindow的删除和编辑
     **/
    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                load_del_Person();
                break;
            case 1:
                edit_Pat(Constants.VALUS_EDIT_PERSON, selectedPosition);
                break;
        }
        pop.dismiss();
    }

    /***
     * 上拉和下拉加载问诊人列表
     **/
    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        loadInquiryPersonInfo();
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    /***
     * 删除问诊人列表
     **/
    public void load_del_Person() {
        Patient.DataEntity patient = (Patient.DataEntity) modules.get(selectedPosition);
        String url = UrlHelpper.DEL_PERSON_URL + "?id=" + patient.getId();
        Request request = new Request(url, Request.RequestMethod.GET, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                modules.remove(selectedPosition);

                adapter.notifyDataSetChanged();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
