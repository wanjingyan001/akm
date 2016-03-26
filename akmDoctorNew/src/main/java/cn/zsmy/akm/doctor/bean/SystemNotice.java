package cn.zsmy.akm.doctor.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/12/25.
 */
public class SystemNotice {

    /**
     * code : SUCC
     * message : null
     * data : [{"id":"f9dac499-8ade-40e5-8950-5b190c635a99","creator":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","userId":null,"title":"系统升级","content":"本系统将生级，到时候会暂停服务，请注意","noticeType":"0","noticeNum":null,"caseId":null,"annexCode":"18b0d526-638b-4eba-820e-899845fa00e8","createTime":1452687358000,"modifer":null,"modifyTime":"2016-01-13","status":null,"avatar":null,"flag":null},{"id":"31ec4ced-7907-49b8-88b1-e9d186fd6745","creator":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","userId":null,"title":null,"content":null,"noticeType":"1","noticeNum":null,"caseId":null,"annexCode":"52054d85-d755-4745-b7b9-5f1b572702ee","createTime":1452676670000,"modifer":null,"modifyTime":"2016-01-13","status":null,"avatar":null,"flag":null},{"id":"1eff36a7-8021-4d4b-9e48-3acc4fa44840","creator":"9415d58d-7857-40e3-805d-43f8545543af","userId":null,"title":"sanz住院医师","content":"他默默粉末来摸摸咯呢","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":"4b209583-82a1-4c13-b0a7-dd3cc8dad484","createTime":1452676187000,"modifer":null,"modifyTime":"2016-01-13","status":null,"avatar":null,"flag":null},{"id":"2ad58ade-cdc3-4d9b-901c-9d0204c638c1","creator":"9415d58d-7857-40e3-805d-43f8545543af","userId":null,"title":"sanz住院医师","content":"他默默粉末来摸摸咯呢","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":"4b209583-82a1-4c13-b0a7-dd3cc8dad484","createTime":1452676187000,"modifer":null,"modifyTime":"2016-01-14","status":null,"avatar":null,"flag":null},{"id":"806fc0ca-ed7a-47c5-9e7f-cf46f4c700b4","creator":"9415d58d-7857-40e3-805d-43f8545543af","userId":null,"title":"快速问诊","content":"测得得的得的得的的1122","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":"72713c87-4a8a-4df9-950b-1922faa7bdb3","createTime":1452676127000,"modifer":null,"modifyTime":"2016-01-13","status":null,"avatar":null,"flag":null},{"id":"2861e1f3-67ee-4bcd-8229-f91147b65e3f","creator":"9415d58d-7857-40e3-805d-43f8545543af","userId":null,"title":"快速问诊","content":"测得得的得的得的的1122","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":"72713c87-4a8a-4df9-950b-1922faa7bdb3","createTime":1452676099000,"modifer":null,"modifyTime":"2016-01-13","status":null,"avatar":null,"flag":null},{"id":"d7ae2b42-933b-43d4-8bc9-055e92b9ac76","creator":"9415d58d-7857-40e3-805d-43f8545543af","userId":null,"title":"快速问诊","content":"测得得的得的得的的1122","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":"72713c87-4a8a-4df9-950b-1922faa7bdb3","createTime":1452676099000,"modifer":null,"modifyTime":"2016-01-13","status":null,"avatar":null,"flag":null},{"id":"42bb5d99-995d-4d03-af60-63dac72da5c2","creator":"9415d58d-7857-40e3-805d-43f8545543af","userId":null,"title":"快速问诊","content":"测得得的得的得的的1122","noticeType":"1","noticeNum":null,"caseId":null,"annexCode":"72713c87-4a8a-4df9-950b-1922faa7bdb3","createTime":1452676098000,"modifer":null,"modifyTime":"2016-01-13","status":null,"avatar":null,"flag":null}]
     */

    private String code;
    private Object message;
    /**
     * id : f9dac499-8ade-40e5-8950-5b190c635a99
     * creator : 36b381c3-0de9-46f1-b1dd-b1c7ae8e3978
     * userId : null
     * title : 系统升级
     * content : 本系统将生级，到时候会暂停服务，请注意
     * noticeType : 0
     * noticeNum : null
     * caseId : null
     * annexCode : 18b0d526-638b-4eba-820e-899845fa00e8
     * createTime : 1452687358000
     * modifer : null
     * modifyTime : 2016-01-13
     * status : null
     * avatar : null
     * flag : null
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
        private String creator;
        private Object userId;
        private String title;
        private String content;
        private String noticeType;
        private Object noticeNum;
        private Object caseId;
        private String annexCode;
        private long createTime;
        private Object modifer;
        private String modifyTime;
        private Object status;
        private Object avatar;
        private Object flag;

        public void setId(String id) {
            this.id = id;
        }

        public void setCreator(String creator) {
            this.creator = creator;
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

        public void setAnnexCode(String annexCode) {
            this.annexCode = annexCode;
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

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public void setFlag(Object flag) {
            this.flag = flag;
        }

        public String getId() {
            return id;
        }

        public String getCreator() {
            return creator;
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

        public String getAnnexCode() {
            return annexCode;
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

        public Object getAvatar() {
            return avatar;
        }

        public Object getFlag() {
            return flag;
        }
    }
}
