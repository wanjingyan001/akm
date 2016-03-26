package cn.zsmy.akm.entity;

import java.util.List;

/**
 * Created by qinwei on 2015/12/9 10:45
 */
public class Case {


    /**
     * content : 2016-0128-未认证测试1
     * id : 64af5e5c-74e4-4007-8eb5-9e353c83edd8
     * modifer : null
     * createTime : 1453958532000
     * title : 1234住院医师
     * status : null
     * caseId : 303a1352-50f6-41ec-9e64-8f0fb8473ada
     * userId : null
     * noticeNum : null
     * avatar : null
     * modifyTime : 1453958980000
     * create : null
     * noticeType : 1
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String content;
        private String id;
        private Object modifer;
        private long createTime;
        private String title;
        private Object status;
        private String caseId;
        private Object userId;
        private Object noticeNum;
        private String avatar;
        private long modifyTime;
        private Object create;
        private String noticeType;

        public void setContent(String content) {
            this.content = content;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setModifer(Object modifer) {
            this.modifer = modifer;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setCaseId(String caseId) {
            this.caseId = caseId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public void setNoticeNum(Object noticeNum) {
            this.noticeNum = noticeNum;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setCreate(Object create) {
            this.create = create;
        }

        public void setNoticeType(String noticeType) {
            this.noticeType = noticeType;
        }

        public String getContent() {
            return content;
        }

        public String getId() {
            return id;
        }

        public Object getModifer() {
            return modifer;
        }

        public long getCreateTime() {
            return createTime;
        }

        public String getTitle() {
            return title;
        }

        public Object getStatus() {
            return status;
        }

        public String getCaseId() {
            return caseId;
        }

        public Object getUserId() {
            return userId;
        }

        public Object getNoticeNum() {
            return noticeNum;
        }

        public String getAvatar() {
            return avatar;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public Object getCreate() {
            return create;
        }

        public String getNoticeType() {
            return noticeType;
        }
    }
}
