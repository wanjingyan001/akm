package cn.zsmy.akm.doctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/12/23.
 */
public class DoctorInfo implements Serializable{

    /**
     * code : SUCC
     * message : 消息返回成功
     * data : {"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246","introduc":"","professionalTitle":"住院医师","specialty":"","receptionNum":0,"avatar":null,"hospitalId":"13933968-1b07-430b-8ece-4d94aabf6439","dept":"心血管内科","cityId":"0b75db16-ab7c-11e5-a0de-4439c4558c0b","city":"北京市","phone":"15000615390","fullname":null,"authFlag":"3","working":"10年","birthday":null,"gender":"男","email":null,"registerTime":null,"workyear":null,"rcmdOrder":0,"chooseIndex":0,"lastLoginTime":null,"status2":null,"doctorAuth":[{"id":"f6e318cb-53b8-4422-83a0-5e649d7e0d87","doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","zType":null,"status":"0","authPic":"upload/authPic/2016/1/13/14526752095578338"}],"price":[]}
     */

    private String code;
    private String message;
    /**
     * id : bcf0735f-c335-4897-8ea3-ed14b25f23b0
     * name : sanz
     * hos : null
     * hospital : 上海市第七人民医院
     * deptId : 7978df44-9fe6-421b-aba0-f6beac258246
     * introduc :
     * professionalTitle : 住院医师
     * specialty :
     * receptionNum : 0
     * avatar : null
     * hospitalId : 13933968-1b07-430b-8ece-4d94aabf6439
     * dept : 心血管内科
     * cityId : 0b75db16-ab7c-11e5-a0de-4439c4558c0b
     * city : 北京市
     * phone : 15000615390
     * fullname : null
     * authFlag : 3
     * working : 10年
     * birthday : null
     * gender : 男
     * email : null
     * registerTime : null
     * workyear : null
     * rcmdOrder : 0
     * chooseIndex : 0
     * lastLoginTime : null
     * status2 : null
     * doctorAuth : [{"id":"f6e318cb-53b8-4422-83a0-5e649d7e0d87","doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","zType":null,"status":"0","authPic":"upload/authPic/2016/1/13/14526752095578338"}]
     * price : []
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
        private String name;
        private Object hos;
        private String hospital;
        private String deptId;
        private String introduc;
        private String professionalTitle;
        private String specialty;
        private int receptionNum;
        private Object avatar;
        private String hospitalId;
        private String dept;
        private String cityId;
        private String city;
        private String phone;
        private Object fullname;
        private String authFlag;
        private String working;
        private Object birthday;
        private String gender;
        private Object email;
        private Object registerTime;
        private Object workyear;
        private int rcmdOrder;
        private int chooseIndex;
        private Object lastLoginTime;
        private Object status2;
        /**
         * id : f6e318cb-53b8-4422-83a0-5e649d7e0d87
         * doctorId : bcf0735f-c335-4897-8ea3-ed14b25f23b0
         * zType : null
         * status : 0
         * authPic : upload/authPic/2016/1/13/14526752095578338
         */

        private List<DoctorAuthEntity> doctorAuth;
        private List<?> price;

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

        public void setAvatar(Object avatar) {
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

        public void setFullname(Object fullname) {
            this.fullname = fullname;
        }

        public void setAuthFlag(String authFlag) {
            this.authFlag = authFlag;
        }

        public void setWorking(String working) {
            this.working = working;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public void setRegisterTime(Object registerTime) {
            this.registerTime = registerTime;
        }

        public void setWorkyear(Object workyear) {
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

        public void setDoctorAuth(List<DoctorAuthEntity> doctorAuth) {
            this.doctorAuth = doctorAuth;
        }

        public void setPrice(List<?> price) {
            this.price = price;
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

        public Object getAvatar() {
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

        public Object getFullname() {
            return fullname;
        }

        public String getAuthFlag() {
            return authFlag;
        }

        public String getWorking() {
            return working;
        }

        public Object getBirthday() {
            return birthday;
        }

        public String getGender() {
            return gender;
        }

        public Object getEmail() {
            return email;
        }

        public Object getRegisterTime() {
            return registerTime;
        }

        public Object getWorkyear() {
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

        public List<DoctorAuthEntity> getDoctorAuth() {
            return doctorAuth;
        }

        public List<?> getPrice() {
            return price;
        }

        public static class DoctorAuthEntity {
            private String id;
            private String doctorId;
            private Object zType;
            private String status;
            private String authPic;

            public void setId(String id) {
                this.id = id;
            }

            public void setDoctorId(String doctorId) {
                this.doctorId = doctorId;
            }

            public void setZType(Object zType) {
                this.zType = zType;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setAuthPic(String authPic) {
                this.authPic = authPic;
            }

            public String getId() {
                return id;
            }

            public String getDoctorId() {
                return doctorId;
            }

            public Object getZType() {
                return zType;
            }

            public String getStatus() {
                return status;
            }

            public String getAuthPic() {
                return authPic;
            }
        }
    }
}
