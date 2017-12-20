package com.example.chenqiao.day_15_service_demo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    private MyServiceConnection conn;
    private EditText et_data;
    private TextView tv_data;
    private Myservice.MyBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_data = (EditText) findViewById(R.id.et_data);
        tv_data = (TextView) findViewById(R.id.tv_data);

    }

    //Start开启服务
    public void 开启服务(View view){
        Intent intent = new Intent(this,Myservice.class);
        startService(intent);

    }
    //停止服务
    public void 停止服务(View view){

        Intent intent = new Intent(this,Myservice.class);
        stopService(intent);

    }
    //Bind绑定服务
    public void 绑定服务(View view){

        conn = new MyServiceConnection();
        Intent intent = new Intent(this,Myservice.class);
        bindService(intent, conn, BIND_AUTO_CREATE);



    }
    //解绑服务
    public void 解绑服务(View view){

        unbindService(conn);

    }

    public void 同步数据(View view){
        String ss = binder.getData();
        tv_data.setText(ss);

        String s = et_data.getText().toString();
        binder.setData(s);
    }

    public class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            binder = (Myservice.MyBinder) service;
            binder.setCallback(new Myservice.Callback() {
                @Override
                public void onDataChange(String data) {

                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putString("data",data);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            });


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

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_data.setText(msg.getData().getString("data"));
        }
    };
}
