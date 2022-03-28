package com.example.karpgraphing.exp;

public class AddExpression extends Expression{
    public AddExpression(BaseExpression a, BaseExpression b){
        this.a = a;
        this.b = b;
    }

    @Override
    public double solve(NameSpace ns) {
        return a.solve(ns) + b.solve(ns);
    }

    @Override
    public String toHtmlString() {
        return a.toHtmlString() + "+" + b.toHtmlString();
    }

    @Override
    public String toPlainString() {
        return a.toPlainString() + "+" + b.toPlainString();
    }
}
