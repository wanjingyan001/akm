package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 检验项目搜索
 * Created by wanjingyan
 * on 2015/12/18 16:37.
 */
public class SearchInspection {

    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"inspectName":"常规检查项目","name":"血常规"}]
     */

    private String code;
    private String message;
    /**
     * inspectName : 常规检查项目
     * name : 血常规
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
        private String inspectName;
        private String name;

        public void setInspectName(String inspectName) {
            this.inspectName = inspectName;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInspectName() {
            return inspectName;
        }

        public String getName() {
            return name;
        }
    }
}
