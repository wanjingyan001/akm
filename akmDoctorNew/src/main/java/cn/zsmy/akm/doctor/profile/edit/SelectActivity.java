package cn.zsmy.akm.doctor.profile.edit;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.profile.adapter.SelectCityAdapter;
import cn.zsmy.akm.doctor.profile.bean.CityName;
import cn.zsmy.akm.doctor.profile.bean.HospitalName;
import cn.zsmy.akm.doctor.profile.bean.JobTitle;
import cn.zsmy.akm.doctor.profile.bean.OfficeName;
import cn.zsmy.akm.doctor.profile.bean.ProvinceName;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2016/1/12 17:27
 * 医院接口无法用
 */
public class SelectActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
private ListView select_lits;
    private SelectCityAdapter adapter;
    private List<Object>datas;
    private String flag;
    private String url;
    private FrameLayout searchLayout;
    private EditText search_hos;
    private ImageView cancel;
    private String name,id;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                showContent(EmptyView.State.empty,1);
            }else {
                showContent(EmptyView.State.ing,1);
            }

        }
    };
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_select_city);
        MyApplication.getInstance().addActivity(this);
        flag=getIntent().getStringExtra("CONTEXT");
        Log.i("TAG", flag);
        if(flag==null){
            return;
        }

    }
    @Override
    protected void initializeView() {
        super.initializeView();
        if(flag.equals(Constants.SELECT_HOSPITAL_VALUES)){
            searchLayout=(FrameLayout)findViewById(R.id.SearchLayoutID);
            search_hos=(EditText)findViewById(R.id.search_doctor_EditID);
            cancel=(ImageView)findViewById(R.id.search_doctor_cancel);
            cancel.setOnClickListener(this);
            searchLayout.setVisibility(View.VISIBLE);
            initEdit();
            setTitle("选择医院");
        }else if(flag.equals(Constants.SELECT_PROVINCE_VALUES)){
            setTitle("选择省份");
        } else if(flag.equals(Constants.SELECT_JOB_TITLE_VALUES)){
            setTitle("选择职称");
        }
        else if(flag.equals(Constants.SELECT_OFFICE_VALUES)){
            setTitle("选择科室");
        }
        select_lits=(ListView)findViewById(R.id.select_city_list);
        datas=new ArrayList<>();
        adapter=new SelectCityAdapter(datas,this);
        select_lits.setAdapter(adapter);
        select_lits.setOnItemClickListener(this);
    }
    @Override
    protected void initializeData() {
        if(flag.equals(Constants.SELECT_HOSPITAL_VALUES)){
            url=UrlHelper.SELECT_HOSPITAL_URL;
        }else if(flag.equals(Constants.SELECT_PROVINCE_VALUES)){
            url=UrlHelper.GET_PROVINCE_URL;
        } else if(flag.equals(Constants.SELECT_JOB_TITLE_VALUES)){
            url=UrlHelper.GET_JOB_TITLE_URL;
        }
        else if(flag.equals(Constants.SELECT_OFFICE_VALUES)){
            url=UrlHelper.GET_OFFICE_NAME_URL;
        }
        Log.i("TAG", url);
        loadCity(name, id);
    }
    //获取城市列表
    public void loadCity(String name, final String id) {
        if(name!=null){
        try {
            name= URLEncoder.encode(name, "UTF-8");
            url=UrlHelper.SELECT_HOSPITAL_URL;
            url=url+"name="+name;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        }
        if(id!=null){
                url=UrlHelper.GET_CITIES_URL+"parentId="+id;
        }
        Log.i("TAG",">>>>>>>>"+ url);
        Request request = new Request(url, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                datas.clear();
                if (flag.equals(Constants.SELECT_HOSPITAL_VALUES)) {
                    HospitalName hospitalName = JsonParser.deserializeFromJson(result, HospitalName.class);
                    datas.addAll(hospitalName.getData());

                } else if (flag.equals(Constants.SELECT_PROVINCE_VALUES)) {
                    if (id != null) {
                        CityName cityName = JsonParser.deserializeFromJson(result, CityName.class);
                        datas.addAll(cityName.getData());
                        setTitle("选择城市");
                    } else {
                        ProvinceName provinceName = JsonParser.deserializeFromJson(result, ProvinceName.class);
                        datas.addAll(provinceName.getData());
                    }
                } else if (flag.equals(Constants.SELECT_JOB_TITLE_VALUES)) {
                    JobTitle jobTitle = JsonParser.deserializeFromJson(result, JobTitle.class);
                    datas.addAll(jobTitle.getData());
                } else if (flag.equals(Constants.SELECT_OFFICE_VALUES)) {
                    OfficeName officeName = JsonParser.deserializeFromJson(result, OfficeName.class);
                    datas.addAll(officeName.getData());
                }
                handler.sendEmptyMessageDelayed(1, 1000);
                adapter.notifyDataSetChanged();

            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(datas.get(position) instanceof ProvinceName.DataEntity){
            loadCity(name,((ProvinceName.DataEntity) datas.get(position)).getId());
        }else{
            setIntentActivtyResult(position);
        }


    }
    public void setIntentActivtyResult(int position){
        Intent intent = new Intent();
        if(flag.equals(Constants.SELECT_HOSPITAL_VALUES)){
            intent.putExtra("HOSPITAL_NAME", (HospitalName.DataEntity) datas.get(position));
        }else if(flag.equals(Constants.SELECT_PROVINCE_VALUES)){
            intent.putExtra("CITY_NAME", (CityName.DataEntity)datas.get(position));
        } else if(flag.equals(Constants.SELECT_JOB_TITLE_VALUES)){
            intent.putExtra("JOB_TITLE", (JobTitle.DataEntity) datas.get(position));
        }
        else if(flag.equals(Constants.SELECT_OFFICE_VALUES)){
            intent.putExtra("OFFICE_NAME", (OfficeName.DataEntity) datas.get(position));
        }
        setResult(RESULT_OK, intent);
        finish();
    }
    public void initEdit(){
        TextWatcher watcher=new TextWatcher() {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                name = search_hos.getText().toString();
                Log.i("TAG", name);
                if(!TextUtils.isEmpty(name)){
                    Log.i("TAG",">>>>>>>>"+ name);
                    loadCity(name,id);
                }

            }
        };
        search_hos.addTextChangedListener(watcher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_doctor_cancel:
                search_hos.setText(null);
                break;
            default:
                break;
        }
    }
}
