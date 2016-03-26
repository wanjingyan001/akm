package cn.zsmy.akm.doctor.profile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sanz on 2016/1/13 15:52
 */
public class HospitalName implements Serializable{

    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"id":"13933968-1b07-430b-8ece-4d94aabf6439","name":"上海市第七人民医院"}]
     */

    private String code;
    private String message;
    /**
     * id : 13933968-1b07-430b-8ece-4d94aabf6439
     * name : 上海市第七人民医院
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

    public static class DataEntity  implements Serializable{
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
