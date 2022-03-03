package com.example.karpgraphing.exp;

public class AddExpression extends Expression{
    public AddExpression(BaseExpression a, BaseExpression b){
        this.precedence = 1;
        this.a = a;
        this.b = b;
    }
    @Override
    public double solve(NameSpace ns) {
        return a.solve(ns) + b.solve(ns);
    }
}