package com.example.chenqiao.broadcastreceiver_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by CHENQIAO on 2016/3/10.
 */
public class LastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        String content = intent.getStringExtra("data");
        Log.i("receiver", "已经收到粘性广播：" + content);
        Toast.makeText(context, "已经收到粘性广播:" + content, Toast.LENGTH_SHORT).show();


        String resultData = getResultData();
        Log.i("receiver","已经收到粘性无序广播："+resultData);
        Toast.makeText(context,"已经收到粘性无序广播:"+resultData,Toast.LENGTH_SHORT).show();
    }
}
