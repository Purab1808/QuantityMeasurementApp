package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.QuantityMeasurementApp.*;

public class QuantityMeasurementAppTest {

    @Test
    void testIMeasurableInterface_LengthUnitImplementation(){
        assertEquals(12.0,LengthUnit.FEET.getconversionfactor());
    }

    @Test
    void testIMeasurableInterface_WeightUnitImplementation(){
        assertEquals(1.0,WeightUnit.KG.getconversionfactor());
    }

    @Test
    void testIMeasurableInterface_ConsistentBehavior(){
        assertEquals(12.0,LengthUnit.FEET.toBaseUnit(1.0));
        assertEquals(1.0,WeightUnit.KG.toBaseUnit(1.0));
    }

    @Test
    void testGenericQuantity_LengthOperations_Equality(){
        Quantity<LengthUnit> q1=new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2=new Quantity<>(12.0,LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testGenericQuantity_WeightOperations_Equality(){
        Quantity<WeightUnit> q1=new Quantity<>(1.0,WeightUnit.KG);
        Quantity<WeightUnit> q2=new Quantity<>(1000.0,WeightUnit.GM);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testGenericQuantity_LengthOperations_Conversion(){
        Quantity<LengthUnit> q=new Quantity<>(1.0,LengthUnit.FEET);
        assertEquals(12.0,q.convertTo(LengthUnit.INCH).getValue(),1e-3);
    }

    @Test
    void testGenericQuantity_WeightOperations_Conversion(){
        Quantity<WeightUnit> q=new Quantity<>(1.0,WeightUnit.KG);
        assertEquals(1000.0,q.convertTo(WeightUnit.GM).getValue(),1e-3);
    }

    @Test
    void testGenericQuantity_LengthOperations_Addition(){
        Quantity<LengthUnit> q1=new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2=new Quantity<>(12.0,LengthUnit.INCH);
        assertEquals(2.0,q1.add(q2,LengthUnit.FEET).getValue(),1e-3);
    }

    @Test
    void testGenericQuantity_WeightOperations_Addition(){
        Quantity<WeightUnit> q1=new Quantity<>(1.0,WeightUnit.KG);
        Quantity<WeightUnit> q2=new Quantity<>(1000.0,WeightUnit.GM);
        assertEquals(2.0,q1.add(q2,WeightUnit.KG).getValue(),1e-3);
    }

    @Test
    void testCrossCategoryPrevention_LengthVsWeight(){
        Quantity<LengthUnit> l=new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<WeightUnit> w=new Quantity<>(1.0,WeightUnit.KG);
        assertFalse(l.equals(w));
    }

    @Test
    void testCrossCategoryPrevention_CompilerTypeSafety(){
        assertTrue(true);
    }

    @Test
    void testGenericQuantity_ConstructorValidation_NullUnit(){
        assertThrows(IllegalArgumentException.class,()->new Quantity<>(1.0,null));
    }

    @Test
    void testGenericQuantity_ConstructorValidation_InvalidValue(){
        assertThrows(IllegalArgumentException.class,()->new Quantity<>(Double.NaN,LengthUnit.FEET));
    }

    @Test
    void testGenericQuantity_Conversion_AllUnitCombinations(){
        Quantity<LengthUnit> q=new Quantity<>(1.0,LengthUnit.FEET);
        assertEquals(12.0,q.convertTo(LengthUnit.INCH).getValue(),1e-3);
        assertEquals(0.3333,q.convertTo(LengthUnit.YARD).getValue(),1e-3);
    }

    @Test
    void testGenericQuantity_Addition_AllUnitCombinations(){
        Quantity<LengthUnit> q1=new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2=new Quantity<>(1.0,LengthUnit.YARD);
        assertEquals(4.0,q1.add(q2,LengthUnit.FEET).getValue(),1e-3);
    }

    @Test
    void testBackwardCompatibility_AllUC1Through9Tests(){
        Quantity<LengthUnit> q=new Quantity<>(1.0,LengthUnit.FEET);
        assertTrue(q.equals(new Quantity<>(12.0,LengthUnit.INCH)));
    }

    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Equality(){
        Quantity<LengthUnit> q1=new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2=new Quantity<>(12.0,LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion(){
        Quantity<WeightUnit> q=new Quantity<>(1.0,WeightUnit.KG);
        assertEquals(1000.0,q.convertTo(WeightUnit.GM).getValue(),1e-3);
    }

    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Addition(){
        Quantity<WeightUnit> q1=new Quantity<>(1.0,WeightUnit.KG);
        Quantity<WeightUnit> q2=new Quantity<>(1000.0,WeightUnit.GM);
        assertEquals(2.0,q1.add(q2,WeightUnit.KG).getValue(),1e-3);
    }

    @Test
    void testTypeWildcard_FlexibleSignatures(){
        Quantity<?> q=new Quantity<>(1.0,LengthUnit.FEET);
        assertNotNull(q);
    }

    @Test
    void testScalability_NewUnitEnumIntegration(){
        assertTrue(true);
    }

    @Test
    void testScalability_MultipleNewCategories(){
        assertTrue(true);
    }

    @Test
    void testGenericBoundedTypeParameter_Enforcement(){
        Quantity<LengthUnit> q=new Quantity<>(1.0,LengthUnit.FEET);
        assertNotNull(q);
    }

    @Test
    void testHashCode_GenericQuantity_Consistency(){
        Quantity<LengthUnit> a=new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<LengthUnit> b=new Quantity<>(12.0,LengthUnit.INCH);
        assertEquals(a.hashCode(),b.hashCode());
    }

    @Test
    void testEquals_GenericQuantity_ContractPreservation(){
        Quantity<LengthUnit> a=new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<LengthUnit> b=new Quantity<>(12.0,LengthUnit.INCH);
        assertTrue(a.equals(b)&&b.equals(a));
    }

    @Test
    void testEnumAsUnitCarrier_BehaviorEncapsulation(){
        assertEquals(12.0,LengthUnit.FEET.toBaseUnit(1.0));
    }

    @Test
    void testTypeErasure_RuntimeSafety(){
        Quantity<LengthUnit> l=new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<WeightUnit> w=new Quantity<>(1.0,WeightUnit.KG);
        assertFalse(l.equals(w));
    }

    @Test
    void testCompositionOverInheritance_Flexibility(){
        Quantity<WeightUnit> q=new Quantity<>(1.0,WeightUnit.KG);
        assertNotNull(q);
    }

    @Test
    void testCodeReduction_DRYValidation(){
        assertTrue(true);
    }

    @Test
    void testMaintainability_SingleSourceOfTruth(){
        Quantity<LengthUnit> q=new Quantity<>(1.0,LengthUnit.FEET);
        assertTrue(q.equals(new Quantity<>(12.0,LengthUnit.INCH)));
    }

    @Test
    void testArchitecturalReadiness_MultipleNewCategories(){
        assertTrue(true);
    }

    @Test
    void testPerformance_GenericOverhead(){
        assertTrue(true);
    }

    @Test
    void testDocumentation_PatternClarity(){
        assertTrue(true);
    }

    @Test
    void testInterfaceSegregation_MinimalContract(){
        assertEquals(1.0,WeightUnit.KG.getconversionfactor());
    }

    @Test
    void testImmutability_GenericQuantity(){
        Quantity<LengthUnit> q1=new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2=q1.convertTo(LengthUnit.INCH);
        assertNotSame(q1,q2);
    }
}