package com.fxsw.fxsw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompleteReceiver extends BroadcastReceiver {
    private final String ACTION="com.fxsw.broadcast.MY_BROADCAST";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("debug","receive");
        if(intent.getAction().equals(ACTION)){
            Log.d("debug","Action is "+ACTION);
            Intent intentStartGuideAcitivity=new Intent(context,GuideActivity.class);
            intentStartGuideAcitivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intentStartGuideAcitivity);
        }
    }
}
