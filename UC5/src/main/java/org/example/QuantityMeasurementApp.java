package org.example;
import java.util.Objects;

public class QuantityMeasurementApp {
    static class Length {
        private double value;
        private LengthUnit unit;

        public enum LengthUnit {
            FEET(12.0),
            INCH(1.0),
            YARD(36.0),
            CM(0.393701);

            private final double toInch;

            LengthUnit(double toInch) {
                this.toInch = toInch;
            }

            double toBase(double value) {
                return value * toInch;
            }
        }

        public Length(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value))
                throw new IllegalArgumentException("Invalid input");
            this.value = value;
            this.unit = unit;
        }

        double baseUnit() {
            return unit.toBase(value);
        }

        public Length convertTo(LengthUnit target) {
            if (target == null) throw new IllegalArgumentException("Invalid target");
            double base = baseUnit();
            double converted = base / target.toBase(1.0);
            return new Length(converted, target);
        }

        public static double convert(double value, LengthUnit source, LengthUnit target) {
            if (source == null || target == null || !Double.isFinite(value))
                throw new IllegalArgumentException("Invalid input");
            double base = source.toBase(value);
            return base / target.toBase(1.0);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            Length l = (Length) obj;
            return Double.compare(this.baseUnit(), l.baseUnit()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(baseUnit());
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }
}