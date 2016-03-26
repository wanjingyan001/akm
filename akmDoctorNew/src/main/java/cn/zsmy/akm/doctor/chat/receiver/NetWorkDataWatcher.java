package cn.zsmy.akm.doctor.chat.receiver;

import java.util.Observable;
import java.util.Observer;

public abstract class NetWorkDataWatcher implements Observer {
	@Override
	public final void update(Observable observable, Object data) {
		if (data != null && data instanceof NetWorkEntity) {
			netConnectStateChanged((NetWorkEntity) data);
		}
	}

	protected abstract void netConnectStateChanged(NetWorkEntity netWork);

}
