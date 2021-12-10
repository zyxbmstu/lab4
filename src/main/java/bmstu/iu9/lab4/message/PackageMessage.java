package bmstu.iu9.lab4.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PackageMessage {

    public PackageMessage(@JsonProperty() int packageId,
                          @JsonProperty() ArrayList<Test> tests) {
        this.packageId = packageId;
        this.tests = tests;
    }

}
