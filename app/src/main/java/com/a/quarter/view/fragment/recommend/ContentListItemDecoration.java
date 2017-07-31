package com.a.quarter.view.fragment.recommend;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 首页热门推荐RecyclerView使用的分割线
 */
public class ContentListItemDecoration extends RecyclerView.ItemDecoration {

    //分割线颜色
    private int dividerColor;
    //分割线高度
    private int dividerHeight;
    //画笔
    private final Paint mPaint;
    //头部数量
    private int headerCount;
    //左右与屏幕边缘的间距
    private int inset;

    public ContentListItemDecoration() {
        this.dividerColor = Color.GRAY;
        this.dividerHeight = 3;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(dividerColor);
    }

    public void setDividerColor(int dividerColor){
        this.dividerColor = dividerColor;
        mPaint.setColor(dividerColor);
    }

    public void setDividerHeight(int height){
        this.dividerHeight = height;
    }

    public void setHeaderCount(int headerCount){
        this.headerCount = headerCount;
    }

    public void setDividerInset(int inset){
        this.inset = inset;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
    }

    //一些临时变量
    private int left;
    private int right;
    private int childCount;
    private View child;
    private RecyclerView.LayoutParams params;
    private int top;
    private int bottom;
    public void drawVertical(Canvas c, RecyclerView parent) {
        //计算分割线在x轴上的起始位置（RecyclerView的paddingLeft加上设置的分割线inset）
        left = parent.getPaddingLeft() + inset;
        //计算分割线在x轴上的结束位置（RecyclerView的宽度减去paddingRight，减去设置的分割线inset）
        right = parent.getWidth() - parent.getPaddingRight() - inset;
        //得到当前屏幕上显示的条目的数量
        childCount = parent.getChildCount();
        //通过循环，绘制出分割线（i < childCount - 1表示最后一个条目下方不用画分割线）
        for (int i = 0; i < childCount - 1; i++) {
            //得到条目对象
            child = parent.getChildAt(i);
            //得到条目的布局参数
            params = (RecyclerView.LayoutParams) child.getLayoutParams();
            //如果该条目是header的话，在它的下方不画分割线
            if(params.getViewAdapterPosition() <= headerCount-1){
                continue;
            }
            //计算分割线在y轴上的起始位置
            top = child.getBottom() + params.bottomMargin;
            //计算分割线在y轴上的结束位置
            bottom = top + dividerHeight;
            //画出分割线
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    private int po;//临时变量
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //根据条目对象view，得到该条目在集合中的position
        po = parent.getChildAdapterPosition(view);
        //设置该条目对应的offset
        if(po > headerCount-1 && po < parent.getAdapter().getItemCount()-1){
            outRect.set(0, 0, 0, dividerHeight);
        }//头部之间，头部和第一个条目之间，最后一个条目的下面不用绘制分割线，所以不用设置offset
    }
}
