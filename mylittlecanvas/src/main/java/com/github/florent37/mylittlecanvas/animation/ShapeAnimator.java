package com.github.florent37.mylittlecanvas.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.github.florent37.mylittlecanvas.listeners.InvalidateListener;
import com.github.florent37.mylittlecanvas.listeners.ViewInvalidateListener;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ShapeAnimator {
    private InvalidateListener invalidateListener;
    private List<ValueAnimator> animators = new ArrayList<>();
    private int repeatCount = 0;
    private long duration = 300;
    private long startDelay = 0;

    private Interpolator interpolator = new LinearInterpolator();

    private List<InitAction> initActions = new ArrayList<>();
    private List<OnAnimationStart> onAnimationStarts = new ArrayList<>();
    private List<OnAnimationEnd> onAnimationEnds = new ArrayList<>();
    private AtomicInteger endedAnimationsCount = new AtomicInteger(0);
    private boolean started = false;

    public ShapeAnimator(@NonNull InvalidateListener invalidateListener) {
        this.invalidateListener = invalidateListener;
    }

    public ShapeAnimator(@NonNull InvalidateListener invalidateListener, List<ValueAnimator> animators) {
        this(invalidateListener);
        play(animators);
    }

    public ShapeAnimator(@NonNull InvalidateListener invalidateListener, ValueAnimator... animators) {
        this(invalidateListener);
        play(animators);
    }

    public ShapeAnimator(@NonNull View view) {
        this(new ViewInvalidateListener(view));
    }

    public ShapeAnimator(@NonNull View view, List<ValueAnimator> animators) {
        this(new ViewInvalidateListener(view), animators);
    }

    public ShapeAnimator(@NonNull View view, ValueAnimator... animators) {
        this(new ViewInvalidateListener(view), animators);
    }

    public ShapeAnimator clear() {
        for (ValueAnimator animator : animators) {
            animator.cancel();
        }
        initActions.clear();
        onAnimationStarts.clear();
        onAnimationEnds.clear();
        animators.clear();
        started = false;
        return this;
    }

    public ShapeAnimator play(List<ValueAnimator> animators) {
        this.animators.addAll(animators);
        return this;
    }

    public ShapeAnimator play(ValueAnimator... animators) {
        if (animators != null) {
            this.animators.addAll(Arrays.asList(animators));
        }
        return this;
    }

    public ShapeAnimator setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public ShapeAnimator start(@Nullable final OnAnimationEnd onAnimationEnd) {
        this.onAnimationEnd(onAnimationEnd);
        return start();
    }

    public ShapeAnimator start() {
        if (!started) {
            started = true;
            endedAnimationsCount.set(0);

            for (OnAnimationStart onAnimationStart : onAnimationStarts) {
                onAnimationStart.onAnimationStart();
            }
            //do not use AnimatorSet because you cannot use setRepeatCount

            final int animationCount = animators.size();

            for (ValueAnimator animator : animators) {
                animator.setRepeatCount(repeatCount);
                animator.setDuration(duration);
                animator.setInterpolator(interpolator);
                animator.setStartDelay(startDelay);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        if (invalidateListener != null) {
                            invalidateListener.invalidate();
                        }
                    }
                });
                animator.addListener(
                        new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                final int finishedAnims = endedAnimationsCount.incrementAndGet();
                                if (animationCount == finishedAnims) {
                                    for (OnAnimationEnd onAnimationEnd : onAnimationEnds) {
                                        onAnimationEnd.onAnimationEnd();
                                    }
                                }
                            }
                        }
                );

                for (InitAction initAction : initActions) {
                    initAction.initAction();
                }

                animator.start();
            }
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

    public ShapeAnimator onAnimationEnd(@Nullable final OnAnimationEnd onAnimationEnd) {
        if (onAnimationEnd != null) {
            this.onAnimationEnds.add(onAnimationEnd);
        }
        return this;
    }

    public ShapeAnimator onAnimationStart(@Nullable final OnAnimationStart onAnimationStart) {
        if (onAnimationStart != null) {
            this.onAnimationStarts.add(onAnimationStart);
        }
        return this;
    }

    public ShapeAnimator withInitAction(@Nullable InitAction initAction) {
        if (initAction != null) {
            this.initActions.add(initAction);
        }
        return this;
    }

    public interface OnAnimationStart {
        void onAnimationStart();
    }

    public interface InitAction {
        void initAction();
    }

    public interface OnAnimationEnd {
        void onAnimationEnd();
    }
}
