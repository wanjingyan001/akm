package cn.zsmy.akm.doctor.admissions.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.wei.library.widget.BadgeView;
import cn.zsmy.akm.doctor.admissions.bean.Contact;
import cn.zsmy.akm.doctor.chat.utils.StringUtils;
import cn.zsmy.akm.doctor.http.Trace;
import cn.zsmy.akm.doctor.utils.DateUtils;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sanz on 2016/1/19 15:41
 */
public class MyexpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<String> groupList;
    private ArrayList<List<Contact.DataEntity>> childList;

    public MyexpandableListAdapter(Context context, ArrayList<List<Contact.DataEntity>> childList, List<String> groupList) {
        this.context = context;
        this.childList = childList;
        this.groupList = groupList;
        inflater = LayoutInflater.from(context);
    }

    // 返回父列表个数
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    // 返回子列表个数
    @Override
    public int getChildrenCount(int groupPosition) {
        if (childList.size() != 1) {
            return childList.get(groupPosition).size();
        }
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return childList.get(groupPosition).get(childPosition);
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

        return true;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = inflater.inflate(R.layout.item_group_contact_office, null);
            groupHolder.initializeView(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.initializeData(groupPosition);
        return convertView;
    }

    class GroupHolder {
        TextView textView;

        public void initializeView(View view) {
            textView = (TextView) view.findViewById(R.id.contact_list_group_tex);
        }

        public void initializeData(int groupPosition) {
            String title = groupList.get(groupPosition);
            textView.setText(title);
        }

    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        Trace.d("--------" + childList.size());
        if (childList.size() != 1) {
            if (convertView == null) {
                childHolder = new ChildHolder();
                convertView = inflater.inflate(R.layout.item_child_contact_office, null);
                childHolder.initializeView(convertView);
                convertView.setTag(childHolder);
            } else {
                childHolder = (ChildHolder) convertView.getTag();
            }
            childHolder.initializeData(groupPosition, childPosition);
            return convertView;
        } else {
            EmptyHolder holder = new EmptyHolder();
            convertView = inflater.inflate(R.layout.item_homelist_empty, null);
            holder.initializeView(convertView);
            return convertView;
        }

    }

    class ChildHolder {
        private TextView name_tex, contact_num, content_tex;
        private CircleImageView head_pic;
        private TextView contactTime;

        public void initializeView(View view) {
            name_tex = (TextView) view.findViewById(R.id.contact_name);
            contact_num = (TextView) view.findViewById(R.id.contact_chatInfoNum);
            head_pic = (CircleImageView) view.findViewById(R.id.head_pic);
            content_tex = (TextView) view.findViewById(R.id.contact_content);
            contactTime = ((TextView) view.findViewById(R.id.contact_time));
        }

        public void initializeData(int groupPosition, int childPosition) {
            Contact.DataEntity dataEntity = childList.get(groupPosition).get(childPosition);
            if (dataEntity.getPatient() != null) {
                String name = dataEntity.getPatient().getName();
                int num = dataEntity.getPatient().getCaseNum();
                if (dataEntity.getInquirer() != null) {
                    if (dataEntity.getInquirer().getNickname() != null) {
                        name_tex.setText(dataEntity.getInquirer().getNickname());
                    } else if (dataEntity.getInquirer().getPhone() != null) {
                        name_tex.setText(StringUtils.getPhone(dataEntity.getInquirer().getPhone()));
                    }
                } else {
                    name_tex.setText(null);
                }
                //修改内容；接诊室如是当天接诊消息，只需显示具体时间
                //修改时间:2016/3/3
                //修改人:yutaotao
                long time=DateUtils.getCurrentTime();
                String timeNew=DateUtils.getDateToString(time,2);
                String timeOld=DateUtils.getDateToString(dataEntity.getModifyTime(), 2);
                if (timeOld.equals(timeNew)){
                    String datatime=DateUtils.getDateToString(dataEntity.getModifyTime(), 3);
                    contactTime.setText(datatime.substring(11, datatime.length()));
                    System.out.println("接诊时间1:" + datatime.substring(11, datatime.length()));
                }else {
                    contactTime.setText(timeOld);
                    System.out.println("接诊时间2:"+timeOld);
                }
//                contactTime.setText(DateUtils.getDateToString(dataEntity.getModifyTime(), 2));
                if (dataEntity.getFlag() != null) {
                    String flag = String.valueOf(dataEntity.getFlag());
                    if (flag.equals("1")) {
                        initBageViewConfig(contact_num, num);
                    } else if (flag.equals("2")) {
                        contact_num.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.contact_chat_type_phone));
                    }

                }

                if (null != dataEntity.getLastChatContent()) {
                    content_tex.setText(dataEntity.getLastChatContent());
                } else {
                    if (null != dataEntity.getContent()) {
                        content_tex.setText(dataEntity.getContent());
                    }
                }
                if (dataEntity.getInquirer() != null) {
                    if (dataEntity.getInquirer().getAvatar() != null) {
                        ImageLoader.getInstance().displayImage(UrlHelper.IP + dataEntity.getInquirer().getAvatar(), head_pic);
                    }
                }
            }
        }
    }


    class EmptyHolder {
        private TextView mListEmpty;

        public void initializeView(View view) {
            mListEmpty = ((TextView) view.findViewById(R.id.home_list_empty));
        }
    }


    public void initBageViewConfig(TextView contact_num, int num) {
        BadgeView badgeView = new BadgeView(context);
        badgeView.setTargetView(contact_num);
        badgeView.setBadgeGravity(Gravity.CENTER);
        badgeView.setBadgeCount(num);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
