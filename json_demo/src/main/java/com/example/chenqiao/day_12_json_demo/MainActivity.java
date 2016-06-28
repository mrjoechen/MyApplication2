package com.example.chenqiao.day_12_json_demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private EditText number;
    String  op;
    String  city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.info_tv);
        number = (EditText) findViewById(R.id.number);


    }

    Handler myHandler = new Handler(){

        //该方法用来处理消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                    Toast.makeText(MainActivity.this,"parse json data", Toast.LENGTH_LONG).show();
                    tv.setText((String) msg.obj);

        }
    };


    public String jsonpase(InputStream is){
        //解析json数据，返回归属地信息组合成的字符串
        String textFromStream = HttpUtils.getTextFromStream(is);

        try {
            JSONObject jsonObject = new JSONObject(textFromStream);

            JSONObject result = (JSONObject) jsonObject.get("result");
            op = (String) result.get("operators");
            city = (String) result.get("style_citynm");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  op+"---"+city;
    }

    public void getFileFromServer(View view){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://api.k780.com:88/?app=phone.get&phone="+number.getText().toString()+"&appkey=14114&sign=ede788f7bf8d0678e281f660654ef995&format=json");
                    HttpURLConnection httpurlConnection = (HttpURLConnection) url.openConnection();
                    //请求有两种方式 get post
                    httpurlConnection.setRequestMethod("GET");
                    httpurlConnection.setConnectTimeout(3000);
                    httpurlConnection.connect();
                    int responseCode = httpurlConnection.getResponseCode();
                    if (responseCode==200){
                        InputStream is =httpurlConnection.getInputStream();
                        String location = jsonpase(is);
                        Message msg  = new Message();
                        msg.obj=location;
                        myHandler.sendMessage(msg );
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
