package com.cskoayan.lan.myfragmentdemo3;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Lan on 2016/3/16.
 */
public class TitleListFragment extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ListView lv = new ListView(getActivity());


        //Base Simple  Array

        final String[] titles = {"新闻1","新闻2","新闻3","新闻4","新闻5","新闻6","新闻7","新闻8"};

        final int[] images ={R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
                R.drawable.c6,R.drawable.c7,R.drawable.c8,};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,titles);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //拿到另一个fragment的引用需要用FragmentManager
                FragmentManager manager = getFragmentManager();
                final ContentFragment fragmentB  = (ContentFragment) manager.findFragmentById(R.id.fg_b);

                fragmentB.setTitle(titles[position]);
                fragmentB.setImage(images[position]);

            }
        });


        return   lv;//super.onCreateView(inflater, container, savedInstanceState);
    }


}
