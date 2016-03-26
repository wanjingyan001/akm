package cn.zsmy.akm.doctor.conversation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.ProgressDialogUtils;
import cn.zsmy.akm.doctor.adapter.PostAdapter;
import cn.zsmy.akm.doctor.base.BaseTitleListActivity;
import cn.zsmy.akm.doctor.chat.utils.Trace;
import cn.zsmy.akm.doctor.conversation.bean.ReplyList;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.JsonParser;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.http.StringCallback;
import cn.zsmy.akm.doctor.learning.bean.PatientCase;
import cn.zsmy.akm.doctor.shared.PicturelookActivity;
import cn.zsmy.akm.doctor.utils.DateUtils;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.utils.Utils;
import cn.zsmy.doctor.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 帖子详情
 * Created by wanjingyan
 * on 2015/12/17 09:43.
 */
public class PostDetailActivity extends BaseTitleListActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private TextView postTitle;
    private CircleImageView postHead;
    private TextView postedName;
    private TextView postDate;
    private TextView postContent;
    private CheckBox forumCB;
    private SharedPreferences preferences;
    private GridView postDetailGrid;
    private PostAdapter imageAdapter;
    private PatientCase.DataEntity post;
    private int i = 1;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_post_detail);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        setTitle(getText(R.string.post_detail));
        findViewById(R.id.refresh).setOnClickListener(this);
        findViewById(R.id.collect).setOnClickListener(this);
        forumCB = ((CheckBox) findViewById(R.id.forum_collect_cb));
        forumCB.setClickable(false);
        forumCB.setOnClickListener(this);
        forumCB.setOnCheckedChangeListener(this);
        findViewById(R.id.reply).setOnClickListener(this);
        mPullToRefreshLsv.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshLsv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    getReplyList(0);
                } else if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                    getReplyList(i++);
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
        Intent intent = getIntent();
        post = ((PatientCase.DataEntity) intent.getSerializableExtra("POST"));
        if (post != null) {
            postedName.setText(post.getNickname());
            postDate.setText(DateUtils.getDateToString(post.getCreateTime(), 3));
            postTitle.setText(post.getTitle());
            postContent.setText(post.getContent());
            if (post != null && post.getAvatar() != null) {
                ImageLoader.getInstance().displayImage(UrlHelper.IP + post.getAvatar(), postHead);
            }
            preferences = getSharedPreferences(post.getId(), MODE_PRIVATE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getReplyList(0);
        if (preferences != null) {
            forumCB.setChecked(preferences.getBoolean("isLiked", false));
            if (forumCB.isChecked()) {
                forumCB.setClickable(false);
            }
        }
    }

    @Override
    protected void addRefreshHeaderView(ListView refreshableView) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_post_head, null);
        postTitle = (TextView) inflate.findViewById(R.id.post_title);
        postHead = (CircleImageView) inflate.findViewById(R.id.posted_head_image);
        postedName = (TextView) inflate.findViewById(R.id.posted_name);
        postDate = (TextView) inflate.findViewById(R.id.post_date);
        postContent = (TextView) inflate.findViewById(R.id.post_content);
        postDetailGrid = ((GridView) inflate.findViewById(R.id.post_detail_grid));//帖子详情图片
        postDetailGrid.setVisibility(View.VISIBLE);
        refreshableView.addHeaderView(inflate);
    }

    public static Intent getIntent(Context context, PatientCase.DataEntity patientCase) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra("POST", patientCase);
        return intent;
    }


    private void getReplyList(final int page) {
        Request request = new Request(UrlHelper.POST_REPLY_LIST + "?id=" + post.getId() + "&size=10" + "&page=" + page, this);
        Trace.d(UrlHelper.POST_REPLY_LIST + "?id=" + post.getId() + "&size=10" + "&page=" + page);
        ProgressDialogUtils.showProgressDialog(this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(post.getPics())) {
                    loadImages(post.getPics());
                }
                try {
                    JSONObject object = new JSONObject(result);
                    String code = object.getString("code");
                    if (code.equals("SUCC")) {
                        if (page == 0) {
                            modules.clear();
                            i = 1;
                        }
                        ReplyList replyList = JsonParser.deserializeFromJson(result, ReplyList.class);
                        if (replyList != null && replyList.getData().size() != 0) {
                            modules.addAll(replyList.getData());
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        ProgressDialogUtils.closeProgressDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ProgressDialogUtils.closeProgressDialog();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    private void loadImages(final String pics) {
        String[] pic = pics.split(",");
        imageAdapter = new PostAdapter(this, pic);
        postDetailGrid.setAdapter(imageAdapter);
        Utils.setListViewHeightBasedOnChildren(postDetailGrid);
        imageAdapter.notifyDataSetChanged();
        postDetailGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = imageAdapter.getItem(position);
                startActivity(PicturelookActivity.getIntent(PostDetailActivity.this, item, null, pics, position));
                Log.d("TAG", "路径：" + item);
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refresh:
                //刷新
//                ProgressDialogUtils.showProgressDialog(this);
                getReplyList(0);
                break;
            case R.id.collect:
                //收藏
                Log.d("TAG", "----" + forumCB.isChecked());
                like(post.getId());
//                if (!forumCB.isChecked()) {
//                }
                break;
            case R.id.reply:
                //回复
                String nickname = post.getNickname();
                Intent intent = ReplyPostActivity.getIntent(this, nickname, post.getId(), post.getTitle());
                startActivity(intent);
                break;
            case R.id.forum_collect_cb:
                Log.d("TAG", "----" + forumCB.isChecked());
                like(post.getId());
                break;
        }
    }


    private void like(String postId) {
        Request request = new Request(UrlHelper.POST_LIKE, Request.RequestMethod.POST, this);
        request.put("topicId", postId);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                forumCB.setChecked(true);
                forumCB.setClickable(false);
                Toast.makeText(PostDetailActivity.this, "点赞成功", Toast.LENGTH_SHORT).show();
                onRefresh(true);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }



    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.item_post_reply, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("TAG", "----" + forumCB.isChecked());
    }


    class Holder extends QBaseViewHolder implements View.OnClickListener {

        private CircleImageView huitieImage;
        private TextView huitieName;
        private TextView huitieTime;
        private TextView huitieContent;
        private ReplyList.DataEntity replyListEntity;
        private TextView replyFor;

        @Override
        public void initializeView(View view) {
            huitieImage = ((CircleImageView) view.findViewById(R.id.huitie_image));
            huitieName = ((TextView) view.findViewById(R.id.huitie_name));
            huitieTime = ((TextView) view.findViewById(R.id.huitie_time));
            replyFor = ((TextView) view.findViewById(R.id.reply_for));
            replyFor.setOnClickListener(this);
            huitieContent = ((TextView) view.findViewById(R.id.huitie_cotent));
        }

        @Override
        public void initializeData(int position) {
            replyListEntity = (ReplyList.DataEntity) modules.get(position);
            huitieContent.setText(replyListEntity.getContent());
            huitieName.setText(replyListEntity.getNickname());
            long modifyTime = replyListEntity.getModifyTime();
            long now = System.currentTimeMillis();
            if ((now - modifyTime) < (1000 * 60)) {
                huitieTime.setText((position + 1) + "楼 \t" + "刚刚");
            } else if ((now - modifyTime) < (1000 * 60 * 60)) {
                long time = (now - modifyTime) / (1000 * 60);
                huitieTime.setText((position + 1) + "楼 \t" + String.valueOf(time) + "分钟前");
            } else if ((now - modifyTime) < (1000 * 60 * 60 * 24)) {
                long hour = (now - modifyTime) / (1000 * 60 * 60);
                huitieTime.setText((position + 1) + "楼 \t" + String.valueOf(hour) + "小时前");
            } else {
                huitieTime.setText((position + 1) + "楼 \t" + DateUtils.getDateToString(modifyTime, 3));
            }
            if (!TextUtils.isEmpty(replyListEntity.getAvatar())) {
                ImageLoader.getInstance().displayImage(UrlHelper.IP + replyListEntity.getAvatar(), huitieImage);
            }
            if (replyListEntity.getCreator().equals(MyApplication.getEntity().getId())) {
                replyFor.setVisibility(View.INVISIBLE);
            }
        }


        @Override
        public void onClick(View v) {
            //回复
            String nickname = replyListEntity.getNickname();
            Intent intent = ReplyPostActivity.getIntent(PostDetailActivity.this, nickname, post.getId(), post.getTitle());
            intent.putExtra("COMMENT", "comment");
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("isLiked", forumCB.isChecked());
        edit.commit();
    }
}
