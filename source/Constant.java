package com.source.app;

public class Constant implements ArithmeticNode{
    double value;

    public Constant(double value){
        this.value = value;
    }

    public double resolve(){
        return this.value;
    }
}
