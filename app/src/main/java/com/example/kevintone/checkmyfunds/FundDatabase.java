package com.example.kevintone.checkmyfunds;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FundDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "funds.db";
    public static final String TABLE_NAME = "funds_table";
    public static final String COL1 = "DESCRIPTION";
    public static final String COL2 = "DATETIME";
    public static final String COL3 = "CLASS";
    public static final String COL4 = "AMOUNT";
    public static final String COL5 = "PICTUREID";

    public FundDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOCRIMENT, " +
                " DESCRIPTION TEXT, DATETIME TEXT, CLASS TEXT, AMOUNT INTEGER, PICTUREID TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
