package com.example.karpgraphing.exp;

public class PowExpression extends Expression{
    public PowExpression(BaseExpression a, BaseExpression b){
        this.a = a;
        this.b = b;
    }

    @Override
    public double solve(NameSpace ns) {
        return Math.pow(a.solve(ns), b.solve(ns));
    }

    @Override
    public String toPlainString() {
        return a.toPlainString() + "^" + b.toPlainString();
    }


}
