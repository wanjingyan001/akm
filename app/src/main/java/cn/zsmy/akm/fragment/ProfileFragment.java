package cn.zsmy.akm.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import cn.wei.library.fragment.BaseFragment;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.ProfileDetails;
import cn.zsmy.akm.entity.UserInfo;
import cn.zsmy.akm.home.LoginActivity;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.profile.BalanceActivity;
import cn.zsmy.akm.profile.FeedBackActivity;
import cn.zsmy.akm.profile.IntegralActivity;
import cn.zsmy.akm.profile.InterrogationUserListActivity;
import cn.zsmy.akm.profile.MoreActivity;
import cn.zsmy.akm.profile.MyDoctorActivity;
import cn.zsmy.akm.profile.UserInfoActivity;
import cn.zsmy.akm.utils.Is_Login;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.row.BaseRowDescriptor;
import cn.zsmy.akm.widget.row.ContainerDescriptor;
import cn.zsmy.akm.widget.row.ContainerView;
import cn.zsmy.akm.widget.row.GeneralRowDescriptor;
import cn.zsmy.akm.widget.row.GroupDescriptor;
import cn.zsmy.akm.widget.row.OnRowClickListener;
import cn.zsmy.akm.widget.row.SimpleInfoRowDescriptor;
import cn.zsmy.akm.widget.row.SimpleInfoRowView;
import cn.zsmy.akm.widget.row.tool.RowActionEnum;
import cn.zsmy.akm.widget.row.tool.RowViewFactory;

/**
 * 我
 * Created by qinwei on 2015/11/17 10:04
 */
public class ProfileFragment extends BaseFragment implements OnRowClickListener {
    private ContainerView mWidgetContainerView;
    private SimpleInfoRowDescriptor.SimpleInfo info;
    private ArrayList<BaseRowDescriptor> rowDescriptors3;
    private ArrayList<GroupDescriptor> groupDescriptors;
    private ArrayList<BaseRowDescriptor> rowDescriptors1;
    private ProfileDetails.DataEntity profileData = new ProfileDetails.DataEntity();
    private int score;
    private int validAmount;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initializeView(View v) {
        Log.d("TAG", "initializeView");
        profileData = ((ProfileDetails.DataEntity) getArguments().getSerializable("TAG"));
        RowViewFactory.LINE_IS_MATCH_PARENT = false;
        mWidgetContainerView = (ContainerView) v.findViewById(R.id.mWidgetContainerView);
        obtainCurrentView();
    }

