package cn.zsmy.akm.doctor.conversation.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9 14:16.
 */
public class ReplyList {

    /**
     * code : SUCC
     * message : 获得帖子回复列表成功
     * data : [{"id":"49757daa-c3c9-4c7e-a8dc-ab82b773cb34","topicId":"58551aa8-57a0-4dde-b466-342ced015d84","title":"有问必答测试1","nickname":"咳咳咳","avatar":"upload/avatar/2016/1/29/14540567847822569","content":"葉倩文","pics":null,"creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1456967682000,"modifier":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","modifyTime":1456967682000,"status":null,"flag":null,"delFlag":"0"}]
     */

    private String code;
    private String message;
    /**
     * id : 49757daa-c3c9-4c7e-a8dc-ab82b773cb34
     * topicId : 58551aa8-57a0-4dde-b466-342ced015d84
     * title : 有问必答测试1
     * nickname : 咳咳咳
     * avatar : upload/avatar/2016/1/29/14540567847822569
     * content : 葉倩文
     * pics : null
     * creator : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * createTime : 1456967682000
     * modifier : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * modifyTime : 1456967682000
     * status : null
     * flag : null
     * delFlag : 0
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
}
