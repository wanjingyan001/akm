package cn.zsmy.akm.doctor.bean;

/**
 * 版本信息
 * Created by Administrator on 2015/12/26.
 */
public class VersionCode {

    /**
     * code : SUCC
     * message : 获取版本信息成功
     * data : {"id":"f9b161f1-9697-431c-911f-06f295c3d6ad","code":"akm","name":"阿克曼皮肤医生","version":"4.0","downloadUrl":null,"ztype":null}
     */

    private String code;
    private String message;
    /**
     * id : f9b161f1-9697-431c-911f-06f295c3d6ad
     * code : akm
     * name : 阿克曼皮肤医生
     * version : 4.0
     * downloadUrl : null
     * ztype : null
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
        private String code;
        private String name;
        private String version;
        private Object downloadUrl;
        private Object ztype;

        public void setId(String id) {
            this.id = id;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public void setDownloadUrl(Object downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public void setZtype(Object ztype) {
            this.ztype = ztype;
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

        public String getVersion() {
            return version;
        }

        public Object getDownloadUrl() {
            return downloadUrl;
        }

        public Object getZtype() {
            return ztype;
        }
    }
}
