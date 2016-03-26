package cn.zsmy.akm.doctor.profile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sanz on 2016/1/12 20:20
 */
public class JobTitle implements Serializable{

    /**
     * code : SUCC
     * message : 返回数据成功
     * data : [{"id":"00df858b-aa2e-4748-afcc-47292c8ea400","code":"archiater","name":"主任医师"},{"id":"077b95b0-ebe6-4801-95c1-b4c312b3369a","code":"visitingStaff","name":"主治医师"},{"id":"3166f3a2-30c1-438c-b9b2-52e939c682bd","code":"associateDoctor","name":"副主任医师"}]
     */

    private String code;
    private String message;
    /**
     * id : 00df858b-aa2e-4748-afcc-47292c8ea400
     * code : archiater
     * name : 主任医师
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
        private String code;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}
