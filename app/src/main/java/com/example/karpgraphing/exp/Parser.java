package com.example.karpgraphing.exp;

import java.util.regex.*;

public class Parser {
    private String stringExpression;
    private static final Pattern sub = Pattern.compile("^\\s*(\\S[^-]*)-\\s*(\\S.*)$"); //every expression that contains '-'
    private static final Pattern add = Pattern.compile("^\\s*(\\S[^+]*)\\+\\s*(\\S.*)$");
    private static final Pattern mul = Pattern.compile("^\\s*(\\S[^*]*)\\*\\s*(\\S.*)$");
    private static final Pattern div = Pattern.compile("^\\s*(\\S[^/]*)/\\s*(\\S.*)$");
    private static final Pattern pow = Pattern.compile("^\\s*(\\S[^\\^]*)\\^\\s*(\\S.*)$");
    private static final Pattern log = Pattern.compile("^\\s*(\\S[^!]*)!\\s*(\\S.*)$");

    private static final Pattern constant = Pattern.compile("[0-9]+");
    private static final Pattern var = Pattern.compile("^[a-zA-Z]*$");

    private static final Pattern sub_front = Pattern.compile("^\\s*(\\S[^-]*)-\\s*$");
    private static final Pattern add_front = Pattern.compile("^\\s*(\\S[^+]*)\\+\\s*$");
    private static final Pattern mul_front = Pattern.compile("^\\s*(\\S[^*]*)\\*\\s*$");
    private static final Pattern div_front = Pattern.compile("^\\s*(\\S[^/]*)/\\s*$");
    private static final Pattern pow_front = Pattern.compile("^\\s*(\\S[^\\^]*)\\^\\s*$");
    private static final Pattern log_front = Pattern.compile("^\\s*(\\S[^!]*)!\\s*$");

    private static final Pattern sub_back = Pattern.compile("^\\s*-\\s*(\\S.*)$");
    private static final Pattern add_back = Pattern.compile("^\\s*\\+\\s*(\\S.*)$");
    private static final Pattern mul_back = Pattern.compile("^\\s*\\*\\s*(\\S.*)$");
    private static final Pattern div_back = Pattern.compile("^\\s*/\\s*(\\S.*)$");
    private static final Pattern pow_back = Pattern.compile("^\\s*\\^\\s*(\\S.*)$");
    private static final Pattern log_back = Pattern.compile("^\\s*!\\s*(\\S.*)$");

    public static BaseExpression parse(BaseExpression parsed,String exp){
        Matcher m;

        // ADD
        m = add_back.matcher(exp);
        if(m.matches())
            return new AddExpression(parsed, parse(m.group(1)));

        // SUB
        m = sub_back.matcher(exp);
        if (m.matches())
            return new SubExpression(parsed, parse(m.group(1)));

        // MUL
        m = mul_back.matcher(exp);
        if(m.matches())
            return new MulExpression(parsed, parse(m.group(1)));

        // DIV
        m = div_back.matcher(exp);
        if(m.matches())
            return new DivExpression(parsed, parse(m.group(1)));

        // POW
        m = pow_back.matcher(exp);
        if(m.matches())
            return new PowExpression(parsed, parse(m.group(1)));

        // LOG
        m = log_back.matcher(exp);
        if(m.matches())
            return new LogExpression(parsed, parse(m.group(1)));

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

    public static BaseExpression parse(String exp, BaseExpression parsed){
        Matcher m;

        // ADD
        m = add_front.matcher(exp);
        if(m.matches())
            return new AddExpression(parse(m.group(1)),parsed);

        // SUB
        m = sub_front.matcher(exp);
        if (m.matches())
            return new SubExpression(parse(m.group(1)), parsed);

        // MUL
        m = mul_front.matcher(exp);
        if(m.matches())
            return new MulExpression(parse(m.group(1)), parsed);

        // DIV
        m = div_front.matcher(exp);
        if(m.matches())
            return new DivExpression(parse(m.group(1)),parsed);

        // POW
        m = pow_front.matcher(exp);
        if(m.matches())
            return new PowExpression(parse(m.group(1)),parsed);

        // LOG
        m = log_front.matcher(exp);
        if(m.matches())
            return new LogExpression(parse(m.group(1)),parsed);

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
