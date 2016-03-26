package cn.zsmy.akm.widget.call;

import android.content.Context;
import android.view.Gravity;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.zsmy.akm.R;

/**
 * Created by Administrator on 2015/11/27.
 */
public class ChatConnecting extends TextView {
    private Context context;

    public ChatConnecting(Context context) {
        super(context);
        this.context=context;
        initializeView();
    }
    private void initializeView(){
        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.widgets_general_row_line));
        setPadding(20, 20, 20, 20);
        setGravity(Gravity.CENTER);
        setTextSize(16);
        setTextColor(context.getApplicationContext().getResources().getColor(R.color.font_color));
        setText("请耐心等待医生接诊");

    }


}
