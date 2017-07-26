package com.a.quarter.view.fragment.video.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a.quarter.R;

import java.util.ArrayList;

import media.IjkVideoView;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VHotFragmentAdapter extends RecyclerView.Adapter<VHotFragmentAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Uri> list;
    private MediaMetadataRetriever mMetadataRetriever;

    public VHotFragmentAdapter(Context context, ArrayList<Uri> list) {
        this.context = context;
        this.list = list;
        Log.e(" list ",list.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.vhotfrag_recycler_item, parent, false);

        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

                holder.mIjkVideoView.setVideoURI(list.get(position));
                holder.mIjkVideoView.start();
                holder.mIjkVideoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.mIjkVideoView.start();
                    }
                });

                mMetadataRetriever = new MediaMetadataRetriever();
                //mPath本地视频地址
                Uri uri = list.get(position);
                String path = uri.getPath();
                mMetadataRetriever.setDataSource(path);

                    String height = mMetadataRetriever.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);//高
                    int i = Integer.parseInt(height);

                Bitmap bitmap = mMetadataRetriever.getFrameAtTime(10000L *
                        i);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final IjkVideoView mIjkVideoView;


        public MyViewHolder(View itemView) {
            super(itemView);
            mIjkVideoView = (IjkVideoView) itemView.findViewById(R.id.IjkVideoView);

        }
    }
}
