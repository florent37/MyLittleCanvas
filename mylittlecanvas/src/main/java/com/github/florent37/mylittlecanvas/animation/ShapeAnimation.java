package com.github.florent37.mylittlecanvas.animation;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.support.annotation.FloatRange;

import com.github.florent37.mylittlecanvas.shape.Shape;

public class ShapeAnimation<S extends Shape> {
    protected final S shape;

    public ShapeAnimation(S shape) {
        this.shape = shape;
    }

    protected float[] insertAtFirst(float value, float[] values){
        final float[] newValues = new float[values.length+1];
        newValues[0] = value;
        for (int i = 0; i < values.length; i++) {
            newValues[i+1] = values[i];
        }
        return newValues;
    }

    protected int[] insertAtFirst(int value, int[] values){
        final int[] newValues = new int[values.length+1];
        newValues[0] = value;
        for (int i = 0; i < values.length; i++) {
            newValues[i+1] = values[i];
        }
        return newValues;
    }

    public ValueAnimator alpha(@FloatRange(from = 0.0f, to = 1.0f) final float... alpha) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(alpha);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                shape.setAlpha((float) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator alphaTo(final float... values) {
        return alpha(insertAtFirst(shape.getAlpha(), values));
    }

    public ValueAnimator alphaBy(final float... values) {
        final float[] newValues = new float[values.length];
        final float alpha = shape.getAlpha();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * alpha;
        }
        return alpha(newValues);
    }

    public ValueAnimator color(final int... color) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(color);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                shape.setColor((int) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator colorTo(final int... values) {
        return color(insertAtFirst(shape.getColor(), values));
    }

    public ValueAnimator strokeWidthTo(final float... strokeWidth) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.getStrokeWidth(), strokeWidth));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                shape.setStrokeWidth((float) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator rotation(final float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                shape.setRotation((float) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator rotationTo(final float... values) {
        return rotation(insertAtFirst(shape.getRotation(), values));
    }

    public ValueAnimator rotationBy(final float... values) {
        final float[] newValues = new float[values.length];
        final float rotation = shape.getRotation();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * rotation;
        }
        return rotation(newValues);
    }

    @Deprecated
    public ValueAnimator scale(final float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                shape.setScale((float) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    @Deprecated
    public ValueAnimator scaleTo(final float... values) {
        return scale(insertAtFirst(shape.getScale(), values));
    }

    @Deprecated
    public ValueAnimator scaleBy(final float... values) {
        final float[] newValues = new float[values.length];
        final float scale = shape.getScale();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * scale;
        }
        return scale(newValues);
    }
}
