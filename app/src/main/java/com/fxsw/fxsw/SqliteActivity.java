package com.fxsw.fxsw;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fxsw.tools.MyDatabaseHelper;


public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,2);
        findViewById(R.id.button_create_database).setOnClickListener(this);
        findViewById(R.id.button_add_data).setOnClickListener(this);
        findViewById(R.id.button_query_data).setOnClickListener(this);
        findViewById(R.id.button_delete_data).setOnClickListener(this);
    }
    private  SQLiteDatabase database;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_create_database:
                dbHelper.getWritableDatabase();
                break;
            case R.id.button_add_data:
                database= dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name","the da vinci code");
                values.put("author","Dan Brown");
                values.put("price",16.96);
                values.put("pages",454 );
                database.insert("Book",null,values);
                values.clear();

                values.put("name","the last symbol");
                values.put("author","Dan brown");
                values.put("price",19.95);
                values.put("pages",510);
                database.insert("Book",null,values);
                break;
            case R.id.button_query_data:
                //Log.d("book","query");
                database= dbHelper.getWritableDatabase();
                Cursor cursor=database.query("Book",null,null,null,null,null,null);

                while (cursor.moveToNext()){
                    String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("book","name is:"+name+",author is:"+author+",pages is :"+pages+",price is :"+price);
                }
                cursor.close();
                break;
            case R.id.button_delete_data:
                database= dbHelper.getWritableDatabase();
                database.delete("Book","pages>?",new String[]{"500"});
                Log.d("Book","has deleted books which pages more than 500");
                break;
            default:
                break;
        }
    }
}
