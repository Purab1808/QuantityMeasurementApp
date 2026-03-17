package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    double EPS = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.Length.convert(1.0,
                        QuantityMeasurementApp.Length.LengthUnit.FEET,
                        QuantityMeasurementApp.Length.LengthUnit.INCH), EPS);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.Length.convert(24.0,
                        QuantityMeasurementApp.Length.LengthUnit.INCH,
                        QuantityMeasurementApp.Length.LengthUnit.FEET), EPS);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.Length.convert(1.0,
                        QuantityMeasurementApp.Length.LengthUnit.YARD,
                        QuantityMeasurementApp.Length.LengthUnit.INCH), EPS);
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0,
                QuantityMeasurementApp.Length.convert(72.0,
                        QuantityMeasurementApp.Length.LengthUnit.INCH,
                        QuantityMeasurementApp.Length.LengthUnit.YARD), EPS);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.Length.convert(2.54,
                        QuantityMeasurementApp.Length.LengthUnit.CM,
                        QuantityMeasurementApp.Length.LengthUnit.INCH), 1e-5);
    }

    @Test
    void testConversion_FeetToYard() {
        assertEquals(2.0,
                QuantityMeasurementApp.Length.convert(6.0,
                        QuantityMeasurementApp.Length.LengthUnit.FEET,
                        QuantityMeasurementApp.Length.LengthUnit.YARD), EPS);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double v = 5.0;
        double result = QuantityMeasurementApp.Length.convert(
                QuantityMeasurementApp.Length.convert(v,
                        QuantityMeasurementApp.Length.LengthUnit.FEET,
                        QuantityMeasurementApp.Length.LengthUnit.INCH),
                QuantityMeasurementApp.Length.LengthUnit.INCH,
                QuantityMeasurementApp.Length.LengthUnit.FEET);

        assertEquals(v, result, EPS);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityMeasurementApp.Length.convert(0.0,
                        QuantityMeasurementApp.Length.LengthUnit.FEET,
                        QuantityMeasurementApp.Length.LengthUnit.INCH), EPS);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityMeasurementApp.Length.convert(-1.0,
                        QuantityMeasurementApp.Length.LengthUnit.FEET,
                        QuantityMeasurementApp.Length.LengthUnit.INCH), EPS);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.Length.convert(1.0, null,
                    QuantityMeasurementApp.Length.LengthUnit.FEET);
        });
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.Length.convert(Double.NaN,
                    QuantityMeasurementApp.Length.LengthUnit.FEET,
                    QuantityMeasurementApp.Length.LengthUnit.INCH);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.Length.convert(Double.POSITIVE_INFINITY,
                    QuantityMeasurementApp.Length.LengthUnit.FEET,
                    QuantityMeasurementApp.Length.LengthUnit.INCH);
        });
    }
}