package com.a.quarter.view.fragment.recommend;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.a.quarter.R;
import com.a.quarter.model.bean.recommend.ContentListBean;
import com.a.quarter.view.adapter.recommend.ContentListAdapter;
import com.a.quarter.view.base.BaseFragment;
import com.exa.framelib_rrm.utils.TimeUtils;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class FocuseFragment extends BaseFragment {
//    @Override
//    protected int getContentViewId() {
//        return R.layout.frag_focuse;
//    }
//
//    @Override
//    protected void initViews() {
//
//    }
//
//    @Override
//    protected void initDatas() {
//
//    }

    @Bind(R.id.rv)
    RecyclerView rv;
    private ArrayList<ContentListBean> list;
    private ContentListAdapter adapter;
    private RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_test_recommend_rv;
    }

    @Override
    protected void initViews() {
        //rv = (RecyclerView) mView.findViewById(R.id.rv);
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
        //itemDecoration.setDividerInset(30);
        //添加分割线
        rv.addItemDecoration(itemDecoration);
        //初始化并设置adapter
        list = new ArrayList<ContentListBean>();

        rv.setAdapter(adapter = new ContentListAdapter(getActivity(), list));


        //监听条目的消失
        onChildAttachStateChangeListener =
                new RecyclerView.OnChildAttachStateChangeListener() {

                    RecyclerView.ViewHolder holder;

                    @Override
                    public void onChildViewAttachedToWindow(View view) {

                    }

                    /**
                     * 在条目从屏幕上完全消失的时候，如果是视频条目，重置条目，恢复到未开始的状态；
                     * 避免可能因为条目复用出现视频和视频缩略图显示混乱的情况。
                     * */
                    @Override
                    public void onChildViewDetachedFromWindow(View view) {
                        //获取到该条目的ViewHolder
                        holder = rv.getChildViewHolder(view);
                        if (holder instanceof ContentListAdapter.VideoViewHolder) {
                            //如果是视频条目，重置该条目
                            ((ContentListAdapter.VideoViewHolder) holder).resetItem();
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
                    .video(Uri.parse(VIDEO_URL), R.mipmap.bg9);
            list.add(bean);
            //图片条目
            bean = new ContentListBean(ContentListAdapter.TYPE_IMG, false, false,
                    R.mipmap.user_icon,
                    "天蝎喝牛奶"+i,
                    TimeUtils.getCurrentTimeShort(),
                    "天气美美的，适合郊游",
                    503, 655, 67123, "1.1万",
                    "骑猪上高速", "不错",
                    "骑猪上高速", "不错", false)
                    .image(R.mipmap.bg1);
            list.add(bean);
        }
        //更新适配器
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        if (adapter != null) {
            adapter.onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (rv != null && onChildAttachStateChangeListener != null) {
            rv.removeOnChildAttachStateChangeListener(onChildAttachStateChangeListener);
        }
        if (adapter != null) {
            adapter.onDestroy();
        }
        super.onDestroyView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden && adapter!=null){
            adapter.onPause();
        }
        super.onHiddenChanged(hidden);
    }
}
