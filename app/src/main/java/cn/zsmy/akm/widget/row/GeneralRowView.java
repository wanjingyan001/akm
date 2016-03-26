package cn.zsmy.akm.widget.row;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import cn.zsmy.akm.R;
import cn.zsmy.akm.http.Trace;

public class GeneralRowView extends BaseRowView implements OnClickListener {
	private ImageView mWidgetRowActionImg;
	private ImageView mWidgetRowIconImg;
	private TextView mWidgetRowLabel;
	private OnRowClickListener listener;
	private GeneralRowDescriptor rowDescriptor;
	private TextView mWidgetRowValueLabel;

	public GeneralRowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView();
	}

	public GeneralRowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView();
	}

	public GeneralRowView(Context context) {
		super(context);
		initializeView();
	}

	public void setOnRowClickListener(OnRowClickListener onRowClickListener) {
		this.listener = onRowClickListener;
	}

	private void initializeView() {
		LayoutInflater.from(context).inflate(R.layout.widget_general_row, this);
		mWidgetRowIconImg = (ImageView) findViewById(R.id.mWidgetRowIconImg);
		mWidgetRowLabel = (TextView) findViewById(R.id.mWidgetRowLabel);
		mWidgetRowValueLabel = (TextView) findViewById(R.id.mWidgetRowValueLabel);
		mWidgetRowActionImg = (ImageView) findViewById(R.id.mWidgetRowActionImg);
	}


	@Override
	public void onClick(View v) {
		if (listener != null) {
			listener.onRowClick(this,rowDescriptor.action);
		}
	}

	@Override
	public void initializeData(BaseRowDescriptor baseRowDescriptor,
			OnRowClickListener listener) {
		this.listener = listener;
		this.rowDescriptor = (GeneralRowDescriptor)baseRowDescriptor;
		
	}
	public void notifyDataChanged() {
		if (rowDescriptor != null) {
			if(rowDescriptor.iconResId==0){
				mWidgetRowIconImg.setVisibility(View.GONE);
			}else{
				mWidgetRowIconImg.setBackgroundResource(rowDescriptor.iconResId);
				mWidgetRowIconImg.setVisibility(View.VISIBLE);
			}
			mWidgetRowActionImg.setBackgroundResource(R.drawable.action_row);
			mWidgetRowLabel.setText(rowDescriptor.label);
			if(TextUtils.isEmpty(rowDescriptor.value)){
				mWidgetRowValueLabel.setVisibility(View.GONE);
			}else{
				mWidgetRowValueLabel.setVisibility(View.VISIBLE);
				mWidgetRowValueLabel.setText(rowDescriptor.value);
			}
			if (rowDescriptor.action != null) {
				setBackgroundResource(R.drawable.widgets_general_row_select);
				mWidgetRowActionImg.setVisibility(View.VISIBLE);
				setOnClickListener(this);
			} else {
				mWidgetRowActionImg.setVisibility(View.GONE);
			}
		} else {
			setVisibility(View.GONE);
		}
	}

}
