package com.example.chenqiao.fragment_homework;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
    Fragment与activity结合的两种方式：
    1）静态调用：在Activity布局文件声明fragment布局，并创建与fragment相对应的fragment类，继承Fragment，
        重写onCreateView方法，加载Activity布局时，会将fragment布局转化成view对象，显示在Activity中
    2）动态调用：通过获取Fragment的管理者，将创建的Fragment对象动态添加到Activity中
     */

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button btn_wx = (Button) findViewById(R.id.btn_weixin);
            Button btn_contact = (Button) findViewById(R.id.btn_contact);
            Button btn_disconver = (Button) findViewById(R.id.btn_discover);
            Button btn_me = (Button) findViewById(R.id.btn_me);
            btn_wx.setOnClickListener(this);
            btn_disconver.setOnClickListener(this);
            btn_me.setOnClickListener(this);
            btn_contact.setOnClickListener(this);

        }



        @Override
        public void onClick(View v) {

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();

            switch (v.getId()) {
                case R.id.btn_weixin:
                    beginTransaction.replace(R.id.ll_layout, new WxFragment());

                    break;

                case R.id.btn_contact:
                    beginTransaction.replace(R.id.ll_layout, new ContactFragment());
                    break;

                case R.id.btn_discover:
                    beginTransaction.replace(R.id.ll_layout, new DiscoverFragment());
                    break;

                case R.id.btn_me:

                    beginTransaction.replace(R.id.ll_layout, new MeFragment());
                    break;

            }

            beginTransaction.commit();

        }
}
