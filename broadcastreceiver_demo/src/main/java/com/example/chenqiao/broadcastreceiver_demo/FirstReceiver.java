package com.example.chenqiao.broadcastreceiver_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by CHENQIAO on 2016/3/9.
 */
public class FirstReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        String resultData = getResultData();
        Log.i("receiver","老大已经收到："+resultData);
        Toast.makeText(context,"老大已经收到:"+resultData,Toast.LENGTH_SHORT).show();

    }
}
