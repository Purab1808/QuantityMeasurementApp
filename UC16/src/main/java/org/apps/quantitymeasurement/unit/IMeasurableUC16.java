package org.apps.quantitymeasurement.unit;

public interface IMeasurableUC16 {

    // functional interface for arithmetic support
    SupportsArithmeticUC16 supportsArithmetic = () -> true;

    String getUnitName();

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
        // default empty (override if needed)
    }
}