package com.example.chenqiao.broadcastreceiver_demo;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    LastReceiver lastReceiver;
    private IntentFilter filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态注册
        filter = new IntentFilter();
        filter.addAction("com.chenqiao.stickyBroadcast");
        lastReceiver=new LastReceiver();
        filter.setPriority(1000);
        registerReceiver(lastReceiver, filter);


        filter = new IntentFilter();
        filter.addAction("com.chenqiao.stickyorderBroadcast");
        lastReceiver=new LastReceiver();
        filter.setPriority(1000);
        registerReceiver(lastReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(lastReceiver);
    }
}
