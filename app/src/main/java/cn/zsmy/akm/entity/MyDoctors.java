package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 我的医生
 * Created by Administrator on 2015/12/29.
 */
public class MyDoctors {


    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"id":"b5d5dea4-7960-4662-a9ca-fa902d556161","userId":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","doctorId":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","num":3,"cases":{"id":"1fd66fb5-7669-4cc5-9db8-984efe31f1e5","patientId":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","content":"2016-0118-歷史複診1","memo":"null","creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1453095493000,"modifier":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","modifyTime":1453098597000,"status":"1","zType":"1","flag":"2","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":null,"lastChatTime":null,"veriftyTime":null,"triageTime":null,"inquirer":{"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"},"doctor":{"id":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","name":"1500***0","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"15000615390","hos":null,"hospital":"上海第七医院","deptId":"55b72c44-2a59-482f-bf5a-33f459556f28"},"patient":{"id":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","name":"我本人","birthday":506102400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}}]
     */

    private String code;
    private String message;
    /**
     * id : b5d5dea4-7960-4662-a9ca-fa902d556161
     * userId : d9489b4f-043b-4fbc-8ae2-e49b460e528c
     * doctorId : 36b381c3-0de9-46f1-b1dd-b1c7ae8e3978
     * num : 3
     * cases : {"id":"1fd66fb5-7669-4cc5-9db8-984efe31f1e5","patientId":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","content":"2016-0118-歷史複診1","memo":"null","creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1453095493000,"modifier":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","modifyTime":1453098597000,"status":"1","zType":"1","flag":"2","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":null,"lastChatTime":null,"veriftyTime":null,"triageTime":null,"inquirer":{"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"},"doctor":{"id":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","name":"1500***0","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"15000615390","hos":null,"hospital":"上海第七医院","deptId":"55b72c44-2a59-482f-bf5a-33f459556f28"},"patient":{"id":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","name":"我本人","birthday":506102400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}
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
        private String userId;
        private String doctorId;
        private int num;
        /**
         * id : 1fd66fb5-7669-4cc5-9db8-984efe31f1e5
         * patientId : 1eb1db67-dc28-4ae0-b854-9d0140adc8cb
         * content : 2016-0118-歷史複診1
         * memo : null
         * creator : d9489b4f-043b-4fbc-8ae2-e49b460e528c
         * createTime : 1453095493000
         * modifier : 36b381c3-0de9-46f1-b1dd-b1c7ae8e3978
         * modifyTime : 1453098597000
         * status : 1
         * zType : 1
         * flag : 2
         * offSet : null
         * evaluate : null
         * evaluateScore : 0
         * evaluateTime : null
         * caseLength : 0
         * lastChatType : null
         * lastChatTime : null
         * veriftyTime : null
         * triageTime : null
         * inquirer : {"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"}
         * doctor : {"id":"36b381c3-0de9-46f1-b1dd-b1c7ae8e3978","name":"1500***0","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"15000615390","hos":null,"hospital":"上海第七医院","deptId":"55b72c44-2a59-482f-bf5a-33f459556f28"}
         * patient : {"id":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","name":"我本人","birthday":506102400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}
         */

        private CasesEntity cases;

        public void setId(String id) {
            this.id = id;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setCases(CasesEntity cases) {
            this.cases = cases;
        }

        public String getId() {
            return id;
        }

        public String getUserId() {
            return userId;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public int getNum() {
            return num;
        }

        public CasesEntity getCases() {
            return cases;
        }

        public static class CasesEntity {
            private String id;
            private String patientId;
            private String content;
            private String memo;
            private String creator;
            private long createTime;
            private String modifier;
            private long modifyTime;
            private String status;
            private String zType;
            private String flag;
            private Object offSet;
            private Object evaluate;
            private int evaluateScore;
            private Object evaluateTime;
            private int caseLength;
            private Object lastChatType;
            private Object lastChatTime;
            private Object veriftyTime;
            private Object triageTime;
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

            public void setEvaluateTime(Object evaluateTime) {
                this.evaluateTime = evaluateTime;
            }

            public void setCaseLength(int caseLength) {
                this.caseLength = caseLength;
            }

            public void setLastChatType(Object lastChatType) {
                this.lastChatType = lastChatType;
            }

            public void setLastChatTime(Object lastChatTime) {
                this.lastChatTime = lastChatTime;
            }

            public void setVeriftyTime(Object veriftyTime) {
                this.veriftyTime = veriftyTime;
            }

            public void setTriageTime(Object triageTime) {
                this.triageTime = triageTime;
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

            public Object getEvaluateTime() {
                return evaluateTime;
            }

            public int getCaseLength() {
                return caseLength;
            }

            public Object getLastChatType() {
                return lastChatType;
            }

            public Object getLastChatTime() {
                return lastChatTime;
            }

            public Object getVeriftyTime() {
                return veriftyTime;
            }

            public Object getTriageTime() {
                return triageTime;
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
}
