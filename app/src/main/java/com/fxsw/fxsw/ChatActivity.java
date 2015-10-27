package com.fxsw.fxsw;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fxsw.models.Message;

import java.util.ArrayList;
import java.util.List;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ChatActivity extends AppCompatActivity implements View.OnClickListener {





   private int typeSwitch=0;
    private ListView mContentView;
    private ChatAdapter adapter;
    private List<Message> msgList;
    private EditText etInput;


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




        mContentView = (ListView) findViewById(R.id.lv_message);


        // Set up the user interaction to manually show or hide the system UI.



        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.


        mContentView.setAdapter(adapter=new ChatAdapter(this,R.layout.content_message_item,msgList));
        findViewById(R.id.button_send_message).setOnClickListener(this);
//        getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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

    @Override
    public void onClick(View v) {
        etInput= (EditText) findViewById(R.id.et_input);
        String content=etInput.getText().toString();
        switch (v.getId())
        {
            case R.id.button_send_message:
                if(!"".equals(content)){

                    Message msg=new Message(typeSwitch%2,content);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    mContentView.setSelection(msgList.size());
                    typeSwitch++;
                }
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                etInput.setText("");
                break;
            default:
                break;
        }
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
                viewHolder.layout_left.setVisibility(View.VISIBLE);
                viewHolder.layout_right.setVisibility(View.GONE);
                viewHolder.message_left.setText(msg.getContent());
                viewHolder.message_sender_left.setText(msg.getSender());
            }else {
                viewHolder.layout_left.setVisibility(View.GONE);
                viewHolder.layout_right.setVisibility(View.VISIBLE);
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
