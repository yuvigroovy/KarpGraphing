package com.example.karpgraphing.exp;

public class CosExpression extends BaseExpression{

    private BaseExpression a;

    public CosExpression(BaseExpression a){
        this.a = a;
    }

    @Override
    public double solve(NameSpace ns) {
        return Math.cos(a.solve(ns));
    }

    @Override
    public String toPlainString() {
        return "±" + a.toPlainString();
    }
}
