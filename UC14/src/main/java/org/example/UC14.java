package org.example;
import java.util.*;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

interface iMeasureable {
    double getconversionfactor();
    default double toBaseUnit(double value) {
        return value * getconversionfactor();
    }
    default double fromBaseUnit(double baseval) {
        return baseval / getconversionfactor();
    }
    default boolean supportsArithmetic() {
        return true;
    }
    default void validateOperationSupport(String operation) {
    }
}

enum VolumeUnit implements iMeasureable {
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double c) {
        conversionFactor=c;
    }

    public double getconversionfactor() {
        return conversionFactor;
    }
}

enum LengthUnit implements iMeasureable {
    INCH(1.0),
    FEET(12.0),
    YARD(36.0),
    CM(0.393701);

    private final double conversionfactor;

    LengthUnit(double c) {
        conversionfactor=c;
    }

    public double getconversionfactor() {
        return conversionfactor;
    }
}

enum WeightUnit implements iMeasureable {
    KG(1.0),
    GM(0.001),
    LB(0.453592);

    private final double conversionfactor;

    WeightUnit(double c) {
        conversionfactor=c;
    }

    public double getconversionfactor() {
        return conversionfactor;
    }
}

enum TemperatureUnit implements iMeasureable {

    CELSIUS {
        public double toBaseUnit(double value) {
            return value;
        }
        public double fromBaseUnit(double base) {
            return base;
        }
    },

    FAHRENHEIT {
        public double toBaseUnit(double value) {
            return (value - 32) * 5 / 9;
        }
        public double fromBaseUnit(double base) {
            return (base * 9 / 5) + 32;
        }
    },

    KELVIN {
        public double toBaseUnit(double value) {
            return value - 273.15;
        }
        public double fromBaseUnit(double base) {
            return base + 273.15;
        }
    };

    public double getconversionfactor() {
        return 1.0;
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException("Temperature does not support " + operation);
    }
}

class Quantity<U extends iMeasureable> {
    private final double value;
    private final U unit;

    public double getValue() {
        return value;
    }

    Quantity(double v, U u) {
        if (u == null) throw new IllegalArgumentException("Unit Cannot be null");
        if (!Double.isFinite(v)) throw new IllegalArgumentException("Invalid number");
        value=v;
        unit=u;
    }

    enum ArithmeticOperation {
        ADD {
            double apply(double a, double b) { return a + b; }
        },
        SUBTRACT {
            double apply(double a, double b) { return a - b; }
        },
        DIVIDE {
            double apply(double a, double b) {
                if (b == 0) throw new ArithmeticException("b cannot be 0");
                return a / b;
            }
        };
        abstract double apply(double a,double b);
    }

    public void validateBasic(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Other cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");

        if (this.unit instanceof TemperatureUnit && this.unit != other.unit) {
            throw new UnsupportedOperationException("Cannot operate on different temperature units");
        }
    }

    double PerformaceBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {
        this.unit.validateOperationSupport(op.name());
        double a=this.unit.toBaseUnit(value);
        double b=other.unit.toBaseUnit(other.value);
        return op.apply(a,b);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Quantity)) return false;
        Quantity<?> a=(Quantity<?>) obj;

        if (!this.unit.getClass().equals(a.unit.getClass())) return false;

        double v1=this.unit.toBaseUnit(this.value);
        double v2=a.unit.toBaseUnit(a.value);

        // 🔥 FIX: better tolerance
        return Math.abs(v1 - v2) < 1e-2;
    }

    public int hashCode() {
        double baseValue=unit.toBaseUnit(value);

        // 🔥 FIX: rounding for consistency
        long rounded=Math.round(baseValue * 100);
        return Objects.hash(rounded, unit.getClass());
    }

    Quantity<U> convertTo(U target) {
        double basevalue=this.unit.toBaseUnit(value);
        return new Quantity<>(target.fromBaseUnit(basevalue), target);
    }

    Quantity<U> add(Quantity<U> other) {
        validateBasic(other);
        double result=PerformaceBaseArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(unit.fromBaseUnit(result), unit);
    }

    double divide(Quantity<U> other) {
        validateBasic(other);
        return PerformaceBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    Quantity<U> subtract(Quantity<U> other) {
        validateBasic(other);
        double result=PerformaceBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(unit.fromBaseUnit(result), unit);
    }
}

public class UC14 {
    public static void main(String[] args) {

    }
}