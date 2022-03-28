package com.example.karpgraphing.exp;

public class DoubleNameSpace extends NameSpace{

    private double num;

    public DoubleNameSpace(double num){
        this.num = num;
    }

    @Override
    public double substitute() {
        return num;
    }
}
