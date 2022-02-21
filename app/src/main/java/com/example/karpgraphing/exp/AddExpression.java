package com.example.karpgraphing.exp;

public class AddExpression extends Expression{
    @Override
    public double solve(NameSpace ns) {
        return a.solve(ns) + b.solve(ns);
    }
}
