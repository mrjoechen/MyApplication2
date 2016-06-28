package com.example.chenqiao.day_14_broadcastreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterInfoActivity extends AppCompatActivity {

    private TextView tv_userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_info);

        tv_userinfo = (TextView) findViewById(R.id.userinfo);
        Intent intent = getIntent();

        User user = intent.getParcelableExtra("userinfo");

        tv_userinfo.setText(user.toString());
    }

}
