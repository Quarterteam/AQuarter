package com.a.quarter.view.adapter.mycollect;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.collect.MyCollectItemBean;
import com.a.quarter.model.utils.AnimUtils;
import com.a.quarter.utils.IconChangeUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import media.IjkVideoView;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/26.
 */

public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.MyHolder>  {
    private Context context;
    private List<MyCollectItemBean>list=new ArrayList<>();

    private View view1;
    private View popView;
    private PopupWindow popupWindow;
    private LinearLayoutManager linearLayoutManager;
    private MyCollectItemAdapter myCollectItemAdapter;

    public MyCollectAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<MyCollectItemBean> mlist){
        if (mlist!=null){
            list.addAll(mlist);
        }
    };
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = View.inflate(context, R.layout.item_userpage, null);
        MyHolder myHolder = new MyHolder(view1);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        //显示 删除编辑框
if (list.get(position).isDelVisibility()){
    holder.boxDel.setVisibility(View.VISIBLE);
}else{
    holder.boxDel.setVisibility(View.GONE);
}
        //设置头像

        holder.tvTitle.setText("天蝎喝牛奶");
        holder.tvTime.setText("2017-7-20  14:20");
        holder.tvPublish.setText("天气美美的，适合");
        System.out.println("--co--"+holder.boxDel);
        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(context);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        myCollectItemAdapter = new MyCollectItemAdapter(context);
        holder.recyclerView.setAdapter(myCollectItemAdapter);
        //点击展开动画
        holder.jokeImageRigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (list.get(position).isAnimshow()) {
                    list.get(position).setAnimshow(false);
                    holder.jokeImageRigth.setImageResource(R.mipmap.icon_open);
                    AnimUtils.setAlpha(1f, 0f, View.VISIBLE, holder.copylinkTextView, holder.reportTextView, holder.shiledTextView, holder.jokeImageRigth);

                } else {
                    list.get(position).setAnimshow(true);
                    holder.jokeImageRigth.setImageResource(R.mipmap.icon_packup);
                    AnimUtils.setAlpha(0f, 1f, View.VISIBLE, holder.copylinkTextView, holder.reportTextView, holder.shiledTextView, holder.jokeImageRigth);
                }
            }
        });



       holder.ivLike.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (list.get(position).isLike()){
                   list.get(position).setLike(false);
                   IconChangeUtils.setIconChangeDefault(context,holder.ivLike,R.mipmap.details_xi_whilt);
               }else{
                   list.get(position).setLike(true);
                   IconChangeUtils.setIconChangeCheck(context,holder.ivLike,R.mipmap.details_xi_a);
               }

           }
       });
        holder.ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).isCollect()){
                    list.get(position).setCollect(false);
                    IconChangeUtils.setIconChangeDefault(context,holder.ivCollect,R.mipmap.my_collect_whilt);
                }else{
                    list.get(position).setCollect(true);
                    IconChangeUtils.setIconChangeCheck(context,holder.ivCollect,R.mipmap.star_checked_whilt);
                }

            }
        });

}
    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
       CheckBox boxDel;
        SimpleDraweeView ImageTitle;
        TextView tvTitle;
        TextView tvTime;
        TextView tvPublish;
        ImageView jokeImageRigth;
        TextView copylinkTextView;
        TextView reportTextView;
        TextView shiledTextView;
        RecyclerView recyclerView;
        IjkVideoView ijkVideoView;
        ImageView ivPlay;
        LinearLayout linear;
        TextView ivLike;
        TextView ivCollect;
        TextView ivShare;
        TextView ivComment;

        public MyHolder(View itemView) {
            super(itemView);
            boxDel= (CheckBox) itemView.findViewById(R.id.box_del);
            ImageTitle = (SimpleDraweeView) itemView.findViewById(R.id.item_userpage_Image_title);
            tvTitle = (TextView) itemView.findViewById(R.id.item_userpage_Text_title);
            tvTime = (TextView) itemView.findViewById(R.id.item_userpage_Text_time);
            tvPublish = (TextView) itemView.findViewById(R.id.item_userpage_Text);
            jokeImageRigth = (ImageView) itemView.findViewById(R.id.item_userpage_image_show);
            copylinkTextView = (TextView) itemView.findViewById(R.id.item_userpage_text_copylink);
            reportTextView = (TextView) itemView.findViewById(R.id.item_userpage_text_report);
            shiledTextView = (TextView) itemView.findViewById(R.id.item_userpage_text_shiled);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.item_userpage_recycler);
            ijkVideoView= (IjkVideoView) itemView.findViewById(R.id.item_userpage_video);
            ivLike= (TextView) itemView.findViewById(R.id.item_userpage_iv_like);
            ivCollect= (TextView) itemView.findViewById(R.id.item_userpage_iv_collect);
            ivShare= (TextView) itemView.findViewById(R.id.item_userpage_iv_share);
            ivComment= (TextView) itemView.findViewById(R.id.item_userpage_iv_comment);
            linear= (LinearLayout) itemView.findViewById(R.id.item_userpage_linear);
            ivPlay= (ImageView) itemView.findViewById(R.id.item_userpage_play);
        }
    }
}
