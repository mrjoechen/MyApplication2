package com.example.chenqiao.time_service_homework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by CHENQIAO on 2016/3/10.
 */
public class TimeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("时间在流逝。。。！");
        Toast.makeText(context,"时间在流逝。。。！",Toast.LENGTH_SHORT).show();
    }
}
