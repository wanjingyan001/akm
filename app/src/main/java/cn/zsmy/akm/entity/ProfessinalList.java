package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 职称列表
 * Created by wanjingyan
 * on 2015/12/18 16:35.
 */
public class ProfessinalList {


    /**
     * code : SUCC
     * message : 消息返回成功
     * data : [{"professionalTitle":"中级职称主治"}]
     */

    private String code;
    private String message;
    /**
     * professionalTitle : 中级职称主治
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
        private String professionalTitle;

        public void setProfessionalTitle(String professionalTitle) {
            this.professionalTitle = professionalTitle;
        }

        public String getProfessionalTitle() {
            return professionalTitle;
        }
    }
}
