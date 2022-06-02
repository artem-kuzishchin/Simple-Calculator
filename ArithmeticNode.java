import java.util.ArrayList;

public class ArithmeticNode {
    // For constants
    public double value; 
    // "const" means constant
    // "+" means addition, ect
    public String nodeType;

    public ArrayList<ArithmeticNode> inputs;

    public ArithmeticNode(double value, String nodeType, ArrayList<ArithmeticNode> inputs){
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

    // if the inputs are all constants, 
    // uses the "nodeType" to carry out the fitting operation,
    // then changes its nodeType to "constant" for evaluation further up the tree.
    public double evaluate(){
        if(this.isConst()){
            return this.value;
        }
        for(int i = 0; i < this.inputs.size() ; i++){
            if (! inputs.get(i).isConst()){
                inputs.get(i).evaluate();
            }
        }
        if(inputs.size() == 2){
            double input1 = this.inputs.get(0).value;
            double input2 = this.inputs.get(1).value;
            if(this.nodeType.equals("+")){
                this.value = input1+input2;
            }
            else if(this.nodeType.equals("-")){
                this.value = input1-input2;
            }
            else if(this.nodeType.equals("*")){
                this.value = input1*input2;
            }
            else if(this.nodeType.equals("/")){
                this.value = input1/input2;
            }
            this.nodeType = "const";
            this.inputs = null;  
        }
        return this.value;
        
        
    }

   


    public boolean isConst(){
        if(this.nodeType.equals("const")){
            return true;
        }
        return false;
    }

}
