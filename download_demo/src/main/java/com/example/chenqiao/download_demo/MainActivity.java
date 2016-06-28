package com.example.chenqiao.download_demo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll_pb;
    private List<ProgressBar> pbLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_pb = (LinearLayout) findViewById(R.id.ll_pb);
        pbLists = new ArrayList<ProgressBar>();
    }


    public void download(View view) {

        final String path = "http://192.168.3.55:8080/Test/gem.mp4";
        final int thread_number = 3;


        ll_pb.removeAllViews();

        pbLists.clear();
        for (int i = 0; i < thread_number; i++) {

            //[1]把我定义的item布局转换成一个view对象
            ProgressBar pbView = (ProgressBar) View.inflate(getApplicationContext(), R.layout.pb_item, null);

            //[2]把pbView 添加到集合中
            pbLists.add(pbView);

            //[3]动态的添加进度条
            ll_pb.addView(pbView);

        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    try {
                        URL url = new URL(path);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(5000);
                        int responseCode = httpURLConnection.getResponseCode();
                        if (responseCode == 200) {
                            int contentLength = httpURLConnection.getContentLength();

                            RandomAccessFile randomAccessFile = new RandomAccessFile(getFilename(path), "rw");
                            randomAccessFile.setLength(contentLength);

                            int size = contentLength / thread_number;

                            for (int i = 0; i < thread_number; i++) {

                                int startindex = i * size;
                                int endindex = (i + 1) * size - 1;
                                if (i == thread_number - 1) {
                                    endindex = contentLength - 1;
                                }
                                new DownLoadThread(path, startindex, endindex, i).start();
                                System.out.println("length---" + contentLength + " ,thread" + i + "开始下载----" + "length:" + (endindex - startindex));
                                Toast.makeText(MainActivity.this,"Thread"+i+"开始下载",Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    class DownLoadThread extends Thread {
        String path;
        int startindex;
        int endindex;
        int threadid;
        private int PbMaxSize;


        public DownLoadThread(String path, int startindex, int endindex, int threadid) {
            this.path = path;
            this.startindex = startindex;
            this.endindex = endindex;
            this.threadid = threadid;
        }

        @Override
        public void run() {
            super.run();

            try {
                PbMaxSize = endindex - startindex;
                URL url = new URL(path);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(5000);
                urlConnection.setRequestProperty("Range", "bytes=" + startindex + "-" + endindex);
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == 206) {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(getFilename(path), "rw");
                    randomAccessFile.seek(startindex);
                    InputStream inputStream = urlConnection.getInputStream();
                    int length = -1;
                    byte[] bytes = new byte[1024 * 1024];
                    int total = 0;
                    while ((length = inputStream.read(bytes)) != -1) {
                        total +=length;
                        randomAccessFile.write(bytes, 0, length);
                        pbLists.get(threadid).setMax(PbMaxSize);//设置进度条的最大值
                        pbLists.get(threadid).setProgress(total);//设置当前进度条的当前进度
                    }
                    randomAccessFile.close();


                    System.out.println("Thread" + threadid + "下载完成");
//                    Toast.makeText(MainActivity.this,"Thread" + threadid + "下载完成",Toast.LENGTH_SHORT).show();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFilename(String path) {

        int start = path.lastIndexOf("/") + 1;
        String substring = path.substring(start);

        String fileName = Environment.getExternalStorageDirectory().getPath() + "/" + substring;
        return fileName;

    }

}



