package cn.zsmy.akm.doctor.admissions.bean;

import java.util.List;

/**
 * 病症用药列表
 * Created by Administrator on 2016/1/17.
 */
public class Disease {
    /**
     * code : SUCC
     * message : 数据返回成功
     * data : [{"id":"ed2c3a1c-1322-447b-b40f-ee28e2ae0231","alias":"痤疮","aliasEn":"ZC","noxa":"长时间坐着导致","noxaShort":"长座","faqSymptom":"屁股上有疙瘩","name":"痤疮"}]
     */

    private String code;
    private String message;
    /**
     * id : ed2c3a1c-1322-447b-b40f-ee28e2ae0231
     * alias : 痤疮
     * aliasEn : ZC
     * noxa : 长时间坐着导致
     * noxaShort : 长座
     * faqSymptom : 屁股上有疙瘩
     * name : 痤疮
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
        private String alias;
        private String aliasEn;
        private String noxa;
        private String noxaShort;
        private String faqSymptom;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public void setAliasEn(String aliasEn) {
            this.aliasEn = aliasEn;
        }

        public void setNoxa(String noxa) {
            this.noxa = noxa;
        }

        public void setNoxaShort(String noxaShort) {
            this.noxaShort = noxaShort;
        }

        public void setFaqSymptom(String faqSymptom) {
            this.faqSymptom = faqSymptom;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getAlias() {
            return alias;
        }

        public String getAliasEn() {
            return aliasEn;
        }

        public String getNoxa() {
            return noxa;
        }

        public String getNoxaShort() {
            return noxaShort;
        }

        public String getFaqSymptom() {
            return faqSymptom;
        }

        public String getName() {
            return name;
        }
    }
}
