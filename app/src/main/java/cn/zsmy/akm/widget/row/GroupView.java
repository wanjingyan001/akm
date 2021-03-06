package cn.zsmy.akm.widget.row;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.zsmy.akm.R;
import cn.zsmy.akm.widget.row.tool.DensityUtil;
import cn.zsmy.akm.widget.row.tool.RowViewFactory;

public class GroupView extends LinearLayout {

    private Context context;
    private OnRowClickListener listener;
    private GroupDescriptor groupDescriptor;
    private View titleContainer;
    private TextView mGroupViewTitleLabel;
    private LinearLayout mGroupViewContainer;

    public GroupView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    public GroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public GroupView(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.widget_general_group, this);
        titleContainer = findViewById(R.id.mGroupViewTitleContainer);
        mGroupViewTitleLabel = (TextView) findViewById(R.id.mGroupViewTitleLabel);
        mGroupViewContainer = (LinearLayout) findViewById(R.id.mGroupViewContainer);
    }

    public void initializeData(GroupDescriptor groupDescriptor, OnRowClickListener listener) {
        this.listener = listener;
        this.groupDescriptor = groupDescriptor;
    }

    public void notifyDataChanged() {
        if (groupDescriptor.baseRowDescriptors != null && groupDescriptor.baseRowDescriptors.size() > 0) {
            if (TextUtils.isEmpty(groupDescriptor.title)) {
                titleContainer.setVisibility(View.GONE);
            } else {
                mGroupViewTitleLabel.setText(groupDescriptor.title);
            }
            setBackgroundColor(getResources().getColor(R.color.white));
            BaseRowView rowView = null;
            View line = null;
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
            if (RowViewFactory.LINE_IS_MATCH_PARENT) {
                params.leftMargin = DensityUtil.dip2px(context, 23);
                params.rightMargin = DensityUtil.dip2px(context, 18);
            } else {
                params.leftMargin = DensityUtil.dip2px(context, 55);
            }
//			params.rightMargin = DensityUtil.dip2px(context, 5);
            for (int i = 0; i < groupDescriptor.baseRowDescriptors.size(); i++) {
                BaseRowDescriptor rowDescriptor = groupDescriptor.baseRowDescriptors.get(i);
                rowView = RowViewFactory.produceRowView(context, rowDescriptor.clazz);
                rowView.initializeData(rowDescriptor, listener);
                rowView.notifyDataChanged();
                mGroupViewContainer.addView(rowView);
                if (i != groupDescriptor.baseRowDescriptors.size() - 1) {
                    line = new View(context);
                    line.setBackgroundColor(getResources().getColor(R.color.widgets_general_row_line));
                    mGroupViewContainer.addView(line, params);
                }
            }
        } else {
            setVisibility(View.GONE);
        }
    }
}
