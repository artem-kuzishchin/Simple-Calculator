package com.source.app;

public class Operation implements ArithmeticNode{
    private NodeType opType;
    private ArithmeticNode leftArg;
    private ArithmeticNode rightArg;
    private OpAlgorithm evaluator;

    public Operation(ArithmeticNode leftArg, ArithmeticNode rightArg,NodeType opType, OpAlgorithm evaluator){
        this.opType = opType;
        this.leftArg = leftArg;
        this.rightArg = rightArg;
        this.evaluator = evaluator;
    }

    public double resolve(){
    	return this.evaluator.evaluate(this.leftArg,this.rightArg,this.opType);
        
    }
}
