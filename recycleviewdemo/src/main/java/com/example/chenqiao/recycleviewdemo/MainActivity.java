package com.example.chenqiao.recycleviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private ArrayList<String> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_listview:
                mRecycleView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_gridview:
                mRecycleView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.action_horizontal_list:
                mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_staggered:
                startActivity(new Intent(this,StaggeredGridLayoutActivity.class));
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.rv_main);
        mRecycleView.setAdapter(new MyRecycleViewAdapter(this, mArrayList));
        //设置布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

    }

    private void initData() {

        mArrayList = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mArrayList.add((char) i + "");
        }

    }

    class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private LayoutInflater mInflater;
        private List<String> mList;
        private Context context;

        public MyRecycleViewAdapter(Context context,  List<String> mList) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.mList = mList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = mInflater.inflate(R.layout.item_recycleview,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

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
