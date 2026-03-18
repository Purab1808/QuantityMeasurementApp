package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor());
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), 1e-6);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor());
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), 1e-6);
    }

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0));
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), 1e-6);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0));
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), 1e-6);
    }

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), 1e-6);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), 1e-6);
    }

    @Test
    void testQuantityLengthRefactored_Equality() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(12.0, LengthUnit.INCHES);
        assertEquals(l1, l2);
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo() {
        QuantityMeasurementApp.Length l = new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length result = l.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue());
    }

    @Test
    void testQuantityLengthRefactored_Add() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(12.0, LengthUnit.INCHES);
        QuantityMeasurementApp.Length result = l1.add(l2, LengthUnit.FEET);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(12.0, LengthUnit.INCHES);
        QuantityMeasurementApp.Length result = l1.add(l2, LengthUnit.YARDS);
        assertEquals(0.67, result.getValue(), 0.01);
    }

    @Test
    void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Length(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Length(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testRoundTripConversion_RefactoredDesign() {
        QuantityMeasurementApp.Length l = new QuantityMeasurementApp.Length(10.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length converted = l.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.FEET);
        assertEquals(l.getValue(), converted.getValue(), 0.01);
    }

    @Test
    void testUnitImmutability() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor());
    }

    @Test
    void testZeroAddition() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(5.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(0.0, LengthUnit.INCHES);

        QuantityMeasurementApp.Length result = l1.add(l2, LengthUnit.FEET);

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
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(1.0, LengthUnit.YARDS);
        assertEquals(l1, l2);
    }

    @Test
    void testHashCodeConsistency() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(12.0, LengthUnit.INCHES);
        assertEquals(l1.hashCode(), l2.hashCode());
    }

    @Test
    void testConversionPrecision() {
        QuantityMeasurementApp.Length l = new QuantityMeasurementApp.Length(2.54, LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.Length result = l.convertTo(LengthUnit.INCHES);
        assertEquals(1.0, result.getValue(), 0.01);
    }
}