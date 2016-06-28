package com.example.chenqiao.day_11_httpclient_demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);

    }
    Handler MyHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                case 1:

                    String text = (String) msg.obj;
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
                    break;

                case -1:
                    Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };






    //get方式提交数据
    public void httpclient_get(View view){

        new Thread(){public void run(){
            try {
                //获取用户名和密码
                String u = et_username.getText().toString();
                String p = et_password.getText().toString();


                String path = "http://192.168.3.36:8080/Day_2_19_MVC/servlet/LoginServlet?username="+u+"&password="+p;
                //获取httpclient实例
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(path);
                //执行请求,获取状态码
                HttpResponse response = client.execute(get);
                int code = response.getStatusLine().getStatusCode();
                if (code == 200){
                    Log.i("httpclient", "get server response!");
                    InputStream inputStream = response.getEntity().getContent();
                    String textFromStream = HttpUtils.getTextFromStream(inputStream);
                    Message msg = MyHandler.obtainMessage();
                    msg.what=1;
                    msg.obj = textFromStream;
                    MyHandler.sendMessage(msg);
                } else
                {
                    Message msg = MyHandler.obtainMessage();
                    msg.what=-1;
                    MyHandler.sendMessage(msg);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        };}.start();


    }


    //封装toast方法  该toast方法执行在主线程
    public void showToast(final String content){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                //该方法一定是执行主线程
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();


            }
        });

    }
    //post方式提交数据
    public void httpclient_post(View view){

        new Thread(){public void run() {

            try {
                //获取用户名和密码
                String name = et_username.getText().toString();
                String pwd = et_password.getText().toString();
                String path = "http://192.168.3.18:8080/Day_2_19_MVC/servlet/LoginServlet";

                //以httpClient 方式进行post 提交
                DefaultHttpClient client = new DefaultHttpClient();
                //准备post 请求
                HttpPost post = new HttpPost(path);
                //准备parameters
                List<NameValuePair> lists = new ArrayList<NameValuePair>();
                //准备 NameValuePair 即要提交的用户名 和密码
                BasicNameValuePair nameValuePair = new BasicNameValuePair("username",name);
                BasicNameValuePair pwdValuePair = new BasicNameValuePair("password",pwd);
                //把nameValuePair  和 pwdValuePair  加入到集合中
                lists.add(nameValuePair);
                lists.add(pwdValuePair);
                //准备entity实体
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(lists);
                //准备post方式提交的正文  以实体形式准备 (键值对)
                post.setEntity(entity);


                HttpResponse response = client.execute(post);
                //获取服务器返回的状态码
                int code = response.getStatusLine().getStatusCode();
                if (code == 200) {
                    //获取服务器返回的数据   以流的形式返回
                    InputStream inputStream = response.getEntity().getContent();

                    //把流转换成字符串

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int len = -1;
                    byte[] buffer = new byte[1024];
                    while((len=inputStream.read(buffer))!=-1){

                        baos.write(buffer, 0, len);
                    }
                    inputStream.close();
                    String content = new String(baos.toByteArray());

                    //展示结果
                    showToast(content);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        };}.start();


    }


    class MyHandlerInterfacer extends AsyncHttpResponseHandler {


        //收到服务器的响应。服务器成功响应
        @Override
        public void onSuccess(int i, Header[] headers, byte[] bytes) {

            if (i==200){
                String result = new String(bytes);
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
            }

        }
        //连接服务器失败的情况
        @Override
        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            Toast.makeText(MainActivity.this,"连接异常！",Toast.LENGTH_LONG).show();

        }
    }

    public void asyncHttpclient_get(View view){


        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();
        //1. 创建一个AsyncHttpClient；
        AsyncHttpClient  client = new AsyncHttpClient();
        //  2. （可选的）通过RequestParams对象设置请求参数；
        String url = "http://192.168.3.18:8080/Day_2_19_MVC/servlet/LoginServlet?username="+ URLEncoder.encode(username)+"&password="+password;
        client.get(url,new MyHandlerInterfacer());

        // ResponseHandlerInterface

    }


    public void asyncHttpclient_post(View view){


        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();
        //  1. 创建一个AsyncHttpClient对象
        AsyncHttpClient  client = new AsyncHttpClient();

        //  2. （可选的）通过RequestParams对象设置请求参数；
        RequestParams params = new RequestParams();
        params.put("username",username);
        params.put("password",password);

        //Post方法提交请求
        String url = "http://192.168.3.18:8080/Day_2_19_MVC/servlet/LoginServlet";
        client.post(url, params, new MyHandlerInterfacer());

    }

}
