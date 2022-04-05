package com.example.karpgraphing.exp;

public class Constant extends BaseExpression {
    private double num;

    Constant(String num) {
        this.num = Double.parseDouble(num);
    }

    @Override
    public double solve(NameSpace ns) {
        return this.num;
    }

    @Override
    public String toHtmlString() {
        return String.valueOf(num);
    }

    @Override
    public String toPlainString() {
        return String.valueOf(num);
    }
}
