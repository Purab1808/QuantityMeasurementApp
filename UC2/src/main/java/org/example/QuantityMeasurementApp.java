package org.example;
import java.util.Objects;

public class QuantityMeasurementApp {

    static class Feet {
        private double value;

        public Feet(double value) {
            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Invalid feet value");
            }
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    static class Inches {
        private double value;

        public Inches(double value) {
            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Invalid inches value");
            }
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static boolean compareFeet(double a, double b) {
        return new Feet(a).equals(new Feet(b));
    }

    public static boolean compareInches(double a, double b) {
        return new Inches(a).equals(new Inches(b));
    }

    public static void main(String[] args) {
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + compareFeet(1.0, 1.0) + ")");

        System.out.println("\nInput: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + compareInches(1.0, 1.0) + ")");
    }
}
