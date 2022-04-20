package com.example.karpgraphing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView historyList = findViewById(R.id.HistoryList);
        History historyDb = new History(this);
        ArrayList<HistoryItem> list = historyDb.getAllItems();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        historyList.setLayoutManager(layoutManager);

        HistoryListAdapter adapter = new HistoryListAdapter(list);
        historyList.setAdapter(adapter);
    }
}