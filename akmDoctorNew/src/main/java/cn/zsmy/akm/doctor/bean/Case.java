package cn.zsmy.akm.doctor.bean;

import java.util.List;

/**
 * Created by qinwei on 2015/12/9 10:45
 */
public class Case {

    /**
     * code : SUCC
     * message : 数据返回成功
     * data : [{"id":"be704e78-75c8-4f05-907d-b888a9c1c642","create":null,"userId":null,"title":"普通病例","content":"感冒发烧，不舒服","noticeType":"2","noticeNum":null,"caseId":null,"createTime":1450855879000,"modifer":null,"modifyTime":"Wed Dec 23 15:31:19 CST 2015","status":null}]
     */

    private String code;
    private String message;
    /**
     * id : be704e78-75c8-4f05-907d-b888a9c1c642
     * create : null
     * userId : null
     * title : 普通病例
     * content : 感冒发烧，不舒服
     * noticeType : 2
     * noticeNum : null
     * caseId : null
     * createTime : 1450855879000
     * modifer : null
     * modifyTime : Wed Dec 23 15:31:19 CST 2015
     * status : null
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
        private Object caseId;
        private long createTime;
        private Object modifer;
        private String modifyTime;
        private Object status;

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

        public void setCaseId(Object caseId) {
            this.caseId = caseId;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setModifer(Object modifer) {
            this.modifer = modifer;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setStatus(Object status) {
            this.status = status;
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

        public Object getCaseId() {
            return caseId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public Object getModifer() {
            return modifer;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public Object getStatus() {
            return status;
        }
    }
}
