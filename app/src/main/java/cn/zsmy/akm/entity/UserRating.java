package cn.zsmy.akm.entity;

/**
 * 用户评价(医生详情)
 * Created by wanjingyan
 * on 2015/12/7 15:44.
 */
public class UserRating {
    private String userPhone;//用户号码
    private int evaluationGrade;//评论星级
    private String evaluationContent;//评论内容

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(int evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }
}
