package cn.zsmy.akm.entity;

import java.util.List;

/**
 * Created by qinwei on 2016/1/10 18:10
 */
public class ChatList {


    /**
     * code : SUCC
     * message : 返回聊天列表数据成功
     * data : [{"_id":"5dc05b01-0d70-44d7-9d67-c49eb1663f93","caseId":"31e867d7-567b-42de-9f28-af407abbf8c7","receiverId":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","receiverName":null,"senderName":null,"senderId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","content":"你好，医生已经接受您的这次咨询，请及时回复。","voice_length":null,"type":"notify","status":null,"timestamp":1452775147000,"isRead":null}]
     */

    private String code;
    private String message;
    /**
     * _id : 5dc05b01-0d70-44d7-9d67-c49eb1663f93
     * caseId : 31e867d7-567b-42de-9f28-af407abbf8c7
     * receiverId : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * receiverName : null
     * senderName : null
     * senderId : bcf0735f-c335-4897-8ea3-ed14b25f23b0
     * content : 你好，医生已经接受您的这次咨询，请及时回复。
     * voice_length : null
     * type : notify
     * status : null
     * timestamp : 1452775147000
     * isRead : null
     */

    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String _id;
        private String caseId;
        private String receiverId;
        private Object receiverName;
        private Object senderName;
        private String senderId;
        private String content;
        private Object voice_length;
        private String type;
        private Object status;
        private long timestamp;
        private Object isRead;

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setCaseId(String caseId) {
            this.caseId = caseId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
        }

        public void setReceiverName(Object receiverName) {
            this.receiverName = receiverName;
        }

        public void setSenderName(Object senderName) {
            this.senderName = senderName;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setVoice_length(Object voice_length) {
            this.voice_length = voice_length;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public void setIsRead(Object isRead) {
            this.isRead = isRead;
        }

        public String get_id() {
            return _id;
        }

        public String getCaseId() {
            return caseId;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public Object getReceiverName() {
            return receiverName;
        }

        public Object getSenderName() {
            return senderName;
        }

        public String getSenderId() {
            return senderId;
        }

        public String getContent() {
            return content;
        }

        public Object getVoice_length() {
            return voice_length;
        }

        public String getType() {
            return type;
        }

        public Object getStatus() {
            return status;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public Object getIsRead() {
            return isRead;
        }
    }
}