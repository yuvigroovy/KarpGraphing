package com.example.karpgraphing;


import static android.graphics.Color.parseColor;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Driver {
    private ArrayList<LineGraphSeries<DataPoint>> functions;
    private ArrayList<String> funcNames;
    private int[] colors;
    private int colorToInsert;
    private int countFunc;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Driver(){
        this.functions = new ArrayList<LineGraphSeries<DataPoint>>();
        funcNames = new ArrayList<String>();
        this.countFunc = 0;
        colors = new int[]{
                parseColor("#FF6363"), // color red
                parseColor("#BAFFB4"), // color green
                parseColor("#548CFF"), // color blue
                parseColor("#F58634"), // color orange
                parseColor("#0A1D37"), // color black
        };
    }

    public LineGraphSeries<DataPoint> insertFunction(String fun){
        funcNames.add(fun);
        colorToInsert = (countFunc++) % this.colors.length;
        Points points = new Points(fun);
        points.generatePoints((-50.0),50.0);
        points.getSeries().setThickness(8);
        points.getSeries().setColor(colors[colorToInsert]);
        functions.add(points.getSeries());
        return points.getSeries();
    }

    public void clearFunctions(){
        this.countFunc =0;
        this.functions.clear();
    }

    public String getNameLastIndex(){
        return funcNames.get(funcNames.size()-1);
    }

    public int getNumOfFunctions(){
        return this.countFunc;
    }

    public int getColorToInsert(){
        return this.colorToInsert;
    }
}
