package com.github.florent37.mylittlecanvas.touch;

import android.animation.ValueAnimator;
import android.view.MotionEvent;

public interface EventAnimator {
    ValueAnimator animate(MotionEvent event);
}