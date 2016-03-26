package cn.zsmy.akm.widget.row;


import cn.zsmy.akm.widget.row.tool.RowActionEnum;
import cn.zsmy.akm.widget.row.tool.RowClassEnum;

public class GeneralRowDescriptor extends BaseRowDescriptor {
    public int iconResId;
    public String label;

    public String value;

    public GeneralRowDescriptor(int iconResId, String label, RowActionEnum action) {
        this.iconResId = iconResId;
        this.label = label;
        this.action = action;
        this.clazz = RowClassEnum.GeneralRowView;
    }

    public GeneralRowDescriptor(int iconResId, String label, String value, RowActionEnum action) {
        this.iconResId = iconResId;
        this.label = label;
        this.action = action;
        this.value = value;
        this.clazz = RowClassEnum.GeneralRowView;
    }

    public GeneralRowDescriptor(String label, int iconResId, RowActionEnum action) {
        this.label = label;
        this.iconResId = iconResId;
        this.action = action;
    }
}
