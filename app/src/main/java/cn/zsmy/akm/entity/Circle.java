package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 圈子
 * Created by sanz on 2015/12/9 9:24
 */
public class Circle {


    /**
     * code : SUCC
     * message : 返回板块列表成功
     * data : [{"id":"030ce5f0-b4ea-11e5-a0de-4439c4558c0b","title":"活动社区","status":null,"flag":"2","latestTopicId":null,"latestTopicTitle":null,"topicQty":null},{"id":"433e5cd4-2ca8-4471-adeb-782146fdf923","title":"有问必答","status":null,"flag":"2","latestTopicId":null,"latestTopicTitle":"---感冒久治不愈---","topicQty":3},{"id":"5b01c211-0624-44d9-8904-7b21b1144db1","title":"皮肤美容","status":null,"flag":"2","latestTopicId":null,"latestTopicTitle":null,"topicQty":null},{"id":"f9b161f1-9697-431c-911f-06f295c3d6aa","title":"软件建议","status":null,"flag":"2","latestTopicId":null,"latestTopicTitle":null,"topicQty":null}]
     */

    private String code;
    private String message;
    /**
     * id : 030ce5f0-b4ea-11e5-a0de-4439c4558c0b
     * title : 活动社区
     * status : null
     * flag : 2
     * latestTopicId : null
     * latestTopicTitle : null
     * topicQty : null
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
        private Object status;
        private String flag;
        private Object latestTopicId;
        private String latestTopicTitle;
        private int topicQty;

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setLatestTopicId(Object latestTopicId) {
            this.latestTopicId = latestTopicId;
        }

        public void setLatestTopicTitle(String latestTopicTitle) {
            this.latestTopicTitle = latestTopicTitle;
        }

        public void setTopicQty(int topicQty) {
            this.topicQty = topicQty;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public Object getStatus() {
            return status;
        }

        public String getFlag() {
            return flag;
        }

        public Object getLatestTopicId() {
            return latestTopicId;
        }

        public String getLatestTopicTitle() {
            return latestTopicTitle;
        }

        public int getTopicQty() {
            return topicQty;
        }
    }
}