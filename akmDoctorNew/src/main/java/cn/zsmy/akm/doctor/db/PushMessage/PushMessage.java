package cn.zsmy.akm.doctor.db.PushMessage;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import cn.zsmy.akm.doctor.chat.bean.MessageType;


/**
 * 推送消息（消息中心）
 * Created by Administrator on 2016/2/25.
 */
@DatabaseTable(tableName = "User_PushMessages")
public class PushMessage {
    /**
     * senderId : system
     * type : activity
     * timestamp : 0
     * title : 资讯_test_title
     * activityId : be01361e-a881-4a01-9e79-5c3dcffd099d
     * httpUrl : upload/activity/user_activity_02.html
     */
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "senderId")
    private String senderId;
    @DatabaseField(columnName = "type")
    private MessageType type;
    @DatabaseField(columnName = "timestamp")
    private long timestamp;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "activityId")
    private String activityId;
    @DatabaseField(columnName = "httpUrl")
    private String httpUrl;
    @DatabaseField(columnName = "content")
    private String content;
    @DatabaseField(columnName = "isRead")
    private boolean isRead;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getSenderId() {
        return senderId;
    }

    public MessageType getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getActivityId() {
        return activityId;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "PushMessage{" +
                "activityId='" + activityId + '\'' +
                ", id=" + id +
                ", senderId='" + senderId + '\'' +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", title='" + title + '\'' +
                ", httpUrl='" + httpUrl + '\'' +
                ", content='" + content + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
