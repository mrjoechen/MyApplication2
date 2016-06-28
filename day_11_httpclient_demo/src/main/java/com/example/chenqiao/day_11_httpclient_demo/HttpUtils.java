package com.example.chenqiao.day_11_httpclient_demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by CHENQIAO on 2016/3/4.
 */
public class HttpUtils {

    public static String getTextFromStream(InputStream is){

//        byte[] buffer = new byte[1024*1024];
        //此流将数据保存在？？ 内存中。
        ByteArrayOutputStream os  = new ByteArrayOutputStream();

        byte[] bytes= new byte[1024];
        int length= 0;
        try {
            while ((length=is.read(bytes,0,1024))!=-1){
                os.write(bytes,0,length);
            }
            is.close();
            os.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes1 = os.toByteArray();
        String result = new String(bytes1,0,bytes1.length);

        return  result;
    }


    public static byte[] getbyteArrayFromStream(InputStream is){

//        byte[] buffer = new byte[1024*1024];
        //此流将数据保存在？？ 内存中。
        ByteArrayOutputStream os  = new ByteArrayOutputStream();

        byte[] bytes= new byte[1024];
        int length= 0;
        try {
            while ((length=is.read(bytes,0,1024))!=-1){
                os.write(bytes,0,length);
            }
            is.close();
            os.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes1 = os.toByteArray();

        return  bytes1;
    }

}
