package com.github.florent37.mylittlecanvas.shape;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public abstract class RectShape extends Shape {

    protected final RectF rectF = new RectF();
    protected final Path path = new Path();

    @Override
    protected void draw(Canvas canvas) {
        final int save = canvas.save();

        final PointF rotationPivot = getRotationPivot();
        if (getRotation() != 0) {
            canvas.rotate(getRotation(), rotationPivot.x, rotationPivot.y);
        }
        canvas.translate(rectF.left, rectF.top);

        canvas.drawRect(0, 0, rectF.right, rectF.bottom, paint);

        canvas.restoreToCount(save);
    }

    protected void update() {

    }

    public RectF getRectF() {
        return new RectF(rectF);
    }

    public RectShape setRect(RectF rectF) {
        return this.setRect(
                rectF.left,
                rectF.top,
                rectF.right,
                rectF.bottom
        );
    }

    public RectShape copyPosition(RectShape other) {
        return this.setRect(other.getRectF());
    }

    public RectShape setRect(float left, float top, float right, float bottom) {
        this.rectF.set(
                Math.max(left, minX),
                Math.max(top, minY),
                Math.min(right, maxX),
                Math.min(bottom, maxY));
        update();
        return this;
    }

    public float getTop() {
        return rectF.top;
    }

    public RectShape setTop(float top) {
        rectF.top = Math.max(top, minY);
        update();
        return this;
    }

    public float getBottom() {
        return rectF.bottom;
    }

    public RectShape setBottom(float bottom) {
        rectF.bottom = Math.min(bottom, maxY);
        update();
        return this;
    }

    public float getLeft() {
        return rectF.left;
    }

    public RectShape setLeft(float left) {
        rectF.left = Math.max(left, minX);
        update();
        return this;
    }

    public float getRight() {
        return rectF.right;
    }

    public RectShape setRight(float right) {
        rectF.right = Math.min(right, maxX);
        update();
        return this;
    }

    public RectShape marginTop(float margin) {
        return this.setTop(getTop() + margin);
    }

    public RectShape marginLeft(float margin) {
        return this.setLeft(getLeft() + margin);
    }

    public RectShape marginRight(float margin) {
        return this.setRight(getRight() + margin);
    }

    public RectShape marginBottom(float margin) {
        return this.setBottom(getBottom() + margin);
    }

    public RectShape below(RectShape other) {
        setTop(other.getBottom());
        return this;
    }

    public RectShape above(RectShape other) {
        setBottom(other.getTop());
        return this;
    }

    public ValueAnimator animateLeft(float... newLeft) {
        return ObjectAnimator.ofFloat(this, "left", newLeft);
    }

    public ValueAnimator animateRight(float... newRight) {
        return ObjectAnimator.ofFloat(this, "right", newRight);
    }

    public ValueAnimator animateTop(float... newTop) {
        return ObjectAnimator.ofFloat(this, "top", newTop);
    }

    public ValueAnimator animateBottom(float... newBottom) {
        return ObjectAnimator.ofFloat(this, "bottom", newBottom);
    }

    @Override
    public int getCenterX() {
        return (int) rectF.centerX();
    }

    @Override
    public int getCenterY() {
        return (int) rectF.centerY();
    }

    public float getWidth() {
        return Math.max(0, getRight() - getLeft());
    }

    public RectShape setWidth(float width) {
        setRight(getLeft() + width);
        return this;
    }

    public float getHeight() {
        return Math.max(0, getBottom() - getTop());
    }

    public RectShape setHeight(float height) {
        setBottom(getTop() + height);
        return this;
    }

    public RectShape centerHorizontal(int parentWidth) {
        final float width = getWidth();
        final float left = parentWidth / 2f - width / 2f;
        setLeft(left);
        setRight(left + width);
        return this;
    }

    public void centerVertical(int parentHeight) {
        final float height = getHeight();
        final float top = parentHeight / 2f - height / 2f;
        setTop(top);
        setBottom(top + height);
    }

    public RectShape alignTop(float top) {
        final float height = getHeight();
        setTop(top);
        setBottom(getTop() + height);
        return this;
    }

    public RectShape alignTop(RectShape other) {
        alignTop(other.getTop());
        return this;
    }

    public RectShape alignBottom(float bottom) {
        final float height = getHeight();
        setBottom(bottom);
        setTop(getBottom() - height);
        return this;
    }

    public RectShape alignBottom(RectShape other) {
        alignBottom(other.getBottom());
        return this;
    }

    public RectShape moveXBy(float differenceX) {
        final float width = getWidth();
        final float oldLeft = getLeft();
        final float oldRight = getRight();

        float newLeft;
        float newRight;

        if (oldLeft + differenceX < minX) {
            newLeft = minX;
            newRight = minX + width;
        } else if (oldRight + differenceX > maxX) {
            newLeft = maxX - width;
            newRight = maxX;
        } else {
            newLeft = oldLeft + differenceX;
            newRight = oldRight + differenceX;
        }

        setLeft(newLeft);
        setRight(newRight);
        return this;
    }

    public RectShape moveYBy(float differenceY) {
        final float height = getHeight();
        float oldTop = getTop();
        float oldBottom = getBottom();

        float newTop;
        float newBottom;

        if (oldTop + differenceY < minY) {
            newTop = minY;
            newBottom = minY + height;
        } else if (oldBottom + differenceY > maxY) {
            newTop = maxY - height;
            newBottom = maxY;
        } else {
            newTop = oldTop + differenceY;
            newBottom = oldBottom + differenceY;
        }

        setTop(newTop);
        setBottom(newBottom);
        return this;
    }

    public RectShape moveBy(float differenceX, float differenceY) {
        moveXBy(differenceX);
        moveYBy(differenceY);
        return this;
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return rectF.contains(x, y);
    }

    public RectShape setMinX(int minX) {
        return (RectShape) super.setMinX(minX);
    }

    public RectShape setMaxX(int maxX) {
        return (RectShape) super.setMaxX(maxX);
    }

    public RectShape setMinY(int minY) {
        return (RectShape) super.setMinY(minY);
    }

    public RectShape setMaxY(int maxY) {
        return (RectShape) super.setMaxY(maxY);
    }

    public RectShape setMinX(float minX) {
        return (RectShape) super.setMinX(minX);
    }

    public RectShape setMaxX(float maxX) {
        return (RectShape) super.setMaxX(maxX);
    }

    public RectShape setMinY(float minY) {
        return (RectShape) super.setMinY(minY);
    }

    public RectShape setMaxY(float maxY) {
        return (RectShape) super.setMaxY(maxY);
    }
}
