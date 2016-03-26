package cn.zsmy.akm.widget.row.tool;


import android.content.Context;

import cn.zsmy.akm.widget.row.BaseRowView;
import cn.zsmy.akm.widget.row.GeneralRowView;
import cn.zsmy.akm.widget.row.SimpleInfoRowView;
import cn.zsmy.akm.widget.row.expand.EditorRowView;
import cn.zsmy.akm.widget.row.expand.GeneralCheckboxRowView;
import cn.zsmy.akm.widget.row.expand.GeneralTextRowView;

public class RowViewFactory {

	public static boolean LINE_IS_MATCH_PARENT=true;
	public static BaseRowView produceRowView(Context context, RowClassEnum clazz) {
		BaseRowView rowView = null;
		switch (clazz) {
		case GeneralRowView:
			rowView = new GeneralRowView(context);
			break;
		case SimpleInfoRowView:
			rowView = new SimpleInfoRowView(context);
			break;
		case GeneralCheckboxRowView:
			rowView = new GeneralCheckboxRowView(context);
			break;
		case EditorRowView:
			rowView = new EditorRowView(context);
			break;
		case GeneralTextRowView:
			rowView = new GeneralTextRowView(context);
			break;
		default:
			break;
		}
		return rowView;
	}
}
