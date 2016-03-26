package cn.zsmy.akm.doctor.conversation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 病人的详细病历
 * Created by Administrator on 2015/12/29.
 */
public class PatientDetail implements Serializable{


    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"id":"8d8f03fb-2e04-4fd3-9dc7-73647cb22639","patientId":"00a9f630-e69e-4924-8bc2-1c1ee74f8a70","content":"咳嗽，不舒服","caseImg":null,"memo":"流鼻涕","offSet":null,"creator":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","createTime":1450340495000,"modifier":"f9b161f1-9697-431c-911f-06f295c3d6aa","modifyTime":1450847035000,"status":"2","flag":null,"inquirer":{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null},"doctor":{"id":"f9b161f1-9697-431c-911f-06f295c3d6aa","name":"李四","hos":null,"hospital":"上海第六医院","deptId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647d","introduc":"皮肤科医生","professionalTitle":"中级职称主治","specialty":"皮肤病","receptionNum":0,"avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","hospitalId":"f9b161f1-9697-431c-911f-06f295c3d6aa","dept":"皮肤科","cityId":"","city":"","phone":"13612155695","fullname":"lisi","birthday":474393600000,"gender":"男","email":"lisi@163.com","registerTime":1450248704000,"workyear":"7","rcmdOrder":12,"chooseIndex":0,"lastLoginTime":null,"status2":null},"patient":{"id":"00a9f630-e69e-4924-8bc2-1c1ee74f8a70","inquirerId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"cherry","gender":"1","birthday":629395200000,"caseNum":0,"areaId":"","offsetX":"12","offsetY":"32","offset":"上海市","status":"1"}}]
     */

    private String code;
    private String message;
    /**
     * id : 8d8f03fb-2e04-4fd3-9dc7-73647cb22639
     * patientId : 00a9f630-e69e-4924-8bc2-1c1ee74f8a70
     * content : 咳嗽，不舒服
     * caseImg : null
     * memo : 流鼻涕
     * offSet : null
     * creator : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * createTime : 1450340495000
     * modifier : f9b161f1-9697-431c-911f-06f295c3d6aa
     * modifyTime : 1450847035000
     * status : 2
     * flag : null
     * inquirer : {"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}
     * doctor : {"id":"f9b161f1-9697-431c-911f-06f295c3d6aa","name":"李四","hos":null,"hospital":"上海第六医院","deptId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647d","introduc":"皮肤科医生","professionalTitle":"中级职称主治","specialty":"皮肤病","receptionNum":0,"avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","hospitalId":"f9b161f1-9697-431c-911f-06f295c3d6aa","dept":"皮肤科","cityId":"","city":"","phone":"13612155695","fullname":"lisi","birthday":474393600000,"gender":"男","email":"lisi@163.com","registerTime":1450248704000,"workyear":"7","rcmdOrder":12,"chooseIndex":0,"lastLoginTime":null,"status2":null}
     * patient : {"id":"00a9f630-e69e-4924-8bc2-1c1ee74f8a70","inquirerId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"cherry","gender":"1","birthday":629395200000,"caseNum":0,"areaId":"","offsetX":"12","offsetY":"32","offset":"上海市","status":"1"}
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

    public class DataEntity implements Serializable{
        private String id;
        private String patientId;
        private String content;
        private Object caseImg;
        private String memo;
        private Object offSet;
        private String creator;
        private long createTime;
        private String modifier;
        private long modifyTime;
        private String status;
        private Object flag;
        /**
         * id : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
         * name : 张三
         * nickname : 小子
         * phone : 18512155695
         * avatar : 120.26.91.145/upload/headpic/2015/10/19/14453295990498602
         * gender : 男
         * birthday : 534700800000
         * registerTime : 1449131928000
         * lastLoginTime : 1449131928000
         * caseNum : 2
         * areaId : null
         * offsetX : null
         * offsetY : null
         * offset : 上海市
         * status : null
         */

        private InquirerEntity inquirer;
        /**
         * id : f9b161f1-9697-431c-911f-06f295c3d6aa
         * name : 李四
         * hos : null
         * hospital : 上海第六医院
         * deptId : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647d
         * introduc : 皮肤科医生
         * professionalTitle : 中级职称主治
         * specialty : 皮肤病
         * receptionNum : 0
         * avatar : 120.26.91.145/upload/headpic/2015/10/19/14453295990498602
         * hospitalId : f9b161f1-9697-431c-911f-06f295c3d6aa
         * dept : 皮肤科
         * cityId :
         * city :
         * phone : 13612155695
         * fullname : lisi
         * birthday : 474393600000
         * gender : 男
         * email : lisi@163.com
         * registerTime : 1450248704000
         * workyear : 7
         * rcmdOrder : 12
         * chooseIndex : 0
         * lastLoginTime : null
         * status2 : null
         */

        private DoctorEntity doctor;
        /**
         * id : 00a9f630-e69e-4924-8bc2-1c1ee74f8a70
         * inquirerId : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
         * name : cherry
         * gender : 1
         * birthday : 629395200000
         * caseNum : 0
         * areaId :
         * offsetX : 12
         * offsetY : 32
         * offset : 上海市
         * status : 1
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

        public InquirerEntity getInquirer() {
            return inquirer;
        }

        public DoctorEntity getDoctor() {
            return doctor;
        }

        public PatientEntity getPatient() {
            return patient;
        }

        public class InquirerEntity implements Serializable{
            private String id;
            private String name;
            private String nickname;
            private String phone;
            private String avatar;
            private String gender;
            private long birthday;
            private long registerTime;
            private long lastLoginTime;
            private int caseNum;
            private Object areaId;
            private Object offsetX;
            private Object offsetY;
            private String offset;
            private Object status;

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

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public void setRegisterTime(long registerTime) {
                this.registerTime = registerTime;
            }

            public void setLastLoginTime(long lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public void setCaseNum(int caseNum) {
                this.caseNum = caseNum;
            }

            public void setAreaId(Object areaId) {
                this.areaId = areaId;
            }

            public void setOffsetX(Object offsetX) {
                this.offsetX = offsetX;
            }

            public void setOffsetY(Object offsetY) {
                this.offsetY = offsetY;
            }

            public void setOffset(String offset) {
                this.offset = offset;
            }

            public void setStatus(Object status) {
                this.status = status;
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

            public String getAvatar() {
                return avatar;
            }

            public String getGender() {
                return gender;
            }

            public long getBirthday() {
                return birthday;
            }

            public long getRegisterTime() {
                return registerTime;
            }

            public long getLastLoginTime() {
                return lastLoginTime;
            }

            public int getCaseNum() {
                return caseNum;
            }

            public Object getAreaId() {
                return areaId;
            }

            public Object getOffsetX() {
                return offsetX;
            }

            public Object getOffsetY() {
                return offsetY;
            }

            public String getOffset() {
                return offset;
            }

            public Object getStatus() {
                return status;
            }
        }

        public  class DoctorEntity implements Serializable{
            private String id;
            private String name;
            private Object hos;
            private String hospital;
            private String deptId;
            private String introduc;
            private String professionalTitle;
            private String specialty;
            private int receptionNum;
            private String avatar;
            private String hospitalId;
            private String dept;
            private String cityId;
            private String city;
            private String phone;
            private String fullname;
            private long birthday;
            private String gender;
            private String email;
            private long registerTime;
            private String workyear;
            private int rcmdOrder;
            private int chooseIndex;
            private Object lastLoginTime;
            private Object status2;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
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

            public void setIntroduc(String introduc) {
                this.introduc = introduc;
            }

            public void setProfessionalTitle(String professionalTitle) {
                this.professionalTitle = professionalTitle;
            }

            public void setSpecialty(String specialty) {
                this.specialty = specialty;
            }

            public void setReceptionNum(int receptionNum) {
                this.receptionNum = receptionNum;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setHospitalId(String hospitalId) {
                this.hospitalId = hospitalId;
            }

            public void setDept(String dept) {
                this.dept = dept;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public void setFullname(String fullname) {
                this.fullname = fullname;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setRegisterTime(long registerTime) {
                this.registerTime = registerTime;
            }

            public void setWorkyear(String workyear) {
                this.workyear = workyear;
            }

            public void setRcmdOrder(int rcmdOrder) {
                this.rcmdOrder = rcmdOrder;
            }

            public void setChooseIndex(int chooseIndex) {
                this.chooseIndex = chooseIndex;
            }

            public void setLastLoginTime(Object lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public void setStatus2(Object status2) {
                this.status2 = status2;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
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

            public String getIntroduc() {
                return introduc;
            }

            public String getProfessionalTitle() {
                return professionalTitle;
            }

            public String getSpecialty() {
                return specialty;
            }

            public int getReceptionNum() {
                return receptionNum;
            }

            public String getAvatar() {
                return avatar;
            }

            public String getHospitalId() {
                return hospitalId;
            }

            public String getDept() {
                return dept;
            }

            public String getCityId() {
                return cityId;
            }

            public String getCity() {
                return city;
            }

            public String getPhone() {
                return phone;
            }

            public String getFullname() {
                return fullname;
            }

            public long getBirthday() {
                return birthday;
            }

            public String getGender() {
                return gender;
            }

            public String getEmail() {
                return email;
            }

            public long getRegisterTime() {
                return registerTime;
            }

            public String getWorkyear() {
                return workyear;
            }

            public int getRcmdOrder() {
                return rcmdOrder;
            }

            public int getChooseIndex() {
                return chooseIndex;
            }

            public Object getLastLoginTime() {
                return lastLoginTime;
            }

            public Object getStatus2() {
                return status2;
            }
        }

        public  class PatientEntity implements Serializable {
            private String id;
            private String inquirerId;
            private String name;
            private String gender;
            private long birthday;
            private int caseNum;
            private String areaId;
            private String offsetX;
            private String offsetY;
            private String offset;
            private String status;

            public void setId(String id) {
                this.id = id;
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

            public void setAreaId(String areaId) {
                this.areaId = areaId;
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

            public void setStatus(String status) {
                this.status = status;
            }

            public String getId() {
                return id;
            }

            public String getInquirerId() {
                return inquirerId;
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

            public String getAreaId() {
                return areaId;
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

            public String getStatus() {
                return status;
            }
        }
    }
}
