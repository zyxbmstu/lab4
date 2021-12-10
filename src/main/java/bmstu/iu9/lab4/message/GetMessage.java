package bmstu.iu9.lab4.message;

public class GetMessage {

    private int packageId;

    public GetMessage(int packageId) {
        this.packageId = packageId;
    }

    public int getPackageId() {
        return packageId;
    }

}
