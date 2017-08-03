package com.a.quarter.view.adapter.recommend;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a.quarter.R;
import com.a.quarter.utils.QQLoginShareUtils;
import com.a.quarter.view.fragment.recommend.BannerFrescoImageLoader;
import com.a.quarter.view.fragment.recommend.BannerLocalImageLoader;
import com.a.quarter.model.bean.recommend.ContentListBean;
import com.exa.framelib_rrm.utils.LogUtils;
import com.exa.framelib_rrm.utils.NetUtils;
import com.exa.framelib_rrm.utils.T;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import java.util.ArrayList;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.schedulers.Schedulers;
import media.AndroidMediaController;
import media.IjkVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by acer on 2017/7/29.
 * 首页热门推荐，RecyclerView的适配器，使用了多条目加载
 * 目前共有三种条目：1、轮播图banner；2、视频条目；3、图片条目
 *
 * 是创建ViewHolder的时候，出现的卡顿？
 *
 * 视频停止的问题？
 * 所有条目只使用一个IjkVideoView，remove之后可能会出现上个播放视频的条目在视频的位置一片空白的情况
 * 怎么监听一个条目的完全消失，在条目完全消失的时候，remove掉IjkVideoView？
 * （使用RecyclerView的OnChildAttachStateChangeListener监听条目从屏幕上移除的时刻，这时停止视频）
 *
 */
public class ContentListAdapter extends RecyclerView.Adapter {

    private Context context;
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

    private Banner banner;
    //轮播图对应的ViewHolder
    class Head1ViewHolder extends RecyclerView.ViewHolder {
        //private Banner banner;

        public Head1ViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);

            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(R.mipmap.banner1);
            list.add(R.mipmap.banner2);
            list.add(R.mipmap.banner3);
            list.add(R.mipmap.banner4);
            list.add(R.mipmap.banner5);

