package cn.zsmy.akm.widget.row.expand;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import cn.zsmy.akm.R;
import cn.zsmy.akm.widget.row.BaseRowDescriptor;
import cn.zsmy.akm.widget.row.BaseRowView;
import cn.zsmy.akm.widget.row.OnRowClickListener;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class GeneralCheckboxRowView extends BaseRowView implements OnCheckedChangeListener {
	private CheckBox mWidgetRowCheckedCb;
	private ImageView mWidgetRowIconImg;
	private TextView mWidgetRowLabel;
	private GeneralCheckboxRowDescriptor descriptor;
	private OnRowClickListener listener;

	public GeneralCheckboxRowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView();
	}

	public GeneralCheckboxRowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView();
	}

	public GeneralCheckboxRowView(Context context) {
		super(context);
		initializeView();
	}

	private void initializeView() {
		LayoutInflater.from(context).inflate(R.layout.widget_general_checkbox_row, this);
		mWidgetRowIconImg = (ImageView) findViewById(R.id.mWidgetRowIconImg);
		mWidgetRowLabel = (TextView) findViewById(R.id.mWidgetRowLabel);
		mWidgetRowCheckedCb = (CheckBox) findViewById(R.id.mWidgetRowCheckedCb);
	}

	public void initializeData(GeneralCheckboxRowDescriptor rowDescriptor, OnRowClickListener listener) {
		this.listener = listener;
		this.descriptor = rowDescriptor;
	}

	@Override
	public void initializeData(BaseRowDescriptor baseRowDescriptor, OnRowClickListener listener) {
		this.listener = listener;
		this.descriptor = (GeneralCheckboxRowDescriptor) baseRowDescriptor;

	}

	public void notifyDataChanged() {
		if (descriptor != null) {
			mWidgetRowLabel.setText(descriptor.label);
			mWidgetRowIconImg.setImageResource(descriptor.iconResId);
			Drawable drawable = getResources().getDrawable(descriptor.checkboxResId);
			mWidgetRowCheckedCb.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null);
			mWidgetRowCheckedCb.setChecked(descriptor.isChecked);
			mWidgetRowCheckedCb.setOnCheckedChangeListener(this);
			this.setTag(descriptor.isChecked);
			setBackgroundResource(R.drawable.widgets_general_row_select);
		} else {
			setVisibility(View.GONE);
		}
	}

	public void setOnRowClickListener(OnRowClickListener listener) {
		this.listener = listener;
	}

	/**
	 * 设置 checkbox 选中监听事件
	 * 
	 * @param listener
	 *            选中状态改变 监听事件
	 */
	public void setOnCheckedChangedListener(OnCheckedChangeListener listener) {
		mWidgetRowCheckedCb.setOnCheckedChangeListener(listener);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (descriptor.action != null) {
			this.setTag(isChecked);
			listener.onRowClick(this, descriptor.action);
		}
	}

}
