package cn.zsmy.akm.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import cn.zsmy.akm.R;


/**
 * Created by Administrator on 2016/1/19.
 */
public class ReplyDialog extends Dialog implements View.OnClickListener {
    private Context context;

    public ReplyDialog(Context context) {
        super(context, R.style.CustomProgressDialog);
        this.context = context;
    }


    public ReplyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public ReplyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reply_dialog);
        findViewById(R.id.comment_cancel).setOnClickListener(this);
    }

    public static ReplyDialog create(Context context) {
        ReplyDialog dialog = new ReplyDialog(context);
        return dialog;
    }


    @Override
    public void onClick(View v) {
        this.dismiss();
        ((Activity) context).finish();
    }
}
