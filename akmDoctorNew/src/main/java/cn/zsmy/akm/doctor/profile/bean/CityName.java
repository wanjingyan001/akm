package cn.zsmy.akm.doctor.profile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sanz on 2016/1/12 17:46
 */
public class CityName implements Serializable{

    /**
     * code : SUCC
     * message : 返回城市信息成功
     * data : [{"id":"0b75db16-ab7c-11e5-a0de-4439c4558c0b","name":"北京市","code":"010","level":2,"parentId":"085d3a18-ab7c-11e5-a0de-4439c4558c0b"}]
     */

    private String code;
    private String message;
    /**
     * id : 0b75db16-ab7c-11e5-a0de-4439c4558c0b
     * name : 北京市
     * code : 010
     * level : 2
     * parentId : 085d3a18-ab7c-11e5-a0de-4439c4558c0b
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

    public  class DataEntity implements Serializable{
        private String id;
        private String name;
        private String code;
        private int level;
        private String parentId;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public int getLevel() {
            return level;
        }

        public String getParentId() {
            return parentId;
        }
    }
}
