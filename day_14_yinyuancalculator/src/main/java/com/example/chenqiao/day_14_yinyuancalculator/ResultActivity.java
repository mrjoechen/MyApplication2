package com.example.chenqiao.day_14_yinyuancalculator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private TextView tv_name1;
    private TextView tv_sex1;
    private TextView tv_name2;
    private TextView tv_sex2;
    private TextView tv_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv_name1 = (TextView) findViewById(R.id.name1);
        tv_sex1 = (TextView) findViewById(R.id.sex1);
        tv_name2 = (TextView) findViewById(R.id.name2);
        tv_sex2 = (TextView) findViewById(R.id.sex2);
        tv_result = (TextView) findViewById(R.id.result);


        Intent intent = getIntent();

        String name1 = intent.getStringExtra("name1");
        String name2 = intent.getStringExtra("name2");
        int sex1 = intent.getIntExtra("sex1", 0);
        int sex2 = intent.getIntExtra("sex2", 0);

        tv_name1.setText(name1);
        tv_name2.setText(name2);

        switch (sex1){
            case 1:
                tv_sex1.setText("男");
                break;
            case 2:
                tv_sex1.setText("女");
                break;
            case 3:
                tv_sex1.setText("其他");
                break;
        }


        switch (sex2){
            case 1:
                tv_sex2.setText("男");
                break;
            case 2:
                tv_sex2.setText("女");
                break;
            case 3:
                tv_sex2.setText("其他");
                break;
        }
        byte[] bytes1 = name1.getBytes();
        int total1 = 0;
        for (byte b:bytes1){
           int number1 = b&0xff;
            total1+=number1;
        }


        int score1 = Math.abs(total1) % 100;

        byte[] bytes2 = name2.getBytes();
        int total2 = 0;
        for (byte b:bytes2){
            int number2 = b&0xff;
            total2+=number2;
        }

        int score2 = Math.abs(total2) % 100;


        int score = (score1+score2+sex1+sex2)/2;

        Toast.makeText(this,"测试成功！恭喜恭喜",Toast.LENGTH_LONG).show();

        if (score > 90){
            tv_result.setText("测试结果："+score+"\n"+"哇,你们两个好般配哦，在一起吧！祝福你们！！！早生贵子");
            return;
        }else if (score >80){
            tv_result.setText("测试结果："+score+"\n"+"哇,你们两个好般配哦，在一起吧！祝福你们！！！早生贵子");
            return;
        }else if (score>70){
            tv_result.setText("测试结果："+score+"\n"+"哇,你们两个好般配哦，在一起吧！祝福你们！！！早生贵子");
            return;
        }else if (score>60){
            tv_result.setText("测试结果："+score+"\n"+"哇,你们两个好般配哦，在一起吧！祝福你们！！！早生贵子");
            return;
        }else{
            tv_result.setText("测试结果："+score+"\n"+"哇,你们两个好般配哦，在一起吧！祝福你们！！！早生贵子");
            return;
        }


    }
}
