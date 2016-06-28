package com.example.chenqiao.recycleviewdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private ArrayList<String> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);

        initData();
        initView();
    }

    private void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.rv_main);
        mRecycleView.setAdapter(new StaggeredAdapter(this, mArrayList));
        //设置布局管理器
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

    }

    private void initData() {

        mArrayList = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mArrayList.add((char) i + "");
        }

    }



    class StaggeredAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private LayoutInflater mInflater;
        private List<String> mList;
        private Context context;
        private List<Integer> mHeights = new ArrayList<>();

        public StaggeredAdapter(Context context,  List<String> mList) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.mList = mList;
            for (int i=0;i<mList.size();i++){
                mHeights.add((int) (100+Math.random()*300));
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = mInflater.inflate(R.layout.item_recycleview,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {


            ViewGroup.LayoutParams layoutParams = holder.tv_item.getLayoutParams();
            layoutParams.height = mHeights.get(position);
            holder.tv_item.setLayoutParams(layoutParams);

            holder.tv_item.setText(mList.get(position));

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
