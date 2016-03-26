package cn.zsmy.akm.doctor.chat.bean;

import java.util.List;

/**
 * Created by qinwei on 2016/1/11 15:28
 */
public class CaseInfo {
    /**
     * code : SUCC
     * data : {"caseAdvices":[],"caseChecks":[],"caseLength":0,"caseMedicines":[],"content":"测得得的得的得的的1122","createTime":1452676049000,"creator":"9415d58d-7857-40e3-805d-43f8545543af","doctor":{"id":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","name":"1500***0","phone":"15000615390","professionalTitle":"住院医师"},"evaluateScore":0,"evaluateTime":"2016-01-12","id":"72713c87-4a8a-4df9-950b-1922faa7bdb3","inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"","phone":""},"memo":"null","modifier":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","modifyTime":1452759450000,"patient":{"birthday":1168531200000,"caseNum":0,"gender":"男","id":"2ff28022-2254-4adf-aa31-3aee854fe6bc","name":"家人"},"patientId":"2ff28022-2254-4adf-aa31-3aee854fe6bc","status":"2","veriftyTime":1452614400000,"zType":"0"}
     * message : 信息返回成功
     */

    private String code;
    /**
     * caseAdvices : []
     * caseChecks : []
     * caseLength : 0
     * caseMedicines : []
     * content : 测得得的得的得的的1122
     * createTime : 1452676049000
     * creator : 9415d58d-7857-40e3-805d-43f8545543af
     * doctor : {"id":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","name":"1500***0","phone":"15000615390","professionalTitle":"住院医师"}
     * evaluateScore : 0
     * evaluateTime : 2016-01-12
     * id : 72713c87-4a8a-4df9-950b-1922faa7bdb3
     * inquirer : {"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"","phone":""}
     * memo : null
     * modifier : 36b381c3-0de9-46f1-b1dd-b1c7ae8e3978
     * modifyTime : 1452759450000
     * patient : {"birthday":1168531200000,"caseNum":0,"gender":"男","id":"2ff28022-2254-4adf-aa31-3aee854fe6bc","name":"家人"}
     * patientId : 2ff28022-2254-4adf-aa31-3aee854fe6bc
     * status : 2
     * veriftyTime : 1452614400000
     * zType : 0
     */

    private DataEntity data;
    private String message;

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        private int caseLength;
        private String content;
        private long createTime;
        private String creator;
        private String casePic;
        /**
         * id : 36b381c3-0de9-46f1-b1dd-b1c7ae8e3978
         * name : 1500***0
         * phone : 15000615390
         * professionalTitle : 住院医师
         */

        private DoctorEntity doctor;
        private int evaluateScore;
        private String evaluateTime;
        private String id;
        /**
         * id : 9415d58d-7857-40e3-805d-43f8545543af
         * name : 1376***2
         * nickname :
         * phone :
         */

        private InquirerEntity inquirer;
        private String memo;
        private String modifier;
        private long modifyTime;
        /**
         * birthday : 1168531200000
         * caseNum : 0
         * gender : 男
         * id : 2ff28022-2254-4adf-aa31-3aee854fe6bc
         * name : 家人
         */

        private PatientEntity patient;
        private String patientId;
        private String status;
        private long veriftyTime;
        private String zType;
        private List<?> caseAdvices;
        private List<?> caseChecks;
        private List<?> caseMedicines;


        public String getCasePic() {
            return casePic;
        }

        public void setCasePic(String casePic) {
            this.casePic = casePic;
        }

        public String getzType() {
            return zType;
        }

        public void setzType(String zType) {
            this.zType = zType;
        }

        public void setCaseLength(int caseLength) {
            this.caseLength = caseLength;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public void setDoctor(DoctorEntity doctor) {
            this.doctor = doctor;
        }

        public void setEvaluateScore(int evaluateScore) {
            this.evaluateScore = evaluateScore;
        }

        public void setEvaluateTime(String evaluateTime) {
            this.evaluateTime = evaluateTime;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setInquirer(InquirerEntity inquirer) {
            this.inquirer = inquirer;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setPatient(PatientEntity patient) {
            this.patient = patient;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setVeriftyTime(long veriftyTime) {
            this.veriftyTime = veriftyTime;
        }

        public void setZType(String zType) {
            this.zType = zType;
        }

        public void setCaseAdvices(List<?> caseAdvices) {
            this.caseAdvices = caseAdvices;
        }

        public void setCaseChecks(List<?> caseChecks) {
            this.caseChecks = caseChecks;
        }

        public void setCaseMedicines(List<?> caseMedicines) {
            this.caseMedicines = caseMedicines;
        }

        public int getCaseLength() {
            return caseLength;
        }

        public String getContent() {
            return content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public String getCreator() {
            return creator;
        }

        public DoctorEntity getDoctor() {
            return doctor;
        }

        public int getEvaluateScore() {
            return evaluateScore;
        }

        public String getEvaluateTime() {
            return evaluateTime;
        }

        public String getId() {
            return id;
        }

        public InquirerEntity getInquirer() {
            return inquirer;
        }

        public String getMemo() {
            return memo;
        }

        public String getModifier() {
            return modifier;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public PatientEntity getPatient() {
            return patient;
        }

        public String getPatientId() {
            return patientId;
        }

        public String getStatus() {
            return status;
        }

        public long getVeriftyTime() {
            return veriftyTime;
        }

        public String getZType() {
            return zType;
        }

        public List<?> getCaseAdvices() {
            return caseAdvices;
        }

        public List<?> getCaseChecks() {
            return caseChecks;
        }

        public List<?> getCaseMedicines() {
            return caseMedicines;
        }

        public static class DoctorEntity {
            private String id;
            private String name;
            private String phone;
            private String professionalTitle;
            private String avatar;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public void setProfessionalTitle(String professionalTitle) {
                this.professionalTitle = professionalTitle;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getPhone() {
                return phone;
            }

            public String getProfessionalTitle() {
                return professionalTitle;
            }
        }

        public static class InquirerEntity {
            private String id;
            private String name;
            private String nickname;
            private String phone;
            private String avatar;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getNickname() {
                return nickname;
            }

            public String getPhone() {
                return phone;
            }
        }

        public static class PatientEntity {
            private long birthday;
            private int caseNum;
            private String gender;
            private String id;
            private String name;

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public void setCaseNum(int caseNum) {
                this.caseNum = caseNum;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getBirthday() {
                return birthday;
            }

            public int getCaseNum() {
                return caseNum;
            }

            public String getGender() {
                return gender;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }
    }
}
