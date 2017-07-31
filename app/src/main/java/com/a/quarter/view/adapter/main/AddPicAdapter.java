package com.a.quarter.view.adapter.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a.quarter.R;
import com.a.quarter.model.bean.main.AddPicItemBean;

import java.util.ArrayList;

/**
 * Created by acer on 2017/7/22.
 * 发表文章时，添加图片
 */
public class AddPicAdapter extends RecyclerView.Adapter{

    //private Context context;
    private ArrayList<AddPicItemBean> list;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_ADD = 1;
    public static final int MAX_COUNT = 15;
    private final LayoutInflater inflater;

    public AddPicAdapter(Context context, ArrayList<AddPicItemBean> list) {
        //this.context = context;
        this.list = list;
        list.add(new AddPicItemBean(AddPicAdapter.TYPE_ADD));
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if(viewType == TYPE_NORMAL){
            holder = new PicViewHolder(inflater.inflate(R.layout.item_add_pic_normal, parent, false));
        }else if(viewType == TYPE_ADD){
            holder = new AddIconViewHolder(inflater.inflate(R.layout.item_add_pic_addicon, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = list.get(position).type;
        if(viewType == TYPE_NORMAL){
            PicViewHolder picHolder = (PicViewHolder)holder;
            picHolder.position = position;
//            picHolder.
        }else if(viewType == TYPE_ADD){
            AddIconViewHolder iconHolder = (AddIconViewHolder)holder;
            iconHolder.ivAddIcon.setImageResource(R.mipmap.add_pic);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PicViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private ImageView ivDelete;
        public int position;

        public PicViewHolder(View itemView) {
            super(itemView);
            this.iv = (ImageView)itemView.findViewById(R.id.iv);
            this.ivDelete = (ImageView)itemView.findViewById(R.id.iv_delete);
            this.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //删除本条目（本图片）
                    if(position<list.size()){
                        list.remove(position);// java.lang.IndexOutOfBoundsException: Invalid index 5, size is 5
                        notifyItemRemoved(position);
                    }
                }
            });
        }

    }

    class AddIconViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivAddIcon;

        public AddIconViewHolder(View itemView) {
            super(itemView);
            this.ivAddIcon = (ImageView)itemView.findViewById(R.id.iv_add_icon);
            this.ivAddIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //添加一张新的图片
                    if(list.size()>1){
                        list.add(list.size()-2, new AddPicItemBean(TYPE_NORMAL));
                        notifyItemInserted(list.size()-2);
                    }else{
                        list.add(0, new AddPicItemBean(TYPE_NORMAL));
                        notifyItemInserted(0);
                    }
                }
            });
        }

    }
}
