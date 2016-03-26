package cn.zsmy.akm.doctor.admissions.bean;

/**
 * Created by Administrator on 2016/1/26.
 */
public class DrugSuggest {
    private String drugId;
    private String drugName;
    private int issueNumber;//开具数量
    private int usageAmount;
    private String usageMethod;

    public DrugSuggest(String drugId, String drugName, int issueNumber, int usageAmount, String usageMethod) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.issueNumber = issueNumber;
        this.usageAmount = usageAmount;
        this.usageMethod = usageMethod;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public int getUsageAmount() {
        return usageAmount;
    }

    public void setUsageAmount(int usageAmount) {
        this.usageAmount = usageAmount;
    }

    public String getUsageMethod() {
        return usageMethod;
    }

    public void setUsageMethod(String usageMethod) {
        this.usageMethod = usageMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrugSuggest that = (DrugSuggest) o;

        if (!drugId.equals(that.drugId)) return false;
        return drugId.equals(that.drugId);

    }

    @Override
    public int hashCode() {
        int result = drugId.hashCode();
        result = 31 * result + drugName.hashCode();
        return result;
    }
}
