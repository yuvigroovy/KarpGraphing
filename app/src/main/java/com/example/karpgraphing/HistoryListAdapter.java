package com.example.karpgraphing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HistoryListAdapter extends RecyclerView.Adapter<FunctionListAdapter.ViewHolder> {

    private History historyDb;
    ArrayList<HistoryItem> functions;

    public HistoryListAdapter(Context context){
        historyDb = new History(context);
        this.functions = historyDb.getAllItems();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.history_func_item, viewGroup, false);
        RecyclerView.ViewHolder viewHolder = new Rec yclerView.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FunctionListAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
