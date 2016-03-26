package cn.zsmy.akm.doctor;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.wei.library.widget.ProgressDialogUtils;
import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.DoctorDetail;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.interrogation.InterrogationInputActivity;
import cn.zsmy.akm.interrogation.PhoneInquiryActivity;
import cn.zsmy.akm.utils.Constants;
import cn.zsmy.akm.utils.StringUtils;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.video.PlayVideoView;

/**
 * Created by sanz on 2015/11/24 13:35
 */
public class DoctorDetailActivity extends BaseTitleActivity implements View.OnClickListener {
    private ImageView mshowIntroduction;
    private TextView mIntroduction;
    private int maxDescripLine = 3; //TextView默认最大展示行数
    private boolean isExpand = false;
    private ImageView mDoctorHeadImg;//医生头像图片
    private TextView mDoctorName, hospital, mJobTitle, mJobTime, mUserTel, mCommentTime, mPrice;//医生名字
    private TextView mDoctorIntroduction;//医生简介
    private String doctorId;
    private TextView recommend;
    private DoctorDetail.DataEntity data;
    private LinearLayout play_line;
    private PlayVideoView playVideoView;
    private ScrollView doctorScroll;
    private TextView recommendNumberType;//指数高或低
    private TextView inquiryPrice;
    private TextView userComment;
    private RatingBar userRating;
    private TextView userCommentContent;
    List<DoctorDetail.DataEntity.PriceEntity> mPriceList;
    private TextView mInQuery;
    private ImageView mInqueryTel;
    private ImageView mPreviewImg;
    private LinearLayout mUserComment;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_doctor_detail);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeData() {
        setTitle(getText(R.string.doctor_introduction));
        Intent intent = this.getIntent();
        doctorId = intent.getStringExtra("doctorId");
        Log.i("TAG", "医生id" + doctorId);
        if (TextUtils.isEmpty(doctorId)) {

        } else {
            getDoctorDetail(doctorId);
        }
    }

    public static Intent getIntent(Context context, String doctorId) {
        Intent intent = new Intent(context, DoctorDetailActivity.class);
        intent.putExtra("doctorId", doctorId);
        return intent;
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        play_line = ((LinearLayout) findViewById(R.id.introduction_video));
        playVideoView = new PlayVideoView(this);
        play_line.addView(playVideoView);
        mDoctorHeadImg = ((ImageView) findViewById(R.id.doctor_head_image));
        mDoctorName = ((TextView) findViewById(R.id.doctor_name));
        hospital = ((TextView) findViewById(R.id.doctor_hospital));
        mJobTitle = ((TextView) findViewById(R.id.job_title));
        mJobTime = ((TextView) findViewById(R.id.job_time));
        mDoctorIntroduction = ((TextView) findViewById(R.id.introduction_centent));
        mUserTel = ((TextView) findViewById(R.id.user_tel_number));
        mUserComment = ((LinearLayout) findViewById(R.id.user_evaluation));
        mCommentTime = ((TextView) findViewById(R.id.evaluation_time));
        recommend = ((TextView) findViewById(R.id.recommend_number));
        recommendNumberType = ((TextView) findViewById(R.id.recommend_number_type));
        inquiryPrice = ((TextView) findViewById(R.id.inquiry_price));
        doctorScroll = ((ScrollView) findViewById(R.id.doctor_scr));
        userComment = ((TextView) findViewById(R.id.user_tel_number));
        userRating = ((RatingBar) findViewById(R.id.room_ratingbar));
        userCommentContent = ((TextView) findViewById(R.id.user_evaluation_content));
        findViewById(R.id.graphic_advisory).setOnClickListener(this);
        findViewById(R.id.phone_advisory).setOnClickListener(this);
        mInQuery = (TextView) findViewById(R.id.inquery_text);
        mInqueryTel = (ImageView) findViewById(R.id.inquery_phone);
        Textshow();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", playVideoView + "");
        playVideoView.restartPlay();
    }

    //获取医生详细信息
    private void getDoctorDetail(String doctorId) {
        String url = UrlHelpper.DOCTOR_DETAILS + "?id=" + doctorId;
        Log.d("TAG", url);
        ProgressDialogUtils.showProgressDialog(this);
        Request request = new Request(url, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                DoctorDetail doctorDetail = JsonParser.deserializeFromJson(result, DoctorDetail.class);
                data = doctorDetail.getData();
                if (data.getName() != null) {
                    mDoctorName.setText(data.getName());
                }
                recommend.setText(String.valueOf(data.getChooseIndex()));
                if (data.getChooseIndex() >= 90) {
                    recommendNumberType.setVisibility(View.VISIBLE);
                } else {
                    recommendNumberType.setVisibility(View.GONE);
                }
                if (data.getHospital() != null) {
                    hospital.setText(data.getHospital());
                }
                mJobTitle.setText(data.getProfessionalTitle());
                mJobTime.setText(data.getWorking());
                mUserTel.setText(StringUtils.getPhone(data.getPhone()));
                mDoctorIntroduction.setText(data.getIntroduc());

                mPriceList = data.getPrice();
                if (mPriceList != null && mPriceList.size() != 0) {
                    String amt = mPriceList.get(0).getAmt();
                    if (amt != null) {
                        String s = amt.replace(amt.substring(amt.indexOf('.'), amt.length()), "元");
                        inquiryPrice.setText(s + "/" + mPriceList.get(0).getUnit());
                    }
                } else {
                    //修改时间；2016/3/1
                    //修改内容：医生简介中，医生电话问诊价格为空时，电话问诊图标变为灰色，不可点击
                    //修改人：yutaotao
                    findViewById(R.id.phone_advisory).setClickable(false);
                    mInQuery.setTextColor(getResources().getColor(R.color.edit_content));
                    mInqueryTel.setImageDrawable(getResources().getDrawable(R.drawable.ic_phone_gray));
                    inquiryPrice.setTextColor(getResources().getColor(R.color.edit_content));
                    inquiryPrice.setText("未开通");
                }
                //医生认证信息中type为4的是医生头像
                List<DoctorDetail.DataEntity.DoctorAuthEntity> doctorAuth = data.getDoctorAuth();
                String headImgPath = null;
                if (doctorAuth != null && doctorAuth.size() > 0) {
                    for (DoctorDetail.DataEntity.DoctorAuthEntity auth : doctorAuth) {
                        if (auth.getZType() != null && auth.getZType().equals("4")) {
                            if (auth.getAuthPic() != null) {
                                headImgPath = auth.getAuthPic();
                                ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + headImgPath, mDoctorHeadImg);
                                System.out.println("医生简介头像：" + auth.getAuthPic());
                            }
                        }
                        //type为6是医生视频的预览图，如果没有则使用医生头像
                        if (auth.getZType() != null) {
                            if (auth.getZType().equals("6") && auth.getAuthPic() != null) {
                                playVideoView.setPreviewPath(auth.getAuthPic());
                            } else if (auth.getZType().equals("4")) {
                                playVideoView.setPreviewPath(auth.getAuthPic());
                            }
                        }

                    }
                }

                if (data.getLastCase() != null) {
                    if (data.getLastCase().getInquirer() != null) {
                        userComment.setText(data.getLastCase().getInquirer().getName());
                        if (!TextUtils.isEmpty(data.getLastCase().getEvaluate())) {
                            userCommentContent.setText(data.getLastCase().getEvaluate());
                        } else {
                            userCommentContent.setText("好评");
                        }
                        float evaluateScore = (float) (data.getLastCase().getEvaluateScore() / 10.0);
                        if (evaluateScore == 0) {
                            userRating.setRating(5);
                        } else if (evaluateScore > 5) {
                            userRating.setNumStars(5);
                            userRating.setRating(5);
                        } else {
                            Log.d("TAG", "》》》》" + evaluateScore + "》》》》" + data.getLastCase().getEvaluateScore());
                            userRating.setRating(evaluateScore);
                        }
                    }
                }
                ProgressDialogUtils.closeProgressDialog();
                loadVideo(data);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    //下载医生介绍视屏
    private void loadVideo(DoctorDetail.DataEntity data) {
        List<DoctorDetail.DataEntity.DoctorAuthEntity> doctorAuth = data.getDoctorAuth();
        for (DoctorDetail.DataEntity.DoctorAuthEntity docAut : doctorAuth) {
            if (docAut.getZType() != null && docAut.getZType().equals("5")) {
                String authPic = docAut.getAuthPic();
                playVideoView.setUrl(UrlHelpper.FILE_IP + authPic);
            }
        }
    }

    private void Textshow() {
        mshowIntroduction = ((ImageView) findViewById(R.id.show_introduction));
        mIntroduction = ((TextView) findViewById(R.id.introduction_centent));
        findViewById(R.id.evaluation_layout).setOnClickListener(this);
        findViewById(R.id.all_evaluation).setOnClickListener(this);
        mIntroduction.setOnClickListener(this);
        mIntroduction.setHeight(mIntroduction.getLineHeight() * maxDescripLine);
        mIntroduction.post(new Runnable() {
            @Override
            public void run() {
                mshowIntroduction.
                        setVisibility(mIntroduction.getLineCount() > maxDescripLine ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (data == null) {
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
        } else {
            String doctorId = data.getId();
            switch (v.getId()) {
                case R.id.introduction_centent:
                    showText();
                    break;
                case R.id.evaluation_layout:
                case R.id.all_evaluation:
                    //跳转至评论列表界面
                    intent = CommentListActivity.getIntent(DoctorDetailActivity.this);
                    intent.putExtra("doctorId", doctorId);
                    startActivity(intent);
                    break;
                case R.id.graphic_advisory:
                    //图文问诊(100积分一次，不够提示)
                    if (doctorId != null) {
//                        Intent toGraphic = GraphicInquiryActivity.getIntent(this, data);
//                        startActivity(toGraphic);
//                        if (MyApplication.getProfileDetails().getScore()>=100){
                        Intent intent1 = InterrogationInputActivity.getIntent(this, 1, data.getId(), Constants.CHAT_FLAG_OF_CONSTACTS_PHOTO_INFORMATION, Constants.CHAT_TYPES_OF_VIP);
                        startActivity(intent1);
//                        }else {
//                            Toast.makeText(this, "积分不足", Toast.LENGTH_SHORT).show();
//                        }
                    } else {
                        Toast.makeText(DoctorDetailActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.phone_advisory:
                    //电话问诊
                    if (doctorId != null) {

                        Intent toGraphic = PhoneInquiryActivity.getIntent(this, data);
                        startActivity(toGraphic);
//                        Intent intent2 = InterrogationInputActivity.getIntent(this, 1, doctorId, Constants.CHAT_FLAG_OF_CONSTACTS_PHONE_INFORMATION, Constants.CHAT_TYPES_OF_VIP);
//                        startActivity(intent2);


                    } else {
                        Toast.makeText(DoctorDetailActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    private void showText() {
        isExpand = !isExpand;
        mIntroduction.clearAnimation(); //清除动画效果
        final int deltaValue;           //默认高度，即前边由maxLine确定的高度
        final int startValue = mIntroduction.getHeight();//起始高度
        int durationMillis = 350;       //动画持续时间
        if (isExpand) {
            /**
             * 折叠动画
             * 从实际高度缩回起始高度
             */
            deltaValue = mIntroduction.getLineHeight() * mIntroduction.getLineCount() - startValue;
            RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(durationMillis);
            animation.setFillAfter(true);
            mshowIntroduction.startAnimation(animation);
        } else {
            /**
             * 展开动画
             * 从起始高度增长至实际高度
             */
            deltaValue = mIntroduction.getLineHeight() * maxDescripLine - startValue;
            RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(durationMillis);
            animation.setFillAfter(true);
            mshowIntroduction.startAnimation(animation);
            doctorScroll.fullScroll(ScrollView.FOCUS_DOWN);
        }
        Animation animation = new Animation() {
            protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                mIntroduction.setHeight((int) (startValue + deltaValue * interpolatedTime));
            }
        };
        animation.setDuration(durationMillis);
        mIntroduction.startAnimation(animation);
    }


    @Override
    protected void onStop() {
        super.onStop();
        playVideoView.pausePlay();
    }
}
