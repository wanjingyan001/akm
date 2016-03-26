package cn.zsmy.akm.entity;

import java.util.List;

/**
 * 科室实体类
 * Created by wanjingyan
 * on 2015/12/9 10:46.
 */
public class Sections {

    /**
     * code : SUCC
     * message : 成功返回5个科室
     * data : [{"id":"b3df3d04-ca23-11e5-96b9-4439c4558c0b","parentId":null,"code":"10000","name":"皮肤性病科","creator":null,"createTime":0,"modifier":null,"modifyTime":0,"status":null,"flag":null,"delFlag":"0","subs":[{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647d","parentId":"b3df3d04-ca23-11e5-96b9-4439c4558c0b","code":"10010","name":"皮肤科","creator":"","createTime":1449645152000,"modifier":null,"modifyTime":0,"status":null,"flag":"0","delFlag":"0","subs":[]}]}]
     */

    private String code;
    private String message;
    /**
     * id : b3df3d04-ca23-11e5-96b9-4439c4558c0b
     * parentId : null
     * code : 10000
     * name : 皮肤性病科
     * creator : null
     * createTime : 0
     * modifier : null
     * modifyTime : 0
     * status : null
     * flag : null
     * delFlag : 0
     * subs : [{"id":"bae8edeb-d7f4-4c1d-883a-9ed3cbd6647d","parentId":"b3df3d04-ca23-11e5-96b9-4439c4558c0b","code":"10010","name":"皮肤科","creator":"","createTime":1449645152000,"modifier":null,"modifyTime":0,"status":null,"flag":"0","delFlag":"0","subs":[]}]
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
        private Object parentId;
        private String code;
        private String name;
        private Object creator;
        private Object createTime;
        private Object modifier;
        private Object modifyTime;
        private Object status;
        private Object flag;
        private String delFlag;
        /**
         * id : bae8edeb-d7f4-4c1d-883a-9ed3cbd6647d
         * parentId : b3df3d04-ca23-11e5-96b9-4439c4558c0b
         * code : 10010
         * name : 皮肤科
         * creator :
         * createTime : 1449645152000
         * modifier : null
         * modifyTime : 0
         * status : null
         * flag : 0
         * delFlag : 0
         * subs : []
         */

        private List<SubsEntity> subs;

        public void setId(String id) {
            this.id = id;
        }

        public void setParentId(Object parentId) {
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

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public void setModifier(Object modifier) {
            this.modifier = modifier;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public void setFlag(Object flag) {
            this.flag = flag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public void setSubs(List<SubsEntity> subs) {
            this.subs = subs;
        }

        public String getId() {
            return id;
        }

        public Object getParentId() {
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

        public Object getCreateTime() {
            return createTime;
        }

        public Object getModifier() {
            return modifier;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public Object getStatus() {
            return status;
        }

        public Object getFlag() {
            return flag;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public List<SubsEntity> getSubs() {
            return subs;
        }

        public static class SubsEntity {
            private String id;
            private String parentId;
            private String code;
            private String name;
            private String creator;
            private Object createTime;
            private Object modifier;
            private Object modifyTime;
            private Object status;
            private String flag;
            private String delFlag;
            private List<?> subs;

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

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public void setModifier(Object modifier) {
                this.modifier = modifier;
            }

            public void setModifyTime(Object modifyTime) {
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

            public void setSubs(List<?> subs) {
                this.subs = subs;
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

            public String getCreator() {
                return creator;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public Object getModifier() {
                return modifier;
            }

            public Object getModifyTime() {
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

            public List<?> getSubs() {
                return subs;
            }
        }
    }
}
