package cn.zsmy.akm.widget.row;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import cn.zsmy.akm.http.Trace;
import cn.zsmy.akm.widget.row.tool.DensityUtil;

public class ContainerView extends LinearLayout {

	private Context context;
	private OnRowClickListener listener;
	private ContainerDescriptor containerDescriptor;

	public ContainerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView(context);
	}

	public ContainerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView(context);
	}

	public ContainerView(Context context) {
		super(context);
		initializeView(context);
	}

	private void initializeView(Context context) {
		this.context = context;
		setOrientation(VERTICAL);
	}

	public void initializeData(ContainerDescriptor containerDescriptor,
			OnRowClickListener listener) {
		this.containerDescriptor = containerDescriptor;
		this.listener = listener;
	}

	public void notifyDataChanged() {
		if (containerDescriptor.groupDescriptors != null
				&& containerDescriptor.groupDescriptors.size() > 0) {
			removeAllViews();
			GroupView groupView = null;
			

			for (int i = 0; i < containerDescriptor.groupDescriptors.size(); i++) {
				LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.WRAP_CONTENT);
				if(i!=0)
				params.topMargin = DensityUtil.dip2px(context, 14);
				groupView = new GroupView(context);
//				groupView.setBackgroundResource(resid);
				groupView.initializeData(
						containerDescriptor.groupDescriptors.get(i), listener);
				groupView.notifyDataChanged();
				addView(groupView, params);
			}
		} else {
			setVisibility(View.GONE);
		}
	}
}
