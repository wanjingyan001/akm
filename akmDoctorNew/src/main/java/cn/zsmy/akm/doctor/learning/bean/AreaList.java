package cn.zsmy.akm.doctor.learning.bean;

import java.util.List;

/**
 * Created by sanz on 2016/1/26 15:57
 */
public class AreaList {

    /**
     * code : SUCC
     * message : 返回板块列表成功
     * data : [{"id":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"活动","status":null,"flag":"1","latestTopicId":null,"latestTopicTitle":null,"topicQty":null},{"id":"f9b161f1-9697-431c-911f-06f295c3d6ab","title":"皮肤科","status":null,"flag":"1","latestTopicId":null,"latestTopicTitle":null,"topicQty":null},{"id":"f9b161f1-9697-431c-911f-06f295c3d6af","title":"学术交流","status":null,"flag":"1","latestTopicId":null,"latestTopicTitle":"2016-1-22","topicQty":1},{"id":"f9b161f1-9697-431c-911f-06f295c3d6ds","title":"政策法规","status":null,"flag":"1","latestTopicId":null,"latestTopicTitle":"胡pull是","topicQty":1}]
     */

    private String code;
    private String message;
    /**
     * id : 19184f10-b4ea-11e5-a0de-4439c4558c0b
     * title : 活动
     * status : null
     * flag : 1
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
        private Object latestTopicTitle;
        private Object topicQty;

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

        public void setLatestTopicTitle(Object latestTopicTitle) {
            this.latestTopicTitle = latestTopicTitle;
        }

        public void setTopicQty(Object topicQty) {
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

        public Object getLatestTopicTitle() {
            return latestTopicTitle;
        }

        public Object getTopicQty() {
            return topicQty;
        }
    }
}
