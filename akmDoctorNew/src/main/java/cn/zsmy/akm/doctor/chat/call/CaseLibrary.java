package cn.zsmy.akm.doctor.chat.call;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.zsmy.doctor.R;

/**
 * Created by Administrator on 2016/1/4.
 */
public class CaseLibrary extends LinearLayout implements View.OnClickListener {
    private Context context;
    private CallEndView.OnItemClickListener btnItem;
    private TextView tv;

    public CaseLibrary(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);
        setBackgroundColor(R.color.widgets_dot_pressed);
        setGravity(Gravity.CENTER);
        setPadding(8, 8, 8, 8);
        initView();
    }

    public CaseLibrary(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CaseLibrary(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        tv = new TextView(context.getApplicationContext());
        addView(tv);
        LayoutParams params = (LayoutParams) tv.getLayoutParams();
        params.width = LayoutParams.WRAP_CONTENT;
        params.height = LayoutParams.WRAP_CONTENT;
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setTextSize(18);
        tv.setTextColor(context.getApplicationContext().getResources().getColor(R.color.title_color));
        tv.setText("分享病例");
        Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_share);
        tv.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        tv.setId(4);
        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        btnItem.OnBtnItemClickListener(v.getId());
    }

    public void setItemClickListener(CallEndView.OnItemClickListener listener) {
        this.btnItem = listener;
    }
}
