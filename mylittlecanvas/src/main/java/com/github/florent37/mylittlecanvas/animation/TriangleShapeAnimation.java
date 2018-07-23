package com.github.florent37.mylittlecanvas.animation;

import android.animation.ValueAnimator;

import com.github.florent37.mylittlecanvas.shape.TriangleShape;

public class TriangleShapeAnimation extends ShapeAnimation<TriangleShape> {

    public TriangleShapeAnimation(TriangleShape shape) {
        super(shape);
    }

    public ValueAnimator centerX(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.moveCenterXTo((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator centerXTo(float... values) {
        return centerX(insertAtFirst(shape.getCenterX(), values));
    }

    public ValueAnimator centerXPlus(float... values) {
        final float[] newValues = new float[values.length];
        final float centerX = shape.getCenterX();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + centerX;
        }
        return centerXTo(newValues);
    }

    public ValueAnimator centerY(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.moveCenterYTo((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator centerYTo(float... values) {
        return centerY(insertAtFirst(shape.getCenterY(), values));
    }

    public ValueAnimator centerYPlus(float... values) {
        final float[] newValues = new float[values.length];
        final float centerY = shape.getCenterY();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + centerY;
        }
        return centerYTo(newValues);
    }

}
