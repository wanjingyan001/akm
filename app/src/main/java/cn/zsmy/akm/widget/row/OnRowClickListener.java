package cn.zsmy.akm.widget.row;

import android.view.View;

import cn.zsmy.akm.widget.row.tool.RowActionEnum;


public interface OnRowClickListener extends BaseRowViewClickListener {
	void onRowClick(View rowView, RowActionEnum action);
}
