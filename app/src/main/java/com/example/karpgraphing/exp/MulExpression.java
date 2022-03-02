package com.example.karpgraphing.exp;

public class MulExpression extends Expression{
    public MulExpression(BaseExpression a, BaseExpression b){
        this.a = a;
        this.b = b;
    }
    @Override
    public double solve(NameSpace ns) {
        return (a.solve(ns) * b.solve(ns));
    }
}
