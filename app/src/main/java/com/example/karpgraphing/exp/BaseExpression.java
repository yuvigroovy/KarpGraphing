package com.example.karpgraphing.exp;

public abstract class BaseExpression {
    public abstract double solve(NameSpace ns);

    public abstract String toHtmlString();
    public abstract String toPlainString();

}

