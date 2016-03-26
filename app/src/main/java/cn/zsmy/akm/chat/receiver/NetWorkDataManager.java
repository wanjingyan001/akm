package cn.zsmy.akm.chat.receiver;

import java.util.Observer;

public class NetWorkDataManager {
	private static NetWorkDataManager mInstance;

	public static NetWorkDataManager getInstance() {
		if (mInstance == null) {
			mInstance = new NetWorkDataManager();
		}
		return mInstance;
	}

	public void addObserver(Observer watcher) {
		NetWorkDataChanger.getInstance().addObserver(watcher);
	}

	public void removeObserver(Observer watcher) {
		NetWorkDataChanger.getInstance().deleteObserver(watcher);
	}

	public void removeObservers() {
		NetWorkDataChanger.getInstance().deleteObservers();
	}

}
