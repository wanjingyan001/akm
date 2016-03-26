package cn.zsmy.akm.doctor.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.zsmy.doctor.R;


public class SlideTabView extends LinearLayout implements OnPageChangeListener {
    private TextView mHomeTabCursorLabel;
    private ViewPager mHomeViewPager;
    private int currIndex;
    private ArrayList<TextView> tabs = new ArrayList<TextView>();
    private LinearLayout mHomeTabItemContainerLl;
    private Context context;
    private TabConfig config;
    private FragmentManager fragmentManager;
    private OnTabClickListener listener;

    public static interface OnTabClickListener {
        void onTabClick(int index);

        void onChange(int index);
    }


    public static class TabConfig {
        public ArrayList<String> labels;
        public int normalItemColor;
        public int selectedItemColor;
        public int itemBackground;
        public int textSize;
        public ArrayList<Fragment> clazz;

        public TabConfig(ArrayList<String> labels, ArrayList<Fragment> clazz) {
            this.labels = labels;
            this.clazz = clazz;
        }

    }

    public SlideTabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        intializeView(context);
    }

    public SlideTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        intializeView(context);
    }

    public SlideTabView(Context context) {
        super(context);
        intializeView(context);
    }

    private void intializeView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.widgets_slide_tab_vew, this);
        mHomeTabCursorLabel = (TextView) findViewById(R.id.mHomeTabCursorLabel);
        mHomeTabItemContainerLl = (LinearLayout) findViewById(R.id.mHomeTabItemContainerLl);
        mHomeViewPager = (ViewPager) findViewById(R.id.mHomeViewPager);
    }

    public void initializeData(FragmentManager fragmentManager, TabConfig config) {
        this.fragmentManager = fragmentManager;
        this.config = config;
    }

    public void notifyDataChanged() {
        mHomeViewPager.setOffscreenPageLimit(config.labels.size());
        mHomeViewPager.setAdapter(new HomePageAdapter(fragmentManager));
        initializeTopTabs();
        initTextBar();
        mHomeViewPager.setOnPageChangeListener(this);
        mHomeViewPager.setCurrentItem(0);
        initalizeTabTxtColor(0);
    }

    /**
     * initialize top tab item
     */
    public void initializeTopTabs() {
        mHomeTabItemContainerLl.removeAllViews();
        TabItemClickListener listener = new TabItemClickListener();
        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        float scale = getResources().getDisplayMetrics().density;
        int paddingSize = (int) (10 * scale + 0.5f);
        for (int i = 0; i < config.labels.size(); i++) {
            TextView tabItemView = new TextView(context);
            tabItemView.setId(i);
            tabItemView.setText(config.labels.get(i));
            if (config.itemBackground != 0) {
                tabItemView.setBackgroundColor(getResources().getColor(config.itemBackground));
            }
            tabItemView.setTextSize(TypedValue.COMPLEX_UNIT_SP, config.textSize);
            tabItemView.setPadding(paddingSize, paddingSize, paddingSize, paddingSize);
            tabItemView.setGravity(Gravity.CENTER);
            tabItemView.setLayoutParams(layoutParams);
            tabItemView.setOnClickListener(listener);
            tabs.add(tabItemView);
            mHomeTabItemContainerLl.addView(tabItemView);
            if (i != config.labels.size() - 1) {
                TextView lineLabel = new TextView(context);
                int width = (int) (1 * scale + 0.5f);
                LayoutParams line = new LayoutParams(width, (int) (20 * scale + 0.5f));
                lineLabel.setBackgroundColor(getResources().getColor(R.color.white));
                lineLabel.setLayoutParams(line);
                mHomeTabItemContainerLl.addView(lineLabel);
            }
        }
    }

    class HomePageAdapter extends FragmentStatePagerAdapter {

        public HomePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return config.clazz.get(position);

        }

        @Override
        public int getCount() {
            return config.labels.size();
        }
    }

    /**
     * 初始化图片的位移像素
     */
    private void initTextBar() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mHomeTabCursorLabel.getLayoutParams();
        // DisplayMetrics outMetrics = new DisplayMetrics();
        // activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        lp.width = width / config.labels.size();
        mHomeTabCursorLabel.setLayoutParams(lp);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 取得该控件的实例
        FrameLayout.LayoutParams ll = (FrameLayout.LayoutParams) mHomeTabCursorLabel.getLayoutParams();
        if (currIndex == position) {
            ll.leftMargin = (int) (currIndex * mHomeTabCursorLabel.getWidth() + positionOffset * mHomeTabCursorLabel.getWidth());
        } else if (currIndex > position) {
            ll.leftMargin = (int) (currIndex * mHomeTabCursorLabel.getWidth() - (1 - positionOffset) * mHomeTabCursorLabel.getWidth());
        }
        mHomeTabCursorLabel.setLayoutParams(ll);
        if (listener == null) {
            return;
        }
        listener.onChange(position);
    }

    @Override
    public void onPageSelected(int position) {
        currIndex = position;
        initalizeTabTxtColor(position);
    }

    private void initalizeTabTxtColor(int position) {
        for (int i = 0; i < tabs.size(); i++) {
            TextView tab = tabs.get(i);
            tab.setTextColor(getResources().getColor(config.normalItemColor));
        }
        tabs.get(position).setTextColor(getResources().getColor(config.selectedItemColor));
    }

    private class TabItemClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            mHomeViewPager.setCurrentItem(v.getId());
            if (listener != null) {
                listener.onTabClick(v.getId());
            }
        }
    }

    public void setOnTabClickListener(OnTabClickListener listener) {
        this.listener = listener;
    }

}
