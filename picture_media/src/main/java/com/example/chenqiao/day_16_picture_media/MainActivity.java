package com.example.chenqiao.day_16_picture_media;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_image;
    String path;
    private int width;
    private int height;
    private TextView img_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_image = (ImageView) findViewById(R.id.iv_image);
        img_info = (TextView) findViewById(R.id.img_info);
        //获取手机分辨率
        WindowManager wm =  (WindowManager) getSystemService(WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        System.out.println("width:"+ width + "---"+"height:"+ height);
    }



    public void browse(View view){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent, 10);

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){//结果码Ok之后，在继续执行下面的东西

            if (requestCode==10){
                Uri pic = data.getData();

                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = managedQuery(pic, proj, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    path = cursor.getString(column_index);
                    System.out.print(path);

                    load();


            }
        }

    }

    public void load(){

        //直接解析图片会出现OOM
//        Bitmap bitmap = BitmapFactory.decodeFile("/storage/sdcard/2.jpg");
//        iv_image.setImageBitmap(bitmap);

        BitmapFactory.Options options = new BitmapFactory.Options();
        //解码器获取图片的宽和高信息,暂时不解析位图
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);
        //获取图片的宽和高信息
        int imgWidth = options.outWidth;
        int imgHeight = options.outHeight;
        System.out.println("图片的宽和高:"+imgWidth+"----"+imgHeight);
        //计算缩放缩放比
        int scale = 1;
        int scaleX = imgWidth/width;
        int scaleY = imgHeight/height;
        if (scaleX>=scaleY && scaleX>scale) {
            scale = scaleX;
        }
        if (scaleY > scaleX && scaleY>scale) {
            scale = scaleY;
        }

        System.out.println("缩放比:"+scale);
        //根据缩放比进行显示
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path,options);
        iv_image.setImageBitmap(bitmap);


        ExifInterface ei = null;
        try {
            ei = new ExifInterface(path);
            String date = ei.getAttribute(ExifInterface.TAG_DATETIME);
            String width = ei.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
            String height = ei.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);
            String make = ei.getAttribute(ExifInterface.TAG_MAKE);
            String model = ei.getAttribute(ExifInterface.TAG_MODEL);
            String iso = ei.getAttribute(ExifInterface.TAG_ISO);



            img_info.setText(new Picture(date, height, iso, make, model, width).toString());





        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
