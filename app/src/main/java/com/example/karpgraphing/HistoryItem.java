package com.example.karpgraphing;

import java.text.DateFormat;

public class HistoryItem {
    private long id;
    private DateFormat date;
    private String function;

    public HistoryItem(long id, DateFormat date, String function){
        this.id = id;
        this.date = date;
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

    public void setDate(DateFormat date) {
        this.date = date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
