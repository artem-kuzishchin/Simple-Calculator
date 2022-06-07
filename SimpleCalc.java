import java.util.ArrayList;

public class SimpleCalc {
    public static void main(String[] args) throws Exception {

        ArithmeticNode a = createConstantNode(35);
        ArithmeticNode b = createConstantNode(5);

        // Test 35+5 
        testOp(a, b, NodeType.ADD, 1, 40);

        // Test 35-5
        testOp(a, b, NodeType.SUB, 2, 30 );

        // Test 35*5 
        testOp(a, b, NodeType.MULT, 3, 175);

        // Test 35/5
        testOp(a, b, NodeType.DIV, 4, 7);

        // 35 + 5
        ArithmeticNode test1 = createOperation(a, b, NodeType.ADD);
        // 35/5
        ArithmeticNode test2 = createOperation(a, b, NodeType.DIV);
        // (35+5) - (35/5)
        testOp(test1, test2, NodeType.SUB, 5, 33);


    }


    public static void testOp(ArithmeticNode a, ArithmeticNode b, NodeType operation, int testNum, double goal){
         // Test (a OP b)
         ArithmeticNode addTest = createOperation(a, b, operation);
         System.out.println("Test " + testNum +":");
         System.out.println("Expecting : "+ goal);
         double result = addTest.evaluate();
         System.out.println("Result: " + result);
         printTestResult(result, goal, testNum);
         System.out.println();
    }

    public static void printTestResult(double result, double goal, int testNum){
        if (result == goal){
            System.out.println("Test " + testNum + " passed.");
        } else {
            System.out.println("Test " + testNum + " FAILED.");
        }
    }
    
    public static ArithmeticNode createOperation(ArithmeticNode val1,ArithmeticNode val2, NodeType operation){
        ArrayList<ArithmeticNode> inputs = new ArrayList<ArithmeticNode>(2);
        inputs.add(val1);
        inputs.add(val2);
        return new Operation(operation , inputs);
    }

    public static ArithmeticNode createConstantNode(double val){
        return new Constant(val);
    }


}
