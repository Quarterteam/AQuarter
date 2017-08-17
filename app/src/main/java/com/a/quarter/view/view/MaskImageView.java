package com.a.quarter.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.a.quarter.R;

/**
 * Created by acer on 2017/7/23.
 * 启动页，圆形可见部分逐渐减小的效果
 */
public class MaskImageView extends ImageView{

    private AnimationListener animationListener;

    public MaskImageView(Context context) {
        super(context);
        init();
    }

    public MaskImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaskImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private Paint paint;
    private MyRunnable runnable;
    private int maskColor;
    private void init() {
        paint = new Paint();
        //圆形可见部分的颜色，白色半透明
        maskColor = Color.parseColor("#55FFFFFF");
        paint.setColor(maskColor);
        runnable = new MyRunnable();
    }

    private int width;//控件宽度
    private int height;//控件高度
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }

    private Rect maskRect;//代表圆形应该可见的部分的位置
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!isStart){
            return;
        }
        //canvas.save();
        if(maskRect==null){
            maskRect = new Rect();
            maskRect.set(0, 0, width, height);//此时设置圆形完全可见
        }
        canvas.clipRect(maskRect);//剪裁画布，只显示部分圆形
        canvas.drawCircle(width/2, height/2, width/2, paint);//画出圆形
        //canvas.restore();
        updateRect();//更新圆形下次可见的部分的位置
    }

    private int top = 0;//记录上次圆形可见部分的顶部位置
    private int timeGap = 20;//画面每次刷新之间的间隔
    private void updateRect() {
        if(top < height){//如果圆形还不是完全不可见
            top+=5;//更新下次可见部分的顶部应该在的位置
            maskRect.set(0, top, width, height);//更新可见部分的位置
            postDelayed(runnable, timeGap);//设置延迟一段时间，然后刷新画面
        }else{
            isStart = false;
            if(animationListener!=null){
                animationListener.onAnimEnd();
            }
        }
    }

    class MyRunnable implements Runnable{
        @Override
        public void run() {
            invalidate();
        }
    }

    public interface AnimationListener{
        void onAnimEnd();
    }

    public void setAnimListener(AnimationListener animationListener){
        this.animationListener = animationListener;
    }

    private boolean isStart = false;
    public void startAnim(){
        top = 0;
        isStart = true;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(runnable);
        animationListener = null;
    }
}
