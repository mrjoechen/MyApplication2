package com.example.lizi.weatherquery;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/* 实现一个查询制定城市天气的应用。
    天气信息课显示一天的，也可以显示多天的。
    具体显示哪些信息可以自行决定。
    要求：分别使用解析xml格式数据和解析json格式数据实现。使用哪种http工具包无要求。
 */
public class MainActivity extends ActionBarActivity {
    private EditText et_city;
    private TextView tv_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_city= (EditText) findViewById(R.id.et_city);
        tv_content = (TextView) findViewById(R.id.tv_content);
        Log.i("TAG","获取参数");
    }
    Handler myHandler = new Handler(){

        //该方法用来处理消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("TAG","处理消息");
                    tv_content.setText((String)msg.obj);
                    Toast.makeText(MainActivity.this, "json 解析", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(MainActivity.this,"xml 解析",Toast.LENGTH_LONG).show();
                    tv_content.setText((String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };
    public void getweather(View v){
        String city = et_city.getText().toString();
        Log.i("TAG","调用方法");
        getFileFromServer("http://api.k780.com:88/?app=weather.future&weaid="+city+"&appkey=14114&sign=ede788f7bf8d0678e281f660654ef995&format=json");

    }

    public void  getFileFromServer(String url){
        final String path=url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection httpurlConnection = (HttpURLConnection) url.openConnection();

                    httpurlConnection.setRequestMethod("GET");
                    httpurlConnection.setConnectTimeout(5000);

                    httpurlConnection.connect();
                    if(httpurlConnection.getResponseCode()==200){
                        InputStream is = httpurlConnection.getInputStream();
                        //通过解析Json后返回 weather
                        Log.i("TAG","取值");
                        String info = parsejson(is);
                        Message msg = myHandler.obtainMessage();
                        // 1 代表json解析的数据
                        msg.what=1;
                        msg.obj=info;
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
        String temperature="";
        String data="";
        String week_day="";
        String city="";
        String wind="";
        //new JSON对象        2.获取其中想要元素          3.通过键值对取值
        try {
            Log.i("TAG","开始json解析");
            JSONObject jsonObject = new JSONObject(textFromStream);
          //  JSONObject result = (JSONObject) jsonObject.get("result[0]");
                 /*  temperature = (String) result.get("temperature");
            data = (String) result.get("days");
            week_day = (String) result.get("week");
            city = (String) result.get("citynm");
            wind= (String) result.get("winp");*/

            JSONArray result = (JSONArray) jsonObject.get("result");
            JSONObject json= (JSONObject) result.get(0);
            temperature = (String) json.get("temperature");
            data = (String) json.get("days");
            week_day = (String) json.get("week");
            city = (String) json.get("citynm");
            wind= (String) json.get("wind");

            Log.i("天气状况:", city + "  " + data + "  " + week_day + "  " + temperature + "  " + wind);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return city+"--"+data+"--"+week_day+"--"+temperature+"--"+wind;
    }
}
