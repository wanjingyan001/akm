package cn.zsmy.akm.doctor.learning.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.doctor.learning.bean.Area;
import cn.zsmy.akm.doctor.learning.fragment.ScholarshipFragment;
import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/19 10:51
 */
public class SorllTabView extends LinearLayout implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private HorizontalScrollView scrollView;
    private ViewPager vPager;
    private TextView scrollText;
    private LinearLayout naLayout;
    private List<Area> titleDatas;
    private List<Fragment> fragDatas;
    private Context mContext;
    private int curPosition;
    private FrameLayout.LayoutParams params;
    private FragmentAdapter adapter;
    private TextView text;
    private ImageView down;
    private OnClickStartCollectionArea listener;

    public void setOnClickStartCollectionAreaListener(OnClickStartCollectionArea listener) {
        this.listener = listener;
    }

    public SorllTabView(Context context) {
        super(context);
        mContext = context;
        intializeView(mContext);

    }

    public SorllTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SorllTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //    public SorllTabView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    private void intializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_srcoll_tab, this);
        scrollView = (HorizontalScrollView) findViewById(R.id.scrollId);
        vPager = (ViewPager) findViewById(R.id.viewPagerId);
        scrollText = (TextView) findViewById(R.id.scrollTextId);
        down = (ImageView) findViewById(R.id.down);
        params = (FrameLayout.LayoutParams) scrollText.getLayoutParams();
        vPager.setOnPageChangeListener(this);
        down.setOnClickListener(this);
    }

    ;

    private void initializeData() {
        clickEvent();

    }

    private void clickEvent() {
        naLayout = (LinearLayout) findViewById(R.id.navigationLayoutiId);
        naLayout.removeAllViews();
        TextView text = null;
        for (int i = 0, len = titleDatas.size(); i < len; i++) {
            text = new TextView(mContext);
            text.setTag(i);
            text.setId(i);
            text.setPadding(50, 30, 50, 30);
            text.setText(titleDatas.get(i).getAreaName());
            text.setTextColor(getResources().getColor(R.color.edit_content));
            text.setOnClickListener(this);
            naLayout.addView(text);
        }
        onClick(naLayout.getChildAt(0));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.down:
                listener.onClickCollectionAreaListenter();
                break;
            default:
                TextView t = (TextView) findViewById(v.getId());
                t.setTextColor(getResources().getColor(R.color.app_main_color));
                ScaleAnimation anim = (ScaleAnimation) AnimationUtils
                        .loadAnimation(getContext(), R.anim.scale_anim);
                v.startAnimation(anim);
                vPager.setCurrentItem((Integer) v.getTag());
                break;
        }

    }

    /**
     * 初始化标题数据
     */
    public void setDatas(List<Area> datas, FragmentManager fragmentManager) {
        titleDatas = new ArrayList<>();
        fragDatas = new ArrayList<>();
        titleDatas.clear();
        fragDatas.clear();
        ScholarshipFragment fragment = null;
        for (Area s : datas) {
            titleDatas.add(s);
            fragment = new ScholarshipFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("AREA", s);
            fragment.setArguments(bundle);
            fragDatas.add(fragment);
        }
        initializeData();
        adapter = new FragmentAdapter(fragmentManager);
        adapter.notifyDataSetChanged();
        vPager.setAdapter(adapter);
    }

    ;

    @Override
    public void onPageSelected(int position) {
        selectScroll(position);
        curPosition = position;
    }

    public FragmentAdapter getAdapter() {
        return adapter;
    }

    public ViewPager getViewPager() {
        return vPager;
    }

    @Override
    public void onPageScrolled(int position, float offset,
                               int offsetPixies) {
        text = (TextView) naLayout.getChildAt(position);
        int oldwidth = 0;
        int allwidth = 0;
        int newWidth;
        for (int i = 0; i <= position; i++) {
            TextView text = (TextView) naLayout.getChildAt(i);
            newWidth = text.getWidth();
            if (i == 0) {
                allwidth = 0;
            } else {
                allwidth = oldwidth + allwidth;
            }
            oldwidth = newWidth;
        }
        params.width = text.getWidth();
        if (offset == 0) {
            params.leftMargin = allwidth;
        } else {
            params.leftMargin = (int) (allwidth + offset);
        }
        scrollText.setLayoutParams(params);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if (arg0 == 2) {
            TextView text = (TextView) naLayout.getChildAt(curPosition);
            ScaleAnimation anim1 = (ScaleAnimation) AnimationUtils
                    .loadAnimation(getContext(), R.anim.scale_anim2);
            text.startAnimation(anim1);
            text.setTextColor(getResources().getColor(R.color.edit_content));
        } else if (arg0 == 0) {
            text.setTextColor(getResources().getColor(R.color.app_main_color));
            ScaleAnimation anim = (ScaleAnimation) AnimationUtils
                    .loadAnimation(getContext(), R.anim.scale_anim);
            text.startAnimation(anim);
        }
    }

    public void selectScroll(int position) {
        int oldwidth = 0;
        int allwidth = 0;
        int newWidth;
        for (int i = 0; i <= position; i++) {
            TextView text = (TextView) naLayout.getChildAt(i);
            newWidth = text.getWidth();
            if (i == 0) {
                allwidth = 0;
            } else {
                allwidth = oldwidth + allwidth;
            }
            oldwidth = newWidth;
        }
        int left = scrollText.getWidth() * position;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int offset = left - (screenWidth / 2) + (params.width / 2);
        scrollView.smoothScrollTo(allwidth, 0);
    }

    public class FragmentAdapter extends FragmentPagerAdapter {
        private FragmentManager fm;

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
            this.fm = fm;
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragDatas.get(arg0);
        }

        @Override
        public int getCount() {
            return fragDatas.size();
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            super.destroyItem(container, position, object);
            FragmentTransaction mCurTransaction = null;
            if (mCurTransaction == null) {
                mCurTransaction = fm.beginTransaction();
            }
            mCurTransaction.detach((Fragment) object); //把fragment从Activity中detach掉
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    public interface OnClickStartCollectionArea {
        public void onClickCollectionAreaListenter();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
