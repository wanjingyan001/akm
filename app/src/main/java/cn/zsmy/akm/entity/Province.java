package cn.zsmy.akm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/1/14.
 */
public class Province {

    /**
     * code : SUCC
     * message : 返回省份信息成功
     * data : [{"id":"085d3a18-ab7c-11e5-a0de-4439c4558c0b","name":"北京","code":"1","level":1,"parentId":null},{"id":"08652470-ab7c-11e5-a0de-4439c4558c0b","name":"广东","code":"19","level":1,"parentId":null},{"id":"0865253e-ab7c-11e5-a0de-4439c4558c0b","name":"广西","code":"20","level":1,"parentId":null},{"id":"0865260e-ab7c-11e5-a0de-4439c4558c0b","name":"海南","code":"21","level":1,"parentId":null},{"id":"086526e5-ab7c-11e5-a0de-4439c4558c0b","name":"重庆","code":"22","level":1,"parentId":null},{"id":"086527bb-ab7c-11e5-a0de-4439c4558c0b","name":"四川","code":"23","level":1,"parentId":null},{"id":"08652889-ab7c-11e5-a0de-4439c4558c0b","name":"贵州","code":"24","level":1,"parentId":null},{"id":"08652959-ab7c-11e5-a0de-4439c4558c0b","name":"云南","code":"25","level":1,"parentId":null},{"id":"08652a29-ab7c-11e5-a0de-4439c4558c0b","name":"西藏","code":"26","level":1,"parentId":null},{"id":"08652af4-ab7c-11e5-a0de-4439c4558c0b","name":"陕西","code":"27","level":1,"parentId":null},{"id":"08652bb7-ab7c-11e5-a0de-4439c4558c0b","name":"甘肃","code":"28","level":1,"parentId":null},{"id":"08652c8e-ab7c-11e5-a0de-4439c4558c0b","name":"青海","code":"29","level":1,"parentId":null},{"id":"08652d58-ab7c-11e5-a0de-4439c4558c0b","name":"宁夏","code":"30","level":1,"parentId":null},{"id":"08652e29-ab7c-11e5-a0de-4439c4558c0b","name":"新疆","code":"31","level":1,"parentId":null},{"id":"08652f02-ab7c-11e5-a0de-4439c4558c0b","name":"香港","code":"32","level":1,"parentId":null},{"id":"08652fc6-ab7c-11e5-a0de-4439c4558c0b","name":"澳门","code":"33","level":1,"parentId":null},{"id":"08652391-ab7c-11e5-a0de-4439c4558c0b","name":"湖南","code":"18","level":1,"parentId":null},{"id":"086522b4-ab7c-11e5-a0de-4439c4558c0b","name":"湖北","code":"17","level":1,"parentId":null},{"id":"085d3cb5-ab7c-11e5-a0de-4439c4558c0b","name":"天津","code":"2","level":1,"parentId":null},{"id":"085d3d7c-ab7c-11e5-a0de-4439c4558c0b","name":"河北","code":"3","level":1,"parentId":null},{"id":"085d3de5-ab7c-11e5-a0de-4439c4558c0b","name":"山西","code":"4","level":1,"parentId":null},{"id":"085d3e4c-ab7c-11e5-a0de-4439c4558c0b","name":"内蒙古","code":"5","level":1,"parentId":null},{"id":"085d3eb6-ab7c-11e5-a0de-4439c4558c0b","name":"辽宁","code":"6","level":1,"parentId":null},{"id":"085d3f19-ab7c-11e5-a0de-4439c4558c0b","name":"吉林","code":"7","level":1,"parentId":null},{"id":"085d3faf-ab7c-11e5-a0de-4439c4558c0b","name":"黑龙江","code":"8","level":1,"parentId":null},{"id":"085d4018-ab7c-11e5-a0de-4439c4558c0b","name":"上海","code":"9","level":1,"parentId":null},{"id":"085d4076-ab7c-11e5-a0de-4439c4558c0b","name":"江苏","code":"10","level":1,"parentId":null},{"id":"08651ba5-ab7c-11e5-a0de-4439c4558c0b","name":"浙江","code":"11","level":1,"parentId":null},{"id":"08651dfa-ab7c-11e5-a0de-4439c4558c0b","name":"安徽","code":"12","level":1,"parentId":null},{"id":"08651f2e-ab7c-11e5-a0de-4439c4558c0b","name":"福建","code":"13","level":1,"parentId":null},{"id":"0865201d-ab7c-11e5-a0de-4439c4558c0b","name":"江西","code":"14","level":1,"parentId":null},{"id":"086520f4-ab7c-11e5-a0de-4439c4558c0b","name":"山东","code":"15","level":1,"parentId":null},{"id":"086521d1-ab7c-11e5-a0de-4439c4558c0b","name":"河南","code":"16","level":1,"parentId":null},{"id":"086530a3-ab7c-11e5-a0de-4439c4558c0b","name":"台湾","code":"34","level":1,"parentId":null}]
     */

    private String code;
    private String message;
    /**
     * id : 085d3a18-ab7c-11e5-a0de-4439c4558c0b
     * name : 北京
     * code : 1
     * level : 1
     * parentId : null
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
        private String code;
        private int level;
        private Object parentId;

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

        public void setParentId(Object parentId) {
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

        public Object getParentId() {
            return parentId;
        }
    }
}
