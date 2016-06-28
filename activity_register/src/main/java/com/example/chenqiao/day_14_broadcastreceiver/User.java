package com.example.chenqiao.day_14_broadcastreceiver;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.UnknownServiceException;

/**
 * Created by CHENQIAO on 2016/3/8.
 */
public class User implements Parcelable{
    private String username;
    private String password;
    private String gender;
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public User(String username,String password,String gender, String email ) {
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "您的用户信息：" +"\n"+
                "用户名:" + username + "\n" +
                "密码:" + password + "\n" +
              "性别："+gender+"\n"+
                "邮箱:"+email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(gender);
        dest.writeString(email);

    }


    public static Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {


            //第一步从包裹里把信息拿出来，
            String username = source.readString();
            String password = source.readString();
            String gender = source.readString();
            String email = source.readString();

            //第二步，把信息还原成info类。
            User info = new User(username,password,gender,email);

            return info;
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };
}
