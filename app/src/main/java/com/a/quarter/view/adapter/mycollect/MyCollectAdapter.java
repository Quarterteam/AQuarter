package com.a.quarter.view.adapter.mycollect;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import com.a.quarter.utils.FrescoCircleUtils;
import com.a.quarter.utils.QQLoginShareUtils;
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

public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.MyHolder> implements View.OnClickListener {
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
        linearLayoutManager = new LinearLayoutManager(context);
        myCollectItemAdapter = new MyCollectItemAdapter(context);
        view1 = View.inflate(context, R.layout.item_userpage, null);
        MyHolder myHolder = new MyHolder(view1);
        setPopwindow();
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        //显示
if (list.get(position).isVisibility()){
    holder.boxDel.setVisibility(View.VISIBLE);
}else{
    holder.boxDel.setVisibility(View.GONE);
}
        //设置头像
        FrescoCircleUtils.setImageViewCircle(holder.ImageTitle, Uri.parse("http://f2.kkmh.com/image/170119/lbejli3bs.webp-w180"));

        holder.tvTitle.setText("天蝎喝牛奶");
        holder.tvTime.setText("2017-7-20  14:20");
        holder.tvPublish.setText("天气美美的，适合");

        holder.recyclerView.setLayoutManager(linearLayoutManager);

        holder.recyclerView.setAdapter(myCollectItemAdapter);
        //动画
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


        //分享
        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.setAnimationStyle(R.style.Animation);
               popupWindow.showAsDropDown(view1,0,-30);


            }
        });

       holder.ivLike.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               setTopIcon(R.mipmap.details_xi, holder.ivLike);
           }
       });
        holder.ivCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              setTopIcon(R.mipmap.star_checked_whilt, holder.ivCollect);
            }
        });

}


public void setPopwindow(){
    popView = View.inflate(context, R.layout.popwindow_share,null);
    LinearLayout popQq= (LinearLayout) popView.findViewById(R.id.pop_qq);
    LinearLayout popQzone= (LinearLayout) popView.findViewById(R.id.pop_qzone);
    LinearLayout popFriend= (LinearLayout) popView.findViewById(R.id.pop_friend);
    LinearLayout popWeixin= (LinearLayout) popView.findViewById(R.id.pop_weixin);
    TextView popCancel= (TextView) popView.findViewById(R.id.pop_cancel);
    popQq.setOnClickListener(this);
    popQzone.setOnClickListener(this);
    popFriend.setOnClickListener(this);
    popWeixin.setOnClickListener(this);
    popCancel.setOnClickListener(this);

    popupWindow = new PopupWindow(popView, RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.MATCH_PARENT);
    popupWindow.setOutsideTouchable(true);
    popupWindow.setBackgroundDrawable(new BitmapDrawable());
    popupWindow.setFocusable(true);
}

public void setTopIcon(int imageId,TextView view){
    Drawable top = context.getResources().getDrawable(imageId);
    view.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);
}
    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.pop_qq:
                 QQLoginShareUtils.setShare("d","分享","djfdjvnm",context);
                 break;
             case R.id.pop_qzone:
                 break;
             case R.id.pop_friend:
                 break;
             case R.id.pop_weixin:
                 break;
             case R.id.pop_cancel:
                 popupWindow.dismiss();
                 break;
         }
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
