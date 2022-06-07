/*
public class Parser {

    // A recursive parsing algorithm for turning a string into a tree of arithmetic operations.
    // It builds addition first, then subtraction, then multiplication/division.
    // Evaluation of expressions runs bottom-up, so we're constructing our tree
    // in the opposite order of operations.
    // Example:
    //      3400/2 - 3 + 10*5
    //      (3400/2 - 3) + (10*5)
    //      The root arithmeticNode has type "+" and inputs (3400/2-3), 10*5
    //      [(3400/2) - (3)] + [(10)*(5)]
    //      { ( [3400] / [2] ) - [3] } + { [10] * [5] }
    // Here, square brackets denote constants, and the parentheses/curly braces mark the boundaries of 
    // ArithmeticNode objects.
    public static ArithmeticNode StringToOpTree(String input){
        ArithmeticNode vertex = new ArithmeticNode(0, "null", null);
        // Loop through the input string to look for mathematical operations
        boolean constantExpression = true;
        int multIndex= 0;
        int subtractionIndex =0;
        boolean multFound = false;
        boolean additionFound = false;
        boolean subtractionFound = false;

        // Loop through the string.
        for(int i = 0 ; i < input.length(); i++){

            // If a multiplicative operation is found, record where it is,.
            if(input.charAt(i) == '*' || input.charAt(i)== '/'){
                multIndex = i;
                multFound = true;
                constantExpression = false;
            } 

            // Similar for subtraction.
            if(input.charAt(i) == '-'){
                subtractionIndex = i;
                subtractionFound = true;
                constantExpression = false;
            }
            
            // If addition is found, make the first arithmetic node:
            // Addition is treated as breaking the string in half:
            // (first part of string) + (last part of string)
            // Once this is done, break the loop.
            // This break is sufficient because the 'addArgs' call continues to populate 
            // the tree on lower levels, and further parsing of the input string is not needed.
            if(input.charAt(i) == '+'){
                additionFound = true;
                constantExpression = false;
                String leftIn = input.substring(0, i);
                String rightIn = input.substring(i+1);
                vertex = addArgs(leftIn, rightIn, Character.toString(input.charAt(i)), vertex);
                break;
            }
        }

        // If addition was not found, but subtraction was, we have only subtraction operations in our string.
        // If only subtraction exists, then we have
        // (first part of string) - a - b - c -....
        // which is equivalent to
        // (first part of string) - (a+b+c + ...)
        // So when handling subtraction, we make an arithmetic node where all the signs in the 
        // right half of the string are flipped. 
        if(!additionFound && subtractionFound){
            int i = subtractionIndex;
            String leftIn = input.substring(0, i);
            String rightIn = input.substring(i+1);
            rightIn.replace('-','+');
            System.out.println(leftIn);
            System.out.println(rightIn);
            vertex = addArgs(leftIn, rightIn, Character.toString(input.charAt(i)), vertex);
        }

        // If neither addition or subtraction were found, 
        // then we have only multiplication and division remaining.
        // We parenthesize a*b*c as (a*b)*c
        if(multFound && !additionFound && !subtractionFound){
            int i = multIndex;
            // We start with the positions directly to the left and right of the operator
            String leftIn = input.substring(0, i);
            String rightIn = input.substring(i+1);
            vertex = addArgs(leftIn, rightIn, Character.toString(input.charAt(i)), vertex);

            
        }

        // If we go through that loop and find no operators, we're at the bottom of our recursion,
        // and thus have a string we can read as a numerical constant.
        if(constantExpression == true){
            vertex.value= Double.parseDouble(input);
            vertex.nodeType = "const";
        }
        return vertex; 

    }


    // Takes an ArithmeticNode with the intention to give it two subnodes, based on which operation is happening where.
    // For example, 
    // LeftArg = "3+4", RightArg = "5-6", opType = '/'
    // This creates an arithmetic node dividing the left input by the right.
    public static ArithmeticNode addArgs(String leftArg, String rightArg, String opType, ArithmeticNode root){
        ArithmeticNode left = StringToOpTree(leftArg);
        ArithmeticNode right = StringToOpTree(rightArg);
        root.addInput(left);
        root.addInput(right);
        root.nodeType = opType;
        return root;
    }


    public static boolean isOperation(char toCheck){
        char [] validOps = {'+','-','/','*'};
        for(int i = 0; i < validOps.length; i++){
            if(toCheck == validOps[i]){
                return true;
            }
        }
        return false;
    }

}
 */ 
