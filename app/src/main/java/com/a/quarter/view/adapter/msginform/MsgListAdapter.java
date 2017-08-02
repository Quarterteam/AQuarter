package com.a.quarter.view.adapter.msginform;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.msginform.MsgInformItemBean;
import com.a.quarter.utils.FrescoCircleUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/26.
 */

public class MsgListAdapter extends RecyclerView.Adapter<MsgListAdapter.MyHolder> {
    private Context context;
    private List<MsgInformItemBean> list=new ArrayList<>();
    public MsgListAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<MsgInformItemBean> mlist){
        if (mlist!=null){
            list.addAll(mlist);
        }
    };
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_msg, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        FrescoCircleUtils.setImageViewCircle(holder.ImageTitle, Uri.parse("http://f2.kkmh.com/image/170119/lbejli3bs.webp-w180"));


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView ImageTitle;
        ImageView Iv_Rigth;
        TextView tv_name;
        TextView tv_wokename;
        TextView tv_info;
        TextView tv_time;
        TextView tv_msg;


        public MyHolder(View itemView) {
            super(itemView);
            ImageTitle = (SimpleDraweeView) itemView.findViewById(R.id.Iv_msg_title);
            Iv_Rigth = (ImageView) itemView.findViewById(R.id.Iv_msg_rigth);
            tv_name = (TextView) itemView.findViewById(R.id.tv_msg_name);
            tv_info = (TextView) itemView.findViewById(R.id.tv_msg_info);
            tv_wokename = (TextView) itemView.findViewById(R.id.tv_msg_wokename);
            tv_time= (TextView) itemView.findViewById(R.id.tv_msg_time);
            tv_msg = (TextView) itemView.findViewById(R.id.tv_msg);
        }
    }
}
