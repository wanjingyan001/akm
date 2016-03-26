package cn.zsmy.akm.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qinwei on 2015/12/14 13:45
 */
public class Patient {

    /**
     * code : SUCC
     * message : 数据返回成功
     * data : [{"id":"bf3d920b-08b1-462e-8cd6-52cabf0f7351","inquirerID":"bf3d920b-08b1-462e-8cd6-52cabf0f7351","name":"小张","gender":"男","birthday":1354464000000,"caseNum":2,"creator":"张三","createTime":1449459799000,"modifier":"张三","modifyTime":1449459799000,"status":"0","delFlag":"0","flag":null}]
     */

    private String code;
    private String message;
    /**
     * id : bf3d920b-08b1-462e-8cd6-52cabf0f7351
     * inquirerID : bf3d920b-08b1-462e-8cd6-52cabf0f7351
     * name : 小张
     * gender : 男
     * birthday : 1354464000000
     * caseNum : 2
     * creator : 张三
     * createTime : 1449459799000
     * modifier : 张三
     * modifyTime : 1449459799000
     * status : 0
     * delFlag : 0
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
    public  DataEntity getDataEntity(){
        DataEntity  dataEntity=new DataEntity();
           return dataEntity;
    }

    public  class DataEntity implements Serializable {
        private String id;
        private String inquirerID;
        private String name;
        private String gender;
        private long birthday;
        private String caseNum;
        private String creator;
        private long createTime;
        private String modifier;
        private long modifyTime;
        private String status;
        private String delFlag;
        private Object flag;

        public void setId(String id) {
            this.id = id;
        }

        public void setInquirerID(String inquirerID) {
            this.inquirerID = inquirerID;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public void setCaseNum(String caseNum) {
            this.caseNum = caseNum;
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

        public void setStatus(String status) {
            this.status = status;
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

        public String getInquirerID() {
            return inquirerID;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public long getBirthday() {
            return birthday;
        }

        public String getCaseNum() {
            return caseNum;
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

        public String getStatus() {
            return status;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public Object getFlag() {
            return flag;
        }
    }
}
