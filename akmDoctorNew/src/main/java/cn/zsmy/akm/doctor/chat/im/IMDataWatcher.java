package cn.zsmy.akm.doctor.chat.im;

import java.util.Observable;
import java.util.Observer;

import cn.zsmy.akm.doctor.chat.bean.Message;


/**
 * 消息观察者
 * 
 * @author 秦伟
 * @version 1.0
 * @created 创建时间: 2015-8-16 下午4:54:40
 */
public abstract class IMDataWatcher implements Observer {

	@Override
	public void update(Observable observable, Object data) {
		if (data instanceof Message) {
			receiverMessage((Message) data);
		} else if (data instanceof Message[]) {
			Message[] messages = (Message[]) data;
			updateMessage(messages[0], messages[1]);
		}
	}
;
	public abstract void updateMessage(Message oldMessage, Message newMessage);

	/**
	 * 接受消息
	 * 
	 * @param message
	 *            消息内容
	 */
	public abstract void receiverMessage(Message message);

}
