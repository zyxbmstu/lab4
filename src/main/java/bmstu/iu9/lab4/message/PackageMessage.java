package bmstu.iu9.lab4.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PackageMessage {

    private final String PACKAGE_ID = "packageId";
    private final String SCRIPT = "script";
    private final String FUNCTION_NAME = "functionName";
    private final String TESTS = "tests";

    private int packageId;
    private String script;
    private String functionName;
    private ArrayList<Test> tests;

    public PackageMessage(@JsonProperty(PACKAGE_ID) int packageId,
                          @JsonProperty(SCRIPT) String script,
                          @JsonProperty(FUNCTION_NAME) String functionName,
                          @JsonProperty(TESTS) ArrayList<Test> tests) {
        this.packageId = packageId;
        this.tests = tests;
        this.functionName = functionName;
        this.script = script;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getScript() {
        return script;
    }

    public String getFunctionName() {
        return getFunctionName();
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

}
