package com.example.karpgraphing;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder> {
    private ArrayList<HistoryItem> itemList;

    public HistoryListAdapter(ArrayList<HistoryItem> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View historyView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_func_item, viewGroup, false);
        return new HistoryViewHolder(historyView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder historyViewHolder, int i) {
        HistoryItem item = itemList.get(i);
        historyViewHolder.func.setText(item.getFunction());
        historyViewHolder.date.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public static class HistoryViewHolder extends RecyclerView.ViewHolder{

        public TextView func;
        public TextView date;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            func = itemView.findViewById(R.id.historyFunc);
            date = itemView.findViewById(R.id.Historydate);
        }
    }
}
