package com.exa.framelib_rrm.base.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.exa.framelib_rrm.R;

/**
 * 自定义宽高比的ImageView</br>
 * xml里设置宽高比，比如whiv:w_d_h="16:9"</br>
 * 如果xml里没有设置宽高比，可以作为一个普通的ImageView使用</br>
 * 在点击时，可以出现阴影。</br>
 * 
 */
public class ShadowRatioImageView extends ImageView {
	private int wRatio = -1;//默认宽度比例
	private int hRatio = -1;//默认高度比例
	private Drawable drawable;
	private boolean drawShadowWhenPressed;
	private PorterDuffColorFilter cf;

	public ShadowRatioImageView(Context context) {
		this(context,null);
	}
	
	public ShadowRatioImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public ShadowRatioImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}
	
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public ShadowRatioImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);//安卓4.2.2上没有该方法 Caused by: java.lang.NoSuchMethodError: android.widget.ImageView.<init>

	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WHImageView);
		//if(a.hasValue(R.attr.WHImageView_wdh)){//会报数组下表越界异常 (因为不该用attr)
		if(a.hasValue(R.styleable.WHImageView_w_d_h)){
			String wdh = a.getString(R.styleable.WHImageView_w_d_h);
			if(wdh!=null && wdh.contains(":")){
				String[] split = wdh.split(":");
				wRatio = Integer.parseInt(split[0]);
				hRatio = Integer.parseInt(split[1]);
			}
		}
		a.recycle();
		
		setDrawShadowWhenPressed(false);//默认不可以绘制阴影
		
	}


	public void setRatio(int ratio){//宽：高=w：h=r
		if(ratio>0){
			this.hRatio = 1;
			this.wRatio = hRatio*ratio;
		}
	}


	/* 
	 * onMeasure是measure方法引起的回调,而measure方法是父VIew在测量子View会调用子的View的measure方法
     * 所以widthMeasureSpec(宽度测量规则)和heightMeasureSpec(高度测量规则)是父VIew在调用子View的measure方法时计算好的
     * MeasureSpec： 测量规则类，由size和mode2个因素组成:
     *   size: 就是指定的大小值
     *   mode: MeasureSpec.AT_MOST : 对应的是warp_content;
     *         MeasureSpec.EXACTLY : 对应的是具体的dp值，match_parent
     *         MeasureSpec.UNSPECIFIED: 未定义的，一般用adapter的view的测量中
     *         
	 * CONTRACT: When overriding this method, you must call setMeasuredDimension(int, int) 
	 * to store the measured width and height of this view. Failure to do so will trigger 
	 * an IllegalStateException, thrown by measure(int, int). 
	 * Calling the superclass' onMeasure(int, int) is a valid use. 
	 * 
	 * 优先根据width来确定height
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		if(wRatio>0){

			 //获取宽度的模式和尺寸
			final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	        //获取高度的模式和尺寸
			final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	        
	        final int widthSize;
	        final int heightSize;
	        if(widthMode == MeasureSpec.EXACTLY){
	        	widthSize = MeasureSpec.getSize(widthMeasureSpec);
	    		heightSize = (int) (widthSize*hRatio/wRatio+0.5f);//根据宽度和比例计算高度
	    		heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
	    	}else if(heightMode==MeasureSpec.EXACTLY){
	    		heightSize = MeasureSpec.getSize(heightMeasureSpec);
	    		widthSize = (int) (heightSize*wRatio/hRatio+0.5f);
	    		widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.EXACTLY);
	    	}
//	    	else{
//	    		throw new RuntimeException("无法设定宽高比");//交给系统
//	    	}
	        	
//			//1.从widthMeasureSpec中反向获取父VIew计算好的size
//	        int width = MeasureSpec.getSize(widthMeasureSpec);//不需要 - getPaddingLeft() - getPaddingRight()？
//	        //2.根据宽高比和width，计算出对应的height
//            float height = width*hRatio/wRatio + 0.5f;
//            //3.重新组建heightMeasureSpec，传递给super.onMeasure
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) height,MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
	}
	
	public void setDrawShadowWhenPressed(boolean drawShadowWhenPressed){
		this.drawShadowWhenPressed = drawShadowWhenPressed;
		if(this.drawShadowWhenPressed){
			setClickable(true);
			if(cf==null){
				cf = new PorterDuffColorFilter(Color.LTGRAY, Mode.MULTIPLY);
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//画上层阴影
			if(isClickable() && drawShadowWhenPressed){
				drawable = getDrawable();
				if(drawable!=null){
					//drawable.setColorFilter(Color.BLACK, Mode.MULTIPLY);
					//可以绘制出阴影，不是整个ImageView，而是drawable不透明的部分；透明的部分不会被绘制阴影。
					//如果选择的颜色是半透明的，阴影也是半透明的，不会挡住下面的图片。但每次都会创建一个新的PorterDuffColorFilter对象
					
					drawable.setColorFilter(cf);
				}
			}
			break;
			
		case MotionEvent.ACTION_UP://会被拦截(需要setClickable(true);)
		case MotionEvent.ACTION_CANCEL:
			if(drawShadowWhenPressed){
				if(drawable!=null){
					drawable.clearColorFilter();
				}
			}
			break;
			
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

}

