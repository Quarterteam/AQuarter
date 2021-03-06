package com.a.quarter.view.adapter.video;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a.quarter.R;

import java.util.ArrayList;


/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VVicinityFragmentAdapter extends RecyclerView.Adapter<VVicinityFragmentAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Integer> list;

    public VVicinityFragmentAdapter(Context context, ArrayList<Integer> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vvicinityfrag_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mImageView.setImageResource(list.get(position));
         holder.mImageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(context, VHotFragDetails.class);
                 intent.putExtra("key","");
                 context.startActivity(intent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.ImageView);
        }
    }
}
