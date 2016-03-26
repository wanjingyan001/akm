package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 患者病例
 * Created by Administrator on 2015/12/29.
 */
public class PatientCase {


    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"id":"766b8faf-b8f8-4162-85b6-10dbfd7f9e9f","patientId":"00a9f630-e69e-4924-8bc2-1c1ee74f8a70","content":"头痛发烧","memo":null,"creator":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","createTime":1450923523000,"modifier":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","modifyTime":1450923523000,"status":"1","flag":null,"offSet":null,"evaluate":"中国好医生","evaluateScore":23,"evaluateTime":null,"doctor":{"id":"f9b161f1-9697-431c-911f-06f295c3d6aa","name":"赵六","professionalTitle":"中级职称主治","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602"},"patient":{"id":"00a9f630-e69e-4924-8bc2-1c1ee74f8a70","name":"cherry","caseNum":0,"offsetX":"12","offsetY":"32","offset":"上海市"},"caseAdvices":[{"id":"51ba4eed-57b0-4366-ad53-0e57c470b915","content":"多喝水，注意保暖"}]}]
     */

    private String code;
    private String message;
    /**
     * id : 766b8faf-b8f8-4162-85b6-10dbfd7f9e9f
     * patientId : 00a9f630-e69e-4924-8bc2-1c1ee74f8a70
     * content : 头痛发烧
     * memo : null
     * creator : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * createTime : 1450923523000
     * modifier : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * modifyTime : 1450923523000
     * status : 1
     * flag : null
     * offSet : null
     * evaluate : 中国好医生
     * evaluateScore : 23
     * evaluateTime : null
     * doctor : {"id":"f9b161f1-9697-431c-911f-06f295c3d6aa","name":"赵六","professionalTitle":"中级职称主治","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602"}
     * patient : {"id":"00a9f630-e69e-4924-8bc2-1c1ee74f8a70","name":"cherry","caseNum":0,"offsetX":"12","offsetY":"32","offset":"上海市"}
     * caseAdvices : [{"id":"51ba4eed-57b0-4366-ad53-0e57c470b915","content":"多喝水，注意保暖"}]
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
        private Object memo;
        private String creator;
        private long createTime;
        private String modifier;
        private long modifyTime;
        private String status;
        private Object flag;
        private Object offSet;
        private String evaluate;
        private int evaluateScore;
        private Object evaluateTime;
        /**
         * id : f9b161f1-9697-431c-911f-06f295c3d6aa
         * name : 赵六
         * professionalTitle : 中级职称主治
         * avatar : 120.26.91.145/upload/headpic/2015/10/19/14453295990498602
         */

        private DoctorEntity doctor;
        /**
         * id : 00a9f630-e69e-4924-8bc2-1c1ee74f8a70
         * name : cherry
         * caseNum : 0
         * offsetX : 12
         * offsetY : 32
         * offset : 上海市
         */

        private PatientEntity patient;
        /**
         * id : 51ba4eed-57b0-4366-ad53-0e57c470b915
         * content : 多喝水，注意保暖
         */

        private List<CaseAdvicesEntity> caseAdvices;

        public void setId(String id) {
            this.id = id;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
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

        public void setFlag(Object flag) {
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

        public void setEvaluateTime(Object evaluateTime) {
            this.evaluateTime = evaluateTime;
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

        public String getId() {
            return id;
        }

        public String getPatientId() {
            return patientId;
        }

        public String getContent() {
            return content;
        }

        public Object getMemo() {
            return memo;
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

        public Object getFlag() {
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

        public Object getEvaluateTime() {
            return evaluateTime;
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

        public static class DoctorEntity {
            private String id;
            private String name;
            private String professionalTitle;
            private String avatar;

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
        }

        public static class PatientEntity {
            private String id;
            private String name;
            private int caseNum;
            private String offsetX;
            private String offsetY;
            private String offset;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setCaseNum(int caseNum) {
                this.caseNum = caseNum;
            }

            public void setOffsetX(String offsetX) {
                this.offsetX = offsetX;
            }

            public void setOffsetY(String offsetY) {
                this.offsetY = offsetY;
            }

            public void setOffset(String offset) {
                this.offset = offset;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public int getCaseNum() {
                return caseNum;
            }

            public String getOffsetX() {
                return offsetX;
            }

            public String getOffsetY() {
                return offsetY;
            }

            public String getOffset() {
                return offset;
            }
        }

        public static class CaseAdvicesEntity {
            private String id;
            private String content;
            private String aeger;

            public void setId(String id) {
                this.id = id;
            }

            public void setContent(String content) {
                this.content = content;
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

            public void setAeger(String aeger) {
                this.aeger = aeger;
            }
        }
    }
}
