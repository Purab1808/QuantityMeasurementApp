package Com.apps.QuantityMeasurementApp.UseCase15.core;
import Com.apps.QuantityMeasurementApp.UseCase15.common.IMeasurable;

public class Quantity<T extends IMeasurable> {

    private final double value;
    private final T unit;

    public Quantity(double value, T unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException();

        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }

    public T getUnit() { return unit; }

    public Quantity<T> convertTo(T target) {
        double base = unit.convertToBaseUnit(value);
        double res = target.convertFromBaseUnit(base);
        return new Quantity<>(round(res), target);
    }

    public Quantity<T> add(Quantity<T> other) {
        unit.validateOperationSupport("add");

        double base = unit.convertToBaseUnit(value)
                + other.unit.convertToBaseUnit(other.value);

        double res = unit.convertFromBaseUnit(base);
        return new Quantity<>(round(res), unit);
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Quantity<?> other)) return false;
        if (unit.getClass() != other.unit.getClass()) return false;

        double b1 = unit.convertToBaseUnit(value);
        double b2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(b1 - b2) < 1e-4;
    }
}