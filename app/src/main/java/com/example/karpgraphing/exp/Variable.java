package com.example.karpgraphing.exp;

public class Variable extends BaseExpression {
    char var;
    public Variable(char var){
        this.var = var;
    }

    @Override
    public double solve(NameSpace ns) {
        return ns.substitute(var);
    }
}
