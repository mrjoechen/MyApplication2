package com.example.lizi.queryphonelocation;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/* 实现一个身份证号码归属地查询的应用。
        要求：分别使用解析xml格式数据和解析json格式数据实现。使用哪种http工具包无要求。

        */
public class MainActivity extends ActionBarActivity {
    private EditText et_phone;
    private TextView tv_content;
    private TextView tv_content2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化两个控件
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content2 = (TextView) findViewById(R.id.tv_content2);

    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(MainActivity.this, "json 解析", Toast.LENGTH_LONG).show();
                    tv_content2.setText((String) msg.obj);
                    break;
                case 2:
                    Toast.makeText(MainActivity.this, "xml 解析", Toast.LENGTH_LONG).show();
                    tv_content.setText((String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    public void query_xml(View v) {
        //首先解析下读取的电话号码
        String phone = et_phone.getText().toString();
        getFileFromServer("http://api.k780.com:88/?app=phone.get&phone=" + phone + "&appkey=14114&sign=ede788f7bf8d0678e281f660654ef995&format=xml");
    }

    public void query_json(View v) {
        //首先解析下读取的电话号码
        String phone = et_phone.getText().toString();
        getFileFromServer2("http://api.k780.com:88/?app=phone.get&phone=" + phone + "&appkey=14114&sign=ede788f7bf8d0678e281f660654ef995&format=json");
    }

    public void getFileFromServer(String url) {
        final String path = url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.connect();
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = urlConnection.getInputStream();
                        //送去解析，并传回归属地信息，地点及运营商
                        String location = parsexml(inputStream);
                        Message msg = new Message();//都可以
                       // Message msg = myHandler.obtainMessage();
                        msg.what = 2;
                        msg.obj = location;
                        myHandler.sendMessage(msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public String parsexml(InputStream is) {
        XmlPullParser parser = Xml.newPullParser();
        String op = "";
        String city = "";
        try {
            parser.setInput(is, "utf_8");
            int next = parser.next();
            while (next != parser.END_DOCUMENT) {//解析步骤，1，switch(next)--2.case XmlPullParser.START_TAG!!!!!!
                //解析正文，解析数据
                switch (next) {
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();//第三步
                        if (name.equals("operators")) {
                            op = parser.nextText();
                        } else if (name.equals("style_citynm")) {
                            city = parser.nextText();
                        }
                        break;
                    default:
                        break;
                }
                next = parser.next();//这是为何？
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return op + "-----" + city;
    }

    public void getFileFromServer2(String url) {
        final String path = url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.connect();
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = urlConnection.getInputStream();
                        //送去解析，并传回归属地信息，地点及运营商
                        String location = parsejson(inputStream);
                        // Message msg = new Message();            ！！暂时无区别，都可以用
                        Message msg = myHandler.obtainMessage();
                        msg.what = 1;
                        msg.obj = location;
                        myHandler.sendMessage(msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public String parsejson(InputStream is) {
        //在这里解析json的数据，然后把得到的归属地信息组合成一个字符串， 返回
        String textFromStream = HttpUtils.getTextFromStream(is);
        String op = "";
        String city = "";
        //步骤，new 一个json对象-----2.获取其中想要的元素------3.通过键值对取值
        try {//1.---new 一个json对象
            JSONObject jsonObject = new JSONObject(textFromStream);
            //2.---获取其中想要的元素
            JSONObject result = (JSONObject) jsonObject.get("result");
            //3.通过键值对取值
            op= (String) result.get("operators");
            city = (String) result.get("style_citynm");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return op + "-----" + city;
    }
}