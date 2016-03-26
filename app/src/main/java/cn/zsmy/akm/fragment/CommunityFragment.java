package cn.zsmy.akm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.handmark.pulltorefresh.PullToRefreshBase;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.fragment.BaseListFragment;
import cn.wei.library.widget.EmptyView;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.Circle;
import cn.zsmy.akm.entity.Recommend;
import cn.zsmy.akm.http.FileCallback;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.salon.CommunityList;
import cn.zsmy.akm.salon.FunctionDetailActivity;
import cn.zsmy.akm.salon.activity.PlatformListActivity;
import cn.zsmy.akm.utils.UrlHelpper;
import cn.zsmy.akm.widget.NetworkImageHolderView;

/**
 * 社区分类
 * Created by sanz on 2015/11/17 10:00
 */
public class CommunityFragment extends BaseListFragment implements OnItemClickListener {
    private int[] images = {R.drawable.ic_activity, R.drawable.ic_cosmetology, R.drawable.ic_questions, R.drawable.ic_suggest};
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showContent();
                    break;
                case 1:
                    showContent(EmptyView.State.error);
                    break;
            }

        }
    };
    private ConvenientBanner convenientBanner;
    private List<String> paths = new ArrayList<>();
    private List<String> lists;
    private List<Recommend.DataEntity> data;

    @Override
    protected int getFragmentLayoutId() {

        return R.layout.fragment_community;
    }

    @Override
    protected void addRefreshHeaderView(ListView refreshableView) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_community_header, null);
        convenientBanner = ((ConvenientBanner) v.findViewById(R.id.convenientBanner));
        loadRecomment();
        convenientBanner.setOnItemClickListener(this);
        refreshableView.addHeaderView(v);
    }

    @Override
    protected void initializeView(View v) {
        super.initializeView(v);
        mPullToRefreshLsv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        loadCmmunityInfo();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            loadCmmunityInfo();
        }
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadCmmunityInfo();
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);

    }

    @Override
    public void onResume() {
        super.onResume();
        convenientBanner.startTurning(3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }

    private void loadCmmunityInfo() {
        Request request = new Request(UrlHelpper.CIRCLE_URL + "?flag=2", Request.RequestMethod.GET, getActivity());
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Circle cir = JsonParser.deserializeFromJson(result, Circle.class);
                modules.clear();
                List<Circle.DataEntity> data = cir.getData();
                modules.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
        if (modules.size() > 0) {
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            mHandler.sendEmptyMessageDelayed(1, 2000);
        }
    }


    private void loadRecomment() {
        initNET();
        Request request = new Request(UrlHelpper.RECOMMEND_ACTIVITY, getActivity());
        Log.d("TAG", UrlHelpper.RECOMMEND_ACTIVITY);
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                paths.clear();
                Recommend recommend = JsonParser.deserializeFromJson(result, Recommend.class);
                data = recommend.getData();
                Iterator<Recommend.DataEntity> iterator = data.iterator();
                while (iterator.hasNext()) {
                    Recommend.DataEntity next = iterator.next();
                    paths.add(next.getLogo());
                }
                String[] strings = new String[paths.size()];
                for (int i = 0; i < paths.size(); i++) {
                    strings[i] = paths.get(i);
                }
                lists = Arrays.asList(strings);
                convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, lists).setPageIndicator(new int[]{
                        R.drawable.icon_paint_select_not, R.drawable.icon_paint_selected
                });
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    private void initNET() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.bg_transparent)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        String cacheDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "shop" + File.separator + "cache";
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext());
        config.defaultDisplayImageOptions(defaultOptions);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.memoryCache(new FIFOLimitedMemoryCache(2 * 1024 * 1024));
        config.memoryCacheSize(2 * 1024 * 1024);
        config.diskCache(new UnlimitedDiskCache(new File(cacheDir)));
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        Log.d("TAG", position + "11-----" + modules.size());
        Intent intent = new Intent();
        if (position == 1) {
            intent.setClass(getActivity(), CommunityList.class);
            startActivity(intent);
        } else {
            Circle.DataEntity item = (Circle.DataEntity) modules.get(position - 2);
            String platform = item.getId();
            if (position > 2) {
                intent.putExtra("platform", platform);
                intent.putExtra("platforName", item.getTitle());
                intent.setClass(getActivity(), PlatformListActivity.class);
                startActivity(intent);
            } else if (position == 2) {
                intent.setClass(getActivity(), CommunityList.class);
                startActivity(intent);
            }
        }

    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_community_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    @Override
    public void onItemClick(int position) {
        Recommend.DataEntity dataEntity = data.get(position);
        String url = UrlHelpper.FILE_IP + dataEntity.getHttpUrl();
        startActivity(FunctionDetailActivity.getIntent(getActivity(), url, dataEntity.getTitle()));
    }

    class Holder extends QBaseViewHolder {
        private TextView content_Salo, Time_Salo, title_Salo;
        private ImageView pic_Salo;

        @Override
        public void initializeView(View view) {
            content_Salo = (TextView) view.findViewById(R.id.Salo_ContentId);
            Time_Salo = (TextView) view.findViewById(R.id.Salo_TimeId);
            title_Salo = (TextView) view.findViewById(R.id.Salo_TitleId);
            pic_Salo = (ImageView) view.findViewById(R.id.Salo_ImgId);
        }

        @SuppressLint("NewApi")
        @Override
        public void initializeData(int position) {
            Circle.DataEntity datas = (Circle.DataEntity) modules.get(position);
            title_Salo.setText(datas.getTitle().toString());
            pic_Salo.setBackground(getResources().getDrawable(images[position]));
            if (datas.getLatestTopicTitle() != null) {
                content_Salo.setText(datas.getLatestTopicTitle());
            }
            if (datas.getTopicQty() != 0) {
                Time_Salo.setText(String.valueOf(datas.getTopicQty()));
            }
        }
    }
}
