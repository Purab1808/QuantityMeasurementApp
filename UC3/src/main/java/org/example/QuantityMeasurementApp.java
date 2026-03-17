package org.example;

public class QuantityMeasurementApp {
    static class Length {
        private double value;
        private LengthUnit unit;
        public enum LengthUnit {
            FEET(12.0),
            INCH(1.0);
            private final double conversion;
            LengthUnit(double conversion) {
                this.conversion = conversion;
            }
            double getConversion() {
                return this.conversion;
            }
        }
        public Length(double value, LengthUnit unit) {
            if (unit == null || Double.isNaN(value))
                throw new IllegalArgumentException("Invalid input");
            this.value = value;
            this.unit = unit;
        }
        double baseUnit() {
            return this.value * this.unit.getConversion();
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            Length l = (Length) obj;
            return Double.compare(this.baseUnit(), l.baseUnit()) == 0;
        }

    }
}