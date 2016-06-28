package com.example.chenqiao.day_14_yinyuancalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_1;
    private EditText et_2;
    private RadioGroup rb_1;
    private RadioGroup rb_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_1 = (EditText) findViewById(R.id.et_1);
        et_2 = (EditText) findViewById(R.id.et_2);
        rb_1 = (RadioGroup) findViewById(R.id.rb_1);
        rb_2 = (RadioGroup) findViewById(R.id.rb_2);
    }


    public void calculator(View view){
        String name1 = et_1.getText().toString().trim();
        String name2 = et_2.getText().toString().trim();
        if (name1.equals("")|name2.equals("")){
            Toast.makeText(this,"亲，必须填写两个人的名字哦",Toast.LENGTH_SHORT).show();
            return;
        }
        int sex1 = 0;
        int sex2 = 0;
        int radioButtonId1 = rb_1.getCheckedRadioButtonId();
        int radioButtonId2 = rb_2.getCheckedRadioButtonId();
        switch (radioButtonId1){
            case R.id.rb_1_male:
                sex1 = 1;
                break;
            case R.id.rb_1_female:
                sex1 = 2;
                break;
            case R.id.rb_1_other:
                sex1 = 3;
                break;
        }
        if (sex1 == 0){
            Toast.makeText(this,"请选择性别",Toast.LENGTH_SHORT).show();
            return;
        }

        switch (radioButtonId2){
            case R.id.rb_2_male:
                sex2 = 1;
                break;
            case R.id.rb_2_female:
                sex2 = 2;
                break;
            case R.id.rb_2_other:
                sex2 = 3;
                break;
        }
        if (sex2 == 0){
            Toast.makeText(this,"请选择性别",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, ResultActivity.class);

        intent.putExtra("name1",name1);
        intent.putExtra("name2",name2);
        intent.putExtra("sex1",sex1);
        intent.putExtra("sex2",sex2);

        startActivity(intent);




    }
}
