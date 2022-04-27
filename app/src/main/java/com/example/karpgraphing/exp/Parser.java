package com.example.karpgraphing.exp;

import java.util.regex.*;

public class Parser {

    //binary functions
    private static final Pattern sub = Pattern.compile("^\\s*(\\S[^-]*)-\\s*(\\S.*)$"); //every expression that contains '-'
    private static final Pattern add = Pattern.compile("^\\s*(\\S[^+]*)\\+\\s*(\\S.*)$"); //every expression that contains '+'
    private static final Pattern mul = Pattern.compile("^\\s*(\\S[^*]*)\\*\\s*(\\S.*)$"); //every expression that contains '*'
    private static final Pattern div = Pattern.compile("^\\s*(\\S[^/]*)/\\s*(\\S.*)$"); //every expression that contains '/'
    private static final Pattern pow = Pattern.compile("^\\s*(\\S[^\\^]*)\\^\\s*(\\S.*)$"); //every expression that contains '^'
    private static final Pattern log = Pattern.compile("^\\s*(\\S[^!]*)!\\s*(\\S.*)$"); //every expression that contains '!'

    //Unary functions
    public static final Pattern sin = Pattern.compile("^\\s*§\\s*(\\S.*)$");
    public static final Pattern cos = Pattern.compile("^\\s*±\\s*(\\S.*)$");
    public static final Pattern tan = Pattern.compile("^\\s*#\\s*(\\S.*)$");

    //special characters
    private static final Pattern brackets = Pattern.compile("^~$");
    private static final Pattern constant = Pattern.compile("[0-9]+");
    private static final Pattern var = Pattern.compile("^[a-zA-Z]*$");

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

        //Unary Functions
        //Sin
        m = sin.matcher(exp);
        if(m.matches())
            return new SinExpression(parse(m.group(1)));

        //Cos
        m = cos.matcher(exp);
        if(m.matches())
            return new CosExpression(parse(m.group(1)));

        //Tan
        m = tan.matcher(exp);
        if(m.matches())
            return new TanExpression(parse(m.group(1)));

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

        throw new RuntimeException("Invalid expression");
    }




}
