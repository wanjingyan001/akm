package cn.zsmy.akm.doctor.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.doctor.R;

/**
 * 自定义ExpandableListView
 * Created by wanjingyan
 * on 2015/12/15 09:46.
 */
public class MyExpandableListView extends ExpandableListView {
    private Context context;
    private int[] titleImage = {R.mipmap.ic_home_admissions,
            R.mipmap.ic_home_casehistory, R.mipmap.ic_home_interactive,
            R.mipmap.ic_home_schedule, R.mipmap.ic_home_personal_center};//ExpandableListView显示的图片
    private int[] colors = {R.color.app_main_color,
            R.color.font_green,
            R.color.font_yellow,
            R.color.font_red,
            R.color.font_pink};//列表项的主题颜色
    private int[] title = {R.string.contact_office, R.string.case_history, R.string.interactive,
            R.string.schedule, R.string.personal};//列表项标题
    private String[][] child_title = {{"普通患者", "VIP患者", "留言中心"},
            {"我的病人", "收藏病历"}, {"病历", "学术圈"}, {}, {}};//子列表项标题
    public static ExpandInfoAdapter adapter;

    public MyExpandableListView(Context context) {
        super(context);
        this.context = context;
        this.setGroupIndicator(null);
        this.setVerticalScrollBarEnabled(false);
        adapter = new ExpandInfoAdapter();
        this.setAdapter(adapter);
    }

    public MyExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static BaseExpandableListAdapter getMyAdapter() {
        return adapter;
    }

    /**
     * 自定义适配器
     */
    public class ExpandInfoAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return title.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return child_title[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return title[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child_title[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ParentHolder holder;
            if (convertView == null || convertView.getTag() == null) {
                holder = new ParentHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_home_operation, null);
                holder.initializeView(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ParentHolder) convertView.getTag();
            }
            holder.initializeData(groupPosition);
            return convertView;
        }

        class ParentHolder extends QBaseViewHolder {
            private ImageView pTitleImage;
            private TextView pTitle;
            private TextView pExplanation;

            @Override
            public void initializeView(View view) {
                pTitleImage = ((ImageView) view.findViewById(R.id.title_image));
                pTitle = ((TextView) view.findViewById(R.id.expand_parent_title));
                pExplanation = ((TextView) view.findViewById(R.id.expand_parent_explanation));
            }

            @Override
            public void initializeData(int position) {
                pTitleImage.setImageResource(titleImage[position]);
                pTitle.setText(title[position]);
                pTitle.setTextColor(colors[position]);
            }
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder holder;
            if (convertView == null || convertView.getTag() == null) {
                holder = new ChildHolder(childPosition);
                convertView = LayoutInflater.from(context).inflate(R.layout.item_child_operation, null);
                holder.initializeView(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }
            holder.initializeData(groupPosition);
            return convertView;
        }


        class ChildHolder extends QBaseViewHolder {
            private TextView cTitle;
            private TextView cExplanation;
            private int childPosition;

            public ChildHolder(int childPosition) {
                this.childPosition = childPosition;
            }

            @Override
            public void initializeView(View view) {
                cTitle = ((TextView) view.findViewById(R.id.expand_child_title));
                cExplanation = ((TextView) view.findViewById(R.id.expand_child_explanation));
            }

            @Override
            public void initializeData(int groupPosition) {
                cTitle.setText(child_title[groupPosition][childPosition]);
                cTitle.setTextColor(colors[groupPosition]);
            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
