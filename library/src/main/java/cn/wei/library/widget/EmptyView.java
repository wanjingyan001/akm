package cn.wei.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import cn.wei.library.R;


/**
 * emptyView解决了请求网络数据时ui显示的三种状态
 * 分别为加载中，加载失败，无数据
 *
 * @author qinwei create by 2015/10/28
 */
public class EmptyView extends FrameLayout implements OnClickListener {

    private View empty;
    private View error;

    private State state;
    private OnRetryListener listener;
    private ProgressDialog dialog;

    public interface OnRetryListener {
        void onRetry();
    }

    public enum State {
        ing, error, empty
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public EmptyView(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_empty_view, this);
        empty = findViewById(R.id.empty);
        dialog=new ProgressDialog(context);
        error = findViewById(R.id.error);
        setOnClickListener(this);
        notifyDataChanged(State.ing);
    }

    public void notifyDataChanged(State state) {
        this.state = state;
        switch (state) {
            case ing:
                dialog.show();
                empty.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
                break;
            case empty:
                dialog.dismiss();
                empty.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
                break;
            case error:
                dialog.dismiss();
                empty.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public void setOnRetryListener(OnRetryListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null && state == State.error) {
            listener.onRetry();
        }
    }
    public void setDialogDismiss(){
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
