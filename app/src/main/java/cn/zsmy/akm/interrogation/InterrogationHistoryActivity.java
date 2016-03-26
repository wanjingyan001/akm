package cn.zsmy.akm.interrogation;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.HistoryCase;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.UrlHelpper;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 问诊历史
 * Created by sanz on 2015/11/24 13:22
 */
public class InterrogationHistoryActivity extends BaseTitleListActivity {
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            EmptyView.State state = null;
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
    private int i = 1;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_interrogation_history);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.historical_records));
        mPullToRefreshLsv.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshLsv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (MyApplication.getProfile() != null && MyApplication.getProfile().getUserId() != null) {
                    if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                        loadHistoryCaseList(0);
                    } else if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                        loadHistoryCaseList(i++);
                    }
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
    protected void initializeView() {
        super.initializeView();
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHistoryCaseList(0);
    }

    private void loadHistoryCaseList(final int page) {
        if (MyApplication.getProfile() != null) {
            Request request = new Request(UrlHelpper.CASE_HISTORY + "?creator=" + MyApplication.getProfile().getUserId() + "&page=" + page, Request.RequestMethod.GET, this);
            request.setCallback(new StringCallback() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        String code = object.getString("code");
                        if (code.equals("SUCC")) {
                            if (page == 0) {
                                modules.clear();
                            }
                            HistoryCase historyCase = JsonParser.deserializeFromJson(result, HistoryCase.class);
                            List<HistoryCase.DataEntity> data = historyCase.getData();
                            modules.addAll(data);
                            adapter.notifyDataSetChanged();
                            if (modules.size() == 0) {
                                mHandler.sendEmptyMessage(1);
                            } else {
                                mHandler.sendEmptyMessageDelayed(0, 1000);
                            }
                        } else {
                            mHandler.sendEmptyMessage(1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        HistoryCase.DataEntity DataEntity = (HistoryCase.DataEntity) modules.get(position - 1);
        if (DataEntity.getId() != null && DataEntity.getDoctor() != null && DataEntity.getDoctor().getId() != null) {
            String zType = DataEntity.getZType();
            Intent intent = InterrogationChatActivity.getIntent(this, DataEntity.getId(), DataEntity.getDoctor().getId(), Constants.HISTORY_CASE_VALUSE);
            Log.d("TAG", zType + "==========11");
            intent.putExtra("zType", zType);
            startActivity(intent);
        } else {
            Toast.makeText(this, "缺少必要参数", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.layout_historical_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    class Holder extends QBaseViewHolder {
        private CircleImageView mDoctorImg;
        private TextView mDoctorName;
        private TextView mSuggest;
        private TextView mSuggestTime;
        private TextView mSuggestType;

        @Override
        public void initializeView(View view) {
            mDoctorImg = ((CircleImageView) view.findViewById(R.id.doctor_head_img));
            mDoctorName = ((TextView) view.findViewById(R.id.historical_doctor_name));
            mSuggest = ((TextView) view.findViewById(R.id.doctor_suggest_content));
            mSuggestTime = ((TextView) view.findViewById(R.id.doctor_suggest_time));
            mSuggestType = ((TextView) view.findViewById(R.id.suggest_type));
        }

        @Override
        public void initializeData(int position) {
            Log.d("cn.zsmy", modules.size() + "---------");
            HistoryCase.DataEntity dataEntity = (HistoryCase.DataEntity) modules.get(position);
            HistoryCase.DataEntity.DoctorEntity doctor = dataEntity.getDoctor();
            if (doctor != null) {
                mDoctorName.setText(doctor.getName() + doctor.getProfessionalTitle());
            }
            if (dataEntity.getLastChatContent() != null) {
                mSuggest.setText(dataEntity.getLastChatContent());
            } else {
                mSuggest.setText(dataEntity.getContent());
            }
            mSuggestTime.setText(DateUtils.getDateToString(dataEntity.getCreateTime(), 2));
            if (dataEntity.getEvaluateTime() != null) {
                mSuggestType.setText("复诊");
                mSuggestType.setTextColor(getResources().getColor(R.color.font_color));
            } else {
                mSuggestType.setText("未评价");
                mSuggestType.setTextColor(getResources().getColor(R.color.red));
            }
            if (dataEntity.getDoctor() != null) {
                ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + dataEntity.getDoctor().getAvatar(), mDoctorImg);
            }
        }
    }
}
