package com.a.quarter.view.adapter.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import com.a.quarter.model.bean.recommend.ContentListBean;
import com.a.quarter.utils.QQLoginShareUtils;
import com.a.quarter.view.fragment.recommend.BannerFrescoImageLoader;
import com.a.quarter.view.view.ItemIjkPlayerView ;
import com.dl7.player.media.IjkPlayerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by acer on 2017/7/29.
 * 首页热门推荐，RecyclerView的适配器，使用了多条目加载
 * 目前共有三种条目：1、轮播图banner；2、视频条目；3、图片条目
 *
 * 遇到过的问题：
 *
 * 1、调用player的相关方法时会有轻微的卡顿，所以需要在子线程里释放资源（使用Thread或者RxJava）
 * 代码示例见VideoViewHolder类的resetItem方法。
 *
 * 2、RecyclerView前几次滑动的时候也会有卡顿，为什么？
 * （是因为需要创建ViewHolder吗？？？）
 * 待解决。
 *
 * 3、Slidingmenu滑动的时候，视频的位置会变成透明的，为什么？
 *
 * 过程中找到的解决视频区域透明的办法：
 *
 * 方法一（不推荐用）：在IjkVideoView类里
 * case RENDER_SURFACE_VIEW:
 * renderView.setZOrderOnTop(true);//renderView是SurfaceRenderView
 * 缺点：这样会把视频画面显示在屏幕除了状态栏之外的最外层，会遮挡住同一位置的其他一切视图（比如滑动的时候也会遮挡住页面的头部布局），
 * 而且被挡住的按钮等可点击的控件也可以点击。
 *
 * 方法二（不推荐用）：
 * renderView.setBackgroundColor(Color.GRAY);//renderView是SurfaceRenderView
 * 缺点：会导致视频画面被挡住，只能看见设置的背景色，看不到播放的视频。
 *
 *
 * ###视频区域透明的原因###：
 * 结合IjkPlayerView框架的源码发现，视频区域在滑动时总是变透明，是因为IjkVideoView默认使用了SurfaceView，而不是TextureView。
 * 大概是SurfaceView的特性导致的，暂时不知道怎么从SurfaceView方面解决透明的问题（待解决）。
 * 所以改用TextureView，IjkPlayerView框架里使用的也是TextureView。
 * （IjkPlayerView框架是对原生IjkPlayer的二次封装）
 *
 * 方法三（可以使用）：
 * 在使用原生IjkVideoView时，使用TextureView，具体代码是ijkVideoView.setRender(IjkVideoView.RENDER_TEXTURE_VIEW);
 * 或者使用IjkPlayerView框架（框架内部也是使用了TextureView）。
 * 缺点：
 * 使用TextureView，需要TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)，也就是API等级14，Android 4.0
 *
 *
 * 4、MediaController不会跟随着SlidingMenu的滑动而移动?
 *
 * ###原因分析###：
 * 使用了原生IjkPlayer里的AndroidMediaController，
 * 查看源码，发现MediaController的本质是一个FrameLayout，源码里使用的是
 * mWindowManager.addView(mDecor, mDecorLayoutParams);
 * mWindowManager.removeView(mDecor);
 * 直接在屏幕上添加，移除MediaController，
 * 而不是添加到条目布局上了。
 *
 * 解决办法：改用IjkPlayerView视频框架，不再直接使用IjkVideoView，
 * 这样不会有MediaController不跟随着SlidingMenu的滑动而移动的问题
 * （因为IjkPlayerView视频框架里没有使用IjkPlayer提供的AndroidMediaController，而是自己封装了controller相关的控件），
 * 也可以同时解决SlidingMenu滑动时视频区域变透明的问题，
 * 并且IjkPlayerView功能也更全，比如已经有弹幕，全屏切换，滑动屏幕调节音量、亮度，截图，加载失败重试等功能。
 *
 *
 * 5、视频停止的问题？
 * 怎么监听一个条目的完全消失，在条目完全消失的时候，停止播放该条目的视频？
 * 使用RecyclerView的OnChildAttachStateChangeListener监听条目从屏幕上完全消失的时刻，这时停止视频，并重置该条目状态。
 * 代码在HotFragment里。
 *
 * 6、避免内存泄露
 * IjkPlayerView类里一个全局变量是关联的Activity，怎么在Fragment销毁的时候置为null？
 * IjkPlayerView类里注册了锁屏状态，网络状态，电量改变三个广播，怎么在Fragment销毁的时候注销广播？（不手动注销的话，logcat里会有提示）
 * 怎么在Fragment销毁的时候停止轮播图？
 *
 * 一个条目从屏幕上完全消失了，下一刻可能该条目的view对象就会被重新添加到屏幕上，让下一个条目使用，达到复用条目的目的，
 * 复用条目的时候，条目上的IjkPlayerView也会被复用，
 * 如果在条目从屏幕上完全消失的时候注销广播，复用的时候IjkPlayerView里就不能再接收到广播，会出现错误。
 * 条目可能被复用，所以不应该在条目从屏幕上完全消失的时候注销广播。
 *
 * 解决办法：
 * 使用集合保存每一个新建的IjkPlayerView对象，在Activity或者Fragment销毁的时候，遍历集合，注销每一个IjkPlayerView里的广播。
 * 使用集合保存每一个新建的Banner对象，在Activity或者Fragment销毁的时候，遍历集合，停止每一个轮播图。
 *
 * IjkPlayerView中的视频缩略图控件改成SimpleDraweeView
 * com.a.quarter.view.view.ItemIjkPlayerView
 *
 * TODO
 * 点击右上角才出现收藏等图标？
 * 前几次滑动RecyclerView的时候会有很明显的卡顿，怎么优化？
 */
