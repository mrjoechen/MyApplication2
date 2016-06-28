package com.example.chenqiao.day_12_weather_homework;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private EditText city;
    private TextView weather;
    private ArrayList<Item> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (EditText) findViewById(R.id.et_city);

    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("=====" + position);
            View view;
            if (convertView == null) {
                view = View.inflate(MainActivity.this, R.layout.item, null);
            } else {
                view = convertView;
            }
            TextView key = (TextView) view.findViewById(R.id.key);                    // 获取这个新生成的View中的TextView
            TextView value = (TextView) view.findViewById(R.id.value);
            Item item = (Item) getItem(position);
            // 给TextView设置文本
            key.setText(item.getKey()+":");
            value.setText(item.getValue());
            return view;

        }
    }

   Handler handler = new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);


            //找到listview控件
           ListView listview = (ListView) findViewById(R.id.listview);
           arrayList = new ArrayList();

           Item item1 = new Item("城市",((Weather)msg.obj).getCity());
           Item item2 = new Item("当前温度",((Weather)msg.obj).getTemp());
           Item item3 = new Item("风力",((Weather)msg.obj).getWind());
           Item item4 = new Item("日出时间",((Weather)msg.obj).getSunrise());
           Item item5 = new Item("日落时间",((Weather)msg.obj).getSunset());
           Item item6 = new Item("湿度",((Weather)msg.obj).getHumidity());
           Item item7 = new Item("更新时间",((Weather)msg.obj).getUpdatetime());
           Item item8 = new Item("pm2.5",((Weather)msg.obj).getPm25());
           Item item9 = new Item("空气质量",((Weather)msg.obj).getQuality());
           Item item10 = new Item("建议",((Weather)msg.obj).getSuggest());



           arrayList.add(item1);
           arrayList.add(item2);
           arrayList.add(item3);
           arrayList.add(item4);
           arrayList.add(item5);
           arrayList.add(item6);
           arrayList.add(item7);
           arrayList.add(item8);
           arrayList.add(item9);
           arrayList.add(item10);



           listview.setAdapter(new MyAdapter());
           Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
       }
   };

    public void query(View view){
        new Thread(){
            public void run(){
                try {
                    URL url = new URL("http://wthrcdn.etouch.cn/WeatherApi?city="+ URLEncoder.encode(city.getText().toString(), "utf-8"));

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    //设置请求方式，和请求等待时间
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(3000);
                    //建立连接
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();


                    //发送天气信息给Handler
                    Message msg = new Message();
                    msg.obj = WeatherParser.parserXml(inputStream);
                    handler.sendMessage(msg);

                    File file = new File(getCacheDir(),"weatherinfo.xml");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    int len = 0;
                    byte[] bytes = new byte[1024];
                    while((len = inputStream.read(bytes,0,1024))!=-1) {
                        fileOutputStream.write(bytes, 0, len);
                    }
                    fileOutputStream.close();

                    Log.i("wwwwwwwwwwwwww", "wwwwwwwwwwwwwwwwwwwwwwwwwwwww");




                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
