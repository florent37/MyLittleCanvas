package com.github.florent37.mylittlecanvas.shape;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;

import java.util.HashMap;
import java.util.Map;

public abstract class Shape {

    protected final TextPaint paint = new TextPaint();

    private boolean willNotDraw = false;

    private float rotation = 0;
    protected PointF rotationPivot = null;

    private float scale = 1f;
    protected PointF scalePivot = null;

    private float shadowRadius = 0f;
    private float shadowDx = 0f;
    private float shadowDy = 0f;
    @ColorInt
    private int shadowColor = Color.BLACK;

    protected float minX = 0;
    protected float maxX = Float.MAX_VALUE;
    protected float minY = 0;
    protected float maxY = Float.MAX_VALUE;

    private Map<String, Object> tags = new HashMap<>();

    public Shape setVariable(String key, Object value){
        tags.put(key, value);
        return this;
    }

    public <T> T getVariable(String key){
        return (T) tags.get(key);
    }

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

    public abstract float getHeight();
    public abstract float getWidth();

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

    public Shape setScale(float scale) {
        this.scale = scale;
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

    public float getShadowRadius() {
        return shadowRadius;
    }

    public Shape setShadowRadius(float shadowRadius) {
        this.shadowRadius = shadowRadius;
        update();
        return this;
    }

    public float getShadowDx() {
        return shadowDx;
    }

    public Shape setShadowDx(float shadowDx) {
        this.shadowDx = shadowDx;
        update();
        return this;
    }

    public float getShadowDy() {
        return shadowDy;
    }

    public Shape setShadowDy(float shadowDy) {
        this.shadowDy = shadowDy;
        update();
        return this;
    }

    public int getShadowColor() {
        return shadowColor;
    }

    public Shape setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
        update();
        return this;
    }

    public Shape shadow(float shadowRadius, float shadowDx, float shadowDy, @ColorInt int shadowColor){
        this.shadowRadius = shadowRadius;
        this.shadowDx = shadowDx;
        this.shadowDy = shadowDy;
        this.shadowColor = shadowColor;
        update();
        return this;
    }

    public void onDraw(Canvas canvas) {
        if (!willNotDraw) {
            canvas.save();
            draw(canvas);
            canvas.restore();
        }
    }

    @CallSuper
    protected void update() {
        if(shadowRadius != 0) {
            paint.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
        } else {
            paint.clearShadowLayer();
        }
    }

    public abstract float getCenterX();

    public abstract float getCenterY();

    @NonNull
    public PointF getRotationPivot() {
        if(rotationPivot == null){
            return new PointF(getCenterX(), getCenterY());
        }
        return rotationPivot;
    }

    @NonNull
    public PointF getScalePivot() {
        if(scalePivot == null){
            return new PointF(getCenterX(), getCenterY());
        }
        return scalePivot;
    }

    public void setRotationPivot(float x, float y) {
        if(rotationPivot == null){
            this.rotationPivot = new PointF(x, y);
        } else {
            this.rotationPivot.set(x, y);
        }
    }

    public void setScalePivot(float x, float y) {
        if(scalePivot == null){
            this.scalePivot = new PointF(x, y);
        } else {
            this.scalePivot.set(x, y);
        }
    }

    public boolean isWillNotDraw() {
        return willNotDraw;
    }

    public Shape setWillNotDraw(boolean willNotDraw) {
        this.willNotDraw = willNotDraw;
        return this;
    }

    public abstract boolean containsTouch(float x, float y);

    public float getScale() {
        return scale;
    }

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

    public ValueAnimator animateAlphaBy(final float... values) {
        final float[] newValues = new float[values.length];
        final float alpha = getAlpha();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * alpha;
        }
        return animateAlpha(newValues);
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
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setStrokeWidth((float) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateRotation(final float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setRotation((float) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateRotationBy(final float... values) {
        final float[] newValues = new float[values.length];
        final float rotation = getRotation();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * rotation;
        }
        return animateRotation(newValues);
    }

    public ValueAnimator animateScale(final float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setScale((float) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateScaleBy(final float... values) {
        final float[] newValues = new float[values.length];
        final float scale = getScale();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * scale;
        }
        return animateAlpha(newValues);
    }

    public float getMinX() {
        return minX;
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMinY() {
        return minY;
    }

    public float getMaxY() {
        return maxY;
    }

    public Shape setMinX(float minX) {
        this.minX = minX;
        return this;
    }

    public Shape setMaxX(float maxX) {
        this.maxX = (int) maxX;
        return this;
    }

    public Shape setMinY(float minY) {
        this.minY = (int) minY;
        return this;
    }

    public Shape setMaxY(float maxY) {
        this.maxY = (int) maxY;
        return this;
    }
}
