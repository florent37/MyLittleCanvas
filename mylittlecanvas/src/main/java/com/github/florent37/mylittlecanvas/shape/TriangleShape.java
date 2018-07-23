package com.github.florent37.mylittlecanvas.shape;

import android.graphics.PointF;
import android.support.annotation.ColorInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TriangleShape extends PathShape {

    private final PointF point0 = new PointF(0f, 0f);
    private final PointF point1 = new PointF(0f, 0f);
    private final PointF point2 = new PointF(0f, 0f);
    private final List<PointF> points = Arrays.asList(point0, point1, point2);

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

    /*
    public float getTop() {
        float top = point0.y;
        for (PointF point : points) {
            top = Math.min(top, point.y);
        }
        return top;
    }

    public float getBottom() {
        float bottom = point0.y;
        for (PointF point : points) {
            bottom = Math.max(bottom, point.y);
        }
        return bottom;
    }

    public float getLeft() {
        float left = point0.x;
        for (PointF point : points) {
            left = Math.min(left, point.x);
        }
        return left;
    }

    @Override
    public float getRight() {
        float right = point0.x;
        for (PointF point : points) {
            right = Math.min(right, point.x);
        }
        return right;
    }
    */

    public TriangleShape moveXBy(float differenceX) {
        for (PointF point : points) {
            point.x += differenceX;
        }
        return this;
    }

    public TriangleShape moveCenterXTo(float newCenterX) {
        return this.moveXBy(newCenterX - getCenterX());
    }

    public TriangleShape moveCenterYTo(float newCenterY){
        return this.moveYBy(newCenterY - getCenterY());
    }

    public TriangleShape moveYBy(float differenceY) {
        for (PointF point : points) {
            point.y += differenceY;
        }
        return this;
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
