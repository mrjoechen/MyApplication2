package com.example.chenqiao.day_13_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }
    public void click3(View view ){
        Intent intent = new Intent();
        intent.setClass(this, FourthActivity.class);
        startActivity(intent);
    }
}