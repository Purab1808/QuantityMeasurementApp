package org.example;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UC13Test {

    @Test
    void testArithmeticOperation_Add_EnumComputation() {
        assertEquals(15.0, Quantity.ArithmeticOperation.ADD.apply(10, 5));
    }

    @Test
    void testArithmeticOperation_Subtract_EnumComputation() {
        assertEquals(5.0, Quantity.ArithmeticOperation.SUBTRACT.apply(10, 5));
    }

    @Test
    void testArithmeticOperation_Divide_EnumComputation() {
        assertEquals(2.0, Quantity.ArithmeticOperation.DIVIDE.apply(10, 5));
    }

    @Test
    void testArithmeticOperation_DivideByZero_EnumThrows() {
        assertThrows(ArithmeticException.class, () ->
                Quantity.ArithmeticOperation.DIVIDE.apply(10, 0));
    }

    @Test
    void testValidation_NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q = new Quantity<>(1, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.add(null));
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> q.divide(null));
    }




    @Test
    void testValidation_NullTargetUnit_AddSubtractReject() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCH);
        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2, null));
    }

    @Test
    void testPerformBaseArithmetic_ConversionAndOperation() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCH);
        assertEquals(2.0, q1.add(q2).getValue(), 1e-3);
    }

    @Test
    void testAdd_UC12_BehaviorPreserved() {
        Quantity<WeightUnit> w1 = new Quantity<>(10, WeightUnit.KG);
        Quantity<WeightUnit> w2 = new Quantity<>(5000, WeightUnit.GM);
        Quantity<WeightUnit> result = w1.add(w2);
        assertEquals(15.0, result.getValue(), 1e-3);
    }

    @Test
    void testSubtract_UC12_BehaviorPreserved() {
        Quantity<WeightUnit> w1 = new Quantity<>(10, WeightUnit.KG);
        Quantity<WeightUnit> w2 = new Quantity<>(5000, WeightUnit.GM);
        Quantity<WeightUnit> result = w1.subtract(w2);
        assertEquals(5.0, result.getValue(), 1e-3);
    }

    @Test
    void testDivide_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);
        assertEquals(5.0, q1.divide(q2), 1e-3);
    }

    @Test
    void testRounding_AddSubtract_TwoDecimalPlaces() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.234, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.345, LengthUnit.FEET);
        double sum = Math.round(q1.add(q2).getValue() * 100.0) / 100.0;
        assertEquals(3.58, sum);
    }

    @Test
    void testRounding_Divide_NoRounding() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);
        assertEquals(2.5, q1.divide(q2), 1e-6);
    }

    @Test
    void testImplicitTargetUnit_AddSubtract() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCH);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(2.0, result.getValue(), 1e-3);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testExplicitTargetUnit_AddSubtract_Overrides() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCH);
        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.INCH);
        assertEquals(24.0, result.getValue(), 1e-3);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testImmutability_AfterAdd_ViaCentralizedHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCH);
        q1.add(q2);
        assertEquals(1.0, q1.getValue());
    }

    @Test
    void testImmutability_AfterSubtract_ViaCentralizedHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);
        q1.subtract(q2);
        assertEquals(5.0, q1.getValue());
    }

    @Test
    void testImmutability_AfterDivide_ViaCentralizedHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);
        q1.divide(q2);
        assertEquals(5.0, q1.getValue());
    }

    @Test
    void testEqualityAcrossUnits() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCH);
        assertEquals(q1, q2);
    }

    @Test
    void testHashConsistency() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCH);
        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    void testAllOperations_AcrossAllCategories() {
        Quantity<LengthUnit> qLen1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> qLen2 = new Quantity<>(12, LengthUnit.INCH);
        assertEquals(2.0, qLen1.add(qLen2).getValue(), 1e-3);

        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KG);
        Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GM);
        assertEquals(2.0, w1.add(w2).getValue(), 1e-3);

        Quantity<VolumeUnit> v1 = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        assertEquals(2.0, v1.add(v2).getValue(), 1e-3);
    }
}