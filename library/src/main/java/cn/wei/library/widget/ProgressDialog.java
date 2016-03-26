package cn.wei.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import cn.wei.library.R;


/**
 * @author qinwei email:qinwei_it@163.com
 * @version 1.0
 * @created createTime: 2015-10-11 下午12:15:53
 */

public class ProgressDialog extends Dialog {
    boolean isDismiss;
    private String message;
    private ProgressWheel mProgressBar;
    private TextView mProgressDialogLabel;

    public ProgressDialog(Context context) {
        super(context,R.style.CustomProgressDialog);
        setCanceledOnTouchOutside(false);


    }

    protected ProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
        mProgressDialogLabel = (TextView) findViewById(R.id.mProgressDialogLabel);
        mProgressBar = (ProgressWheel) findViewById(R.id.progress_wheel);
        if (TextUtils.isEmpty(message)) {
            mProgressDialogLabel.setVisibility(View.GONE);
        } else {
            mProgressDialogLabel.setVisibility(View.VISIBLE);
            mProgressDialogLabel.setText(message);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long i = 0;
                    while (!isDismiss) {
                        Thread.sleep(800);
                        i++;
                        mHandler.sendEmptyMessage((int) (i % 3));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static ProgressDialog show(Context context, String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        return dialog;
    }


    @Override
    public void dismiss() {
        super.dismiss();
        isDismiss=true;

    }

    private void setMessage(String message) {
        this.message = message;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mProgressDialogLabel.setText(message + ".");
                    break;
                case 1:
                    mProgressDialogLabel.setText(message + "..");
                    break;
                case 2:
                    mProgressDialogLabel.setText(message + "...");
                    break;
            }

        }
    };
}
