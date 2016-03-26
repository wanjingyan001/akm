package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 医生评价列表
 * Created by Administrator on 2015/12/24.
 */
public class Evaluation {

    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"evaluateScore":0,"evaluate":null,"evaluateTime":null,"inquirer":{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}},{"evaluateScore":0,"evaluate":null,"evaluateTime":null,"inquirer":{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}},{"evaluateScore":0,"evaluate":null,"evaluateTime":null,"inquirer":{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}},{"evaluateScore":0,"evaluate":null,"evaluateTime":null,"inquirer":{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}},{"evaluateScore":0,"evaluate":null,"evaluateTime":null,"inquirer":{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}},{"evaluateScore":0,"evaluate":null,"evaluateTime":null,"inquirer":{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}},{"evaluateScore":0,"evaluate":null,"evaluateTime":null,"inquirer":{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}},{"evaluateScore":0,"evaluate":null,"evaluateTime":null,"inquirer":{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}},{"evaluateScore":0,"evaluate":null,"evaluateTime":null,"inquirer":{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}}]
     */

    private String code;
    private String message;
    /**
     * evaluateScore : 0
     * evaluate : null
     * evaluateTime : null
     * inquirer : {"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","name":"张三","nickname":"小子","phone":"18512155695","avatar":"120.26.91.145/upload/headpic/2015/10/19/14453295990498602","gender":"男","birthday":534700800000,"registerTime":1449131928000,"lastLoginTime":1449131928000,"caseNum":2,"areaId":null,"offsetX":null,"offsetY":null,"offset":"上海市","status":null}
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
        private int evaluateScore;
        private Object evaluate;
        private long evaluateTime;
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

        public void setEvaluateScore(int evaluateScore) {
            this.evaluateScore = evaluateScore;
        }

        public void setEvaluate(Object evaluate) {
            this.evaluate = evaluate;
        }

        public void setEvaluateTime(long evaluateTime) {
            this.evaluateTime = evaluateTime;
        }

        public void setInquirer(InquirerEntity inquirer) {
            this.inquirer = inquirer;
        }

        public int getEvaluateScore() {
            return evaluateScore;
        }

        public Object getEvaluate() {
            return evaluate;
        }

        public long getEvaluateTime() {
            return evaluateTime;
        }

        public InquirerEntity getInquirer() {
            return inquirer;
        }

        public static class InquirerEntity {
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
}
