package com.github.florent37.mylittlecanvas.animation;

import android.animation.ValueAnimator;

import com.github.florent37.mylittlecanvas.shape.ArcShape;

public class ArcShapeAnimation extends RectShapeAnimation<ArcShape> {
    public ArcShapeAnimation(ArcShape shape) {
        super(shape);
    }

    //region startangle
    public ValueAnimator startAngle(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.setStartAngle((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator startAngleTo(final float... values) {
        return startAngle(insertAtFirst(shape.getStartAngle(), values));
    }

    public ValueAnimator startAngleBy(float... values) {
        final float[] newValues = new float[values.length];
        final float left = shape.getStartAngle();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * left;
        }
        return startAngle(newValues);
    }

    public ValueAnimator startAnglePlus(float... values) {
        final float[] newValues = new float[values.length];
        final float left = shape.getStartAngle();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + left;
        }
        return startAngleTo(newValues);
    }
    //endregion

    //region startangle
    public ValueAnimator endAngle(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.setEndAngle((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator endAngleTo(final float... values) {
        return endAngle(insertAtFirst(shape.getStartAngle(), values));
    }

    public ValueAnimator endAngleBy(float... values) {
        final float[] newValues = new float[values.length];
        final float left = shape.getEndAngle();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * left;
        }
        return endAngle(newValues);
    }

    public ValueAnimator endAnglePlus(float... values) {
        final float[] newValues = new float[values.length];
        final float left = shape.getEndAngle();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + left;
        }
        return endAngleTo(newValues);
    }
    //endregion
}
