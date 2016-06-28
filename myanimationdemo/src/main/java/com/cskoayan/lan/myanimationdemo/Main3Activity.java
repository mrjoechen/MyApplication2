package com.cskoayan.lan.myanimationdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

public class Main3Activity extends ActionBarActivity {


    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bt = (Button) findViewById(R.id.showbt);


    }


    public void showtoast(View v){

        Toast.makeText(this,"吐个司",Toast.LENGTH_SHORT).show();

    }

    //使用补间动画移动该button
    public void move(View v){



        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,200);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);

        bt.startAnimation(translateAnimation);


    }

    //使用属性动画 valueAnimator实现button的移动

    public void move2(View v){


        //第一步，初始化Animator，给初始值
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setDuration(3000);
        valueanimator.setIntValues(0, 200,0,200);

        //第二步，设置 动画的监听

        valueanimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int animatedValue = (int) animation.getAnimatedValue();

                //怎么去移动button的位置？
                //View --> onMeasure -->onLayout--->onDraw

                bt.layout(0, animatedValue, bt.getWidth(), bt.getHeight() + animatedValue);

                Log.i("onAnimationUpdate", animatedValue + "");
            }
        });

        //第三步，启动动画

        valueanimator.start();

    }


    //使用属性动画 ObjectAnimator实现button的移动
    //可以去帮你修改属性值，而且是类似动画的每一帧，每一帧去修改一次
    //settranslateY()
    public void move3(View v){

        //实际上是valueAnimator的子类，进一步封装
/*      ObjectAnimator objectAnimator= ObjectAnimator.ofFloat( bt ,"TranslationX" ,0,200,0,200);
        objectAnimator.setDuration(2000);
        objectAnimator.start();*/


/*      ObjectAnimator objectAnimator= ObjectAnimator.ofFloat( bt ,"Alpha" ,0,1,0,1);
        objectAnimator.setDuration(2000);
        objectAnimator.start();*/


        ObjectAnimator objectAnimator= ObjectAnimator.ofFloat( bt ,"Rotation" ,0,180,0,360);
        objectAnimator.setDuration(2000);
        objectAnimator.start();


    }

}
