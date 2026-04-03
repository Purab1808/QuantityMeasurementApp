package Com.apps.QuantityMeasurementApp.UseCase15.common;

public interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    double getConversionFactor();
    String getUnitName();

    default boolean supportsArithmetic() {
        return true;
    }
    default void validateOperationSupport(String operation) {
        if (!supportsArithmetic()) {
            throw new UnsupportedOperationException("Operation " + operation + " not supported");
        }
    }
}