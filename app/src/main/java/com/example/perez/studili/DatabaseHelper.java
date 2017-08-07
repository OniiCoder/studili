package com.example.perez.studili;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Perez on 7/25/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_name = "studili.db";
    public static final String Table_name = "task_table";

    public static final String Col_1 = "ID";
    public static final String Col_2 = "TITLE";
    public static final String Col_3 = "TASK_TEXT";
    public static final String Col_4 = "INTERVAL";



    public DatabaseHelper(Context context) {
        super(context, Database_name, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , TITLE TEXT , TASK_TEXT TEXT , INTERVAL INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_name);
        onCreate(db);
    }

    public boolean insertData(String title, String task_text, String interval){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, title);
        contentValues.put(Col_3, task_text);
        contentValues.put(Col_4, interval);

        long result = db.insert(Table_name, null, contentValues);

        if(result == -1){
            return false;
        } else{
            return true;
        }

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table_name + " ORDER BY " + Col_1 + " DESC", null);


        return res;
    }

    public String[] getTitles(){
        Cursor titleCursor = getReadableDatabase().rawQuery("SELECT " + Col_2 + " FROM " + Table_name + " ORDER BY " + Col_1 + " DESC", null);
        titleCursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();

        while(!titleCursor.isAfterLast()){
            String title = titleCursor.getString(titleCursor.getColumnIndex(Col_2));
            int titleLength = title.length();

            if(titleLength > 20){
                names.add(title.substring(0,20) + "...");
            } else{
                names.add(title);
            }

            titleCursor.moveToNext();
        }
        titleCursor.close();
        return names.toArray(new String[names.size()]);
    }

    public String[] getTexts(){
        Cursor titleCursor = getReadableDatabase().rawQuery("SELECT " + Col_3 + " FROM " + Table_name + " ORDER BY " + Col_1 + " DESC", null);
        titleCursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();

        while(!titleCursor.isAfterLast()){
            String text = titleCursor.getString(titleCursor.getColumnIndex(Col_3));
            int textLength = text.length();

            if(textLength > 35){
                names.add(text.substring(0,35) + "...");
            } else {
                names.add(text);
            }

            titleCursor.moveToNext();
        }
        titleCursor.close();
        return names.toArray(new String[names.size()]);
    }



    public boolean updateData(String id, String title, String task_text, String interval){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_1, id);
        contentValues.put(Col_2, title);
        contentValues.put(Col_3, task_text);
        contentValues.put(Col_4, interval);

        db.update(Table_name, contentValues, "ID = ?", new String[] { id });
        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_name, "ID = ?", new String[] { id });

    }

}
