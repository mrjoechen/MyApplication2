package com.example.chenqiao.day_15_service_demo;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by CHENQIAO on 2016/3/10.
 */
public class Myservice extends Service {

    private boolean flag;
    private int count;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.i("myService","onBind");
        Toast.makeText(this,"服务已绑定",Toast.LENGTH_SHORT).show();

        flag = true;
        new Thread(){
            @Override
            public void run() {
                super.run();


                while(flag){

                    count++;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.i("MyServcie", count +"");

                }

            }
        }.start();
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("myService", "onCreate");
        Toast.makeText(this,"服务已创建",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("myService", "onStartCommand");
        Toast.makeText(this,"服务已启动",Toast.LENGTH_SHORT).show();
        flag = true;
        new Thread(){
            @Override
            public void run() {
                super.run();


                while(flag){

                    count++;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.i("MyServcie", count +"");

                }

            }
        }.start();
        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myService","onDestroy");

        Toast.makeText(this,"服务已停止",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);

        Log.i("myService","unbindService");
        Toast.makeText(this,"服务已解绑",Toast.LENGTH_SHORT).show();

    }
}
