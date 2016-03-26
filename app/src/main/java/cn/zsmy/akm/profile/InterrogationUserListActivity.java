package cn.zsmy.akm.profile;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.Interrogation;
import cn.zsmy.akm.entity.Profile;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.StringUtils;
import cn.zsmy.akm.utils.UrlHelpper;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 档案列表
 * Created by qinwei on 2015/11/24 17:25
 */
public class InterrogationUserListActivity extends BaseTitleListActivity {
    private List<Interrogation.InterrogationInfo> infos;
    private String url = UrlHelpper.INTERROGATION_LIST + "?inquirerId=";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                showContent(EmptyView.State.empty, 1);
            } else {
                showContent(EmptyView.State.ing, 1);
            }

        }
    };
    private Profile profile;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_profile_interrogation_user_list);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeData() {
        setTitle(getString(R.string.mProfileInterrogationUserLabel));
        profile = MyApplication.getProfile();
        getInterrogation();
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    /**
     * 获取健康档案
     */
    public void getInterrogation() {
        if (profile != null) {
            String userId = profile.getUserId();
            url = url + userId;
        }
        Request request = new Request(url, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Interrogation interrogation = JsonParser.deserializeFromJson(result, Interrogation.class);
                infos = interrogation.getData();
                modules.clear();
                modules.addAll(infos);
                if (modules.size() == 0) {
                    handler.sendEmptyMessageDelayed(0, 1000);
                } else {
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);

    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.layout_profile_interrogation_user_list_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private CircleImageView mHeadImage;
        private TextView mInterviewName;
        private TextView mInterviewInfo;

        @Override
        public void initializeView(View view) {
            mHeadImage = ((CircleImageView) view.findViewById(R.id.interrogation_head_image));
            mInterviewName = ((TextView) view.findViewById(R.id.interview_name));
            mInterviewInfo = ((TextView) view.findViewById(R.id.interview_info));
        }

        @Override
        public void initializeData(int position) {
            Interrogation.InterrogationInfo info = (Interrogation.InterrogationInfo) modules.get(position);
            mInterviewName.setText(info.getName());
            String sex = null;
            if (info.getGender().equals("0")) {
                sex = "男";
                ImageLoader.getInstance().displayImage("http://img4.imgtn.bdimg.com/it/u=128811874,840272376&fm=21&gp=0.jpg", mHeadImage);
            } else if (info.getGender().equals("1")) {
                sex = "女";
                mHeadImage.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_healthy_dateil_head));
            }
            String[] str = {"（", info.getGender(), "  ", DateUtils.getAge(info.getBirthday()) + "岁", "）"};
            mInterviewInfo.setText(StringUtils.getString(str));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        Interrogation.InterrogationInfo info = (Interrogation.InterrogationInfo) modules.get(position - 1);
        if (!TextUtils.isEmpty(info.getName()) && !TextUtils.isEmpty(info.getInquirerId())) {
            startActivity(ArchivesDetailActivity.getIntent(this, info.getName(), info.getId()));
        } else {
            Toast.makeText(this, "缺少数据", Toast.LENGTH_LONG).show();
        }

    }
}
