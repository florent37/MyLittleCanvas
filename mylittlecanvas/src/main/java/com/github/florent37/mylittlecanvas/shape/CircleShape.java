package com.github.florent37.mylittlecanvas.shape;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.github.florent37.mylittlecanvas.animation.CircleShapeAnimation;
import com.github.florent37.mylittlecanvas.animation.ShapeAnimation;

public class CircleShape extends Shape {

    private float radius;
    private float centerX;
    private float centerY;

    @ColorInt
    private int borderColor;
    private float borderWidth;

    private CircleShapeAnimation circleShapeAnimation;

    public CircleShape centerVertical(final float parentHeight) {
        this.centerY = (parentHeight / 2f);
        return this;
    }

    public CircleShape centerHorizontal(final float parentWidth) {
        this.centerX = (parentWidth / 2f);
        return this;
    }

    @NonNull
    public CircleShapeAnimation animate(){
        if (!(shapeShapeAnimation instanceof CircleShapeAnimation)) {
            shapeShapeAnimation = new CircleShapeAnimation(this);
        }
        return (CircleShapeAnimation) shapeShapeAnimation;
    }

    @Override
    public float getLeft() {
        return centerX - radius;
    }

    @Override
    public float getTop() {
        return centerY - radius;
    }

    @Override
    public float getBottom() {
        return centerY + radius;
    }

    @Override
    public float getRight() {
        return centerX + radius;
    }

    private float limitX(float value) {
        float left = value - getRadius();
        float right = value + getRadius();

        if (left < minX) {
            value = minX + getRadius();
        }
        if (right > maxX) {
            value = maxX - getRadius();
        }
        return value;
    }

    private float limitY(float value) {
        float top = value - getRadius();
        float bottom = value + getRadius();
        if (top < minY) {
            value = minY + getRadius();
        }
        if (bottom > maxY) {
            value = maxY - getRadius();
        }
        return value;
    }

    public CircleShape setVariable(String key, Object value) {
        return (CircleShape) super.setVariable(key, value);
    }

    @Override
    public float getHeight() {
        return radius * 2f;
    }

    @Override
    public float getWidth() {
        return radius * 2f;
    }

    @Override
    protected void draw(final Canvas canvas) {
        if (borderWidth > 0) {
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

    public CircleShape setRadius(final float radius) {
        this.radius = (int) radius;
        return this;
    }

    @Override
    public float getCenterX() {
        return centerX;
    }

    public CircleShape setCenterX(final float centerX) {
        this.centerX = limitX(centerX);
        return this;
    }

    @Override
    public float getCenterY() {
        return centerY;
    }

    public CircleShape setCenterY(final float centerY) {
        this.centerY = limitY(centerY);
        return this;
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) < Math.pow(radius, 2);
    }

    @ColorInt
    public int getBorderColor() {
        return borderColor;
    }

    public CircleShape setBorderColor(@ColorInt final int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public CircleShape setBorderWidth(final float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
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

    public enum Pos {
        CENTER_X,
        CENTER_Y
    }

}
