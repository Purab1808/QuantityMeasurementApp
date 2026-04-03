package org.apps.quantitymeasurement.unit;

import java.util.function.Function;

public enum TemperatureUnitUC16 implements IMeasurableUC16 {

    CELSIUS(false),
    FAHRENHEIT(true);

    Function<Double, Double> conversion;

    SupportsArithmeticUC16 supportsArithmetic = () -> false;

    // ✅ FIXED constructor
    TemperatureUnitUC16(boolean f) {

        if (f)
            conversion = x -> (x - 32) * 5 / 9;
        else
            conversion = x -> x;
    }

    public String getUnitName() {
        return name();
    }

    public double getConversionFactor() {
        return 1;
    }

    public double convertToBaseUnit(double value) {
        return conversion.apply(value);
    }

    public double convertFromBaseUnit(double baseValue) {

        if (this == CELSIUS)
            return baseValue;

        return baseValue * 9 / 5 + 32;
    }

    public double convertTo(double value, TemperatureUnitUC16 target) {

        double base = convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String op) {

        if (!supportsArithmetic.isSupported()) {
            throw new UnsupportedOperationException(
                    name() + " does not support " + op);
        }
    }
}