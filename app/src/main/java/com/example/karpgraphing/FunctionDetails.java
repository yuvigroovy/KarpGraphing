package com.example.karpgraphing;

public class FunctionDetails {
    private String fun;
    private int color;
    private String colorName;

    public FunctionDetails(String fun, int color, String colorName) {
        this.fun = fun;
        this.color = color;
        this.colorName = colorName;
    }

    public String getFun() {
        return fun;
    }

    public int getColor() {
        return color;
    }

    public String getColorName(){
        return this.colorName;
    }
}
