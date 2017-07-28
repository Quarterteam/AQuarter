package com.a.quarter.view.adapter.setting;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.recommend.ItemBean;
import com.a.quarter.view.media.IjkVideoView;
import com.a.quarter.view.utils.AnimationsUtils;

import java.util.ArrayList;


/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class SettingAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<ItemBean> list = new ArrayList<>();

    public SettingAdapter(Context context) {
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class MyHolder extends RecyclerView.ViewHolder {


        public MyHolder(View itemView) {
            super(itemView);

        }

    }
}
