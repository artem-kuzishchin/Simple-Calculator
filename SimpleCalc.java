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
         double result = evaluate(addTest);
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

    // if the inputs are all constants, 
    // uses the "nodeType" to carry out the fitting operation,
    // then changes its nodeType to "constant" for evaluation further up the tree.
    private static double evaluate(ArithmeticNode root){
        double[] inputs = {0,0};
        if(!root.isConst()){
            for(int i = 0; i < 2 ; i++){
                inputs[i] = evaluate(root.getArg(i));
            }
        }
        
        NodeType rootType = root.getType();
        switch(rootType){
            case CONST:
                return root.getVal();
            case ADD:
                return inputs[0]+inputs[1];
            case SUB:
                return inputs[0]-inputs[1];
            case MULT:
                return inputs[0]*inputs[1];
            case DIV:
                return inputs[0]/inputs[1];
        }

        // For detecting edge cases
        return Double.NaN;
        
        
    }

    
    public static ArithmeticNode createOperation(ArithmeticNode val1,ArithmeticNode val2, NodeType operation){
        ArrayList<ArithmeticNode> inputs = new ArrayList<ArithmeticNode>(2);
        inputs.add(val1);
        inputs.add(val2);
        return new ArithmeticNode(0, operation , inputs);
    }

    public static ArithmeticNode createConstantNode(double val){
        System.out.println(val);
        return new ArithmeticNode(val, NodeType.CONST , null);
    }


}
