package bmstu.iu9.lab4.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class StorageMessage {

    private int packageId;
    private ArrayList<Test> tests;

    private final String PACKAGE_ID = "packageId";
    private final String TESTS = "test";

    public StorageMessage(@JsonProperty(PACKAGE_ID) int packageId,
                          @JsonProperty(TESTS)ArrayList<Test> tests) {
        this.packageId = packageId;
        this.tests = tests;
    }

    public int getPackageId() {
        return packageId;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

}
