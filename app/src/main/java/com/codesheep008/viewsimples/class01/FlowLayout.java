package com.codesheep008.viewsimples.class01;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.codesheep008.viewsimples.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FlowLayout extends ViewGroup {

    private int mHorizontalSpacing = UIUtils.dp2px(18);
    private int mVerticalSpacing = UIUtils.dp2px(18);

    private List<List<View>> allLineViews = new ArrayList<>();
    private List<Integer> maxLines = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void clearMeasureParams() {
        //防止内存抖动,所以使用clear
        allLineViews.clear();
        maxLines.clear();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //放在这里是因为有些父容器会多次调用子view的onmeasure 比如FrameLayout
        clearMeasureParams();

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        //父亲给我的宽高
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        int parentNeedWidth = 0;
        int parentNeedHeight = 0;

        //单行已经使用的宽度
        int singleLineUsedWidth = 0;
        //单行最高的高度
        int singleMaxHeight = 0;

        List<View> singleViews = new ArrayList<>();

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //父view中测量子view
            LayoutParams lps = childView.getLayoutParams();
            Log.e("gyy", "child lps.width = -> " + lps.width);
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, lps.width);
            int childheightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, lps.height);
            childView.measure(childWidthMeasureSpec, childheightMeasureSpec);
            int childViewWidth = childView.getMeasuredWidth();
            int childViewHeight = childView.getMeasuredHeight();
            Log.e("gyy", "childViewWidth => " + childViewWidth + "  childViewHeight -->" + childViewHeight);
            //如果已有宽度+需要的宽度()>父view给自己的宽度,那么换行
            if (childViewWidth + mHorizontalSpacing + singleLineUsedWidth > selfWidth) {
                //换行需要记录一行的view与这一行最高的高度
                allLineViews.add(singleViews);
                maxLines.add(singleMaxHeight);

                parentNeedWidth = Math.max(singleLineUsedWidth + mHorizontalSpacing, parentNeedWidth);
                parentNeedHeight += mVerticalSpacing + singleMaxHeight;

                //清空数据
                singleViews = new ArrayList<>();
                singleMaxHeight = 0;
                singleLineUsedWidth = 0;
            }
            //换不换行都需要做的事,保存当前的view
            singleViews.add(childView);
            singleLineUsedWidth = singleLineUsedWidth + mHorizontalSpacing + childViewWidth;
            singleMaxHeight = Math.max(singleMaxHeight, childViewHeight);

            //todo 为什么需要单独处理最后一行呢?因为最后一行没有换行的话是没有加入到大集合中的allLineViews.add(singleViews);
            //处理最后一行
            if (i == childCount - 1) {
                allLineViews.add(singleViews);
                maxLines.add(singleMaxHeight);
                parentNeedHeight = parentNeedHeight + mVerticalSpacing + singleMaxHeight;
                //todo:疑问? 这里不加上widthSpace是不是更好
                parentNeedWidth = Math.max(singleLineUsedWidth + mHorizontalSpacing, parentNeedWidth);
            }
        }

        //再次度量自己,并且保存值
        //根据子View的度量结果，来重新度量自己ViewGroup
        // 作为一个ViewGroup，它自己也是一个View,它的大小也需要根据它的父亲给它提供的宽高来度量
        int realWidth = (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) ?
                //这里需要的是一个度量后的宽度,而不是widthMeasureSpec
                selfWidth : parentNeedWidth;
        int realheight = (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) ?
                selfHeight : parentNeedHeight;
        setMeasuredDimension(realWidth, realheight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int curL = getPaddingLeft();
        int curT = getPaddingTop();
        for (int i = 0; i < allLineViews.size(); i++) {
            //布局一行
            List<View> lineViews = allLineViews.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                int right = curL + view.getMeasuredWidth();
                view.layout(curL, curT, right, curT + view.getMeasuredHeight());
                curL = right + mHorizontalSpacing;
            }
            curL = getPaddingLeft();
            curT = curT + maxLines.get(i) + mVerticalSpacing;
        }
    }
}
