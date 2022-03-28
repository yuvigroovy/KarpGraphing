package com.example.karpgraphing.exp;

public class Calc {
    private String rawExp;
    private BaseExpression exp;

    public Calc(String rawExp){
        this.rawExp = rawExp;
        this.exp = Parser.parse(rawExp);
    }

    public double solve(double x){
        return exp.solve(new DoubleNameSpace(x));
    }
}
