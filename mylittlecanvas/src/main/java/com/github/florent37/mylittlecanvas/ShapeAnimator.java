package com.github.florent37.mylittlecanvas;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeAnimator {
    private View view;
    private List<ValueAnimator> animators = new ArrayList<>();
    private int repeatCount = 1;
    private long duration = 300;
    private long startDelay = 0;

    public ShapeAnimator(@NonNull View view) {
        this.view = view;
    }

    public void clear(){
        for (ValueAnimator animator : animators) {
            animator.cancel();
        }
        animators.clear();
    }

    public ShapeAnimator playTogether(List<ValueAnimator> animators){
        this.animators.addAll(animators);
        return this;
    }

    public ShapeAnimator playTogether(ValueAnimator...animators){
        if (animators != null) {
            this.animators.addAll(Arrays.asList(animators));
        }
        return this;
    }

    public ShapeAnimator start(){
        //do not use AnimatorSet because you cannot use setRepeatCount
        for (ValueAnimator animator : animators) {
            animator.setRepeatCount(repeatCount);
            animator.setDuration(duration);
            animator.setStartDelay(startDelay);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (view != null) {
                        view.postInvalidate();
                    }
                }
            });
            animator.start();
        }
        return this;
    }

    public ShapeAnimator setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public ShapeAnimator setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public ShapeAnimator setStartDelay(long startDelay) {
        this.startDelay = startDelay;
        return this;
    }
}
