package com.example.chenqiao.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by CHENQIAO on 2016/3/14.
 */
public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    //返回MyBinder对象
    public IBinder onBind(Intent intent) {
        Log.i("MusicService", "onBind");
        return new MyBInder();

    }

    @Override
    //服务一旦开启就执行该方法,媒体播放器准备
    public void onCreate() {
        super.onCreate();
        Log.i("MusicService", "onCreate");
        mediaPlayer = new MediaPlayer();

    }



    //播放音乐
    public void playMusic(){
        Log.i("MusicService", "音乐开始播放");
        Toast.makeText(this,"音乐开始播放",Toast.LENGTH_SHORT).show();

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource("/storage/sdcard0/Champions.mp3");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

        updateseekbar();

    }


    private void updateseekbar(){
        final int duration = mediaPlayer.getDuration();
        final Timer timer = new Timer();

        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                int currentPosition = mediaPlayer.getCurrentPosition();
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("duration",duration);
                bundle.putInt("currentPosition",currentPosition);
                msg.setData(bundle);
                MainActivity.handler.sendMessage(msg);

            }
        };
        //100毫秒后每秒更新进度
        timer.schedule(timerTask, 100, 1000);

        //歌曲完毕取消

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                timer.cancel();
                timerTask.cancel();
            }
        });
    }


    public void seekto(int position){
        mediaPlayer.seekTo(position);
    }
    /*//暂停音乐
    public void pauseMusic(){
        Log.i("MusicService","暂停播放");
        Toast.makeText(this,"暂停播放",Toast.LENGTH_SHORT).show();

        mediaPlayer.pause();
    }
    //继续播放
    public void replayMusic(){
        Toast.makeText(this,"继续播放",Toast.LENGTH_SHORT).show();
        Log.i("MusicService", "继续播放");
        mediaPlayer.start();
    }*/

    //暂停或者继续播放
    public void pauseorreplayMusic(){
        if (mediaPlayer.isPlaying()){
            Log.i("MusicService","暂停播放");
            Toast.makeText(this,"暂停播放",Toast.LENGTH_SHORT).show();
            mediaPlayer.pause();
        }else {
            Toast.makeText(this,"继续播放",Toast.LENGTH_SHORT).show();
            Log.i("MusicService", "继续播放");
            mediaPlayer.start();
        }

    }
    //停止音乐
    public void stopMusic(){
        Toast.makeText(this,"音乐已停止",Toast.LENGTH_SHORT).show();
        Log.i("MusicService", "音乐已停止");
        mediaPlayer.stop();
        mediaPlayer.reset();

    }

    private  class MyBInder extends Binder implements MusicServiceInterface{

        @Override//调用播放音乐方法
        public void callplayMusic() {
            playMusic();

        }

        @Override//调用暂停音乐方法
        public void callpauseorreplayMusic() {
            pauseorreplayMusic();
        }

//        @Override//调用继续播放方法
//        public void callreplayMusic() {
//            replayMusic();
//        }

        @Override//调用停止播放方法
        public void callstopMusic() {
            stopMusic();
        }

        @Override
        public void callseekto(int position) {
            seekto(position);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MusicService", "onDestroy");

    }

}
