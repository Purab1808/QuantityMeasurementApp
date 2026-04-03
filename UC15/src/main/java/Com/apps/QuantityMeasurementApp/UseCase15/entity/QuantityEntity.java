package Com.apps.QuantityMeasurementApp.UseCase15.entity;

public class QuantityEntity {

    private final String operation;
    private final String result;
    private final boolean error;

    public QuantityEntity(String op, String res) {
        this.operation = op;
        this.result = res;
        this.error = false;
    }

    public QuantityEntity(String errorMsg) {
        this.operation = null;
        this.result = errorMsg;
        this.error = true;
    }

    public boolean hasError() { return error; }

    public String toString() {
        return error ? "Error: " + result : operation + " → " + result;
    }
}