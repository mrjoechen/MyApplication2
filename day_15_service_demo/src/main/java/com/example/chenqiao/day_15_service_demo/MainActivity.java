package com.example.chenqiao.day_15_service_demo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //Start开启服务
    public void click1(View view){
        Intent intent = new Intent(this,Myservice.class);
        startService(intent);

    }
    //关闭服务
    public void click2(View view){

        Intent intent = new Intent(this,Myservice.class);
        stopService(intent);

    }
    //Bind绑定服务
    public void click3(View view){

        conn = new MyServiceConnection();
        Intent intent = new Intent(this,Myservice.class);
        bindService(intent, conn,BIND_AUTO_CREATE);


    }
    //解绑服务
    public void click4(View view){

        unbindService(conn);

    }

    public class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("myService","onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("myService","onServiceDisconnected");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
