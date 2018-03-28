package com.github.florent37.mylittlecanvas.shape;

import android.graphics.Canvas;

import com.github.florent37.mylittlecanvas.RoundRect;

public class RoundRectShape extends RectShape {

    private float cornerRadius = 10;

    private boolean drawAngleTopLeft = true;
    private boolean drawAngleTopRight = true;
    private boolean drawAngleBottomLeft = true;
    private boolean drawAngleBottomRight = true;

    @Override
    protected void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    protected void update(){
        super.path.set(RoundRect.generatePath(rectF,
                drawAngleTopLeft ? cornerRadius : 0,
                drawAngleTopRight ? cornerRadius : 0,
                drawAngleBottomRight ? cornerRadius : 0,
                drawAngleBottomLeft ? cornerRadius : 0
        ));
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public RoundRectShape setCorderRadius(float radiusAngle) {
        this.cornerRadius = radiusAngle;
        update();
        return this;
    }

    public boolean isDrawAngleTopLeft() {
        return drawAngleTopLeft;
    }

    public RoundRectShape setDrawAngleTopLeft(boolean drawAngleTopLeft) {
        this.drawAngleTopLeft = drawAngleTopLeft;
        update();
        return this;
    }

    public boolean isDrawAngleTopRight() {
        return drawAngleTopRight;
    }

    public RoundRectShape setDrawAngleTopRight(boolean drawAngleTopRight) {
        this.drawAngleTopRight = drawAngleTopRight;
        update();
        return this;
    }

    public boolean isDrawAngleBottomLeft() {
        return drawAngleBottomLeft;
    }

    public RoundRectShape setDrawAngleBottomLeft(boolean drawAngleBottomLeft) {
        this.drawAngleBottomLeft = drawAngleBottomLeft;
        update();
        return this;
    }

    public boolean isDrawAngleBottomRight() {
        return drawAngleBottomRight;
    }

    public RoundRectShape setDrawAngleBottomRight(boolean drawAngleBottomRight) {
        this.drawAngleBottomRight = drawAngleBottomRight;
        update();
        return this;
    }

    @Override
    public RoundRectShape setColor(int color) {
        return (RoundRectShape)super.setColor(color);
    }

    @Override
    public RoundRectShape setWidth(float width){
        return (RoundRectShape) super.setWidth(width);
    }

    @Override
    public RoundRectShape setHeight(float height){
        return (RoundRectShape) super.setHeight(height);
    }
}
