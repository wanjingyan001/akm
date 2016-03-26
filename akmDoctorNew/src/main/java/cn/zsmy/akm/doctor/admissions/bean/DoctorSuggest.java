package cn.zsmy.akm.doctor.admissions.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/1/16.
 */
public class DoctorSuggest {

    /**
     * code : SUCC
     * data : {"caseAdvices":[{"aeger":"发送测试","content":"没有","hospitalId":"bc8f8e77-b526-11e5-a0de-4439c4558c0b","hospitalName":"民航上海医院","id":"226ddea3-5337-45d8-8ab5-9a107c905221","ifInquiry":"0"}],"caseChecks":[{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","id":"989e2e72-74e1-4053-bb4f-52b326d6fe37","inspection":"常规检查项目","inspectionId":"562ef123-31fc-43ea-ad0a-f5a5c843c5fb"},{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","id":"c8afc9d1-1b67-48b1-ad7f-6eeefb79f850","inspection":"常规检查项目","inspectionId":"d5ed5430-cb5c-4f13-864c-382f046f10ab"}],"caseLength":0,"caseMedicines":[{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","issueNumber":1,"medicineId":"2b1e2fa9-5f21-46ac-86ff-aa08db7a3d15","medicineName":"盐酸特比萘芬乳膏","usageAmount":1},{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","issueNumber":1,"medicineId":"6fc6c81f-2352-4e49-a447-9c9f784fc69e","medicineName":"复方聚维酮碘搽剂","usageAmount":1},{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","issueNumber":1,"medicineId":"537a5745-fe83-4929-b49a-273c55930028","medicineName":"醋酸氟轻松乳膏","usageAmount":2},{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","issueNumber":1,"medicineId":"c2702b40-7343-423f-a83b-17ec5c19feec","medicineName":"倍他米松乳膏","usageAmount":2}],"casePic":"upload/casePic/2016/1/28/14539768590621882","content":"2016-0128-11","createTime":1453976859000,"creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","doctor":{"avatar":"upload/casePic/2016/1/14/14527739227084854","deptId":"7978df44-9fe6-421b-aba0-f6beac258246","hospital":"上海市第七人民医院","id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"1234","phone":"13585906920","professionalTitle":"住院医师"},"evaluateScore":0,"flag":"0","id":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","inquirer":{"avatar":"upload/avatar/2016/1/28/14539822464817697","id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"我是问诊人1","phone":"13511007508"},"lastChatContent":"嘎嘎嘎","lastChatTime":1453976921000,"lastChatType":"doctor","memo":"null","modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1454045729000,"patient":{"birthday":475689600000,"caseNum":0,"gender":"男","id":"4c49fd25-404c-46fa-9214-78261305cb2a","name":"我"},"patientId":"4c49fd25-404c-46fa-9214-78261305cb2a","status":"1","triageTime":0,"veriftyTime":1454045729000,"zType":"1"}
     * message : 信息返回成功
     */

