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

import android.graphics.Color;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Driver {
    private ArrayList<LineGraphSeries<DataPoint>> functions;
    private int[] colors;
    private int countFunc;

    public Driver(){
        this.functions = new ArrayList<LineGraphSeries<DataPoint>>();
        countFunc = 0;
        colors = new int[]{
                Color.BLUE,
                Color.RED,
                Color.GREEN,
                Color.CYAN,
                Color.MAGENTA,
                Color.YELLOW,
                Color.BLACK,
                Color.GRAY
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
        points.getSeries().setColor(colors[colorToInsert]);
        functions.add(points.getSeries());
        return points.getSeries();
    }

    public void clearFunctions(){
        this.countFunc =0;
        this.functions.clear();
    }

}
