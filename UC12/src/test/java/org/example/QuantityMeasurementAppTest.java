package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.QuantityMeasurementApp.*;

public class QuantityMeasurementAppTest {

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        assertEquals(5.0,new Quantity<>(10.0,LengthUnit.FEET).subtract(new Quantity<>(5.0,LengthUnit.FEET)).getValue(),1e-2);
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        assertEquals(7.0,new Quantity<>(10.0,VolumeUnit.LITRE).subtract(new Quantity<>(3.0,VolumeUnit.LITRE)).getValue(),1e-2);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        assertEquals(9.5,new Quantity<>(10.0,LengthUnit.FEET).subtract(new Quantity<>(6.0,LengthUnit.INCH)).getValue(),1e-2);
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
        assertEquals(60.0,new Quantity<>(120.0,LengthUnit.INCH).subtract(new Quantity<>(5.0,LengthUnit.FEET)).getValue(),1e-2);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet() {
        assertEquals(9.5,new Quantity<>(10.0,LengthUnit.FEET).subtract(new Quantity<>(6.0,LengthUnit.INCH),LengthUnit.FEET).getValue(),1e-2);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        assertEquals(114.0,new Quantity<>(10.0,LengthUnit.FEET).subtract(new Quantity<>(6.0,LengthUnit.INCH),LengthUnit.INCH).getValue(),1e-2);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre() {
        assertEquals(3000.0,new Quantity<>(5.0,VolumeUnit.LITRE).subtract(new Quantity<>(2.0,VolumeUnit.LITRE),VolumeUnit.MILLILITRE).getValue(),1e-2);
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        assertEquals(-5.0,new Quantity<>(5.0,LengthUnit.FEET).subtract(new Quantity<>(10.0,LengthUnit.FEET)).getValue(),1e-2);
    }

    @Test
    void testSubtraction_ResultingInZero() {
        assertEquals(0.0,new Quantity<>(10.0,LengthUnit.FEET).subtract(new Quantity<>(120.0,LengthUnit.INCH)).getValue(),1e-2);
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        assertEquals(5.0,new Quantity<>(5.0,LengthUnit.FEET).subtract(new Quantity<>(0.0,LengthUnit.INCH)).getValue(),1e-2);
    }

    @Test
    void testSubtraction_WithNegativeValues() {
        assertEquals(7.0,new Quantity<>(5.0,LengthUnit.FEET).subtract(new Quantity<>(-2.0,LengthUnit.FEET)).getValue(),1e-2);
    }

    @Test
    void testSubtraction_NonCommutative() {
        double a=new Quantity<>(10.0,LengthUnit.FEET).subtract(new Quantity<>(5.0,LengthUnit.FEET)).getValue();
        double b=new Quantity<>(5.0,LengthUnit.FEET).subtract(new Quantity<>(10.0,LengthUnit.FEET)).getValue();
        assertNotEquals(a,b);
    }

    @Test
    void testSubtraction_WithLargeValues() {
        assertEquals(5e5,new Quantity<>(1e6,WeightUnit.KG).subtract(new Quantity<>(5e5,WeightUnit.KG)).getValue(),1e-2);
    }

    @Test
    void testSubtraction_WithSmallValues() {
        assertEquals(0.0005,new Quantity<>(0.001,LengthUnit.FEET).subtract(new Quantity<>(0.0005,LengthUnit.FEET)).getValue(),1e-2);
    }

    @Test
    void testSubtraction_NullOperand() {
        assertThrows(IllegalArgumentException.class,()->new Quantity<>(10.0,LengthUnit.FEET).subtract(null));
    }

    @Test
    void testSubtraction_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class,()->new Quantity<>(10.0,LengthUnit.FEET).subtract(new Quantity<>(5.0,LengthUnit.FEET),null));
    }

    @Test
    void testSubtraction_CrossCategory() {
        Quantity a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity b=new Quantity<>(5.0,WeightUnit.KG);
        assertThrows(IllegalArgumentException.class,()->a.subtract(b));
    }

    @Test
    void testSubtraction_AllMeasurementCategories() {
        assertNotNull(new Quantity<>(5.0,LengthUnit.FEET).subtract(new Quantity<>(2.0,LengthUnit.FEET)));
        assertNotNull(new Quantity<>(5.0,WeightUnit.KG).subtract(new Quantity<>(2.0,WeightUnit.KG)));
        assertNotNull(new Quantity<>(5.0,VolumeUnit.LITRE).subtract(new Quantity<>(2.0,VolumeUnit.LITRE)));
    }

    @Test
    void testSubtraction_ChainedOperations() {
        assertEquals(7.0,new Quantity<>(10.0,LengthUnit.FEET).subtract(new Quantity<>(2.0,LengthUnit.FEET)).subtract(new Quantity<>(1.0,LengthUnit.FEET)).getValue(),1e-2);
    }

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {
        assertEquals(5.0,new Quantity<>(10.0,LengthUnit.FEET).divide(new Quantity<>(2.0,LengthUnit.FEET)),1e-2);
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre() {
        assertEquals(2.0,new Quantity<>(10.0,VolumeUnit.LITRE).divide(new Quantity<>(5.0,VolumeUnit.LITRE)),1e-2);
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {
        assertEquals(1.0,new Quantity<>(24.0,LengthUnit.INCH).divide(new Quantity<>(2.0,LengthUnit.FEET)),1e-2);
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram() {
        assertEquals(1.0,new Quantity<>(2.0,WeightUnit.KG).divide(new Quantity<>(2000.0,WeightUnit.GM)),1e-2);
    }

    @Test
    void testDivision_RatioGreaterThanOne() {
        assertEquals(5.0,new Quantity<>(10.0,LengthUnit.FEET).divide(new Quantity<>(2.0,LengthUnit.FEET)),1e-2);
    }

    @Test
    void testDivision_RatioLessThanOne() {
        assertEquals(0.5,new Quantity<>(5.0,LengthUnit.FEET).divide(new Quantity<>(10.0,LengthUnit.FEET)),1e-2);
    }

    @Test
    void testDivision_RatioEqualToOne() {
        assertEquals(1.0,new Quantity<>(10.0,LengthUnit.FEET).divide(new Quantity<>(10.0,LengthUnit.FEET)),1e-2);
    }

    @Test
    void testDivision_NonCommutative() {
        double a=new Quantity<>(10.0,LengthUnit.FEET).divide(new Quantity<>(5.0,LengthUnit.FEET));
        double b=new Quantity<>(5.0,LengthUnit.FEET).divide(new Quantity<>(10.0,LengthUnit.FEET));
        assertNotEquals(a,b);
    }

    @Test
    void testDivision_ByZero() {
        assertThrows(ArithmeticException.class,()->new Quantity<>(10.0,LengthUnit.FEET).divide(new Quantity<>(0.0,LengthUnit.FEET)));
    }

    @Test
    void testDivision_WithLargeRatio() {
        assertEquals(1e6,new Quantity<>(1e6,WeightUnit.KG).divide(new Quantity<>(1.0,WeightUnit.KG)),1e-2);
    }

    @Test
    void testDivision_WithSmallRatio() {
        assertEquals(1e-6,new Quantity<>(1.0,WeightUnit.KG).divide(new Quantity<>(1e6,WeightUnit.KG)),1e-6);
    }

    @Test
    void testDivision_NullOperand() {
        assertThrows(IllegalArgumentException.class,()->new Quantity<>(10.0,LengthUnit.FEET).divide(null));
    }

    @Test
    void testDivision_CrossCategory() {
        Quantity a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity b=new Quantity<>(5.0,WeightUnit.KG);
        assertThrows(IllegalArgumentException.class,()->a.divide(b));
    }

    @Test
    void testDivision_AllMeasurementCategories() {
        assertEquals(2.0,new Quantity<>(10.0,LengthUnit.FEET).divide(new Quantity<>(5.0,LengthUnit.FEET)),1e-2);
        assertEquals(2.0,new Quantity<>(10.0,WeightUnit.KG).divide(new Quantity<>(5.0,WeightUnit.KG)),1e-2);
        assertEquals(2.0,new Quantity<>(10.0,VolumeUnit.LITRE).divide(new Quantity<>(5.0,VolumeUnit.LITRE)),1e-2);
    }

    @Test
    void testDivision_Associativity() {
        double a=new Quantity<>(10.0,LengthUnit.FEET).divide(new Quantity<>(2.0,LengthUnit.FEET))/2;
        double b=new Quantity<>(10.0,LengthUnit.FEET).divide(new Quantity<>(4.0,LengthUnit.FEET));
        assertEquals(a,b,1e-2);
    }

    @Test
    void testSubtractionAndDivision_Integration() {
        double result=new Quantity<>(10.0,LengthUnit.FEET).subtract(new Quantity<>(2.0,LengthUnit.FEET)).divide(new Quantity<>(4.0,LengthUnit.FEET));
        assertEquals(2.0,result,1e-2);
    }

    @Test
    void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> a=new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> b=new Quantity<>(2.0,LengthUnit.FEET);
        assertEquals(a.getValue(),a.add(b).subtract(b).getValue(),1e-2);
    }

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> a=new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> b=a.subtract(new Quantity<>(2.0,LengthUnit.FEET));
        assertNotSame(a,b);
    }

    @Test
    void testDivision_Immutability() {
        Quantity<LengthUnit> a=new Quantity<>(5.0,LengthUnit.FEET);
        double b=a.divide(new Quantity<>(5.0,LengthUnit.FEET));
        assertEquals(1.0,b,1e-2);
    }

    @Test
    void testSubtraction_PrecisionAndRounding() {
        assertEquals(0.33,new Quantity<>(1.0,LengthUnit.FEET).subtract(new Quantity<>(8.04,LengthUnit.INCH)).getValue(),1e-2);
    }

    @Test
    void testDivision_PrecisionHandling() {
        assertEquals(0.3333,new Quantity<>(1.0,LengthUnit.FEET).divide(new Quantity<>(3.0,LengthUnit.FEET)),1e-3);
    }
}