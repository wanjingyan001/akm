package cn.zsmy.akm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.R;
import cn.zsmy.akm.entity.SystemNotice;

/**
 * 消息中心列表适配器
 * Created by wanjingyan
 * on 2015/12/8 11:15.
 */
public class MessageAdapter extends BaseAdapter {
    private Context context;
    private List<SystemNotice.DataEntity> contents;

    public MessageAdapter(Context context, List<SystemNotice.DataEntity> contents) {
        this.context = context;
        this.contents = contents;
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public SystemNotice.DataEntity getItem(int position) {
        return contents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_message_list_item, null);
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.initializeData(position);
        return convertView;
    }

    public class ViewHolder extends QBaseViewHolder {
        private TextView mMessageTime;
        private TextView mMessageContent;
        private TextView mMessageTitle;

        @Override
        public void initializeView(View view) {
            mMessageTitle = ((TextView) view.findViewById(R.id.message_title));
            mMessageTime = ((TextView) view.findViewById(R.id.messags_time));
            mMessageContent = ((TextView) view.findViewById(R.id.message_centent));
        }

        @Override
        public void initializeData(int position) {
            SystemNotice.DataEntity dataEntity = contents.get(position);
            mMessageTitle.setText(dataEntity.getTitle());
            mMessageContent.setText(dataEntity.getContent());
            Object time = dataEntity.getModifyTime();
            if (time != null) {
                mMessageTime.setText((String) time);
            }
        }
    }

}
