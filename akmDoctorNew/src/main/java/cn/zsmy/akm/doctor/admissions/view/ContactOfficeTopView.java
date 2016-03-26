package cn.zsmy.akm.doctor.admissions.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.zsmy.doctor.R;

/**
 * Created by qinwei on 2015/12/17 10:20
 */
public class ContactOfficeTopView extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private ListView listView;
    private TextView text;

    public ContactOfficeTopView(Context context) {
        super(context);
        this.context = context;
        initializeView();
        setOnClickListener(this);
        setBackgroundColor(getResources().getColor(R.color.white));
        setPadding(0,10,0,10);
    }

    public ContactOfficeTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContactOfficeTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeView() {
        text = new TextView(context);
        text.setTextSize(16);
        ImageView imge = new ImageView(context);
        Drawable drawable = getResources().getDrawable(R.mipmap.cancel);
        imge.setBackgroundDrawable(drawable);
        addView(text);
        addView(imge);
        LayoutParams layoutParams = (RelativeLayout.LayoutParams) text.getLayoutParams();
        LayoutParams imge_Params = (RelativeLayout.LayoutParams) imge.getLayoutParams();
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        imge_Params.width=56;
        imge_Params.height=56;
        imge_Params.setMargins(0,0,24,0);

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        text.setLayoutParams(layoutParams);
        imge.setLayoutParams(imge_Params);
        imge_Params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imge_Params.addRule(RelativeLayout.CENTER_VERTICAL);
    }

    public void remind(int type){
        switch (type){
            case 0:
                text.setText("请在5分钟内接诊，超时订单将退回系统");
                break;
            case 1:
                text.setText("请在24小时内接诊，超时订单将自动取消");
                break;
        }
    }


    @Override
    public void onClick(View v) {
        setVisibility(GONE);
    }


}
