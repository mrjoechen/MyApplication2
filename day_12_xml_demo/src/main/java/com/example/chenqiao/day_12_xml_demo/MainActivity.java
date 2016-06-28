package com.example.chenqiao.day_12_xml_demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText number;
    private TextView info_tv;
    PhoneNumberInfo phoneNumberInfo;
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = (EditText) findViewById(R.id.number);
        info_tv = (TextView) findViewById(R.id.info);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            info_tv.setText((String)msg.obj);
            Toast.makeText(MainActivity.this,"查询成功",Toast.LENGTH_SHORT).show();


        }
    };

    public void query(View view){

        new Thread(){
            public void run(){
                try {
                    String string = number.getText().toString();
                    URL url = new URL("http://api.k780.com:88/?app=phone.get&phone="+string+"&appkey=14114&sign=ede788f7bf8d0678e281f660654ef995&format=xml");

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    //设置请求方式，和请求等待时间
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(3000);
                    //建立连接
                    httpURLConnection.connect();
                    //得到响应码
                    int responseCode = httpURLConnection.getResponseCode();

                    if(responseCode==200){

                        InputStream inputStream = httpURLConnection.getInputStream();

                        XmlPullParser xmlPullParser = Xml.newPullParser();
                        xmlPullParser.setInput(inputStream, "UTF-8");
                        int eventType = xmlPullParser.getEventType();
                        while (eventType!=xmlPullParser.END_DOCUMENT){
                            switch (eventType){
                                case XmlPullParser.START_TAG:
                                    if(xmlPullParser.getName().equals("result")){
                                        phoneNumberInfo = new PhoneNumberInfo();
                                        Log.i("test","=======result=======");
                                    }else if(xmlPullParser.getName().equals("phone")){
                                        phoneNumberInfo.setNumber(xmlPullParser.nextText());
                                        Log.i("test", "=======phone=======");
                                    }else if (xmlPullParser.getName().equals("area")){
                                        phoneNumberInfo.setAreanumber(xmlPullParser.nextText());
                                        Log.i("test", "=======area=======");
                                    }else if(xmlPullParser.getName().equals("att")){
                                        phoneNumberInfo.setAttribution(xmlPullParser.nextText());
                                        Log.i("test", "=======att=======");
                                    }else if (xmlPullParser.getName().equals("operators")){
                                        phoneNumberInfo.setoperators(xmlPullParser.nextText());
                                        Log.i("test", "=======operators=======");
                                    }else if (xmlPullParser.getName().equals("status")){
                                        phoneNumberInfo.setStatus(xmlPullParser.nextText());
                                        Log.i("test", "=======status=======");
                                    }
                                    break;
                                case XmlPullParser.END_TAG:
                                    if (xmlPullParser.getName().equals("result")){
                                        info = phoneNumberInfo.toString();

                                        Log.i("test",info);
                                        Log.i("test","===============ok============");
                                    }
                                    break;
                            }
                            eventType = xmlPullParser.next();
                        }

                        inputStream.close();
                    }

                    Message msg = new Message();
                    msg.obj = info;
                    handler.sendMessage(msg);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }
}
