package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
    @Test
    void testAddition_SameUnit_FeetPlusFeet(){
        QuantityMeasurementApp.Length l1=new QuantityMeasurementApp.Length(1.0,QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2=new QuantityMeasurementApp.Length(2.0,QuantityMeasurementApp.Length.LengthUnit.FEET);
        assertEquals(new QuantityMeasurementApp.Length(3.0, QuantityMeasurementApp.Length.LengthUnit.FEET),
                QuantityMeasurementApp.Length.add(l1, l2, QuantityMeasurementApp.Length.LengthUnit.FEET));
    }

    @Test
    void testAddition_SameUnit_InchPlusInch(){
        QuantityMeasurementApp.Length l1=new QuantityMeasurementApp.Length(6.0,QuantityMeasurementApp.Length.LengthUnit.INCH);
        QuantityMeasurementApp.Length l2=new QuantityMeasurementApp.Length(6.0,QuantityMeasurementApp.Length.LengthUnit.INCH);
        assertEquals(new QuantityMeasurementApp.Length(12.0,QuantityMeasurementApp.Length.LengthUnit.INCH),
                QuantityMeasurementApp.Length.add(l1, l2, QuantityMeasurementApp.Length.LengthUnit.INCH));
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches(){
        QuantityMeasurementApp.Length l1=new QuantityMeasurementApp.Length(1.0,QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2=new QuantityMeasurementApp.Length(12.0,QuantityMeasurementApp.Length.LengthUnit.INCH);
        assertEquals(new QuantityMeasurementApp.Length(2.0,QuantityMeasurementApp.Length.LengthUnit.FEET),
                QuantityMeasurementApp.Length.add(l1, l2, QuantityMeasurementApp.Length.LengthUnit.FEET));
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet(){
        QuantityMeasurementApp.Length l1=new QuantityMeasurementApp.Length(1.0,QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2=new QuantityMeasurementApp.Length(12.0,QuantityMeasurementApp.Length.LengthUnit.INCH);
        assertEquals(new QuantityMeasurementApp.Length(24.0,QuantityMeasurementApp.Length.LengthUnit.INCH),
                QuantityMeasurementApp.Length.add(l1, l2, QuantityMeasurementApp.Length.LengthUnit.INCH));
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet(){
        QuantityMeasurementApp.Length l1=new QuantityMeasurementApp.Length(1.0,QuantityMeasurementApp.Length.LengthUnit.YARD);
        QuantityMeasurementApp.Length l2=new QuantityMeasurementApp.Length(3.0,QuantityMeasurementApp.Length.LengthUnit.FEET);
        assertEquals(new QuantityMeasurementApp.Length(2.0,QuantityMeasurementApp.Length.LengthUnit.YARD),
                QuantityMeasurementApp.Length.add(l1, l2, QuantityMeasurementApp.Length.LengthUnit.YARD));
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch(){
        QuantityMeasurementApp.Length l1=new QuantityMeasurementApp.Length(2.54,QuantityMeasurementApp.Length.LengthUnit.CM);
        QuantityMeasurementApp.Length l2=new QuantityMeasurementApp.Length(1.0,QuantityMeasurementApp.Length.LengthUnit.INCH);
        QuantityMeasurementApp.Length result=QuantityMeasurementApp.Length.add(l1,l2,QuantityMeasurementApp.Length.LengthUnit.CM);
        double expected=5.08;
        double epsilon=1e-3;
        double actual= result.getValue();
        assertEquals(expected,actual,epsilon);
    }

    @Test
    void testAddition_Commutativity(){
        QuantityMeasurementApp.Length l1=new QuantityMeasurementApp.Length(1.0,QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2=new QuantityMeasurementApp.Length(12.0,QuantityMeasurementApp.Length.LengthUnit.INCH);
        QuantityMeasurementApp.Length res1=QuantityMeasurementApp.Length.add(l1,l2,QuantityMeasurementApp.Length.LengthUnit.INCH);
        QuantityMeasurementApp.Length res2=QuantityMeasurementApp.Length.add(l2,l1,QuantityMeasurementApp.Length.LengthUnit.CM);
        assertEquals(res1,res2);
    }

    @Test
    void testAddition_WithZero(){
        QuantityMeasurementApp.Length l1=new QuantityMeasurementApp.Length(5.0,QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2=new QuantityMeasurementApp.Length(0.0,QuantityMeasurementApp.Length.LengthUnit.INCH);
        assertEquals(new QuantityMeasurementApp.Length(5.0, QuantityMeasurementApp.Length.LengthUnit.FEET),
                QuantityMeasurementApp.Length.add(l1, l2, QuantityMeasurementApp.Length.LengthUnit.FEET));
    }

    @Test
    void testAddition_NegativeValues(){
        QuantityMeasurementApp.Length l1=new QuantityMeasurementApp.Length(5.0,QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2=new QuantityMeasurementApp.Length(-2.0,QuantityMeasurementApp.Length.LengthUnit.FEET);
        assertEquals(new QuantityMeasurementApp.Length(3.0,QuantityMeasurementApp.Length.LengthUnit.FEET),
                QuantityMeasurementApp.Length.add(l1,l2,QuantityMeasurementApp.Length.LengthUnit.FEET));
    }

    @Test
    void testAddition_NullSecondOperand(){
        assertThrows(IllegalArgumentException.class,()->{
            QuantityMeasurementApp.Length.add(new QuantityMeasurementApp.Length(5.0,QuantityMeasurementApp.Length.LengthUnit.FEET),null,QuantityMeasurementApp.Length.LengthUnit.FEET);
        });
    }

    @Test
    void testAddition_LargeValues() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(1e6, QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(1e6, QuantityMeasurementApp.Length.LengthUnit.FEET);
        assertEquals(new QuantityMeasurementApp.Length(2e6, QuantityMeasurementApp.Length.LengthUnit.FEET),
                QuantityMeasurementApp.Length.add(l1, l2, QuantityMeasurementApp.Length.LengthUnit.FEET));
    }

    @Test
    void testAddition_SmallValues() {
        QuantityMeasurementApp.Length l1 = new QuantityMeasurementApp.Length(0.001, QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length l2 = new QuantityMeasurementApp.Length(0.002, QuantityMeasurementApp.Length.LengthUnit.FEET);
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(l1, l2, QuantityMeasurementApp.Length.LengthUnit.FEET);
        assertEquals(0.003, result.baseUnit() / 12.0, 0.0001);
    }
}