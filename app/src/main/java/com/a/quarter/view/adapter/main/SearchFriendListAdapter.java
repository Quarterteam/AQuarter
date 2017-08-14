package com.a.quarter.view.adapter.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.main.MultiTypeItemBean;
import com.a.quarter.model.bean.main.SearchFriendBean;
import com.a.quarter.utils.SearchFriendHistoryUtils;
import com.a.quarter.view.activity.userpage.UserPageActivity;
import com.exa.framelib_rrm.base.view.view.CircleImageView;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.T;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by acer on 2017/7/22.
 * 搜索好友的列表适配器
 */
public class SearchFriendListAdapter extends RecyclerView.Adapter{

    //private Context context;
    private ArrayList<MultiTypeItemBean> list;
    public static final int TYPE_HISTORY_TITLE = 0;
    public static final int TYPE_HISTORY = 1;
    public static final int TYPE_INTEREST_TITLE = 2;
    public static final int TYPE_INTEREST = 3;
    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

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
    SearchFriendBean interestBean;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewType = list.get(position).itemType;
        if(viewType == TYPE_HISTORY_TITLE){
            //HistoryTitleHolder holder1 = (HistoryTitleHolder)holder;

        }else if(viewType == TYPE_HISTORY){
            HistoryHolder holder2 = (HistoryHolder)holder;
            interestBean = (SearchFriendBean)list.get(position);
            holder2.tvName.setText(interestBean.userName);
            if(position < list.size()-1 && list.get(position+1).itemType != TYPE_HISTORY){
                holder2.line.setVisibility(View.GONE);
            }else{
                holder2.line.setVisibility(View.VISIBLE);
            }
        }else if(viewType == TYPE_INTEREST_TITLE){
            //InterestTitleHolder holder3 = (InterestTitleHolder)holder;

        }else if(viewType == TYPE_INTEREST){
            InterestHolder holder4 = (InterestHolder)holder;
            interestBean = (SearchFriendBean) list.get(position);
            holder4.tvName.setText(interestBean.userName);
            holder4.tvInfo.setText(interestBean.userPhone);
            if(!TextUtils.isEmpty(interestBean.userHead)){
                holder4.iv.setImageURI(interestBean.userHead);
            }else{
                holder4.iv.setActualImageResource(R.mipmap.bg10);
            }

            if(position < list.size()-1 && list.get(position+1).itemType != TYPE_INTEREST){
                holder4.line.setVisibility(View.GONE);
            }else{
                if(position != list.size()-1){
                    holder4.line.setVisibility(View.VISIBLE);
                }else{
                    holder4.line.setVisibility(View.GONE);
                }
            }

            //是否已关注过
            if(!interestBean.isAlreadyFollowed){
                holder4.tvFollow.setText("+ 关注");
            }else{
                holder4.tvFollow.setText("已关注");
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HistoryTitleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvClearHistory;

        public HistoryTitleHolder(View itemView) {
            super(itemView);
            this.tvClearHistory = (TextView) itemView.findViewById(R.id.tv_clear_history);
            this.tvClearHistory.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == tvClearHistory){
                //清空所有的搜索历史记录
                if(SearchFriendHistoryUtils.deleteAllHistorys()){
                    //如果从SharedPrefrences中删除成功的话，从集合中移除
                    Iterator<MultiTypeItemBean> iterator = list.iterator();
                    int itemType = -1;
                    int count = 0;
                    while(iterator.hasNext()){
                        itemType = iterator.next().itemType;
                        if(itemType==TYPE_HISTORY_TITLE || itemType==TYPE_HISTORY){
                            iterator.remove();
                            count++;
                        }else{
                            break;
                        }
                    }
                    //更新适配器
                    if(count>0){
                        notifyDataSetChanged();
                    }
                }
            }
        }
    }

    class HistoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView iv;
        private ImageView ivRight;
        private TextView tvName;
        private View line;

        public HistoryHolder(View view) {
            super(view);
            iv = (CircleImageView) view.findViewById(R.id.iv);
            ivRight = (ImageView) view.findViewById(R.id.iv_arrow_right);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            line = view.findViewById(R.id.line);

            ivRight.setOnClickListener(this);
            tvName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==ivRight){
                //删除该条历史记录
                //从SharedPrefrences中删除
                if(SearchFriendHistoryUtils.deleteHistory(tvName.getText().toString())){
                    //从集合中移除
                    int po = getAdapterPosition();
                    list.remove(po);
                    notifyItemRemoved(po);
                }else{
                    T.showShort(v.getContext(), "删除失败");
                }
            }else if(v==tvName){
                //根据该历史记录进行搜索
                //T.showShort(v.getContext(), "点击了历史记录");
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(TYPE_HISTORY, v, getAdapterPosition());
                }
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int itemType, View v, int position);
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

    class InterestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SimpleDraweeView iv;
        private TextView tvFollow;
        private TextView tvName;
        private TextView tvInfo;
        private View line;
        private View itemView;

        public InterestHolder(View view) {
            super(view);
            iv = (SimpleDraweeView) view.findViewById(R.id.iv);
            tvFollow = (TextView) view.findViewById(R.id.tv_follow);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvInfo = (TextView) view.findViewById(R.id.tv_info);
            line = view.findViewById(R.id.line);
            tvFollow.setOnClickListener(this);
            this.itemView = view;
            this.itemView.setOnClickListener(this);
        }

        //2011-1993 = 7+11
        //14，3， 24 ，21
        //14-15 15-16 16-17
        //
        @Override
        public void onClick(View v) {
            if(v == tvFollow){
                //点击条目上的关注按钮
                int position = getAdapterPosition();
                if(!((SearchFriendBean)list.get(position)).isAlreadyFollowed){
                    //如果没有关注过，进行关注
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(TYPE_INTEREST, v, position);
                    }
                }else{
                    //如果已经关注过，什么都不做
                }
            }else if(v == itemView){
//                if(onItemClickListener!=null){
//                    int position = getAdapterPosition();
//                    onItemClickListener.onItemClick(TYPE_INTEREST, v, position);
//                }
                ActivityUtils.jumpIn(v.getContext(), UserPageActivity.class);
            }
        }
    }
}
