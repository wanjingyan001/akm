package cn.zsmy.akm.entity;

/**
 * 消息中心的消息实体类
 * Created by wanjingyan
 * on 2015/12/8 11:16.
 */
public class MessageContent {
    private String messageTime;//消息发送时间
    private String messageContent;//消息内容
    private int messageType;//消息类型

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
