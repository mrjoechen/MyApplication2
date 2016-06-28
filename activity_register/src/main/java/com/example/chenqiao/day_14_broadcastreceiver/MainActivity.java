package com.example.chenqiao.day_14_broadcastreceiver;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText et_username;
    private EditText et_password;
    private EditText et_repassword;
    private EditText et_gender;
    private EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        et_repassword = (EditText) findViewById(R.id.repassword);
        et_gender = (EditText) findViewById(R.id.gender);
        et_email = (EditText) findViewById(R.id.email);


    }


    public void register(View view){
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String repassword = et_repassword.getText().toString().trim();
        String gender = et_gender.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        if (username.equals("")) {
            Toast.makeText(this,"用户名不能为空!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals("")) {
            Toast.makeText(this,"密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        if (gender.equals("")) {
            Toast.makeText(this,"性别不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }

        if(repassword==null ||repassword.trim().equals("")){

            Toast.makeText(this,"确认密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }else{
            if(!password.equals(repassword)){
                Toast.makeText(this, "密码不一致！", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (email.equals("")) {
            Toast.makeText(this,"邮箱不能为空！",Toast.LENGTH_SHORT).show();
//				System.out.println("不能为空！");
            return;
        }

        User user = new User(username,password,gender,email);

        Intent intent = new Intent(this,RegisterInfoActivity.class);
        intent.putExtra("userinfo",user);
        startActivity(intent);

        Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();


    }


}
