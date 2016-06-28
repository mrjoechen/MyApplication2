package com.cskoayan.lan.myanimationdemo;

  import android.graphics.drawable.AnimationDrawable;
  import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_demo = (ImageView) findViewById(R.id.iv_demo);
//        iv_demo.setImageResource(R.mipmap.cyx); //只显示第一帧

        iv_demo.setImageResource(R.drawable.cyx);
        AnimationDrawable   drawable = (AnimationDrawable) iv_demo.getDrawable();

        drawable.start();
    }
}
