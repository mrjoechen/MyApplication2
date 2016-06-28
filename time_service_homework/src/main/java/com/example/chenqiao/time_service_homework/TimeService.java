package com.example.chenqiao.time_service_homework;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by CHENQIAO on 2016/3/10.
 */
public class TimeService extends Service {


    private TimeReceiver timeReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TimeService","TimeService Created!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("TimeService", "TimeService Started!");

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.TIME_TICK");
        timeReceiver = new TimeReceiver();
        registerReceiver(timeReceiver,filter);
        System.out.println("广播已注册");
        System.out.println("服务已启动");
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {

        super.onDestroy();
        unregisterReceiver(timeReceiver);
        Log.i("TimeService", "TimeService destroied!");
    }

}
