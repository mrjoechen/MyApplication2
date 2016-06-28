package com.example.chenqiao.animation_homework;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
假设支付宝有个计算收益的画面，用户进入该画面后，显示收益的textview内的文字从0 变到1，变到2... 最后变为当前收益，比如36.00.
使用属性动画实现该效果。
 */
public class MainActivity extends AppCompatActivity  {

    private TextView tv_1;
    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_1 = (TextView) findViewById(R.id.tv_1);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);


    }

    public void check(View view){
        Toast.makeText(this,"正在查询，请稍后。。。",Toast.LENGTH_SHORT).show();
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100.00f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                tv_1.setText((animatedValue+"").substring(0, (animatedValue+"").indexOf(".")+2));
            }
        });

        animator.start();
    }

    public void jump1(View view){

//        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }
    public void jump2(View view){


        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);


    }
    public void jump3(View view){


        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


    }



}
