package com.github.florent37.mylittlecanvas.shape;

import android.graphics.Canvas;
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
    public int getCenterX() {
        return (int) ((startX + endX) / 2f);
    }

    @Override
    public int getCenterY() {
        return (int) ((endX + endY) / 2f);
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return false;
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
}
