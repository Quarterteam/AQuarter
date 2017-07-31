package com.a.quarter.view.adapter.msginform;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.exa.framelib_rrm.base.view.view.CircleImageView;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/26.
 */

public class MsgListAdapter extends RecyclerView.Adapter<MsgListAdapter.MyHolder> {
    private Context context;

    public MsgListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_msg, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CircleImageView ImageTitle;
        ImageView Iv_Rigth;
        TextView tv_name;
        TextView tv_wokename;
        TextView tv_info;
        TextView tv_time;
        TextView tv_msg;


        public MyHolder(View itemView) {
            super(itemView);
            ImageTitle = (CircleImageView) itemView.findViewById(R.id.Iv_msg_title);
            Iv_Rigth = (ImageView) itemView.findViewById(R.id.Iv_msg_rigth);
            tv_name = (TextView) itemView.findViewById(R.id.tv_msg_name);
            tv_info = (TextView) itemView.findViewById(R.id.tv_msg_info);
            tv_wokename = (TextView) itemView.findViewById(R.id.tv_msg_wokename);
            tv_time= (TextView) itemView.findViewById(R.id.tv_msg_time);
            tv_msg = (TextView) itemView.findViewById(R.id.tv_msg);
        }
    }
}
