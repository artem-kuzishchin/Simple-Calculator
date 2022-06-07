import java.util.ArrayList;

public class ArithmeticNode {
    // For constants
    private double value; 
    // "const" means constant
    // "+" means addition, ect
    private NodeType nodeType;

    private ArrayList<ArithmeticNode> inputs;

    public ArithmeticNode(double value, NodeType nodeType, ArrayList<ArithmeticNode> inputs){
        this.value = value;
        this.nodeType = nodeType;
        if(inputs != null){
            this.inputs = inputs;
        }else{
            this.inputs = new ArrayList<ArithmeticNode>(2);
        }
        
    }

    public void addInput(ArithmeticNode toAdd){
        this.inputs.add(toAdd);
    }

   
    public boolean isConst(){
        if(this.nodeType == NodeType.CONST){
            return true;
        }
        return false;
    }

    public double getVal(){
        return this.value;
    }

    public ArithmeticNode getArg(int index){
        return this.inputs.get(index);
    }

    public NodeType getType(){
        return this.nodeType;
    }

}
