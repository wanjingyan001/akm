package cn.zsmy.akm.doctor;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.Evaluation;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * 评论列表
 * Created by wanjingyan
 * on 2015/11/26 09:47.
 */
public class CommentListActivity extends BaseTitleListActivity {
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showContent(EmptyView.State.ing, 1);
                    break;
                case 1:
                    showContent(EmptyView.State.empty, 1);
                    break;
                default:
            }
        }
    };
    private String doctorId;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_comment_list);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeData() {
        setTitle(getString(R.string.comment_list));
        Intent intent = getIntent();
        doctorId = intent.getStringExtra("doctorId");
        getEvaluation(doctorId);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        //设置数据
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        getEvaluation(doctorId);
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    /**
     * 获取医生评价列表
     */
    private void getEvaluation(String doctorId) {
        Request request = new Request(UrlHelpper.DOCTOR_EVALUATION + "?doctorId=" + doctorId, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("code").equals("NO_DATA")) {
                        mHandler.sendEmptyMessage(1);
                    } else if (object.getString("code").equals("SUCC")) {
                        modules.clear();
                        Evaluation evaluation = JsonParser.deserializeFromJson(result, Evaluation.class);
                        List<Evaluation.DataEntity> data = evaluation.getData();
                        modules.addAll(data);
                        adapter.notifyDataSetChanged();
                        if (adapter.getCount() == 0) {
                            mHandler.sendEmptyMessage(1);
                        } else {
                            mHandler.sendEmptyMessageDelayed(0, 500);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
            convertView = LayoutInflater.from(this).inflate(R.layout.layout_evaluation_list_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }


    class Holder extends QBaseViewHolder {
        private TextView mUserTel;//评论中显示的用户手机号
        private TextView mUserComment;//用户评论内容
        private TextView mCommentTime;//评论时间
        private RatingBar mRating;

        @Override
        public void initializeView(View view) {
            mRating = ((RatingBar) view.findViewById(R.id.room_ratingbar));
            mUserTel = ((TextView) view.findViewById(R.id.user_tel_number));
            mUserComment = ((TextView) view.findViewById(R.id.user_evaluation_content));
            mCommentTime = ((TextView) view.findViewById(R.id.evaluation_time));
        }

        @Override
        public void initializeData(int position) {
            getLayoutInflater().inflate(R.layout.layout_evaluationview, null);
            Evaluation.DataEntity dataEntity = (Evaluation.DataEntity) modules.get(position);
            Evaluation.DataEntity.InquirerEntity inquirer = dataEntity.getInquirer();
            if (inquirer != null) {
                mUserTel.setText(inquirer.getName());
                if (dataEntity.getEvaluate() != null) {
                    mUserComment.setText(dataEntity.getEvaluate().toString());
                } else {
                    mUserComment.setText("好评");
                }
                if ((long) dataEntity.getEvaluateTime() != 0) {
                    mCommentTime.setText(DateUtils.getDateToString((long) dataEntity.getEvaluateTime(), DateUtils.TYPE_YMD));
                }
                float evaluateScore = (float) (dataEntity.getEvaluateScore() / 10.0);
                if (evaluateScore == 0) {
                    mRating.setRating(5);
                } else if (evaluateScore > 5) {
                    mRating.setNumStars(5);
                    mRating.setRating(5);
                } else {
                    Log.d("TAG", "》》》》" + evaluateScore + "》》》》" + dataEntity.getEvaluateScore());
                    mRating.setRating(evaluateScore);
                }
                if (dataEntity.getEvaluateTime() != 0) {
                    mCommentTime.setText(DateUtils.getDateToString(dataEntity.getEvaluateTime(), 3));
                }
            }
        }
    }


    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, CommentListActivity.class);
        return intent;
    }

}
