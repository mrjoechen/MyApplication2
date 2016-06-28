package com.example.chenqiao.day_17_filter_demo;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private ImageView iv_image;
    private int width;
    private int height;
    private Bitmap bitmap;
    private SeekBar red;
    private SeekBar green;
    private SeekBar blue;
    private SeekBar alpha;
    private float[] array;
    private ColorMatrixColorFilter cf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_image = (ImageView) findViewById(R.id.iv_image);
        //获取手机分辨率
        WindowManager wm =  (WindowManager) getSystemService(WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        System.out.println("width:"+ width + "---"+"height:"+ height);

        red = (SeekBar) findViewById(R.id.red);
        green = (SeekBar) findViewById(R.id.green);
        blue = (SeekBar) findViewById(R.id.blue);
        alpha = (SeekBar) findViewById(R.id.alpha);
        array = new float[]{1,0,0,0,0,
                            0,1,0,0,0,
                            0,0,1,0,0,
                            0,0,0,1,0};
        red.setMax(255);
        green.setMax(255);
        blue.setMax(255);
        alpha.setMax(255);

        red.setOnSeekBarChangeListener(this);
        green.setOnSeekBarChangeListener(this);
        blue.setOnSeekBarChangeListener(this);
        alpha.setOnSeekBarChangeListener(this);

        cf = new ColorMatrixColorFilter(array);

        iv_image.setColorFilter(cf);

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
                String path = cursor.getString(column_index);
                System.out.print(path);
                load(path);
            }
        }
    }

    public void load(String path){

        //直接解析图片会出现OOM
//        Bitmap bitmap = BitmapFactory.decodeFile("/storage/sdcard/2.jpg");
//        iv_image.setImageBitmap(bitmap);

        BitmapFactory.Options options = new BitmapFactory.Options();
        //解码器获取图片的宽和高信息,暂时不解析
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
        bitmap = BitmapFactory.decodeFile(path, options);
        iv_image.setImageBitmap(bitmap);

    }

    public void save(View view){

        /**
         * format 保存图片的格式
         * quality 保存图片的质量
         *
         */
        try {

            Toast.makeText(this,"正在保存",Toast.LENGTH_LONG).show();
            File file = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis()+".png");
            FileOutputStream fos = new FileOutputStream(file);


            Paint paint = new Paint();
            paint.setColorFilter(cf);
            Bitmap destbiBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            Canvas canvas = new Canvas(destbiBitmap);
            //[2.4]开始作画
            canvas.drawBitmap(bitmap, new Matrix(), paint);

            destbiBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            //4.4及以后的系统是不允许这样发送这个广播
            /*//设置action
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
            //发送广播
            sendBroadcast(intent);*/

            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));


        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.red:
                array[4] = progress;
                break;
            case R.id.green:
                array[9] = progress;
                break;
            case R.id.blue:
                array[14] = progress;
                break;
            case R.id.alpha:
                array[19] =-progress;
                break;


        }

        cf=new ColorMatrixColorFilter(array);
        iv_image.setColorFilter(cf);


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {



    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {



    }
}
