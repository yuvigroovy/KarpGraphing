package com.example.karpgraphing.exp;

public class Constant implements BaseExpression {
    private double num;

    Constant(double num) {
        this.num = num;
    }

    @Override
    public double solve(NameSpace ns) {
        return this.num;
    }
}
