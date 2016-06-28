package com.example.lizi.queryphonelocation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lizi on 2016/3/6.
 */
public class HttpUtils {//  ------- >记得加上static
    public static String getTextFromStream(InputStream is){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            while((len = is.read(bytes))!=-1){
                os.write(bytes,0,len);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes1 = os.toByteArray();
        String result = new String(bytes1);
        return result;
    }
    //返回数组
    public static byte[] getByteArrayFromStream(InputStream inputStream){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            while((len = inputStream.read(bytes))!=-1){
                os.write(bytes,0,len);
            }
            inputStream.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes1 = os.toByteArray();
        return bytes1;
    }
}
