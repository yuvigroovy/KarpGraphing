package com.example.karpgraphing;


import android.graphics.Color;
import android.graphics.Paint;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Driver {
    private ArrayList<LineGraphSeries<DataPoint>> functions;
    private LineGraphSeries<DataPoint> func;
    private Color[] colors;

    public Driver(){
        this.functions = new ArrayList<LineGraphSeries<DataPoint>>();
        this.func = new LineGraphSeries<DataPoint>();
        //colors = new Color[]{new Color.BLUE,};

    }

    public LineGraphSeries<DataPoint> insertFunction(String fun){
        Expression expression = new Expression(fun);
        Points points = new Points(expression);
        points.generatePoints((-50.0),50.0,2);
        points.getSeries().setThickness(8);
        points.getSeries().setColor(Color.RED);
        functions.add(points.getSeries());
        return points.getSeries();
    }

}
