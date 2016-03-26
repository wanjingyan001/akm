package cn.zsmy.akm.salon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.fragment.BaseListFragment;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.Posts;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.salon.activity.PostDetailActivity;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.UrlHelpper;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 帖子列表fragment
 * Created by wanjingyan
 * on 2015/11/30 15:13.
 */
public class PostListFragment extends BaseListFragment {
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showContent();
                    break;
                case 1:
                    showContent(EmptyView.State.empty);
                    break;
                case 2:
                    showContent();
                    if (mPullToRefreshLsv.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                        Log.d("TAG", mPullToRefreshLsv.getRefreshableView().getFooterViewsCount() + "---------");
                        if (mPullToRefreshLsv.getRefreshableView().getFooterViewsCount() == 1) {
                            inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_foot_no_data, null);
                            mPullToRefreshLsv.getRefreshableView().addFooterView(inflate);
                            adapter.notifyDataSetChanged();
                            mPullToRefreshLsv.getRefreshableView().setSelection(ListView.FOCUS_DOWN);
                        }
                    }

                    getPostList(0);
                    mPullToRefreshLsv.onRefreshComplete();
                    break;
            }

        }
    };
    private static String TAG = "MyApplication";
    private String platform;
    private int sta;
    private String url;
    private int i = 1;
    private View inflate;


    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_post_list;
    }


    public static PostListFragment newInstance(int page, String platform) {
        PostListFragment postListFragment = new PostListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("sta", page);
        bundle.putString("platform", platform);
        postListFragment.setArguments(bundle);
        return postListFragment;
    }

    @Override
    protected void initializeView(View v) {
        super.initializeView(v);
        Bundle arguments = getArguments();
        if (arguments != null) {
            platform = arguments.getString("platform");
            sta = arguments.getInt("sta");
        }
        mPullToRefreshLsv.setMode(PullToRefreshBase.Mode.BOTH);
        //下拉刷新，获取新帖子；上拉加载，获取更多
        mPullToRefreshLsv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    getPostList(0);
                } else if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                    getPostList(i++);
                }
                mPullToRefreshLsv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshLsv.onRefreshComplete();
                    }
                }, 1000);
            }
        });
        mPullToRefreshLsv.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), true, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        getPostList(0);
    }

    private void getPostList(final int page) {
        if (sta == 0) {
            url = UrlHelpper.POST_LIST + "forumPlateId=" + platform + "&flag=" + 0 + "&page=" + page + "&size=" + 10;
        } else {
            url = UrlHelpper.POST_LIST + "forumPlateId=" + platform + "&status=" + sta + "&flag=" + 0 + "&page=" + page + "&size=" + 10;
        }
        Log.d("TAG", url);
        Request request = new Request(url, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Posts posts = JsonParser.deserializeFromJson(result, Posts.class);
                if (page == 0) {
                    modules.clear();
                }
                modules.addAll(posts.getData());
                adapter.notifyDataSetChanged();
                if (adapter.getCount() == 0) {
                    mHandler.sendEmptyMessage(1);
                } else {
                    mHandler.sendEmptyMessageDelayed(0, 2000);
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
            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_post_list_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }


    class Holder extends QBaseViewHolder {
        private TextView mPostTitle;//帖子标题
        private TextView mPostContent;//帖子内容
        private CircleImageView mPostedHead;//发帖人头像
        private TextView mPostedName;//发帖人名字
        private TextView mPostTime;//发帖时间
        private TextView mCommentNumber;//收藏人数
        private TextView mExamineNumber;//帖子查阅人数
        private ImageView mPostImg;//

        @Override
        public void initializeView(View view) {
            mPostTitle = ((TextView) view.findViewById(R.id.post_title));
            mPostContent = ((TextView) view.findViewById(R.id.post_content));
            mPostedHead = ((CircleImageView) view.findViewById(R.id.posted_img));
            mPostedName = ((TextView) view.findViewById(R.id.posted_name));
            mPostTime = ((TextView) view.findViewById(R.id.post_time));
            mCommentNumber = ((TextView) view.findViewById(R.id.comment_number));
            mExamineNumber = ((TextView) view.findViewById(R.id.examine_number));
            mPostImg = ((ImageView) view.findViewById(R.id.post_img));
        }

        @Override
        public void initializeData(int position) {
            Posts.DataEntity posts = (Posts.DataEntity) modules.get(position);
            mPostTitle.setText(posts.getTitle());
            mPostContent.setText(posts.getContent());
            System.out.println("帖子列表标题：" + posts.getTitle());
            if (posts.getSupportNum() != null) {
                mCommentNumber.setText(posts.getSupportNum());
            }
            if (posts.getVisitNum() != null) {

                mExamineNumber.setText(posts.getVisitNum());
            }
            if (posts.getNickname() != null) {
                mPostedName.setText(posts.getNickname().toString());
            } else {
                mPostedName.setText("");
            }
            if (posts.getCreateTime() != 0) {
                mPostTime.setText(DateUtils.getDateToString(posts.getCreateTime(), 3));
            } else {
                mPostTime.setText("");
            }
            if (posts.getPics() != null) {
                System.out.println("帖子列表图片地址1:" + posts.getPics());
                mPostImg.setVisibility(View.VISIBLE);
                String[] postimgs = posts.getPics().split(",");
                System.out.println("列表图片子地址：" + postimgs[0]);
                ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + postimgs[0], mPostImg);
//                Log.d("TAG", posts.getTitle() + "=====" + postimgs[0]);
            } else {
                mPostImg.setVisibility(View.GONE);
                System.out.println("帖子列表图片地址2:" + posts.getPics());
            }
//            else {
//                mPostImg.setVisibility(View.GONE);
//            }
            if (posts.getAvatar() != null) {
                System.out.println("列表图片:" + posts.getAvatar());
                ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + posts.getAvatar(), mPostedHead);
            }
            if (!TextUtils.isEmpty(posts.getAvatar())) {
                ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + posts.getAvatar(), mPostedHead);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        Posts.DataEntity postEntity = (Posts.DataEntity) modules.get(position - 1);
        String id1 = postEntity.getId();
        String avatar = postEntity.getAvatar();
        Intent intent = PostDetailActivity.getIntent(getActivity(), postEntity);
        if (avatar != null) {
            intent.putExtra("avatar", avatar);
        }
        startActivity(intent);
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        if (inflate != null) {
            mPullToRefreshLsv.getRefreshableView().removeFooterView(inflate);
            adapter.notifyDataSetChanged();
        }
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessageAtTime(2, 1000);
            }
        }, 1000);
    }
}
