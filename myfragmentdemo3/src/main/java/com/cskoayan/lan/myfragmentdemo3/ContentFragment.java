package com.cskoayan.lan.myfragmentdemo3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

/**
 * Created by Lan on 2016/3/16.
 */
public class ContentFragment extends Fragment {


    private TextView tv_framentb;
    private ImageView iv_framentb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // return  new TextView(getActivity());//super.onCreateView(inflater, container, savedInstanceState);

        View v =   inflater.inflate(R.layout.fragemt_b,null);

        tv_framentb = (TextView) v.findViewById(R.id.tv_framentb);
        iv_framentb = (ImageView) v.findViewById(R.id.iv_framentb);

        return v;
    }


    public void setTitle(String title){

        tv_framentb.setText(title);

    }

    public void setImage(int Resid){

        iv_framentb.setImageResource(Resid);

    }
}
