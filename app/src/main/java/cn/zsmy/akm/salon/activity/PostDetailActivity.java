package cn.zsmy.akm.salon.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.widget.ProgressDialogUtils;
import cn.zsmy.akm.BaseTitleListActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.PostDetails;
import cn.zsmy.akm.entity.Posts;
import cn.zsmy.akm.entity.ReplyList;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.http.Trace;
import cn.zsmy.akm.salon.activity.adapter.PostAdapter;
import cn.zsmy.akm.salon.activity.choosePhoto.ForumReleaseActivity;
import cn.zsmy.akm.shared.PicturelookActivity;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.Is_Login;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 帖子详情
 * Created by wanjingyan
 * on 2015/11/30 17:18.
 */
public class PostDetailActivity extends BaseTitleListActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private PostDetails postDetails;
    private TextView mForumName;
    private TextView mForumTime;
    private TextView mCollectors;
    private TextView mBuyer;
    private TextView mForumTitle;
    private TextView mForumContent;
    private TextView mCollectorsName;
    private ImageButton refreshBtn;
    private ImageButton replyBtn;
    private GridView postDetailGrid;
    private PostAdapter postAdapter;
    private CheckBox forumCB;
    private SharedPreferences preferences;
    private PostAdapter imageAdapter;
    private CircleImageView mForumHeadImg;
    private PostDetails.DataEntity data;
    private int i = 1;
    private Posts.DataEntity post;
    private String postid;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_forum_detail);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        setTitle(getText(R.string.post_detail));
        refreshBtn = ((ImageButton) findViewById(R.id.forum_refresh));
        refreshBtn.setOnClickListener(this);
        findViewById(R.id.forum_collect).setOnClickListener(this);
        replyBtn = ((ImageButton) findViewById(R.id.forum_reply));
        forumCB = ((CheckBox) findViewById(R.id.forum_collect_cb));
        forumCB.setClickable(false);
        forumCB.setOnClickListener(this);
        forumCB.setOnCheckedChangeListener(this);
        replyBtn.setOnClickListener(this);
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
        post = ((Posts.DataEntity) getIntent().getSerializableExtra("POST"));
        postid = getIntent().getStringExtra("POSTID");
        loadPostDetail(0);
        preferences = getSharedPreferences(post.getId(), MODE_PRIVATE);
        forumCB.setChecked(preferences.getBoolean("isLiked", false));

    }

    @Override
    protected void onResume() {
        super.onResume();
        getReplyList(0);
        forumCB.setChecked(preferences.getBoolean("isLiked", false));
        if (forumCB.isChecked()) {
            forumCB.setClickable(false);
        }
    }

    private void loadPostDetail(final int page) {
        String url = null;
        if (postid == null) {
            url = UrlHelpper.POST_DETAIL_URL + "?id=" + post.getId() + "&size=10" + "&page=" + page;
        } else {
            url = UrlHelpper.POST_DETAIL_URL + "?id=" + postid + "&size=10" + "&page=" + page;
        }
        Request request = new Request(url, Request.RequestMethod.GET, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                postDetails = JsonParser.deserializeFromJson(result, PostDetails.class);
                data = postDetails.getData();
                init();
                ProgressDialogUtils.closeProgressDialog();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    private void getReplyList(final int page) {
        String url = null;
        if (postid == null) {
            url = UrlHelpper.POST_REPLY_LIST + "?id=" + post.getId() + "&size=10" + "&page=" + page;
        } else {
            url = UrlHelpper.POST_REPLY_LIST + "?id=" + postid + "&size=10" + "&page=" + page;
        }
        Trace.d(url);
        Request request = new Request(url, this);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Trace.d(result);
                if (post != null && post.getPics() != null) {
                    loadImages(post.getPics());
                } else {
                    loadImages(data.getPics());
                }
                if (page == 0) {
                    modules.clear();
                    i = 1;
                }
                ReplyList replyList = JsonParser.deserializeFromJson(result, ReplyList.class);
                modules.addAll(replyList.getData());
                adapter.notifyDataSetChanged();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    private void init() {
        mForumName.setText(data.getNickname());
        mForumTime.setText(DateUtils.getDateToString(data.getCreateTime(), 3));
        mCollectors.setText(String.valueOf(data.getSupportNum()));
        mBuyer.setText(String.valueOf(data.getVisitNum()));
        mForumTitle.setText(data.getTitle());
        mForumContent.setText(data.getContent());
        if (post != null && post.getAvatar() != null) {
            ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + post.getAvatar(), mForumHeadImg);
        }
        // TODO: 点赞人列表
        List<PostDetails.DataEntity.TopicSupportEntity> topicSupport = data.getTopicSupport();
        if (topicSupport.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (PostDetails.DataEntity.TopicSupportEntity topic : topicSupport) {
                buffer.append(topic.getNickname() + ",");
            }
            String str = buffer.substring(0, buffer.lastIndexOf(","));
            mCollectorsName.setText(str);
        }
        ProgressDialogUtils.closeProgressDialog();

    }

    private void loadImages(final String pics) {
        String[] pic = pics.split(",");
        imageAdapter = new PostAdapter(this, pic);
        imageAdapter.notifyDataSetChanged();
        postDetailGrid.setAdapter(imageAdapter);
//        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        int width = wm.getDefaultDisplay().getWidth();
//        int px = DensityUtil.dip2px(this, 115);
//        AutoCountInRow countInRow = AutoCountInRow.calculateColumnWidthAndCountInRow(width, px, 5);
//        int dip = DensityUtil.px2dip(this, countInRow.width);
//        postDetailGrid.setColumnWidth(dip);
//        postDetailGrid.setNumColumns(countInRow.countInRow);
        Utils.setListViewHeightBasedOnChildren(postDetailGrid);
        imageAdapter.notifyDataSetChanged();
        postDetailGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = imageAdapter.getItem(position);
//                startActivity(ChatPictureLookActivity.getIntent(PostDetailActivity.this, item, null));
                startActivity(PicturelookActivity.getIntent(PostDetailActivity.this, item, null, pics, position));
                Log.d("TAG", "路径：" + item);
            }
        });

    }

    @Override
    protected void addRefreshHeaderView(ListView refreshableView) {
        //帖子详细内容
        View view = LayoutInflater.from(this).inflate(R.layout.activity_post_detail_head, null);
        mForumHeadImg = (CircleImageView) view.findViewById(R.id.forum_detail_head_img);//发帖人头像
        mForumName = (TextView) view.findViewById(R.id.forum_name);//发帖人名字
        mForumTime = (TextView) view.findViewById(R.id.forum_time);//发帖时间
        mCollectors = (TextView) view.findViewById(R.id.collectors_count);//点赞人数
        mBuyer = (TextView) view.findViewById(R.id.buyers_count);//查阅人数
        mForumTitle = (TextView) view.findViewById(R.id.detail_post_title);//帖子标题
        mForumContent = (TextView) view.findViewById(R.id.detail_post_content);//帖子内容
        mCollectorsName = (TextView) view.findViewById(R.id.collectors_name);//点赞人名字
        postDetailGrid = ((GridView) view.findViewById(R.id.post_detail_grid));//帖子详情图片
        postDetailGrid.setVisibility(View.VISIBLE);
        refreshableView.addHeaderView(view);
    }


    public static Intent getIntent(Context context, Posts.DataEntity entity) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra("POST", entity);
        return intent;
    }


    public static Intent getIntent(Context context, String postId) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra("POSTID", postId);
        return intent;
    }


    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(this).inflate(R.layout.layout_forum_detail_item, null);
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
        Log.d("TAG", "buttonchange----" + forumCB.isChecked());
    }


    class Holder extends QBaseViewHolder implements View.OnClickListener {
        private CircleImageView mForumDetailHeadImg;//帖子详情回帖人头像
        private TextView mReplies;//回帖人名字
        private TextView mRepliesTime;//回帖时间和楼层
        private TextView mReplyDetail;//回帖内容
        private TextView reply;
        private int item;
        private int position;

        @Override
        public void initializeView(View view) {
            mForumDetailHeadImg = ((CircleImageView) view.findViewById(R.id.forum_detail_head_img));
            mReplies = ((TextView) view.findViewById(R.id.replies_popple));
            mRepliesTime = ((TextView) view.findViewById(R.id.replies_time));
            mReplyDetail = ((TextView) view.findViewById(R.id.reply_detail));
            reply = ((TextView) view.findViewById(R.id.reply));

        }

        @Override
        public void initializeData(int position) {
            this.position = position;
            ReplyList.DataEntity replyListEntity = (ReplyList.DataEntity) modules.get(position);
            mReplyDetail.setText(replyListEntity.getContent());
            long modifyTime = replyListEntity.getModifyTime();
            long now = System.currentTimeMillis();
            if ((now - modifyTime) < (1000 * 60)) {
                mRepliesTime.setText((position + 1) + "楼 \t" + "刚刚");
            } else if ((now - modifyTime) < (1000 * 60 * 60)) {
                long time = (now - modifyTime) / (1000 * 60);
                mRepliesTime.setText((position + 1) + "楼 \t" + String.valueOf(time) + "分钟前");
            } else if ((now - modifyTime) < (1000 * 60 * 60 * 24)) {
                long hour = (now - modifyTime) / (1000 * 60 * 60);
                mRepliesTime.setText((position + 1) + "楼 \t" + String.valueOf(hour) + "小时前");
            } else {
                mRepliesTime.setText((position + 1) + "楼 \t" + DateUtils.getDateToString(modifyTime, 3));
            }
            item = position;
            reply.setOnClickListener(this);
            mReplies.setText(replyListEntity.getNickname());
            if (!TextUtils.isEmpty(replyListEntity.getAvatar())) {
                ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + replyListEntity.getAvatar(), mForumDetailHeadImg);
            }
            if (MyApplication.getUserInfo() != null) {
                if (MyApplication.getUserInfo().getId().equals(replyListEntity.getCreator())) {
                    reply.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.reply:
                    //回复
                    PostDetails.DataEntity.ReplyListEntity replyListEntity = (PostDetails.DataEntity.ReplyListEntity) modules.get(position);
                    String nickname = replyListEntity.getNickname();
                    Intent intent = ReplyPostActivity.getIntent(PostDetailActivity.this, nickname, data.getId(), data.getTitle());
                    intent.putExtra("COMMENT", "comment");
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.forum_refresh) {
            //刷新
            ProgressDialogUtils.showProgressDialog(this);
            loadPostDetail(0);
        } else {
            switch (v.getId()) {
//                case R.id.forum_refresh:
//                    //刷新
//                    ProgressDialogUtils.showProgressDialog(this);
//                    break;
                case R.id.forum_collect:
                    //点赞
                    like(data.getId());
                    break;
                case R.id.forum_reply:
                    //回复
                    boolean loginStatus = Is_Login.getLoginStatus(this);
                    String nickname = data.getNickname();
                    if (loginStatus) {
                        Intent intent = ReplyPostActivity.getIntent(this, nickname, data.getId(), data.getTitle());
                        startActivity(intent);
                    }
                    break;
                case R.id.forum_collect_cb:
                    like(data.getId());
                    break;
            }
        }
    }


    private void like(String postId) {
        Request request = new Request(UrlHelpper.POST_LIKE, Request.RequestMethod.POST, this);
        request.put("topicId", postId);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                forumCB.setChecked(true);
                forumCB.setClickable(false);
                Toast.makeText(PostDetailActivity.this, "点赞成功", Toast.LENGTH_SHORT).show();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.menu.menu_add_post) {
            //分享
            startActivity(ForumReleaseActivity.getIntent(PostDetailActivity.this));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("isLiked", forumCB.isChecked());
        edit.commit();
    }
}
