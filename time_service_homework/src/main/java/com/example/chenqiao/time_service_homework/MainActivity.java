package com.example.chenqiao.time_service_homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public  void click1(View view){
        Intent intent = new Intent(this,TimeService.class);
        startService(intent);

    }

    public  void click2(View view){
        Intent intent = new Intent(this,TimeService.class);
        stopService(intent);

    }

}