    private String code;
    /**
     * caseAdvices : [{"aeger":"发送测试","content":"没有","hospitalId":"bc8f8e77-b526-11e5-a0de-4439c4558c0b","hospitalName":"民航上海医院","id":"226ddea3-5337-45d8-8ab5-9a107c905221","ifInquiry":"0"}]
     * caseChecks : [{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","id":"989e2e72-74e1-4053-bb4f-52b326d6fe37","inspection":"常规检查项目","inspectionId":"562ef123-31fc-43ea-ad0a-f5a5c843c5fb"},{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","id":"c8afc9d1-1b67-48b1-ad7f-6eeefb79f850","inspection":"常规检查项目","inspectionId":"d5ed5430-cb5c-4f13-864c-382f046f10ab"}]
     * caseLength : 0
     * caseMedicines : [{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","issueNumber":1,"medicineId":"2b1e2fa9-5f21-46ac-86ff-aa08db7a3d15","medicineName":"盐酸特比萘芬乳膏","usageAmount":1},{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","issueNumber":1,"medicineId":"6fc6c81f-2352-4e49-a447-9c9f784fc69e","medicineName":"复方聚维酮碘搽剂","usageAmount":1},{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","issueNumber":1,"medicineId":"537a5745-fe83-4929-b49a-273c55930028","medicineName":"醋酸氟轻松乳膏","usageAmount":2},{"caseId":"ad22e0fa-e9bd-4c23-8978-5a065f30e97b","issueNumber":1,"medicineId":"c2702b40-7343-423f-a83b-17ec5c19feec","medicineName":"倍他米松乳膏","usageAmount":2}]
     * casePic : upload/casePic/2016/1/28/14539768590621882
     * content : 2016-0128-11
     * createTime : 1453976859000
     * creator : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * doctor : {"avatar":"upload/casePic/2016/1/14/14527739227084854","deptId":"7978df44-9fe6-421b-aba0-f6beac258246","hospital":"上海市第七人民医院","id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"1234","phone":"13585906920","professionalTitle":"住院医师"}
     * evaluateScore : 0
     * flag : 0
     * id : ad22e0fa-e9bd-4c23-8978-5a065f30e97b
     * inquirer : {"avatar":"upload/avatar/2016/1/28/14539822464817697","id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"我是问诊人1","phone":"13511007508"}
     * lastChatContent : 嘎嘎嘎
     * lastChatTime : 1453976921000
     * lastChatType : doctor
     * memo : null
     * modifier : bcf0735f-c335-4897-8ea3-ed14b25f23b0
     * modifyTime : 1454045729000
     * patient : {"birthday":475689600000,"caseNum":0,"gender":"男","id":"4c49fd25-404c-46fa-9214-78261305cb2a","name":"我"}
     * patientId : 4c49fd25-404c-46fa-9214-78261305cb2a
     * status : 1
     * triageTime : 0
     * veriftyTime : 1454045729000
     * zType : 1
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
        private String casePic;
        private String content;
        private long createTime;
        private String creator;
        /**
         * avatar : upload/casePic/2016/1/14/14527739227084854
         * deptId : 7978df44-9fe6-421b-aba0-f6beac258246
         * hospital : 上海市第七人民医院
         * id : bcf0735f-c335-4897-8ea3-ed14b25f23b0
         * name : 1234
         * phone : 13585906920
         * professionalTitle : 住院医师
         */

        private DoctorEntity doctor;
        private int evaluateScore;
        private String flag;
        private String id;
        /**
         * avatar : upload/avatar/2016/1/28/14539822464817697
         * id : d9489b4f-043b-4fbc-8ae2-e49b460e528c
         * name : 1351***8
         * nickname : 我是问诊人1
         * phone : 13511007508
         */

        private InquirerEntity inquirer;
        private String lastChatContent;
        private long lastChatTime;
        private String lastChatType;
        private String memo;
        private String modifier;
        private long modifyTime;
        /**
         * birthday : 475689600000
         * caseNum : 0
         * gender : 男
         * id : 4c49fd25-404c-46fa-9214-78261305cb2a
         * name : 我
         */

        private PatientEntity patient;
        private String patientId;
        private String status;
        private int triageTime;
        private long veriftyTime;
        private String zType;
        /**
         * aeger : 发送测试
         * content : 没有
         * hospitalId : bc8f8e77-b526-11e5-a0de-4439c4558c0b
         * hospitalName : 民航上海医院
         * id : 226ddea3-5337-45d8-8ab5-9a107c905221
         * ifInquiry : 0
         */

        private List<CaseAdvicesEntity> caseAdvices;
        /**
         * caseId : ad22e0fa-e9bd-4c23-8978-5a065f30e97b
         * id : 989e2e72-74e1-4053-bb4f-52b326d6fe37
         * inspection : 常规检查项目
         * inspectionId : 562ef123-31fc-43ea-ad0a-f5a5c843c5fb
         */

        private List<CaseChecksEntity> caseChecks;
        /**
         * caseId : ad22e0fa-e9bd-4c23-8978-5a065f30e97b
         * issueNumber : 1
         * medicineId : 2b1e2fa9-5f21-46ac-86ff-aa08db7a3d15
         * medicineName : 盐酸特比萘芬乳膏
         * usageAmount : 1
         */

        private List<CaseMedicinesEntity> caseMedicines;

        public void setCaseLength(int caseLength) {
            this.caseLength = caseLength;
        }

