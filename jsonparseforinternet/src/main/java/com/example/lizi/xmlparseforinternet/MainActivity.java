package com.example.lizi.xmlparseforinternet;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    private TextView tv_content;
    private ArrayList<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        students = new ArrayList<Student>();
        tv_content = (TextView) findViewById(R.id.tv_content);
    }

    Handler myhandler = new Handler(){//该方法用来处理消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_LONG).show();
                    tv_content.setText(students.toString());
                    break;
                case 2:
                    Toast.makeText(MainActivity.this,"login fail",Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(MainActivity.this,"parse json data",Toast.LENGTH_LONG).show();
                    tv_content.setText((String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };
    //在这里实现Json解析
    public void getandparse3(View v){
        //从服务器上获取文件
        getFileFromServer("http://api.k780.com:88/?app=phone.get&phone=13688888888&appkey=14114&sign=ede788f7bf8d0678e281f660654ef995&format=json");
    }

    public void getFileFromServer(String url){
        final String path = url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //此处不能写localhost，代表手机自己
                    URL url =new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.connect();//阻塞
                    //等待服务器的响应
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode==200){
                        //如何拿到正文
                        InputStream inputStream = urlConnection.getInputStream();

                        String localtion = paserXml3(inputStream);

                        //加上标签，并且附带信息
                        Message msg = new Message();
                        msg.what = 3;
                        msg.obj = localtion;
                        myhandler.sendMessage(msg);
                    }else{
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //在这里解析json数据,然后把得到的归属地信息组合成一个字符串，返回
    public String  paserXml3(InputStream is){

        String textFromStream = HttpUtils.getTextFromStream(is);
        String op ="";
        String city="";
        try {
            JSONObject jsonObject = new JSONObject(textFromStream);

            JSONObject result = (JSONObject) jsonObject.get("result");


            op = (String) result.get("operators");
            city = (String) result.get("style_citynm");
        } catch (JSONException e) {
            e.printStackTrace();
        }
     return op+"--"+city;
    }

}
