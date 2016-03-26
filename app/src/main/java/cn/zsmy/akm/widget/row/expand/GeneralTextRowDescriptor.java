package cn.zsmy.akm.widget.row.expand;


import cn.zsmy.akm.widget.row.BaseRowDescriptor;
import cn.zsmy.akm.widget.row.tool.RowActionEnum;
import cn.zsmy.akm.widget.row.tool.RowClassEnum;

public class GeneralTextRowDescriptor extends BaseRowDescriptor {
	
	public String label;

	public String value;

	public GeneralTextRowDescriptor( String label, RowActionEnum action) {
		
		this.label = label;
		this.action = action;
		this.clazz = RowClassEnum.GeneralTextRowView;
	}

	public GeneralTextRowDescriptor(String label, String value, RowActionEnum action) {
		
		this.label = label;
		this.action = action;
		this.value = value;
		this.clazz = RowClassEnum.GeneralTextRowView;
	}
}
