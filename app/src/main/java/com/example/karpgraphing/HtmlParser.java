package com.example.karpgraphing;

public class HtmlParser {
    public static String Parser(String fun){
        String parsed ="";
        for(int i=0; i<fun.length(); i++){
            if (fun.charAt(i) == '*')
                parsed += '·';
            else if(fun.charAt(i) == '^'){
                parsed +="<sup><small>";
                i+=2;
                while(fun.charAt(i) != ')'){
                    parsed += fun.charAt(i);
                    i++;
                }
                parsed +="</small></sup>";
            }
            else if(fun.charAt(i) == '§')
                parsed += MathFont.s + MathFont.i + MathFont.n;
            else if(fun.charAt(i) == '±')
                parsed += MathFont.c + MathFont.o + MathFont.s;
            else if(fun.charAt(i) == '#')
                parsed += MathFont.t + MathFont.a + MathFont.n;
            else
                parsed += fun.charAt(i);
        }
        return parsed;
    }
}
