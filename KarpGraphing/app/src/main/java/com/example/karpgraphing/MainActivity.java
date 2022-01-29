package com.example.karpgraphing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //remove app title
        getSupportActionBar().hide();

        GraphView graph = findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        series.appendData(new DataPoint(5,5), true, 2);
        series.appendData(new DataPoint(10,10), true, 2);
        //graph.addSeries(series);

        String preFix = "x^3";
        Expression exp = new Expression(preFix);
        Points p = new Points(exp);
        Points p2 = new Points(new Expression("x^2"));
        p2.generatePoints((-10.0),10.0,2);
        p.generatePoints((-15.0),15.0,2);
//        graph.computeScroll();
//        graph.getViewport().setScalable(true);
//        graph.getViewport().setScrollable(true);
//        graph.getViewport().setScalableY(true);
//        graph.getViewport().setScrollableY(true);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-150);
        graph.getViewport().setMaxY(150);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.setTitle(preFix);
        p.getSeries().setThickness(8);
        p2.getSeries().setColor(android.R.color.holo_red_light);
        //graph.addSeries(p2.getSeries());
        graph.addSeries(p.getSeries());




    }
}