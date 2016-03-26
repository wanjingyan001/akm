package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 健康档案
 * Created by wanjingyan
 * on 2015/12/14 14:12.
 */
public class Interrogation {
    /**
     * code : SUCC
     * message : 数据返回成功
     * data : [{"id":"bf3d920b-08b1-462e-8cd6-52cabf0f7351","inquirerID":"bf3d920b-08b1-462e-8cd6-52cabf0f7351","name":"小张","gender":"男","birthday":1354464000000,"caseNum":2,"creator":"张三","createTime":1449459799000,"modifier":"张三","modifyTime":1449459799000,"status":"0","delFlag":"0","flag":null}]
     */

    private String code;
    private String message;
    private List<InterrogationInfo> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<InterrogationInfo> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<InterrogationInfo> getData() {
        return data;
    }

    public class InterrogationInfo {
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

        private String id;
        private String inquirerId;
        private String name;
        private String gender;
        private long birthday;
        private int caseNum;
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

        public String getInquirerId() {
            return inquirerId;
        }

        public void setInquirerId(String inquirerId) {
            this.inquirerId = inquirerId;
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

        public void setCaseNum(int caseNum) {
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


        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public long getBirthday() {
            return birthday;
        }

        public int getCaseNum() {
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
