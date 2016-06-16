package com.example.kevintone.checkmyfunds;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;


public class FundDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "funds.db";
    public static final String TABLE_NAME = "funds_table";
    public static final String COL1 = "DESCRIPTION";
    public static final String COL2 = "DATETIME";
    public static final String COL3 = "CLASS";
    public static final String COL4 = "AMOUNT";

    private double currentBalance = 0;

    public FundDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (DESCRIPTION TEXT, " +
                "DATETIME TEXT, CLASS TEXT, AMOUNT REAL PRIMARY KEY)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addTransactionToDatabase(String description, String classText, Double amountToChange) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //Example: contentValues.put(COL1, description);
        contentValues.put(COL1, description);
        contentValues.put(COL2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        contentValues.put(COL3, classText);
        contentValues.put(COL4, amountToChange);

        long isInserted = db.insert(TABLE_NAME, null, contentValues);
        if(isInserted == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean removeTransactionFromDatabase() {

        return true;
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        long size = new File(db.getPath()).length();
        String dbQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY datetime(DATETIME) DESC Limit " + size;
        Cursor data = db.rawQuery(dbQuery, null);
        return data;
    }

    public Double getCurrentBalance() {
        double result;
        SQLiteDatabase db = this.getWritableDatabase();
        String dbQuery = "SELECT SUM(AMOUNT) FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(dbQuery, null);
        if(data.moveToFirst()) {
            result = data.getDouble(0);
        } else {
            result = 0;
        }
        return result;
    }

    public String getAllClasses() {
        SQLiteDatabase db = this.getWritableDatabase();
        String dbQuery = "SELECT DISTINCT CLASS FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(dbQuery, null);
        return "Random String";
    }


}
