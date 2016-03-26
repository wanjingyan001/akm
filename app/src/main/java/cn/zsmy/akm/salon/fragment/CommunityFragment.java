package cn.zsmy.akm.salon.fragment;

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
import android.widget.Toast;

import com.handmark.pulltorefresh.PullToRefreshBase;
import com.nostra13.universalimageloader.core.ImageLoader;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.wei.library.fragment.BaseListFragment;
import cn.zsmy.akm.R;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.salon.FunctionDetailActivity;
import cn.zsmy.akm.salon.bean.Function;
import cn.zsmy.akm.utils.DateUtils;
import cn.zsmy.akm.utils.UrlHelpper;

/**
 * Created by Administrator on 2015/11/30.
 */
public class CommunityFragment extends BaseListFragment {
    private View inflate;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
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
        }
    };
    private int type;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initializeView(View v) {
        super.initializeView(v);
        type = getArguments().getInt("TYPE");
        mPullToRefreshLsv.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        super.onRefresh(isRefresh);
        if (inflate!=null){
            mPullToRefreshLsv.getRefreshableView().removeFooterView(inflate);
            adapter.notifyDataSetChanged();
        }
        mPullToRefreshLsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadCommunityList(String.valueOf(type));
                mPullToRefreshLsv.onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadCommunityList(String.valueOf(type));
    }


    private void loadCommunityList(String type) {
        Request request = new Request(UrlHelpper.DEFULT_EVENT_LIST + "resultType=" + type, getActivity());
        Log.i("TAG", UrlHelpper.DEFULT_EVENT_LIST + "resultType" + type);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                modules.clear();
                Function function = JsonParser.deserializeFromJson(result, Function.class);
                modules.addAll(function.getData());
                adapter.notifyDataSetChanged();
                mHandler.sendEmptyMessage(0);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);

    }

    @Override
    public View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_community_fragment_adapter_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }


    class ViewHolder extends QBaseViewHolder {
        private ImageView comImg;
        private TextView creat_time, cash, ing, regist, date;

        @Override
        public void initializeView(View view) {
            comImg = (ImageView) view.findViewById(R.id.community_imgID);
            ing = (TextView) view.findViewById(R.id.community_ing_text);
            cash = (TextView) view.findViewById(R.id.community_cash_text);
            regist = (TextView) view.findViewById(R.id.community_regist_text);
            creat_time = (TextView) view.findViewById(R.id.community_time_text);
            date = (TextView) view.findViewById(R.id.community_date_text);
        }

        @Override
        public void initializeData(int position) {
            Function.DataEntity function_DataEntity = (Function.DataEntity) modules.get(position);
            String start_Time = DateUtils.getDateToString(function_DataEntity.getStartTime(), 4);
            String end_Time = DateUtils.getDateToString(function_DataEntity.getEndTime(), 4);
            ImageLoader.getInstance().displayImage(UrlHelpper.FILE_IP + function_DataEntity.getLogo(), comImg);
            cash.setText(function_DataEntity.getTitle());
            date.setText(start_Time + "--" + end_Time);
            long now = System.currentTimeMillis();
            if (now < function_DataEntity.getStartTime()) {
                ing.setText("即将开始");
                ing.setBackgroundResource(R.drawable.function_list_about_to_start);
            } else if (now >= function_DataEntity.getStartTime() && now <= function_DataEntity.getEndTime()) {
                ing.setText("进行中");
                ing.setBackgroundResource(R.drawable.function_list_item_function_status_text_bg);
            } else if (now > function_DataEntity.getEndTime()) {
                ing.setText("已结束");
                ing.setBackgroundResource(R.drawable.function_list_end);
            }
            creat_time.setText(DateUtils.getDateToString(function_DataEntity.getStartTime(), 1));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Function.DataEntity function_DataEntity = (Function.DataEntity) modules.get(position - 1);
        String url = function_DataEntity.getHttpUrl();
        String title = function_DataEntity.getTitle();
        if (url != null) {
            startActivity(FunctionDetailActivity.getIntent(getActivity(), UrlHelpper.FILE_IP + url, title));
        } else {
            Toast.makeText(getActivity(), "没有该链接", Toast.LENGTH_LONG).show();
        }

    }
}
