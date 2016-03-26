package cn.zsmy.akm.salon.bean;

/**
 * 帖子
 * Created by Administrator on 2016/1/17.
 */
public class POST {

    /**
     * code : SUCC
     * message : 提交帖子成功
     * data : {"id":"e312619c-3155-4dad-99a3-0fd6dba526b3","creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1453017627877,"modifier":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","modifyTime":1453017627877,"flag":null,"status":null,"createrUser":null,"modifierUser":null,"delFlag":"0","parentId":null,"parent":null,"subs":null,"replyList":null,"forumPlateId":"433e5cd4-2ca8-4471-adeb-782146fdf923","title":"2016-1-17  1559","content":"发帖测试","nickname":null,"supportNum":null,"visitNum":null,"pics":null,"poster":null}
     */

    private String code;
    private String message;
    /**
     * id : e312619c-3155-4dad-99a3-0fd6dba526b3
     * creator : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * createTime : 1453017627877
     * modifier : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * modifyTime : 1453017627877
     * flag : null
     * status : null
     * createrUser : null
     * modifierUser : null
     * delFlag : 0
     * parentId : null
     * parent : null
     * subs : null
     * replyList : null
     * forumPlateId : 433e5cd4-2ca8-4471-adeb-782146fdf923
     * title : 2016-1-17  1559
     * content : 发帖测试
     * nickname : null
     * supportNum : null
     * visitNum : null
     * pics : null
     * poster : null
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
        private String creator;
        private long createTime;
        private String modifier;
        private long modifyTime;
        private Object flag;
        private Object status;
        private Object createrUser;
        private Object modifierUser;
        private String delFlag;
        private Object parentId;
        private Object parent;
        private Object subs;
        private Object replyList;
        private String forumPlateId;
        private String title;
        private String content;
        private Object nickname;
        private Object supportNum;
        private Object visitNum;
        private Object pics;
        private Object poster;

        public void setId(String id) {
            this.id = id;
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

        public void setFlag(Object flag) {
            this.flag = flag;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setCreaterUser(Object createrUser) {
            this.createrUser = createrUser;
        }

        public void setModifierUser(Object modifierUser) {
            this.modifierUser = modifierUser;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public void setParent(Object parent) {
            this.parent = parent;
        }

        public void setSubs(Object subs) {
            this.subs = subs;
        }

        public void setReplyList(Object replyList) {
            this.replyList = replyList;
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

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public void setSupportNum(Object supportNum) {
            this.supportNum = supportNum;
        }

        public void setVisitNum(Object visitNum) {
            this.visitNum = visitNum;
        }

        public void setPics(Object pics) {
            this.pics = pics;
        }

        public void setPoster(Object poster) {
            this.poster = poster;
        }

        public String getId() {
            return id;
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

        public Object getFlag() {
            return flag;
        }

        public Object getStatus() {
            return status;
        }

        public Object getCreaterUser() {
            return createrUser;
        }

        public Object getModifierUser() {
            return modifierUser;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public Object getParentId() {
            return parentId;
        }

        public Object getParent() {
            return parent;
        }

        public Object getSubs() {
            return subs;
        }

        public Object getReplyList() {
            return replyList;
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

        public Object getNickname() {
            return nickname;
        }

        public Object getSupportNum() {
            return supportNum;
        }

        public Object getVisitNum() {
            return visitNum;
        }

        public Object getPics() {
            return pics;
        }

        public Object getPoster() {
            return poster;
        }
    }
}
