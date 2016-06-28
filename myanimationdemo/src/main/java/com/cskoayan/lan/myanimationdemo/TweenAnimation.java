package com.cskoayan.lan.myanimationdemo;

 import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
 import android.view.animation.DecelerateInterpolator;
 import android.view.animation.Interpolator;
 import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class TweenAnimation extends ActionBarActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);
        iv = (ImageView) findViewById(R.id.iv_cyx);
    }


    //放大缩小该控件
    public void scale(View v){


        //如何找到res/anim?
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.myscale);
        //给iv控件加上这种动画效果
        iv.startAnimation(animation);
    }

    //更改透明度
    public void alpha(View v){

         //如何找到res/anim?
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.myalpha);
        //给iv控件加上这种动画效果
        iv.startAnimation(animation);
    }



    //平移动画
    public void translate(View v){

        //如何找到res/anim?
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.mytranslate);
        //给iv控件加上这种动画效果
        iv.startAnimation(animation);
    }




    //旋转动画
    public void rotate(View v){

        //如何找到res/anim?
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.myrotate);
        //给iv控件加上这种动画效果
        iv.startAnimation(animation);
    }



    //------------------使用代码实现----------------------------

    //放大缩小该控件
    public void scale2(View v){


        ScaleAnimation animation = new ScaleAnimation(1,2,1,2);
        animation.setDuration(2000);
//      animation.setRepeatCount(2);//做完之后，重复来两次
//      animation.setRepeatMode(Animation.REVERSE);  //1 重新开始    2
        animation.setFillAfter(true);

        //设置插值器
        Interpolator interpolator = new DecelerateInterpolator();
        animation.setInterpolator(interpolator);

        iv.startAnimation(animation);

    }

    //更改透明度
    public void alpha2(View v){


        AlphaAnimation  animation = new AlphaAnimation(100,0);
        animation.setDuration(2000);
        iv.startAnimation(animation);

    }



    //平移动画
    public void translate2(View v){

        TranslateAnimation translateAnimation = new TranslateAnimation(0,100,0,100);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        iv.startAnimation(translateAnimation);

    }




    //旋转动画
    public void rotate2(View v){


        RotateAnimation rotateAnimation = new RotateAnimation
                (0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        iv.startAnimation(rotateAnimation);

    }

    public void animset(View v){

        AnimationSet set = new AnimationSet(false);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1,2,1,2);
        scaleAnimation.setDuration(2000);

        AlphaAnimation  alphaAnimation = new AlphaAnimation(100,0);
        alphaAnimation.setDuration(2000);

        RotateAnimation rotateAnimation = new RotateAnimation
                (0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);

        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.addAnimation(rotateAnimation);

        iv.startAnimation(set);


    }

    public void animset_xml(View v){

        //如何找到res/anim?
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.myanimset);
        //给iv控件加上这种动画效果
        iv.startAnimation(animation);
    }


}
