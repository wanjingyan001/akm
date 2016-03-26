package cn.zsmy.akm.entity;

/**
 * Created by Administrator on 2015/12/21.
 */
public class UserInfo {

    /**
     * code : SUCC
     * message : 消息返回成功
     * data : {"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":null,"gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}
     */

    private String code;
    private String message;
    /**
     * id : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * name : 张三
     * nickname : 小子
     * phone : 18512155695
     * avatar : null
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
}