    private void initUserOpera() {
        groupDescriptors = new ArrayList<>();
        rowDescriptors1 = new ArrayList<BaseRowDescriptor>();
        ArrayList<BaseRowDescriptor> rowDescriptors2 = new ArrayList<BaseRowDescriptor>();
        if (Is_Login.getLoginStatus(getActivity())) {
            rowDescriptors1.add(new GeneralRowDescriptor(R.drawable.icon_profile_remaining, "我的余额 ", "", RowActionEnum.PROFILE_BALANCE));
            rowDescriptors1.add(new GeneralRowDescriptor(R.drawable.icon_profile_integral, "我的积分", "", RowActionEnum.PROFILE_INTEGRAL));
            rowDescriptors1.add(new GeneralRowDescriptor(R.drawable.icon_profile_doctor, "我的医生", "", RowActionEnum.PROFILE_DOCTOR));
            rowDescriptors1.add(new GeneralRowDescriptor(R.drawable.icon_profile_health, "健康档案", "", RowActionEnum.PROFILE_INTERROGATION_USER));
        }
        rowDescriptors2.add(new GeneralRowDescriptor(R.drawable.icon_profile_setting, "更多", RowActionEnum.PROFILE_MORE));
        rowDescriptors2.add(new GeneralRowDescriptor(R.drawable.icon_feed_back, "意见反馈", RowActionEnum.PROFILE_SETTING));
        GroupDescriptor groupDescriptor1 = new GroupDescriptor("", rowDescriptors1);
        GroupDescriptor groupDescriptor2 = new GroupDescriptor("", rowDescriptors2);
        this.rowDescriptors3 = new ArrayList<>();
        if (MyApplication.getProfile() != null) {
            Log.d("TAG", "11111");
            info = new SimpleInfoRowDescriptor.SimpleInfo();
            UserInfo.DataEntity userInfo = MyApplication.getUserInfo();
            if (userInfo != null) {
                Log.d("TAG", "22222");
                String nickname = userInfo.getNickname();
                if (!TextUtils.isEmpty(nickname)) {
                    info.setAccount(nickname);
                } else {
                    info.setAccount(userInfo.getPhone());
                }
                if (!TextUtils.isEmpty(userInfo.getAvatar())) {
                    info.setIconUrl(UrlHelpper.FILE_IP + userInfo.getAvatar());
                }
                rowDescriptors3.add(new SimpleInfoRowDescriptor(info, RowActionEnum.PROFILE_INFO));
            }
        } else {
            rowDescriptors3.add(new SimpleInfoRowDescriptor(null, RowActionEnum.PROFILE_INFO));
        }
        GroupDescriptor groupDescriptor3 = new GroupDescriptor("", rowDescriptors3);
        groupDescriptors.add(groupDescriptor3);
        groupDescriptors.add(groupDescriptor1);
        groupDescriptors.add(groupDescriptor2);
        ContainerDescriptor containerDescriptor = new ContainerDescriptor(groupDescriptors);
        mWidgetContainerView.initializeData(containerDescriptor, this);
        mWidgetContainerView.notifyDataChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG", "onStart");
        if (MyApplication.getProfile() != null && MyApplication.getProfile().getUserId() != null) {
            loadMyProfileInfo();
            score = profileData.getScore();
            validAmount = profileData.getValidAmount();
            initUserOpera();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.i(this.getClass().getSimpleName(), hidden + "");
        if (!hidden) {
            obtainCurrentView();
        }

    }

    @Override
    public void onRowClick(View rowView, RowActionEnum action) {
        switch (action) {
            case PROFILE_INFO:
                if (MyApplication.getProfile() != null) {
                    startActivity(UserInfoActivity.getIntent(getContext()));
                } else {
                    startActivity(LoginActivity.getIntent(getContext()));
                }
                break;
            case PROFILE_BALANCE:
                Intent intent = new Intent(getActivity(), BalanceActivity.class);
                intent.putExtra("validAmount", validAmount);
                startActivity(intent);
                break;
            case PROFILE_INTEGRAL:
                Intent intent1 = new Intent(getActivity(), IntegralActivity.class);
                intent1.putExtra("score", score);
                startActivity(intent1);
                break;
            case PROFILE_INTERROGATION_USER:
                startActivity(new Intent(getActivity(), InterrogationUserListActivity.class));
                break;
            case PROFILE_MORE:
                startActivity(new Intent(getActivity(), MoreActivity.class));
                break;
            case PROFILE_DOCTOR:
                startActivity(MyDoctorActivity.newIntent(getActivity()));
                break;
            case PROFILE_SETTING:
                startActivity(FeedBackActivity.newIntent(getActivity()));
                break;
        }
    }

    /****
     * 加载个人中心
     **/
    private void loadMyProfileInfo() {
        Request request = new Request(UrlHelpper.PERSON_PROFILE, Request.RequestMethod.GET, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                ProfileDetails profileDetails = JsonParser.deserializeFromJson(result, ProfileDetails.class);
                profileData = profileDetails.getData();
                getUserInfo();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    private void getUserInfo() {
        Request request = new Request(UrlHelpper.USER_INFO, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                UserInfo userInfo = JsonParser.deserializeFromJson(result, UserInfo.class);
                if (userInfo.getData() != null) {
                    Log.d("TAG", "==========");
                    MyApplication.setUserInfo(userInfo.getData());
                    SimpleInfoRowView.setUserName(userInfo.getData().getNickname());
                    SimpleInfoRowView.setUserHead(userInfo.getData().getAvatar());
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    private void obtainCurrentView() {
        initUserOpera();
    }

}
