package com.example.karpgraphing.exp;

import java.util.regex.*;

public class Parser {
    private String stringExpression;
    private static final Pattern sub = Pattern.compile("^\\s*(\\S[^-]*)-\\s*(\\S.*)$"); //every expression that contains '-'
    private static final Pattern add = Pattern.compile("^\\s*(\\S[^+]*)\\+\\s*(\\S.*)$");
    private static final Pattern mul = Pattern.compile("^\\s*(\\S[^*]*)\\*\\s*(\\S.*)$");
    private static final Pattern div = Pattern.compile("^\\s*(\\S[^/]*)\\/\\s*(\\S.*)$");
    private static final Pattern pow = Pattern.compile("^\\s*(\\S[^^]*)\\^\\s*(\\S.*)$");
    private static final Pattern log = Pattern.compile("^\\s*(\\S[^!]*)\\!\\s*(\\S.*)$");
    private static final Pattern constant = Pattern.compile("[0-9]+");
    private static final Pattern var = Pattern.compile("^[a-zA-Z]*$");


    public static BaseExpression parse(String exp) {
        Matcher m;

        // ADD
        m = add.matcher(exp);
        if(m.matches())
            return new AddExpression(parse(m.group(1)),parse(m.group(2)));

        // SUB
        m = sub.matcher(exp);
        if (m.matches())
            return new SubExpression(parse(m.group(1)), parse(m.group(2)));

        // MUL
        m = mul.matcher(exp);
        if(m.matches())
            return new MulExpression(parse(m.group(1)), parse(m.group(2)));

        // DIV
        m = div.matcher(exp);
        if(m.matches())
            return new DivExpression(parse(m.group(1)),parse(m.group(2)));

        // POW
        m = pow.matcher(exp);
        if(m.matches())
            return new PowExpression(parse(m.group(1)),parse(m.group(2)));

        // LOG
        m = log.matcher(exp);
        if(m.matches())
            return new LogExpression(parse(m.group(1)),parse(m.group(2)));

        // CONSTANT
        m = constant.matcher(exp);
        if(m.matches())
            return new Constant(exp);

        // VARIABLE
        m = var.matcher(exp);
        if(m.matches())
            return new Variable(exp);

        throw new RuntimeException("Invalid expression");
    }




}
