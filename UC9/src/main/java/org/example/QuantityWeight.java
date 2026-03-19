package org.example;
import java.util.Objects;
enum WeightUnit{
    KG(1.0),
    GM(0.001),
    POUND(0.453592);
    private final double conversionFactor;
    WeightUnit(double conversionFactor){
        this.conversionFactor=conversionFactor;
    }
    double convertToBaseUnit(double value){
        return value*conversionFactor;
    }
    double convertFromBaseUnit(double baseValue){
        return baseValue/conversionFactor;
    }
}
enum LengthUnit{
    FEET(1.0),
    INCH(1.0/12.0),
    YARD(3.0),
    CM(1.0/30.48);
    private final double conversionFactor;
    LengthUnit(double conversionFactor){
        this.conversionFactor=conversionFactor;
    }
    double toBase(double value){
        return value*conversionFactor;
    }
    double fromBase(double baseValue){
        return baseValue/conversionFactor;
    }
}
public class QuantityWeight {
    static class Weight{
        private final double value;
        private final WeightUnit unit;
        private static final double Epsilon = 1e-6;

        public Weight(double value, WeightUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit can not be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public WeightUnit getUnit() {
            return unit;
        }

        private double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        private static double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            Weight w = (Weight) obj;
            return Math.abs(this.toBaseUnit() - w.toBaseUnit()) < Epsilon;
        }

        @Override
        public int hashCode() {
            return Objects.hash(round(toBaseUnit()));
        }

        public Weight convertTo(WeightUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

            double base = toBaseUnit();
            double result = targetUnit.convertFromBaseUnit(base);

            return new Weight(round(result), targetUnit);
        }

        public Weight add(Weight other) {
            if (other == null) throw new IllegalArgumentException("Weight cannot be null");
            if (!Double.isFinite(other.value)) throw new IllegalArgumentException("Invalid value");

            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            double result = this.unit.convertFromBaseUnit(sumBase);

            return new Weight(round(result), this.unit);
        }

        public static Weight add(Weight w1, Weight w2) {
            return add(w1, w2, w1.unit);
        }

        public static Weight add(Weight w1, Weight w2, WeightUnit targetUnit) {
            if (w1 == null || w2 == null) throw new IllegalArgumentException("Weights cannot be null");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            if (!Double.isFinite(w1.value) || !Double.isFinite(w2.value)) throw new IllegalArgumentException("Invalid values");

            double sumBase = w1.toBaseUnit() + w2.toBaseUnit();
            double result = targetUnit.convertFromBaseUnit(sumBase);

            return new Weight(round(result), targetUnit);
        }
    }
    static class Length{
        private final double value;
        private final LengthUnit unit;
        private static final double Epsilon=1e-6;
        public Length(double value,LengthUnit unit){
            if(unit==null) throw new IllegalArgumentException("Unit can not be null");
            if(!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            this.value=value;
            this.unit=unit;
        }
        public double getValue(){
            return value;
        }
        public LengthUnit getUnit(){
            return unit;
        }
        private double toBaseUnit(){
            return unit.toBase(value);
        }
        private static double round(double value){
            return Math.round(value * 100.0)/100.0;
        }
        @Override
        public boolean equals(Object obj){
            if(this==obj) return true;
            if(obj==null || obj.getClass()!=this.getClass()) return false;
            Length l=(Length) obj;
            return Math.abs(this.toBaseUnit()-l.toBaseUnit())<Epsilon;
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
