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
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Points {
    private ArrayList<Double> points;
    private ArrayList<Double> undefined;
    private Expression exp; //postfix expression
    private LineGraphSeries<DataPoint> series;
    private int maxPoint;
    public Points (Expression exp) {
        this.exp = exp.infixToPostfix();
        points = new ArrayList<Double>();
        series = new LineGraphSeries<>();
        undefined = new ArrayList<Double>();
    }  
    
    public void generatePoints(Double min, Double max, double step){
        String res;
        //maxPoint = ((max - min) / 0.1);
        for(Double i=min;i<max; i+=0.01){
            res = String.valueOf(exp.substitute(i, 'x').solvePostfix());
            if(!res.equals("Infinity")) {
                points.add(Double.parseDouble(res));
                series.appendData(new DataPoint(i, Double.parseDouble(res)), false, 60000);
            }
            else    
                undefined.add(i);
        }
    }

    public ArrayList<Double> getPoints(){
        return this.points;
    }

    public LineGraphSeries<DataPoint> getSeries(){
        return this.series;
    }

    public ArrayList<Double> getUndefined(){
        return this.undefined;
    }



}
