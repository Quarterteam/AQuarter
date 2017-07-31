package com.a.quarter.view.adapter.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a.quarter.R;
//import com.dl7.player.media.IjkPlayerView;
//import com.dl7.player.media.IjkPlayerView.OnPlayCircleClickListener;
import com.a.quarter.view.fragment.recommend.BannerLocalImageLoader;
import com.a.quarter.model.bean.recommend.ContentListBean;
import com.a.quarter.view.fragment.recommend.MyIjkVideoView;
import com.exa.framelib_rrm.utils.NetUtils;
import com.exa.framelib_rrm.utils.T;
import com.youth.banner.Banner;

import java.util.ArrayList;

import media.AndroidMediaController;

/**
 * Created by acer on 2017/7/29.
 * 首页热门推荐，RecyclerView的适配器，使用了多条目加载
 * 目前共有三种条目：1、轮播图banner；2、视频条目；3、图片条目
 *
 * 是创建ViewHolder的时候，出现的卡顿？
 * 视频停止的问题？
 */
public class ContentListAdapter extends RecyclerView.Adapter {

    private final Context context;
    private ArrayList<ContentListBean> list;
    public static final int TYPE_HEAD1 = 0;//轮播图
    public static final int TYPE_IMG = 1;//图片条目
    public static final int TYPE_VIDEO = 2;//视频条目
    private final LayoutInflater inflater;

