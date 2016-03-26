package cn.zsmy.akm.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/12/25.
 */
public class SystemNotice {

    /**
     * code : SUCC
     * message : null
     * data : [{"id":"0c20ba77-7c84-49f0-b2bf-ef5fe7abf250","create":null,"userId":null,"title":"您的积分增加","content":"恭喜，您的的积分增加了10分","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":null,"createTime":null,"modifer":null,"modifyTime":null,"status":null,"avatar":null},{"id":"2132c10f-0a05-4cf5-a888-252160798ab2","create":null,"userId":null,"title":"历史病历2","content":"拉肚子","noticeType":"2","noticeNum":null,"caseId":null,"annexCode":"6f65abc3-0d3d-477c-9b75-9eecd10469e8","createTime":1451020774000,"modifer":null,"modifyTime":"2015-12-25","status":null,"avatar":null},{"id":"58d71937-451e-447f-9832-87be8dc44121","create":null,"userId":null,"title":"万经言主任医生","content":"感冒发烧，不舒服","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":"766b8faf-b8f8-4162-85b6-10dbfd7f9e9f","createTime":1451009028000,"modifer":null,"modifyTime":"2015-12-25","status":null,"avatar":null},{"id":"86825aff-c792-446c-96d9-2cf868b53324","create":null,"userId":null,"title":"历史病历4","content":"上火","noticeType":"2","noticeNum":null,"caseId":null,"annexCode":"f0d035d6-0bdc-4ea1-84d9-021f92ccfd0d","createTime":1451020774000,"modifer":null,"modifyTime":"2015-12-25","status":null,"avatar":null},{"id":"8cae0e03-28e9-44ac-a78b-0f35f1ddd9d4","create":null,"userId":null,"title":"我的消息","content":"请你及时交费，否则账号取消","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":null,"createTime":null,"modifer":null,"modifyTime":null,"status":null,"avatar":null},{"id":"8ecf0819-42ce-4098-a977-2cb88b8914f3","create":null,"userId":null,"title":"历史病历3","content":"上火","noticeType":"2","noticeNum":null,"caseId":null,"annexCode":"c45df375-3fab-44a8-9a46-fa0c6f956f74","createTime":1451020774000,"modifer":null,"modifyTime":"2015-12-25","status":null,"avatar":null},{"id":"a85c3477-6283-4ee0-ad68-16672634e036","create":null,"userId":null,"title":null,"content":null,"noticeType":null,"noticeNum":null,"caseId":null,"annexCode":null,"createTime":1451546911000,"modifer":null,"modifyTime":"2015-12-31","status":null,"avatar":null},{"id":"bae8edeb-d7f4-4c1d-883c-9ed3cbd6647c","create":null,"userId":null,"title":"义诊","content":"亲，不要忘记参加义廨","noticeType":null,"noticeNum":null,"caseId":null,"annexCode":null,"createTime":null,"modifer":null,"modifyTime":null,"status":null,"avatar":null},{"id":"bae8edeb-d7f4-4c1d-883c-9ed3cbd6647e","create":null,"userId":null,"title":"系统升级","content":"系统升级，请预先备份数据","noticeType":null,"noticeNum":null,"caseId":null,"annexCode":null,"createTime":null,"modifer":null,"modifyTime":null,"status":null,"avatar":null},{"id":"be704e78-75c8-4f05-907d-b888a9c1c642","create":null,"userId":null,"title":"李云锋主治医师","content":"感冒发烧，不舒服","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":"d43881a0-feec-47cd-a053-c9354b76b987","createTime":1450855879000,"modifer":null,"modifyTime":"2015-12-23","status":null,"avatar":null},{"id":"c8987f75-3dda-4a8b-aafc-4740c38051c9","create":null,"userId":null,"title":"张钻铮主任医生","content":"感冒发烧，不舒服","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":"cdc467cb-0ef8-4269-9a60-3a749416fe66","createTime":1451009028000,"modifer":null,"modifyTime":"2015-12-25","status":null,"avatar":null},{"id":"c8b24198-89aa-45d6-bc93-2e75f80310e5","create":null,"userId":null,"title":null,"content":null,"noticeType":null,"noticeNum":null,"caseId":null,"annexCode":null,"createTime":1451546027000,"modifer":null,"modifyTime":"2015-12-31","status":null,"avatar":null},{"id":"ce318fe0-fbb3-4a14-9c23-2d3e74531bb8","create":null,"userId":null,"title":"有人向你问诊","content":"有患者在一个小时之前向您问诊，请接诊","noticeType":"0","noticeNum":null,"caseId":null,"annexCode":null,"createTime":null,"modifer":null,"modifyTime":null,"status":null,"avatar":null}]
     */

    private String code;
    private Object message;
    /**
     * id : 0c20ba77-7c84-49f0-b2bf-ef5fe7abf250
     * create : null
     * userId : null
     * title : 您的积分增加
     * content : 恭喜，您的的积分增加了10分
     * noticeType : 1
     * noticeNum : null
     * caseId : null
     * annexCode : null
     * createTime : null
     * modifer : null
     * modifyTime : null
     * status : null
     * avatar : null
     */

    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public Object getMessage() {
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
        private Object annexCode;
        private Object createTime;
        private Object modifer;
        private Object modifyTime;
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

        public void setCaseId(Object caseId) {
            this.caseId = caseId;
        }

        public void setAnnexCode(Object annexCode) {
            this.annexCode = annexCode;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public void setModifer(Object modifer) {
            this.modifer = modifer;
        }

        public void setModifyTime(Object modifyTime) {
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

        public Object getCaseId() {
            return caseId;
        }

        public Object getAnnexCode() {
            return annexCode;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public Object getModifer() {
            return modifer;
        }

        public Object getModifyTime() {
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
