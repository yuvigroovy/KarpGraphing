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

    private static final Pattern brackets = Pattern.compile("^~$");
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

    private static BaseExpression parsedBr;

    public static BaseExpression parse(String exp) {
        Matcher m;

        if(exp.contains("(")){
            int countBr=0, start=0,  stop=0;
            String br="";
            for (int i = 0; i < exp.length(); i++) {
                if(exp.charAt(i) == ')') {
                    countBr--;
                    if(countBr == 0)
                        stop = i;
                }
                if(countBr > 0)
                    br += exp.charAt(i);
                if(exp.charAt(i) == '(') {
                    countBr++;
                    if(countBr == 1)
                        start = i;
                }
            }

            parsedBr = parse(br);

            String post = (String) exp.subSequence(stop+1, exp.length());
            String pre = (String) exp.subSequence(0, start);

            exp = pre + "~" + post;
        }

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

        // special characters

        // CONSTANT
        m = constant.matcher(exp);
        if(m.matches())
            return new Constant(exp);

        // VARIABLE
        m = var.matcher(exp);
        if(m.matches())
            return new Variable(exp);
        //BRACKETS
        m = brackets.matcher(exp);
        if(m.matches())
            return parsedBr;

        //Unary functions

        throw new RuntimeException("Invalid expression");
    }




}
