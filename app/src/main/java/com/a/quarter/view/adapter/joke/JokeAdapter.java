package com.a.quarter.view.adapter.joke;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.joke.JokeItemBean;
import com.a.quarter.model.utils.AnimUtils;
import com.a.quarter.utils.FrescoCircleUtils;
import com.a.quarter.view.activity.userpage.UserPageActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/21.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.MyHolder> {
    private Context context;
    private List<JokeItemBean> list=new ArrayList<>();


    public JokeAdapter(Context context) {
        this.context = context;
    }
public void setData(List<JokeItemBean> mlist){
    if (mlist!=null){
        list.addAll(mlist);
    }
};
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.item_joke_, null);
        MyHolder myHolder = new MyHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        FrescoCircleUtils.setImageViewCircle(holder.imageTitle,Uri.parse("http://169.254.1.100/ic_ss.jpg"));
        holder.jokeTextTitle.setText(list.get(position).getJokeBean().getUser().getUserName());
        holder.jokeTextTime.setText(list.get(position).getJokeBean().getCharacter_uptime());
        holder.jokeText.setText("内容展示内容展示示内内容展示内容展示内示内示内容展示内容展示内容展示内示内示内容展示内容展示内容展示内示内示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示内容展示");
        holder.followTextView.setText("1201");
        holder.transmitTextView.setText(list.get(position).getJokeBean().getCharacter_forward_num()+"");
        holder.commentTextView.setText(list.get(position).getJokeBean().getCharacter_comment_num()+"");

        //点击头像进入用户界面
        holder.imageTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserPageActivity.class);
                context.startActivity(intent);
            }
        });
        //动画效果
        holder.jokeImageRigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnim(holder,position);
            }
        });

        //赞
        holder.followTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFollow(holder,position);
            }
        });
        //转发
        holder.transmitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //评论
        holder.commentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


//点击条目右边“+”展开动画
public void setAnim(MyHolder holder,int position){
    final float width = holder.jokeImageRigth.getWidth();
    float likeWidth = (float) (-1.5 * width);
    float transmitWidth = -3 * width;
    float commentWidth = (float) (-4.5 * width);
    //动画
    if (list.get(position).isRigth()) {
        list.get(position).setRigth(false);
        holder.jokeImageRigth.setImageResource(R.mipmap.icon_open);
        AnimatorSet anim1 = AnimUtils.setTransAlpha(holder.followTextView, likeWidth, 0f, 1f, 0f);
        AnimatorSet anim2 = AnimUtils.setTransAlpha(holder.transmitTextView, transmitWidth, 0f, 1f, 0f);
        AnimatorSet anim3 = AnimUtils.setTransAlpha(holder.commentTextView, commentWidth, 0f, 1f, 0f);
        AnimUtils.getSetAnimator(anim1, anim2, anim3);
    } else {
        list.get(position).setRigth(true);
        holder.jokeImageRigth.setImageResource(R.mipmap.icon_packup);
        AnimatorSet anim1 = AnimUtils.setTransAlpha(holder.followTextView, 0f, likeWidth, 0f, 1f);
        AnimatorSet anim2 = AnimUtils.setTransAlpha(holder.transmitTextView, 0f, transmitWidth, 0f, 1f);
        AnimatorSet anim3 = AnimUtils.setTransAlpha(holder.commentTextView, 0f, commentWidth, 0f, 1f);
        AnimUtils.getSetAnimator(anim1, anim2, anim3);
    }
}
//点击心型
public void setFollow(MyHolder holder,int position){
    if (list.get(position).isfollow()) {
        Drawable top = context.getResources().getDrawable(R.mipmap.follow_default);
        holder.followTextView.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
        String s = holder.followTextView.getText().toString();
        int in = Integer.parseInt(s) - 1;
        holder.followTextView.setText("" + in);
        list.get(position).setIsfollow(false);
    } else {
        Drawable top = context.getResources().getDrawable(R.mipmap.follow_pressed);
        holder.followTextView.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
        String s = holder.followTextView.getText().toString();
        int in = Integer.parseInt(s) + 1;
        holder.followTextView.setText("" + in);
        list.get(position).setIsfollow(true);
    }
}
    public class MyHolder extends RecyclerView.ViewHolder{
       SimpleDraweeView imageTitle;
        TextView jokeTextTitle;
        TextView jokeTextTime;
        TextView jokeText;
        ImageView jokeImageRigth;
        TextView followTextView;//
        TextView transmitTextView;//转发
        TextView commentTextView;//评论

        public MyHolder(View itemView) {
            super(itemView);
            imageTitle = (SimpleDraweeView) itemView.findViewById(R.id.joke_Image_title);
            jokeTextTitle = (TextView) itemView.findViewById(R.id.joke_Text_title);
            jokeTextTime = (TextView) itemView.findViewById(R.id.joke_Text_time);
            jokeText = (TextView) itemView.findViewById(R.id.joke_text);
            jokeImageRigth = (ImageView) itemView.findViewById(R.id.joke_image_rigth);
            followTextView = (TextView) itemView.findViewById(R.id.joke_text_follow);
            transmitTextView = (TextView) itemView.findViewById(R.id.joke_text_transmit);
            commentTextView = (TextView) itemView.findViewById(R.id.joke_text_comment);//评论

        }

    }

}
