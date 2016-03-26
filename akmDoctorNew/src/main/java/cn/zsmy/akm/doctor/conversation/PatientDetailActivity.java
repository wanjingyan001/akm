package cn.zsmy.akm.doctor.conversation;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.doctor.admissions.bean.Contact;
import cn.zsmy.akm.doctor.base.BaseTitleListActivity;
import cn.zsmy.akm.doctor.chat.ChatActivity;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.utils.DateUtils;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 病人的病历
 * Created by Administrator on 2015/12/19.
 */
public class PatientDetailActivity extends BaseTitleListActivity {
    private String patientId;
    private String name;
    private int i = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    showContent();
                    break;
                case 1:
                    showContent(EmptyView.State.empty, 1);
                    break;
            }
        }
    };

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_my_patient);
        MyApplication.getInstance().addActivity(this);
    }


    @Override
    protected void initializeView() {
        super.initializeView();
        mPullToRefreshLsv.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshLsv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    getPatientCase(0);
                } else if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                    getPatientCase(i++);
                }
                mPullToRefreshLsv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshLsv.onRefreshComplete();
                    }
                }, 1000);
            }
        });
    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        if (TextUtils.isEmpty(name)) {
            setTitle("患者的问诊历史");
        } else {
            setTitle(name + "的问诊历史");
        }
        patientId = intent.getStringExtra("patientId");
        if (patientId != null) {
            getPatientCase(0);
        } else {
            Toast.makeText(this, "数据有误", Toast.LENGTH_SHORT).show();
        }

    }

    private void getPatientCase(final int page) {
        Request request = new Request(UrlHelper.PATIENT_CASE + patientId + "&page=" + page, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", result);
                if (page == 0) {
                    modules.clear();
                }
                Contact detail = JsonParser.deserializeFromJson(result, Contact.class);
                List<Contact.DataEntity> data = detail.getData();
                modules.addAll(data);
                if (modules.size() == 0) {
                    handler.sendEmptyMessage(1);
                } else {
                    handler.sendEmptyMessage(0);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    public static Intent getIntent(Context context) {
        return new Intent(context, PatientDetailActivity.class);
    }


    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.item_patient_detail, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private CircleImageView dPatientImage;
        private TextView dNikeName;
        private TextView dInquiryContent;
        private TextView dLastTime;

        @Override
        public void initializeView(View view) {
            dPatientImage = ((CircleImageView) view.findViewById(R.id.patient_image));
            dNikeName = ((TextView) view.findViewById(R.id.patient_info));
            dInquiryContent = ((TextView) view.findViewById(R.id.inquiry_content));
            dLastTime = ((TextView) view.findViewById(R.id.last_inquiry));
            dPatientImage.setBorderColor(getResources().getColor(R.color.edit_stroke));
            dPatientImage.setBorderWidth(1);
        }

        @Override
        public void initializeData(int position) {
            try {
                Contact.DataEntity entity = (Contact.DataEntity) modules.get(position);
                Contact.DataEntity.PatientEntity patient = entity.getPatient();
                if (patient != null) {
                    long birthday = patient.getBirthday();
                    dNikeName.setText("为谁问诊：" + patient.getName() + "\t" + patient.getGender() + "\t" + DateUtils.getAge(birthday) + "岁");
                }
                ImageLoader loader = ImageLoader.getInstance();
                Contact.DataEntity.DoctorEntity doctor = entity.getDoctor();
                if (doctor != null) {
                    loader.displayImage(UrlHelper.IP + doctor.getAvatar(), dPatientImage);
                }
                if (null != entity.getLastChatContent()) {
                    dInquiryContent.setText(entity.getLastChatContent());
                } else {
                    if (null != entity.getContent()) {
                        dInquiryContent.setText(entity.getContent());
                    }
                }
                dLastTime.setText(DateUtils.getDateToString(entity.getModifyTime(), 2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        //病人的详细病例
        Log.i("TAG", position + "");
        Contact.DataEntity dataEntity = (Contact.DataEntity) modules.get(position - 1);
        if (dataEntity != null && dataEntity.getId() != null && dataEntity.getInquirer() != null && dataEntity.getStatus() != null) {
            Intent intent = ChatActivity.getIntent(this, dataEntity);
            intent.putExtra("CASESTATUS", dataEntity.getStatus());
            startActivity(intent);
        } else {
            Toast.makeText(this, "无此病例", Toast.LENGTH_LONG).show();
        }

    }
}
