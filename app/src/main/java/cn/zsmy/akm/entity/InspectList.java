package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 检验项目列表
 * Created by wanjingyan
 * on 2015/12/18 16:30.
 */
public class InspectList {


    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"caseId":"f9b161f1-9697-431c-911f-06f295c3d6aa","inspectId":"29139df9-79f7-4b7b-8a3c-51b6a88934e3","inspectName":"常规检查项目","code":"bloodinspection","name":"血常规","heedItem":"空腹检查"}]
     */

    private String code;
    private String message;
    /**
     * caseId : f9b161f1-9697-431c-911f-06f295c3d6aa
     * inspectId : 29139df9-79f7-4b7b-8a3c-51b6a88934e3
     * inspectName : 常规检查项目
     * code : bloodinspection
     * name : 血常规
     * heedItem : 空腹检查
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
        private String caseId;
        private String inspectId;
        private String inspectName;
        private String code;
        private String name;
        private String heedItem;

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
