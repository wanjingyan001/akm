package cn.zsmy.akm.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import cn.zsmy.akm.R;
import cn.zsmy.akm.adapter.PopWinAdapter;
import cn.zsmy.akm.entity.Province;

/**
 * Created by Administrator on 2016/1/14.
 */
public class PopWin implements View.OnTouchListener, AdapterView.OnItemClickListener {
    private Context context;
    private GetPosition getPosition;
    private ImageView imageView;
    private PopWinAdapter adapter;
    private ListView mDropDown;
    private PopupWindow popupWindow;
    private View inflate;
    private List<Object> strings;

    public PopWin(Context context, GetPosition getPosition, ImageView imageView) {
        this.context = context;
        this.getPosition = getPosition;
        this.imageView = imageView;
        initWin();
        setConfig();
        setListener();
    }

    public void initWin() {
        Activity activity = (Activity) context;
        inflate = activity.getLayoutInflater().inflate(R.layout.layout_drop_down_list, null);
        mDropDown = (ListView) inflate.findViewById(R.id.drop_down_list);
        View top = LayoutInflater.from(context).inflate(R.layout.item_doctor_popwin, null);
        TextView topText = (TextView) top.findViewById(R.id.popwin_title);
        topText.setText("全部");
        mDropDown.addHeaderView(top);

    }


    private void setConfig() {
        popupWindow = new PopupWindow(inflate,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                true);
        popupWindow.setAnimationStyle(R.style.drop_down_list_anim);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                imageView.setImageResource(R.drawable.ic_down);
            }
        });
    }

    private void setListener() {
        inflate.setOnTouchListener(this);
        mDropDown.setOnItemClickListener(this);
    }


    public void initData(List<Object> strings) {
        this.strings = strings;
        adapter = new PopWinAdapter(context, strings);
        adapter.notifyDataSetChanged();
        mDropDown.setAdapter(adapter);
    }

    public void show(View view) {
        imageView.setImageResource(R.drawable.ic_up);
        popupWindow.showAsDropDown(view);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            imageView.setImageResource(R.drawable.ic_down);
            popupWindow = null;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object item = null;
        if (position == 0) {
            item = adapter.getItem(position);
        } else {
            item = adapter.getItem(position - 1);
        }

        if (item instanceof Province.DataEntity) {
            if (position == 0) {
                Object o = adapter.getItem(0);
                getPosition.clickItem(0, o);
            } else {
                getPosition.clickItem(position, adapter.getItem(position - 1));
            }
        } else {
            if (popupWindow.isShowing()) {
                imageView.setImageResource(R.drawable.ic_down);
                popupWindow.dismiss();
                if (position != 0) {
                    getPosition.clickItem(position, adapter.getItem(position - 1));
                } else {
                    Object o = adapter.getItem(0);
                    getPosition.clickItem(0, o);
                }
            }
        }
    }


    public interface GetPosition {
        void clickItem(int position, Object o);
    }

}
