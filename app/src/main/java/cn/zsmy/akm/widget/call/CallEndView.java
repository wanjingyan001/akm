package cn.zsmy.akm.widget.call;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.zsmy.akm.R;

/**
 * Created by Administrator on 2015/11/27.
 */
public class CallEndView extends LinearLayout implements View.OnClickListener {
    private Context context;
    private TextView endTex;
    private Button endBtn;
    private Drawable drawable;
    private OnItemClickListener btnItem;
    private ArrayList<String> btnDatas;
    private LinearLayout endLinea;

    public CallEndView(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);
        initializeView();

    }

    public CallEndView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CallEndView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initializeView() {
        endTex = new TextView(context.getApplicationContext());
        addView(endTex);
        setTextParams();
        endLinea = new LinearLayout(context.getApplicationContext());
        endLinea.setWeightSum(2);
        endLinea.setOrientation(LinearLayout.HORIZONTAL);
        drawable = getResources().getDrawable(R.drawable.selector_general_button);
        LinearLayout.LayoutParams btnParams = setButtonLayoutParams();
        setBtnTextD();
        for (int i = 0; i < btnDatas.size(); i++) {
            setButtonParams(btnParams);
            endBtn.setText(btnDatas.get(i));
            endBtn.setId(i);
            if (i == 0) {
                endBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_general_red_button));
            }
            endLinea.addView(endBtn);
            endBtn.setOnClickListener(this);
        }

        addView(endLinea);

    }


    public void setBtnTextD() {
        btnDatas = new ArrayList<String>();
        btnDatas.add("评价");
        btnDatas.add("复诊");
    }

    private void setTextParams() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) endTex.getLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        endTex.setLayoutParams(params);
        endTex.setPadding(20, 20, 20, 20);
        endTex.setGravity(Gravity.CENTER);
        endTex.setTextSize(16);
        endTex.setTextColor(context.getApplicationContext().getResources().getColor(R.color.font_color));
        endTex.setText("问诊已结束");
    }

    private LinearLayout.LayoutParams setButtonLayoutParams() {
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(20, 20, 20, 20);
        btnParams.weight = 1;
        return btnParams;
    }

    private void setButtonParams(LinearLayout.LayoutParams btnParams) {
        endBtn = new Button(context.getApplicationContext());
        endBtn.setBackgroundDrawable(drawable);
        endBtn.setTextSize(20);
        endBtn.setLayoutParams(btnParams);
    }

    public void setButtonStatus(String evaluateTime) {
        if(TextUtils.isEmpty(evaluateTime)){
            Button btn=(Button)endLinea.getChildAt(0);
            btn.setClickable(true);
            btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_style_red));
        }else{
            Button btn=(Button)endLinea.getChildAt(0);
            btn.setClickable(false);
            btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.chat_rates_click_false));
            btn.setText("已评价");

        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                btnItem.OnBtnItemClickListener(v.getId());
                break;
            case 1:
                btnItem.OnBtnItemClickListener(v.getId());
                break;

            default:
                break;
        }
    }

    public interface OnItemClickListener {
        void OnBtnItemClickListener(int position);
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.btnItem = listener;

    }
}
