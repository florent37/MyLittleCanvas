package com.github.florent37.mylittlecanvas.shape;

import android.graphics.Canvas;
import android.graphics.PathEffect;
import android.support.annotation.ColorInt;

public class LineShape extends Shape {

    private float startX;
    private float startY;
    private float endX;
    private float endY;

    public LineShape setStrokeWidth(int strokeWith){
        paint.setStrokeWidth(strokeWith);
        return this;
    }

    public LineShape setStartX(final float startX) {
        this.startX = startX;
        return this;
    }

    public LineShape setStartY(final float startY) {
        this.startY = startY;
        return this;
    }

    public LineShape setEndX(final float endX) {
        this.endX = endX;
        return this;
    }

    public LineShape setEndY(final float endY) {
        this.endY = endY;
        return this;
    }

    @Override
    protected void draw(final Canvas canvas) {
        canvas.drawLine(startX, startY, endX, endY, paint);
    }

    @Override
    public float getCenterX() {
        return ((startX + endX) / 2f);
    }

    @Override
    public float getCenterY() {
        return ((endX + endY) / 2f);
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return false;
    }

    @Override
    public float getLeft() {
        return Math.min(startX, endX);
    }

    @Override
    public float getTop() {
        return Math.min(startY, endY);
    }

    @Override
    public float getBottom() {
        return Math.max(startY, endY);
    }

    @Override
    public float getRight() {
        return Math.max(startX, endX);
    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public float getEndX() {
        return endX;
    }

    public float getEndY() {
        return endY;
    }

    public LineShape setVariable(String key, Object value) {
        return (LineShape) super.setVariable(key, value);
    }

    @Override
    public float getHeight() {
        return Math.max(startY, endY) - Math.min(startY, endY);
    }

    @Override
    public float getWidth() {
        return Math.max(startX, endX) - Math.min(startX, endX);
    }

    public LineShape start(float x, float y) {
        setStartX(x);
        setStartY(y);
        return this;
    }

    public LineShape end(float x, float y) {
        setEndX(x);
        setEndY(y);
        return this;
    }

    public LineShape setColor(@ColorInt int color) {
        return (LineShape) super.setColor(color);
    }

    public LineShape shadow(float shadowRadius, float shadowDx, float shadowDy, @ColorInt int shadowColor) {
        return (LineShape) super.shadow(shadowRadius, shadowDx, shadowDy, shadowColor);
    }

    public LineShape setShadowRadius(float shadowRadius) {
        return (LineShape) super.setShadowRadius(shadowRadius);
    }

    public LineShape setShadowDx(float shadowDx) {
        return (LineShape) super.setShadowDx(shadowDx);
    }

    public LineShape setShadowDy(float shadowDy) {
        return (LineShape) super.setShadowDy(shadowDy);
    }

    public LineShape setShadowColor(int shadowColor) {
        return (LineShape) super.setShadowColor(shadowColor);
    }

    public LineShape setPathEffect(final PathEffect pathEffect){
        return (LineShape) super.setPathEffect(pathEffect);
    }

}
