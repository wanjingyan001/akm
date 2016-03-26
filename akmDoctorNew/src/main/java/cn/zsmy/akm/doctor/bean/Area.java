package cn.zsmy.akm.doctor.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/1/9.
 */
public class Area {

    /**
     * code : SUCC
     * message : 返回板块列表成功
     * data : [{"id":"030ce5f0-b4ea-11e5-a0de-4439c4558c0b","title":"活动社区"},{"id":"19184f10-b4ea-11e5-a0de-4439c4558c0b","title":"活动"},{"id":"433e5cd4-2ca8-4471-adeb-782146fdf923","title":"有问必答"},{"id":"5b01c211-0624-44d9-8904-7b21b1144db1","title":"皮肤美容"},{"id":"f9b161f1-9697-431c-911f-06f295c3d6aa","title":"软件建议"},{"id":"f9b161f1-9697-431c-911f-06f295c3d6ab","title":"皮肤科"},{"id":"f9b161f1-9697-431c-911f-06f295c3d6af","title":"学术交流"},{"id":"f9b161f1-9697-431c-911f-06f295c3d6ds","title":"政策法规"}]
     */

    private String code;
    private String message;
    /**
     * id : 030ce5f0-b4ea-11e5-a0de-4439c4558c0b
     * title : 活动社区
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

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }
}
