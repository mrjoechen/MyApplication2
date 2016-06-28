package com.cskoayan.lan.myfragmentdemo2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private FrameLayout fl_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1 = (Button) findViewById(R.id.bt1);
        Button bt2 = (Button) findViewById(R.id.bt2);
        Button bt3 = (Button) findViewById(R.id.bt3);
        Button bt4 = (Button) findViewById(R.id.bt4);


        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);

        fl_content = (FrameLayout) findViewById(R.id.fl_content);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt1:
                addFragemnt();
                break;
            case R.id.bt2:
                addFragemntB();

                break;
            case R.id.bt3:

                break;
            case R.id.bt4:

                break;

        }
    }

    private void addFragemntB() {

        MyFragmentB fragmentB = new MyFragmentB();
        fl_content.removeAllViews();

        //把该fragment加入到fl_content 帧布局

        final FragmentManager fragmentManager = getFragmentManager();
        //注意：fragment动态添加的方式
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_content,  fragmentB, "fragmentB");
        fragmentTransaction.commit();
    }

    //在此显示MyfrgmentA
    private void addFragemnt() {

        MyFragmentA fragmentA = new MyFragmentA();

        //把该fragment加入到fl_content 帧布局

        fl_content.removeAllViews();

        final FragmentManager fragmentManager = getFragmentManager();
        //注意：fragment动态添加的方式
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_content,fragmentA);
        fragmentTransaction.commit();

    }
}
