package com.a.quarter.view.fragment.recommend;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.a.quarter.R;
import com.a.quarter.model.bean.recommend.ContentListBean;
import com.a.quarter.view.adapter.recommend.ContentListAdapter;
import com.a.quarter.view.base.BaseFragment;
import com.exa.framelib_rrm.utils.LogUtils;
import com.exa.framelib_rrm.utils.TimeUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class HotFragment extends BaseFragment {

    @Bind(R.id.rv)
    RecyclerView rv;
    private ArrayList<ContentListBean> list;
    private ContentListAdapter adapter;
    RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_test_recommend_rv;
    }

    @Override
    protected void initViews() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //条目分割线，只显示视频条目和图片条目之间的分割线
        ContentListItemDecoration itemDecoration = new ContentListItemDecoration();
        //设置分割线的颜色
        itemDecoration.setDividerColor(Color.GRAY);
        //设置分割线的高度
        itemDecoration.setDividerHeight(1);
        //设置RecyclerView的header数量，用于绘制分割线的时候header之间，header和第一个非header的条目之间不画分割线
        itemDecoration.setHeaderCount(1);
        //设置分割线左右与屏幕左右的距离
      // itemDecoration.setDividerInset(50);
        //添加分割线
        rv.addItemDecoration(itemDecoration);
        //初始化并设置adapter
        list = new ArrayList<ContentListBean>();
        rv.setAdapter(adapter = new ContentListAdapter(getActivity(), list));

        //监听列表的滚动
//        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//        });
//        rv.setRecyclerListener(new RecyclerView.RecyclerListener() {
//
//            @Override
//            public void onViewRecycled(RecyclerView.ViewHolder holder) {
//                if(holder instanceof ContentListAdapter.VideoViewHolder){
//
//                }
//            }
//
//        });

//        onChildAttachStateChangeListener =
//                new RecyclerView.OnChildAttachStateChangeListener(){
//
//            ViewGroup parent = null;
//                    @Override
//            public void onChildViewAttachedToWindow(View view) {
//
//            }
//
//            //在条目从屏幕上消失的时候，如果是视频条目，移除video控件，恢复未开始的状态
//            //这个方法有效
//            @Override
//            public void onChildViewDetachedFromWindow(View view) {
////                RecyclerView.LayoutParams p = (RecyclerView.LayoutParams)view.getLayoutParams();
////                p.
//
//                LogUtils.i("onChildViewDetachedFromWindow,view="+view);
//                if(adapter.player!=null) {
//                    parent = (ViewGroup)adapter.player.getParent();
//                    if (parent!=null){
//                        if(parent.getParent() == view){
//                            LogUtils.i("移除视频控件,view="+view);
//                            adapter.player.stopPlayback();
//                            adapter.player.release(true);
//                            parent.removeView(adapter.player);
//                            //所有条目只使用一个IjkVideoView，解决remove之后可能会出现上个播放视频的条目在视频的位置一片空白的情况
//
//                            //TODO 显示开始播放的图标和视频缩略图
//                            //怎么找到 VideoViewHolder 对象？
//                            rv.getChildViewHolder(view);
//                            rv.findViewHolderForAdapterPosition()
//                        }
//                    }
//                }
////                if(list.get()rv.getChildAdapterPosition(view)){
////
////                }
//            }
//
//        };

        onChildAttachStateChangeListener =
        new RecyclerView.OnChildAttachStateChangeListener(){

            ViewGroup parent = null;
            RecyclerView.ViewHolder holder;
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            /**
             * 在条目从屏幕上消失的时候，如果是视频条目，移除视频控件，恢复未开始的状态；
             * 解决remove之后可能会出现上个播放视频的条目上在视频的位置一片空白的情况；
             * 这个方法有效；
             * */
            @Override
            public void onChildViewDetachedFromWindow(View view) {
                //LogUtils.i("onChildViewDetachedFromWindow,view="+view);
                //获取到该条目的ViewHolder
                holder = rv.getChildViewHolder(view);
                if(holder instanceof ContentListAdapter.VideoViewHolder){//如果是视频条目
                    if(adapter.player!=null) {//如果视频控件不是null
                        parent = (ViewGroup)adapter.player.getParent();
                        if (parent!=null){//如果视频控件已经被添加到某个条目上了
                            if(parent.getParent() == view){//如果视频控件是在该view对应的条目上
                                //LogUtils.i("移除视频控件,view="+view);
                                ((ContentListAdapter.VideoViewHolder)holder).resetItem();
                            }
                        }
                    }
                }
            }

        };
        rv.addOnChildAttachStateChangeListener(onChildAttachStateChangeListener);
    }

    @Override
    protected void initDatas() {
        //初始化数据
        list.add(new ContentListBean(ContentListAdapter.TYPE_HEAD1));//头部的ViewPager，作为RecyclerView第一个条目
        final String VIDEO_URL = "http://flv2.bn.netease.com/videolib3/1611/28/GbgsL3639/SD/movie_index.m3u8";
        final String VIDEO_HD_URL = "http://flv2.bn.netease.com/videolib3/1611/28/GbgsL3639/HD/movie_index.m3u8";
        ContentListBean bean;
        for (int i = 0; i < 8; i++) {
            //视频条目
            bean = new ContentListBean(ContentListAdapter.TYPE_VIDEO, false, false,
                    R.mipmap.user_icon,
                    "天蝎喝牛奶"+i,
                    TimeUtils.getCurrentTimeShort(),
                    "天气美美的，适合郊游",
                    503, 655, 67123, "1.1万",
                    "骑猪上高速", "不错",
                    "骑猪上高速", "不错", false)
                    .video(Uri.parse(VIDEO_URL), R.mipmap.bg6);
            list.add(bean);
            //图片条目
            bean = new ContentListBean(ContentListAdapter.TYPE_IMG, false, false,
                    R.mipmap.user_icon,
                    "天蝎喝牛奶"+i,
                    TimeUtils.getCurrentTimeShort(),
                    "天气美美的，适合郊游",
                    503, 655, 67123, "1.1万",
                    "骑猪上高速", "不错",
                    "骑猪上高速", "不错", true)
                    .image(R.mipmap.bg11);
            list.add(bean);
        }
        //更新适配器
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onStop() {
        if(adapter!=null){
            adapter.onStop();
        }
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        if(rv!=null && onChildAttachStateChangeListener!=null){
            rv.removeOnChildAttachStateChangeListener(onChildAttachStateChangeListener);
        }
        if(adapter!=null){
            adapter.onDestory();
            adapter = null;
        }
        super.onDestroyView();
    }
}
