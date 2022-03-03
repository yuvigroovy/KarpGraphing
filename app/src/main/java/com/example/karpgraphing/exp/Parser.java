package com.example.karpgraphing.exp;

import com.example.karpgraphing.Operation;

import java.util.Stack;

public class Parser {
    private BaseExpression exp;
    private String  rawExpression;
    public Parser(String rawExpression){
        this.rawExpression = rawExpression;
    }

    public BaseExpression parse() {
        Stack<Character> ops = new Stack<Character>();
        char cur;

    }

}
