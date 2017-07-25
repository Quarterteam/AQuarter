package com.a.quarter.view.adapter.recommend;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a.quarter.R;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class HotAdapter extends RecyclerView.Adapter   {
    Context context;
    ArrayList<String> list = new ArrayList<>();

    public HotAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<String> datas) {
        if (datas != null) {
            list.addAll(datas);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(View.inflate(context, R.layout.item_recommend_recycler, null));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

     final  MyHolder   holder1 = (MyHolder) holder;
        holder1.nickName.setText("hello");
        holder1.time.setText(list.get(position));

        holder1.add.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                System.out.println("------------------");


                        if (holder1.add.isChecked()){
                            Drawable drawable = context.getResources().getDrawable(R.mipmap.one);
                            holder1.add.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);  //设置drawableTop

                            holder1.shiled.setVisibility(View.VISIBLE);
                            holder1.report.setVisibility(View.VISIBLE);
                            holder1.copyLink.setVisibility(View.VISIBLE);
                            System.out.println("----1--------------");

                        }else {
                            Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_launcher);
                            holder1.add.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);  //设置drawableTop

                            holder1.shiled.setVisibility(View.INVISIBLE);
                            holder1.report.setVisibility(View.INVISIBLE);
                            holder1.copyLink.setVisibility(View.INVISIBLE);
                            System.out.println("--------2----------");

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
        private final JCVideoPlayer player;
        private final View include;
        private final RecyclerView recyclerView;
        private final CheckBox copyLink;
        private final CheckBox report;
        private final CheckBox shiled;
        private final CheckBox add;

        public MyHolder(View itemView) {
            super(itemView);

            nickName = (TextView) itemView.findViewById(R.id.re_textNickName_item);
            time = (TextView) itemView.findViewById(R.id.re_textTime_item);
            player = (JCVideoPlayer) itemView.findViewById(R.id.re_player_item);
            include = itemView.findViewById(R.id.include_re_btn);

            copyLink = (CheckBox) include.findViewById(R.id.check_re_copyLink);

            report = (CheckBox) include.findViewById(R.id.check_re_report);
            shiled = (CheckBox) include.findViewById(R.id.check_re_shiled);
            add = (CheckBox) include.findViewById(R.id.check_re_add);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.re_hot_recycler);

        }

    }
}
