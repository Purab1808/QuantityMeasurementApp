package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UC14Test {

    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        assertTrue(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT).equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
    }


    @Test
    void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> a = new Quantity<>(50, TemperatureUnit.CELSIUS);
        assertTrue(a.equals(a));
    }

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit_50() {
        assertEquals(122, new Quantity<>(50, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(), 0.01);
    }

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit_Negative20() {
        assertEquals(-4, new Quantity<>(-20, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(), 0.01);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius_32() {
        assertEquals(0, new Quantity<>(32, TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS).getValue(), 0.01);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius_212() {
        assertEquals(100, new Quantity<>(212, TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS).getValue(), 0.01);
    }

    @Test
    void testTemperatureConversion_RoundTrip() {
        double val = new Quantity<>(100, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS).getValue();
        assertEquals(100, val, 0.01);
    }

    @Test
    void testTemperatureConversion_SameUnit() {
        assertEquals(50, new Quantity<>(50, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.CELSIUS).getValue());
    }

    @Test
    void testTemperatureConversion_Zero() {
        assertEquals(32, new Quantity<>(0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(), 0.01);
    }

    @Test
    void testTemperatureConversion_Large() {
        assertEquals(1832, new Quantity<>(1000, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).getValue(), 0.01);
    }

    @Test
    void testUnsupported_Add() {
        assertThrows(UnsupportedOperationException.class, () -> new Quantity<>(100, TemperatureUnit.CELSIUS).add(new Quantity<>(50, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testUnsupported_Subtract() {
        assertThrows(UnsupportedOperationException.class, () -> new Quantity<>(100, TemperatureUnit.CELSIUS).subtract(new Quantity<>(50, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testUnsupported_Divide() {
        assertThrows(UnsupportedOperationException.class, () -> new Quantity<>(100, TemperatureUnit.CELSIUS).divide(new Quantity<>(50, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testUnsupported_ErrorMessage() {
        Exception ex = assertThrows(UnsupportedOperationException.class, () -> new Quantity<>(100, TemperatureUnit.CELSIUS).add(new Quantity<>(50, TemperatureUnit.CELSIUS)));
        assertTrue(ex.getMessage().contains("Temperature"));
    }

    @Test
    void testTemperatureVsLength() {
        assertFalse(new Quantity<>(100, TemperatureUnit.CELSIUS).equals(new Quantity<>(100, LengthUnit.FEET)));
    }

    @Test
    void testTemperatureVsWeight() {
        assertFalse(new Quantity<>(50, TemperatureUnit.CELSIUS).equals(new Quantity<>(50, WeightUnit.KG)));
    }

    @Test
    void testTemperatureVsVolume() {
        assertFalse(new Quantity<>(25, TemperatureUnit.CELSIUS).equals(new Quantity<>(25, VolumeUnit.LITRE)));
    }

    @Test
    void testSupportsArithmetic_Temperature() {
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
    }

    @Test
    void testSupportsArithmetic_Length() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    @Test
    void testSupportsArithmetic_Weight() {
        assertTrue(WeightUnit.KG.supportsArithmetic());
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(100, null));
    }

    @Test
    void testEqualsNull() {
        Quantity<TemperatureUnit> q = new Quantity<>(50, TemperatureUnit.CELSIUS);
        assertFalse(q.equals(null));
    }

    @Test
    void testDifferentValues() {
        assertFalse(new Quantity<>(50, TemperatureUnit.CELSIUS).equals(new Quantity<>(100, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testPrecision() {
        double v = new Quantity<>(0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS).getValue();
        assertEquals(0, v, 0.01);
    }

    @Test
    void testSmallDifference() {
        assertEquals(0, new Quantity<>(0.0001, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.CELSIUS).getValue(), 0.01);
    }

    @Test
    void testImplementsInterface() {
        assertTrue(TemperatureUnit.CELSIUS instanceof iMeasureable);
    }

    @Test
    void testDefaultSupportInheritance() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }


    @Test
    void testValidateOperationSupport() {
        assertThrows(UnsupportedOperationException.class, () -> TemperatureUnit.CELSIUS.validateOperationSupport("ADD"));
    }

    @Test
    void testGenericIntegration() {
        assertNotNull(new Quantity<>(25, TemperatureUnit.CELSIUS));
    }

    @Test
    void testKelvinConversion() {
        assertEquals(273.15, new Quantity<>(0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.KELVIN).getValue(), 0.01);
    }

    @Test
    void testKelvinToCelsius() {
        assertEquals(0, new Quantity<>(273.15, TemperatureUnit.KELVIN).convertTo(TemperatureUnit.CELSIUS).getValue(), 0.01);
    }

    @Test
    void testDivideByZeroOtherUnits() {
        assertThrows(ArithmeticException.class, () -> new Quantity<>(10, LengthUnit.FEET).divide(new Quantity<>(0, LengthUnit.FEET)));
    }

    @Test
    void testAdditionOtherUnits() {
        assertEquals(24, new Quantity<>(12, LengthUnit.INCH).add(new Quantity<>(1, LengthUnit.FEET)).getValue(), 0.01);
    }
}