package com.fxsw.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by xxk on 15/10/31.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_TABLE_BOOK="create table Book ( id integer primary key autoincrement,"
            +" author text, price real, pages integer, name text)";
    public static final String CREATE_TABLE_CATEGORY="create table Category ( id integer primary key autoincrement, category text, category_code integer)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOK);
      db.execSQL(CREATE_TABLE_CATEGORY);
        Toast.makeText(mContext,"you have created databse",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
