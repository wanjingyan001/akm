package cn.zsmy.akm.doctor.profile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sanz on 2016/1/13 16:17
 */
public class OfficeName implements Serializable {

    /**
     * code : SUCC
     * message : 成功返回9个科室
     * data : [{"id":"55b72c44-2a59-482f-bf5a-33f459556f28","parentId":"e73589d5-ec16-4167-ba00-bd9ab2e6682f","code":"respiration","name":"呼吸内科","creator":null,"createTime":1450776249000,"modifier":null,"modifyTime":1450776249000,"status":null,"flag":"0","delFlag":"0"},{"id":"7978df44-9fe6-421b-aba0-f6beac258246","parentId":"e73589d5-ec16-4167-ba00-bd9ab2e6682f","code":"cardiovascular","name":"心血管内科","creator":null,"createTime":1450776249000,"modifier":null,"modifyTime":1450776249000,"status":null,"flag":"0","delFlag":"0"},{"id":"7c8d9bbb-3dd2-4dc5-9eb5-2cfd9e53d0b9","parentId":"e73589d5-ec16-4167-ba00-bd9ab2e6682f","code":"digestion","name":"消化内科","creator":null,"createTime":1450776249000,"modifier":null,"modifyTime":1450776249000,"status":null,"flag":"0","delFlag":"0"},{"id":"d432ecc3-1848-4b12-8f99-41b23dbead8e","parentId":"e73589d5-ec16-4167-ba00-bd9ab2e6682f","code":"nervous","name":"神经内科","creator":null,"createTime":1450776249000,"modifier":null,"modifyTime":1450776249000,"status":null,"flag":"0","delFlag":"0"},{"id":"e73589d5-ec16-4167-ba00-bd9ab2e6682f","parentId":null,"code":"internal","name":"内科","creator":null,"createTime":1450776249000,"modifier":null,"modifyTime":1450776249000,"status":null,"flag":"0","delFlag":"0"},{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647d","parentId":null,"code":"10010","name":"皮肤科","creator":"","createTime":1449645152000,"modifier":null,"modifyTime":null,"status":null,"flag":"0","delFlag":"0"},{"id":"bca3ac1c-fd57-4eab-8b7f-7a24c9eaf06f","parentId":null,"code":"erke","name":"儿科","creator":null,"createTime":null,"modifier":null,"modifyTime":null,"status":null,"flag":null,"delFlag":"0"},{"id":"bf9b5ad8-bb5d-4e39-be35-524976e8ab22","parentId":null,"code":"pifuke","name":"皮肤科","creator":null,"createTime":null,"modifier":null,"modifyTime":null,"status":null,"flag":null,"delFlag":"0"},{"id":"c5b1e31f-44d2-4ee8-988b-cdc7372a3599","parentId":null,"code":"weike","name":"外科科","creator":null,"createTime":null,"modifier":null,"modifyTime":null,"status":null,"flag":null,"delFlag":"0"}]
     */

    private String code;
    private String message;
    /**
     * id : 55b72c44-2a59-482f-bf5a-33f459556f28
     * parentId : e73589d5-ec16-4167-ba00-bd9ab2e6682f
     * code : respiration
     * name : 呼吸内科
     * creator : null
     * createTime : 1450776249000
     * modifier : null
     * modifyTime : 1450776249000
     * status : null
     * flag : 0
     * delFlag : 0
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

    public static class DataEntity implements Serializable{
        private String id;
        private String parentId;
        private String code;
        private String name;
        private Object creator;
        private long createTime;
        private Object modifier;
        private long modifyTime;
        private Object status;
        private String flag;
        private String delFlag;

        public void setId(String id) {
            this.id = id;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCreator(Object creator) {
            this.creator = creator;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setModifier(Object modifier) {
            this.modifier = modifier;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getId() {
            return id;
        }

        public String getParentId() {
            return parentId;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Object getCreator() {
            return creator;
        }

        public long getCreateTime() {
            return createTime;
        }

        public Object getModifier() {
            return modifier;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public Object getStatus() {
            return status;
        }

        public String getFlag() {
            return flag;
        }

        public String getDelFlag() {
            return delFlag;
        }
    }
}
