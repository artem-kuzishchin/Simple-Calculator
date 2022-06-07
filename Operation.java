import java.util.ArrayList;

public class Operation implements ArithmeticNode{
    private NodeType opType;
    private ArrayList<ArithmeticNode> inputs;

    public Operation(NodeType opType, ArrayList<ArithmeticNode> inputs){
        this.opType = opType;
        this.inputs = inputs;
    }

    public double evaluate(){
        double[] inputs = {0,0};
        for(int i = 0; i < 2; i++){
            inputs[i] = this.inputs.get(i).evaluate();
        }
        switch(this.opType){
            case ADD:
                return inputs[0]+inputs[1];
            case SUB:
                return inputs[0]-inputs[1];
            case MULT:
                return inputs[0]*inputs[1];
            case DIV:
                return inputs[0]/inputs[1];
        }

        return Double.NaN;
    }
}
