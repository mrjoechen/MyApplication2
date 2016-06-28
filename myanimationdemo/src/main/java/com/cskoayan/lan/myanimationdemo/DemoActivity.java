package com.cskoayan.lan.myanimationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

public class DemoActivity extends ActionBarActivity implements View.OnClickListener {

     ImageView iv_a  ;
     ImageView iv_b  ;
     ImageView iv_c  ;
     ImageView iv_d  ;
     ImageView iv_e  ;

    boolean flag ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

         iv_a = (ImageView) findViewById(R.id.center);
         iv_b = (ImageView) findViewById(R.id.b);
         iv_c = (ImageView) findViewById(R.id.c);
         iv_d = (ImageView) findViewById(R.id.d);
         iv_e = (ImageView) findViewById(R.id.e);

        iv_a.setOnClickListener(this);
        iv_b.setOnClickListener(this);
        iv_c.setOnClickListener(this);
        iv_d.setOnClickListener(this);
        iv_e.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.center:
                //Toast.makeText(DemoActivity.this, "centerclicked", Toast.LENGTH_SHORT).show();
                if (!flag)
                   showMenu();
                else
                   hideMenu();
                break;

            case R.id.d:
                Toast.makeText(DemoActivity.this, "camera clicked", Toast.LENGTH_SHORT).show();
                break;


        }

    }

    //把左边的图片移出来
    public void  showMenu(){

/*        ObjectAnimator iv_bAnimation = ObjectAnimator.ofFloat(iv_b,"TranslationX",0,-100);

        iv_bAnimation.setDuration(500);
        iv_bAnimation.start();

        ObjectAnimator iv_cAnimation = ObjectAnimator.ofFloat(iv_c,"TranslationX",0,100);

        iv_cAnimation.setDuration(500);
        iv_cAnimation.start();

        ObjectAnimator iv_dAnimation = ObjectAnimator.ofFloat(iv_d,"TranslationY",0,-100);

        iv_dAnimation.setDuration(500);
        iv_dAnimation.start();

        ObjectAnimator iv_eAnimation = ObjectAnimator.ofFloat(iv_e,"TranslationY",0,100);

        iv_eAnimation.setDuration(500);
        iv_eAnimation.start();*/


        ObjectAnimator iv_aAnimation = ObjectAnimator.ofFloat(iv_a,"Alpha",1,0.3f);

        ObjectAnimator iv_bAnimation = ObjectAnimator.ofFloat(iv_b,"TranslationX",0,-100);
        ObjectAnimator iv_cAnimation = ObjectAnimator.ofFloat(iv_c,"TranslationX",0,100);
        ObjectAnimator iv_dAnimation = ObjectAnimator.ofFloat(iv_d,"TranslationY",0,-100);
        ObjectAnimator iv_eAnimation = ObjectAnimator.ofFloat(iv_e,"TranslationY",0,100);

        //iv_bAnimation.setInterpolator();
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());

        set.playTogether(iv_aAnimation,iv_bAnimation, iv_cAnimation, iv_dAnimation, iv_eAnimation);
        set.start();


        flag = true;



    };

    //把左边的图片移出来
    public void   hideMenu(){


      /*  ObjectAnimator iv_bAnimation = ObjectAnimator.ofFloat(iv_b,"TranslationX",-100,0);

        iv_bAnimation.setDuration(500);
        iv_bAnimation.start();

        ObjectAnimator iv_cAnimation = ObjectAnimator.ofFloat(iv_c,"TranslationX",100,0);

        iv_cAnimation.setDuration(500);
        iv_cAnimation.start();

        ObjectAnimator iv_dAnimation = ObjectAnimator.ofFloat(iv_d,"TranslationY",-100,0);

        iv_dAnimation.setDuration(500);
        iv_dAnimation.start();

        ObjectAnimator iv_eAnimation = ObjectAnimator.ofFloat(iv_e,"TranslationY",100,0);

        iv_eAnimation.setDuration(500);
        iv_eAnimation.start();*/

        ObjectAnimator iv_aAnimation = ObjectAnimator.ofFloat(iv_a,"Alpha",0.3f,1);
        ObjectAnimator iv_bAnimation = ObjectAnimator.ofFloat(iv_b,"TranslationX",-100,0);
        ObjectAnimator iv_cAnimation = ObjectAnimator.ofFloat(iv_c,"TranslationX",100,0);
        ObjectAnimator iv_dAnimation = ObjectAnimator.ofFloat(iv_d,"TranslationY",-100,0);
        ObjectAnimator iv_eAnimation = ObjectAnimator.ofFloat(iv_e,"TranslationY",100,0);


        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());

        set.playTogether(iv_aAnimation,iv_bAnimation, iv_cAnimation, iv_dAnimation, iv_eAnimation);
        set.start();

        flag = false;

    }
}
