package com.example.elena.lab1;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Chats.db";
    public static final int VERSION_NUM = 2;//???
    public static final int KEY_ID = 0;
    public static final String KEY_MESSAGE = "MESSAGE";
    public static final String TABLE_NAME = "chatMessages";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_MESSAGE+" text)" );
       Log.i("ChatDataBaseHelper","Calling OnCreate");
    }

    public void onDowngrade(SQLiteDatabase db, int oldversion, int newversion) {

        onUpgrade(db, oldversion, newversion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i("ChatDataBaseHelper","Calling OnUpgrade, oldVersion=" + oldVersion + "newVersion" + newVersion);
    }

    public boolean insertData(String message){
        SQLiteDatabase db = this.getWritableDatabase(); // creating the database
        ContentValues contentValues = new ContentValues();

        try {

            contentValues.put(KEY_MESSAGE,message);

            db.insert(TABLE_NAME, null, contentValues);

            return true;
        }catch(Exception e){
            return false;
        }
    }

    public void deleteMessages(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
