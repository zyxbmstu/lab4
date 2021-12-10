package bmstu.iu9.lab4.message;

public class TestMessage {

    private int packageId;
    private String script;
    private String functionName;
    private Test test;

    TestMessage(int packageId, String script, String functionName, Test test) {
        this.packageId = packageId;
        this.script = script;
        this.functionName = functionName;
        this.test = test;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getScript() {
        return script;
    }

    public String getFunctionName() {
        return functionName;
    }

    public Test getTest() {
        return test;
    }

}
