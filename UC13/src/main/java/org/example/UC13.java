package org.example;
import java.util.*;
interface iMeasureable{
    double getconversionfactor();
    default double toBaseUnit(double value){
        return value*getconversionfactor();
    }
    default double fromBaseUnit(double baseval){
        return baseval/getconversionfactor();
    }
}

enum VolumeUnit implements iMeasureable{
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);
    private final double conversionFactor;
    VolumeUnit(double c){
        conversionFactor=c;
    }
    public double getconversionfactor(){
        return conversionFactor;
    }
}

enum LengthUnit implements iMeasureable{
    INCH(1.0),
    FEET(12.0),
    YARD(36.0),
    CM(0.393701);

    private final double conversionfactor;
    LengthUnit(double c){
        conversionfactor=c;
    }

    public double getconversionfactor(){
        return conversionfactor;
    }

}

enum WeightUnit implements iMeasureable{
    KG(1.0),
    GM(0.001),
    LB(0.453592);

    private final double conversionfactor;
    WeightUnit(double c){
        conversionfactor=c;
    }

    public double getconversionfactor(){
        return conversionfactor;
    }
}

class Quantity<U extends iMeasureable>{
    private final double value;
    private final U unit;
    Quantity(double v,U u){
        if(u==null){
            throw new IllegalArgumentException("Unit Cannot be null");
        }
        if(!Double.isFinite(v)){
            throw new IllegalArgumentException("Invalid number");
        }
        value=v;
        unit=u;
    }

    enum ArithmeticOperation{
        ADD{
            double apply(double a, double b){
                return a+b;
            }
        },
        SUBTRACT{
            double apply(double a, double b){
                return a-b;
            }
        },
        DIVIDE{
            double apply(double a, double b){
                if(b==0){
                    throw new ArithmeticException("b cannot be 0");
                }
                return a/b;
            }
        };
        abstract double apply(double a,double b);

    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public void validate(Quantity<U> other,U target ){
        if(other==null ){
            throw new IllegalArgumentException("Other cannot be null");
        }
        if(target==null){
            throw new IllegalArgumentException("Target cannot be null");
        }
        if(this.unit.getClass()!=other.unit.getClass()){
            throw new IllegalArgumentException("Cross-category operation not allowed");
        }
        if(!Double.isFinite(this.value) || !Double.isFinite(other.value)){
            throw new IllegalArgumentException("Invalid number");
        }
    }

    public void validateBasic(Quantity<U> other ){
        if(other==null ){
            throw new IllegalArgumentException("Other cannot be null");
        }

        if(!this.unit.getClass().equals(other.unit.getClass())){
            throw new IllegalArgumentException("Cross-category operation not allowed");
        }
        if(!Double.isFinite(this.value) || !Double.isFinite(other.value)){
            throw new IllegalArgumentException("Invalid number");
        }
    }


    double PerformaceBaseArithmetic(Quantity<U> other,ArithmeticOperation op){
        double a=this.unit.toBaseUnit(value);
        double b=other.unit.toBaseUnit(other.value);
        return op.apply(a,b);
    }

    public boolean equals(Object obj){
        if(this==obj)return true;
        if(obj==null || !(obj instanceof Quantity<?> a))return false;

        if(!this.unit.getClass().equals(a.unit.getClass()))return false;
        double v1 = this.unit.toBaseUnit(this.value);
        double v2 = a.unit.toBaseUnit(a.value);

        return Math.abs(v1 - v2) < 1e-3;
    }
    public int hashCode() {
        double baseValue = unit.toBaseUnit(value);
        return Objects.hash(baseValue, unit.getClass());
    }

    Quantity<U> convertTo(U target){
        double basevalue=this.unit.toBaseUnit(value);
        return new Quantity<>(target.fromBaseUnit(basevalue), target);
    }

    Quantity<U> add(Quantity<U> other){
        validate(other, unit);
        double result=PerformaceBaseArithmetic(other, ArithmeticOperation.ADD);

        return new Quantity<>(unit.fromBaseUnit(result), unit);
    }

    Quantity<U> add(Quantity<U> other,U target){
        validate(other, target);
        double result=PerformaceBaseArithmetic(other,ArithmeticOperation.ADD);
        return new Quantity<>(target.fromBaseUnit(result), target);
    }

    double divide(Quantity<U> other){
        validateBasic(other);
        double result=PerformaceBaseArithmetic(other,ArithmeticOperation.DIVIDE);
        return result;

    }

    Quantity<U> subtract(Quantity<U> other){
        validate(other, this.unit);
        double result=PerformaceBaseArithmetic(other,ArithmeticOperation.SUBTRACT);
        return new Quantity<>(this.unit.fromBaseUnit(result), this.unit);
    }

    Quantity<U> subtract(Quantity<U> other,U target){
        validate(other, target);
        double result=PerformaceBaseArithmetic(other,ArithmeticOperation.SUBTRACT);
        return new Quantity<>(target.fromBaseUnit(result),target);
    }
}
public class UC13{
    static void main() {

    }
}
