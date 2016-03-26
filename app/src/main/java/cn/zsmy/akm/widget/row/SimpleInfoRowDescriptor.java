package cn.zsmy.akm.widget.row;


import cn.zsmy.akm.widget.row.tool.RowActionEnum;
import cn.zsmy.akm.widget.row.tool.RowClassEnum;

public class SimpleInfoRowDescriptor extends BaseRowDescriptor {
	public SimpleInfo info;
	public SimpleInfoRowDescriptor(SimpleInfo info, RowActionEnum action) {
		this.info = info;
		this.action = action;
		this.clazz= RowClassEnum.SimpleInfoRowView;
	}
	
	public static class SimpleInfo{
		public String id;
		public String iconUrl;
		public String account;

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getIconUrl() {
			return iconUrl;
		}

		public void setIconUrl(String iconUrl) {
			this.iconUrl = iconUrl;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
}
