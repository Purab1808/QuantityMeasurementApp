package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_SameValue() {
        assertTrue(new QuantityMeasurementApp.Feet(1.0).equals(new QuantityMeasurementApp.Feet(1.0)));
        assertTrue(new QuantityMeasurementApp.Inches(1.0).equals(new QuantityMeasurementApp.Inches(1.0)));
    }

    @Test
    void testEquality_DifferentValue() {
        assertFalse(new QuantityMeasurementApp.Feet(1.0).equals(new QuantityMeasurementApp.Feet(2.0)));
        assertFalse(new QuantityMeasurementApp.Inches(1.0).equals(new QuantityMeasurementApp.Inches(2.0)));
    }

    @Test
    void testEquality_NullComparison() {
        assertFalse(new QuantityMeasurementApp.Feet(1.0).equals(null));
        assertFalse(new QuantityMeasurementApp.Inches(1.0).equals(null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(f.equals(f));
        assertTrue(i.equals(i));
    }

    @Test
    void testEquality_TypeMismatch() {
        assertFalse(new QuantityMeasurementApp.Feet(1.0).equals(new QuantityMeasurementApp.Inches(12.0)));
    }
}