package com.a.quarter.view.adapter.recommend;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.recommend.ItemBean;
import com.a.quarter.view.media.IjkVideoView;
import com.a.quarter.view.utils.AnimationsUtils;
import com.exa.framelib_rrm.utils.LogUtils;

import java.util.ArrayList;

import io.reactivex.processors.PublishProcessor;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class HotAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<ItemBean> list = new ArrayList<>();

    public HotAdapter(Context context) {
        this.context = context;

    }

    public void setData(ArrayList<ItemBean> datas) {
        if (datas != null) {
            list.addAll(datas);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(View.inflate(context, R.layout.item_recommend_recycler, null));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final MyHolder holder1 = (MyHolder) holder;
        holder1.nickName.setText("hello");
        holder1.time.setText(list.get(position).getName());

        String s = Environment.getExternalStorageDirectory().getPath() + "/oppo.mp4";
        Uri uri=Uri.parse(s);
        holder1.player.setVideoURI(uri);
        holder1.player.start();
        holder1.add.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {



                final float x = holder1.add.getX();

                if (!list.get(position).getCheck()) {
                    list.get(position).setCheck(false);

//                    Animation animation = AnimationsUtils.getRotaAnimation(context, R.anim.anim, holder1.add);

                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {


                            LogUtils.i("onAnimationStart1");
                            holder1.add.setImageResource(R.mipmap.packup);
                            setVisibility(holder1.copyLink, View.VISIBLE);
                            setVisibility(holder1.report, View.VISIBLE);
                            setVisibility(holder1.shade, View.VISIBLE);
                            AnimationsUtils.setAnimationSet(1200, holder1.copyLink, x, -(holder1.add.getWidth() * 1.2f), 0f, 1f);
                            AnimationsUtils.setAnimationSet(1200, holder1.report, x, -(holder1.add.getWidth() * 3f), 0f, 1f);
                            AnimationsUtils.setAnimationSet(1200, holder1.shade, x, -(holder1.add.getWidth() * 4.2f), 0f, 1f);
                            LogUtils.i("onAnimationStart2");
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });


                } else {
                    list.get(position).setCheck(true);

                    holder1.add.setImageResource(R.mipmap.show);

                    Animation animation = AnimationsUtils.getRotaAnimation(context, R.anim.rotate2, holder1.add);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            AnimationsUtils.setAnimationSet(2000, holder1.copyLink, -(holder1.add.getWidth() * 1.2f), x, 1f, 0f);
                            AnimationsUtils.setAnimationSet(2000, holder1.report, -(holder1.add.getWidth() * 3f), x, 1f, 0f);
                            AnimationsUtils.setAnimationSet(2000, holder1.shade, -(holder1.add.getWidth() * 4.2f), x, 1f, 0f);


                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });


                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class MyHolder extends RecyclerView.ViewHolder {

        private final TextView nickName;
        private final TextView time;
        private final View include;
        private final RecyclerView recyclerView;
        private final TextView copyLink;
        private final TextView report;
        private final TextView shade;
        private final ImageView add;
        private final IjkVideoView player;

        public MyHolder(View itemView) {
            super(itemView);

            nickName = (TextView) itemView.findViewById(R.id.re_textNickName_item);
            time = (TextView) itemView.findViewById(R.id.re_textTime_item);
            player = (IjkVideoView) itemView.findViewById(R.id.re_player_item);
            include = itemView.findViewById(R.id.include_re_btn);

            copyLink = (TextView) include.findViewById(R.id.text_re_copyLink);

            report = (TextView) include.findViewById(R.id.text_re_report);
            shade = (TextView) include.findViewById(R.id.text_re_shiled);

            add = (ImageView) include.findViewById(R.id.text_re_add);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.re_hot_recycler);

        }

    }

    public void setVisibility(View view, int visi) {

        if (visi == View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        } else if (visi == View.GONE) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }


    }

    public static void setAnimation(Context context, float fromX, float toX, float fromY, float toY, final View view) {

        AnimationSet animationSet = new AnimationSet(true);
        //渐变和平移动画
        TranslateAnimation translate = new TranslateAnimation(fromX, toX, fromY, toY);
        translate.setDuration(2200);

        AlphaAnimation alpha = (AlphaAnimation) AnimationUtils.loadAnimation(context, R.anim.alppha);

        animationSet.addAnimation(translate);
        animationSet.addAnimation(alpha);
        animationSet.setFillAfter(true);


        view.startAnimation(animationSet);


    }

}
