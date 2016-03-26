package cn.zsmy.akm.doctor.chat.bean;

import java.util.List;

/**
 * Created by qinwei on 2016/1/10 18:10
 */
public  class  ChatList{


    /**
     * code : SUCC
     * message : 返回聊天列表数据成功
     * data : [{"_id":"f8a3d8d3-853b-4c8e-9da3-e9bcda4e0b8d","caseId":"4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f","receiverId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","receiverName":null,"senderName":null,"senderId":"0fc52d11-986b-4d88-8279-8b408b8d937b","content":"你好，医生已经接受您的这次咨询，请及时回复。","voice_length":null,"type":"通知","status":null,"timestamp":1452498838000,"isRead":null},{"_id":"9c3fa593-f93c-4e92-97aa-6ee708c08af3","caseId":"4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f","receiverId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","receiverName":null,"senderName":null,"senderId":"0fc52d11-986b-4d88-8279-8b408b8d937b","content":"你好，医生已经接受您的这次咨询，请及时回复。","voice_length":null,"type":"通知","status":null,"timestamp":1452498865000,"isRead":null},{"_id":"f5712e3f-a7ba-46c9-a6d1-108d7116bd3c","caseId":"4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f","receiverId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","receiverName":null,"senderName":null,"senderId":"0fc52d11-986b-4d88-8279-8b408b8d937b","content":"你好，医生已经接受您的这次咨询，请及时回复。","voice_length":null,"type":"通知","status":null,"timestamp":1452498870000,"isRead":null},{"_id":"56723b81-7a08-4ec5-b554-b21f87be7ee3","caseId":"4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f","receiverId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","receiverName":null,"senderName":null,"senderId":"0fc52d11-986b-4d88-8279-8b408b8d937b","content":"你好，医生已经接受您的这次咨询，请及时回复。","voice_length":null,"type":"通知","status":null,"timestamp":1452498871000,"isRead":null},{"_id":"6896deb9-618f-414e-ab72-8bbf556f04c6","caseId":"4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f","receiverId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","receiverName":null,"senderName":null,"senderId":"0fc52d11-986b-4d88-8279-8b408b8d937b","content":"你好，医生已经接受您的这次咨询，请及时回复。","voice_length":null,"type":"通知","status":null,"timestamp":1452498934000,"isRead":null},{"_id":"5a3ab736-5a34-4fb2-b8b9-1c506cf320f9","caseId":"4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f","receiverId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","receiverName":null,"senderName":null,"senderId":"0fc52d11-986b-4d88-8279-8b408b8d937b","content":"你好，医生已经接受您的这次咨询，请及时回复。","voice_length":null,"type":"通知","status":null,"timestamp":1452500877000,"isRead":null},{"_id":"6ca74be1-fb9c-45c5-a02c-4d76ab996e97","caseId":"4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f","receiverId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","receiverName":null,"senderName":null,"senderId":"0fc52d11-986b-4d88-8279-8b408b8d937b","content":"覅","voice_length":"1","type":null,"status":null,"timestamp":1452503658000,"isRead":null},{"_id":"0c84439b-b38e-4017-bb4e-1333be12d39c","caseId":"4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f","receiverId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","receiverName":null,"senderName":null,"senderId":"0fc52d11-986b-4d88-8279-8b408b8d937b","content":"让她与","voice_length":"3","type":null,"status":null,"timestamp":1452503671000,"isRead":null},{"_id":"bc7b18ca-c884-40fc-9330-3d9e902f2daa","caseId":"4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f","receiverId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","receiverName":null,"senderName":null,"senderId":"0fc52d11-986b-4d88-8279-8b408b8d937b","content":"夫妇","voice_length":"2","type":null,"status":null,"timestamp":1452503694000,"isRead":null},{"_id":"af690e4c-c661-4cb5-a462-b4c0444dc55d","caseId":"4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f","receiverId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","receiverName":null,"senderName":null,"senderId":"0fc52d11-986b-4d88-8279-8b408b8d937b","content":"统计局多个","voice_length":"5","type":null,"status":null,"timestamp":1452503707000,"isRead":null}]
     */

    private String code;
    private String message;
    /**
     * _id : f8a3d8d3-853b-4c8e-9da3-e9bcda4e0b8d
     * caseId : 4d4d8d35-f11f-4c7a-9a28-2d5a365a4f7f
     * receiverId : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * receiverName : null
     * senderName : null
     * senderId : 0fc52d11-986b-4d88-8279-8b408b8d937b
     * content : 你好，医生已经接受您的这次咨询，请及时回复。
     * voice_length : null
     * type : 通知
     * status : null
     * timestamp : 1452498838000
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
