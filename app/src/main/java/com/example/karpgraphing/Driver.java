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

import static android.graphics.Color.parseColor;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Driver {
    private ArrayList<LineGraphSeries<DataPoint>> functions;
    private int[] colors;
    private int countFunc;
    private Color myRed;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Driver(){
        this.functions = new ArrayList<LineGraphSeries<DataPoint>>();
        this.countFunc = 0;
        this.myRed = Color.valueOf(199,68,64,1);
        colors = new int[]{
                parseColor("#FF6363"), // color red
                parseColor("#BAFFB4"), // color green
                parseColor("#548CFF"), // color blue
                parseColor("#F58634"), // color orange
                parseColor("#0A1D37"), // color black
        };
    }

    public LineGraphSeries<DataPoint> insertFunction(String fun){
        countFunc++;
        int colorToInsert = countFunc;
        Expression expression = new Expression(fun);
        Points points = new Points(expression);
        points.generatePoints((-50.0),50.0);
        points.getSeries().setThickness(8);
        if(colorToInsert > this.colors.length-1)
            colorToInsert -= this.colors.length;
        points.getSeries().setColor(colors[colorToInsert-1]);
        functions.add(points.getSeries());
        return points.getSeries();
    }

    public void clearFunctions(){
        this.countFunc =0;
        this.functions.clear();
    }

}
