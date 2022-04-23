package com.example.karpgraphing;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //remove app title
        Objects.requireNonNull(getSupportActionBar()).hide();

        RecyclerView historyList = findViewById(R.id.HistoryList);
        History historyDb = new History(this);
        ArrayList<HistoryItem> list = historyDb.getAllItems();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        historyList.setLayoutManager(layoutManager);

        HistoryListAdapter adapter = new HistoryListAdapter(list);
        historyList.setAdapter(adapter);

        Button close = findViewById(R.id.exitBtn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void exit(){
        finish();
    }
}
