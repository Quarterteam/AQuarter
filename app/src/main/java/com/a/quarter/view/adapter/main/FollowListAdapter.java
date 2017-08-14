package com.a.quarter.view.adapter.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.main.FollowListItemBean;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by acer on 2017/7/24.
 */
public class FollowListAdapter extends RecyclerView.Adapter<FollowListAdapter.MyFollowViewHolder>{

    private ArrayList<FollowListItemBean> list;
    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public FollowListAdapter(Context context, ArrayList<FollowListItemBean> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyFollowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyFollowViewHolder(inflater.inflate(R.layout.item_myfollow_list, parent, false));
    }

    @Override
    public void onBindViewHolder(MyFollowViewHolder holder, int position) {
        holder.ivIcon.setActualImageResource(R.mipmap.bg10);
        holder.tvName.setText(list.get(position).name);
        holder.tvInfo.setText(list.get(position).info);
        holder.tvTime.setText(list.get(position).time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyFollowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SimpleDraweeView ivIcon;
        private ImageView ivArrowRight;
        private TextView tvName;
        private TextView tvInfo;
        private TextView tvTime;
        public int position;

        public MyFollowViewHolder(View itemView) {
            super(itemView);
            this.ivIcon = (SimpleDraweeView)itemView.findViewById(R.id.iv_icon);
            this.ivArrowRight = (ImageView)itemView.findViewById(R.id.iv_arrow_right);
            this.tvName = (TextView)itemView.findViewById(R.id.tv_name);
            this.tvInfo = (TextView)itemView.findViewById(R.id.tv_info);
            this.tvTime = (TextView)itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener!=null){
                onItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
