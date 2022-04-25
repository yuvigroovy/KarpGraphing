package com.example.karpgraphing.exp;

public class SinExpression extends BaseExpression{

    private BaseExpression a;

    public SinExpression(BaseExpression a){
        this.a = a;
    }

    @Override
    public double solve(NameSpace ns) {
        return Math.sin(a.solve(ns));
    }

    @Override
    public String toPlainString() {
        return "§" + a.toPlainString();
    }
}
