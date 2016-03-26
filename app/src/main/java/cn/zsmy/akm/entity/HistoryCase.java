package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 历史病例
 * Created by sanz on 2015/12/25 11:27
 */
public class HistoryCase {
    /**
     * code : SUCC
     * message : 病历返回成功
     * data : [{"id":"c217ac93-7c19-47a5-a309-c8498ec7a45c","patientId":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","content":"2016-0120-VIP问诊1","memo":"null","creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","casePic":"upload/casePic/2016/1/20/14532803385764356","createTime":1453280339000,"modifier":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","modifyTime":1453280449000,"status":"2","zType":"1","flag":"1","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":"doctor","lastChatTime":1453280448000,"veriftyTime":null,"triageTime":null,"annexCode":null,"lastChatContent":"嗯","inquirer":{"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"},"doctor":{"id":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","name":"1500***0","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"15000615390","hos":null,"hospital":"上海第七医院","deptId":"55b72c44-2a59-482f-bf5a-33f459556f28"},"patient":{"id":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","name":"我本人","birthday":506102400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null},"caseAdvices":[],"caseChecks":[],"caseMedicines":[]},{"id":"917f52a8-5df9-4dc6-8037-069c05807d55","patientId":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","content":"2016-0120-忽略测试1","memo":"null","creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","casePic":"upload/casePic/2016/1/20/14532796972953873","createTime":1453279697000,"modifier":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","modifyTime":1453369935000,"status":"2","zType":"1","flag":"2","offSet":null,"evaluate":"1234567822345678","evaluateScore":45,"evaluateTime":"2016-01-21","caseLength":0,"lastChatType":null,"lastChatTime":null,"veriftyTime":null,"triageTime":null,"annexCode":null,"lastChatContent":null,"inquirer":{"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","name":"我本人","birthday":506102400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null},"caseAdvices":[],"caseChecks":[],"caseMedicines":[]}]
     */

    private String code;
    private String message;
    /**
     * id : c217ac93-7c19-47a5-a309-c8498ec7a45c
     * patientId : 1eb1db67-dc28-4ae0-b854-9d0140adc8cb
     * content : 2016-0120-VIP问诊1
     * memo : null
     * creator : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * casePic : upload/casePic/2016/1/20/14532803385764356
     * createTime : 1453280339000
     * modifier : 36b381c3-0de9-46f1-b1dd-b1c7ae8e3978
     * modifyTime : 1453280449000
     * status : 2
     * zType : 1
     * flag : 1
     * offSet : null
     * evaluate : null
     * evaluateScore : 0
     * evaluateTime : null
     * caseLength : 0
     * lastChatType : doctor
     * lastChatTime : 1453280448000
     * veriftyTime : null
     * triageTime : null
     * annexCode : null
     * lastChatContent : 嗯
     * inquirer : {"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"}
     * doctor : {"id":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","name":"1500***0","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"15000615390","hos":null,"hospital":"上海第七医院","deptId":"55b72c44-2a59-482f-bf5a-33f459556f28"}
     * patient : {"id":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","name":"我本人","birthday":506102400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}
     * caseAdvices : []
     * caseChecks : []
     * caseMedicines : []
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
        private String memo;
        private String creator;
        private String casePic;
        private long createTime;
        private String modifier;
        private long modifyTime;
        private String status;
        private String zType;
        private String flag;
        private Object offSet;
        private Object evaluate;
        private int evaluateScore;
        private String evaluateTime;
        private int caseLength;
        private String lastChatType;
        private long lastChatTime;
        private Object veriftyTime;
        private Object triageTime;
        private Object annexCode;
        private String lastChatContent;
        /**
         * id : d9489b4f-043b-4fbc-8ae2-e49b460e528c
         * name : 1351***8
         * nickname : 隊長
         * avatar : upload/avatar/2016/1/18/14530891835042092
         * phone : 13511007508
         */

        private InquirerEntity inquirer;
        /**
         * id : 36b381c3-0de9-46f1-b1dd-b1c7ae8e3978
         * name : 1500***0
         * professionalTitle : 住院医师
         * avatar : upload/casePic/2016/1/14/14527739227084854
         * phone : 15000615390
         * hos : null
         * hospital : 上海第七医院
         * deptId : 55b72c44-2a59-482f-bf5a-33f459556f28
         */

        private DoctorEntity doctor;
        /**
         * id : 1eb1db67-dc28-4ae0-b854-9d0140adc8cb
         * name : 我本人
         * birthday : 506102400000
         * gender : 男
         * caseNum : 0
         * offsetX : null
         * offsetY : null
         * offset : null
         */

        private PatientEntity patient;
        private List<?> caseAdvices;
        private List<?> caseChecks;
        private List<?> caseMedicines;

