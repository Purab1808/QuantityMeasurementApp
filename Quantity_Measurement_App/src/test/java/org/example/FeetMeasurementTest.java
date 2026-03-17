package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeetMeasurementTest {
    @Test
    void testEquality_SameValue() {
        FeetMeasurement.Feet f1 = new FeetMeasurement.Feet(1.0);
        FeetMeasurement.Feet f2 = new FeetMeasurement.Feet(1.0);
        assertTrue(f1.equals(f2));
    }
    @Test
    void testEquality_DifferentValue() {
        FeetMeasurement.Feet f1 = new FeetMeasurement.Feet(1.0);
        FeetMeasurement.Feet f2 = new FeetMeasurement.Feet(2.0);
        assertFalse(f1.equals(f2));
    }
    @Test
    void testEquality_NullComparison() {
        FeetMeasurement.Feet f1 = new FeetMeasurement.Feet(1.0);
        assertFalse(f1.equals(null));
    }
    @Test
    void testEquality_NonNumericInput() {
        FeetMeasurement.Feet f1 = new FeetMeasurement.Feet(1.0);
        String str = "hello";
        assertFalse(f1.equals(str));
    }
    @Test
    void testEquality_SameReference() {
        FeetMeasurement.Feet f1 = new FeetMeasurement.Feet(1.0);
        assertTrue(f1.equals(f1));
    }
}
