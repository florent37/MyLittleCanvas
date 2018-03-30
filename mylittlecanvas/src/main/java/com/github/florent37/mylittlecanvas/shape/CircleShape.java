package com.github.florent37.mylittlecanvas.shape;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;

public class CircleShape extends Shape {

    private float radius;
    private float centerX;
    private float centerY;

    @ColorInt
    private int borderColor;
    private float borderWidth;

    public CircleShape setRadius(final float radius) {
        this.radius = (int) radius;
        return this;
    }

    public CircleShape setCenterX(final float centerX) {
        this.centerX = limitX(centerX);
        return this;
    }

    public CircleShape setCenterY(final float centerY) {
        this.centerY = limitY(centerY);
        return this;
    }

    public CircleShape centerVertical(final float parentHeight){
        this.centerY = (parentHeight / 2f);
        return this;
    }

    public CircleShape centerHorizontal(final float parentWidth){
        this.centerX = (parentWidth / 2f);
        return this;
    }

    private float limitX(float value){
        float left = value - getRadius();
        float right = value + getRadius();

        if(left < minX){
            value = minX + getRadius();
        }
        if(right > maxX){
            value = maxX - getRadius();
        }
        return value;
    }

    private float limitY(float value){
        float top = value - getRadius();
        float bottom = value + getRadius();
        if(top < minY){
            value = minY + getRadius();
        }
        if(bottom > maxY){
            value = maxY - getRadius();
        }
        return value;
    }

    public CircleShape setVariable(String key, Object value) {
        return (CircleShape) super.setVariable(key, value);
    }

    public ValueAnimator animateCenterX(float...values){
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setCenterX((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateCenterXTo(float finalValue){
        return animateCenterX(getCenterX(), finalValue);
    }

    public ValueAnimator animateCenterXAdded(float...values){
        final float[] newValues = new float[values.length];
        final float centerX = getCenterX();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + centerX;
        }
        return animateCenterX(newValues);
    }

    public ValueAnimator animateCenterY(float...values){
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setCenterY((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateCenterYTo(float finalValue){
        return animateCenterY(getCenterY(), finalValue);
    }

    public ValueAnimator animateCenterYAdded(float...values){
        final float[] newValues = new float[values.length];
        final float centerY = getCenterY();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + centerY;
        }
        return animateCenterY(newValues);
    }

    public ValueAnimator animateRadius(float...values){
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setRadius((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateRadiusTo(float finalValue){
        return animateRadius(getRadius(), finalValue);
    }

    public ValueAnimator animateRadiusBy(float...values){
        final float[] newValues = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * radius;
        }
        return animateRadius(newValues);
    }

    public ValueAnimator animateRadiusAdded(float...values){
        final float[] newValues = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + radius;
        }
        return animateRadius(newValues);
    }

    public CircleShape setBorderColor(@ColorInt final int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public ValueAnimator animateBorderColor(final int... color) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(color);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setBorderColor((int) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateBorderWidth(final float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setBorderWidth((int) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }


    public CircleShape setBorderWidth(final float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    @Override
    protected void draw(final Canvas canvas) {
        if(borderWidth > 0) {
            int color = paint.getColor();
            paint.setColor(borderColor);
            canvas.drawCircle(centerX, centerY, radius, paint);
            paint.setColor(color);
        }
        canvas.drawCircle(centerX, centerY, radius - borderWidth, paint);
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public float getCenterX() {
        return centerX;
    }

    @Override
    public float getCenterY() {
        return centerY;
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return Math.pow(x-centerX, 2) + Math.pow(y - centerY, 2) < Math.pow(radius, 2);
    }

    @ColorInt
    public int getBorderColor() {
        return borderColor;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    @Override
    public CircleShape setColor(int color) {
        return (CircleShape) super.setColor(color);
    }

    public CircleShape setMinX(float minX) {
        return (CircleShape) super.setMinX(minX);
    }

    public CircleShape setMaxX(float maxX) {
        return (CircleShape) super.setMaxX(maxX);
    }

    public CircleShape setMinY(float minY) {
        return (CircleShape) super.setMinY(minY);
    }

    public CircleShape setMaxY(float maxY) {
        return (CircleShape) super.setMaxY(maxY);
    }
}
