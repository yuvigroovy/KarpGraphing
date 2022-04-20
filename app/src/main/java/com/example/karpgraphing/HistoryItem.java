package com.example.karpgraphing;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;

public class HistoryItem {
    private long id;
    private LocalDateTime cur;
    private String date;
    private String function;

    public HistoryItem(long id, String date, String function){
        this.id = id;
        this.date = date;
        this.function = function;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public HistoryItem(String function){
        this.id = id;
        this.date =  "1/1";
        this.function = function;
    }

    public String getDate() {
        return date.toString();
    }

    public long getId() {
        return id;
    }

    public String getFunction() {
        return function;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
