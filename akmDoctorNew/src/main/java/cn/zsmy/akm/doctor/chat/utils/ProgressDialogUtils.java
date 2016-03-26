package cn.zsmy.akm.doctor.chat.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogUtils {
    private static ProgressDialog progressDialog;

    @SuppressLint("NewApi")
    public static void showProgressDialog(Context context, String message) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(message);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        if (!((Activity) context).isDestroyed()){
            progressDialog.show();
        }
    }

    @SuppressLint("NewApi")
    public static void closeProgressDialog(Context context) {
        if (progressDialog != null && progressDialog.isShowing() && !((Activity) context).isDestroyed()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
