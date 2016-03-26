package cn.zsmy.akm.side.bean;

import java.util.List;

/**
 * 药品
 * Created by Administrator on 2015/12/29.
 */
public class Drugs {

    /**
     * code : SUCC
     * message : 返回数据成功
     * data : [{"id":"6fc6c81f-2352-4e49-a447-9c9f784fc69e","name":"复方聚维酮碘搽剂","commonName":"通用名称10","composition":"聚维酮碘","property":"性状10","cureMainly":"功能主治10","standard":"规格1","dosage":"1","untowardEffect":"昏睡","taboo":"多睡觉","heedItems":"注意事项1","store":null,"number":null,"authenticationCode":"批准文号1","productNumber":"产品批次1","productDate":1450195200000,"productAddress":null,"postCode":"322004","telephone":"021-125544788","webSite":"222.163.com","faxes":"021-125544788","validityDate":1450195200000,"superviseNum":"21447700","zUsage":"口服","pics":null,"icon":null},{"id":"950fa4fd-ca22-11e5-96b9-4439c4558c0b","name":"阿莫西林胶囊","commonName":"广林,羟氨苄胶囊,羟氨苄青霉素胶囊","composition":"适用于敏感菌(不产β内酰胺酶菌株)所大肠下致染：感列的埃希菌、奇异变形杆菌或粪肠球菌所致的泌尿生殖道感染,溶血链球菌、葡萄球菌或大肠埃希菌所致的皮肤软组织感染等等。","property":null,"cureMainly":null,"standard":"0.25g*50粒","dosage":null,"untowardEffect":null,"taboo":null,"heedItems":"1.青霉素类口服药可引起过敏性休克，有多见于青霉素或头孢菌素过敏史的患者。用药前必须详细询问药物过敏史并做青霉素皮肤试验。如发生过敏性休克，应就地抢救，予以保持气道畅通、吸氧及应用肾上腺素、糖皮质激素等治疗措施。2.传染性单核细胞增多症患者应用本品易发生皮疹，应避免使用。3.疗程较长患者应检查肝、肾功能和血常规。4.阿莫西林可导致采用Benedict或Fehling试剂的尿糖试验出现假阳性。5.下列情况应慎用：(1)有哮喘、枯草热等过敏性疾病史者。(2)老年人和肾功能严重损害时可能须调整剂量。","store":null,"number":null,"authenticationCode":"H53020867","productNumber":null,"productDate":0,"productAddress":null,"postCode":null,"telephone":null,"webSite":null,"faxes":null,"validityDate":0,"superviseNum":null,"zUsage":null,"pics":null,"icon":null}]
     */

    private String code;
    private String message;
    /**
     * id : 6fc6c81f-2352-4e49-a447-9c9f784fc69e
     * name : 复方聚维酮碘搽剂
     * commonName : 通用名称10
     * composition : 聚维酮碘
     * property : 性状10
     * cureMainly : 功能主治10
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
