public class Constant implements ArithmeticNode{
    double value;

    public Constant(double value){
        this.value = value;
    }

    public double evaluate(){
        return this.value;
    }
}
