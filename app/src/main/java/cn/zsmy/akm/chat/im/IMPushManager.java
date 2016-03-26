package cn.zsmy.akm.chat.im;

import android.content.Context;
import android.content.Intent;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

import java.util.List;

import cn.zsmy.akm.chat.bean.Message;
import cn.zsmy.akm.chat.bean.MessageStatus;
import cn.zsmy.akm.chat.utils.Constants;
import cn.zsmy.akm.chat.utils.Utils;

public class IMPushManager {
	private static IMPushManager mInstance;
	private Context context;

	public IMPushManager(Context context) {
		this.context = context;
		if (context == null) {
			throw new IllegalArgumentException("you should set paramter context");
		}
	}

	public static IMPushManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new IMPushManager(context);
		}
		return mInstance;
	}

	public void sendMessage(Message message) {
		message.setStatus(MessageStatus.ing);
		Intent intent = new Intent(context, IMPushService.class);
		intent.putExtra(Constants.KEY_MESSAGE_ENTITIES, message);
		context.startService(intent);
		notifyDataChanged(message, null);
	}

	public void notifyProgressUpdate(int percent, Message message) {
		IMDataChanger.getInstance().notifyProgressUpdate(percent, message);
	}

	public void notifyDataChanged(Message oldMessage, Message updatedMessage) {
		IMDataChanger.getInstance().notifyDataChanged(oldMessage, updatedMessage);
	}

	/**
	 * 开启推送
	 */
	public void startPush() {
		PushManager.startWork(context, PushConstants.LOGIN_TYPE_API_KEY, Utils.getMetaValue(context, "api_key"));
		context.startService(new Intent(context, IMPushService.class));
	}

	/**
	 * 设置推送标签
	 * 
	 * @param textTags
	 */
	public void setTags(String textTags) {
		if (PushManager.isPushEnabled(context)) {
			List<String> tags = Utils.getTagsList(textTags);
			PushManager.setTags(context, tags);
		}
	}

	public boolean baiduPushIsConneted() {
		return PushManager.isPushEnabled(context);
	}

	/**
	 * 停止推送
	 */
	public void stopPush() {
		PushManager.stopWork(context);
		context.stopService(new Intent(context, IMPushService.class));
	}

	public void addObserver(IMDataWatcher watcher) {
		IMDataChanger.getInstance().addObserver(watcher);
	}

	public void removeObserver(IMDataWatcher watcher) {
		IMDataChanger.getInstance().deleteObserver(watcher);
	}

	public void removeAllObserver() {
		IMDataChanger.getInstance().deleteObservers();
	}

}
