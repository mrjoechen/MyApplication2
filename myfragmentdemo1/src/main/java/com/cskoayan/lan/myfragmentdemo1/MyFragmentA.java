package com.cskoayan.lan.myfragmentdemo1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Lan on 2016/3/16.
 */
public class MyFragmentA extends Fragment{


    private static final String TAG ="MyFragment" ;

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "onAttach");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");

    }

    //必须重写的
    //Caused by: java.lang.IllegalStateException: Fragment com.cskoayan.lan.myfragmentdemo1.MyFragmentA did not create a view.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    /*    TextView tv = new TextView(getActivity());
        tv.setText("hello,fragemt A!");
        return  tv; *///super.onCreateView(inflater, container, savedInstanceState);

        Log.i(TAG, "onCreateView");


        View v=   inflater.inflate(R.layout.fragemt_a,null);

        Button bt = (Button) v.findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return  v;
    }
}
