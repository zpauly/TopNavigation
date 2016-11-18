package com.zpauly.topnavigation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by zpauly on 2016/11/16.
 */

public class TopNavigationLayout extends LinearLayout {
    private final String TAG = getClass().getName();

    private View mChildRV;

    private float childHeight;

    private boolean isOpened;

    private long animDuration;
    private ObjectAnimator openAnimator;
    private ObjectAnimator closeAnimator;

    public TopNavigationLayout(Context context) {
        super(context);
        init();
    }

    public TopNavigationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TopNavigationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TopNavigationLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        animDuration = AnimUtils.ANIM_TIME;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for (int i = 0; i < getChildCount(); i++) {
            if ((mChildRV = getChildAt(i)) instanceof RecyclerView) {
                break;
            }
            if (i == getChildCount() - 1) {
                throw new IllegalArgumentException("No RecyclerView in TopNavigationView.");
            }
        }
        mChildRV.layout(getLeft(), getTop() - mChildRV.getHeight(), getRight(), getTop());
        childHeight = mChildRV.getHeight();
        open();
    }

    public void resetAnimDuration(long duration) {
        this.animDuration = duration;
    }

    public void open() {
        if (!isOpened) {
            openAnimator = AnimUtils.translationDown(mChildRV, childHeight, animDuration);
            openAnimator.start();
            isOpened = true;
        }
    }

    public void close() {
        if (isOpened) {
            closeAnimator = AnimUtils.translationUp(mChildRV, childHeight, animDuration);
            closeAnimator.start();
            isOpened = false;
        }
    }
}
