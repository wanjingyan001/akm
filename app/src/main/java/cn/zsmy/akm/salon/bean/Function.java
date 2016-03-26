package cn.zsmy.akm.salon.bean;

import java.util.List;

/**
 * Created by sanz on 2016/1/21 14:09
 */
public class Function {

    /**
     * code : SUCC
     * message : 返回消息成功
     * data : [{"id":"02c2a81b-21c1-4bdf-a8ea-2d47a5031df6","code":"020","title":"免费体验","pubTime":1450713600000,"endTime":1453478400000,"startTime":1450108800000,"logo":"logo7.png","httpUrl":"www.006.com","zType":null,"content":"扫二维码免费体验"},{"id":"81d47718-0bd1-467a-b3e4-8c487abe8523","code":"﻿deposit","title":"30","pubTime":1450540800000,"endTime":1453478400000,"startTime":1450886400000,"logo":"logo1.png","httpUrl":"www.001.com","zType":null,"content":"10"}]
     */

    private String code;
    private String message;
    /**
     * id : 02c2a81b-21c1-4bdf-a8ea-2d47a5031df6
     * code : 020
     * title : 免费体验
     * pubTime : 1450713600000
     * endTime : 1453478400000
     * startTime : 1450108800000
     * logo : logo7.png
     * httpUrl : www.006.com
     * zType : null
     * content : 扫二维码免费体验
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
        private String code;
        private String title;
        private long pubTime;
        private long endTime;
        private long startTime;
        private String logo;
        private String httpUrl;
        private Object zType;
        private String content;

        public void setId(String id) {
            this.id = id;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPubTime(long pubTime) {
            this.pubTime = pubTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public void setHttpUrl(String httpUrl) {
            this.httpUrl = httpUrl;
        }

        public void setZType(Object zType) {
            this.zType = zType;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public String getCode() {
            return code;
        }

        public String getTitle() {
            return title;
        }

        public long getPubTime() {
            return pubTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public String getLogo() {
            return logo;
        }

        public String getHttpUrl() {
            return httpUrl;
        }

        public Object getZType() {
            return zType;
        }

        public String getContent() {
            return content;
        }
    }
}
