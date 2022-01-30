package com.example.karpgraphing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //remove app title
        Objects.requireNonNull(getSupportActionBar()).hide();

        //add functions button
        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddFunction();
            }
        });

        //get function
        Intent getFunc = getIntent();
        String func = getFunc.getStringExtra("func");

        //graph settings
        GraphView graph = findViewById(R.id.graph);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-150);
        graph.getViewport().setMaxY(150);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        //function params
//        Expression expression = new Expression(func);
//        Points points = new Points(expression);
//        points.generatePoints((-15.0),15.0,2);
//        points.getSeries().setThickness(8);
//        graph.addSeries(points.getSeries());


        String preFix = "x^3";
        Expression exp = new Expression(preFix);
        Points p = new Points(exp);
        p.generatePoints((-15.0),15.0,2);

        p.getSeries().setThickness(8);

        graph.addSeries(p.getSeries());




    }

    //move to function menu
    public void goToAddFunction(){
        //add function window intent
        Intent intent  = new Intent(this,addFunction.class);
        startActivity(intent);
    }
}