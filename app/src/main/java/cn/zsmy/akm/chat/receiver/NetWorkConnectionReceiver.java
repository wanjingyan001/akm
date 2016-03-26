package cn.zsmy.akm.chat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cn.zsmy.akm.chat.im.IMPushManager;
import cn.zsmy.akm.chat.utils.Trace;
import cn.zsmy.akm.home.MyApplication;

public class NetWorkConnectionReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// Toast.makeText(context, intent.getAction(), 1).show();
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo activeInfo = manager.getActiveNetworkInfo();
		if (mobileInfo.isConnected()) {
			// TODO 手机2g 3g 4g网络连接成功
			MyApplication.isNetConnected = true;
			NetWorkDataChanger.getInstance().notifyDataChanged(NetWorkEntity.getInstance(true, NetWorkEntity.MOBILE_NET));
		} else if (wifiInfo.isConnected()) {
			// TODO wifi 连接成功
			MyApplication.isNetConnected = true;
			NetWorkDataChanger.getInstance().notifyDataChanged(NetWorkEntity.getInstance(true, NetWorkEntity.WIFI_NET));
		} else if (activeInfo == null) {
			// TODO 当前无网络连接
			MyApplication.isNetConnected = false;
			NetWorkDataChanger.getInstance().notifyDataChanged(NetWorkEntity.getInstance(false, -1));
		}
		if (MyApplication.isNetConnected) {
			if (!IMPushManager.getInstance(context).baiduPushIsConneted()) {
				IMPushManager.getInstance(context).startPush();
			}
		}
		Trace.d("mobile:" + mobileInfo.isConnected() + "wifi:" + wifiInfo.isConnected() + "active:" + activeInfo);
	} // 如果无网络连接activeInfo为null
}
