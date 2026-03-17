package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_YardToYard_SameValue() {
        assertTrue(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD)
                .equals(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD)));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertFalse(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD)
                .equals(new QuantityMeasurementApp.Length(2.0, QuantityMeasurementApp.Length.LengthUnit.YARD)));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD)
                .equals(new QuantityMeasurementApp.Length(3.0, QuantityMeasurementApp.Length.LengthUnit.FEET)));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertTrue(new QuantityMeasurementApp.Length(3.0, QuantityMeasurementApp.Length.LengthUnit.FEET)
                .equals(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD)));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertTrue(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD)
                .equals(new QuantityMeasurementApp.Length(36.0, QuantityMeasurementApp.Length.LengthUnit.INCH)));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        assertTrue(new QuantityMeasurementApp.Length(36.0, QuantityMeasurementApp.Length.LengthUnit.INCH)
                .equals(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD)));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        assertFalse(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD)
                .equals(new QuantityMeasurementApp.Length(2.0, QuantityMeasurementApp.Length.LengthUnit.FEET)));
    }

    @Test
    void testEquality_CentimeterToInch_EquivalentValue() {
        assertTrue(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.CM)
                .equals(new QuantityMeasurementApp.Length(0.393701, QuantityMeasurementApp.Length.LengthUnit.INCH)));
    }

    @Test
    void testEquality_CentimeterToFeet_NonEquivalentValue() {
        assertFalse(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.CM)
                .equals(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.FEET)));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        QuantityMeasurementApp.Length a =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD);
        QuantityMeasurementApp.Length b =
                new QuantityMeasurementApp.Length(3.0, QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length c =
                new QuantityMeasurementApp.Length(36.0, QuantityMeasurementApp.Length.LengthUnit.INCH);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void testEquality_YardSameReference() {
        QuantityMeasurementApp.Length l =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD);
        assertTrue(l.equals(l));
    }

    @Test
    void testEquality_YardNullComparison() {
        assertFalse(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.YARD)
                .equals(null));
    }

    @Test
    void testEquality_CentimeterSameReference() {
        QuantityMeasurementApp.Length l =
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.CM);
        assertTrue(l.equals(l));
    }

    @Test
    void testEquality_CentimeterNullComparison() {
        assertFalse(new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.Length.LengthUnit.CM)
                .equals(null));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.Length(1.0, null);
        });
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        QuantityMeasurementApp.Length a =
                new QuantityMeasurementApp.Length(2.0, QuantityMeasurementApp.Length.LengthUnit.YARD);
        QuantityMeasurementApp.Length b =
                new QuantityMeasurementApp.Length(6.0, QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length c =
                new QuantityMeasurementApp.Length(72.0, QuantityMeasurementApp.Length.LengthUnit.INCH);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }
}