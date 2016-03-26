package cn.zsmy.akm.doctor.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/12/23.
 */
public class Hosptials {
    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"id":"d49e0f90-471d-4de3-89c8-03e7c4760548","name":"复旦大学附属医院"},{"id":"bc8f7733-b526-11e5-a0de-4439c4558c0b","name":"复旦大学附属儿科医院"},{"id":"bc8f7c28-b526-11e5-a0de-4439c4558c0b","name":"复旦大学附属妇产科医院（红房子）"},{"id":"bc8f7e93-b526-11e5-a0de-4439c4558c0b","name":"复旦大学附属华山医院"},{"id":"bc8f8343-b526-11e5-a0de-4439c4558c0b","name":"复旦大学附属金山医院"},{"id":"bc8f8566-b526-11e5-a0de-4439c4558c0b","name":"复旦大学附属眼耳鼻喉科医院"},{"id":"bc8f872d-b526-11e5-a0de-4439c4558c0b","name":"复旦大学附属中山医院"},{"id":"bc8f88f0-b526-11e5-a0de-4439c4558c0b","name":"复旦大学附属中山医院青浦分院"},{"id":"bc8f8af1-b526-11e5-a0de-4439c4558c0b","name":"复旦大学附属肿瘤医院"},{"id":"bc8f8cbd-b526-11e5-a0de-4439c4558c0b","name":"华东医院"},{"id":"bc8f8e77-b526-11e5-a0de-4439c4558c0b","name":"民航上海医院"},{"id":"bc8f9049-b526-11e5-a0de-4439c4558c0b","name":"上海电力医院"},{"id":"bc8f924d-b526-11e5-a0de-4439c4558c0b","name":"上海奉贤区古华医院"},{"id":"bc8f93e2-b526-11e5-a0de-4439c4558c0b","name":"上海海员医院"},{"id":"bc8f96ba-b526-11e5-a0de-4439c4558c0b","name":"上海航道医院"},{"id":"bc8f97cb-b526-11e5-a0de-4439c4558c0b","name":"上海衡山虹妇幼医院"},{"id":"bc8f9a65-b526-11e5-a0de-4439c4558c0b","name":"上海沪东造船集团职工医院"},{"id":"bc8f9b96-b526-11e5-a0de-4439c4558c0b","name":"上海建工医院"},{"id":"bc8f9e07-b526-11e5-a0de-4439c4558c0b","name":"上海江南造船集团职工医院"},{"id":"bc8f9f38-b526-11e5-a0de-4439c4558c0b","name":"上海交通大学医学院附属第九人民医院"}]
     */

    private String code;
    private String message;
    /**
     * id : d49e0f90-471d-4de3-89c8-03e7c4760548
     * name : 复旦大学附属医院
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
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
