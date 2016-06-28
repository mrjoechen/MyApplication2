package com.example.chenqiao.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
