package cn.zsmy.akm.doctor.profile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.wei.library.fragment.BaseFragment;
import cn.zsmy.akm.doctor.bean.DoctorInfo;
import cn.zsmy.akm.doctor.bean.Profile;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.profile.ProfileInfoActivity;
import cn.zsmy.akm.doctor.profile.adapter.ProfileInfoAdapter;
import cn.zsmy.akm.doctor.profile.bean.CityName;
import cn.zsmy.akm.doctor.profile.bean.HospitalName;
import cn.zsmy.akm.doctor.profile.bean.JobTitle;
import cn.zsmy.akm.doctor.profile.bean.OfficeName;
import cn.zsmy.akm.doctor.profile.edit.EditActivity;
import cn.zsmy.akm.doctor.profile.edit.SelectActivity;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.pop.PhotoPopupWindow;
import cn.zsmy.doctor.R;


/**
 * Created by zzz on 15/12/21.
 * 基本信息
 */
public class ProfileCenterFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener ,PhotoPopupWindow.onPopupWindowItemClickListener {
    private ListView listView;
    private ProfileInfoAdapter adapter;
    private List<String> datas;
    private Profile profile;
    private String[] profile_s;
    private String TAG=this.getClass().getSimpleName();
    private boolean WHETHER_FROM_ACTIVITY_BACK=false;
    private PhotoPopupWindow pop;
    private Request request;
    private DoctorInfo.DataEntity data;
    private Button next_Button;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_profile_center;
    }

    @Override
    protected void initializeView(View v) {
        listView = (ListView) v.findViewById(R.id.profile_center_lv);
        next_Button=(Button)v.findViewById(R.id.next);
        next_Button.setOnClickListener(this);
        profile = MyApplication.getProfile();
        datas = new ArrayList<>();
        int[] drawable = {R.drawable.icon_profile_center_person_info, R.drawable.icon_profile_center_sex, R.drawable.icon_profile_center_city,
                R.drawable.icon_profile_center_hospital,
                R.drawable.icon_profile_center_office_name, R.drawable.icon_profile_center_job_title, R.drawable.icon_profile_center_office_phone
        };
        List<Integer> drawableDatas = new ArrayList<>();
        for (int s : drawable) {
            drawableDatas.add(s);
        }
        if(profile!=null) {
            profile_s = getResources().getStringArray(R.array.profile_info_list);
            for (String s : profile_s) {
                datas.add(s);
            }
        }
        adapter = new ProfileInfoAdapter(datas, drawableDatas, getActivity());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        request = new Request(UrlHelper.EDIT_DOCTOR_INFO, Request.RequestMethod.POST, getActivity());


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ProfileInfoActivity)getActivity()).setOnProfileCenterDataListener(new ProfileInfoActivity.onProfileCenterDataListener() {
            @Override
            public void getReturnData(DoctorInfo doctorInfo) {
                if (doctorInfo != null) {
                    data = doctorInfo.getData();
                    loadViewData();
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (profile != null&&!WHETHER_FROM_ACTIVITY_BACK&&data!=null) {
                loadViewData();
        }else{
            WHETHER_FROM_ACTIVITY_BACK=false;
        }
    }

    /***
     * 跳转到名字编辑界面
     **/
    public void setName_Rela() {
        Intent intent = new Intent(getActivity(), EditActivity.class);
        intent.putExtra("CONTEXT", Constants.EDIT_PERSON_NAME_VALUES);
        if(data!=null){
            intent.putExtra("CONTENT",data.getName());
        }
        startActivityForResult(intent, Constants.RESULT_CDDE_EDIT_PERSON_NAME);
    }
    /***
     * 跳转到科室电话编辑界面
     **/
    public void setDepartment_Phone() {
        Intent intent = new Intent(getActivity(), EditActivity.class);
        intent.putExtra("CONTEXT", Constants.EDIT_OFFICE_PHONE_NAME_VALUES);
        if(data!=null){
            intent.putExtra("CONTENT",data.getPhone());
        }

        startActivityForResult(intent, Constants.RESULT_CDDE_EDIT_OFFICE_PHONE_NAME);
    }
    /***
     * 跳转到选择城市界面
     **/
    public void setCity_Name() {
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("CONTEXT", Constants.SELECT_PROVINCE_VALUES);
        startActivityForResult(intent,  Constants.RESULT_CDDE_SELECT_PROVINCE);
    }
    /***
     * 跳转到选择医院界面
     **/
    public void setHospital_Name() {
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("CONTEXT", Constants.SELECT_HOSPITAL_VALUES);
        startActivityForResult(intent, Constants.RESULT_CDDE_SELECT_HOSPITAL);
    }
    /***
     * 跳转到选择职称界面
     **/
    public void setJob_Title_Name() {
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("CONTEXT", Constants.SELECT_JOB_TITLE_VALUES);
        startActivityForResult(intent, Constants.RESULT_CDDE_SELECT_JOB_TITLE);
    }
    /***
     * 跳转到选择科室界面
     **/
    public void setOffice_Name() {
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("CONTEXT", Constants.SELECT_OFFICE_VALUES);
        startActivityForResult(intent, Constants.RESULT_CDDE_SELECT_OFFICE);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                //修改名字
                setName_Rela();
                WHETHER_FROM_ACTIVITY_BACK=true;
                break;
            case 1:
                //修改性别
                pop = new PhotoPopupWindow(getActivity());
                pop.setItemText("男","女");
                pop.setItemClickListener(this);
                pop.showPopupWindow();
                break;
            case 2:
                //修改城市
                setCity_Name();
                WHETHER_FROM_ACTIVITY_BACK=true;
                break;
            case 3:
                setHospital_Name();
                WHETHER_FROM_ACTIVITY_BACK=true;
                break;
            case 4:
                //修改科室
                setOffice_Name();
                WHETHER_FROM_ACTIVITY_BACK=true;
                break;
            case 5:
                setJob_Title_Name();
                WHETHER_FROM_ACTIVITY_BACK=true;
                //修改职称
                break;
            case 6:
                //修改科室电话
                setDepartment_Phone();
                WHETHER_FROM_ACTIVITY_BACK=true;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if(request.getUrlParameters()!=null&&request.getUrlParameters().size()!=0){
                     submit();
                }
                if (getActivity() instanceof ToIntroduction) {
                    ((ToIntroduction) getActivity()).toIntroduction();
                }
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, resultCode + "");
        if (data != null && resultCode == -1) {
            switch (requestCode) {
                case Constants.RESULT_CDDE_EDIT_PERSON_NAME:
                    String name = data.getAction();
                    if (!TextUtils.isEmpty(name)) {
                          datas.set(0,"姓名："+name);
                          adapter.notifyDataSetChanged();
//                        name 姓名
                          request.put("name",name);
                    }
                    break;
                case Constants.RESULT_CDDE_SELECT_PROVINCE:
                    CityName.DataEntity city_name =( CityName.DataEntity) data.getExtras().get("CITY_NAME");
                    if(city_name!=null){
                        datas.set(2,"城市："+city_name.getName());
                        adapter.notifyDataSetChanged();
//                        cityId	城市Id
//                        city 城市
                        request.put("cityId", city_name.getId());
                        request.put("city",city_name.getName());
                    }
                    break;
                case Constants.RESULT_CDDE_SELECT_HOSPITAL:
                    HospitalName.DataEntity hospital_name = (HospitalName.DataEntity) data.getSerializableExtra("HOSPITAL_NAME");
                    datas.set(3, "医院：" + hospital_name.getName());
                    adapter.notifyDataSetChanged();
                    request.put("hospital", hospital_name.getName());
                    request.put("hospitalId", hospital_name.getId());
                    break;
                case Constants.RESULT_CDDE_SELECT_OFFICE:
                    OfficeName.DataEntity officeName = (OfficeName.DataEntity) data.getSerializableExtra("OFFICE_NAME");
                    datas.set(4, "科室：" + officeName.getName());
                    adapter.notifyDataSetChanged();
                    request.put("dept", officeName.getName());
                    request.put("deptId", officeName.getId());
                    break;
                case Constants.RESULT_CDDE_SELECT_JOB_TITLE:
                    JobTitle.DataEntity jobTitle =(JobTitle.DataEntity ) data.getExtras().get("JOB_TITLE");
                    if(jobTitle!=null){
                        datas.set(5,"职称："+jobTitle.getName());
                        adapter.notifyDataSetChanged();
//                        professionalTitle	职称
                        request.put("professionalTitle",jobTitle.getName());
                    }
                    break;
                case Constants.RESULT_CDDE_EDIT_OFFICE_PHONE_NAME:
                    String phone = data.getAction();
                    if (!TextUtils.isEmpty(phone)) {
//                        phone 电话号码
                        datas.set(6,"科室电话："+phone);
                        adapter.notifyDataSetChanged();
                        request.put("phone", phone);
                    }
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * 修改医生个人信息
     */
    private void submit() {
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {


            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                datas.set(1, "性别：男");
//                gender	性别
                request.put("gender","男");
                break;
            case 1:
                datas.set(1, "性别：女");
                request.put("gender","女");
                break;
        }
        adapter.notifyDataSetChanged();
        pop.dismiss();
    }

    public interface ToIntroduction {
        void toIntroduction();
    }
    public void loadViewData(){
        if(data==null){
            listView.setOnItemClickListener(this);
            next_Button.setVisibility(View.VISIBLE);
            return;
        }
        if(data.getAuthFlag()!=null&&data.getAuthFlag().equals("3")){
            next_Button.setVisibility(View.GONE);
        }else{
            listView.setOnItemClickListener(this);
            next_Button.setVisibility(View.VISIBLE);
        }
        datas.clear();
        if(data.getName()!=null){

            datas.add("姓名：" + data.getName());
        }else{
            datas.add("姓名：未填写");
        }

        if(data.getGender()!=null){
            if(data.getGender().equals("1")){
                datas.add("性别：男");
            }else if(data.getGender().equals("0")){
                datas.add("性别：女");
            }else{
                datas.add("性别："+data.getGender());
            }
        }else{
            datas.add("性别：未填写");
        }
        if(data.getCity()!=null){
            datas.add("城市：" + data.getCity());
        }else{
            datas.add("城市：未填写");
        }
        if(data.getHospital()!=null){
            datas.add("医院：" + data.getHospital());
        } else {
            datas.add("医院：未填写");
        }
        if(data.getDept()!= null) {
            datas.add("科室：" + data.getDept());
        } else {
            datas.add("科室：未填写");
        }
        if(data.getProfessionalTitle() != null) {
            datas.add("职称：" + data.getProfessionalTitle());
        }else{
            datas.add("职称：未填写");
        }

        if(data.getPhone()!=null){
            datas.add("科室电话：" + data.getPhone());
        } else {
            datas.add("科室电话：未填写");
        }
        adapter.notifyDataSetChanged();
    }
}
