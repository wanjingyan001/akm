package cn.zsmy.akm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/1/29.
 */
public class Recommend {
    /**
     * code : SUCC
     * message : 返回消息成功
     * data : [{"id":"02c2a81b-21c1-4bdf-a8ea-2d47a5031df6","code":"020","title":"免费体验","subTitle":null,"recommend":"false","pubTime":1450713600000,"endTime":1453478400000,"startTime":1450108800000,"logo":"upload/activity/doc11.png","httpUrl":"upload/activity/doc_activity_01.html","zType":"2","content":"扫二维码免费体验"},{"id":"606f3a59-4c4e-4b9b-bcdb-49b93c8b8002","code":"011","title":"健康咨询","subTitle":null,"recommend":"false","pubTime":1450800000000,"endTime":1482854400000,"startTime":1451232000000,"logo":"upload/activity/doc22.png","httpUrl":"upload/activity/doc_activity_02.html","zType":"2","content":"301医院专家为大家进行健康咨询"},{"id":"a1974e57-9eaa-4fc7-bd1e-9680987569e4","code":"010","title":"义诊","subTitle":null,"recommend":"false","pubTime":1450713600000,"endTime":1482681600000,"startTime":1450972800000,"logo":"upload/activity/doc33.png","httpUrl":"upload/activity/doc_activity_02.html","zType":"1","content":"给中老年客户免费看病一天"},{"id":"aa4d3397-8726-4767-a4b8-5b300bfb0799","code":"011","title":"注册送优惠券","subTitle":null,"recommend":"false","pubTime":1450800000000,"endTime":1452700800000,"startTime":1452355200000,"logo":"upload/activity/user33.png","httpUrl":"upload/activity/user_activity_02.html","zType":"1","content":"殷教授作学术讲座，欢迎大家去旁听"}]
     */

    private String code;
    private String message;
    /**
     * id : 02c2a81b-21c1-4bdf-a8ea-2d47a5031df6
     * code : 020
     * title : 免费体验
     * subTitle : null
     * recommend : false
     * pubTime : 1450713600000
     * endTime : 1453478400000
     * startTime : 1450108800000
     * logo : upload/activity/doc11.png
     * httpUrl : upload/activity/doc_activity_01.html
     * zType : 2
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
        private Object subTitle;
        private String recommend;
        private long pubTime;
        private long endTime;
        private long startTime;
        private String logo;
        private String httpUrl;
        private String zType;
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

        public void setSubTitle(Object subTitle) {
            this.subTitle = subTitle;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
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

        public void setZType(String zType) {
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

        public Object getSubTitle() {
            return subTitle;
        }

        public String getRecommend() {
            return recommend;
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

        public String getZType() {
            return zType;
        }

        public String getContent() {
            return content;
        }
    }
}
