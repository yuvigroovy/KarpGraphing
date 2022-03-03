package com.example.karpgraphing.exp;

public class Variable implements BaseExpression {
    String var;
    public Variable(String var){
        this.var = var;
    }

    @Override
    public double solve(NameSpace ns) {
        return ns.substitute(var);
    }
}