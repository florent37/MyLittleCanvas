package com.github.florent37.canvastoolbox.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;

public class PathShape extends Shape {
    private Path path;
    private RectF pathBounds;

    public Path getPath() {
        return path;
    }

    public PathShape setPath(final Path path) {
        this.path = path;
        path.computeBounds(pathBounds, false);
        return this;
    }

    @Override
    protected void draw(final Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public int getCenterX() {
        return (int) pathBounds.centerX();
    }

    @Override
    public int getCenterY() {
        return (int) pathBounds.centerY();
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return false;
    }
}
