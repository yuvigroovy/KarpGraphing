package com.example.karpgraphing.exp;

public class Variable extends BaseExpression {
    String var;
    public Variable(String var){
        this.var = var;
    }

    @Override
    public double solve(NameSpace ns) {
        return ns.substitute();
    }

    @Override
    public String toPlainString() {
        return var;
    }

    @Override
    public String toHtmlString() {
        return var;
    }
}
