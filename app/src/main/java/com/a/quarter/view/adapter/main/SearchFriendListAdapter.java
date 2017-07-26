package com.a.quarter.view.adapter.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.main.AddPicItemBean;
import com.a.quarter.model.bean.main.MultiTypeItemBean;
import com.a.quarter.model.bean.main.SearchFriendHistory;
import com.a.quarter.model.bean.main.SearchFriendInterest;
import com.exa.framelib_rrm.base.view.view.CircleImageView;

import java.util.ArrayList;

/**
 * Created by acer on 2017/7/22.
 * 搜索好友的列表
 */
public class SearchFriendListAdapter extends RecyclerView.Adapter{

    //private Context context;
    private ArrayList<MultiTypeItemBean> list;
    public static final int TYPE_HISTORY_TITLE = 0;
    public static final int TYPE_HISTORY = 1;
    public static final int TYPE_INTEREST_TITLE = 2;
    public static final int TYPE_INTEREST = 3;
    private final LayoutInflater inflater;

    public SearchFriendListAdapter(Context context, ArrayList<MultiTypeItemBean> list) {
        //this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if(viewType == TYPE_HISTORY_TITLE){
            holder = new HistoryTitleHolder(inflater.inflate(R.layout.item_search_friend_type_history_title, parent, false));
        }else if(viewType == TYPE_HISTORY){
            holder = new HistoryHolder(inflater.inflate(R.layout.item_search_friend_type_history, parent, false));
        }else if(viewType == TYPE_INTEREST_TITLE){
            holder = new InterestTitleHolder(inflater.inflate(R.layout.item_search_friend_type_interest_title, parent, false));
        }else if(viewType == TYPE_INTEREST){
            holder = new InterestHolder(inflater.inflate(R.layout.item_search_friend_type_interest, parent, false));
        }
        return holder;
    }

    int viewType;
    SearchFriendHistory historyBean;
    SearchFriendInterest interestBean;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewType = list.get(position).itemType;
        if(viewType == TYPE_HISTORY_TITLE){
            //HistoryTitleHolder holder1 = (HistoryTitleHolder)holder;

        }else if(viewType == TYPE_HISTORY){
            HistoryHolder holder2 = (HistoryHolder)holder;
            historyBean = (SearchFriendHistory)list.get(position);
            holder2.tvName.setText(historyBean.name);
            if(position < list.size()-1 && list.get(position+1).itemType != TYPE_HISTORY){
                holder2.line.setVisibility(View.GONE);
            }else{
                holder2.line.setVisibility(View.VISIBLE);
            }
        }else if(viewType == TYPE_INTEREST_TITLE){
            //InterestTitleHolder holder3 = (InterestTitleHolder)holder;

        }else if(viewType == TYPE_INTEREST){
            InterestHolder holder4 = (InterestHolder)holder;
            interestBean = (SearchFriendInterest) list.get(position);
            holder4.tvName.setText(interestBean.name);
            holder4.tvInfo.setText(interestBean.info);
            if(position < list.size()-1 && list.get(position+1).itemType != TYPE_INTEREST){
                holder4.line.setVisibility(View.GONE);
            }else{
                holder4.line.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HistoryTitleHolder extends RecyclerView.ViewHolder{

        private TextView tvClearHistory;

        public HistoryTitleHolder(View itemView) {
            super(itemView);
            this.tvClearHistory = (TextView) itemView.findViewById(R.id.tv_clear_history);
        }

    }

    class HistoryHolder extends RecyclerView.ViewHolder{

        private CircleImageView iv;
        private ImageView ivArrowRight;
        private TextView tvName;
        private View line;

        public HistoryHolder(View view) {
            super(view);
            iv = (CircleImageView) view.findViewById(R.id.iv);
            ivArrowRight = (ImageView) view.findViewById(R.id.iv_arrow_right);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            line = (View) view.findViewById(R.id.line);
        }

    }

    class InterestTitleHolder extends RecyclerView.ViewHolder{

        private TextView tvChange;
        private ImageView ivProgressDots;

        public InterestTitleHolder(View view) {
            super(view);
            tvChange = (TextView) view.findViewById(R.id.tv_change);
            ivProgressDots = (ImageView) view.findViewById(R.id.iv_progress_dots);
        }

    }

    class InterestHolder extends RecyclerView.ViewHolder{

        private CircleImageView iv;
        private TextView tvFollow;
        private TextView tvName;
        private TextView tvInfo;
        private View line;

        public InterestHolder(View view) {
            super(view);
            iv = (CircleImageView) view.findViewById(R.id.iv);
            tvFollow = (TextView) view.findViewById(R.id.tv_follow);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvInfo = (TextView) view.findViewById(R.id.tv_info);
            line = (View) view.findViewById(R.id.line);
        }

    }
}
