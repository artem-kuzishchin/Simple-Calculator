package com.source.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for the simple calculator.
 */
public class OperationTest 
{

    // TESTS FOR OPERATION.RESOLVE()

    @Test
    public void testBasicAdd()
    {
        ArithmeticNode a = new Constant (35);
        ArithmeticNode b = new Constant (5);
        Operation test = new Operation(a,b,NodeType.ADD, new Evaluator());
        assertEquals( 40, test.resolve(),0 );
    }

    @Test
    public void testBasicMult()
    {
        ArithmeticNode a = new Constant (35);
        ArithmeticNode b = new Constant (5);
        Operation test = new Operation(a,b,NodeType.MULT, new Evaluator());
        assertEquals( 175, test.resolve(), 0 );
    }

    @Test
    public void testBasicSub()
    {
        ArithmeticNode a = new Constant (35);
        ArithmeticNode b = new Constant (5);
        Operation test = new Operation(a,b,NodeType.SUB, new Evaluator());
        assertEquals( 30, test.resolve(), 0 );
    }
    @Test
    public void testBasicDiv(){
        ArithmeticNode a = new Constant (35);
        ArithmeticNode b = new Constant (5);
        Operation test = new Operation(a,b,NodeType.DIV, new Evaluator());
        assertEquals( 7, test.resolve(), 0 );
    }

    @Test
    public void testTreeAdd()
    {
        ArithmeticNode a = new Constant (35);
        ArithmeticNode b = new Constant (5);
        ArithmeticNode c = new Constant (23);
        ArithmeticNode d = new Constant (3);
        Operation test1 = new Operation(a, b, NodeType.ADD, new Evaluator());
        Operation test2 = new Operation(c, d, NodeType.SUB, new Evaluator());
        Operation test = new Operation(test1, test2, NodeType.DIV, new Evaluator());
        assertEquals(2, test.resolve(), 0);

    }

}
