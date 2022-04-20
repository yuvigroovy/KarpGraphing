package com.example.karpgraphing;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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

    public ArrayList<HistoryItem> getAllItems() {
        database = getReadableDatabase();
        ArrayList<HistoryItem> list = new ArrayList<>();
        String sortOrder = COLUMN_DATE + "DESC";
        Cursor cursor = database.query(TABLE_HISTORY, allColumns, null, null, null, null, sortOrder);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String func = cursor.getString(cursor.getColumnIndex(COLUMN_FUNC));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                HistoryItem c = new HistoryItem(id, date, func);

                list.add(c);
            }
        }
        database.close();
        return list;
    }

    public long deleteAll() {

        database = getWritableDatabase();
        database.delete(TABLE_HISTORY, null, null);

        database.close();

        return -1;
    }

    public void deletePlayerByRow(long id)
    {

        database = getWritableDatabase();
        database.delete(TABLE_HISTORY, COLUMN_ID + "=" + id, null);

        database.close();
    }

    public long updateById(HistoryItem item)
    {
        database = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_ID, item.getId());
        values.put(COLUMN_FUNC, item.getFunction());
        values.put(COLUMN_DATE, item.getDate());
        return database.update(TABLE_HISTORY, values, COLUMN_ID +
                "=" + item.getId(), null);
    }
}
