package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 问诊人当前病历(GET):参数需要问疹人ID:id   病历状态：status
 * Created by wanjingyan
 * on 2015/12/18 16:41.
 */
public class CurrentInterrogation {

    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"id":"ebb03d7f-9e63-4220-98aa-60b5c9aa1031","patientId":"e148e157-5764-4f8f-aa41-5209241d1464","content":"要多喝热水","caseImg":null,"memo":null,"offSet":null,"creator":"18512155695","createTime":null,"modifier":null,"modifyTime":null,"status":"1","flag":null},{"id":"fb35c073-cc81-49b0-8bf9-96c58b97c1f9","patientId":"e148e157-5764-4f8f-aa41-5209241d1464","content":"要多喝热水","caseImg":null,"memo":null,"offSet":null,"creator":"18512155695","createTime":null,"modifier":null,"modifyTime":null,"status":"0","flag":null}]
     */

    private String code;
    private String message;
    /**
     * id : ebb03d7f-9e63-4220-98aa-60b5c9aa1031
     * patientId : e148e157-5764-4f8f-aa41-5209241d1464
     * content : 要多喝热水
     * caseImg : null
     * memo : null
     * offSet : null
     * creator : 18512155695
     * createTime : null
     * modifier : null
     * modifyTime : null
     * status : 1
     * flag : null
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
        private String patientId;
        private String content;
        private Object caseImg;
        private Object memo;
        private Object offSet;
        private String creator;
        private Object createTime;
        private Object modifier;
        private Object modifyTime;
        private String status;
        private Object flag;

        public void setId(String id) {
            this.id = id;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setCaseImg(Object caseImg) {
            this.caseImg = caseImg;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public void setOffSet(Object offSet) {
            this.offSet = offSet;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public void setModifier(Object modifier) {
            this.modifier = modifier;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setFlag(Object flag) {
            this.flag = flag;
        }

        public String getId() {
            return id;
        }

        public String getPatientId() {
            return patientId;
        }

        public String getContent() {
            return content;
        }

        public Object getCaseImg() {
            return caseImg;
        }

        public Object getMemo() {
            return memo;
        }

        public Object getOffSet() {
            return offSet;
        }

        public String getCreator() {
            return creator;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public Object getModifier() {
            return modifier;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public String getStatus() {
            return status;
        }

        public Object getFlag() {
            return flag;
        }
    }
}
