package Com.apps.QuantityMeasurementApp.UseCase15.units;
import Com.apps.QuantityMeasurementApp.UseCase15.common.IMeasurable;

public enum WeightUnit implements IMeasurable {
    GRAM(0.001),
    KILOGRAM(1.0),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double base) {
        return base / factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return name();
    }
}