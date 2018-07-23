package com.github.florent37.mylittlecanvas.shape;

import android.graphics.PointF;
import android.support.annotation.ColorInt;

public class TriangleShape extends PathShape {

    private final PointF point0 = new PointF(0f, 0f);
    private final PointF point1 = new PointF(0f, 0f);
    private final PointF point2 = new PointF(0f, 0f);

    @Override
    protected void update() {
        super.update();
        super.path.reset();
        super.path.moveTo(point0.x, point0.y);
        super.path.lineTo(point1.x, point1.y);
        super.path.lineTo(point2.x, point2.y);
        super.path.close();
    }

    public TriangleShape setPoint0(float x, float y) {
        point0.set(x, y);
        update();
        return this;
    }

    public TriangleShape setPoint1(float x, float y) {
        point1.set(x, y);
        update();
        return this;
    }

    public TriangleShape setPoint2(float x, float y) {
        point2.set(x, y);
        update();
        return this;
    }

    public PointF getPoint0() {
        return point0;
    }

    public PointF getPoint1() {
        return point1;
    }

    public PointF getPoint2() {
        return point2;
    }

    public TriangleShape shadow(float shadowRadius, float shadowDx, float shadowDy, @ColorInt int shadowColor) {
        return (TriangleShape) super.shadow(shadowRadius, shadowDx, shadowDy, shadowColor);
    }

    public TriangleShape setShadowRadius(float shadowRadius) {
        return (TriangleShape) super.setShadowRadius(shadowRadius);
    }

    public TriangleShape setShadowDx(float shadowDx) {
        return (TriangleShape) super.setShadowDx(shadowDx);
    }

    public TriangleShape setShadowDy(float shadowDy) {
        return (TriangleShape) super.setShadowDy(shadowDy);
    }

    public TriangleShape setShadowColor(int shadowColor) {
        return (TriangleShape) super.setShadowColor(shadowColor);
    }
}
