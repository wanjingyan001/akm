package cn.zsmy.akm.doctor.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/1/8.
 */
public class ServiceInfo {

    /**
     * code : SUCC
     * message : 数据返回成功
     * data : [{"id":"741af552-624b-4aa1-b308-e17ce4932cfb","doctorId":"0fc52d11-986b-4d88-8279-8b408b8d937b","zType":"3","payType":"2","amt":"20.0000","unit":"5分钟","creator":"0fc52d11-986b-4d88-8279-8b408b8d937b","createTime":1452047384000,"modifier":"0fc52d11-986b-4d88-8279-8b408b8d937b","modifyTime":1452047384000,"status":null,"delFlag":"0","flag":null},{"id":"8e4bb4cc-a6e4-4394-8394-9318988f3323","doctorId":"0fc52d11-986b-4d88-8279-8b408b8d937b","zType":"3","payType":"2","amt":"20.0000","unit":"10分钟","creator":"0fc52d11-986b-4d88-8279-8b408b8d937b","createTime":1451958378000,"modifier":"0fc52d11-986b-4d88-8279-8b408b8d937b","modifyTime":1452047417000,"status":null,"delFlag":"0","flag":null},{"id":"b0967ece-b2d3-11e5-a0de-4439c4558c0b","doctorId":"0fc52d11-986b-4d88-8279-8b408b8d937b","zType":"1","payType":"1","amt":"10.0000","unit":"10分钟","creator":null,"createTime":0,"modifier":null,"modifyTime":0,"status":null,"delFlag":"1","flag":null},{"id":"b372ede2-f5b0-456c-8c19-dcbca2a06fd0","doctorId":"0fc52d11-986b-4d88-8279-8b408b8d937b","zType":"1","payType":"1","amt":"13.0000","unit":"3分钟","creator":"0fc52d11-986b-4d88-8279-8b408b8d937b","createTime":1451956740000,"modifier":"0fc52d11-986b-4d88-8279-8b408b8d937b","modifyTime":1451956895000,"status":null,"delFlag":"0","flag":null},{"id":"c2983546-b2d3-11e5-a0de-4439c4558c0b","doctorId":"0fc52d11-986b-4d88-8279-8b408b8d937b","zType":"2","payType":"2","amt":"5.0000","unit":"1分钟","creator":null,"createTime":0,"modifier":null,"modifyTime":0,"status":null,"delFlag":"1","flag":null}]
     */

    private String code;
    private String message;
    /**
     * id : 741af552-624b-4aa1-b308-e17ce4932cfb
     * doctorId : 0fc52d11-986b-4d88-8279-8b408b8d937b
     * zType : 3
     * payType : 2
     * amt : 20.0000
     * unit : 5分钟
     * creator : 0fc52d11-986b-4d88-8279-8b408b8d937b
     * createTime : 1452047384000
     * modifier : 0fc52d11-986b-4d88-8279-8b408b8d937b
     * modifyTime : 1452047384000
     * status : null
     * delFlag : 0
     * flag : null
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
        private String zType;
        private String payType;
        private String amt;
        private String unit;
        private String creator;
        private long createTime;
        private String modifier;
        private long modifyTime;
        private Object status;
        private String delFlag;
        private Object flag;

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

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public void setFlag(Object flag) {
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

        public Object getStatus() {
            return status;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public Object getFlag() {
            return flag;
        }
    }
}
