package com.source.app;

public class Evaluator implements OpAlgorithm{

	public Evaluator(){

	}

	public double evaluate(ArithmeticNode leftArg, ArithmeticNode rightArg, NodeType opType){
		double leftRes = leftArg.resolve();
		double rightRes= rightArg.resolve();
		switch(opType){
            		case ADD:
                		return leftRes+rightRes;
            		case SUB:
                		return leftRes-rightRes;
            		case MULT:
                		return leftRes*rightRes;
            		case DIV:
                		return leftRes/rightRes;
        }

		return Double.NaN;
	}
}
