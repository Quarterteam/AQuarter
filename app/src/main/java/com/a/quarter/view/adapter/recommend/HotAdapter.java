package com.a.quarter.view.adapter.recommend;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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

public class HotAdapter extends RecyclerView.Adapter  {
    Context context;
    ArrayList<String> list = new ArrayList<>();
    private MyHolder holder1;

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
        holder1 = (MyHolder) holder;
        holder1.nickName.setText("hello");
        holder1.time.setText(list.get(position));
        holder1.vg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.re_btn_add:
                        holder1.report.setVisibility(View.INVISIBLE);
                        Log.i("---","000");

                        break;
                    case R.id.re_btn_shiled:
                        Log.i("---","000");

                        break;
                    case R.id.re_btn_report:
                        Log.i("---","000");

                        break;
                    case R.id.re_btn_copyLink:
                        Log.i("---","000");

                        break;
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
        private final RadioGroup vg;
        private final RadioButton shiled;
        private final RadioButton copyLink;
        private final RadioButton add;
        private final RadioButton report;

        public MyHolder(View itemView) {
            super(itemView);

            nickName = (TextView) itemView.findViewById(R.id.re_textNickName_item);
            time = (TextView) itemView.findViewById(R.id.re_textTime_item);
            player = (JCVideoPlayer) itemView.findViewById(R.id.re_player_item);
            include = itemView.findViewById(R.id.include_re_btn);

            vg = (RadioGroup) include.findViewById(R.id.re_vg);

            shiled = (RadioButton) include.findViewById(R.id.re_btn_shiled);
            copyLink = (RadioButton) include.findViewById(R.id.re_btn_copyLink);
            add = (RadioButton) include.findViewById(R.id.re_btn_add);
            report = (RadioButton) include.findViewById(R.id.re_btn_report);

        }

    }
}
