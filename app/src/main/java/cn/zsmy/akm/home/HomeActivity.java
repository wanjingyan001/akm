package cn.zsmy.akm.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.update.UmengUpdateAgent;

import java.util.ArrayList;
import java.util.List;

import cn.wei.library.widget.BadgeView;
import cn.wei.library.widget.Tab;
import cn.wei.library.widget.TabIndicator;
import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.chat.im.IMPushManager;
import cn.zsmy.akm.entity.ProfileDetails;
import cn.zsmy.akm.fragment.CommunityFragment;
import cn.zsmy.akm.fragment.ConversationFragment;
import cn.zsmy.akm.fragment.IndexFragment;
import cn.zsmy.akm.fragment.ProfileFragment;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.http.Trace;
import cn.zsmy.akm.profile.FeedBackActivity;
import cn.zsmy.akm.side.CheckProjectActivity;
import cn.zsmy.akm.side.DrugListActivity;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.dialog.ChooseDialog;
import cn.zsmy.akm.widget.row.BaseRowDescriptor;
import cn.zsmy.akm.widget.row.ContainerDescriptor;
import cn.zsmy.akm.widget.row.ContainerView;
import cn.zsmy.akm.widget.row.GeneralRowDescriptor;
import cn.zsmy.akm.widget.row.GroupDescriptor;
import cn.zsmy.akm.widget.row.OnRowClickListener;
import cn.zsmy.akm.widget.row.tool.RowActionEnum;


/**
 * 主界面
 * Created by qinwei on 2015/11/16.
 */
public class HomeActivity extends BaseTitleActivity implements TabIndicator.OnTabClickListener, OnRowClickListener, View.OnClickListener {

    private TabIndicator mHomeIndicator;
    private ArrayList<Tab> tabs;
    private int currentIndex = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ContainerView mWidgetContainerView;
    private BadgeView badgeView;
    private static String TAG = "MyApplication";
    private boolean isShow;
    private TextView mToobarTitle;
    private ImageView mMessageIcon;
    private ProfileDetails.DataEntity profileDate = new ProfileDetails.DataEntity();
    private List<Fragment> fragmentList;
    private IndexFragment indexFragment;
    private CommunityFragment communityFragment;
    private ConversationFragment conversationFragment;
    private ProfileFragment profileFragment;
    private ChooseDialog dialog;

    @Override
    protected void setContentView() {
        UmengUpdateAgent.setUpdateOnlyWifi(false);//所有网络环境下都提醒
        UmengUpdateAgent.update(this);//设置自动更新
        UmengUpdateAgent.setUpdateCheckConfig(false);//是否显示调试信息
        UmengUpdateAgent.setDeltaUpdate(false);//设置全量更新
        setContentView(R.layout.activity_home);
    }

    @SuppressWarnings("ResourceType")
    @Override
    protected void initializeView() {
        super.initializeView();
        MyApplication.getInstance().addActivity(this);
        findViewById(R.id.mHomeMessage).setOnClickListener(this);
        indexFragment = new IndexFragment();
        conversationFragment = new ConversationFragment();
        communityFragment = new CommunityFragment();
        profileFragment = new ProfileFragment();
        mToobarTitle = ((TextView) findViewById(R.id.mToolBarTitleLabel));
        mMessageIcon = ((ImageView) findViewById(R.id.message_icon));
        initializeLeftMenuView();
        mHomeIndicator = (TabIndicator) findViewById(R.id.mHomeIndicator);
        mHomeIndicator.setOnTabClickListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                isShow = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                isShow = false;
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        badgeView = new BadgeView(this);
        badgeView.setTargetView(findViewById(R.id.mHomeNoticeImg));
        dialog = new ChooseDialog(this, 0);
        loadMyProfileInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (MyApplication.getProfile() != null) {
            if (MyApplication.getProfile().getUserId() != null) {
                IMPushManager.getInstance(getApplicationContext()).startPush();
                IMPushManager.getInstance(getApplicationContext()).setTags(
                        MyApplication.getProfile().getUserId());
            }
        }
    }

    private void initializeLeftMenuView() {
        mWidgetContainerView = (ContainerView) findViewById(R.id.mWidgetContainerView);
        ArrayList<GroupDescriptor> groupDescriptors = new ArrayList<GroupDescriptor>();
        ArrayList<BaseRowDescriptor> rowDescriptors1 = new ArrayList<BaseRowDescriptor>();
        rowDescriptors1.add(new GeneralRowDescriptor(0, "阿克曼皮肤药房 ", RowActionEnum.PROFILE_REMAIN));
        rowDescriptors1.add(new GeneralRowDescriptor(0, "阿克曼皮肤检验科", RowActionEnum.PROFILE_INTEGRAL));
        rowDescriptors1.add(new GeneralRowDescriptor(0, "意见反馈", RowActionEnum.PROFILE_SETTING));

        GroupDescriptor groupDescriptor1 = new GroupDescriptor("", rowDescriptors1);

        groupDescriptors.add(groupDescriptor1);
        ContainerDescriptor containerDescriptor = new ContainerDescriptor(groupDescriptors);
        mWidgetContainerView.initializeData(containerDescriptor, this);
        mWidgetContainerView.notifyDataChanged();
    }


