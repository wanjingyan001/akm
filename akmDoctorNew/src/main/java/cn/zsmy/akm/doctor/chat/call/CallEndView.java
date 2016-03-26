package cn.zsmy.akm.doctor.chat.call;

import android.content.Context;
import android.graphics.Color;
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
public class CallEndView extends LinearLayout implements View.OnClickListener{
    private Context context;
    private TextView endTex;
    private Button endBtn;
    private Drawable drawable;
    private OnItemClickListener btnItem;
    private ArrayList<String> btnDatas;
    public CallEndView(Context context) {
        super(context);
        this.context=context;
        setOrientation(VERTICAL);
        initializeView();

    }

    public CallEndView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CallEndView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initializeView(){
        endTex = new TextView(context.getApplicationContext());
        addView(endTex);
        setTextParams();
        LinearLayout endLinea=new LinearLayout(context.getApplicationContext());
        endLinea.setWeightSum(2);
        endLinea.setOrientation(LinearLayout.HORIZONTAL);
        drawable=getResources().getDrawable(R.drawable.selector_general_button);
        LayoutParams btnParams=setButtonLayoutParams();
        setBtnTextD();
        for (int i = 0; i < btnDatas.size(); i++) {
            setButtonParams(btnParams);
            endBtn.setText(btnDatas.get(i));
            endBtn.setId(i);
            if (i == 0) {
                endBtn.setBackgroundColor(Color.RED);
            }
            endLinea.addView(endBtn);
            endBtn.setOnClickListener(this);
        }

        addView(endLinea);

    }


    public  void setBtnTextD(){
                btnDatas=new ArrayList<String>();
                btnDatas.add("评价");
                btnDatas.add("复诊");
    }
    private void setTextParams(){
        LayoutParams params=(LayoutParams)endTex.getLayoutParams();
        params.width=LayoutParams.MATCH_PARENT;
        params.height=LayoutParams.WRAP_CONTENT;
        endTex.setLayoutParams(params);
        endTex.setPadding(20, 20, 20, 20);
        endTex.setGravity(Gravity.CENTER);
        endTex.setTextSize(16);
        endTex.setTextColor(context.getApplicationContext().getResources().getColor(R.color.font_color));
        endTex.setText("问诊已结束");
    }
    private LayoutParams setButtonLayoutParams(){
        LayoutParams btnParams=new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(20, 20, 20, 20);
        btnParams.weight=1;
        return btnParams;
    }
    private void setButtonParams(LayoutParams btnParams){
        endBtn=new Button(context.getApplicationContext());
        endBtn.setBackgroundDrawable(drawable);
        endBtn.setTextSize(20);
        endBtn.setLayoutParams(btnParams);
    }
    private void setLinearLayoutParams(){

}


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
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

    public interface OnItemClickListener{
                 void OnBtnItemClickListener(int position);
    }

    public void setItemClickListener(OnItemClickListener listener){
        this.btnItem=listener;

    }
}
