package com.github.florent37.mylittlecanvas.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;

public class PathShape extends Shape {
    protected Path path = new Path();
    private RectF pathBounds = new RectF();

    public Path getPath() {
        return path;
    }

    public PathShape setPath(final Path path) {
        this.path.set(path);
        return this;
    }

    @Override
    protected void update() {
        super.update();
        path.computeBounds(pathBounds, false);
    }

    @Override
    public float getHeight() {
        return pathBounds.height();
    }

    @Override
    public float getWidth() {
        return pathBounds.width();
    }

    @Override
    protected void draw(final Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public float getCenterX() {
        return (int) pathBounds.centerX();
    }

    @Override
    public float getCenterY() {
        return (int) pathBounds.centerY();
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return false;
    }
}
