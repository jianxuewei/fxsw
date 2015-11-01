package com.fxsw.fxsw;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView from,receiveMessageContent;
    private IntentFilter receiveFilter,sendFilter;
    private MyMessageReceiver myMessageReceiver;

    private EditText sendto,sendmessage;
    private SendMessageReceiver sendMessageReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        //发送通知,在通知栏有一条消息
        findViewById(R.id.button_notification).setOnClickListener(this);

        //接收短信,自android 4.4以后,通过设置优先级不能屏蔽系统接收到短信
        from= (TextView) findViewById(R.id.tv_message_from);
        receiveMessageContent= (TextView) findViewById(R.id.tv_receive_message_content);
        receiveFilter=new IntentFilter();

        receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        receiveFilter.setPriority(2147483647);
        myMessageReceiver=new MyMessageReceiver();
        registerReceiver(myMessageReceiver, receiveFilter);

        //发送短信
        sendto= (EditText) findViewById(R.id.tv_message_to);
        sendmessage= (EditText) findViewById(R.id.tv_send_message_content);
        sendFilter=new IntentFilter();
        sendFilter.addAction("SENT_SMS_ACTION");
        sendMessageReceiver=new SendMessageReceiver();
        registerReceiver(sendMessageReceiver,sendFilter);
        findViewById(R.id.button_send_message).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myMessageReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_notification:
                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
               //setLatestEvent方法已经过时,用builder
                Notification.Builder builder=new Notification.Builder(this);
                builder.setAutoCancel(false).setSmallIcon(R.drawable.ic_info_black_24dp).setContentTitle("title:a new notice").setContentText("this is a test notice")
                        .setTicker("ticker:notice") .setWhen(System.currentTimeMillis());
                PendingIntent pendingIntent=PendingIntent.getActivity(NotificationActivity.this, 0, new Intent(NotificationActivity.this, NotificationActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                Notification notification=builder.build();
                manager.notify(1,notification);
                break;
            case R.id.button_send_message:
                SmsManager smsManager=SmsManager.getDefault();
                Intent intent=new Intent("SENT_SMS_ACTION");
                PendingIntent pendingIntent1=PendingIntent.getBroadcast(NotificationActivity.this,0,intent,0);
                smsManager.sendTextMessage(sendto.getText().toString(),null,sendmessage.getText().toString(),pendingIntent1,null);
                break;
            default:
                break;
        }

    }
    public class MyMessageReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();
            Object[] pdus= (Object[]) bundle.get("pdus");
            SmsMessage[] smsMessages=new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                smsMessages[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);

            }
            String address=smsMessages[0].getOriginatingAddress();
            String content="";
            for(SmsMessage message:smsMessages){
                content+=message.getMessageBody();
            }
            from.setText("发送人:"+address);
            receiveMessageContent.setText("短信内容:"+content);
            abortBroadcast();

        }
    }

    private class SendMessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(getResultCode()==RESULT_OK){
                Toast.makeText(NotificationActivity.this,"send success",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(NotificationActivity.this,"send failure",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
