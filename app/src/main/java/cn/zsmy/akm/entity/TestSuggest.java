package cn.zsmy.akm.entity;

/**
 * Created by Administrator on 2016/1/26.
 */
public class TestSuggest {
    private String testName;
    private String testId;

    public TestSuggest(String testId, String testName) {
        this.testId = testId;
        this.testName = testName;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
