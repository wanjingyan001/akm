package cn.zsmy.akm.entity;

/**
 * 上传图片
 * Created by Administrator on 2015/12/31.
 */
public class LoadImage {
    /**
     * code : SUCC
     * message : 图片保存成功
     * data : {"id":"500447aa-5a07-4275-9086-1458f035dcb8"}
     */

    private String code;
    private String message;
    /**
     * id : 500447aa-5a07-4275-9086-1458f035dcb8
     */

    private DataEntity data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String id;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}
