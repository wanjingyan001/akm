package cn.zsmy.akm.doctor.learning.bean;

import java.util.List;

/**
 * Created by sanz on 2016/1/16 13:55
 */
public class TopicsList {

    /**
     * code : SUCC
     * message : 返回帖子列表成功
     * data : [{"id":"d49c5245-9913-481e-9767-ea0f5f70598e","forumPlateId":"f9b161f1-9697-431c-911f-06f295c3d6ab","title":"123","content":"觉得结婚的接电话","nickname":"华佗医生","supportNum":null,"visitNum":null,"pics":"upload/topicPic/2016/1/29/14540587553366629","createTime":1454058754000,"status":null,"flag":"0","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"f9979745-6f45-4a69-b8ad-f2351a237ae6","forumPlateId":"f9b161f1-9697-431c-911f-06f295c3d6ab","title":"if还","content":"哦哦付放假","nickname":"华佗医生","supportNum":null,"visitNum":null,"pics":"upload/topicPic/2016/1/29/14540582077261604","createTime":1454058208000,"status":null,"flag":"0","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"8fedf290-00a0-456b-92b9-478e9be85b1e","forumPlateId":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"123","content":"abc","nickname":"华佗医生","supportNum":null,"visitNum":null,"pics":null,"createTime":1454058145000,"status":null,"flag":"0","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"a9359414-3b19-4c1d-a020-fbe70757d91b","forumPlateId":"f9b161f1-9697-431c-911f-06f295c3d6aa","title":"要要要","content":"故意雨","nickname":"我","supportNum":"1","visitNum":"11","pics":null,"createTime":1454052467000,"status":null,"flag":"0","avatar":"upload/avatar/2016/1/28/14539822464817697"},{"id":"df05371e-79de-4204-a908-144935554a62","forumPlateId":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"华佗在世","content":"一回到家对你的大姐大几几打几打几打几打几","nickname":"华佗医生","supportNum":null,"visitNum":null,"pics":"upload/topicPic/2016/1/29/14540511118423947","createTime":1454051110000,"status":null,"flag":"0","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"23ba6eff-1896-433e-a981-f6c18b78a121","forumPlateId":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"很低调回电话","content":"哦地刚到家打工的","nickname":"医生姓名","supportNum":null,"visitNum":"3","pics":"upload/topicPic/2016/1/29/14540503275283345","createTime":1454050326000,"status":null,"flag":"0","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"78b3ff59-2406-46b3-8070-2b82aafb075b","forumPlateId":"f9b161f1-9697-431c-911f-06f295c3d6af","title":"擦擦擦","content":"哈吧舅舅家","nickname":"医生姓名","supportNum":null,"visitNum":"2","pics":null,"createTime":1454049754000,"status":null,"flag":"0","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"02f54950-94fb-486e-933b-e881ba7a28cc","forumPlateId":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"2016-0129-1","content":"就到家好滴多喝点回答红我我哈打","nickname":"1234","supportNum":null,"visitNum":"1","pics":null,"createTime":1454036775000,"status":null,"flag":"0","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"316c0e63-3256-443f-811a-460219fb06df","forumPlateId":"f9b161f1-9697-431c-911f-06f295c3d6aa","title":"2016-0129-","content":"一个男人裸体坐在石头上","nickname":"甲乙丙丁","supportNum":null,"visitNum":"5","pics":"upload/topicPic/2016/1/29/14540357567141679","createTime":1454035756000,"status":null,"flag":"0","avatar":"upload/avatar/2016/1/28/14539822464817697"},{"id":"575914d4-3625-45a2-baf7-c5c11f206632","forumPlateId":"5b01c211-0624-44d9-8904-7b21b1144db1","title":"每天都是","content":"路上老K了","nickname":"甲乙丙丁","supportNum":null,"visitNum":"1","pics":null,"createTime":1454034775000,"status":null,"flag":"0","avatar":"upload/avatar/2016/1/28/14539822464817697"},{"id":"3b84382e-451b-49bd-8227-3b96d305a092","forumPlateId":"5b01c211-0624-44d9-8904-7b21b1144db1","title":"测试测试就医建议","content":"地主之谊","nickname":"甲乙丙丁","supportNum":null,"visitNum":"1","pics":null,"createTime":1454034575000,"status":null,"flag":"0","avatar":"upload/avatar/2016/1/28/14539822464817697"}]
     */

    private String code;
    private String message;
    /**
     * id : d49c5245-9913-481e-9767-ea0f5f70598e
     * forumPlateId : f9b161f1-9697-431c-911f-06f295c3d6ab
     * title : 123
     * content : 觉得结婚的接电话
     * nickname : 华佗医生
     * supportNum : null
     * visitNum : null
     * pics : upload/topicPic/2016/1/29/14540587553366629
     * createTime : 1454058754000
     * status : null
     * flag : 0
     * avatar : upload/casePic/2016/1/14/14527739227084854
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
        private String forumPlateId;
        private String title;
        private String content;
        private String nickname;
        private int supportNum;
        private int visitNum;
        private String pics;
        private long createTime;
        private Object status;
        private String flag;
        private String avatar;

        public void setId(String id) {
            this.id = id;
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

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getId() {
            return id;
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

        public long getCreateTime() {
            return createTime;
        }

        public Object getStatus() {
            return status;
        }

        public String getFlag() {
            return flag;
        }

        public String getAvatar() {
            return avatar;
        }
    }
}
