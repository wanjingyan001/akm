package cn.zsmy.akm.doctor.learning.bean;

import java.util.List;

/**
 * Created by sanz on 2016/1/26 15:57
 */
public class CollectionArea {

    /**
     * code : SUCC
     * message : 返回数据成功
     * data : [{"id":"ad114d05-58e1-4962-abd1-b5ed26141b24","itemId":"f9b161f1-9697-431c-911f-06f295c3d6ds","name":"政策法规"},{"id":"fb38a6b8-dbd4-4ef8-87ba-8a1c6af8399d","itemId":"f9b161f1-9697-431c-911f-06f295c3d6af","name":"学术交流"},{"id":"2a8a9651-1c3e-4ac0-8e60-bef87fbacc54","itemId":"f9b161f1-9697-431c-911f-06f295c3d6ab","name":"皮肤科"},{"id":"ca403e3f-7ff8-483e-a809-34a7adce97d1","itemId":"f9b161f1-9697-431c-911f-06f295c3d6aa","name":"软件建议"},{"id":"e09487ee-9ef2-41b7-b05a-8ef709e103d6","itemId":"f9b161f1-9697-431c-911f-06f295c3d6kk","name":"五官科"}]
     */

    private String code;
    private String message;
    /**
     * id : ad114d05-58e1-4962-abd1-b5ed26141b24
     * itemId : f9b161f1-9697-431c-911f-06f295c3d6ds
     * name : 政策法规
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
        private String itemId;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getItemId() {
            return itemId;
        }

        public String getName() {
            return name;
        }
    }
}
