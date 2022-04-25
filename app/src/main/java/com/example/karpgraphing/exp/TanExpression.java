package com.example.karpgraphing.exp;

public class TanExpression extends BaseExpression{

    private BaseExpression a;

    public TanExpression(BaseExpression a){
        this.a = a;
    }

    @Override
    public double solve(NameSpace ns) {
        return Math.tan(a.solve(ns));
    }

    @Override
    public String toPlainString() {
        return "'" + a.toPlainString();
    }
}
