package com.example.karpgraphing.exp;

public class LogExpression extends Expression{
    public LogExpression(BaseExpression a, BaseExpression b){
        this.a = a;
        this.b = b;
    }

    @Override
    public double solve(NameSpace ns) {
         return Math.log10(b.solve(ns))/Math.log10(a.solve(ns));
    }

    @Override
    public String toPlainString() {
        return a.toPlainString() + "!" + b.toPlainString();
    }
}
