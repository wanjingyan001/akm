package cn.zsmy.akm.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.wei.library.adapter.QBaseViewHolder;
import cn.zsmy.akm.R;
import cn.zsmy.akm.chat.utils.Trace;
import cn.zsmy.akm.db.PushMessage.PushMessage;
import cn.zsmy.akm.utils.DateUtils;

/**
 * Created by Administrator on 2016/3/15 13:38.
 */
public class PushMessageAdapter extends BaseAdapter {
    private Context context;
    private List<PushMessage> pushMessages;


    public PushMessageAdapter(Context context, List<PushMessage> pushMessages) {
        this.context = context;
        this.pushMessages = pushMessages;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return pushMessages.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public PushMessage getItem(int position) {
        return pushMessages.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
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
        private TextView mMessageContent;
        private TextView mMessageTitle;
        private TextView mMessageTime;

        @Override
        public void initializeView(View view) {
            mMessageTitle = ((TextView) view.findViewById(R.id.message_title));
            mMessageContent = ((TextView) view.findViewById(R.id.message_centent));
            mMessageTime = ((TextView) view.findViewById(R.id.messags_time));
        }

        @Override
        public void initializeData(int position) {
            PushMessage dataEntity = pushMessages.get(position);
            Trace.d(dataEntity.toString());
            mMessageTitle.setText(dataEntity.getTitle());
            mMessageContent.setText(dataEntity.getContent());
            mMessageTime.setText(DateUtils.getDateToString(dataEntity.getTimestamp(), 3));
            if (dataEntity.isRead()) {
                mMessageTitle.setTextColor(Color.rgb(131, 131, 131));
                mMessageContent.setTextColor(Color.rgb(131, 131, 131));
                mMessageTime.setTextColor(Color.rgb(131, 131, 131));
            }
        }
    }
}
