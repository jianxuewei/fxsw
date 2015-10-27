package com.fxsw.fxsw;

import android.annotation.SuppressLint;
import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fxsw.models.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ChatActivity extends AppCompatActivity {



    private ListView mContentView;

    private List<Message> msgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initMsgList();
        mContentView = (ListView) findViewById(R.id.lv_message);
        mContentView.setAdapter(new ChatAdapter(this,R.layout.content_message_item,msgList));

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void initMsgList() {
        msgList=new ArrayList<>() ;
        Message msg1,msg2,msg3;
        msg1=new Message(0,"hello");
        msg2=new Message(1,"who's that?");
        msg3=new Message(0,"i am hello world");
        msgList.add(msg1);
        msgList.add(msg2);
        msgList.add(msg3);
    }

    private class ChatAdapter extends ArrayAdapter<Message> {
        private int resourceId;
        public ChatAdapter(Context context, int resource, List<Message> list) {
            super(context, resource, list);
            resourceId=resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder viewHolder;
            Message msg= getItem(position);
            if (convertView==null){
                viewHolder=new ViewHolder();
                view= LayoutInflater.from(getContext()).inflate(resourceId,null);
                viewHolder.layout_left=view.findViewById(R.id.layout_left);
                viewHolder.layout_right=view.findViewById(R.id.layout_right);
                viewHolder.message_left= (TextView) view.findViewById(R.id.message_left);
                viewHolder.message_right= (TextView) view.findViewById(R.id.message_right);
                viewHolder.message_sender_left= (TextView) view.findViewById(R.id.message_sender_left);
                viewHolder.message_sender_right= (TextView) view.findViewById(R.id.message_sender_right);
                view.setTag(viewHolder);
            }else {
                view=convertView;
                viewHolder= (ViewHolder) view.getTag();
            }
            if(msg.getType()==0){
                viewHolder.layout_right.setVisibility(View.GONE);
                viewHolder.message_left.setText(msg.getContent());
                viewHolder.message_sender_left.setText(msg.getSender());
            }else {
                viewHolder.layout_left.setVisibility(View.GONE);
                viewHolder.message_right.setText(msg.getContent());
                viewHolder.message_sender_right.setText(msg.getSender());
            }
            return view;
        }
    }
    class ViewHolder {
        View layout_left;
        View layout_right;
        TextView message_left;
        TextView message_right;
        TextView message_sender_left;
        TextView message_sender_right;
    }
}
