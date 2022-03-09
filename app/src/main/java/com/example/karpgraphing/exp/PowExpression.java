package com.example.karpgraphing.exp;

public class PowExpression extends Expression{
    @Override
    public double solve(NameSpace ns) {
        return Math.pow(a.solve(ns), b.solve(ns));
    }

    @Override
    public String toPlainString() {
        return a.toPlainString() + "^" + b.toPlainString();
    }

    @Override
    public String toHtmlString() {
        return a.toHtmlString() + "<sup><small>" + b.toHtmlString() + "</small></sup>";
    }
}
