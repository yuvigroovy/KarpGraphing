package com.example.karpgraphing.exp;

public class DivExpression extends Expression{
    public DivExpression(BaseExpression a, BaseExpression b){
        precedence = 2;
        this.a = a;
        this.b = b;
    }
    @Override
    public double solve(NameSpace ns) {
        return (a.solve(ns) / b.solve(ns));
    }
}
