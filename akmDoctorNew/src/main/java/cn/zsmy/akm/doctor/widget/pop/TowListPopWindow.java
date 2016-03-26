package cn.zsmy.akm.doctor.widget.pop;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.List;

import cn.zsmy.akm.doctor.admissions.adapter.HospitalAdapter;
import cn.zsmy.doctor.R;

/**
 * Created by qinwei on 2015/12/18 11:10
 */
public class TowListPopWindow implements AdapterView.OnItemClickListener {
    //上下文对象
    private Context mContext;
    //Title文字
    private String mTitle;
    //PopupWindow对象
    private PopupWindow mPopupWindow;
    private ListView left_lv, right_lv;
    private HospitalAdapter adapter_l, adapter_r;
    private List<String> strings;
    private List<String> twoSec;
    private ImageView imageView;
    private int checkType;
    //点击事件
    private onPopupWindowItemClickListener itemClickListener;

    /**
     * 一个参数的构造方法
     *
     * @param context
     */
    public TowListPopWindow(Context context) {
        this.mContext = context;
    }

    public TowListPopWindow(Context context, List<String> strings) {
        this(context);
        this.strings = strings;
    }

    public TowListPopWindow(Context mContext, List<String> strings, List<String> twoSec, ImageView imageView,int checkType) {
        this.mContext = mContext;
        this.strings = strings;
        this.twoSec = twoSec;
        this.imageView = imageView;
        this.checkType = checkType;
    }

    public TowListPopWindow() {

    }


    /**
     * 设置选项的点击事件
     *
     * @param itemClickListener
     */
    public void setItemClickListener(
            onPopupWindowItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(View view) {
        Log.i("TAG", 2 + "");
        View popupWindow_view = LayoutInflater.from(mContext).inflate(R.layout.pop_select_drug_sections, null);
        initializeData(popupWindow_view);
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.drop_down_list_anim);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                imageView.setBackgroundResource(R.mipmap.ic_down);
                setWindowAlpa(false);
            }
        });
        show(view);
    }

    public void initializeData(View view) {
        left_lv = (ListView) view.findViewById(R.id.left);
        Log.i("TAG", left_lv.toString());
        right_lv = (ListView) view.findViewById(R.id.right);
        if (strings != null) {
            adapter_l = new HospitalAdapter(strings, mContext, 0);
            adapter_l.notifyDataSetChanged();
            left_lv.setAdapter(adapter_l);
            left_lv.setOnItemClickListener(this);
            left_lv.setVisibility(View.VISIBLE);
        } else {
            left_lv.setVisibility(View.GONE);
        }
        adapter_r = new HospitalAdapter(twoSec, mContext, 1);
        right_lv.setAdapter(adapter_r);
        right_lv.setOnItemClickListener(this);

    }

    /**
     * 显示PopupWindow
     */
    private void show(View v) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAsDropDown(v);
            imageView.setBackgroundResource(R.mipmap.ic_up);
        }
        setWindowAlpa(true);
    }


    /**
     * 消失PopupWindow
     */
    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            imageView.setBackgroundResource(R.mipmap.ic_down);
        }
    }

    /**
     * 动态设置Activity背景透明度
     *
     * @param isopen
     */
    public void setWindowAlpa(boolean isopen) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        final Window window = ((Activity) mContext).getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();
        window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ValueAnimator animator;
        if (isopen) {
            animator = ValueAnimator.ofFloat(1.0f, 0.5f);
        } else {
            animator = ValueAnimator.ofFloat(0.5f, 1.0f);
        }
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                float alpha = (float) animation.getAnimatedValue();
//                lp.alpha = alpha;
//                window.setAttributes(lp);
            }
        });
        animator.start();
    }

    public boolean isShow() {
        return mPopupWindow.isShowing();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (checkType){
            case 0:
                if (parent.getId() == left_lv.getId()) {
                    adapter_l.setSelectItem(position);
                    adapter_l.notifyDataSetChanged();
                    String item = (String) adapter_l.getItem(position);
                    itemClickListener.onItemClick(position, item, 0);
                } else if (parent.getId() == right_lv.getId()) {
                    String item = (String) adapter_r.getItem(position);
                    itemClickListener.onItemClick(position, item, 1);
                    dismiss();
                }
                break;
            case 1:
                String item = (String) adapter_r.getItem(position);
                itemClickListener.onItemClick(position, item, 2);
                dismiss();
                break;
        }

    }

    /**
     * 点击事件选择回调
     */
    public interface onPopupWindowItemClickListener {
        public void onItemClick(int position, String str, int type);
    }
}
