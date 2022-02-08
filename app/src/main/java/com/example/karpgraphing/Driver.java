package com.example.karpgraphing;


import android.graphics.Paint;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Driver {
    private ArrayList<LineGraphSeries<DataPoint>> functions;
    private LineGraphSeries<DataPoint> func;
    private Paint paint;

    public Driver(){
        this.functions = new ArrayList<LineGraphSeries<DataPoint>>();
        this.func = new LineGraphSeries<DataPoint>();
        this.paint = new Paint();
    }

    public LineGraphSeries<DataPoint> insertFunction(String fun){
        paint.setARGB(1,255,0,0);
        Expression expression = new Expression(fun);
        Points points = new Points(expression);
        points.generatePoints((-50.0),50.0,2);
        points.getSeries().setThickness(8);
        points.getSeries().setCustomPaint(paint);
        functions.add(points.getSeries());
        return points.getSeries();
    }

}
