package cn.zsmy.akm.entity;

import java.util.List;

/**
 * Created by sanz on 2015/12/10 9:29
 * 帖子详情
 */
public class PostDetails {


    /**
     * code : SUCC
     * message : 获得帖子详情成功
     * data : {"id":"73b319b0-72c9-4d23-8d7f-5a88b148d9ed","masterId":null,"forumPlateId":"433e5cd4-2ca8-4471-adeb-782146fdf923","title":"构造","content":"咯吐了","nickname":"隊長","supportNum":2,"visitNum":52,"pics":"upload/topicPic/2016/1/22/14534694130387585,upload/topicPic/2016/1/22/14534694150922210,upload/topicPic/2016/1/22/14534694161048546","creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1453469411000,"modifier":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","modifyTime":1453887922000,"status":null,"flag":"0","delFlag":"0","replyList":[{"id":"07f338bc-f49b-4eb6-80ad-81757b71d123","topicId":"73b319b0-72c9-4d23-8d7f-5a88b148d9ed","title":"构造","nickname":"隊長","avatar":"upload/avatar/2016/1/26/14537771714009569","content":"测试测试","pics":null,"creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1453808776000,"modifier":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","modifyTime":1453808776000,"status":null,"flag":null,"delFlag":"0"}],"topicSupport":[{"id":"8a8b1bdd-d4cd-4cf2-809e-54470e1e6a68","title":null,"content":null,"nickName":null,"phone":null,"email":null,"qq":null,"creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1453786881000,"modifier":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","modifyTime":1453786881000,"delFlag":"0","flag":null},{"id":"db4dc6a1-a537-46db-89ca-d7732d214e44","title":null,"content":null,"nickName":null,"phone":null,"email":null,"qq":null,"creator":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","createTime":1453707164000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1453707164000,"delFlag":"0","flag":null}]}
     */

    private String code;
    private String message;
    /**
     * id : 73b319b0-72c9-4d23-8d7f-5a88b148d9ed
     * masterId : null
     * forumPlateId : 433e5cd4-2ca8-4471-adeb-782146fdf923
     * title : 构造
     * content : 咯吐了
     * nickname : 隊長
     * supportNum : 2
     * visitNum : 52
     * pics : upload/topicPic/2016/1/22/14534694130387585,upload/topicPic/2016/1/22/14534694150922210,upload/topicPic/2016/1/22/14534694161048546
     * creator : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * createTime : 1453469411000
     * modifier : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * modifyTime : 1453887922000
     * status : null
     * flag : 0
     * delFlag : 0
     * replyList : [{"id":"07f338bc-f49b-4eb6-80ad-81757b71d123","topicId":"73b319b0-72c9-4d23-8d7f-5a88b148d9ed","title":"构造","nickname":"隊長","avatar":"upload/avatar/2016/1/26/14537771714009569","content":"测试测试","pics":null,"creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1453808776000,"modifier":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","modifyTime":1453808776000,"status":null,"flag":null,"delFlag":"0"}]
     * topicSupport : [{"id":"8a8b1bdd-d4cd-4cf2-809e-54470e1e6a68","title":null,"content":null,"nickName":null,"phone":null,"email":null,"qq":null,"creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1453786881000,"modifier":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","modifyTime":1453786881000,"delFlag":"0","flag":null},{"id":"db4dc6a1-a537-46db-89ca-d7732d214e44","title":null,"content":null,"nickName":null,"phone":null,"email":null,"qq":null,"creator":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","createTime":1453707164000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1453707164000,"delFlag":"0","flag":null}]
     */

    private DataEntity data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String id;
        private Object masterId;
        private String forumPlateId;
        private String title;
        private String content;
        private String nickname;
        private int supportNum;
        private int visitNum;
        private String pics;
        private String creator;
        private long createTime;
        private String modifier;
        private long modifyTime;
        private Object status;
        private String flag;
        private String delFlag;
        /**
         * id : 07f338bc-f49b-4eb6-80ad-81757b71d123
         * topicId : 73b319b0-72c9-4d23-8d7f-5a88b148d9ed
         * title : 构造
         * nickname : 隊長
         * avatar : upload/avatar/2016/1/26/14537771714009569
         * content : 测试测试
         * pics : null
         * creator : d9489b4f-043b-4fbc-8ae2-e49b460e528c
         * createTime : 1453808776000
         * modifier : d9489b4f-043b-4fbc-8ae2-e49b460e528c
         * modifyTime : 1453808776000
         * status : null
         * flag : null
         * delFlag : 0
         */

