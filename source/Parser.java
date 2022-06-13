package com.source.app;

public class Parser {

    public Parser(){}

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

    // a/b * S / 10
    public ArithmeticNode StringToOpTree(String input){
        // If the string looks like "(CONTENT)"
        if(input.charAt(0) == '(' && input.charAt(input.length()-1)==')'){
            // Then we parse "CONTENT" without the parentheses.
            return this.StringToOpTree(input.substring(1, input.length()-1));
        }


        // Loop through the input string to look for mathematical operations
        boolean constantExpression = true;
        int multIndex= 0;
        int subtractionIndex =0;
        boolean multFound = false;
        boolean subtractionFound = false;

        // These count open and closed parentheses encountered.
        // When # open parentheses = # closed parentheses, we have a parenthetical.
        int openParCounter = 0;
        int closedParCounter = 0;

        // Loop through the string.
        // There is a return statement only if an addition is found in the input string.
        for(int i = 0 ; i < input.length(); i++){

            if(input.charAt(i) == '('){
                openParCounter++;
            }

            if(input.charAt(i) == ')'){
                closedParCounter++;
            }

            // This is true only when we are in an un-paranthesized part of the string.
            // This logic ensures that the stuff outside parentheses is closer to the root of the operation tree,
            // and is thus calculated last, as intended by Order of Ops.
            if(closedParCounter == openParCounter){
                
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
                    constantExpression = false;
                    String leftIn = input.substring(0, i);
                    String rightIn = input.substring(i+1);
                    ArithmeticNode leftArg = this.StringToOpTree(leftIn);
                    ArithmeticNode rightArg = this.StringToOpTree(rightIn);
                    return new Operation(leftArg, rightArg, NodeType.ADD, new Evaluator());
                }
            }
        }

        // If the code below is executing, the above loop did not find addition outside of a parenthetical.
        
        // If addition was not found, but subtraction was, we have only subtraction operations in our string.
        // If only subtraction exists, then we have
        // (first part of string) - a - b - c -....
        // which is equivalent to
        // (first part of string) - (a+b+c + ...)
        // So when handling subtraction, we make an arithmetic node where all the signs in the 
        // right half of the string are flipped. 
        if(subtractionFound){
            int i = subtractionIndex;
            String leftIn = input.substring(0, i);
            String rightIn = input.substring(i+1);
            rightIn.replace('-','+');
            ArithmeticNode leftArg = this.StringToOpTree(leftIn);
            ArithmeticNode rightArg = this.StringToOpTree(rightIn);
            return new Operation(leftArg, rightArg, NodeType.SUB, new Evaluator());
        }

        // If neither addition or subtraction were found, 
        // then we have only multiplication and division remaining.
        // We parenthesize a*b*c as (a*b)*c
        if(multFound && !subtractionFound){
            int i = multIndex;
            // We start with the positions directly to the left and right of the operator
            String leftIn = input.substring(0, i);
            String rightIn = input.substring(i+1);
            ArithmeticNode leftArg = this.StringToOpTree(leftIn);
            ArithmeticNode rightArg = this.StringToOpTree(rightIn);
            if(input.charAt(i) == '*'){
                return new Operation(leftArg, rightArg, NodeType.MULT, new Evaluator());
            }
            // If the char is not *, it must be / by the logic setting multFound.
            return new Operation(leftArg, rightArg, NodeType.DIV, new Evaluator());            
        }

        // If we go through that loop and find no operators, we're at the bottom of our recursion,
        // and thus have a string we can read as a numerical constant.
        if(constantExpression == true){
            return new Constant( Double.parseDouble(input));
        }


        return new Constant(Double.NaN); 

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

