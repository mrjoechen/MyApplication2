package com.example.chenqiao.musicplayer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;


public class MainActivity extends AppCompatActivity {

    private MusicServiceInterface musicServiceInterface;
    private MyMusicConnection connection;
    private Intent intent;
    private static SeekBar seekbar;


    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            Bundle data = msg.getData();

            int duration = data.getInt("duration");
            int currentPosition = data.getInt("currentPosition");


            seekbar.setMax(duration);
            seekbar.setProgress(currentPosition);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //开启应用立即即开启服务
        intent = new Intent(this, MusicService.class);
        startService(intent);

        connection = new MyMusicConnection();
        //调用bindService获取联系对象，间接调用服务里的方法
        bindService(intent, connection, BIND_AUTO_CREATE);
        //设置进度控制
        seekbar = (SeekBar) findViewById(R.id.seekBar);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    musicServiceInterface.callseekto(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void play(View view){
        musicServiceInterface.callplayMusic();
    }
    public void pauseorreplay(View view){
        musicServiceInterface.callpauseorreplayMusic();
    }

    public void stop(View view){
        musicServiceInterface.callstopMusic();
    }
    
    
    


    //监听服务状态
    class MyMusicConnection implements ServiceConnection{

        @Override
        //服务连接成功调用
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取联系对象
            musicServiceInterface = (MusicServiceInterface) service;
        }

        @Override
        //
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    //页面销毁时，解绑服务
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        Log.i("MusicService","主界面销毁");
    }
}
