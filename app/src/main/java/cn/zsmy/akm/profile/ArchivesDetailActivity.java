package cn.zsmy.akm.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.PatientCase;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * 档案详情
 * Created by wanjingyan
 * on 2015/12/14 11:37.
 */
public class ArchivesDetailActivity extends BaseTitleListActivity {
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
    private String patientId;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_archives_detail);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        handler.sendEmptyMessageDelayed(0, 1000);
    }


    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDataList(patientId);
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    protected void initializeData() {
        Intent intent = getIntent();
        patientId = intent.getStringExtra("PATIENTID");
        String name = intent.getStringExtra("NAME");
        if (TextUtils.isEmpty(name)) {
        } else {
            setTitle(name + "的档案");
        }
        if (TextUtils.isEmpty(patientId)) {
        } else {
            getDataList(patientId);
        }
    }

    private void getDataList(String patientId) {
        String url = UrlHelpper.PATIENT_CASE_LIST + "?patientId=" + patientId;
        Log.d("TAG", url + ">>>>>>");
        Request request = new Request(url, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                PatientCase patientCase = JsonParser.deserializeFromJson(result, PatientCase.class);
                modules.clear();
                modules.addAll(patientCase.getData());
                if (modules.size() == 0) {
                    handler.sendEmptyMessage(0);
                } else {
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ArchivesDetailActivity.class);
    }

    public static Intent getIntent(Context context, String name, String id) {
        Intent intent = new Intent(context, ArchivesDetailActivity.class);
        intent.putExtra("NAME", name);
        intent.putExtra("PATIENTID", id);
        return intent;
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.layout_archives_detail_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {

        private TextView mDoctorName;
        private TextView mChiefDetail;
        private TextView mSuggestDetail;

        @Override
        public void initializeView(View view) {
            mDoctorName = ((TextView) view.findViewById(R.id.doctor_name));
            mChiefDetail = ((TextView) view.findViewById(R.id.chief_complaint_detail));
            mSuggestDetail = ((TextView) view.findViewById(R.id.suggest_detail));
        }

        @Override
        public void initializeData(int position) {
            PatientCase.DataEntity entity = (PatientCase.DataEntity) modules.get(position);
            PatientCase.DataEntity.DoctorEntity doctor = entity.getDoctor();
            mDoctorName.setText(doctor.getName() + doctor.getProfessionalTitle());
            mChiefDetail.setText(entity.getContent());
            List<PatientCase.DataEntity.CaseAdvicesEntity> caseAdvices = entity.getCaseAdvices();
            if (caseAdvices.size() != 0) {
                String aeger = caseAdvices.get(0).getAeger();
                mSuggestDetail.setText(aeger);
            } else {
                mSuggestDetail.setText(null);
            }
        }
    }
}
