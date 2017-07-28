package com.a.quarter.view.adapter.userpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.utils.AnimUtils;
import com.exa.framelib_rrm.base.view.view.CircleImageView;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/26.
 */

public class UserPageAdapter extends RecyclerView.Adapter<UserPageAdapter.MyHolder> {
    private Context context;
    private boolean flag = true;

    public UserPageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_userpage, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {

        holder.jokeImageRigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag == true) {
                    flag = false;
                    holder.jokeImageRigth.setImageResource(R.mipmap.packup2);
                    AnimUtils.setTransRot(0f, -90f, 0f, 1f, View.VISIBLE, holder.copylinkTextView, holder.reportTextView, holder.shiledTextView, holder.jokeImageRigth);
                } else {
                    flag = true;
                    holder.jokeImageRigth.setImageResource(R.mipmap.icon_open);
                    AnimUtils.setTransRot(0f, 90f, 1f, 0f, View.VISIBLE, holder.copylinkTextView, holder.reportTextView, holder.shiledTextView, holder.jokeImageRigth);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        com.exa.framelib_rrm.base.view.view.CircleImageView ImageTitle;
        TextView TextTitle;
        TextView TextTime;
        ImageView jokeImageRigth;
        TextView copylinkTextView;
        TextView reportTextView;
        TextView shiledTextView;

        public MyHolder(View itemView) {
            super(itemView);
            ImageTitle = (CircleImageView) itemView.findViewById(R.id.item_userpage_Image_title);
            TextTitle = (TextView) itemView.findViewById(R.id.item_userpage_Text_title);
            TextTime = (TextView) itemView.findViewById(R.id.item_userpage_Text_time);
            jokeImageRigth = (ImageView) itemView.findViewById(R.id.item_userpage_image_show);
            copylinkTextView = (TextView) itemView.findViewById(R.id.item_userpage_text_copylink);
            reportTextView = (TextView) itemView.findViewById(R.id.item_userpage_text_report);
            shiledTextView = (TextView) itemView.findViewById(R.id.item_userpage_text_shiled);
        }
    }
}
