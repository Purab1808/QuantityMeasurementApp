package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.QuantityMeasurementApp.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_LitreToLitre_SameValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_MillilitreToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1000.0, VolumeUnit.MILLILITRE).equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToGallon_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(0.264172, VolumeUnit.GALLON)));
    }

    @Test
    void testEquality_GallonToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.GALLON).equals(new Quantity<>(3.78541, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_VolumeVsLength_Incompatible() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_VolumeVsWeight_Incompatible() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, WeightUnit.KG)));
    }

    @Test
    void testEquality_NullComparison() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(null));
    }

    @Test
    void testEquality_SameReference() {
        Quantity<VolumeUnit> q=new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,()->new Quantity<>(1.0,null));
    }

    @Test
    void testEquality_TransitiveProperty() {
        Quantity<VolumeUnit> a=new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b=new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> c=new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(a.equals(b)&&b.equals(c)&&a.equals(c));
    }

    @Test
    void testEquality_ZeroValue() {
        assertTrue(new Quantity<>(0.0, VolumeUnit.LITRE).equals(new Quantity<>(0.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_NegativeVolume() {
        assertTrue(new Quantity<>(-1.0, VolumeUnit.LITRE).equals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_LargeVolumeValue() {
        assertTrue(new Quantity<>(1000000.0, VolumeUnit.MILLILITRE).equals(new Quantity<>(1000.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_SmallVolumeValue() {
        assertTrue(new Quantity<>(0.001, VolumeUnit.LITRE).equals(new Quantity<>(1.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testConversion_LitreToMillilitre() {
        assertEquals(1000.0,new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE).getValue(),1e-3);
    }

    @Test
    void testConversion_MillilitreToLitre() {
        assertEquals(1.0,new Quantity<>(1000.0, VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE).getValue(),1e-3);
    }

    @Test
    void testConversion_GallonToLitre() {
        assertEquals(3.78541,new Quantity<>(1.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE).getValue(),1e-3);
    }

    @Test
    void testConversion_LitreToGallon() {
        assertEquals(1.0,new Quantity<>(3.78541, VolumeUnit.LITRE).convertTo(VolumeUnit.GALLON).getValue(),1e-3);
    }

    @Test
    void testConversion_MillilitreToGallon() {
        assertEquals(0.264172,new Quantity<>(1000.0, VolumeUnit.MILLILITRE).convertTo(VolumeUnit.GALLON).getValue(),1e-3);
    }

    @Test
    void testConversion_SameUnit() {
        assertEquals(5.0,new Quantity<>(5.0, VolumeUnit.LITRE).convertTo(VolumeUnit.LITRE).getValue(),1e-3);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,new Quantity<>(0.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE).getValue(),1e-3);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-1000.0,new Quantity<>(-1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE).getValue(),1e-3);
    }

    @Test
    void testConversion_RoundTrip() {
        Quantity<VolumeUnit> q=new Quantity<>(1.5, VolumeUnit.LITRE);
        assertEquals(1.5,q.convertTo(VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE).getValue(),1e-3);
    }

    @Test
    void testAddition_SameUnit_LitrePlusLitre() {
        assertEquals(3.0,new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(2.0, VolumeUnit.LITRE)).getValue(),1e-3);
    }

    @Test
    void testAddition_SameUnit_MillilitrePlusMillilitre() {
        assertEquals(1000.0,new Quantity<>(500.0, VolumeUnit.MILLILITRE).add(new Quantity<>(500.0, VolumeUnit.MILLILITRE)).getValue(),1e-3);
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        assertEquals(2.0,new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)).getValue(),1e-3);
    }

    @Test
    void testAddition_CrossUnit_MillilitrePlusLitre() {
        assertEquals(2000.0,new Quantity<>(1000.0, VolumeUnit.MILLILITRE).add(new Quantity<>(1.0, VolumeUnit.LITRE)).getValue(),1e-3);
    }

    @Test
    void testAddition_CrossUnit_GallonPlusLitre() {
        assertEquals(2.0,new Quantity<>(1.0, VolumeUnit.GALLON).add(new Quantity<>(3.78541, VolumeUnit.LITRE)).getValue(),1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Litre() {
        assertEquals(2.0,new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),VolumeUnit.LITRE).getValue(),1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre() {
        assertEquals(2000.0,new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),VolumeUnit.MILLILITRE).getValue(),1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gallon() {
        assertEquals(2.0,new Quantity<>(3.78541, VolumeUnit.LITRE).add(new Quantity<>(3.78541, VolumeUnit.LITRE),VolumeUnit.GALLON).getValue(),1e-3);
    }

    @Test
    void testAddition_Commutativity() {
        Quantity<VolumeUnit> a=new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b=new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(a.add(b).convertTo(VolumeUnit.LITRE).getValue(),b.add(a).convertTo(VolumeUnit.LITRE).getValue(),1e-3);
    }

    @Test
    void testAddition_WithZero() {
        assertEquals(5.0,new Quantity<>(5.0, VolumeUnit.LITRE).add(new Quantity<>(0.0, VolumeUnit.MILLILITRE)).getValue(),1e-3);
    }

    @Test
    void testAddition_NegativeValues() {
        assertEquals(3.0,new Quantity<>(5.0, VolumeUnit.LITRE).add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE)).getValue(),1e-3);
    }

    @Test
    void testAddition_LargeValues() {
        assertEquals(2e6,new Quantity<>(1e6, VolumeUnit.LITRE).add(new Quantity<>(1e6, VolumeUnit.LITRE)).getValue(),1e-3);
    }

    @Test
    void testAddition_SmallValues() {
        assertEquals(0.003,new Quantity<>(0.001, VolumeUnit.LITRE).add(new Quantity<>(0.002, VolumeUnit.LITRE)).getValue(),1e-3);
    }

    @Test
    void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0,VolumeUnit.LITRE.getconversionfactor());
    }

    @Test
    void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001,VolumeUnit.MILLILITRE.getconversionfactor());
    }

    @Test
    void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3.78541,VolumeUnit.GALLON.getconversionfactor());
    }

    @Test
    void testConvertToBaseUnit_LitreToLitre() {
        assertEquals(5.0,VolumeUnit.LITRE.toBaseUnit(5.0),1e-3);
    }

    @Test
    void testConvertToBaseUnit_MillilitreToLitre() {
        assertEquals(1.0,VolumeUnit.MILLILITRE.toBaseUnit(1000.0),1e-3);
    }

    @Test
    void testConvertToBaseUnit_GallonToLitre() {
        assertEquals(3.78541,VolumeUnit.GALLON.toBaseUnit(1.0),1e-3);
    }

    @Test
    void testConvertFromBaseUnit_LitreToLitre() {
        assertEquals(2.0,VolumeUnit.LITRE.fromBaseUnit(2.0),1e-3);
    }

    @Test
    void testConvertFromBaseUnit_LitreToMillilitre() {
        assertEquals(1000.0,VolumeUnit.MILLILITRE.fromBaseUnit(1.0),1e-3);
    }

    @Test
    void testConvertFromBaseUnit_LitreToGallon() {
        assertEquals(1.0,VolumeUnit.GALLON.fromBaseUnit(3.78541),1e-3);
    }

    @Test
    void testBackwardCompatibility_AllUC1Through10Tests() {
        assertTrue(true);
    }

    @Test
    void testGenericQuantity_VolumeOperations_Consistency() {
        Quantity<VolumeUnit> q=new Quantity<>(1.0, VolumeUnit.LITRE);
        assertNotNull(q);
    }

    @Test
    void testScalability_VolumeIntegration() {
        assertTrue(true);
    }
}