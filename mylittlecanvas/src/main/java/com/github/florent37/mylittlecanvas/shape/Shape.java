package com.github.florent37.mylittlecanvas.shape;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.text.TextPaint;

public abstract class Shape {

    protected final TextPaint paint = new TextPaint();

    private boolean willNotDraw = false;

    private float rotation = 0;

    public Shape() {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
        paint.setTypeface(Typeface.DEFAULT);
    }

    public Shape setStyle(Paint.Style style) {
        paint.setStyle(style);
        return this;
    }

    public TextPaint getPaint() {
        return paint;
    }

    public Shape setColor(@ColorInt int color) {
        paint.setColor(color);
        update();
        return this;
    }

    public Shape setRotation(float rotation) {
        this.rotation = rotation;
        update();
        return this;
    }

    public float getStrokeWidth() {
        return paint.getStrokeWidth();
    }

    public float getRotation() {
        return rotation;
    }

    public Shape setStrokeWidth(float width) {
        paint.setStrokeWidth(width);
        update();
        return this;
    }

    protected abstract void draw(Canvas canvas);

    public void onDraw(Canvas canvas) {
        if (!willNotDraw) {
            canvas.save();
            canvas.rotate(rotation);
            draw(canvas);
            canvas.restore();
        }
    }

    protected void update() {
    }

    public abstract int getCenterX();

    public abstract int getCenterY();

    public boolean isWillNotDraw() {
        return willNotDraw;
    }

    public Shape setWillNotDraw(boolean willNotDraw) {
        this.willNotDraw = willNotDraw;
        return this;
    }

    public abstract boolean containsTouch(float x, float y);

    public float getAlpha() {
        return paint.getAlpha() / 255f;
    }

    public Shape setAlpha(@FloatRange(from = 0.0f, to = 1.0f) final float alpha) {
        paint.setAlpha((int) (255 * alpha));
        update();
        return this;
    }

    public ValueAnimator animateAlpha(@FloatRange(from = 0.0f, to = 1.0f) final float... alpha) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(alpha);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setAlpha((float) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateColor(final int... color) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(color);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setColor((int) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateStrokeWidth(final float... strokeWidth) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(strokeWidth);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setStrokeWidth((float) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }
}
