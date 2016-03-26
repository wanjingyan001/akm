package cn.zsmy.akm.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/12/23.
 */
public class Recharge {

    /**
     * code : SUCC
     * message : 返回充值活动信息成功
     * data : [{"id":"9e55993e-a466-4322-9368-3ecb52f3d8bb","title":"充值50元","pubTime":null,"endTime":null,"startTime":null,"logo":null,"content":"赠送30元"},{"id":"f0a873fb-dbc7-4572-8077-82e617a2d10f","title":"充值100元","pubTime":null,"endTime":null,"startTime":null,"logo":null,"content":"赠送100元"},{"id":"f2ecf1f0-c03e-4c27-b8fe-d21e85b3d450","title":"充值30元","pubTime":null,"endTime":null,"startTime":null,"logo":null,"content":"赠送10元"}]
     */

    private String code;
    private String message;
    /**
     * id : 9e55993e-a466-4322-9368-3ecb52f3d8bb
     * title : 充值50元
     * pubTime : null
     * endTime : null
     * startTime : null
     * logo : null
     * content : 赠送30元
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
        private String title;
        private Object pubTime;
        private Object endTime;
        private Object startTime;
        private Object logo;
        private String content;

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPubTime(Object pubTime) {
            this.pubTime = pubTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public Object getPubTime() {
            return pubTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public Object getStartTime() {
            return startTime;
        }

        public Object getLogo() {
            return logo;
        }

        public String getContent() {
            return content;
        }
    }
}
