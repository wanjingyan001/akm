package cn.zsmy.akm.doctor.profile.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import cn.zsmy.doctor.R;

/**
 * Created by sanz on 2015/12/22 17:37
 */
public class MyIntegralTopView extends LinearLayout {
    private Context  context;
    public MyIntegralTopView(Context context) {
        super(context);
        this.context=context;
        initializeView();
    }

    public MyIntegralTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyIntegralTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //    public MyIntegralTopView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    private void initializeView() {
        LayoutInflater.from(context).inflate(R.layout.view_my_intergral_top_view,this);

    }
}
