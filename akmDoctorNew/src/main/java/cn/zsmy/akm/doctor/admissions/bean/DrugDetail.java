package cn.zsmy.akm.doctor.admissions.bean;

/**
 * 药品详情
 * Created by Administrator on 2015/12/29.
 */
public class DrugDetail {

    /**
     * code : SUCC
     * message : 返回消息成功
     * data : {"id":"194ffebd-5c54-4bfa-a1a1-74616fc7c3b0","name":"﻿复方苯甲酸酊","commonName":"通用名称1","composition":"苯甲酸酊","property":"性状1","cureMainly":"功能主治1","standard":"规格1","dosage":"1","untowardEffect":"昏睡","taboo":"多睡觉","heedItems":"注意事项1","store":null,"number":null,"authenticationCode":"批准文号1","productNumber":"产品批次1","productDate":1450195200000,"productAddress":null,"postCode":"322004","telephone":"021-125544788","webSite":"222.163.com","faxes":"021-125544788","validityDate":1450195200000,"superviseNum":"21447700","zUsage":"口服","pics":null,"icon":null}
     */

    private String code;
    private String message;
    /**
     * id : 194ffebd-5c54-4bfa-a1a1-74616fc7c3b0
     * name : ﻿复方苯甲酸酊
     * commonName : 通用名称1
     * composition : 苯甲酸酊
     * property : 性状1
     * cureMainly : 功能主治1
     * standard : 规格1
     * dosage : 1
     * untowardEffect : 昏睡
     * taboo : 多睡觉
     * heedItems : 注意事项1
     * store : null
     * number : null
     * authenticationCode : 批准文号1
     * productNumber : 产品批次1
     * productDate : 1450195200000
     * productAddress : null
     * postCode : 322004
     * telephone : 021-125544788
     * webSite : 222.163.com
     * faxes : 021-125544788
     * validityDate : 1450195200000
     * superviseNum : 21447700
     * zUsage : 口服
     * pics : null
     * icon : null
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
        private String name;
        private String commonName;
        private String composition;
        private String property;
        private String cureMainly;
        private String standard;
        private String dosage;
        private String untowardEffect;
        private String taboo;
        private String heedItems;
        private Object store;
        private Object number;
        private String authenticationCode;
        private String productNumber;
        private long productDate;
        private Object productAddress;
        private String postCode;
        private String telephone;
        private String webSite;
        private String faxes;
        private long validityDate;
        private String superviseNum;
        private String zUsage;
        private Object pics;
        private Object icon;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCommonName(String commonName) {
            this.commonName = commonName;
        }

        public void setComposition(String composition) {
            this.composition = composition;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public void setCureMainly(String cureMainly) {
            this.cureMainly = cureMainly;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        public void setUntowardEffect(String untowardEffect) {
            this.untowardEffect = untowardEffect;
        }

        public void setTaboo(String taboo) {
            this.taboo = taboo;
        }

        public void setHeedItems(String heedItems) {
            this.heedItems = heedItems;
        }

        public void setStore(Object store) {
            this.store = store;
        }

        public void setNumber(Object number) {
            this.number = number;
        }

        public void setAuthenticationCode(String authenticationCode) {
            this.authenticationCode = authenticationCode;
        }

        public void setProductNumber(String productNumber) {
            this.productNumber = productNumber;
        }

        public void setProductDate(long productDate) {
            this.productDate = productDate;
        }

        public void setProductAddress(Object productAddress) {
            this.productAddress = productAddress;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void setWebSite(String webSite) {
            this.webSite = webSite;
        }

        public void setFaxes(String faxes) {
            this.faxes = faxes;
        }

        public void setValidityDate(long validityDate) {
            this.validityDate = validityDate;
        }

        public void setSuperviseNum(String superviseNum) {
            this.superviseNum = superviseNum;
        }

        public void setZUsage(String zUsage) {
            this.zUsage = zUsage;
        }

        public void setPics(Object pics) {
            this.pics = pics;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCommonName() {
            return commonName;
        }

        public String getComposition() {
            return composition;
        }

        public String getProperty() {
            return property;
        }

        public String getCureMainly() {
            return cureMainly;
        }

        public String getStandard() {
            return standard;
        }

        public String getDosage() {
            return dosage;
        }

        public String getUntowardEffect() {
            return untowardEffect;
        }

        public String getTaboo() {
            return taboo;
        }

        public String getHeedItems() {
            return heedItems;
        }

        public Object getStore() {
            return store;
        }

        public Object getNumber() {
            return number;
        }

        public String getAuthenticationCode() {
            return authenticationCode;
        }

        public String getProductNumber() {
            return productNumber;
        }

        public long getProductDate() {
            return productDate;
        }

        public Object getProductAddress() {
            return productAddress;
        }

        public String getPostCode() {
            return postCode;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getWebSite() {
            return webSite;
        }

        public String getFaxes() {
            return faxes;
        }

        public long getValidityDate() {
            return validityDate;
        }

        public String getSuperviseNum() {
            return superviseNum;
        }

        public String getZUsage() {
            return zUsage;
        }

        public Object getPics() {
            return pics;
        }

        public Object getIcon() {
            return icon;
        }
    }
}
