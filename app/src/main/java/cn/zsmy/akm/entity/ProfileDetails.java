package cn.zsmy.akm.entity;

import java.io.Serializable;

/**
 * 个人中心
 * Created by Administrator on 2015/12/22.
 */
public class ProfileDetails implements Serializable {

    /**
     * code : SUCC
     * message : 消息返回成功
     * data : {"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c","noticeNum":12,"score":123,"hisCaseNum":4,"loanAmount":0,"validAmount":1230,"amount":1230,"wasteAmount":125,"revenueTotel":225,"epositTotel":12345}
     */
    private static final long serialVersionUID = 1L; // 定义序列化ID
    private String code;
    private String message;
    /**
     * id : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647c
     * noticeNum : 12
     * score : 123
     * hisCaseNum : 4
     * loanAmount : 0
     * validAmount : 1230
     * amount : 1230
     * wasteAmount : 125.0
     * revenueTotel : 225.0
     * epositTotel : 12345.0
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

    public static class DataEntity implements Serializable {
        private static final long serialVersionUID = 1L; // 定义序列化ID
        private String id;
        private int noticeNum;
        private int score;
        private int hisCaseNum;
        private int loanAmount;
        private int validAmount;
        private int amount;
        private double wasteAmount;
        private double revenueTotel;
        private double epositTotel;

        public void setId(String id) {
            this.id = id;
        }

        public void setNoticeNum(int noticeNum) {
            this.noticeNum = noticeNum;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void setHisCaseNum(int hisCaseNum) {
            this.hisCaseNum = hisCaseNum;
        }

        public void setLoanAmount(int loanAmount) {
            this.loanAmount = loanAmount;
        }

        public void setValidAmount(int validAmount) {
            this.validAmount = validAmount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setWasteAmount(double wasteAmount) {
            this.wasteAmount = wasteAmount;
        }

        public void setRevenueTotel(double revenueTotel) {
            this.revenueTotel = revenueTotel;
        }

        public void setEpositTotel(double epositTotel) {
            this.epositTotel = epositTotel;
        }

        public String getId() {
            return id;
        }

        public int getNoticeNum() {
            return noticeNum;
        }

        public int getScore() {
            return score;
        }

        public int getHisCaseNum() {
            return hisCaseNum;
        }

        public int getLoanAmount() {
            return loanAmount;
        }

        public int getValidAmount() {
            return validAmount;
        }

        public int getAmount() {
            return amount;
        }

        public double getWasteAmount() {
            return wasteAmount;
        }

        public double getRevenueTotel() {
            return revenueTotel;
        }

        public double getEpositTotel() {
            return epositTotel;
        }
    }
}
