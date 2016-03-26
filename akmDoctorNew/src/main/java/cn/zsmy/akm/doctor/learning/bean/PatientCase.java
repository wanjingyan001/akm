package cn.zsmy.akm.doctor.learning.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 病例
 * Created by Administrator on 2015/12/30.
 */
public class PatientCase implements Serializable{

    /**
     * code : SUCC
     * message : 返回帖子列表成功
     * data : [{"id":"c5b40260-13c5-4334-9f92-803deee14ddd","forumPlateId":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"你好","content":"你好妈妈。","nickname":"1234","supportNum":"1","visitNum":"42","pics":null,"createTime":1454034260000,"status":null,"flag":"1","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"bfbf2459-6874-4c2c-8a2d-c023d414539a","forumPlateId":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"感觉急急急","content":"急急急急急急","nickname":"1500***0","supportNum":null,"visitNum":"17","pics":"upload/topicPic/2016/2/29/14568023830045920","createTime":1456802383000,"status":null,"flag":"1","avatar":"upload/authPic/2016/1/28/14539697918886842"},{"id":"9f3e994e-253a-416b-9ed0-0533b734be11","forumPlateId":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"2016-0202-----","content":"患者问诊内容：2016-0201-5","nickname":"华佗医生","supportNum":"1","visitNum":"13","pics":null,"createTime":1454396242000,"status":null,"flag":"1","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"2b2ec546-599c-4d1f-a1a9-1850bc17abd0","forumPlateId":"f9b161f1-9697-431c-911f-06f295c3d6ds","title":"病历发帖测试","content":"发帖测试111啊啊鹅鹅鹅","nickname":"1234","supportNum":"1","visitNum":"10","pics":null,"createTime":1454045435000,"status":null,"flag":"1","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"bfca45fe-4d64-46f8-a708-967312fc3ec6","forumPlateId":"f9b161f1-9697-431c-911f-06f295c3d6af","title":"病例发帖","content":"患者问诊内容：2016-0201-测试2n诊断结果：测试内容没有","nickname":"华佗医生","supportNum":null,"visitNum":"10","pics":"upload/topicPic/2016/2/2/14543847102178308","createTime":1454384708000,"status":null,"flag":"1","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"16fcadd5-4274-4c9a-b116-9355785f260c","forumPlateId":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"2016-0202-1","content":"患者问诊内容：没有问题，请你琢磨一下","nickname":"华佗医生","supportNum":"1","visitNum":"3","pics":null,"createTime":1454383933000,"status":null,"flag":"1","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"7005e31c-f98f-4f06-b2d7-0a33cc516b56","forumPlateId":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"腿","content":"患者问诊内容：没有问题，请你琢磨一下n诊断结果：退役","nickname":"华佗医生","supportNum":null,"visitNum":"3","pics":null,"createTime":1454466903000,"status":null,"flag":"1","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"3bb6c4da-9898-448b-b766-5b2d2b38d07c","forumPlateId":"f9b161f1-9697-431c-911f-06f295c3d6af","title":"没看见","content":"他哦里咯了","nickname":"华佗医生","supportNum":null,"visitNum":"1","pics":"upload/topicPic/2016/2/14/14554293688226546,upload/topicPic/2016/2/14/14554293691207734","createTime":1455429368000,"status":null,"flag":"1","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"4fc1336a-f603-46a1-b103-20bf4d21f24a","forumPlateId":"f9b161f1-9697-431c-911f-06f295c3d6ds","title":"发表测试","content":"患者问诊内容：2016-0128-晚1","nickname":"华佗医生","supportNum":null,"visitNum":"1","pics":"upload/topicPic/2016/2/2/14543849597826635","createTime":1454384958000,"status":null,"flag":"1","avatar":"upload/casePic/2016/1/14/14527739227084854"},{"id":"723743f9-4afc-4fd7-98d7-e2dd7844ce9d","forumPlateId":"f9b161f1-9697-431c-911f-06f295c3d6ds","title":"发帖3.8","content":"3.8发帖测试","nickname":"华佗医生","supportNum":null,"visitNum":null,"pics":null,"createTime":1457424857000,"status":null,"flag":"1","avatar":"upload/authPic/2016/1/29/14540365834097536"}]
     */
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    /**
     * id : c5b40260-13c5-4334-9f92-803deee14ddd
     * forumPlateId : 19184f10-b4ea-11e5-a0de-4439c4558c0b
     * title : 你好
     * content : 你好妈妈。
     * nickname : 1234
     * supportNum : 1
     * visitNum : 42
     * pics : null
     * createTime : 1454034260000
     * status : null
     * flag : 1
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

    public static class DataEntity implements Serializable{
        private static final long serialVersionUID = 1L;
        private String id;
        private String forumPlateId;
        private String title;
        private String content;
        private String nickname;
        private String supportNum;
        private String visitNum;
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

        public void setSupportNum(String supportNum) {
            this.supportNum = supportNum;
        }

        public void setVisitNum(String visitNum) {
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

        public String getSupportNum() {
            return supportNum;
        }

        public String getVisitNum() {
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
