package org.example;
import java.util.Objects;
enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CM(1.0 / 30.48);
    private final double conversionFactor;
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
    double toBase(double value) {
        return value * conversionFactor;
    }
    double fromBase(double baseValue) {
        return baseValue / conversionFactor;
    }
}
public class QuantityMeasurementApp {
    static class Length{
        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;
        public Length(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            this.value = value;
            this.unit = unit;
        }
        public double getValue() {
            return value;
        }
        public LengthUnit getUnit() {
            return unit;
        }
        private double toBaseUnit() {
            return unit.toBase(value);
        }
        private static double round(double val) {
            return Math.round(val * 100.0) / 100.0;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Length other = (Length) obj;
            return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
        }
        @Override
        public int hashCode() {
            return Objects.hash(round(toBaseUnit()));
        }
        public Length add(Length other) {
            if (other == null) throw new IllegalArgumentException("Length cannot be null");
            if (!Double.isFinite(other.value)) throw new IllegalArgumentException("Invalid value");
            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            double result = this.unit.fromBase(sumBase);
            return new Length(round(result), this.unit);
        }
        public static Length add(Length l1, Length l2) {
            return add(l1, l2, l1.unit);
        }

        public static Length add(Length l1, Length l2, LengthUnit targetUnit) {
            if (l1 == null || l2 == null) throw new IllegalArgumentException("Lengths cannot be null");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            if (!Double.isFinite(l1.value) || !Double.isFinite(l2.value)) throw new IllegalArgumentException("Invalid values");

            double sumBase = l1.toBaseUnit() + l2.toBaseUnit();
            double result = targetUnit.fromBase(sumBase);

            return new Length(round(result), targetUnit);
        }
    }
}