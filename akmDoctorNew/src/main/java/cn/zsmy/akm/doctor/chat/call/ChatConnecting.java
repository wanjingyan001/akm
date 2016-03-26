package cn.zsmy.akm.doctor.chat.call;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.zsmy.doctor.R;


/**
 * Created by Administrator on 2015/11/27.
 */
public class ChatConnecting extends LinearLayout implements View.OnClickListener {
    private Context context;
    private TextView tv;
    private Drawable drawable;
    private ArrayList<String> btnDatas;
    private Button startBtn;
    private CallEndView.OnItemClickListener btnItem;

    public ChatConnecting(Context context) {
        super(context);
        setOrientation(VERTICAL);
        this.context = context;
        initializeView();
    }

    public ChatConnecting(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChatConnecting(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeView() {
        tv = new TextView(context.getApplicationContext());
        addView(tv);
        setTextParams();
        LinearLayout endLinea = new LinearLayout(context.getApplicationContext());
        endLinea.setWeightSum(2);
        endLinea.setOrientation(LinearLayout.HORIZONTAL);
        drawable = getResources().getDrawable(R.drawable.selector_general_button);
        LayoutParams btnParams = setButtonLayoutParams();
        setBtnTextD();
        for (int i = 0; i < btnDatas.size(); i++) {
            setButtonParams(btnParams);
            startBtn.setText(btnDatas.get(i));
            startBtn.setId(i + 2);
            if (i == 0) {
                startBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.chat_choose));
            }
            endLinea.addView(startBtn);
            startBtn.setOnClickListener(this);
        }

        addView(endLinea);
    }


    private void setTextParams() {
        LayoutParams params = (LayoutParams) tv.getLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        tv.setLayoutParams(params);
        tv.setPadding(20, 20, 20, 20);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(16);
        tv.setTextColor(context.getApplicationContext().getResources().getColor(R.color.app_main_color));
//        tv.setText("请在5分钟内接诊，超时订单将退回系统");
    }

    public void setAdmissionsTimeInfo(int type) {
        switch (type) {
            case 0:
                tv.setText("请在5分钟内接诊，超时订单将退回系统");
                break;
            case 1:
                tv.setText("请在24小时内接诊，超时订单将自动取消");
                break;

        }

    }

    public void setBtnTextD() {
        btnDatas = new ArrayList<>();
        btnDatas.add("忽略");
        btnDatas.add("接诊");
    }

    private LayoutParams setButtonLayoutParams() {
        LayoutParams btnParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(20, 20, 20, 20);
        btnParams.weight = 1;
        return btnParams;
    }

    private void setButtonParams(LayoutParams btnParams) {
        startBtn = new Button(context.getApplicationContext());
        startBtn.setBackgroundDrawable(drawable);
        startBtn.setTextSize(20);
        startBtn.setLayoutParams(btnParams);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 2:
                btnItem.OnBtnItemClickListener(v.getId());
                break;
            case 3:
                btnItem.OnBtnItemClickListener(v.getId());
                break;
        }
    }

    public void setItemClickListener(CallEndView.OnItemClickListener listener) {
        this.btnItem = listener;

    }
}
