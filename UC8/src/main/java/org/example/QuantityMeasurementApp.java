package org.example;
import java.util.Objects;

enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;
    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }
    public double getConversionFactor() {
        return toFeetFactor;
    }
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }
}
public class QuantityMeasurementApp {
    public static class Length {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public Length(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            if (Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        public Length convertTo(LengthUnit targetUnit) {
            double base = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(base);
            return new Length(round(converted), targetUnit);
        }

        public Length add(Length other, LengthUnit targetUnit) {
            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);
            double sum = base1 + base2;
            double result = targetUnit.convertFromBaseUnit(sum);
            return new Length(round(result), targetUnit);
        }

        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        private double round(double val) {
            return Math.round(val * 100.0) / 100.0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Length)) return false;
            Length length = (Length) o;
            return Math.abs(this.toBase() - length.toBase()) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Objects.hash(round(toBase()));
        }
    }
}