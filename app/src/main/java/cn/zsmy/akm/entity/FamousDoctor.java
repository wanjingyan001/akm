package cn.zsmy.akm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/1/15.
 */
public class FamousDoctor {


    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz2","doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246","introduc":"擅长疾病治疗","professionalTitle":"住院医师","specialty":"擅长疾病治疗","receptionNum":0,"avatar":"upload/casePic/2016/1/14/14527739227084854","hospitalId":"13933968-1b07-430b-8ece-4d94aabf6439","dept":"心血管内科","cityId":"0b75db16-ab7c-11e5-a0de-4439c4558c0b","city":"北京市","phone":"13585906920","fullname":null,"authFlag":"3","working":"10年","birthday":null,"gender":"男","email":null,"registerTime":null,"workyear":null,"rcmdOrder":0,"chooseIndex":91,"lastLoginTime":null,"status2":null,"doctorAuth":[{"id":"f6e318cb-53b8-4422-83a0-5e649d7e0d87","doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","zType":null,"status":"0","authPic":"upload/authPic/2016/1/13/14526756293306412"}],"price":[{"id":"f6776b49-bb2d-11e5-a0de-4439c4558c0b","doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","zType":"2","payType":"2","amt":"10.0000","unit":"1分钟","creator":null,"createTime":0,"modifier":null,"modifyTime":0,"status":"0","delFlag":"1","flag":"0"}],"cases":null}}]
     */

    private String code;
    private String message;
    /**
     * id : bcf0735f-c335-4897-8ea3-ed14b25f23b0
     * doctorId : bcf0735f-c335-4897-8ea3-ed14b25f23b0
     * name : sanz2
     * doctor : {"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246","introduc":"擅长疾病治疗","professionalTitle":"住院医师","specialty":"擅长疾病治疗","receptionNum":0,"avatar":"upload/casePic/2016/1/14/14527739227084854","hospitalId":"13933968-1b07-430b-8ece-4d94aabf6439","dept":"心血管内科","cityId":"0b75db16-ab7c-11e5-a0de-4439c4558c0b","city":"北京市","phone":"13585906920","fullname":null,"authFlag":"3","working":"10年","birthday":null,"gender":"男","email":null,"registerTime":null,"workyear":null,"rcmdOrder":0,"chooseIndex":91,"lastLoginTime":null,"status2":null,"doctorAuth":[{"id":"f6e318cb-53b8-4422-83a0-5e649d7e0d87","doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","zType":null,"status":"0","authPic":"upload/authPic/2016/1/13/14526756293306412"}],"price":[{"id":"f6776b49-bb2d-11e5-a0de-4439c4558c0b","doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","zType":"2","payType":"2","amt":"10.0000","unit":"1分钟","creator":null,"createTime":0,"modifier":null,"modifyTime":0,"status":"0","delFlag":"1","flag":"0"}],"cases":null}
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
        private String doctorId;
        private String name;
        /**
         * id : bcf0735f-c335-4897-8ea3-ed14b25f23b0
         * name : sanz
         * hos : null
         * hospital : 上海市第七人民医院
         * deptId : 7978df44-9fe6-421b-aba0-f6beac258246
         * introduc : 擅长疾病治疗
         * professionalTitle : 住院医师
         * specialty : 擅长疾病治疗
         * receptionNum : 0
         * avatar : upload/casePic/2016/1/14/14527739227084854
         * hospitalId : 13933968-1b07-430b-8ece-4d94aabf6439
         * dept : 心血管内科
         * cityId : 0b75db16-ab7c-11e5-a0de-4439c4558c0b
         * city : 北京市
         * phone : 13585906920
         * fullname : null
         * authFlag : 3
         * working : 10年
         * birthday : null
         * gender : 男
         * email : null
         * registerTime : null
         * workyear : null
         * rcmdOrder : 0
         * chooseIndex : 91
         * lastLoginTime : null
         * status2 : null
         * doctorAuth : [{"id":"f6e318cb-53b8-4422-83a0-5e649d7e0d87","doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","zType":null,"status":"0","authPic":"upload/authPic/2016/1/13/14526756293306412"}]
         * price : [{"id":"f6776b49-bb2d-11e5-a0de-4439c4558c0b","doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","zType":"2","payType":"2","amt":"10.0000","unit":"1分钟","creator":null,"createTime":0,"modifier":null,"modifyTime":0,"status":"0","delFlag":"1","flag":"0"}]
         * cases : null
         */

        private DoctorEntity doctor;

        public void setId(String id) {
            this.id = id;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDoctor(DoctorEntity doctor) {
            this.doctor = doctor;
        }

        public String getId() {
            return id;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public String getName() {
            return name;
        }

        public DoctorEntity getDoctor() {
            return doctor;
        }

        public static class DoctorEntity {
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
            private Object cases;
            /**
             * id : f6e318cb-53b8-4422-83a0-5e649d7e0d87
             * doctorId : bcf0735f-c335-4897-8ea3-ed14b25f23b0
             * zType : null
             * status : 0
             * authPic : upload/authPic/2016/1/13/14526756293306412
             */

            private List<DoctorAuthEntity> doctorAuth;
            /**
             * id : f6776b49-bb2d-11e5-a0de-4439c4558c0b
             * doctorId : bcf0735f-c335-4897-8ea3-ed14b25f23b0
             * zType : 2
             * payType : 2
             * amt : 10.0000
             * unit : 1分钟
             * creator : null
             * createTime : 0
             * modifier : null
             * modifyTime : 0
             * status : 0
             * delFlag : 1
             * flag : 0
             */

            private List<PriceEntity> price;

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

            public void setCases(Object cases) {
                this.cases = cases;
            }

            public void setDoctorAuth(List<DoctorAuthEntity> doctorAuth) {
                this.doctorAuth = doctorAuth;
            }

            public void setPrice(List<PriceEntity> price) {
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

            public Object getCases() {
                return cases;
            }

            public List<DoctorAuthEntity> getDoctorAuth() {
                return doctorAuth;
            }

            public List<PriceEntity> getPrice() {
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

            public static class PriceEntity {
                private String id;
                private String doctorId;
                private String zType;
                private String payType;
                private String amt;
                private String unit;
                private Object creator;
                private Object modifier;
                private String status;
                private String delFlag;
                private String flag;

                public void setId(String id) {
                    this.id = id;
                }

                public void setDoctorId(String doctorId) {
                    this.doctorId = doctorId;
                }

                public void setZType(String zType) {
                    this.zType = zType;
                }

                public void setPayType(String payType) {
                    this.payType = payType;
                }

                public void setAmt(String amt) {
                    this.amt = amt;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public void setCreator(Object creator) {
                    this.creator = creator;
                }


                public void setModifier(Object modifier) {
                    this.modifier = modifier;
                }


                public void setStatus(String status) {
                    this.status = status;
                }

                public void setDelFlag(String delFlag) {
                    this.delFlag = delFlag;
                }

                public void setFlag(String flag) {
                    this.flag = flag;
                }

                public String getId() {
                    return id;
                }

                public String getDoctorId() {
                    return doctorId;
                }

                public String getZType() {
                    return zType;
                }

                public String getPayType() {
                    return payType;
                }

                public String getAmt() {
                    return amt;
                }

                public String getUnit() {
                    return unit;
                }

                public Object getCreator() {
                    return creator;
                }


                public Object getModifier() {
                    return modifier;
                }


                public String getStatus() {
                    return status;
                }

                public String getDelFlag() {
                    return delFlag;
                }

                public String getFlag() {
                    return flag;
                }
            }
        }
    }
}
