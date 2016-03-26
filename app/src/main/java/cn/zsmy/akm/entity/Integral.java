package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 积分
 * Created by wanjingyan
 * on 2015/12/9 14:20.
 */
public class Integral {

    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"id":"69aa9149-17d2-4c99-804d-6377df1cb765","userId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","accountId":null,"account":null,"annexCode":"","srcAmount":326,"endAmount":426,"amount":100,"zType":"0","creator":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","createTime":1450861328000,"modifier":null,"modifyTime":null,"status":null,"delFlag":"0","flag":"0"},{"id":"a3271bdf-7bd5-4ef0-8678-f93cf9ba950b","userId":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","accountId":null,"account":null,"annexCode":"","srcAmount":226,"endAmount":126,"amount":100,"zType":"1","creator":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","createTime":1450861442000,"modifier":null,"modifyTime":null,"status":null,"delFlag":"0","flag":"0"}]
     */

    private String code;
    private String message;
    /**
     * id : 69aa9149-17d2-4c99-804d-6377df1cb765
     * userId : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * accountId : null
     * account : null
     * annexCode :
     * srcAmount : 326
     * endAmount : 426
     * amount : 100
     * zType : 0
     * creator : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * createTime : 1450861328000
     * modifier : null
     * modifyTime : null
     * status : null
     * delFlag : 0
     * flag : 0
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
        private Object accountId;
        private Object account;
        private String annexCode;
        private int srcAmount;
        private int endAmount;
        private int amount;
        private String zType;
        private String creator;
        private long createTime;
        private Object modifier;
        private Object modifyTime;
        private Object status;
        private String delFlag;
        private String flag;

        public void setId(String id) {
            this.id = id;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setAccountId(Object accountId) {
            this.accountId = accountId;
        }

        public void setAccount(Object account) {
            this.account = account;
        }

        public void setAnnexCode(String annexCode) {
            this.annexCode = annexCode;
        }

        public void setSrcAmount(int srcAmount) {
            this.srcAmount = srcAmount;
        }

        public void setEndAmount(int endAmount) {
            this.endAmount = endAmount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setZType(String zType) {
            this.zType = zType;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setModifier(Object modifier) {
            this.modifier = modifier;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setStatus(Object status) {
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

        public String getUserId() {
            return userId;
        }

        public Object getAccountId() {
            return accountId;
        }

        public Object getAccount() {
            return account;
        }

        public String getAnnexCode() {
            return annexCode;
        }

        public int getSrcAmount() {
            return srcAmount;
        }

        public int getEndAmount() {
            return endAmount;
        }

        public int getAmount() {
            return amount;
        }

        public String getZType() {
            return zType;
        }

        public String getCreator() {
            return creator;
        }

        public long getCreateTime() {
            return createTime;
        }

        public Object getModifier() {
            return modifier;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public Object getStatus() {
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
