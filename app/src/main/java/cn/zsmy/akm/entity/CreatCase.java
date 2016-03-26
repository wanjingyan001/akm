package cn.zsmy.akm.entity;

import java.io.Serializable;

/**
 * Created by qinwei on 2015/12/30 19:26
 */
public class CreatCase implements Serializable {

    /**
     * code : SUCC
     * message : 普通病例建成功
     * data : {"id":"373ccd1b-2188-4dc3-a094-f79868febe70","patientId":null,"content":"dhjdjdjdkfkfkf","caseImg":null,"memo":"null","offSet":null,"creator":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","createTime":null,"modifier":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","modifyTime":1451474621585,"status":null,"flag":null,"inquirer":null,"doctor":null,"patient":null}
     */

    private String code;
    private String message;
    /**
     * id : 373ccd1b-2188-4dc3-a094-f79868febe70
     * patientId : null
     * content : dhjdjdjdkfkfkf
     * caseImg : null
     * memo : null
     * offSet : null
     * creator : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * createTime : null
     * modifier : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * modifyTime : 1451474621585
     * status : null
     * flag : null
     * inquirer : null
     * doctor : null
     * patient : null
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
        private Object patientId;
        private String content;
        private Object caseImg;
        private String memo;
        private Object offSet;
        private String creator;
        private Object createTime;
        private String modifier;
        private long modifyTime;
        private Object status;
        private Object flag;
        private Object inquirer;
        private Object doctor;
        private Object patient;

        public void setId(String id) {
            this.id = id;
        }

        public void setPatientId(Object patientId) {
            this.patientId = patientId;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setCaseImg(Object caseImg) {
            this.caseImg = caseImg;
        }

        public void setMemo(String memo) {
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

        public void setInquirer(Object inquirer) {
            this.inquirer = inquirer;
        }

        public void setDoctor(Object doctor) {
            this.doctor = doctor;
        }

        public void setPatient(Object patient) {
            this.patient = patient;
        }

        public String getId() {
            return id;
        }

        public Object getPatientId() {
            return patientId;
        }

        public String getContent() {
            return content;
        }

        public Object getCaseImg() {
            return caseImg;
        }

        public String getMemo() {
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

        public Object getInquirer() {
            return inquirer;
        }

        public Object getDoctor() {
            return doctor;
        }

        public Object getPatient() {
            return patient;
        }
    }
}
