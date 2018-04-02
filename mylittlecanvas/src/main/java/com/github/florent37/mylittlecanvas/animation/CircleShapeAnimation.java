package com.github.florent37.mylittlecanvas.animation;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;

import com.github.florent37.mylittlecanvas.shape.CircleShape;

public class CircleShapeAnimation extends ShapeAnimation<CircleShape> {

    public CircleShapeAnimation(CircleShape shape) {
        super(shape);
    }

    public ValueAnimator centerX(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.setCenterX((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator centerXTo(float ... values) {
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
                shape.setCenterY((Float) animation.getAnimatedValue());
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

    public ValueAnimator radius(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.setRadius((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator radiusTo(float... values) {
        return radius(insertAtFirst(shape.getRadius(), values));
    }

    public ValueAnimator radiusBy(float... values) {
        final float[] newValues = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * shape.getRadius();
        }
        return radius(newValues);
    }

    public ValueAnimator radiusPlus(float... values) {
        final float[] newValues = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + shape.getRadius();
        }
        return radiusTo(newValues);
    }

    public ValueAnimator borderColorTo(final int... color) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(insertAtFirst(shape.getBorderColor(), color));
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                shape.setBorderColor((int) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator borderWidthTo(final float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.getBorderWidth(), values));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                shape.setBorderWidth((int) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

}
