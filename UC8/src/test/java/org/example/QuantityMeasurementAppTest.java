package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.toBase(1.0));
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCH.toBase(1.0), 1e-6);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARD.toBase(1.0));
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CM.toBase(1.0), 1e-6);
    }

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.toBase(5.0));
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCH.toBase(12.0), 1e-6);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARD.toBase(1.0));
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CM.toBase(30.48), 1e-6);
    }

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.fromBase(2.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCH.fromBase(1.0), 1e-6);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARD.fromBase(3.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CM.fromBase(1.0), 1e-6);
    }

    @Test
    void testQuantityLengthRefactored_Equality() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(12.0, LengthUnit.INCH);
        assertEquals(l1, l2);
    }

    @Test
    void testQuantityLengthRefactored_Add() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(12.0, LengthUnit.INCH);
        QuantityMeasurementApp.Length result = l1.add(l2);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(12.0, LengthUnit.INCH);
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(l1, l2, LengthUnit.YARD);
        assertEquals(0.67, result.getValue(), 0.01);
    }

    @Test
    void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Length(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Length(Double.NaN,
                LengthUnit.FEET));
    }

    @Test
    void testRoundTripConversion_RefactoredDesign() {
        QuantityMeasurementApp.Length l = new QuantityMeasurementApp.Length(10.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length converted = QuantityMeasurementApp.Length.add(l, new QuantityMeasurementApp.Length(0,
                        LengthUnit.FEET));
        assertEquals(l.getValue(), converted.getValue(), 0.01);
    }

    @Test
    void testUnitImmutability() {
        assertEquals(1.0, LengthUnit.FEET.toBase(1.0));
    }

    @Test
    void testZeroAddition() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(5.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(0.0, LengthUnit.INCH);
        QuantityMeasurementApp.Length result = l1.add(l2);
        assertEquals(5.0, result.getValue());
    }

    @Test
    void testEqualitySameObject() {
        QuantityMeasurementApp.Length l = new QuantityMeasurementApp.Length(5.0, LengthUnit.FEET);
        assertEquals(l, l);
    }

    @Test
    void testEqualityDifferentUnits() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(3.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(1.0, LengthUnit.YARD);
        assertEquals(l1, l2);
    }

    @Test
    void testHashCodeConsistency() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(12.0, LengthUnit.INCH);
        assertEquals(l1.hashCode(), l2.hashCode());
    }

    @Test
    void testConversionPrecision() {
        QuantityMeasurementApp.Length l = new QuantityMeasurementApp.Length(2.54, LengthUnit.CM);
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(l, new QuantityMeasurementApp.Length(0,
                        LengthUnit.CM));
        assertEquals(2.54, result.getValue(), 0.01);
    }
    @Test
    void testAddition_Commutativity() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(1.0,
                        LengthUnit.FEET);

        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(12.0,
                        LengthUnit.INCH);
        QuantityMeasurementApp.Length result1 = QuantityMeasurementApp.Length.add(l1, l2);
        QuantityMeasurementApp.Length result2 = QuantityMeasurementApp.Length.add(l2, l1);
        assertEquals(result1, result2);
    }
}