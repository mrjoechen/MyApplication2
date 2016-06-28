package com.example.chenqiao.day_14_broadcastreceiver_homework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by CHENQIAO on 2016/3/9.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        State wifiState = null;
        State mobileState = null;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if (wifiState != null && mobileState != null
                && State.CONNECTED != wifiState
                && State.CONNECTED == mobileState) {
            // 手机网络连接成功
            Log.i("BroadcastReceiver","移动网络已连接");
            Toast.makeText(context,"移动网络已连接",Toast.LENGTH_SHORT).show();

        }  else if (wifiState != null && State.CONNECTED == wifiState) {
            // 无线网络连接成功
            Log.i("BroadcastReceiver","无线网络已连接");
            Toast.makeText(context,"无线网络已连接",Toast.LENGTH_SHORT).show();
        }else if (wifiState != null && mobileState != null
                && State.CONNECTED != wifiState
                && State.CONNECTED != mobileState) {
            // 手机没有任何的网络
            Log.i("BroadcastReceiver","请连接网络");
            Toast.makeText(context,"请连接网络！",Toast.LENGTH_SHORT).show();
        }
    }
}
