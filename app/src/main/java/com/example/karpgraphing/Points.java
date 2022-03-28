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
import com.example.karpgraphing.exp.Calc;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class
Points {
    private String rawExpression;
    private Calc exp; //postfix expression
    private LineGraphSeries<DataPoint> series;
    private ArrayList<LineGraphSeries<DataPoint>> fullSeries;
    private int maxPoint;
    public Points (String rawExp) {
        this.rawExpression = rawExp;
        this.exp = new Calc(rawExpression);
        series = new LineGraphSeries<>();
        fullSeries = new ArrayList<LineGraphSeries<DataPoint>>();
    }  
    
    public void generatePoints(Double min, Double max){
        Double res;
        for(Double i=min;i<max; i+=0.01){
            res = exp.solve(i);
            if(!res.equals("Infinity")) {
                series.appendData(new DataPoint(i, res), false, 60000);
            }
            else{
                fullSeries.add(series);
                series = new LineGraphSeries<>();
            }
        }
        fullSeries.add(series);
    }

    public LineGraphSeries<DataPoint> getSeries(){
        return this.series;
    }

    public ArrayList<LineGraphSeries<DataPoint>> getFullSeries(){
        return this.fullSeries;
    }



}
