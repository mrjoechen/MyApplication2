package com.example.lizi.xmlparseforinternet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lizi on 2016/3/5.
 */
public class HttpUtils {
    public static String getTextFromStream (InputStream is){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len= 0;
        try {
            while((len=is.read(bytes))!=-1){
                os.write(bytes,0,len);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//转换成字节数组
        byte[] bytes1 = os.toByteArray();
        String resullt = new String(bytes1,0,bytes1.length);
        return resullt;
    }
    //另一种方法，-- xml && json 解析 --
    public static byte[] getbyteArrayFromStream(InputStream is){
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
