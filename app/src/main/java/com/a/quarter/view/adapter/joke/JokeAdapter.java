package com.a.quarter.view.adapter.joke;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a.quarter.R;
import com.a.quarter.model.bean.joke.JokeBean;
import com.a.quarter.utils.AnimUtils;
import com.a.quarter.utils.IconChangeUtils;
import com.a.quarter.utils.QQLoginShareUtils;
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
    private List<JokeBean.Characterp> list=new ArrayList<>();
    private Handler handler;
    private String nickNum;
    private String commentNum;
    private String badNum;
    private String forwardNum;
    public JokeAdapter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void setData(List<JokeBean.Characterp> mlist){
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
       holder.imageTitle.setImageURI("http://f2.kkmh.com/image/170415/k02plqhh4.webp-w320");
    //   holder.imageTitle.setImageURI(list.get(position).getJokeBean().getUser().getUserHead());
        holder.jokeTextTitle.setText( list.get(position).getUser().getUserName());
        holder.jokeTextTime.setText(list.get(position).getCharacter_uptime());
        holder.jokeText.setText(list.get(position).getCharacter_content());
       //点赞
        if (nickNum==null){
            holder.followTextView.setText(""+list.get(position).getNice_num());
        }else if (nickNum!=null){
            holder.followTextView.setText(""+nickNum);
        }
        //转发
        if (forwardNum==null){
            holder.transmitTextView.setText(""+list.get(position).getCharacter_forward_num());
        }else{
            holder.transmitTextView.setText(""+forwardNum);

        }
        //评论
        if (commentNum==null){
            holder.commentTextView.setText(""+list.get(position).getCharacter_comment_num());
        }else{

        }

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
                if (list.get(position).getNice()==true){
                    Toast.makeText(context, "已经点赞过了", Toast.LENGTH_SHORT).show();
                }else{
                    IconChangeUtils.setIconChangeCheck(context,holder.followTextView,R.mipmap.follow_pressed);
                    list.get(position).setNice(true);
                    setHandlerWaht(position,0);
                }
            }
        });
        //转发
        holder.transmitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).getIsforward()==false) {
                    setHandlerWaht(position, 1);
                    QQLoginShareUtils utils = new QQLoginShareUtils();
                    utils.setShare("http://www.baidu.com", "标题", "描述内容", context);
                }
            }
        });
        //评论
        holder.commentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).getIscomment()==true){

                }else{
                  //  list.get(position).setIscomment(true);
                    Toast.makeText(context, "评论", Toast.LENGTH_SHORT).show();
                  //  setHandlerWaht(position,2);
                }
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
    if (list.get(position).getRigth()==true) {
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

public void setHandlerWaht(int position,int waht){
    Message message =new Message();
    message.obj=position;
    message.what=waht;
    handler.sendMessage(message);
}
public void getNickNum(String num){
    nickNum=num;
}
    public void getBadNum(String num){
        badNum=num;
    }
    public void getForwardNum(String num){
        forwardNum=num;
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
