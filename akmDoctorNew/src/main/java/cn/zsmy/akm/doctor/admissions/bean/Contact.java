package cn.zsmy.akm.doctor.admissions.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sanz on 2015/12/24 15:52
 */
public class Contact implements Serializable {
    /**
     * code : SUCC
     * message : 病历返回成功
     * data : [{"id":"1fe7843d-d91d-4c09-83c7-34d0b5d555d7","patientId":"539beae4-a9e6-4471-8e06-46078dee59af","content":"2016-0120-复诊测试1","memo":"null","creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","casePic":"upload/casePic/2016/1/20/14532788918666022","createTime":1453278892000,"modifier":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","modifyTime":1453445413000,"status":"2","zType":"1","flag":"2","offSet":null,"evaluate":"2016-0122-评价测试\n好滴就是觉得结婚相机惊喜就是想就好像基地回家虾兵蟹将好吧撒卡吧撒几记不得害羞大概v独角戏看书呢学不会哦睡觉喜欢","evaluateScore":35,"evaluateTime":"2016-01-22","caseLength":0,"lastChatType":"doctor","lastChatTime":1453280058000,"veriftyTime":1453357945000,"triageTime":null,"annexCode":null,"lastChatContent":"医生","inquirer":{"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"539beae4-a9e6-4471-8e06-46078dee59af","name":"我自己","birthday":537638400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null},"caseAdvices":[{"id":"acc5f011-aa1a-4d8f-bbd2-f005f859ddd5","content":"","aeger":"2016-0121-就医建议1伏笔故居隔壁姐ID刺蛇佛波i给i好玩的发生从事就从基地为FB你哦白痴哦彼此的林念慈恩赐哦呵许嵩念慈庵佛事哦哦黑FB诶鄙视"}],"caseChecks":[],"caseMedicines":[]}]
     */
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    /**
     * id : 1fe7843d-d91d-4c09-83c7-34d0b5d555d7
     * patientId : 539beae4-a9e6-4471-8e06-46078dee59af
     * content : 2016-0120-复诊测试1
     * memo : null
     * creator : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * casePic : upload/casePic/2016/1/20/14532788918666022
     * createTime : 1453278892000
     * modifier : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * modifyTime : 1453445413000
     * status : 2
     * zType : 1
     * flag : 2
     * offSet : null
     * evaluate : 2016-0122-评价测试
     好滴就是觉得结婚相机惊喜就是想就好像基地回家虾兵蟹将好吧撒卡吧撒几记不得害羞大概v独角戏看书呢学不会哦睡觉喜欢
     * evaluateScore : 35
     * evaluateTime : 2016-01-22
     * caseLength : 0
     * lastChatType : doctor
     * lastChatTime : 1453280058000
     * veriftyTime : 1453357945000
     * triageTime : null
     * annexCode : null
     * lastChatContent : 医生
     * inquirer : {"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"}
     * doctor : {"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"}
     * patient : {"id":"539beae4-a9e6-4471-8e06-46078dee59af","name":"我自己","birthday":537638400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}
     * caseAdvices : [{"id":"acc5f011-aa1a-4d8f-bbd2-f005f859ddd5","content":"","aeger":"2016-0121-就医建议1伏笔故居隔壁姐ID刺蛇佛波i给i好玩的发生从事就从基地为FB你哦白痴哦彼此的林念慈恩赐哦呵许嵩念慈庵佛事哦哦黑FB诶鄙视"}]
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

    public static class DataEntity implements Serializable{
        private static final long serialVersionUID = 1L;
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
        private String evaluate;
        private int evaluateScore;
        private String evaluateTime;
        private int caseLength;
        private String lastChatType;
        private long lastChatTime;
        private long veriftyTime;
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
         * id : bcf0735f-c335-4897-8ea3-ed14b25f23b0
         * name : sanz
         * professionalTitle : 住院医师
         * avatar : upload/casePic/2016/1/14/14527739227084854
         * phone : 13585906920
         * hos : null
         * hospital : 上海市第七人民医院
         * deptId : 7978df44-9fe6-421b-aba0-f6beac258246
         */

        private DoctorEntity doctor;
        /**
         * id : 539beae4-a9e6-4471-8e06-46078dee59af
         * name : 我自己
         * birthday : 537638400000
         * gender : 男
         * caseNum : 0
         * offsetX : null
         * offsetY : null
         * offset : null
         */

        private PatientEntity patient;
        /**
         * id : acc5f011-aa1a-4d8f-bbd2-f005f859ddd5
         * content :
         * aeger : 2016-0121-就医建议1伏笔故居隔壁姐ID刺蛇佛波i给i好玩的发生从事就从基地为FB你哦白痴哦彼此的林念慈恩赐哦呵许嵩念慈庵佛事哦哦黑FB诶鄙视
         */

        private List<CaseAdvicesEntity> caseAdvices;
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

        public void setEvaluate(String evaluate) {
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

        public void setVeriftyTime(long veriftyTime) {
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

        public void setCaseAdvices(List<CaseAdvicesEntity> caseAdvices) {
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

        public String getEvaluate() {
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

        public long getVeriftyTime() {
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

        public List<CaseAdvicesEntity> getCaseAdvices() {
            return caseAdvices;
        }

        public List<?> getCaseChecks() {
            return caseChecks;
        }

        public List<?> getCaseMedicines() {
            return caseMedicines;
        }

        public static class InquirerEntity implements Serializable {
            private static final long serialVersionUID = 1L;
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

        public static class DoctorEntity implements Serializable{
            private static final long serialVersionUID = 1L;
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

        public static class PatientEntity implements Serializable{
            private static final long serialVersionUID = 1L;
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

        public static class CaseAdvicesEntity implements Serializable {
            private String id;
            private String content;
            private String aeger;

            public void setId(String id) {
                this.id = id;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setAeger(String aeger) {
                this.aeger = aeger;
            }

            public String getId() {
                return id;
            }

            public String getContent() {
                return content;
            }

            public String getAeger() {
                return aeger;
            }
        }
    }
}