    public ContentListAdapter(Context context, ArrayList<ContentListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == TYPE_HEAD1) {
            //轮播图
            holder = new Head1ViewHolder(inflater.inflate(R.layout.item_content_list_type_banner, parent, false));
        } else if (viewType == TYPE_IMG) {
            //图片条目
            holder = new ImageViewHolder(inflater.inflate(R.layout.item_content_list_type_image, parent, false));
        } else if (viewType == TYPE_VIDEO) {
            //视频条目
            holder = new VideoViewHolder(inflater.inflate(R.layout.item_content_list_type_video, parent, false));
            //holder = new ImageViewHolder(inflater.inflate(R.layout.item_test_recommend_rv_type2, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = list.get(position).type;
        if (viewType == TYPE_HEAD1) {//轮播图
            Head1ViewHolder head1Holder = (Head1ViewHolder) holder;

        } else if (viewType == TYPE_VIDEO) {//视频条目
            VideoViewHolder videoHolder = (VideoViewHolder) holder;
            videoHolder.initCommon(position);
            videoHolder.showVideo();
        } else if (viewType == TYPE_IMG) {//图片条目
            ImageViewHolder imageHolder = (ImageViewHolder) holder;
            imageHolder.initCommon(position);
            imageHolder.showImage();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //轮播图对应的ViewHolder
    class Head1ViewHolder extends RecyclerView.ViewHolder {

        private Banner banner;

        public Head1ViewHolder(View itemView) {
            super(itemView);
            this.banner = (Banner) itemView.findViewById(R.id.banner);

            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(R.mipmap.banner1);
            list.add(R.mipmap.banner2);
            list.add(R.mipmap.banner3);
            list.add(R.mipmap.banner4);
            list.add(R.mipmap.banner5);

            //开始自动轮播
            banner.setImages(list)
                    .setImageLoader(new BannerLocalImageLoader())
                    .start();
        }

    }

    //视频条目和图片条目在布局上有一些相同的地方，所以在这里写了一个父类，抽取出相同的部分
    class NormalItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Animation.AnimationListener{

        ImageView ivUserIcon;
        TextView tvUsername;
        TextView tvTime;
        TextView tvContent;
        ImageView ivShiled;
        TextView tvShiled;
        ImageView ivPort;
        TextView tvPort;
        ImageView ivCopyLink;
        TextView tvCopyLink;
        ImageView ivShow;
        TextView tvName1;
        TextView tvComment1;
        TextView tvName2;
        TextView tvComment2;
        TextView tvTag;
        public int position;
        AnimationSet openAnimIvShiled;
        AnimationSet closeAnimIvShiled;
        AnimationSet openAnimIvCopyLink;
        AnimationSet closeAnimIvCopyLink;
        AnimationSet openAnimIvPort;
        AnimationSet closeAnimIvPort;

        RotateAnimation openAnimIvShow;
        RotateAnimation closeAnimIvShow;

        LinearLayout llTypeImage;
        CheckBox cbHeartCount;
        CheckBox cbStarCount;
        TextView tvTransmitCount;
        TextView tvCommentCount;

        public NormalItemViewHolder(View itemView) {
            super(itemView);
            //初始化补间动画对象
            initAnimtions();
            //找到控件
            findViews(itemView);
            //设置点击监听
            ivShow.setOnClickListener(this);
            ivShiled.setOnClickListener(this);
            ivCopyLink.setOnClickListener(this);
            ivPort.setOnClickListener(this);
        }

        private void initAnimtions() {
            //展开时，屏蔽图标对应的动画
            openAnimIvShiled = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.open_1_translate_rotate_alpha);
            //收起时，屏蔽图标对应的动画
            closeAnimIvShiled = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.close_1_translate_rotate_alpha);
            //展开时，复制链接图标对应的动画
            openAnimIvCopyLink = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.open_2_translate_rotate_alpha);
            //收起时，复制链接图标对应的动画
            closeAnimIvCopyLink = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.close_2_translate_rotate_alpha);
            //展开时，举报图标对应的动画
            openAnimIvPort = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.open_3_translate_rotate_alpha);
            //收起时，举报图标对应的动画
            closeAnimIvPort = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.close_3_translate_rotate_alpha);

            //添加动画监听，用于在动画结束的时候，做一些操作
            openAnimIvShiled.setAnimationListener(this);
            closeAnimIvShiled.setAnimationListener(this);

            openAnimIvCopyLink.setAnimationListener(this);
            closeAnimIvCopyLink.setAnimationListener(this);

            openAnimIvPort.setAnimationListener(this);
            closeAnimIvPort.setAnimationListener(this);

            //展开时，加号和减号对应的图标的动画
            openAnimIvShow = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.open_0_rotate);
            //收起时，加号和减号对应的图标的动画
            closeAnimIvShow = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.close_0_rotate);
            openAnimIvShow.setAnimationListener(this);
            closeAnimIvShow.setAnimationListener(this);
        }

        private void findViews(View itemView) {
            ivUserIcon = (ImageView) itemView.findViewById(R.id.iv_user_icon);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_user_name);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            ivShiled = (ImageView) itemView.findViewById(R.id.iv_shiled);
            tvShiled = (TextView) itemView.findViewById(R.id.tv_shiled);
            ivPort = (ImageView) itemView.findViewById(R.id.iv_port);
            tvPort = (TextView) itemView.findViewById(R.id.tv_port);
            ivCopyLink = (ImageView) itemView.findViewById(R.id.iv_copyLink);
            tvCopyLink = (TextView) itemView.findViewById(R.id.tv_copyLink);
            ivShow = (ImageView) itemView.findViewById(R.id.iv_show);
            //player = (IjkVideoView)itemView.findViewById(R.id.player);
            //player = itemView.findViewById(R.id.player);
            tvName1 = (TextView) itemView.findViewById(R.id.tv_name1);
            tvComment1 = (TextView) itemView.findViewById(R.id.tv_comment1);
            tvName2 = (TextView) itemView.findViewById(R.id.tv_name2);
            tvComment2 = (TextView) itemView.findViewById(R.id.tv_comment2);
            tvTag = (TextView) itemView.findViewById(R.id.tv_tag);
            //rlBtns = (RelativeLayout) itemView.findViewById(R.id.rl_btns);


            llTypeImage = (LinearLayout) itemView.findViewById(R.id.ll_type_img);
            cbHeartCount = (CheckBox) itemView.findViewById(R.id.cb_heart_count);
            cbStarCount = (CheckBox) itemView.findViewById(R.id.cb_star_count);
            tvTransmitCount = (TextView) itemView.findViewById(R.id.tv_transmit_count);
            tvCommentCount = (TextView) itemView.findViewById(R.id.tv_comment_count);

            cbHeartCount.setOnClickListener(this);
            cbStarCount.setOnClickListener(this);
            tvTransmitCount.setOnClickListener(this);
            tvCommentCount.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_show:
                    if (list.get(position).isOpen) {
                        //点击ImageView时，如果该条目上的三个图标正处于展开状态，就开始收起的动画
                        startClose();
                    } else {
                        //点击ImageView时，如果该条目上的三个图标正处于收起状态，就开始展开的动画
                        startOpen();
                    }
                    break;

                case R.id.iv_shiled:
                    Toast.makeText(context, "屏蔽", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.iv_copyLink:
                    Toast.makeText(context, "复制链接", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.iv_port:
                    Toast.makeText(context, "举报", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.cb_heart_count:
                    list.get(position).isHearted = !list.get(position).isHearted;
                    break;

                case R.id.cb_star_count:
                    list.get(position).isStared = !list.get(position).isStared;
                    break;

                case R.id.tv_transmit_count:
                    Toast.makeText(context, "转发？", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.tv_comment_count:
                    Toast.makeText(context, "评论", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

        //开始展开的动画
        private void startOpen() {
            //隐藏屏蔽，复制，举报这几个字
            tvCopyLink.setVisibility(View.GONE);
            tvShiled.setVisibility(View.GONE);
            tvPort.setVisibility(View.GONE);

            //显示三个图标
            ivCopyLink.setVisibility(View.VISIBLE);
            ivShiled.setVisibility(View.VISIBLE);
            ivPort.setVisibility(View.VISIBLE);

            //展开时，加号的动画
            ivShow.startAnimation(openAnimIvShow);

            //展开时，三个图标的动画
            ivShiled.startAnimation(openAnimIvShiled);
            ivCopyLink.startAnimation(openAnimIvCopyLink);
            ivPort.startAnimation(openAnimIvPort);
        }

        //开始收起的动画
        private void startClose() {
            //隐藏屏蔽，复制，举报这几个字
            tvCopyLink.setVisibility(View.GONE);
            tvShiled.setVisibility(View.GONE);
            tvPort.setVisibility(View.GONE);

            //显示三个图标
            ivCopyLink.setVisibility(View.VISIBLE);
            ivShiled.setVisibility(View.VISIBLE);
            ivPort.setVisibility(View.VISIBLE);

            //收起时，减号的动画
            ivShow.startAnimation(closeAnimIvShow);

            //收起时，三个图标的动画
            ivShiled.startAnimation(closeAnimIvShiled);
            ivCopyLink.startAnimation(closeAnimIvCopyLink);
            ivPort.startAnimation(closeAnimIvPort);
        }

        //显示三个图标，如果应该显示的话
        public void showBtns() {
            ivCopyLink.setVisibility(View.VISIBLE);
            ivShiled.setVisibility(View.VISIBLE);
            ivPort.setVisibility(View.VISIBLE);
            tvCopyLink.setVisibility(View.VISIBLE);
            tvShiled.setVisibility(View.VISIBLE);
            tvPort.setVisibility(View.VISIBLE);
        }

        //隐藏三个图标，如果不应该显示的话
        public void hideBtns() {
            ivCopyLink.setVisibility(View.GONE);
            ivShiled.setVisibility(View.GONE);
            ivPort.setVisibility(View.GONE);
            tvCopyLink.setVisibility(View.GONE);
            tvShiled.setVisibility(View.GONE);
            tvPort.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        //动画结束时的监听
        @Override
        public void onAnimationEnd(Animation animation) {
            if (animation == openAnimIvShiled) {
                //如果是屏蔽图标的展开动画的结束，清空该ImageView上的动画，
                //避免需要隐藏时，调用setVisibility(View.GONE)无效
                ivShiled.clearAnimation();
                //显示屏蔽这两个字
                tvShiled.setVisibility(View.VISIBLE);
            } else if (animation == openAnimIvCopyLink) {
                ivCopyLink.clearAnimation();
                tvCopyLink.setVisibility(View.VISIBLE);
            } else if (animation == openAnimIvPort) {
                ivPort.clearAnimation();
                tvPort.setVisibility(View.VISIBLE);
            } else if (animation == closeAnimIvShiled) {
                ivShiled.clearAnimation();
                ivShiled.setVisibility(View.GONE);
            } else if (animation == closeAnimIvCopyLink) {
                ivCopyLink.clearAnimation();
                ivCopyLink.setVisibility(View.GONE);
            } else if (animation == closeAnimIvPort) {
                ivPort.clearAnimation();
                ivPort.setVisibility(View.GONE);
            } else if (animation == openAnimIvShow) {
                //把该条目在集合中对应的状态改为已展开
                list.get(position).isOpen = true;
                //把加号改为减号
                ivShow.setImageResource(R.mipmap.packuphorizontal);
            } else if (animation == closeAnimIvShow) {
                //把该条目在集合中对应的状态改为已收起
                list.get(position).isOpen = false;
                //把减号改为加号
                ivShow.setImageResource(R.mipmap.show);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        //初始化视频条目和图片条目一些共同的
        public void initCommon(int position) {
            //该ViewHolder正在展示的条目，在集合中对应的位置
            this.position = position;
            contentListBean = list.get(this.position);
            //避免因条目复用产生的图标混乱
            if (contentListBean.isOpen) {//如果是已经展开状态，就显示三个图标和减号
                this.ivShow.setImageResource(R.mipmap.packuphorizontal);
                this.showBtns();
            } else {//如果是已经收起状态，就显示加号，不显示三个图标
                this.ivShow.setImageResource(R.mipmap.show);
                this.hideBtns();
            }

            //用户头像
            ivUserIcon.setImageResource(contentListBean.userIconResourceId);
            //用户名称
            tvUsername.setText(contentListBean.userName);
            //时间
            tvTime.setText(contentListBean.time);
            //内容
            tvContent.setText(contentListBean.content);
            //heartCount
            cbHeartCount.setText(contentListBean.heartCount+"");
            cbHeartCount.setChecked(contentListBean.isHearted);
            //starCount
            cbStarCount.setText(contentListBean.starCount+"");
            cbStarCount.setChecked(contentListBean.isStared);
            //transmitCount
            tvTransmitCount.setText(contentListBean.transmitCount+"");
            //commentCount
            tvCommentCount.setText(contentListBean.commentCount);
            //评论
            tvName1.setText(contentListBean.commentUserName1+":");
            tvComment1.setText(contentListBean.commentContent1);
            tvName2.setText(contentListBean.commentUserName2+":");
            tvComment2.setText(contentListBean.commentContent2);
            //神评
            if(list.get(position).isGoodComment){
                tvTag.setVisibility(View.VISIBLE);
            }else{
                tvTag.setVisibility(View.GONE);
            }
        }

    }

    //图片条目
    class ImageViewHolder extends NormalItemViewHolder{
        ImageView ivImg;

        public ImageViewHolder(View itemView) {
            super(itemView);

            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            ivImg.setOnClickListener(this);
        }

        private int id;
        @Override
        public void onClick(View v) {
            super.onClick(v);
            id = v.getId();
            if(id==R.id.iv_img) {
                Toast.makeText(context, "点击了图片", Toast.LENGTH_SHORT).show();
            }
        }

        public void showImage() {
            ivImg.setImageResource(list.get(position).imgResourceId);
        }
    }

    private ContentListBean contentListBean;
    //视频条目
    class VideoViewHolder extends NormalItemViewHolder {

        MyIjkVideoView player;

        public VideoViewHolder(View itemView) {
            super(itemView);

            player = (MyIjkVideoView) itemView.findViewById(R.id.ijkVideoView);

            player.setMediaController(new AndroidMediaController(context));
            player.mIvStartIcon.setOnClickListener(this);
        }

        public void showVideo() {
            if(player.isPlaying()){
                player.stopPlayback();//因为可能是复用的条目，所以先停止播放视频
            }
            player.release(true);
            contentListBean = list.get(position);
//            player.mIvStartIcon.setVisibility(View.VISIBLE);
//            player.mIvThumb.setVisibility(View.VISIBLE);
            player.mIvThumb.setImageResource(contentListBean.videoThumbResourceId);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);

            //点击视频开始图标的时候，才初始化视频
            if(v == player.mIvStartIcon){
                if(!NetUtils.isWifiActivity(context)){
                    T.showShort(context, "正在使用不是WiFi，请注意流量！");
                }

                if(contentListBean.videoUri != null && !TextUtils.isEmpty(contentListBean.videoUri.getPath())){
                    player.setVideoURI(contentListBean.videoUri);
                    player.start();
                    player.mIvStartIcon.setVisibility(View.GONE);
                    player.mIvThumb.setVisibility(View.GONE);
                }else{
                    T.showShort(context, "视频地址为空！");
                }
            }
        }

    }
}

