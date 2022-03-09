package com.example.karpgraphing.exp;

import com.example.karpgraphing.MathFont;

public class LogExpression extends Expression{
    @Override
    public double solve(NameSpace ns) {
         return Math.log10(b.solve(ns))/Math.log10(a.solve(ns));
    }

    @Override
    public String toPlainString() {
        return a.toPlainString() + "!" + b.toPlainString();
    }

    @Override
    public String toHtmlString() {
        return a.toHtmlString() + MathFont.l +MathFont.o + MathFont.g + "<sub><small>" + b.toHtmlString() + "</small></sub>";
    }
}
