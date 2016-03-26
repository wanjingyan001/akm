package cn.zsmy.akm.chat.receiver;

public class NetWorkEntity {
	public static final int MOBILE_NET = 1;
	public static final int WIFI_NET = 2;
	public boolean isConnected;
	public int connectType;

	@Override
	public String toString() {
		return "NetWorkEntity [isConnected=" + isConnected + ", connectType=" + connectType + "]";
	}

	private NetWorkEntity(boolean isConnected, int connectType) {
		super();
		this.isConnected = isConnected;
		this.connectType = connectType;
	}

	public static NetWorkEntity getInstance(boolean isConnected, int connectType) {
		return new NetWorkEntity(isConnected, connectType);
	}

}
