package cn.wei.library.widget;

import android.content.Context;

public class ProgressDialogUtils {
    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context, String message) {
        progressDialog = ProgressDialog.show(context, message);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static void showProgressDialog(Context context) {
        progressDialog = ProgressDialog.show(context, null);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog.cancel();
        }
    }
}
