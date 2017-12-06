package com.github.florent37.mylittlecanvas.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;

public abstract class Shape {

    protected final Paint paint = new Paint();

    private boolean willNotDraw = false;

    public Shape() {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
    }

    public Shape setStyle(Paint.Style style){
        paint.setStyle(style);
        return this;
    }

    public Shape setStrokeWidth(int width){
        paint.setStrokeWidth(width);
        return this;
    }

    public Shape setColor(@ColorInt int color){
        paint.setColor(color);
        update();
        return this;
    }

    protected abstract void draw(Canvas canvas);

    public void onDraw(Canvas canvas) {
        if(!willNotDraw){
            draw(canvas);
        }
    }

    protected void update(){}

    public abstract int getCenterX();
    public abstract int getCenterY();

    public boolean isWillNotDraw() {
        return willNotDraw;
    }

    public void setWillNotDraw(boolean willNotDraw) {
        this.willNotDraw = willNotDraw;
    }

    public abstract boolean containsTouch(float x, float y);
}
