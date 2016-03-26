package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 选择城市
 * Created by wanjingyan
 * on 2015/12/18 16:33.
 */
public class CityList {


    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"id":"c1a56d72-4a57-40d9-8250-c2bb5b3612a5","code":"10010","name":"上海市第六人民医院","status":null}]
     */

    private String code;
    private String message;
    /**
     * id : c1a56d72-4a57-40d9-8250-c2bb5b3612a5
     * code : 10010
     * name : 上海市第六人民医院
     * status : null
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
        private String name;
        private Object status;

        public void setId(String id) {
            this.id = id;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Object getStatus() {
            return status;
        }
    }
}
