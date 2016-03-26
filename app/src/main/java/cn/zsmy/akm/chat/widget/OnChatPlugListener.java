package cn.zsmy.akm.chat.widget;

public interface OnChatPlugListener {
	void sendTxt(String txt);
	void recording();
	void stopRecoding();
	void sendVoice(String path);
	void sendPicture(String path);
}