            //开始自动轮播
            banner.setImages(list)
                    //.setImageLoader(new BannerLocalImageLoader())
                    .setImageLoader(new BannerFrescoImageLoader())
                    .start();

        }

    }

    //视频条目和图片条目在布局上有一些相同的地方，所以在这里写了一个父类，抽取出相同的部分
    class NormalItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Animation.AnimationListener{

//        ImageView ivUserIcon;
        SimpleDraweeView ivUserIcon;
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
            ivUserIcon = (SimpleDraweeView) itemView.findViewById(R.id.iv_user_icon);
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

                    QQLoginShareUtils.setShare("","hello","hello",context);
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
//            ivUserIcon.setImageResource(contentListBean.userIconResourceId);
            ivUserIcon.setActualImageResource(contentListBean.userIconResourceId);
//            ivUserIcon.setImageURI();
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
        //ImageView ivImg;
        SimpleDraweeView ivImg;

        public ImageViewHolder(View itemView) {
            super(itemView);

            ivImg = (SimpleDraweeView) itemView.findViewById(R.id.iv_img);
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
            //ivImg.setImageResource(list.get(position).imgResourceId);
            ivImg.setActualImageResource(list.get(position).imgResourceId);
        }
    }

    private ContentListBean contentListBean;
    public IjkVideoView player;
    //视频条目
    public class VideoViewHolder extends NormalItemViewHolder {

        private final View include_video;
        //        MyIjkVideoView player;
        FrameLayout videoContainer;
        SimpleDraweeView ivVideoThumb;
        ImageView ivVideoStartIcon;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoContainer = (FrameLayout) itemView.findViewById(R.id.fl_video_container);
            ivVideoThumb = (SimpleDraweeView) itemView.findViewById(R.id.iv_video_thumb);
            ivVideoStartIcon = (ImageView) itemView.findViewById(R.id.iv_video_start_icon);
            include_video = (View) itemView.findViewById(R.id.include_video);

            ivVideoStartIcon.setOnClickListener(this);
        }

        public void showVideo() {
            if(ivVideoStartIcon.getVisibility()!=View.VISIBLE){
                ivVideoStartIcon.setVisibility(View.VISIBLE);
                ivVideoThumb.setVisibility(View.VISIBLE);
            }
            ivVideoThumb.setActualImageResource(list.get(position).videoThumbResourceId);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);

            //点击视频开始图标的时候，才初始化视频
            if(v == ivVideoStartIcon){
                if(!NetUtils.isConnected()){
                    T.showShort(context, "没有连接网络！");
                    return;
                }
                if(!NetUtils.isWifiActivity(context)){
                    T.showShort(context, "正在使用不是WiFi，请注意流量！");
                }

                contentListBean = list.get(position);
                if(contentListBean.videoUri != null && !TextUtils.isEmpty(contentListBean.videoUri.getPath())){
                    if(player==null){
                        player = new IjkVideoView(context);
                        player.setMediaController(new AndroidMediaController(context));
                        player.setBackgroundColor(Color.GRAY);
                        player.setRender(IjkVideoView.RENDER_TEXTURE_VIEW);//如果使用SurfaceView的话，SlidingMenu滑动时视频区域会变透明
                    }else{
                        //player.stopPlayback();
                        player.release(true);
                        ViewGroup parent = (ViewGroup) player.getParent();
                        if(parent!=null){
                            parent.removeView(player);
                            //所有条目只使用一个IjkVideoView，remove之后可能会出现上个播放视频的条目在视频的位置一片空白的情况
                        }
                    }
                    videoContainer.addView(player);
                    player.setVideoURI(contentListBean.videoUri);
                    player.start();
                    //开始播放 让右边的include出现
                    include_video.setVisibility(View.VISIBLE);

                    T.showShort(context, "开始播放视频！");
                    ivVideoThumb.setVisibility(View.INVISIBLE);
                    ivVideoStartIcon.setVisibility(View.GONE);
                    // TODO: 2017/8/2   //设置播放完成地监听

                    player.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(IMediaPlayer iMediaPlayer) {
                            include_video.setVisibility(View.GONE);
                            ivVideoThumb.setVisibility(View.VISIBLE);

                        }
                    });

                }else{
                    T.showShort(context, "视频地址为空！");
                }
            }
        }

        /**
         * 回复到未播放的状态，
         * 该方法里调用player的相关方法时会有轻微的卡顿，所以需要在子线程里释放资源（使用Thread或者RxJava）
         *
         * RecyclerView第一次滑动的时候也会有卡顿，为什么？
         * Slidingmenu滑动的时候，视频的位置会变成透明的，为什么？
         * case RENDER_SURFACE_VIEW:
         * SurfaceRenderView renderView.setZOrderOnTop(true);
         * 一开始视频还没加载好的时候，会是白色的背景。
         *
         * 会挡住原来在视频右边的按钮？
         * 会把视频画面显示在屏幕的最外层，只在状态栏的下层，而且被挡住的按钮也可以点击。
         *
         * renderView.setBackgroundColor(Color.GRAY);会导致视频画面被挡住。
         *
         * 视频区域在滑动时总是变透明，是因为默认使用的是SurfaceView。
         *
         * 使用player.setRender(IjkVideoView.RENDER_TEXTURE_VIEW);可以解决透明问题，
         * 但是TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)？
         *
         *
         * MediaController不会跟随着移动?
         * MediaController的本质是一个FrameLayout
         * 因为使用的是
         * mWindowManager.addView(mDecor, mDecorLayoutParams);
         * mWindowManager.removeView(mDecor);
         * 直接在屏幕上添加，移除MediaController
         *
         * renderView.setBackgroundColor(Color.DKGRAY);
         *
         * 可以写一个自己的MediaController吗？
         * 不用，只需要使用updateFloatingWindowLayout
         *
         * 点击右上角才出现那些图标
         *
         * */
        public void resetItem() {
            //LogUtils.i("resetItem start");
            if(player!=null){
                ViewGroup parent = (ViewGroup) player.getParent();
                if(parent!=null){
                    //long t = System.currentTimeMillis();
                    parent.removeView(player);
//                    new Thread(){
//                        @Override
//                        public void run() {
//                            player.release(true);
//                        }
//                    }.start();
                    Observable.create(new ObservableOnSubscribe<Integer>() {
                        @Override
                        public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                            player.release(true);
                            //LogUtils.i("resetItem release");
                            emitter.onComplete();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();//耗时：42

                    //LogUtils.i("resetItem removePlayerView，耗时："+(System.currentTimeMillis()-t));
                }
            }
            if(ivVideoStartIcon.getVisibility()!=View.VISIBLE) {
                ivVideoStartIcon.setVisibility(View.VISIBLE);
                ivVideoThumb.setVisibility(View.VISIBLE);
                ivVideoThumb.setActualImageResource(list.get(position).videoThumbResourceId);
                //LogUtils.i("resetItem setActualImageResource");
            }
            //LogUtils.i("resetItem end");
        }
    }

    public void onDestory(){
        if(player!=null){
            ViewGroup parent = (ViewGroup) player.getParent();
            if(parent!=null){
                parent.removeView(player);//所有条目只使用一个IjkVideoView，
            }
            player.stopPlayback();


            player.release(true);
            player = null;
        }
        if(banner!=null){
            banner.stopAutoPlay();
            banner = null;
        }
        context = null;
    }

    public void onStop() {
        if(player!=null){
            player.pause();
        }
    }
}

