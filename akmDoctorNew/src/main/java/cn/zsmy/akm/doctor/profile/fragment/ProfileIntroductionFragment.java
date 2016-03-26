package cn.zsmy.akm.doctor.profile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.zsmy.akm.doctor.bean.DoctorInfo;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.profile.ProfileInfoActivity;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;


/**
 * Created by zzz on 15/12/21.
 * 个人介绍
 */
public class ProfileIntroductionFragment extends Fragment implements View.OnClickListener {
    private View viewById;
    private EditText introduc,specialty;
    private DoctorInfo.DataEntity dataEntity;
    private Button next_btn,privous_btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_profile_introduction, null);
        introduc=(EditText)view.findViewById(R.id.self_introduction_edit);
        specialty=(EditText)view.findViewById(R.id.Areas_of_expertise_edit);
        next_btn=(Button)view.findViewById(R.id.next);
        privous_btn=(Button)view.findViewById(R.id.privous);
        privous_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ProfileInfoActivity)getActivity()).setOnProfileIntroductionListener(new ProfileInfoActivity.onProfileIntroductionListener() {
            @Override
            public void getReturnData(DoctorInfo doctorInfo) {
                if (doctorInfo != null) {
                    dataEntity = doctorInfo.getData();
                    loadViewData();
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if(dataEntity!=null){
            loadViewData();
        }
    }
    public void loadViewData(){
        if(dataEntity.getAuthFlag()!=null&&dataEntity.getAuthFlag().equals("3")){
            next_btn.setVisibility(View.GONE);
            privous_btn.setVisibility(View.GONE);
            introduc.setEnabled(false);
            specialty.setEnabled(false);
        }else{
            next_btn.setVisibility(View.VISIBLE);
            privous_btn.setVisibility(View.VISIBLE);
        }
        introduc.setText(dataEntity.getIntroduc());
        specialty.setText(dataEntity.getSpecialty());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.privous:
                if (getActivity() instanceof ToUploadPhoto) {
                    ((ToUploadPhoto) getActivity()).toProfileInfo();
                }
                break;
            case R.id.next:
                if(specialty.getText().toString()==null){
                    Toast.makeText(getActivity(),"请输入擅长领域",Toast.LENGTH_LONG).show();

                }else if(introduc.getText().toString()==null){
                    Toast.makeText(getActivity(),"请输入个人介绍",Toast.LENGTH_LONG).show();
                }else{
                    submit();
                }
                if (getActivity() instanceof ToUploadPhoto) {
                    ((ToUploadPhoto) getActivity()).toUploadPhoto();
                }
                break;
        }
    }
    /**
     * 修改医生个人信息
     */
    private void submit() {
        Request request = new Request(UrlHelper.EDIT_DOCTOR_INFO, Request.RequestMethod.POST, getActivity());
        request.put("specialty",specialty.getText().toString().trim());
        request.put("introduc",introduc.getText().toString().trim());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    public interface ToUploadPhoto {
        void toUploadPhoto();
        void toProfileInfo();
    }


}
