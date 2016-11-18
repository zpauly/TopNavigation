package com.zpauly.topnavigation;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by zpauly on 2016/11/13.
 */

public class AnimUtils {
    public static final long ANIM_TIME = 500;

    public static ObjectAnimator translationDown(View v, float y, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY", 0, y);
        animator.setDuration(duration);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }

    public static ObjectAnimator translationUp(View v, float y, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY", y, 0);
        animator.setDuration(duration);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }
}
