package com.example.chenqiao.day_upload_image;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;




public class MainActivity extends ActionBarActivity {



    ImageView iv_image;

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iv_image = (ImageView) findViewById(R.id.iv_image);
    }

    public void select(View v){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap;
        ContentResolver resolver = getContentResolver();
        if (resultCode==RESULT_OK){//结果码Ok之后，在继续执行下面的东西

            if (requestCode==10){
                Uri pic = data.getData();
                iv_image.setImageURI(pic);


                try {
                    bitmap = MediaStore.Images.Media.getBitmap(resolver, pic);

                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = managedQuery(pic, proj, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                     path = cursor.getString(column_index);
                    System.out.print(path);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



// String path = "http://192.168.3.6:8080/Day_2_13_FileUpload/servlet/UploadServlet2";
    public void upload(View view){

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.3.6:8080/Day_2_13_FileUpload/servlet/UploadServlet2";
        Log.i("uri",path);
        File file = new File(path);
        RequestParams requestParams = new RequestParams();//使用requestParams进行传递数据
        try {
            requestParams.put("file",file);//键值对
            client.post(url, requestParams,new AsyncHttpResponseHandler() {//文件保存在rp中发送到服务器
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {

                    String text = new String(bytes);
                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
