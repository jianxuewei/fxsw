package com.fxsw.fxsw;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.button_progress_dialog).setOnClickListener(this);
        findViewById(R.id.button_show_listview).setOnClickListener(this);
        findViewById(R.id.button_show_expandablelistview).setOnClickListener(this);
        findViewById(R.id.button_show_chat).setOnClickListener(this);
        findViewById(R.id.button_show_viewpager).setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public Handler handler=new Handler() {


        public void  handleMessage(Message message){
            switch (message.what){
                case RESULT_OK:
                    if(progressDialog!=null){
                        progressDialog.dismiss();
                    }
                    break;
                default:
                    break;
            }
        }

    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

            case R.id.button_progress_dialog:
                progressDialog=new ProgressDialog(GuideActivity.this);
                progressDialog.setTitle(R.string.notes);
                progressDialog.setMessage(getResources().getString(R.string.data_loading));
                progressDialog.setCancelable(false);
                progressDialog.show();
                new Thread(new Runnable(){
                  @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                      Message message=new Message();
                      message.what=RESULT_OK;
                      handler.sendMessage(message);
                    }
                }).start();
                break;

            case  R.id.button_show_listview:
               startActivity(new Intent(GuideActivity.this, ListViewActivity.class));
                break;

            case R.id.button_show_expandablelistview:
                startActivity(new Intent(GuideActivity.this,ExpanActivity.class));
                break;

            case R.id.button_show_chat:
                startActivity(new Intent(GuideActivity.this,ChatActivity.class));
                break;

            case R.id.button_show_viewpager:
                Log.d("viewpager","start");
                startActivity(new Intent(GuideActivity.this, ViewpagerActivity.class));
                break;

            default:
                break;
        }
    }
}
