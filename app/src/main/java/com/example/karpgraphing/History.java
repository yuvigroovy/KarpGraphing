package com.example.karpgraphing;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class History extends SQLiteOpenHelper{

    public static final String DATABASENAME = "history.db";
    public static final String TABLE_HISTORY = "tbhistory";
    public static final int DATABASEVERSION = 1;
    public static final String COLUMN_FUNC = "function";
    public static final String COLUMN_DATE= "date";
    public static final String COLUMN_ID= "Id";
    public static final String[] allColumns = {COLUMN_ID,
            COLUMN_FUNC, COLUMN_DATE};

    private static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE IF NOT EXISTS " +
            TABLE_HISTORY + "(" +
            COLUMN_ID+" INTEGER PRIMARY KEY,"+

    COLUMN_FUNC + " TEXT," +
    COLUMN_DATE + " TEXT );";

    SQLiteDatabase database;

    public History(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CUSTOMER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(sqLiteDatabase);
    }

    public void createFunction(HistoryItem item){
        database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FUNC, item.getFunction());
        cv.put(COLUMN_DATE, item.getDate());
        long id = database.insert(TABLE_HISTORY, null, cv);
        item.setId(id);
        database.close();
    }

    
}
