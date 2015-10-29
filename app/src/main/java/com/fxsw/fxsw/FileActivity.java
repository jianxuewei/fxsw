package com.fxsw.fxsw;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileActivity extends AppCompatActivity {
    private  EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         editText= (EditText) findViewById(R.id.et_File);
        String inputText=load();
        if(!TextUtils.isEmpty(inputText)){
            editText.setText(inputText);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private String load()  {
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try {
            in=openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save();

    }

    private void save() {
        String textToSave=editText.getText().toString();
        if (TextUtils.isEmpty(textToSave)){
            return;
        }else {
            FileOutputStream out= null;
            BufferedWriter writer=null;
            try {
                out =openFileOutput("data", Context.MODE_PRIVATE);
                writer=new BufferedWriter(new OutputStreamWriter(out));
                writer.write(textToSave);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(writer!=null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }
}
