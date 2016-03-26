package cn.zsmy.akm.doctor.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/1/8.
 */
public class PatientsNotice {


    /**
     * code : SUCC
     * message : 数据返回成功
     * data : [{"id":"dc966c61-6331-4c2d-94c8-540e2d87071c","create":null,"userId":null,"title":"华佗医生住院医师","content":"上吐下泻,不舒服","noticeType":"1","noticeNum":null,"caseId":"48ce22ca-9ecb-4dbc-bbf4-1c98c240ff0f","createTime":1456391731000,"modifer":null,"modifyTime":1456391731000,"status":null,"avatar":null}]
     */

    private String code;
    private String message;
    /**
     * id : dc966c61-6331-4c2d-94c8-540e2d87071c
     * create : null
     * userId : null
     * title : 华佗医生住院医师
     * content : 上吐下泻,不舒服
     * noticeType : 1
     * noticeNum : null
     * caseId : 48ce22ca-9ecb-4dbc-bbf4-1c98c240ff0f
     * createTime : 1456391731000
     * modifer : null
     * modifyTime : 1456391731000
     * status : null
     * avatar : null
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
        private String id;
        private Object create;
        private Object userId;
        private String title;
        private String content;
        private String noticeType;
        private Object noticeNum;
        private String caseId;
        private long createTime;
        private Object modifer;
        private long modifyTime;
        private Object status;
        private Object avatar;

        public void setId(String id) {
            this.id = id;
        }

        public void setCreate(Object create) {
            this.create = create;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setNoticeType(String noticeType) {
            this.noticeType = noticeType;
        }

        public void setNoticeNum(Object noticeNum) {
            this.noticeNum = noticeNum;
        }

        public void setCaseId(String caseId) {
            this.caseId = caseId;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setModifer(Object modifer) {
            this.modifer = modifer;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public String getId() {
            return id;
        }

        public Object getCreate() {
            return create;
        }

        public Object getUserId() {
            return userId;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getNoticeType() {
            return noticeType;
        }

        public Object getNoticeNum() {
            return noticeNum;
        }

        public String getCaseId() {
            return caseId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public Object getModifer() {
            return modifer;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public Object getStatus() {
            return status;
        }

        public Object getAvatar() {
            return avatar;
        }
    }
}
