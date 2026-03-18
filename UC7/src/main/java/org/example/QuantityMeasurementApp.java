package org.example;
import java.util.Objects;
public class QuantityMeasurementApp {
    static class Length{
        private double value;
        private LengthUnit unit;
        public enum LengthUnit{
            FEET(12.0),
            INCH(1.0),
            YARD(36.0),
            CM(0.393701);

            private final double conversionFactor;
            LengthUnit(double conversionFactor){
                this.conversionFactor=conversionFactor;
            }
            double getConversionFactor(){
                return this.conversionFactor;
            }
        }
        public Length(double value,LengthUnit unit){
            if(unit==null) throw new IllegalArgumentException("Invalid");
            this.value=value;
            this.unit=unit;
        }
        double getValue(){
            return this.value;
        }
        double baseUnit(){
            return this.value*this.unit.getConversionFactor();
        }
        @Override
        public boolean equals(Object obj){
            if(this==obj) return true;
            if(obj==null || this.getClass()!=obj.getClass()) return false;
            Length l= (Length) obj;
            return Double.compare(this.baseUnit(),l.baseUnit())==0;
        }

        static Length add(Length l1,Length l2,LengthUnit targetUnit){
            if(l1==null || l2==null) throw new IllegalArgumentException("Can not be null");
            if(l1.unit==null || l2.unit==null) throw new IllegalArgumentException("Unit can not be null");
            if(targetUnit==null) throw new IllegalArgumentException("Target unit can not be null");
            if(!Double.isFinite(l1.value) || !Double.isFinite(l2.value)) throw new IllegalArgumentException("Value can not be Infinite");
            double res1= l1.baseUnit();
            double res2=l2.baseUnit();
            double result=res1+res2;
            return new Length(result/targetUnit.getConversionFactor(),targetUnit);
        }
    }
}