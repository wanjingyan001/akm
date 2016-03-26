package cn.zsmy.akm.doctor.bean;

import java.util.List;

/**
 * 医生收支明细
 * Created by Administrator on 2015/12/25.
 */
public class Consumption {

    /**
     * code : SUCC
     * message : 数据返回成功
     * data : [{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","userId":"bae8edeb-d7f4-4c1d-883c-9ed3cbd6647c","accountId":null,"account":"1","srcAmount":1,"endAmount":1,"amount":1,"zType":"1","annexCode":"1","creator":"1","createTime":null},{"id":"f9b161f1-9697-431c-911f-06f295c3d6aa","userId":"f9b161f1-9697-431c-911f-06f295c3d6aa","accountId":null,"account":null,"srcAmount":1000,"endAmount":900,"amount":100,"zType":"1","annexCode":null,"creator":null,"createTime":null}]
     */

    private String code;
    private String message;
    /**
     * id : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * userId : bae8edeb-d7f4-4c1d-883c-9ed3cbd6647c
     * accountId : null
     * account : 1
     * srcAmount : 1
     * endAmount : 1
     * amount : 1
     * zType : 1
     * annexCode : 1
     * creator : 1
     * createTime : null
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
        private String account;
        private int srcAmount;
        private int endAmount;
        private int amount;
        private String zType;
        private String annexCode;
        private String creator;
        private Object createTime;

        public void setId(String id) {
            this.id = id;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setAccountId(Object accountId) {
            this.accountId = accountId;
        }

        public void setAccount(String account) {
            this.account = account;
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

        public void setAnnexCode(String annexCode) {
            this.annexCode = annexCode;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
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

        public String getAccount() {
            return account;
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

        public String getAnnexCode() {
            return annexCode;
        }

        public String getCreator() {
            return creator;
        }

        public Object getCreateTime() {
            return createTime;
        }
    }
}
