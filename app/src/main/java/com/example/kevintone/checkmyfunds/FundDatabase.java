package com.example.kevintone.checkmyfunds;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;


public class FundDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "funds.db";
    public static final String TABLE_NAME = "funds_table";
    public static final String COL1 = "DESCRIPTION";
    public static final String COL2 = "DATETIME";
    public static final String COL3 = "CLASS";
    public static final String COL4 = "AMOUNT";

    public FundDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOCRIMENT, " +
                " DESCRIPTION TEXT, DATETIME TEXT, CLASS TEXT, AMOUNT INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addTransactionToDatabase(String description, String dateTime, String classText, Double amountToChange) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //Example: contentValues.put(COL1, description);
        contentValues.put(COL1, description);
        contentValues.put(COL2, dateTime);
        contentValues.put(COL3, classText);
        contentValues.put(COL4, amountToChange);

        long isInserted = db.insert(TABLE_NAME, null, contentValues);
        if(isInserted == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }


}
