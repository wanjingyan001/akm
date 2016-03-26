package cn.zsmy.akm.widget.dialog;

import android.content.Context;
import android.util.Log;

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
            Log.i("TAG", ">>>>>>>>>>isshow");
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
                progressDialog.cancel();
            }
        }
    }
}
