
package com.source.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for the simple calculator.
 */
public class ParseTest 
{
    @Test 
    public void testParseConstants(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("3");
        assertEquals(3, test.resolve(),0);
    }

    @Test 
    public void testParseConstantParenthesis(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("(3)");
        assertEquals(3, test.resolve(),0);
    }

    @Test 
    public void testParseAdd(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("3+5");
        assertEquals(8, test.resolve(),0);
    }
    
    @Test 
    public void testParseAddParenthesis(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("(3+5)");
        assertEquals(8, test.resolve(),0);
    }

    @Test 
    public void testParseSub(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("3-5");
        assertEquals(-2, test.resolve(),0);
    }
    
    @Test 
    public void testParseMult(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("3*5");
        assertEquals(15, test.resolve(),0);
    }

    @Test 
    public void testParseDiv(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("3/5");
        assertEquals(0.6, test.resolve(),0);
    }

    @Test 
    public void testOrderOfOps1(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("15/3+6");
        assertEquals(11, test.resolve(),0);
    }

    @Test 
    public void testOrderOfOps2(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("18/(3+6)");
        assertEquals(2, test.resolve(),0);
    }

    @Test 
    public void testOrderOfOps3(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("12/(3/6+(3.5))");
        assertEquals(3, test.resolve(),0);
    }

    @Test 
    public void testOrderOfOps4(){
        Parser trial = new Parser();
        ArithmeticNode test = trial.StringToOpTree("12+(0.5+((3.5)-4))");
        assertEquals(12, test.resolve(),0);
    }

}