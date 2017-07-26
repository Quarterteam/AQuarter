package com.a.quarter.view.fragment.video;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseFragment;
import com.a.quarter.view.fragment.video.adapter.VHotFragmentAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import media.AndroidMediaController;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VHotFragment extends BaseFragment{
    @Bind(R.id.vhot_RecyclerView)
    RecyclerView mRecyclerView;
    private ArrayList<Uri> list=new ArrayList<>();
    private AndroidMediaController mMediaController;

    @Override
    protected int getContentViewId() {
        return R.layout.vhotfrag_video;
    }

    @Override
    protected void initViews() {
        // RecycleView 使用的什么样的布局方式
        GridLayoutManager manager=new GridLayoutManager(getActivity(),1);
        mRecyclerView.setLayoutManager(manager);

//        mMediaController = new AndroidMediaController(getActivity(), false);
//        mMediaController.setSupportActionBar(actionBar);
        // TODO: Ijkplayer
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

    }

    @Override
    protected void initDatas() {

        Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/oppo.mp4");
        // TODO: 添加集合
        list.add(uri);
        // TODO: 设置适配器
        VHotFragmentAdapter adapter = new VHotFragmentAdapter(getActivity(),list);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
