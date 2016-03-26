package cn.zsmy.akm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.fragment.BaseListFragment;
import cn.wei.library.widget.BadgeView;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.Case;
import cn.zsmy.akm.entity.ProfileDetails;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.http.Trace;
import cn.zsmy.akm.interrogation.InterrogationChatActivity;
import cn.zsmy.akm.interrogation.InterrogationHistoryActivity;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.ListSort;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.dialog.ChooseDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 病例列表
 * Created by qinwei on 2015/11/17 10:00
 */
public class ConversationFragment extends BaseListFragment {
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            showContent();
        }
    };
    private ProfileDetails.DataEntity profile = new ProfileDetails.DataEntity();
    private boolean hidden = false;
    private TextView hisCase;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    showContent();
                    break;
                case 1:
                    showContent(EmptyView.State.empty);
                    break;

            }

        }
    };
    private CircleImageView caseImage;
    private List<Case.DataEntity> data;
    private boolean hasFoot = false;
    private int i = 1;
    private LinearLayout mConverLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_conversation;
    }

    @Override
    protected void initializeView(View v) {
        super.initializeView(v);
        if (MyApplication.getProfile() == null) {
            handler.sendEmptyMessageDelayed(1, 3000);
        }
        mPullToRefreshLsv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        obtainCurrentView();
        mPullToRefreshLsv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉加载和下拉刷新处理
                if (MyApplication.getProfile() != null && MyApplication.getProfile().getUserId() != null) {
                    if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                        loadConversationInfo(0);
                    }
                } else {
                    ChooseDialog dialog = new ChooseDialog(getActivity(), 0);
                    dialog.show();

                }
                getProfile();
                mPullToRefreshLsv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshLsv.onRefreshComplete();
                    }
                }, 1000);
            }
        });
    }

    @SuppressLint("NewApi")
    @Override
    protected void addRefreshFooterView(ListView refreshableView) {
        super.addRefreshFooterView(refreshableView);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_his_case, null);
        ((TextView) view.findViewById(R.id.Case_TitleID)).setText("历史病历");
        hisCase = ((TextView) view.findViewById(R.id.Case_ContentID));
        caseImage = ((CircleImageView) view.findViewById(R.id.Case_imgID));
        mConverLayout = ((LinearLayout) view.findViewById(R.id.conver_layout));
        caseImage.setBackground(getResources().getDrawable(R.drawable.ic_doctor_file));
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        if (MyApplication.getProfile() != null) {
            if (MyApplication.getProfileDetails() != null) {
                hisCase.setText("你有" + MyApplication.getProfileDetails().getHisCaseNum() + "个历史病历");
                hasFoot = true;
                refreshableView.addFooterView(view);
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            obtainCurrentView();
        }
    }

    private void obtainCurrentView() {
        if (MyApplication.getProfile() != null && MyApplication.getProfile().getUserId() != null) {
            if (!hasFoot) {
                addRefreshFooterView(mPullToRefreshLsv.getRefreshableView());
            }
            loadConversationInfo(0);
        } else {
            ChooseDialog dialog = new ChooseDialog(getActivity(), 0);
            dialog.show();
        }
    }

    private void loadConversationInfo(final int page) {
        Request request = new Request(UrlHelpper.CASE_LIST_URL + "&page=" + page, Request.RequestMethod.GET, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                if (page == 0) {
                    modules.clear();
                }
                Case c = JsonParser.deserializeFromJson(result, Case.class);
                data = c.getData();
                ListSort.sort(data);
                modules.addAll(data);
                adapter.notifyDataSetChanged();
                if (adapter.getCount() == 0) {
                    handler.sendEmptyMessage(1);
                } else {
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Trace.d(position + "======" + modules.size());
        if (position == modules.size() + 1 || position == modules.size() + 2) {
            Intent intent = new Intent(getActivity(), InterrogationHistoryActivity.class);
            startActivity(intent);
        } else {
            Case.DataEntity c = (Case.DataEntity) modules.get(position - 1);
            if (c.getCaseId() != null) {
                startActivity(InterrogationChatActivity.getIntent(getActivity(), c.getCaseId(), c.getId()));
            } else {
                Toast.makeText(getActivity(), "无此病例", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_conversation_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private TextView content_Salo, Time_Salo, title_Salo;
        private CircleImageView pic_Case;
        private LinearLayout badgeView_Add;

        @Override
        public void initializeView(View view) {
            content_Salo = (TextView) view.findViewById(R.id.Case_ContentID);
            Time_Salo = (TextView) view.findViewById(R.id.Case_TimeID);
            title_Salo = (TextView) view.findViewById(R.id.Case_TitleID);
            pic_Case = (CircleImageView) view.findViewById(R.id.Case_imgID);
            badgeView_Add = (LinearLayout) view.findViewById(R.id.Case_LinearLayoutID);
        }

        //        status  状态（0是接诊，1是已接诊，2、是已完成）
        @Override
        public void initializeData(int position) {
            Case.DataEntity c = (Case.DataEntity) modules.get(position);
            content_Salo.setText(c.getContent());
            String dateToString = DateUtils.getDateToString(c.getCreateTime(), 2);
            String nowTime = DateUtils.getDateToString(System.currentTimeMillis(), 2);
            if (dateToString.equals(nowTime)) {
                Time_Salo.setText(DateUtils.getDateToString(c.getCreateTime(), 1));
            } else {
                Time_Salo.setText(dateToString);
            }
            title_Salo.setText(c.getTitle());
            if (!TextUtils.isEmpty(c.getAvatar())) {
                ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + c.getAvatar(), pic_Case);
            }
            if (c.getNoticeNum() == null) {
                setBadgeView(null, 0);
            } else {
                setBadgeView(badgeView_Add, (int) c.getNoticeNum());
            }
        }
    }

    public void setBadgeView(LinearLayout badgeView_Add, int num) {
        BadgeView badgeView = new BadgeView(getContext());
        badgeView.setTargetView(badgeView_Add);
        badgeView.setBadgeGravity(Gravity.RIGHT);
        badgeView.setBadgeCount(num);
    }

    private void getProfile() {
        Request request = new Request(UrlHelpper.PERSON_PROFILE, Request.RequestMethod.GET, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", "个人中心" + result);
                ProfileDetails profileDetails = JsonParser.deserializeFromJson(result, ProfileDetails.class);
                ProfileDetails.DataEntity profileDate = profileDetails.getData();
                if (profileDate != null) {
                    MyApplication.setProfileDetails(profileDate);
                    hisCase.setText("你有" + profileDate.getHisCaseNum() + "个历史病历");
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }
}
