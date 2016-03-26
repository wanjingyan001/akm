package cn.zsmy.akm.widget.row.expand;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import cn.zsmy.akm.R;
import cn.zsmy.akm.widget.row.BaseRowDescriptor;
import cn.zsmy.akm.widget.row.BaseRowView;
import cn.zsmy.akm.widget.row.OnRowClickListener;

public class EditorRowView extends BaseRowView implements OnClickListener, TextWatcher {
	private TextView mWidgetRowLabel;

	private OnRowClickListener listener;
	private EditorRowDescriptor rowDescriptor;

	private EditText mWidgetValueEdt;

	public EditorRowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView();
	}

	public EditorRowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView();
	}

	public EditorRowView(Context context) {
		super(context);
		initializeView();
	}

	public void setOnRowClickListener(OnRowClickListener onRowClickListener) {
		this.listener = onRowClickListener;
	}

	private void initializeView() {
		LayoutInflater.from(context).inflate(R.layout.widget_general_editor_row, this);
		mWidgetValueEdt = (EditText) findViewById(R.id.mWidgetValueEdt);
		mWidgetRowLabel = (TextView) findViewById(R.id.mWidgetRowLabel);
		mWidgetValueEdt.addTextChangedListener(this);
	}

	@Override
	public void onClick(View v) {
		if (listener != null) {
			listener.onRowClick(this, rowDescriptor.action);
		}
	}

	@Override
	public void initializeData(BaseRowDescriptor baseRowDescriptor, OnRowClickListener listener) {
		this.listener = listener;
		this.rowDescriptor = (EditorRowDescriptor) baseRowDescriptor;

	}

	public void notifyDataChanged() {
		if (rowDescriptor != null) {
			mWidgetRowLabel.setText(rowDescriptor.label);
			mWidgetValueEdt.setText(rowDescriptor.value);
		} else {
			setVisibility(View.GONE);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		rowDescriptor.value = s.toString();
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

}
