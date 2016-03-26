package cn.zsmy.akm.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 帖子列表实体类
 * Created by wanjingyan
 * on 2015/12/10 10:35.
 */
public class Posts implements Serializable {

    /**
     * content : 发图测试
     * id : ccdcb660-c528-49e9-829e-fd800fc4a69d
     * createTime : 1453960937000
     * pics : null
     * title : 发图测试
     * flag : 0
     * status : null
     * nickname : 甲乙丙
     * visitNum : 5
     * supportNum : 1
     * avatar : upload/avatar/2016/1/27/14538767943488120
     * forumPlateId : 5b01c211-0624-44d9-8904-7b21b1144db1
     */
    private static final long serialVersionUID = 1L;
    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Serializable {
        private static final long serialVersionUID = 1L;
        private String content;
        private String id;
        private long createTime;
        private String pics;
        private String title;
        private String flag;
        private Object status;
        private String nickname;
        private String visitNum;
        private String supportNum;
        private String avatar;
        private String forumPlateId;

        public void setContent(String content) {
            this.content = content;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setVisitNum(String visitNum) {
            this.visitNum = visitNum;
        }

        public void setSupportNum(String supportNum) {
            this.supportNum = supportNum;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setForumPlateId(String forumPlateId) {
            this.forumPlateId = forumPlateId;
        }

        public String getContent() {
            return content;
        }

        public String getId() {
            return id;
        }

        public long getCreateTime() {
            return createTime;
        }

        public String getPics() {
            return pics;
        }

        public String getTitle() {
            return title;
        }

        public String getFlag() {
            return flag;
        }

        public Object getStatus() {
            return status;
        }

        public String getNickname() {
            return nickname;
        }

        public String getVisitNum() {
            return visitNum;
        }

        public String getSupportNum() {
            return supportNum;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getForumPlateId() {
            return forumPlateId;
        }
    }
}
