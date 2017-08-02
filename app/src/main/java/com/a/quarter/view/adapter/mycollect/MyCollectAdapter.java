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
import com.a.quarter.model.utils.AnimUtils;
import com.a.quarter.utils.FrescoCircleUtils;
import com.a.quarter.utils.QQLoginShareUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import media.IjkVideoView;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/26.
 */

public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private boolean flag = true;
    private boolean playBo=false;
    private View view1;
    private View popView;
    private PopupWindow popupWindow;
    public MyCollectAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view1 = View.inflate(context, R.layout.item_userpage, null);
        MyHolder myHolder = new MyHolder(view1);
        setPopwindow();
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        //显示

        //设置数据
        FrescoCircleUtils.setImageViewCircle(holder.ImageTitle, Uri.parse("http://169.254.1.100/ic_ss.jpg"));
        holder.tvTitle.setText("天蝎喝牛奶");
        holder.tvTime.setText("2017-7-20  14:20");
        holder.tvPublish.setText("妹子智斗抢劫男，标题总是这样滴");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        MyCollectItemAdapter myCollectItemAdapter = new MyCollectItemAdapter(context);
        holder.recyclerView.setAdapter(myCollectItemAdapter);
        //动画
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
               setTopIcon(R.mipmap.details_xi_a, holder.ivLike);
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
