package com.a.quarter.view.adapter.mywork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a.quarter.R;
import com.a.quarter.view.adapter.video.VHotFragDetails;

import java.util.ArrayList;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class LocationAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Integer> list = new ArrayList<>();

    public LocationAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Integer> datas) {
        if (datas != null) {
            list.addAll(datas);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyHolder(View.inflate(context, R.layout.item_location, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder holder1 = (MyHolder) holder;
//        holder1.imageview.setImageURI(list.get(position));
        holder1.imageview.setImageResource(list.get(position));

        holder1.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2017/8/1 传值：url
                Intent intent = new Intent(context, VHotFragDetails.class);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {


        return list.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView imageview;

        public MyHolder(View itemView) {
            super(itemView);

            imageview = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }

}
