package bmstu.iu9.lab4.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {

    private final String TEST_NAME = "testName";
    private final String PARAMET = "testName";
    private final String TEST_NAME = "testName";

    @JsonProperty(TEST_NAME)
    private String testName;


    public Test(@JsonProperty(TEST_NAME) String testNAme,
                )

}
