package cn.zsmy.akm.chat.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogUtils {
	private static ProgressDialog progressDialog;

	public static void showProgressDialog(Context context, String message) {
		progressDialog = new ProgressDialog(context);
		progressDialog.setCancelable(true);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setMessage(message);
		progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		progressDialog.show();
	}

	public static void closeProgressDialog() {
		if (progressDialog != null&&progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog=null;
		}
	}
}
