package com.example.chenqiao.day_13_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    /*
   写一个Activity，重载Activity声明周期涉及到的所有函数。在里面加上log。同时在该Activity页面上添加一个输入框edittext。
可以让用户输入内容。
然后将模拟器Ctrl+F12 横屏。
观察在这个过程中你的Activity的生命周期变化。
然后进行如下实验：
在手机竖屏的时候，在该edittext输入框内输入一段文字。
然后将手机横屏，你发现什么问题？
如何解决该问题？
     */

    /*
    MainActivity生命周期
    打开app：
            03-07 23:51:39.263: I/System.out(3210): MainActivity---------------------onCreate
            03-07 23:51:39.263: I/System.out(3210): MainActivity---------------------onStart
            03-07 23:51:39.263: I/System.out(3210): MainActivity---------------------onResume
    点击按钮：
            03-07 23:52:16.900: I/System.out(3210): MainActivity---------------------click1
            03-07 23:52:16.900: I/System.out(3210): MainActivity---------------------onPause
            03-07 23:52:17.400: I/System.out(3210): MainActivity---------------------onStop
    切换横屏：
            03-07 23:53:20.352: I/System.out(3210): MainActivity---------------------onPause
            03-07 23:53:20.352: I/System.out(3210): MainActivity---------------------onStop
            03-07 23:53:20.352: I/System.out(3210): MainActivity---------------------onDestroy
            03-07 23:53:20.392: I/System.out(3210): MainActivity---------------------onCreate
            03-07 23:53:20.392: I/System.out(3210): MainActivity---------------------onStart
            03-07 23:53:20.392: I/System.out(3210): MainActivity---------------------onResume
    返回：
            03-07 23:53:59.540: I/System.out(3210): MainActivity---------------------onPause
            03-07 23:53:59.830: I/System.out(3210): MainActivity---------------------onStop
            03-07 23:53:59.830: I/System.out(3210): MainActivity---------------------onDestroy
     */
/*
edittext里面的文字没有了

解决方法
1：给edittext控件加上id，系统会自动保存然后恢复过来
或者在onSaveInstanceState保存，在重新创建的oncreate方法中恢复
2：在AndroidManifest.xml里加入声明android:screenOrientation="landscape"
 <activity android:name=".MainActivity" android:screenOrientation="landscape">
3：在AndroidManifest.xml里加入声明android:configChanges="screenSize|orientation"
 <activity android:name=".MainActivity" android:configChanges="screenSize|orientation">
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(getLocalClassName() + "---------------------onCreate");
    }
    public void click1(View view ){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setClass(this, SecondActivity.class);
        startActivity(intent);
        System.out.println(getLocalClassName() + "---------------------click1");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println(getLocalClassName() + "---------------------onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(getLocalClassName() + "---------------------onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println(getLocalClassName() + "---------------------onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println(getLocalClassName() + "---------------------onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(getLocalClassName() + "---------------------onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println(getLocalClassName() + "---------------------onRestart");
    }
}
