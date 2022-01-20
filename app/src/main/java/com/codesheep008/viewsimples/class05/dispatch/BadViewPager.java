package com.codesheep008.viewsimples.class05.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class BadViewPager extends ViewPager {

    private int mLastX, mLastY;

    public BadViewPager(@NonNull Context context) {
        super(context);
    }

    public BadViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //return true:代表自己拦截,viewpager能滑动,内部的listview不能滑动
    //return false:代表自己不拦截,内部的listview能滑动,viewpager不能滑动
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    // 外部拦截法：父容器处理冲突
    // 我想要把事件分发给谁就分发给谁
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
////        if (event.getAction() == MotionEvent.ACTION_DOWN){
////            super.onInterceptTouchEvent(event);
////            return false;
////        }
////        return true;
//
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//                mLastX = (int) event.getX();
//                mLastY = (int) event.getY();
//                break;
//            }
//            case MotionEvent.ACTION_MOVE: {
//                int deltaX = x - mLastX;
//                int deltaY = y - mLastY;
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    return true;
//                }
//                break;
//            }
//            case MotionEvent.ACTION_UP: {
//                break;
//            }
//            default:
//                break;
//        }
//
//        return super.onInterceptTouchEvent(event);
//
//    }
}
