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
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private Driver driver = new Driver();
    private RecyclerView funcList;
    private ArrayList<FunctionDetails> list;
    private FunctionListAdapter adapter;
    private Queue<String> functions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        functions = new LinkedList<>();

        //recycle view
        list = new ArrayList<FunctionDetails>();
        funcList = findViewById(R.id.functions_list);


        //remove app title
        Objects.requireNonNull(getSupportActionBar()).hide();

        //buttons
        Button clear = findViewById(R.id.clear);
        Button add = findViewById(R.id.add);
        Button history = findViewById(R.id.goToHistory);
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
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHistory();
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

    //move to history menu
    public void goToHistory(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivityForResult(intent,15);
    }

    //add new function
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==10 && resultCode==RESULT_OK){ //a functions is added
            String function = data.getExtras().getString("func");
            addFunction(function);
        }

        //received functions back
        if(requestCode==15 && resultCode==RESULT_OK){
            functions = (Queue<String>) data.getExtras().get("funcQ");
            while(!functions.isEmpty())
                addFunction(functions.remove());
        }
    }

    //inserting a function and drawing it from a string
    public void addFunction(String function){
        GraphView graph = findViewById(R.id.graph);
        functions.add(function);
        graph.addSeries(driver.insertFunction(function));
        list.add(new FunctionDetails(driver.getNameLastIndex(),driver.getColorToInsert()));
        adapter = new FunctionListAdapter(list);
        funcList.setAdapter(adapter);
        funcList.setLayoutManager(new LinearLayoutManager(this));
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
        if(!list.isEmpty()){
            list.clear();
            adapter.notifyDataSetChanged();
        }
    }
}