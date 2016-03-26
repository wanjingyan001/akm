package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 医院搜索
 * Created by wanjingyan
 * on 2015/12/18 16:39.
 */
public class SearchHospital {

    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"name":"解放军301医院"},{"name":"海军411医院"}]
     */

    private String code;
    private String message;
    /**
     * name : 解放军301医院
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
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
