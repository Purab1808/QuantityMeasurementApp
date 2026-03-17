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
            if (unit == null || Double.isNaN(value))
                throw new IllegalArgumentException("Invalid input");
            this.value = value;
            this.unit = unit;
        }

        double baseUnit() {
            return unit.toBase(value);
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
    }

    public static void main(String[] args) {
        Length q1 = new Length(1.0, Length.LengthUnit.YARD);
        Length q2 = new Length(3.0, Length.LengthUnit.FEET);
        System.out.println(q1.equals(q2));

        Length q3 = new Length(1.0, Length.LengthUnit.CM);
        Length q4 = new Length(0.393701, Length.LengthUnit.INCH);
        System.out.println(q3.equals(q4));
    }
}