        public void setCasePic(String casePic) {
            this.casePic = casePic;
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

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setInquirer(InquirerEntity inquirer) {
            this.inquirer = inquirer;
        }

        public void setLastChatContent(String lastChatContent) {
            this.lastChatContent = lastChatContent;
        }

        public void setLastChatTime(long lastChatTime) {
            this.lastChatTime = lastChatTime;
        }

        public void setLastChatType(String lastChatType) {
            this.lastChatType = lastChatType;
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

        public void setTriageTime(int triageTime) {
            this.triageTime = triageTime;
        }

        public void setVeriftyTime(long veriftyTime) {
            this.veriftyTime = veriftyTime;
        }

        public void setZType(String zType) {
            this.zType = zType;
        }

        public void setCaseAdvices(List<CaseAdvicesEntity> caseAdvices) {
            this.caseAdvices = caseAdvices;
        }

        public void setCaseChecks(List<CaseChecksEntity> caseChecks) {
            this.caseChecks = caseChecks;
        }

        public void setCaseMedicines(List<CaseMedicinesEntity> caseMedicines) {
            this.caseMedicines = caseMedicines;
        }

        public int getCaseLength() {
            return caseLength;
        }

        public String getCasePic() {
            return casePic;
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

        public String getFlag() {
            return flag;
        }

        public String getId() {
            return id;
        }

        public InquirerEntity getInquirer() {
            return inquirer;
        }

        public String getLastChatContent() {
            return lastChatContent;
        }

        public long getLastChatTime() {
            return lastChatTime;
        }

        public String getLastChatType() {
            return lastChatType;
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

        public int getTriageTime() {
            return triageTime;
        }

        public long getVeriftyTime() {
            return veriftyTime;
        }

        public String getZType() {
            return zType;
        }

        public List<CaseAdvicesEntity> getCaseAdvices() {
            return caseAdvices;
        }

        public List<CaseChecksEntity> getCaseChecks() {
            return caseChecks;
        }

        public List<CaseMedicinesEntity> getCaseMedicines() {
            return caseMedicines;
        }

        public static class DoctorEntity {
            private String avatar;
            private String deptId;
            private String hospital;
            private String id;
            private String name;
            private String phone;
            private String professionalTitle;

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setDeptId(String deptId) {
                this.deptId = deptId;
            }

            public void setHospital(String hospital) {
                this.hospital = hospital;
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

            public String getAvatar() {
                return avatar;
            }

            public String getDeptId() {
                return deptId;
            }

            public String getHospital() {
                return hospital;
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
            private String avatar;
            private String id;
            private String name;
            private String nickname;
            private String phone;

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

            public String getAvatar() {
                return avatar;
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

        public static class CaseAdvicesEntity {
            private String aeger;
            private String content;
            private String hospitalId;
            private String hospitalName;
            private String id;
            private String ifInquiry;

            public void setAeger(String aeger) {
                this.aeger = aeger;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setHospitalId(String hospitalId) {
                this.hospitalId = hospitalId;
            }

            public void setHospitalName(String hospitalName) {
                this.hospitalName = hospitalName;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setIfInquiry(String ifInquiry) {
                this.ifInquiry = ifInquiry;
            }

            public String getAeger() {
                return aeger;
            }

            public String getContent() {
                return content;
            }

            public String getHospitalId() {
                return hospitalId;
            }

            public String getHospitalName() {
                return hospitalName;
            }

            public String getId() {
                return id;
            }

            public String getIfInquiry() {
                return ifInquiry;
            }
        }

        public static class CaseChecksEntity {
            private String caseId;
            private String id;
            private String inspection;
            private String inspectionId;

            public void setCaseId(String caseId) {
                this.caseId = caseId;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setInspection(String inspection) {
                this.inspection = inspection;
            }

            public void setInspectionId(String inspectionId) {
                this.inspectionId = inspectionId;
            }

            public String getCaseId() {
                return caseId;
            }

            public String getId() {
                return id;
            }

            public String getInspection() {
                return inspection;
            }

            public String getInspectionId() {
                return inspectionId;
            }
        }

        public static class CaseMedicinesEntity {
            private String caseId;
            private int issueNumber;
            private String medicineId;
            private String medicineName;
            private int usageAmount;
            private String usegeMethod;

            public void setCaseId(String caseId) {
                this.caseId = caseId;
            }

            public void setIssueNumber(int issueNumber) {
                this.issueNumber = issueNumber;
            }

            public void setMedicineId(String medicineId) {
                this.medicineId = medicineId;
            }

            public void setMedicineName(String medicineName) {
                this.medicineName = medicineName;
            }

            public void setUsageAmount(int usageAmount) {
                this.usageAmount = usageAmount;
            }

            public String getCaseId() {
                return caseId;
            }

            public int getIssueNumber() {
                return issueNumber;
            }

            public String getMedicineId() {
                return medicineId;
            }

            public String getMedicineName() {
                return medicineName;
            }

            public int getUsageAmount() {
                return usageAmount;
            }

            public String getUsegeMethod() {
                return usegeMethod;
            }

            public void setUsegeMethod(String usegeMethod) {
                this.usegeMethod = usegeMethod;
            }
        }
    }
}
