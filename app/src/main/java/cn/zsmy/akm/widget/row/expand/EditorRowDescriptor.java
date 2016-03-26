package cn.zsmy.akm.widget.row.expand;


import cn.zsmy.akm.widget.row.BaseRowDescriptor;
import cn.zsmy.akm.widget.row.tool.RowActionEnum;
import cn.zsmy.akm.widget.row.tool.RowClassEnum;

public class EditorRowDescriptor extends BaseRowDescriptor {
	public String label;
	public String value;

	public EditorRowDescriptor(String label,String value, RowActionEnum action) {
		this.label = label;
		this.value=value;
		this.action = action;
		this.clazz = RowClassEnum.EditorRowView;
	}
}
