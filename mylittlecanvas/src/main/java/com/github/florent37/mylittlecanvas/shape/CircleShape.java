package com.github.florent37.mylittlecanvas.shape;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.PointF;

public class CircleShape extends Shape {

    private int radius;
    private int centerX;
    private int centerY;

    private int borderColor;
    private float borderWidth;

    public CircleShape setRadius(final int radius) {
        this.radius = radius;
        return this;
    }

    public CircleShape setRadius(final float radius) {
        this.radius = (int) radius;
        return this;
    }

    public CircleShape setCenterX(final int centerX) {
        this.centerX = (int) limitX(centerX);
        return this;
    }

    public CircleShape setCenterY(final int centerY) {
        this.centerY = (int) limitY(centerY);
        return this;
    }

    public CircleShape setCenterX(final float centerX) {
        this.centerX = (int) limitX(centerX);
        return this;
    }

    public CircleShape setCenterY(final float centerY) {
        this.centerY = (int) limitY(centerY);
        return this;
    }

    public CircleShape centerVertical(final float parentHeight){
        this.centerY = (int) (parentHeight / 2f);
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

    public ValueAnimator animateCenterX(float...values){
        return ObjectAnimator.ofFloat(this, "centerX", values);
    }

    public ValueAnimator animateCenterY(float...values){
        return ObjectAnimator.ofFloat(this, "centerY", values);
    }

    public ValueAnimator animateRadius(float...values){
        return ObjectAnimator.ofFloat(this, "radius", values);
    }

    public CircleShape setBorderColor(final int borderColor) {
        this.borderColor = borderColor;
        return this;
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

    public int getRadius() {
        return radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }


    @Override
    public boolean containsTouch(float x, float y) {
        return Math.pow(x-centerX, 2) + Math.pow(y - centerY, 2) < Math.pow(radius, 2);
    }

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

    public CircleShape setMinX(int minX) {
        return (CircleShape) super.setMinX(minX);
    }

    public CircleShape setMaxX(int maxX) {
        return (CircleShape) super.setMaxX(maxX);
    }

    public CircleShape setMinY(int minY) {
        return (CircleShape) super.setMinY(minY);
    }

    public CircleShape setMaxY(int maxY) {
        return (CircleShape) super.setMaxY(maxY);
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
