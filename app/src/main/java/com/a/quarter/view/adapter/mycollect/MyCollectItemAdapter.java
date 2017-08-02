package com.a.quarter.view.adapter.mycollect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a.quarter.R;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/28.
 */

public class MyCollectItemAdapter extends RecyclerView.Adapter<MyCollectItemAdapter.MyHolder>{
    private Context context;

    public MyCollectItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_userpage_item_recy, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
       holder.name.setText("骑猪上高速");
       holder.content.setText(":骑猪上高速");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView content;
        public MyHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.item_userpage_tv_name);
            content= (TextView) itemView.findViewById(R.id.item_userpage_tv_content);
        }
    }
}
