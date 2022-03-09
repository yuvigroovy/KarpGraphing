package com.example.karpgraphing.exp;

import java.util.LinkedList;
import java.util.Queue;

public class Parser {
    private BaseExpression exp;
    private String  rawExpression;
    public Parser(String rawExpression){
        this.rawExpression = rawExpression;
    }

    private void parseFirstScope(NameSpace ns){
        int startIndex=0,endIndex=0;
        String exp = " ";
        String preExp = " ";
        String postExp = " ";
        for (int i = 0; i < this.rawExpression.length(); i++) {
            if(this.rawExpression.charAt(i) == '(') {
                startIndex = i;
                exp = "";
            }
            else if(this.rawExpression.charAt(i) == ')') {
                endIndex = i;
            }
            else
                exp += this.rawExpression.charAt(i);
        }
        preExp = this.rawExpression.substring(0,startIndex) + parseScope(exp).solve(ns);
        postExp = this.rawExpression.substring(endIndex);
        this.rawExpression = preExp + postExp;
    }

    private Expression parseScope(String exp){
        Expression parsed;
        String number = "";
        char op=' ';
        Queue<BaseExpression> numbers = new LinkedList<>();
        for (int i = 0; i < exp.length(); i++) {
            if(Character.isDigit(exp.charAt(i))) {
                number = Character.toString(exp.charAt(i));
                if(i<exp.length()-1 && exp.charAt(i+1)=='.'){
                    number += '.' + Character.toString(exp.charAt(i+2));
                    numbers.add(new Constant(Double.parseDouble(number)));
                    i += 2;
                }
                else
                    numbers.add(new Constant(Double.parseDouble(number)));
            } else if(Character.isLetter(exp.charAt(i)))
                numbers.add(new Variable(exp.charAt(i)));
            else
                op = exp.charAt(i);
        }
        switch (op) {
            case '+':
                parsed = new AddExpression(numbers.remove(), numbers.remove());
            case '-':
                parsed = new SubExpression(numbers.remove(), numbers.remove());
            case '*':
                parsed = new MulExpression(numbers.remove(), numbers.remove());
            case '/':
                parsed = new DivExpression(numbers.remove(), numbers.remove());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        }
        return parsed;
    }
}
