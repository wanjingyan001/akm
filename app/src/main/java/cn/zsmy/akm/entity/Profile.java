package cn.zsmy.akm.entity;

/**
 * Created by qinwei on 2015/11/17 14:50
 */
public class Profile {
    /**
     * accessToken : ffa17ebe-6818-4dcc-a199-2445379ab03b
     * avatar : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * code : SUCC
     * data : {"createTime":1449891371000,"credentialsSalt":"18512155695f418f5c155dc2b47f4c11b7a30629361","delFlag":"0","flag":"0","id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","modifyTime":1449891371000,"nickname":"问诊人","password":"7e902860e60709ba936488189bc25aca","salt":"f418f5c155dc2b47f4c11b7a30629361","userType":"akeman","username":"18512155695"}
     * message : 登陆成功
     * nickname : 问诊人
     * userId : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     */

    private String accessToken;
    private String avatar;
    private String code;
    /**
     * createTime : 1449891371000
     * credentialsSalt : 18512155695f418f5c155dc2b47f4c11b7a30629361
     * delFlag : 0
     * flag : 0
     * id : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * modifyTime : 1449891371000
     * nickname : 问诊人
     * password : 7e902860e60709ba936488189bc25aca
     * salt : f418f5c155dc2b47f4c11b7a30629361
     * userType : akeman
     * username : 18512155695
     */

    private DataEntity data;
    private String message;
    private String nickname;
    private String userId;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAvatar() {
        return avatar;
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

    public String getNickname() {
        return nickname;
    }

    public String getUserId() {
        return userId;
    }

    public  class DataEntity {
        private long createTime;
        private String credentialsSalt;
        private String delFlag;
        private String flag;
        private String id;
        private long modifyTime;
        private String nickname;
        private String password;
        private String salt;
        private String userType;
        private String username;

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setCredentialsSalt(String credentialsSalt) {
            this.credentialsSalt = credentialsSalt;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public long getCreateTime() {
            return createTime;
        }

        public String getCredentialsSalt() {
            return credentialsSalt;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public String getFlag() {
            return flag;
        }

        public String getId() {
            return id;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public String getNickname() {
            return nickname;
        }

        public String getPassword() {
            return password;
        }

        public String getSalt() {
            return salt;
        }

        public String getUserType() {
            return userType;
        }

        public String getUsername() {
            return username;
        }
    }
//    private String id;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

}
