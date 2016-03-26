package cn.zsmy.akm.doctor.chat.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 消息实体bean
 *
 * @author Administrator
 */
@DatabaseTable
public class Message implements Serializable {
    /**
     * 描述
     */
    private static final long serialVersionUID = 1L;
    public static final String TIMESTAMP = "timestamp";
    public static final String SENDERID = "senderId";
    public static final String RECEIVERID = "receiverId";
    public static final String ISREAD = "isRead";
    public static final String CASEID = "caseId";
    @DatabaseField(id = true)
    private String _id;// 消息id
    @DatabaseField
    private String receiverId;// 消息接受者id
    @DatabaseField
    private String receiverName;// 消息接受者名称
    @DatabaseField
    private String senderName;// 消息发送着名称

    @DatabaseField
    private String receiverIcon;//消息接收者头像

    @DatabaseField
    private String senderIcon;//消息发送者头像
    @DatabaseField
    private String senderId;// 消息发送这id
    @DatabaseField
    private String content;// 文本消息or系统通知
    @DatabaseField
    private long voice_length;//音频长度
    @DatabaseField
    private String receiver_image;// 图片消息
    @DatabaseField
    private MessageType type;// 消息类型
    @DatabaseField
    private MessageStatus status;// 消息的状态 android 字段

    @DatabaseField
    public long timestamp;// 时间戳
    @DatabaseField
    public boolean isRead;// 消息是否已读 android 这边维护
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<Attachment> attachments;// 文件上传用，例如语音，图片文件的上传
    @DatabaseField
    private String caseId;

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        if (_id != null) {
            return _id.hashCode();
        } else {
            return -1;
        }
    }

    public String getReceiver_image() {
        return receiver_image;
    }

    public void setReceiver_image(String receiver_image) {
        this.receiver_image = receiver_image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public long getVoice_length() {
        return voice_length;
    }

    public void setVoice_length(long voice_length) {
        this.voice_length = voice_length;
    }

    public String getReceiverIcon() {
        return receiverIcon;
    }

    public String getSenderIcon() {
        return senderIcon;
    }

    public void setSenderIcon(String senderIcon) {
        this.senderIcon = senderIcon;
    }

    public void setReceiverIcon(String receiverIcon) {
        this.receiverIcon = receiverIcon;
    }

//    public Conversation copyTo() {
//        Conversation conversation=new Conversation();
//        conversation.setContent(getContent());
//        conversation.setTimestamp(getTimestamp());
//        conversation.setType(getType());
//        conversation.setStatus(getStatus());
//        if(senderId.equals(MyApplication.getProfile().getUserId())) {
//            conversation.setIcon(getReceiverIcon());
//            conversation.setTitle(receiverName);
//            conversation.setId(receiverId);
//        }else{
//            Conversation tmp= ConversationController.queryById(senderId);
//            if(tmp!=null){
//                tmp.setUnReadNumber(tmp.getUnReadNumber()+1);
//            }else{
//                conversation.setUnReadNumber(1);
//            }
//            conversation.setIcon(senderIcon);
//            conversation.setTitle(senderName);
//            conversation.setId(senderId);
//        }
//        return conversation;
//    }
}
