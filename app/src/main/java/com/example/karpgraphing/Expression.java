package com.example.karpgraphing;
/***************************************************************************
             _   __                   _____                 _     _             
            | | / /                  |  __ \               | |   (_)            
           | |/ /  __ _ _ __ _ __   | |  \/_ __ __ _ _ __ | |__  _ _ __   __ _ 
          |    \ / _` | '__| '_ \  | | __| '__/ _` | '_ \| '_ \| | '_ \ / _` |
         | |\  \ (_| | |  | |_) | | |_\ \ | | (_| | |_) | | | | | | | | (_| | 
        |_| \_/\__,_|_|  | .__/   \____/_|  \__,_| .__/|_| |_|_|_| |_|\__, |
                        | |                     | |                   __/ |
                        |_|                     |_|                  |___/ 

***************************************************************************/

import java.util.Stack;
import java.util.ArrayList;
import java.lang.Character;


public class Expression{


    ArrayList<Node> nodes;

    public Expression() {
        nodes = new ArrayList<Node>();
    }

    public Expression(String s) {
        // parse the expression in the string
        nodes = new ArrayList<Node>();

        String number = "";
        for(char c : s.toCharArray()){
            if(Character.isDigit(c) || c == '.'){
                number += c;
            }
            else {
                // current char is an operator or a variable
                if (Character.isLetter(c)){
                    //current char is a letter
                    addVariable(new Variable(c));
                }
                else{
                    if (!number.isEmpty()){
                        //current char is an operator
                        addNumber(new Number(Double.parseDouble(number)));
                        number = "";
                    }
                    addOperator(new Operation(c));
                }
            }
        }

        if (!number.isEmpty()) addNumber(new Number(Double.parseDouble(number)));
    }
    
    public String toString() {
        String s = "";
        for (Node node: nodes) s+= node.toString();
        return s;
    }

    public void addNumber(Number num) {
        nodes.add(num);
    }

    public void addOperator(Operation op) {
        nodes.add(op);
    }

    public void addVariable(Variable var){
        nodes.add(var);
    }

    
    //convert an infix expression to postfix 
    //  using full shunting algorithm 
    public Expression infixToPostfix() {
        Expression postfix = new Expression();
        Stack<Operation> ops = new Stack<Operation>();
        for(Node node : nodes)
        {
            if(node instanceof Operation){
                if(ops.isEmpty() || ((Operation)node).compare(ops.peek()))
                    ops.push((Operation) node);
                else if(!((Operation)node).compare(ops.peek())){
                    postfix.addOperator(ops.pop());
                    ops.push((Operation)node);
                }
            }
            else if(node instanceof Number)
                postfix.addNumber((Number)node);
            else
                postfix.addVariable((Variable) node);
        }
        while(!ops.isEmpty())
        {
            postfix.addOperator((Operation)ops.pop());
        }
        return postfix;
    }


    public double solvePostfix() {
        Stack<Number> nums = new Stack<Number>();
        for(Node node : nodes){
            if(node instanceof Number){
                //node is a number
                nums.push((Number)node);
            } else{
                Operation op = (Operation)node;
                if(!nums.isEmpty()) {
                    Number num1 = nums.pop();
                    if(!nums.isEmpty()){
                        Number num2 = nums.pop();
                        nums.push(new Number(op.calc(num2, num1)));
                    }
                    else {
                        Operation sub = new Operation('*');
                        nums.push(new Number(sub.calc(new Number(-1.0), num1)));
                    }
                }

            }
        }
        return nums.pop().getNumber();
    }

    public Expression substitute(double x, char c)
    {
        Expression subExpression = new Expression();
        for(Node node : nodes){
            if(node instanceof Variable){
                Variable var = (Variable)node;
                if(var.getLetter() == c)
                    subExpression.addNumber(new Number(x));
            }
            else if(node instanceof Number)
                subExpression.addNumber((Number)node);
            else
                subExpression.addOperator((Operation)node);
        }
        return subExpression;
    }
               
}