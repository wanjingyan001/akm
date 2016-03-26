package cn.zsmy.akm.doctor.profile;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.doctor.base.BaseTitleActivity;
import cn.zsmy.akm.doctor.bean.DoctorInfo;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.profile.edit.VideoRecordActivity;
import cn.zsmy.akm.doctor.profile.fragment.DoctorLicenseFragment;
import cn.zsmy.akm.doctor.profile.fragment.ProfileCenterFragment;
import cn.zsmy.akm.doctor.profile.fragment.ProfileIntroductionFragment;
import cn.zsmy.akm.doctor.profile.fragment.UploadPhotoFragment;
import cn.zsmy.akm.doctor.profile.view.ProfileInfoTopChooseView;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;

public class ProfileInfoActivity extends BaseTitleActivity implements ProfileCenterFragment.ToIntroduction
        , ProfileIntroductionFragment.ToUploadPhoto, UploadPhotoFragment.ToLicense, DoctorLicenseFragment.Submit,ViewPager.OnPageChangeListener,ProfileInfoTopChooseView.ChooseListener {
    private ViewPager viewPager;
    private List<Fragment> datas;
    private LinearLayout profileHead;
    private List<String>title_datas;
    private boolean flag;
    private int curPosition;
    private ProfileInfoTopChooseView chooseView;
    private DoctorInfo.DataEntity data;
    private ProfileCenterFragment profileCenterFragment;
    private ProfileIntroductionFragment profileIntroductionFragment;
    private String autoFlag;
    public static Intent getIntent(Context context,String autoFlag){
        Intent intent=new Intent(context,ProfileInfoActivity.class);
        intent.putExtra("AUTOFLAG",autoFlag);
                      return intent;
    }
//    private Handler mHandler;
//    public void setHandler(Handler handler) {
//        mHandler = handler;
//    }
//    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_profile_details);
        MyApplication.getInstance().addActivity(this);
        autoFlag=getIntent().getStringExtra("AUTOFLAG");

    }
    @Override
    protected void initializeView() {
        super.initializeView();
        viewPager = (ViewPager) findViewById(R.id.profile_viewpage);
        profileHead = ((LinearLayout) findViewById(R.id.profile_info_top_group));
        datas = new ArrayList<>();
        title_datas = new ArrayList<>();
        title_datas.add("基本信息");
        title_datas.add("个人介绍");
        title_datas.add("上传形象");
        title_datas.add("上传证书");
        chooseView = new ProfileInfoTopChooseView(this,title_datas);
        chooseView.setOnChooseListener(this);
        profileHead.addView(chooseView);

    }

    @Override
    protected void initializeData() {
        setTitle("个人介绍");
        profileCenterFragment = new ProfileCenterFragment();
        profileIntroductionFragment = new ProfileIntroductionFragment();
        UploadPhotoFragment uploadPhotoFragment = new UploadPhotoFragment();
        DoctorLicenseFragment doctorLicenseFragment = new DoctorLicenseFragment();
        datas.add(profileCenterFragment);
        datas.add(profileIntroductionFragment);
        datas.add(uploadPhotoFragment);
        datas.add(doctorLicenseFragment);
        FragmentManager fm = getSupportFragmentManager();
        FragmentPagerAdapter adapter = new FragmentAdapter(fm);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(0);
        getDoctorInfo();

    }
    @Override
    public void toIntroduction() {

        viewPager.setCurrentItem(1);
    }

    @Override
    public void toUploadPhoto()
    {
        viewPager.setCurrentItem(2);
    }

    @Override
    public void toProfileInfo() {

        viewPager.setCurrentItem(0);
    }

    @Override
    public void toLicense() {

        viewPager.setCurrentItem(3);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        chooseView.selectView(position);
    }

    @Override
    public void onPageSelected(int position) {
        curPosition=position;
//        chooseView.selectView(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
           flag=false;

        }else if (state ==0) {
            flag=true;

        }

    }

    @Override
    public void OnChooseListener(int id) {
        chooseView.selectView(id);
        viewPager.setCurrentItem(id);
    }

    class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }
        @Override
        public int getCount() {
            return datas.size();
        }

    }
    private void getDoctorInfo() {
        Request request = new Request(UrlHelper.DOCTOR_PERSONAL_INFO,this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DoctorInfo doctorInfo = JsonParser.deserializeFromJson(result, DoctorInfo.class);
                doctorInfo.getData().setAuthFlag(autoFlag);
                mProfileCenterlistener.getReturnData(doctorInfo);
                mProfileIntroductionlistener.getReturnData(doctorInfo);
                mUpLoadPhotolistener.getReturnData(doctorInfo);
                mDoctorLicenselistener.getReturnData(doctorInfo);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }
    public onProfileCenterDataListener mProfileCenterlistener;
    public void setOnProfileCenterDataListener(onProfileCenterDataListener listener){
                        this.mProfileCenterlistener=listener;
    }
    public interface onProfileCenterDataListener{
               public void getReturnData(DoctorInfo doctorInfo);

    }
    public onProfileIntroductionListener mProfileIntroductionlistener;
    public void setOnProfileIntroductionListener(onProfileIntroductionListener listener){
        this.mProfileIntroductionlistener=listener;
    }
    public interface onProfileIntroductionListener{
        public void getReturnData(DoctorInfo doctorInfo);

    }

    public onUpLoadPhotoListener mUpLoadPhotolistener;
    public void setUpLoadPhotoListener(onUpLoadPhotoListener listener){
        this.mUpLoadPhotolistener=listener;
    }
    public interface onUpLoadPhotoListener{
        public void getReturnData(DoctorInfo doctorInfo);

    }

    public onDoctorLicenseListener mDoctorLicenselistener;
    public void setOnDoctorLicenseListener(onDoctorLicenseListener listener){
        this.mDoctorLicenselistener=listener;
    }
    public interface onDoctorLicenseListener{
        public void getReturnData(DoctorInfo doctorInfo);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RecordingActivity.uri=null;
        VideoRecordActivity.uri=null;
    }
}
