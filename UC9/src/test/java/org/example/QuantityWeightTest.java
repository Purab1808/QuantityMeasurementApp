package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityWeightTest {

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        assertEquals(w1, w2);
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(2.0, WeightUnit.KG);
        assertNotEquals(w1, w2);
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1000.0, WeightUnit.GM);
        assertEquals(w1, w2);
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1000.0, WeightUnit.GM);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        assertEquals(w1, w2);
    }

    @Test
    void testEquality_WeightVsLength_Incompatible() {
        QuantityWeight.Weight w = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Length l = new QuantityWeight.Length(1.0, LengthUnit.FEET);
        assertNotEquals(w, l);
    }

    @Test
    void testEquality_NullComparison() {
        QuantityWeight.Weight w = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        assertNotEquals(w, null);
    }

    @Test
    void testEquality_SameReference() {
        QuantityWeight.Weight w = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        assertEquals(w, w);
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight.Weight(1.0, null));
    }

    @Test
    void testEquality_TransitiveProperty() {
        QuantityWeight.Weight a = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight b = new QuantityWeight.Weight(1000.0, WeightUnit.GM);
        QuantityWeight.Weight c = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    void testEquality_ZeroValue() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(0.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(0.0, WeightUnit.GM);
        assertEquals(w1, w2);
    }

    @Test
    void testEquality_NegativeWeight() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(-1.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(-1000.0, WeightUnit.GM);
        assertEquals(w1, w2);
    }

    @Test
    void testEquality_LargeWeightValue() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1000000.0, WeightUnit.GM);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1000.0, WeightUnit.KG);
        assertEquals(w1, w2);
    }

    @Test
    void testEquality_SmallWeightValue() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(0.001, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1.0, WeightUnit.GM);
        assertEquals(w1, w2);
    }

    @Test
    void testConversion_PoundToKilogram() {
        QuantityWeight.Weight w = new QuantityWeight.Weight(2.20462, WeightUnit.POUND);
        QuantityWeight.Weight result = w.convertTo(WeightUnit.KG);
        assertEquals(1.0, result.getValue(), 0.01);
    }

    @Test
    void testConversion_KilogramToPound() {
        QuantityWeight.Weight w = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight result = w.convertTo(WeightUnit.POUND);
        assertEquals(2.20462, result.getValue(), 0.01);
    }

    @Test
    void testConversion_SameUnit() {
        QuantityWeight.Weight w = new QuantityWeight.Weight(5.0, WeightUnit.KG);
        QuantityWeight.Weight result = w.convertTo(WeightUnit.KG);
        assertEquals(5.0, result.getValue());
    }

    @Test
    void testConversion_ZeroValue() {
        QuantityWeight.Weight w = new QuantityWeight.Weight(0.0, WeightUnit.KG);
        QuantityWeight.Weight result = w.convertTo(WeightUnit.GM);
        assertEquals(0.0, result.getValue());
    }

    @Test
    void testConversion_NegativeValue() {
        QuantityWeight.Weight w = new QuantityWeight.Weight(-1.0, WeightUnit.KG);
        QuantityWeight.Weight result = w.convertTo(WeightUnit.GM);
        assertEquals(-1000.0, result.getValue());
    }

    @Test
    void testConversion_RoundTrip() {
        QuantityWeight.Weight w = new QuantityWeight.Weight(1.5, WeightUnit.KG);
        QuantityWeight.Weight result = w.convertTo(WeightUnit.GM).convertTo(WeightUnit.KG);
        assertEquals(1.5, result.getValue(), 0.01);
    }

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(2.0, WeightUnit.KG);
        QuantityWeight.Weight result = w1.add(w2);
        assertEquals(3.0, result.getValue());
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1000.0, WeightUnit.GM);
        QuantityWeight.Weight result = w1.add(w2);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(2.20462, WeightUnit.POUND);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight result = w1.add(w2);
        assertEquals(4.41, result.getValue(), 0.01);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Kilogram() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1000.0, WeightUnit.GM);
        QuantityWeight.Weight result = QuantityWeight.Weight.add(w1, w2, WeightUnit.GM);
        assertEquals(2000.0, result.getValue());
    }

    @Test
    void testAddition_Commutativity() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1000.0, WeightUnit.GM);
        assertEquals(w1.add(w2), w2.add(w1));
    }

    @Test
    void testAddition_WithZero() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(5.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(0.0, WeightUnit.GM);
        QuantityWeight.Weight result = w1.add(w2);
        assertEquals(5.0, result.getValue());
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(5.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(-2000.0, WeightUnit.GM);
        QuantityWeight.Weight result = w1.add(w2);
        assertEquals(3.0, result.getValue());
    }

    @Test
    void testAddition_LargeValues() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1e6, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1e6, WeightUnit.KG);
        QuantityWeight.Weight result = w1.add(w2);
        assertEquals(2e6, result.getValue());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Pound() {
        QuantityWeight.Weight w1 = new QuantityWeight.Weight(1.0, WeightUnit.KG);
        QuantityWeight.Weight w2 = new QuantityWeight.Weight(1000.0, WeightUnit.GM);

        QuantityWeight.Weight result = QuantityWeight.Weight.add(w1, w2, WeightUnit.POUND);

        assertEquals(4.41, result.getValue(), 0.01);
    }
}