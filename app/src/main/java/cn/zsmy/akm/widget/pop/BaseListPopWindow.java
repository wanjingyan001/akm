package cn.zsmy.akm.widget.pop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import java.util.ArrayList;

import cn.zsmy.akm.R;

public abstract class  BaseListPopWindow {
    public Context context;
    public ArrayList<Object> modules = new ArrayList<Object>();
    private PopupWindow pop;
    protected PopWindowAdapter adapter;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClickListener(Object obj);

        void onPopWindowDismiss();
    }

    public BaseListPopWindow(Context context) {
        this.context=context;
        View v = LayoutInflater.from(context).inflate(getLayoutId(), null);
        initializeView(v);
        initializeData();
    }

    public abstract int getLayoutId();

    public void initializeView(View v) {
        ListView mPopWindow = (ListView) v.findViewById(R.id.generalPullToRefreshLsv);
        adapter = new PopWindowAdapter();
        mPopWindow.setAdapter(adapter);
        mPopWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onItemClickListener(parent.getAdapter().getItem(position));
                pop.dismiss();
            }
        });
        pop = new PopupWindow(v, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, false);
        pop.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                listener.onPopWindowDismiss();
            }
        });
        pop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        pop.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击窗口外边窗口消失
        pop.setOutsideTouchable(true);
        // pop.setAnimationStyle(R.style.AnimationPreview);
        // 设置此参数获得焦点，否则无法点击
        pop.setFocusable(true);
        pop.update();
    }

    public void showAsDropDown(View mainView) {
        pop.showAsDropDown(mainView, 0, 0);
    }

    public abstract void initializeData();

    class PopWindowAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return modules.size();
        }

        @Override
        public Object getItem(int position) {
            return modules.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getAdapterViewAtPosition(position, convertView, parent);
        }


    }

    public abstract View getAdapterViewAtPosition(int position, View convertView, ViewGroup parent);


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
