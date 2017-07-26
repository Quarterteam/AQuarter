package com.a.quarter.view.adapter.joke;

import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a.quarter.R;
import com.a.quarter.model.utils.AnimUtils;
import com.exa.framelib_rrm.base.view.view.CircleImageView;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/21.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.MyHolder> {
    private Context context;
    private boolean flag=true;
    public JokeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.item_joke_,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.likeTextView.setText("1201");
        holder.transmitTextView.setText("1202");
        holder.commentTextView.setText("1203");
      holder.jokeImageRigth.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              final float width = holder.jokeImageRigth.getWidth();
              float likeWidth=-2*width;
              float transmitWidth=-4*width;
              float commentWidth=-6*width;

              if (flag==true){
                  flag=false;
                  holder.jokeImageRigth.setImageResource(R.mipmap.packup);
                  holder.likeTextView.setVisibility(View.VISIBLE);
                  holder.transmitTextView.setVisibility(View.VISIBLE);
                  holder.commentTextView.setVisibility(View.VISIBLE);
                  AnimatorSet anim1 = AnimUtils.setJokeAnimShow(holder.likeTextView, 0f, likeWidth ,0f,1f);
                  AnimatorSet anim2 = AnimUtils.setJokeAnimShow(holder.transmitTextView, 0f, transmitWidth,0f,1f);
                  AnimatorSet anim3 = AnimUtils.setJokeAnimShow(holder.commentTextView, 0f, commentWidth,0f,1f);
                  AnimUtils.getSetAnimator(anim1,anim2,anim3);
              }else {
                  flag=true;
                  holder.jokeImageRigth.setImageResource(R.mipmap.show);
                  AnimatorSet anim1 = AnimUtils.setJokeAnimShow(holder.likeTextView, likeWidth,0f ,1f,0f);
                  AnimatorSet anim2 = AnimUtils.setJokeAnimShow(holder.transmitTextView, transmitWidth, 0f,1f,0f);
                  AnimatorSet anim3 = AnimUtils.setJokeAnimShow(holder.commentTextView, commentWidth, 0f,1f,0f);
                  AnimUtils.getSetAnimator(anim1,anim2,anim3);
              }
          }
      });
      holder.imageTitle.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(context, "title", Toast.LENGTH_SHORT).show();
          }
      });
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        com.exa.framelib_rrm.base.view.view.CircleImageView imageTitle;
        TextView jokeTextTitle;
        TextView jokeTextTime;
        TextView itemJokeText;
        ImageView jokeImageRigth;
        TextView likeTextView;
        TextView transmitTextView;
        TextView commentTextView;
       public MyHolder(View itemView) {
           super(itemView);
           imageTitle= (CircleImageView) itemView.findViewById(R.id.joke_Image_title);
           jokeTextTitle= (TextView) itemView.findViewById(R.id.joke_Text_title);
           jokeTextTime= (TextView) itemView.findViewById(R.id.joke_Text_time);
           itemJokeText= (TextView) itemView.findViewById(R.id.joke_text);
           jokeImageRigth= (ImageView) itemView.findViewById(R.id.joke_image_rigth);
           likeTextView= (TextView) itemView.findViewById(R.id.joke_text_rigth1);
           transmitTextView= (TextView) itemView.findViewById(R.id.joke_text_rigth2);
           commentTextView= (TextView) itemView.findViewById(R.id.joke_text_rigth3);

       }
   }
}