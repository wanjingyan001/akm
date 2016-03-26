package cn.zsmy.akm.doctor.conversation;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.doctor.base.BaseTitleListActivity;
import cn.zsmy.akm.doctor.bean.MyPatients;
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
 * 我的病人
 * Created by wanjingyan
 * on 2015/12/16 16:51.
 */
public class MyPatientActivity extends BaseTitleListActivity {
    private int i = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EmptyView.State state = null;
            switch (msg.what) {
                case 0:
                    state = EmptyView.State.ing;
                    break;
                case 1:
                    state = EmptyView.State.empty;
                    break;
                case 2:
                    state = EmptyView.State.error;
                    break;
            }
            showContent(state, 1);
        }
    };

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_my_patient);
        MyApplication.getInstance().addActivity(this);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mPullToRefreshLsv.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshLsv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    getPatientList(0);
                } else if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                    getPatientList(i++);
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

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.my_patient));
        getPatientList(0);
    }

    private void getPatientList(final int page) {
        Request request = new Request(UrlHelper.MY_PATIENT + "?page=" + page, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("code").equals("SUCC")) {
                        if (page == 0) {
                            modules.clear();
                        }
                        MyPatients myPatients = JsonParser.deserializeFromJson(result, MyPatients.class);
                        List<MyPatients.DataEntity> data = myPatients.getData();
                        modules.addAll(data);
                        if (modules.size() > 0) {
                            handler.sendEmptyMessageDelayed(0, 500);
                        } else {
                            handler.sendEmptyMessageDelayed(1, 2000);
                        }
                    } else {
                        handler.sendEmptyMessageDelayed(1,1000);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    public static Intent getIntent(Context context) {
        return new Intent(context, MyPatientActivity.class);
    }


    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.item_my_patient, null);
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
        private TextView dInquiryCount;
        private TextView dLastTime;

        @Override
        public void initializeView(View view) {
            dPatientImage = ((CircleImageView) view.findViewById(R.id.patient_image));
            dNikeName = ((TextView) view.findViewById(R.id.patient_nike_name));
            dInquiryContent = ((TextView) view.findViewById(R.id.inquiry_content));
            dInquiryCount = ((TextView) view.findViewById(R.id.inquiry_count));
            dLastTime = ((TextView) view.findViewById(R.id.last_inquiry));
            dPatientImage.setBorderColor(getResources().getColor(R.color.edit_stroke));
            dPatientImage.setBorderWidth(1);
        }

        @Override
        public void initializeData(int position) {
            MyPatients.DataEntity entity = (MyPatients.DataEntity) modules.get(position);
            MyPatients.DataEntity.CasesEntity cases = entity.getCases();
            try {
                if (cases != null) {
                    MyPatients.DataEntity.CasesEntity.InquirerEntity inquirer = cases.getInquirer();
                    if (inquirer != null) {
                        if (inquirer.getNickname() != null) {
                            dNikeName.setText(inquirer.getNickname());
                        } else if (inquirer.getPhone() != null) {
                            dNikeName.setText(inquirer.getPhone());
                        }
                    }
                    if (cases.getPatient() != null) {
                        dInquiryCount.setText("向我问诊" + entity.getNum() + "次");
                    }
                    if (cases.getLastChatContent() != null) {
                        dInquiryContent.setText(cases.getLastChatContent());
                    } else if (cases.getContent() != null) {
                        dInquiryContent.setText(cases.getContent());
                    }

                }
                if (cases.getModifyTime() != 0) {
                    dLastTime.setText(DateUtils.getDateToString(cases.getModifyTime(), 2));
                }
                ImageLoader loader = ImageLoader.getInstance();
                MyPatients.DataEntity.CasesEntity.InquirerEntity inquirer = cases.getInquirer();
                if (inquirer != null) {
                    String avatar = inquirer.getAvatar();
                    if (!TextUtils.isEmpty(avatar)) {
                        loader.displayImage(UrlHelper.IP + avatar, dPatientImage);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        try {
            Intent intent = PatientDetailActivity.getIntent(MyPatientActivity.this);
            MyPatients.DataEntity entity = (MyPatients.DataEntity) modules.get(position - 1);
            String patientId = entity.getUserId();
            MyPatients.DataEntity.CasesEntity.PatientEntity patient = entity.getCases().getPatient();
            String name = null;
            if (patient != null) {
                if (entity.getCases().getInquirer() != null) {
                    if (entity.getCases().getInquirer().getNickname() != null) {
                        name = entity.getCases().getInquirer().getNickname();
                    } else if (entity.getCases().getInquirer().getPhone() != null) {
                        name = entity.getCases().getInquirer().getPhone();
                    } else {
                        name = "";
                    }
                }
                intent.putExtra("name", name);
                intent.putExtra("patientId", patientId);
            }
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "数据有误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
}
