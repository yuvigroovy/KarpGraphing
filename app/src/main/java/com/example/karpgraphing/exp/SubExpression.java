package com.example.karpgraphing.exp;

public class SubExpression extends Expression{
    @Override
    public double solve(NameSpace ns) {
        return (a.solve(ns) - b.solve(ns));
    }
}