        public void setId(String id) {
            this.id = id;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public void setCasePic(String casePic) {
            this.casePic = casePic;
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

        public void setZType(String zType) {
            this.zType = zType;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setOffSet(Object offSet) {
            this.offSet = offSet;
        }

        public void setEvaluate(Object evaluate) {
            this.evaluate = evaluate;
        }

        public void setEvaluateScore(int evaluateScore) {
            this.evaluateScore = evaluateScore;
        }

        public void setEvaluateTime(String evaluateTime) {
            this.evaluateTime = evaluateTime;
        }

        public void setCaseLength(int caseLength) {
            this.caseLength = caseLength;
        }

        public void setLastChatType(String lastChatType) {
            this.lastChatType = lastChatType;
        }

        public void setLastChatTime(long lastChatTime) {
            this.lastChatTime = lastChatTime;
        }

        public void setVeriftyTime(Object veriftyTime) {
            this.veriftyTime = veriftyTime;
        }

        public void setTriageTime(Object triageTime) {
            this.triageTime = triageTime;
        }

        public void setAnnexCode(Object annexCode) {
            this.annexCode = annexCode;
        }

        public void setLastChatContent(String lastChatContent) {
            this.lastChatContent = lastChatContent;
        }

        public void setInquirer(InquirerEntity inquirer) {
            this.inquirer = inquirer;
        }

        public void setDoctor(DoctorEntity doctor) {
            this.doctor = doctor;
        }

        public void setPatient(PatientEntity patient) {
            this.patient = patient;
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

        public String getId() {
            return id;
        }

        public String getPatientId() {
            return patientId;
        }

        public String getContent() {
            return content;
        }

        public String getMemo() {
            return memo;
        }

        public String getCreator() {
            return creator;
        }

        public String getCasePic() {
            return casePic;
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

        public String getZType() {
            return zType;
        }

        public String getFlag() {
            return flag;
        }

        public Object getOffSet() {
            return offSet;
        }

        public Object getEvaluate() {
            return evaluate;
        }

        public int getEvaluateScore() {
            return evaluateScore;
        }

        public String getEvaluateTime() {
            return evaluateTime;
        }

        public int getCaseLength() {
            return caseLength;
        }

        public String getLastChatType() {
            return lastChatType;
        }

        public long getLastChatTime() {
            return lastChatTime;
        }

        public Object getVeriftyTime() {
            return veriftyTime;
        }

        public Object getTriageTime() {
            return triageTime;
        }

        public Object getAnnexCode() {
            return annexCode;
        }

        public String getLastChatContent() {
            return lastChatContent;
        }

        public InquirerEntity getInquirer() {
            return inquirer;
        }

        public DoctorEntity getDoctor() {
            return doctor;
        }

        public PatientEntity getPatient() {
            return patient;
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

        public static class InquirerEntity {
            private String id;
            private String name;
            private String nickname;
            private String avatar;
            private String phone;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
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

            public String getAvatar() {
                return avatar;
            }

            public String getPhone() {
                return phone;
            }
        }

        public static class DoctorEntity {
            private String id;
            private String name;
            private String professionalTitle;
            private String avatar;
            private String phone;
            private Object hos;
            private String hospital;
            private String deptId;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setProfessionalTitle(String professionalTitle) {
                this.professionalTitle = professionalTitle;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public void setHos(Object hos) {
                this.hos = hos;
            }

            public void setHospital(String hospital) {
                this.hospital = hospital;
            }

            public void setDeptId(String deptId) {
                this.deptId = deptId;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getProfessionalTitle() {
                return professionalTitle;
            }

            public String getAvatar() {
                return avatar;
            }

            public String getPhone() {
                return phone;
            }

            public Object getHos() {
                return hos;
            }

            public String getHospital() {
                return hospital;
            }

            public String getDeptId() {
                return deptId;
            }
        }

        public static class PatientEntity {
            private String id;
            private String name;
            private long birthday;
            private String gender;
            private int caseNum;
            private Object offsetX;
            private Object offsetY;
            private Object offset;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setCaseNum(int caseNum) {
                this.caseNum = caseNum;
            }

            public void setOffsetX(Object offsetX) {
                this.offsetX = offsetX;
            }

            public void setOffsetY(Object offsetY) {
                this.offsetY = offsetY;
            }

            public void setOffset(Object offset) {
                this.offset = offset;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public long getBirthday() {
                return birthday;
            }

            public String getGender() {
                return gender;
            }

            public int getCaseNum() {
                return caseNum;
            }

            public Object getOffsetX() {
                return offsetX;
            }

            public Object getOffsetY() {
                return offsetY;
            }

            public Object getOffset() {
                return offset;
            }
        }
    }
}
