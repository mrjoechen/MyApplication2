package com.example.chenqiao.day_12_json_demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpUtils {
    public static String getTextFromStream(InputStream is){

//        byte[] buffer = new byte[1024*1024];
        //ByteArrayOutputStream将数据保存在内存中。
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
        //ByteArrayOutputStream此流将数据保存在内存中。
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
