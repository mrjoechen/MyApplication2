package com.cskoayan.lan.myfragmentdemo2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Lan on 2016/3/16.
 */
public class MyFragmentA extends Fragment{



   /* @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
   */

    //必须重写的
    //Caused by: java.lang.IllegalStateException: Fragment com.cskoayan.lan.myfragmentdemo1.MyFragmentA did not create a view.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    /*    TextView tv = new TextView(getActivity());
        tv.setText("hello,fragemt A!");
        return  tv; *///super.onCreateView(inflater, container, savedInstanceState);


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
