package cn.zsmy.akm.chat.receiver;

import java.util.Observable;

public class NetWorkDataChanger extends Observable {
	private static NetWorkDataChanger mInstance;

	public static NetWorkDataChanger getInstance() {
		if (mInstance == null) {
			mInstance = new NetWorkDataChanger();
		}
		return mInstance;
	}

	public void notifyDataChanged(NetWorkEntity entity) {
		setChanged();
		notifyObservers(entity);
	}
}
