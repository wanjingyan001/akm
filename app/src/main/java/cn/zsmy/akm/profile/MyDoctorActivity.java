package cn.zsmy.akm.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.doctor.DoctorDetailActivity;
import cn.zsmy.akm.entity.MyDoctors;
import cn.zsmy.akm.entity.Profile;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.UrlHelpper;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的医生
 */
public class MyDoctorActivity extends BaseTitleListActivity {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    showContent(EmptyView.State.empty, 1);
                    break;
                case 1:
                    showContent();
                    break;
            }
        }
    };
    private Profile profile;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_my_doctor);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
    }

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.my_doctor));
        getMyDoctorList();
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, MyDoctorActivity.class);
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        getMyDoctorList();
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }


    /**
     * 获取我的医生
     */
    public void getMyDoctorList() {
        profile = MyApplication.getProfile();
        Request request = new Request(UrlHelpper.MY_DOCTORS + profile.getUserId(), this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                MyDoctors myDoctors = JsonParser.deserializeFromJson(result, MyDoctors.class);
                modules.clear();
                modules.addAll(myDoctors.getData());
                if (modules.size() == 0) {
                    handler.sendEmptyMessage(0);
                } else {
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        MyDoctors.DataEntity dataEntity = (MyDoctors.DataEntity) modules.get(position - 1);
        String doctorId = dataEntity.getDoctorId();
        startActivity(DoctorDetailActivity.getIntent(this, doctorId));
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.layout_my_doctor_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }


    class Holder extends QBaseViewHolder {
        private CircleImageView mDocImg;
        private TextView mDocName;
        private TextView mDocPost;
        private TextView mDocHospital;
        private TextView mInquiryCount;

        @Override
        public void initializeView(View view) {
            mDocImg = ((CircleImageView) view.findViewById(R.id.doctoe_head_portrait));
            mDocName = ((TextView) view.findViewById(R.id.doctor_name));
            mDocPost = ((TextView) view.findViewById(R.id.doctor_post));
            mDocHospital = ((TextView) view.findViewById(R.id.hospital));
            mInquiryCount = ((TextView) view.findViewById(R.id.inquiry_count));
        }

        @Override
        public void initializeData(int position) {
            try {
                MyDoctors.DataEntity dataEntity = (MyDoctors.DataEntity) modules.get(position);
                MyDoctors.DataEntity.CasesEntity cases = dataEntity.getCases();
                String name = cases.getDoctor().getName();
                mDocName.setText(name);
                mDocPost.setText(cases.getDoctor().getProfessionalTitle());
                mInquiryCount.setText("向ta问诊" + dataEntity.getNum() + "次");
                mDocHospital.setText(cases.getDoctor().getHospital());
                ImageLoader imageLoader = ImageLoader.getInstance();
                if (cases.getDoctor().getAvatar() != null) {
                    imageLoader.displayImage(UrlHelpper.FILE_IP + cases.getDoctor().getAvatar(), mDocImg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
