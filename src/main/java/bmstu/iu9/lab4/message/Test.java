package bmstu.iu9.lab4.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {

    private final String TEST_NAME = "testName";
    private final String PARAMS = "params";
    private final String RESULT = "result";
    private final String EXPECTED_RESULT = "expectedResult";

    @JsonProperty(TEST_NAME)
    private String testName;


    public Test(@JsonProperty(TEST_NAME) String testName,
                @JsonProperty(EXPECTED_RESULT) String expectedResult,
                @JsonProperty(TEST_NAME) String testName)

}
