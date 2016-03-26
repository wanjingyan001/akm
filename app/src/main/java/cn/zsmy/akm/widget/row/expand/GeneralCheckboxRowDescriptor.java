package cn.zsmy.akm.widget.row.expand;


import cn.zsmy.akm.widget.row.BaseRowDescriptor;
import cn.zsmy.akm.widget.row.tool.RowActionEnum;
import cn.zsmy.akm.widget.row.tool.RowClassEnum;

public class GeneralCheckboxRowDescriptor extends BaseRowDescriptor {
	public int iconResId;
	public int checkboxResId;
	public String label;
	public boolean isChecked;

	public GeneralCheckboxRowDescriptor(int iconResId, String label, boolean isChecked, int checkboxResId, RowActionEnum action) {
		this.iconResId = iconResId;
		this.label = label;
		this.clazz = RowClassEnum.GeneralCheckboxRowView;
		this.checkboxResId = checkboxResId;
		this.isChecked = isChecked;
		this.action = action;
	}
}
