package bmstu.iu9.lab4.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Test {

    private final String TEST_NAME = "testName";
    private final String PARAMS = "params";
    private final String RESULT = "result";
    private final String EXPECTED_RESULT = "expectedResult";

    @JsonProperty(TEST_NAME)
    private String testName;

    @JsonProperty(PARAMS)
    private ArrayList<Integer> params;

    @JsonProperty(RESULT)
    private boolean result;

    @JsonProperty(EXPECTED_RESULT)
    private String expectedResult;


    public Test(@JsonProperty(TEST_NAME) String testName,
                @JsonProperty(EXPECTED_RESULT) String expectedResult,
                @JsonProperty(PARAMS) ArrayList<Integer> params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
        this.result = false;
    }

    public Test(String testName,
                String expectedResult,
                ArrayList<Integer> params,
                boolean result) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
        this.result = result;
    }

    public ArrayList<Integer> getParams() {
        return params;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    @Override
    public String toString() {
        return "testName = " + testName + " result = " + result;
    }
}