        private List<ReplyListEntity> replyList;
        /**
         * id : 8a8b1bdd-d4cd-4cf2-809e-54470e1e6a68
         * title : null
         * content : null
         * nickName : null
         * phone : null
         * email : null
         * qq : null
         * creator : d9489b4f-043b-4fbc-8ae2-e49b460e528c
         * createTime : 1453786881000
         * modifier : d9489b4f-043b-4fbc-8ae2-e49b460e528c
         * modifyTime : 1453786881000
         * delFlag : 0
         * flag : null
         */

        private List<TopicSupportEntity> topicSupport;

        public void setId(String id) {
            this.id = id;
        }

        public void setMasterId(Object masterId) {
            this.masterId = masterId;
        }

        public void setForumPlateId(String forumPlateId) {
            this.forumPlateId = forumPlateId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setSupportNum(int supportNum) {
            this.supportNum = supportNum;
        }

        public void setVisitNum(int visitNum) {
            this.visitNum = visitNum;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public void setReplyList(List<ReplyListEntity> replyList) {
            this.replyList = replyList;
        }

        public void setTopicSupport(List<TopicSupportEntity> topicSupport) {
            this.topicSupport = topicSupport;
        }

        public String getId() {
            return id;
        }

        public Object getMasterId() {
            return masterId;
        }

        public String getForumPlateId() {
            return forumPlateId;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getNickname() {
            return nickname;
        }

        public int getSupportNum() {
            return supportNum;
        }

        public int getVisitNum() {
            return visitNum;
        }

        public String getPics() {
            return pics;
        }

        public String getCreator() {
            return creator;
        }

        public long getCreateTime() {
            return createTime;
        }

        public String getModifier() {
            return modifier;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public Object getStatus() {
            return status;
        }

        public String getFlag() {
            return flag;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public List<ReplyListEntity> getReplyList() {
            return replyList;
        }

        public List<TopicSupportEntity> getTopicSupport() {
            return topicSupport;
        }

        public static class ReplyListEntity {
            private String id;
            private String topicId;
            private String title;
            private String nickname;
            private String avatar;
            private String content;
            private Object pics;
            private String creator;
            private long createTime;
            private String modifier;
            private long modifyTime;
            private Object status;
            private Object flag;
            private String delFlag;

            public void setId(String id) {
                this.id = id;
            }

            public void setTopicId(String topicId) {
                this.topicId = topicId;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setPics(Object pics) {
                this.pics = pics;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setModifier(String modifier) {
                this.modifier = modifier;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public void setFlag(Object flag) {
                this.flag = flag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getId() {
                return id;
            }

            public String getTopicId() {
                return topicId;
            }

            public String getTitle() {
                return title;
            }

            public String getNickname() {
                return nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public String getContent() {
                return content;
            }

            public Object getPics() {
                return pics;
            }

            public String getCreator() {
                return creator;
            }

            public long getCreateTime() {
                return createTime;
            }

            public String getModifier() {
                return modifier;
            }

            public long getModifyTime() {
                return modifyTime;
            }

            public Object getStatus() {
                return status;
            }

            public Object getFlag() {
                return flag;
            }

            public String getDelFlag() {
                return delFlag;
            }
        }

        public static class TopicSupportEntity {
            private String id;
            private Object title;
            private Object content;
            private String nickname;
            private Object phone;
            private Object email;
            private Object qq;
            private String creator;
            private long createTime;
            private String modifier;
            private long modifyTime;
            private String delFlag;
            private Object flag;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public void setQq(Object qq) {
                this.qq = qq;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setModifier(String modifier) {
                this.modifier = modifier;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public void setFlag(Object flag) {
                this.flag = flag;
            }

            public String getId() {
                return id;
            }

            public Object getTitle() {
                return title;
            }

            public Object getContent() {
                return content;
            }

            public String getNickname() {
                return nickname;
            }

            public Object getPhone() {
                return phone;
            }

            public Object getEmail() {
                return email;
            }

            public Object getQq() {
                return qq;
            }

            public String getCreator() {
                return creator;
            }

            public long getCreateTime() {
                return createTime;
            }

            public String getModifier() {
                return modifier;
            }

            public long getModifyTime() {
                return modifyTime;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public Object getFlag() {
                return flag;
            }
        }
    }
}
