package cn.zsmy.akm.doctor.chat.im;

import android.app.Activity;
import android.os.Bundle;

import cn.zsmy.akm.doctor.chat.bean.Message;
import cn.zsmy.akm.doctor.chat.bean.MessageType;


public class MainActivity extends Activity {
	private IMDataWatcher watcher = new IMDataWatcher() {

		@Override
		public void updateMessage(Message oldMessage, Message newMessage) {

		}


		@Override
		public void receiverMessage(Message message) {

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
//		IMPushManager.getInstance(this).startPush();
		Message message = new Message();
		message.setType(MessageType.txt);
		IMPushManager.getInstance(this).sendMessage(message);
	}

	@Override
	protected void onResume() {
		super.onResume();
		IMPushManager.getInstance(getApplicationContext()).addObserver(watcher);
	}

	@Override
	protected void onPause() {
		super.onPause();
		IMPushManager.getInstance(getApplicationContext()).removeObserver(watcher);
	}

}
