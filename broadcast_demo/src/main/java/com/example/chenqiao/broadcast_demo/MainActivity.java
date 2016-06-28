package com.example.chenqiao.broadcast_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendbroadcast(View view){
        Intent intent = new Intent();
        intent.setAction("com.chenqiao.android");
        intent.putExtra("data", "广播时间！！！");
        sendBroadcast(intent);
        Toast.makeText(this,"广播已发出",Toast.LENGTH_SHORT).show();
        Log.i("Broadcast", "广播已发出");
    }


    public void sendorderbroadcast(View view){
            Intent intent = new Intent();
        intent.setAction("com.chenqiao.orderbroadcast");
        /*(@NonNull Intent intent,
            @Nullable String receiverPermission, @Nullable BroadcastReceiver resultReceiver,
            @Nullable Handler scheduler, int initialCode, @Nullable String initialData,
            @Nullable Bundle initialExtras)
         */
        sendOrderedBroadcast(intent, null, null, null, 1, "orderbroadcast!", null);
    }


    public void stickySendBroadcast(View view){
        Intent intent = new Intent();
        intent.setAction("com.chenqiao.stickyBroadcast");
        intent.putExtra("data", "粘性无序广播");
        sendStickyBroadcast(intent);
    }
    public void stickySendorderBroadcast(View view){
        Intent intent = new Intent();
        intent.setAction("com.chenqiao.stickyorderBroadcast");
        sendStickyOrderedBroadcast(intent, null, null, 1, "粘性有序广播", null);
    }
}
