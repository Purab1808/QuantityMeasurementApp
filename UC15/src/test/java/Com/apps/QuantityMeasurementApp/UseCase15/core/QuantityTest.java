package Com.apps.QuantityMeasurementApp.UseCase15.core;
import org.junit.jupiter.api.Test;
import Com.apps.QuantityMeasurementApp.UseCase15.core.Quantity;
import Com.apps.QuantityMeasurementApp.UseCase15.units.LengthUnit;
import Com.apps.QuantityMeasurementApp.UseCase15.units.WeightUnit;
import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {
    @Test
    void testEquality_Length() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_Weight() {
        Quantity<WeightUnit> q1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testConversion() {
        Quantity<LengthUnit> q = new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.001);
    }

    @Test
    void testAddition() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), 0.001);
    }

    @Test
    void testCrossCategory_ShouldFail() {
        Quantity<LengthUnit> l = new Quantity<>(1, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(1, WeightUnit.KILOGRAM);

        assertFalse(l.equals(w));
    }
}