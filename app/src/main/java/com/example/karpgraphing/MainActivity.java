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

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private Driver driver = new Driver();
    private RecyclerView funcList;
    private ArrayList<FunctionDetails> list;
    private FunctionListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recycle view
        list = new ArrayList<FunctionDetails>();
        funcList = findViewById(R.id.functions_list);


        //remove app title
        Objects.requireNonNull(getSupportActionBar()).hide();

        //buttons
        Button clear = findViewById(R.id.clear);
        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddFunction();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear(findViewById(R.id.graph));
            }
        });

        //graph startup
        startGraph();

    }

    //initialize graph and styling options
    public void startGraph(){
        GraphView graph = findViewById(R.id.graph);

        initGraph(graph);

        //temporary point on graph
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        series.appendData(new DataPoint(0,0),true,2);
        graph.addSeries(series);
    }

    //move to function menu
    public void goToAddFunction(){
        //add function window intent
        Intent intent  = new Intent(this, addFunctionActivity.class);
        startActivityForResult(intent,10);
    }

    //add new function
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==10 && resultCode==RESULT_OK){ //a functions is added
            GraphView graph = findViewById(R.id.graph);
            String function = data.getExtras().getString("func");
            graph.addSeries(driver.insertFunction(function));
            list.add(new FunctionDetails(driver.getNameLastIndex(),driver.getNumOfFunctions()));
            adapter = new FunctionListAdapter(list);
            funcList.setAdapter(adapter);
            funcList.setLayoutManager(new LinearLayoutManager(this));

        }
    }

    public void initGraph(GraphView graph){
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-50);
        graph.getViewport().setMaxY(50);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(-50);
        graph.getViewport().setMaxX(50);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
    }

    //clear graph
    public void clear(GraphView graph){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        graph.removeAllSeries();
        series.appendData(new DataPoint(0,0),true,2);
        graph.addSeries(series);
        driver.clearFunctions();
    }
}