public class ContentListAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<ContentListBean> list;
    public static final int TYPE_HEAD1 = 0;//轮播图
    public static final int TYPE_IMG = 1;//图片条目
    public static final int TYPE_VIDEO = 2;//视频条目
    private final LayoutInflater inflater;

    private ArrayList<ItemIjkPlayerView> players;
    private ArrayList<Banner> banners;

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

    //    private Banner banner;
    //轮播图对应的ViewHolder
    class Head1ViewHolder extends RecyclerView.ViewHolder {
        private Banner banner;

        public Head1ViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
            if(banners==null){
                banners = new ArrayList<Banner>();
            }
            banners.add(banner);

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

//        public void onDestory(){
//            if(banner!=null){
//                banner.stopAutoPlay();
//                banner = null;
//            }
//        }

    }

    //视频条目和图片条目在布局上有一些相同的地方，所以在这里写了一个父类，抽取出相同的部分
    class NormalItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Animation.AnimationListener{

        //ImageView ivUserIcon;
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
            //ivUserIcon.setImageResource(contentListBean.userIconResourceId);
            ivUserIcon.setActualImageResource(contentListBean.userIconResourceId);
            //ivUserIcon.setImageURI();
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
    //视频条目
    public class VideoViewHolder extends NormalItemViewHolder implements ItemIjkPlayerView.OnPlayCircleClickListener {

        private final View include_video;
        //        public IjkPlayerView player;
        public ItemIjkPlayerView player;

        public VideoViewHolder(View itemView) {
            super(itemView);

//            player = (IjkPlayerView) itemView.findViewById(R.id.ijkPlayerView);
            player = (ItemIjkPlayerView) itemView.findViewById(R.id.ijkPlayerView);
            player.setOnPlayCircleClickListener(this);
            player.init(true);
            if(players==null){
                players = new ArrayList<ItemIjkPlayerView>();
            }
            players.add(player);
            include_video = (View) itemView.findViewById(R.id.include_video);
        }

        //刚显示该条目的时候，加载该条目对应的视频缩略图
        public void showVideo() {
            player.mPlayerThumb.setActualImageResource(list.get(position).videoThumbResourceId);
            //player.mPlayerThumb.setImageResource(list.get(position).videoThumbResourceId);
        }

        /**
         * 重置视频条目，回复到未播放的状态。（重新显示视频缩略图和开始播放的图标，隐藏其他相关的视频控制控件）
         *
         *
         * */
        public void resetItem() {
            //LogUtils.i("resetItem start");
//            if(player!=null && player.mPlayerThumb.getVisibility()!=View.VISIBLE){
            if(player!=null && player.mIvPlayCircle.getVisibility()!=View.VISIBLE){
                //long t = System.currentTimeMillis();
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        player.resetVideoView();//因为MediaPlayer.release()释放资源的时候，会有些耗时，所以放在子线程里
                        emitter.onComplete();
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();//耗时：42

                //player.resetVideoView();放在主线程，会有些卡顿
                player.resetPlayerView();//player.init()
                //TODO LogUtils.i("resetItem removePlayerView，耗时："+(System.currentTimeMillis()-t));
            }
//            player.mPlayerThumb.setImageResource(list.get(position).videoThumbResourceId);
            player.mPlayerThumb.setActualImageResource(list.get(position).videoThumbResourceId);
            //LogUtils.i("resetItem end");
        }

        @Override
        public void onPlayCircleClicked() {
            //初始化需要播放的视频的信息
            contentListBean = list.get(position);
            player.setTitle("这是个跑马灯TextView，标题要足够长才会跑。-(゜ -゜)つロ 乾杯~")
                    .setSkipTip(1000*60*1)
                    .enableDanmaku()
                    .setDanmakuSource(context.getResources().openRawResource(R.raw.bili))
                    //.setVideoSource(null, contentListBean.videoUri.getPath(), null, null, null)得到的是网址的后半段
                    //比如/videolib3/1611/28/GbgsL3639/SD/movie_index.m3u8: No such file or directory
                    .setVideoSource(null, contentListBean.videoUri.toString(), null, null, null)
                    .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_MEDIUM);

            //开始播放 让右边的include出现
            include_video.setVisibility(View.VISIBLE);

            //T.showShort(context, "开始播放视频！");

            // TODO: 2017/8/2   //设置播放完成地监听
            player.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    include_video.setVisibility(View.GONE);
                }
            });
        }

    }

    /**
     * Activity的onPause()或者Fragment的onPause()或者Fragment的onHiddenChanged()方法里调用
     */
    public void onPause() {
        //暂停轮播图
        if(banners!=null){
            for (int i = 0; i < banners.size(); i++) {
                banners.get(i).stopAutoPlay();
            }
        }
        //暂停视频播放
        if(players!=null) {
            for (int i = 0; i < players.size(); i++) {
                players.get(i).onPause();
            }
        }
    }

    /**
     * Fragment的onHiddenChanged()里调用
     */
    public void onResume() {
        //恢复轮播图的播放
        if(banners!=null){
            for (int i = 0; i < banners.size(); i++) {
                banners.get(i).startAutoPlay();
            }
        }
    }

    /**
     * Activity的onDestroy()或者Fragment的onDestroyView()里调用
     */
    public void onDestroy(){
        //停止轮播图
        if(banners!=null) {
            for (int i = 0; i < banners.size(); i++) {
                banners.get(i).releaseBanner();
                //LogUtils.i("onDestroy releaseBanner "+banners.get(i));
            }
            banners.clear();
            banners = null;
        }
        //停止视频播放
        if(players!=null) {
            for (int i = 0; i < players.size(); i++) {
                players.get(i).onDestroy();
                //LogUtils.i("onDestroy player "+players.get(i));
            }
            players.clear();
            players = null;
        }
    }

}

