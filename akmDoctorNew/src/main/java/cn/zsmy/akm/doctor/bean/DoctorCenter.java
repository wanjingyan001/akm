package cn.zsmy.akm.doctor.bean;

import java.io.Serializable;

/**
 * 医生个人中心
 * Created by Administrator on 2015/12/23.
 */
public class DoctorCenter implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * code : SUCC
     * message : 消息返回成功
     * data : {"id":"0fc52d11-986b-4d88-8279-8b408b8d937b","name":"李锋","score":230,"balance":200,"onlinfFlag":null,"avail":23,"authFlag":null}
     */

    private String code;
    private String message;
    /**
     * id : 0fc52d11-986b-4d88-8279-8b408b8d937b
     * name : 李锋
     * score : 230
     * balance : 200.0
     * onlinfFlag : null
     * avail : 23.0
     * authFlag : null
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

    public  class DataEntity {
        private String id;
        private String name;
        private int score;
        private double balance;
        private Object onlinfFlag;
        private double avail;
        private Object authFlag;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public void setOnlinfFlag(Object onlinfFlag) {
            this.onlinfFlag = onlinfFlag;
        }

        public void setAvail(double avail) {
            this.avail = avail;
        }

        public void setAuthFlag(Object authFlag) {
            this.authFlag = authFlag;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public double getBalance() {
            return balance;
        }

        public Object getOnlinfFlag() {
            return onlinfFlag;
        }

        public double getAvail() {
            return avail;
        }

        public Object getAuthFlag() {
            return authFlag;
        }
    }
}
