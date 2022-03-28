package com.example.karpgraphing.exp;

public abstract class Expression extends BaseExpression{
    BaseExpression a,b;
    public Expression(BaseExpression a, BaseExpression b) {
        this.a = a;
        this.b = b;
    }

    public Expression(){
        this.a = null;
        this.b = null;
    }

}
