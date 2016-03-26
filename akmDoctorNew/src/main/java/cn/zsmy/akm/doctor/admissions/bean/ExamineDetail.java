package cn.zsmy.akm.doctor.admissions.bean;

/**
 * Created by Administrator on 2016/2/15.
 */
public class ExamineDetail {

    /**
     * code : SUCC
     * message : 消息返回成功
     * data : {"id":"562ef123-31fc-43ea-ad0a-f5a5c843c5fc","caseId":"6f65abc3-0d3d-477c-9b75-9eecd10469e8","inspectId":"f2bca96d-6ea8-4a1e-a749-eeca79b828fa","inspectName":"常规检查项目","code":"urineinspection","name":"彩超","heedItem":"把上衣脱掉"}
     */

    private String code;
    private String message;
    /**
     * id : 562ef123-31fc-43ea-ad0a-f5a5c843c5fc
     * caseId : 6f65abc3-0d3d-477c-9b75-9eecd10469e8
     * inspectId : f2bca96d-6ea8-4a1e-a749-eeca79b828fa
     * inspectName : 常规检查项目
     * code : urineinspection
     * name : 彩超
     * heedItem : 把上衣脱掉
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
        private String caseId;
        private String inspectId;
        private String inspectName;
        private String code;
        private String name;
        private String heedItem;

        public void setId(String id) {
            this.id = id;
        }

        public void setCaseId(String caseId) {
            this.caseId = caseId;
        }

        public void setInspectId(String inspectId) {
            this.inspectId = inspectId;
        }

        public void setInspectName(String inspectName) {
            this.inspectName = inspectName;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setHeedItem(String heedItem) {
            this.heedItem = heedItem;
        }

        public String getId() {
            return id;
        }

        public String getCaseId() {
            return caseId;
        }

        public String getInspectId() {
            return inspectId;
        }

        public String getInspectName() {
            return inspectName;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getHeedItem() {
            return heedItem;
        }
    }
}
