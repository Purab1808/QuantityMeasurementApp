package org.example;
import java.util.Objects;

public class QuantityMeasurementApp{
    interface IMeasureable {
        double getconversionfactor();

        default double toBaseUnit(double value) {
            return value*getconversionfactor();
        }

        default double fromBaseUnit(double baseval) {
            return baseval/getconversionfactor();
        }
    }

    enum LengthUnit implements IMeasureable {
        INCH(1.0),
        FEET(12.0),
        YARD(36.0),
        CM(0.393701);

        private final double conversionfactor;

        LengthUnit(double c) {
            this.conversionfactor=c;
        }

        public double getconversionfactor() {
            return conversionfactor;
        }
    }

    enum WeightUnit implements IMeasureable {
        KG(1.0),
        GM(0.001),
        LB(0.453592);

        private final double conversionfactor;

        WeightUnit(double c) {
            this.conversionfactor=c;
        }

        public double getconversionfactor() {
            return conversionfactor;
        }
    }

    enum VolumeUnit implements IMeasureable {
        LITRE(1.0),
        MILLILITRE(0.001),
        GALLON(3.78541);

        private final double conversionfactor;

        VolumeUnit(double c) {
            this.conversionfactor=c;
        }

        public double getconversionfactor() {
            return conversionfactor;
        }
    }

    static class Quantity<U extends IMeasureable> {

        private final double value;
        private final U unit;

        Quantity(double v,U u) {
            if(u==null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if(!Double.isFinite(v)) {
                throw new IllegalArgumentException("Invalid number");
            }
            this.value=v;
            this.unit=u;
        }

        public double getValue() {
            return value;
        }

        public U getUnit() {
            return unit;
        }

        @Override
        public boolean equals(Object obj) {
            if(this==obj) return true;
            if(!(obj instanceof Quantity<?>)) return false;
            Quantity<?> other=(Quantity<?>)obj;
            if(this.unit.getClass()!=other.unit.getClass()) return false;

            double v1=this.unit.toBaseUnit(this.value);
            double v2=other.unit.toBaseUnit(other.value);

            return Math.abs(v1-v2)<1e-3;
        }

        @Override
        public int hashCode() {
            double baseValue=unit.toBaseUnit(value);
            return Objects.hash(baseValue,unit.getClass());
        }

        Quantity<U> convertTo(U target) {
            double basevalue=this.unit.toBaseUnit(value);
            return new Quantity<>(target.fromBaseUnit(basevalue),target);
        }

        Quantity<U> add(Quantity<U> other) {
            if(other==null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if(this.unit.getClass()!=other.unit.getClass()) {
                throw new IllegalArgumentException("Cross-category operation not allowed");
            }

            double sum=this.unit.toBaseUnit(value)+other.unit.toBaseUnit(other.value);
            double result=Math.round(unit.fromBaseUnit(sum)*100.0)/100.0;

            return new Quantity<>(result,unit);
        }

        Quantity<U> add(Quantity<U> other,U target) {
            if(other==null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if(target==null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            if(this.unit.getClass()!=other.unit.getClass()) {
                throw new IllegalArgumentException("Cross-category operation not allowed");
            }

            double sum=this.unit.toBaseUnit(value)+other.unit.toBaseUnit(other.value);
            double result=Math.round(target.fromBaseUnit(sum)*100.0)/100.0;

            return new Quantity<>(result,target);
        }

        Quantity<U> subtract(Quantity<U> other) {
            if(other==null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if(this.unit.getClass()!=other.unit.getClass()) {
                throw new IllegalArgumentException("Cross-category operation not allowed");
            }

            double diff=this.unit.toBaseUnit(value)-other.unit.toBaseUnit(other.value);
            double result=Math.round(unit.fromBaseUnit(diff)*100.0)/100.0;

            return new Quantity<>(result,unit);
        }

        Quantity<U> subtract(Quantity<U> other,U target) {
            if(other==null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if(target==null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            if(this.unit.getClass()!=other.unit.getClass()) {
                throw new IllegalArgumentException("Cross-category operation not allowed");
            }

            double diff=this.unit.toBaseUnit(value)-other.unit.toBaseUnit(other.value);
            double result=Math.round(target.fromBaseUnit(diff)*100.0)/100.0;

            return new Quantity<>(result,target);
        }

        double divide(Quantity<U> other) {
            if(other==null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if(this.unit.getClass()!=other.unit.getClass()) {
                throw new IllegalArgumentException("Cross-category operation not allowed");
            }

            double a=this.unit.toBaseUnit(value);
            double b=other.unit.toBaseUnit(other.value);

            if(b==0) {
                throw new ArithmeticException("Cannot divide by zero");
            }

            return a/b;
        }
    }
}