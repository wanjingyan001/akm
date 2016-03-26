package cn.zsmy.akm.doctor.widget.pop;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.List;

import cn.zsmy.doctor.R;


/**
 * Created by wanjingyan
 * on 2015/11/25 10:37.
 */
public class PopWinUtils {
    private static PopupWindow popupWindow;

    public static void getPopupWindow(Context context, List<String> strings, View view) {

        if (popupWindow != null) {
            popupWindow.dismiss();
        } else {
            initPopupWindow(strings, context, view);
        }
    }

    protected static void initPopupWindow(List<String> strings, Context context, View view) {
        Activity activity = (Activity) context;
        View inflate = activity.getLayoutInflater().inflate(R.layout.pop_select_drug_sections, null, false);
        ListView mDropDown = (ListView) inflate.findViewById(R.id.left);
        mDropDown.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, strings));
        popupWindow = new PopupWindow(inflate,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                true);
        popupWindow.setAnimationStyle(R.style.drop_down_list_anim);
        inflate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        popupWindow.showAsDropDown(view);
        mDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
