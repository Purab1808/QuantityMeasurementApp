package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        QuantityMeasurementApp.Length l1 =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        QuantityMeasurementApp.Length l1 =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.INCH);
        QuantityMeasurementApp.Length l2 =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.INCH);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        QuantityMeasurementApp.Length l1 =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 =
                new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.Length.LengthUnit.INCH);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        QuantityMeasurementApp.Length l1 =
                new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.Length.LengthUnit.INCH);
        QuantityMeasurementApp.Length l2 =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        QuantityMeasurementApp.Length l1 =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 =
                new QuantityMeasurementApp.Length(2.0, QuantityMeasurementApp.Length.LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        QuantityMeasurementApp.Length l1 =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.INCH);
        QuantityMeasurementApp.Length l2 =
                new QuantityMeasurementApp.Length(2.0, QuantityMeasurementApp.Length.LengthUnit.INCH);

        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Length l =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.FEET);

        assertTrue(l.equals(l));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Length l =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.FEET);

        assertFalse(l.equals(null));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.Length(1.0, null);
        });
    }
}