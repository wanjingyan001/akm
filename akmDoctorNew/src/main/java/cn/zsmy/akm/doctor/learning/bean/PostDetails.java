package cn.zsmy.akm.doctor.learning.bean;

import java.util.List;

/**
 * Created by sanz on 2015/12/10 9:29
 * 帖子详情
 */
public class PostDetails {


    /**
     * code : SUCC
     * message : 获得帖子详情成功
     * data : {"id":"2795cded-7fcb-4703-9d38-a4dcea2e986c","masterId":null,"forumPlateId":null,"title":"切切切","content":"成就感8好看V8好结果8","nickname":"sanz","supportNum":3,"visitNum":19,"pics":null,"creator":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","createTime":1453362302000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1453893822000,"status":null,"flag":"1","delFlag":"0","replyList":[{"id":"0533bd31-fa4b-4fb8-be2a-6a26f8f3491a","topicId":"2795cded-7fcb-4703-9d38-a4dcea2e986c","title":"切切切","nickname":"1234","avatar":"upload/casePic/2016/1/14/14527739227084854","content":"的价格发广告","pics":null,"creator":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","createTime":1453893653000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1453893653000,"status":null,"flag":null,"delFlag":"0"}],"topicSupport":[{"id":"76c6c2a5-6d50-48bf-b69d-ca0cc47331ed","title":null,"content":null,"nickName":null,"phone":null,"email":null,"qq":null,"creator":"ed2df7e2-ad4f-4895-945a-e16a64624bc9","createTime":1453877968000,"modifier":"ed2df7e2-ad4f-4895-945a-e16a64624bc9","modifyTime":1453877968000,"delFlag":"0","flag":null}]}
     */

    private String code;
    private String message;
    /**
     * id : 2795cded-7fcb-4703-9d38-a4dcea2e986c
     * masterId : null
     * forumPlateId : null
     * title : 切切切
     * content : 成就感8好看V8好结果8
     * nickname : sanz
     * supportNum : 3
     * visitNum : 19
     * pics : null
     * creator : bcf0735f-c335-4897-8ea3-ed14b25f23b0
     * createTime : 1453362302000
     * modifier : bcf0735f-c335-4897-8ea3-ed14b25f23b0
     * modifyTime : 1453893822000
     * status : null
     * flag : 1
     * delFlag : 0
     * replyList : [{"id":"0533bd31-fa4b-4fb8-be2a-6a26f8f3491a","topicId":"2795cded-7fcb-4703-9d38-a4dcea2e986c","title":"切切切","nickname":"1234","avatar":"upload/casePic/2016/1/14/14527739227084854","content":"的价格发广告","pics":null,"creator":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","createTime":1453893653000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1453893653000,"status":null,"flag":null,"delFlag":"0"}]
     * topicSupport : [{"id":"76c6c2a5-6d50-48bf-b69d-ca0cc47331ed","title":null,"content":null,"nickName":null,"phone":null,"email":null,"qq":null,"creator":"ed2df7e2-ad4f-4895-945a-e16a64624bc9","createTime":1453877968000,"modifier":"ed2df7e2-ad4f-4895-945a-e16a64624bc9","modifyTime":1453877968000,"delFlag":"0","flag":null}]
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
        private Object forumPlateId;
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
         * id : 0533bd31-fa4b-4fb8-be2a-6a26f8f3491a
         * topicId : 2795cded-7fcb-4703-9d38-a4dcea2e986c
         * title : 切切切
         * nickname : 1234
         * avatar : upload/casePic/2016/1/14/14527739227084854
         * content : 的价格发广告
         * pics : null
         * creator : bcf0735f-c335-4897-8ea3-ed14b25f23b0
         * createTime : 1453893653000
         * modifier : bcf0735f-c335-4897-8ea3-ed14b25f23b0
         * modifyTime : 1453893653000
         * status : null
         * flag : null
         * delFlag : 0
         */

        private List<ReplyListEntity> replyList;
        /**
         * id : 76c6c2a5-6d50-48bf-b69d-ca0cc47331ed
         * title : null
         * content : null
         * nickName : null
         * phone : null
         * email : null
         * qq : null
         * creator : ed2df7e2-ad4f-4895-945a-e16a64624bc9
         * createTime : 1453877968000
         * modifier : ed2df7e2-ad4f-4895-945a-e16a64624bc9
         * modifyTime : 1453877968000
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

        public void setForumPlateId(Object forumPlateId) {
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

        public Object getForumPlateId() {
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
            private String pics;
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
            private Object nickName;
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

            public void setNickName(Object nickName) {
                this.nickName = nickName;
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

            public Object getNickName() {
                return nickName;
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
