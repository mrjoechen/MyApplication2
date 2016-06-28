package com.example.chenqiao.broadcastreceiver_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by CHENQIAO on 2016/3/9.
 */
public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        String content = intent.getStringExtra("data");
        Log.i("receiver",content);
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
