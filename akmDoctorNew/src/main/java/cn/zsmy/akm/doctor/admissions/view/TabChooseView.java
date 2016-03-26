package cn.zsmy.akm.doctor.admissions.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.zsmy.doctor.R;

/**
 * Created by qinwei on 2015/12/18 10:02
 */
public class TabChooseView extends LinearLayout {
    private Context mContext;
    private int num;
    private OnItemChooseClickListener itemChooseClickListener;
    private TextView title;
    private ImageView chooseArrow;

    public TabChooseView(Context context, int num) {
        super(context);
        mContext = context;
        this.num = num;
        setOrientation(HORIZONTAL);
        setWeightSum(2);

    }

    public TabChooseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public TabChooseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    public void initializeView(List<String> datas, Context context) {
        mContext = context;
        View view = null;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < datas.size(); i++) {
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            view = LayoutInflater.from(context).inflate(R.layout.view_top_choose, null);
            view.setLayoutParams(params);
            title = (TextView) view.findViewById(R.id.choose_title);
            chooseArrow = ((ImageView) view.findViewById(R.id.choose_arrow));
            title.setText(datas.get(i));
            title.setTag(i);
            chooseArrow.setTag(i + 2);
            final int finalI = i;
            title.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemChooseClickListener != null) {
                        itemChooseClickListener.OnChooseChlickListener(finalI);
                    }
                }
            });
            addView(view);
        }

    }

    //改变文字
    public void setText(String text, int tag) {
        ((TextView) findViewWithTag(tag)).setText(text);
    }


    //改变箭头方向
    @SuppressLint("NewApi")
    public void changeArrow(boolean isShow, int tag) {
        if (isShow) {
            ((ImageView) findViewWithTag(tag)).setBackground(getResources().getDrawable(R.mipmap.ic_up));
        } else {
            ((ImageView) findViewWithTag(tag)).setBackground(getResources().getDrawable(R.mipmap.ic_down));
        }
    }


    public interface OnItemChooseClickListener {
        public void OnChooseChlickListener(int id);
    }

    public void setOnItemChooseChlickListener(OnItemChooseClickListener listener) {
        this.itemChooseClickListener = listener;
    }
}
