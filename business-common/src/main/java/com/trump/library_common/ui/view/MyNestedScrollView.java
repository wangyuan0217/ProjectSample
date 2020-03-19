package com.trump.library_common.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.core.widget.NestedScrollView;

/**
 * @author 王元_Trump
 * @time 2019/12/09 17:23
 * @desc 为ScrollView添加滚动监听
 */
public class MyNestedScrollView extends NestedScrollView {

    private OnScrollListener onScrollListener;

    public MyNestedScrollView(Context context) {
        super(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.onScroll(t);
        }
    }

    /**
     * 接口对外公开
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**
     * 滚动的回调接口
     *
     * @author xiaanming
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         *
         * @param scrollY 、
         */
        void onScroll(int scrollY);
    }

    private int lastInterceptX;
    private int lastInterceptY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;
                //如果是垂直滑动，则拦截
                if (Math.abs(deltaX) - Math.abs(deltaY) < 0) {
                    intercept = true;
                } else {
                    intercept = false;
                }

                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        lastInterceptX = x;
        lastInterceptY = y;
        super.onInterceptTouchEvent(ev);//这一句一定不能漏掉，否则无法拦截事件
        return intercept;
    }
}