    @Override
    protected void initializeData() {
//        IMPushManager.getInstance(getApplicationContext()).startPush();
        tabs = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putSerializable("TAG", profileDate);
        conversationFragment.setArguments(bundle);
        profileFragment.setArguments(bundle);
        tabs.add(new Tab("主页", R.drawable.tab_inquiry_btn, IndexFragment.class));
        tabs.add(new Tab("病历", R.drawable.tab_casehistory_btn, ConversationFragment.class));
        tabs.add(new Tab("圈子", R.drawable.tab_community_btn, CommunityFragment.class));
        tabs.add(new Tab("我", R.drawable.tab_mine_btn, ProfileFragment.class));
        mHomeIndicator.initializeData(tabs);
        setFragmentList();

        mHomeIndicator.setCurrentTab(0);
        findViewById(R.id.mHomeMessage).setOnClickListener(this);
        findViewById(R.id.drawer_view);
    }

    private void setFragmentList() {
        fragmentList = new ArrayList<>();

        fragmentList.add(indexFragment);
        fragmentList.add(conversationFragment);
        fragmentList.add(communityFragment);
        fragmentList.add(profileFragment);
        mHomeIndicator.setCurrentTab(currentIndex);
    }

    @Override
    public boolean onTabClick(int index) {
        replaceFragment(index);
        setTitle(tabs.get(index).getLabel());
        return true;
    }

    /**
     * 切换fragment
     *
     * @param index
     */
    private void replaceFragment(int index) {
        Trace.d("<<<<<<<<<<<" + fragmentList.get(index).getClass().getSimpleName());
        try {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("" + index);
            Fragment oldFragment = getSupportFragmentManager().findFragmentByTag("" + currentIndex);
            if (fragment == null) {
                Trace.d("fragment为空");
                if (oldFragment != null && index != currentIndex) {
                    getSupportFragmentManager().beginTransaction().hide(oldFragment).commitAllowingStateLoss();
                }
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.mHomeFrame, fragmentList.get(index), "" + index)
//                        .show(fragmentList.get(index))
//                        .commitAllowingStateLoss();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.mHomeFrame, fragmentList.get(index), "" + index)
                        .commitAllowingStateLoss();
                if (fragmentList.get(index).isAdded()) {
//                    getSupportFragmentManager().beginTransaction().show(fragmentList.get(index)).commit();
                    getSupportFragmentManager().beginTransaction().hide(oldFragment).add(fragmentList.get(index), "" + currentIndex).commit();
                }
            } else {
                for (int i = 0; i < fragmentList.size(); i++) {
                    if (i != index) {
                        getSupportFragmentManager().beginTransaction().hide(fragmentList.get(i)).commitAllowingStateLoss();
                    }
                }
                getSupportFragmentManager().beginTransaction().show(fragmentList.get(index)).commitAllowingStateLoss();
            }
            currentIndex = index;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void recoveryState(Bundle saveInstance) {
        tabs = (ArrayList<Tab>) saveInstance.getSerializable(Constants.KEY_TAB_ENTITIES);
        mHomeIndicator.initializeData(tabs);
        currentIndex = saveInstance.getInt(Constants.KEY_CURRENT_TAB_INDEX);
        for (int i = 0; i < tabs.size(); i++) {
            if (getSupportFragmentManager().findFragmentByTag("" + i) != null)
                getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("" + i)).commitAllowingStateLoss();
            Trace.d(getSupportFragmentManager().findFragmentByTag("" + i).getClass() + "");
        }
        mHomeIndicator.setCurrentTab(currentIndex);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.KEY_CURRENT_TAB_INDEX, currentIndex);
        outState.putSerializable(Constants.KEY_TAB_ENTITIES, tabs);
    }

    @Override
    public void protectApp() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    @Override
    public boolean hasAppKilled() {
        if (MyApplication.getInstance().getAppState() != -1) {
            return false;
        }
        return true;
    }

    @Override
    public void onRowClick(View rowView, RowActionEnum action) {
        switch (action) {
            case PROFILE_REMAIN:
                startActivity(DrugListActivity.getIntent(this));
                break;
            case PROFILE_INTEGRAL:
                startActivity(CheckProjectActivity.getIntent(this));
                break;
            case PROFILE_SETTING:
                startActivity(FeedBackActivity.newIntent(this));
                break;

        }
    }


    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {
                    //两次按键小于2秒时，退出应用
                    MyApplication.getInstance().exit();
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mHomeMessage:
                //点击铃铛图标
                startActivity(MessagesContentActivity.getIntent(HomeActivity.this));
                changedNoticeNum(0);
                if (isShow) {
                    mDrawerLayout.closeDrawers();
                }
                break;
        }
    }

    public void changedNoticeNum(int num) {
//        badgeView.setText("1");
        badgeView.setBadgeCount(num);
    }

    /****
     * 加载个人中心
     **/
    private void loadMyProfileInfo() {
        Request request = new Request(UrlHelpper.PERSON_PROFILE, Request.RequestMethod.GET, this);
        Log.d("TAG", UrlHelpper.PERSON_PROFILE);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                ProfileDetails profileDetails = JsonParser.deserializeFromJson(result, ProfileDetails.class);
                profileDate = profileDetails.getData();
                if (profileDate != null) {
                    MyApplication.setProfileDetails(profileDate);
                    Bundle bundle = new Bundle();
                    bundle.putString("TAG", String.valueOf(profileDate.getHisCaseNum()));
                    conversationFragment.setArguments(bundle);
                    changedNoticeNum(profileDate.getNoticeNum());
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request, dialog);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}