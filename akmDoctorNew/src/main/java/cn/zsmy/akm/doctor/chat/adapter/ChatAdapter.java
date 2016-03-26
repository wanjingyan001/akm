package cn.zsmy.akm.doctor.chat.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.zsmy.akm.doctor.chat.bean.Message;
import cn.zsmy.akm.doctor.chat.holder.ChatHolder;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.doctor.R;


/**
 * Created by sanz on 2015/12/14 11:27
 * 聊天的适配器
 */
public class ChatAdapter extends BaseAdapter {
    private Context context;
    private List<Message> datas;
    private String TAG = this.getClass().getSimpleName();
    private String locationImagePath;
    public int imgNumber;
    private String senderIcon;
    private String receiverIcon;

    public void setReceiverIcon(String receiverIcon) {
        this.receiverIcon = receiverIcon;
    }

    public void setSenderIcon(String senderIcon) {
        this.senderIcon = senderIcon;
    }

    public ChatAdapter(Context context, List<Message> datas) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        Message message = datas.get(position);
        if (message.getType() != null) {
            switch (message.getType()) {
                case txt:
                    return message.getSenderId().equals(MyApplication.getProfile().getUserId()) ? 1
                            : 0;
                case img:
                    return message.getSenderId().equals(MyApplication.getProfile().getUserId()) ? 3
                            : 2;
                case notify:
                    return message.getSenderId().equals(MyApplication.getProfile().getUserId()) ? 1
                            : 0;
                case advice:
                    return message.getSenderId().equals(MyApplication.getProfile().getUserId()) ? 1
                            : 0;
                default:
                    break;
            }
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatHolder holder = null;
        int type = getItemViewType(position);
        if (convertView == null || convertView.getTag() == null) {
            switch (type) {
                case 0:
                    holder = new ChatTextHolderLeft();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_chat_text_left, null);
                    break;
                case 1:
                    holder = new ChatTextHolderRight();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_chat_text_right, null);
                    break;
                case 2:
                    holder = new ChatImageHolderLeft();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_chat_img_left, null);
                    break;
                case 3:
                    holder = new ChatImageHolderRight();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_chat_img_right, null);
                    break;
            }
            holder.initializeView(convertView);
            convertView.setTag(holder);
        } else {
            switch (type) {
                case 0:
                    holder = (ChatTextHolderLeft) convertView.getTag();
                    break;
                case 1:
                    holder = (ChatTextHolderRight) convertView.getTag();
                    break;
                case 2:
                    holder = (ChatImageHolderLeft) convertView.getTag();
                    break;
                case 3:
                    holder = (ChatImageHolderRight) convertView.getTag();
                    break;
            }
        }
        holder.initializeData(position);
        return convertView;
    }

    public class ChatTextHolderLeft extends ChatHolder {
        private ImageView head_pic;
        private TextView chat_content;

        @Override
        public void initializeView(View view) {
            chat_content = (TextView) view.findViewById(R.id.chat_contentID);
            head_pic = (ImageView) view.findViewById(R.id.head_pic_chatID);
        }

        @Override
        public void initializeData(int position) {
            Message message = datas.get(position);
            if (message != null) {
                if (receiverIcon != null) {
                    ImageLoader.getInstance().displayImage(UrlHelper.IP + receiverIcon, head_pic);
                }
                chat_content.setText(message.getContent());
            }
        }
    }

    public class ChatTextHolderRight extends ChatHolder {
        private ImageView head_pic;
        private TextView chat_content;

        @Override
        public void initializeView(View view) {
            chat_content = (TextView) view.findViewById(R.id.chat_contentID);
            head_pic = (ImageView) view.findViewById(R.id.head_pic_chatID);
        }

        @Override
        public void initializeData(int position) {
            Message message = datas.get(position);
            if (message != null) {
                if (senderIcon != null) {
                    ImageLoader.getInstance().displayImage(UrlHelper.IP + senderIcon, head_pic);
                }
                chat_content.setText(message.getContent());
            }
        }
    }

    public class ChatImageHolderLeft extends ChatHolder {
        private ImageView head_pic, chat_pic;

        @Override
        public void initializeView(View view) {
            head_pic = (ImageView) view.findViewById(R.id.head_pic_chatID);
            chat_pic = (ImageView) view.findViewById(R.id.chat_IAMGE_left_ID);
        }

        @Override
        public void initializeData(int position) {
            Message message = datas.get(position);
            if (message != null) {
                if (receiverIcon != null) {
                    ImageLoader.getInstance().displayImage(UrlHelper.IP + receiverIcon, head_pic);
                }

                ImageLoader.getInstance().displayImage(UrlHelper.IP + message.getContent(), chat_pic);

            }
        }
    }

    public class ChatImageHolderRight extends ChatHolder {
        private ImageView head_pic, chat_pic;

        @Override
        public void initializeView(View view) {
            head_pic = (ImageView) view.findViewById(R.id.head_pic_chatID);
            chat_pic = (ImageView) view.findViewById(R.id.chat_IAMGE_right_ID);
        }

        @Override
        public void initializeData(int position) {
            Message message = datas.get(position);
            if (message != null) {
                if (senderIcon != null) {
                    ImageLoader.getInstance().displayImage(UrlHelper.IP + senderIcon, head_pic);
                }
                if (!TextUtils.isEmpty(message.getContent())) {
                    ImageLoader.getInstance().displayImage(UrlHelper.IP + message.getContent(), chat_pic);
                } else {
                    ImageLoader.getInstance().displayImage("file://" + locationImagePath, chat_pic);
                }
            }
        }
    }

    public String getLocationImagePath() {
        return locationImagePath;
    }

    public void setLocationImagePath(String locationImagePath) {
        this.locationImagePath = locationImagePath;
    }

}
