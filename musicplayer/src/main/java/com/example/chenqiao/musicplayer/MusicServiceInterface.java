package com.example.chenqiao.musicplayer;

/**
 * Created by CHENQIAO on 2016/3/14.
 */
public interface MusicServiceInterface {

    public void callplayMusic();
    //暂停或继续音乐
    public void callpauseorreplayMusic();
    //继续播放
//    public void callreplayMusic();
    //停止音乐
    public void callstopMusic();
    public void callseekto(int position);

}
