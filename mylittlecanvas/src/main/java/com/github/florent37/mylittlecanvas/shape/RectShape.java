package com.github.florent37.mylittlecanvas.shape;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.ColorInt;

import com.github.florent37.mylittlecanvas.RoundRect;

public class RectShape extends PathShape {

    protected final RectF rectF = new RectF();
    private float cornerRadius = 0;
    private boolean drawAngleTopLeft = true;
    private boolean drawAngleTopRight = true;
    private boolean drawAngleBottomLeft = true;
    private boolean drawAngleBottomRight = true;
    private float borderWidth = 0;
    @ColorInt
    private int borderColor = Color.BLACK;

    @Override
    protected void draw(Canvas canvas) {
        final int save = canvas.save();

        final PointF rotationPivot = getRotationPivot();
        if (getRotation() != 0) {
            canvas.rotate(getRotation(), rotationPivot.x, rotationPivot.y);
        }
        canvas.translate(rectF.left, rectF.top);

        canvas.drawPath(super.path, paint);

        if (borderWidth != 0) {
            final Paint.Style oldStyle = paint.getStyle();
            final float strokeWidth = paint.getStrokeWidth();
            final int paintColor = paint.getColor();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(borderColor);
            paint.setStrokeWidth(borderWidth);

            canvas.drawPath(super.path, paint);

            paint.setColor(paintColor);
            paint.setStrokeWidth(strokeWidth);
            paint.setStyle(oldStyle);
        }

        canvas.restoreToCount(save);
    }

    protected void update() {
        super.update();
        if (cornerRadius == 0) {
            path.reset();
            path.moveTo(0, 0);
            path.addRect(0, 0, getWidth(), getHeight(), Path.Direction.CW);
            path.close();
        } else {
            path.reset();
            path.set(RoundRect.generatePath(
                    getWidth(), getHeight(),
                    drawAngleTopLeft ? cornerRadius : 0,
                    drawAngleTopRight ? cornerRadius : 0,
                    drawAngleBottomRight ? cornerRadius : 0,
                    drawAngleBottomLeft ? cornerRadius : 0
            ));
        }
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

    public RectShape setColor(@ColorInt int color) {
        return (RectShape) super.setColor(color);
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public RectShape setCornerRadius(float radiusAngle) {
        this.cornerRadius = radiusAngle;
        update();
        return this;
    }

    public boolean isDrawAngleTopLeft() {
        return drawAngleTopLeft;
    }

    public RectShape setDrawAngleTopLeft(boolean drawAngleTopLeft) {
        this.drawAngleTopLeft = drawAngleTopLeft;
        update();
        return this;
    }

    public boolean isDrawAngleTopRight() {
        return drawAngleTopRight;
    }

    public RectShape setDrawAngleTopRight(boolean drawAngleTopRight) {
        this.drawAngleTopRight = drawAngleTopRight;
        update();
        return this;
    }

    public boolean isDrawAngleBottomLeft() {
        return drawAngleBottomLeft;
    }

    public RectShape setDrawAngleBottomLeft(boolean drawAngleBottomLeft) {
        this.drawAngleBottomLeft = drawAngleBottomLeft;
        update();
        return this;
    }

    public boolean isDrawAngleBottomRight() {
        return drawAngleBottomRight;
    }

    public RectShape setDrawAngleBottomRight(boolean drawAngleBottomRight) {
        this.drawAngleBottomRight = drawAngleBottomRight;
        update();
        return this;
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

    public RectShape shadow(float shadowRadius, float shadowDx, float shadowDy, @ColorInt int shadowColor) {
        return (RectShape) super.shadow(shadowRadius, shadowDx, shadowDy, shadowColor);
    }

    public RectShape setShadowRadius(float shadowRadius) {
        return (RectShape) super.setShadowRadius(shadowRadius);
    }

    public RectShape setShadowDx(float shadowDx) {
        return (RectShape) super.setShadowDx(shadowDx);
    }

    public RectShape setShadowDy(float shadowDy) {
        return (RectShape) super.setShadowDy(shadowDy);
    }

    public RectShape setShadowColor(int shadowColor) {
        return (RectShape) super.setShadowColor(shadowColor);
    }

    public float getLeft() {
        return rectF.left;
    }

    public RectShape setLeft(float left) {
        rectF.left = Math.max(left, minX);
        update();
        return this;
    }

    public RectShape setVariable(String key, Object value) {
        return (RectShape) super.setVariable(key, value);
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

    public ValueAnimator animateLeft(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setLeft((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateLeftBy(float... values) {
        final float[] newValues = new float[values.length];
        final float left = getLeft();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * left;
        }
        return animateLeft(newValues);
    }

    public ValueAnimator animateLeftAdded(float... values) {
        final float[] newValues = new float[values.length];
        final float left = getLeft();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + left;
        }
        return animateLeft(newValues);
    }

    public ValueAnimator animateLeftTo(float finalValue) {
        return animateLeft(getLeft(), finalValue);
    }

    public ValueAnimator animateRight(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setRight((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateRightAdded(float... values) {
        final float[] newValues = new float[values.length];
        final float right = getRight();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + right;
        }
        return animateRight(newValues);
    }

    public ValueAnimator animateRightBy(float... values) {
        final float[] newValues = new float[values.length];
        final float right = getRight();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * right;
        }
        return animateRight(newValues);
    }

    public ValueAnimator animateRightTo(float finalValue) {
        return animateRight(getRight(), finalValue);
    }

    public ValueAnimator animateTop(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setTop((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateTopBy(float... values) {
        final float[] newValues = new float[values.length];
        final float top = getTop();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * top;
        }
        return animateTop(newValues);
    }

    public ValueAnimator animateTopAdded(float... values) {
        final float[] newValues = new float[values.length];
        final float top = getTop();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + top;
        }
        return animateTop(newValues);
    }

    public ValueAnimator animateTopTo(float finalValue) {
        return animateTop(getTop(), finalValue);
    }

    public ValueAnimator animateBottom(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setBottom((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator animateBottomTo(float finalValue) {
        return animateBottom(getBottom(), finalValue);
    }

    public ValueAnimator animateBottomBy(float... values) {
        final float[] newValues = new float[values.length];
        final float bottom = getBorderColor();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * bottom;
        }
        return animateBottom(newValues);
    }

    public ValueAnimator animateBottomAdded(float... values) {
        final float[] newValues = new float[values.length];
        final float bottom = getBorderColor();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + bottom;
        }
        return animateBottom(newValues);
    }

    @Override
    public float getCenterX() {
        return rectF.centerX();
    }

    @Override
    public float getCenterY() {
        return rectF.centerY();
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

    public RectShape centerHorizontal(float parentWidth) {
        final float width = getWidth();
        final float left = parentWidth / 2f - width / 2f;
        setLeft(left);
        setRight(left + width);
        return this;
    }

    public void centerVertical(float parentHeight) {
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

    public RectShape moveCenterXTo(float newCenterX) {
        return this.moveXBy(newCenterX - getCenterX());
    }

    public RectShape moveCenterYTo(float newCenterY){
        return this.moveXBy(newCenterY - getCenterY());
    }

    public RectShape moveLeftTo(float newLeft){
        return this.moveXBy(newLeft - getLeft());
    }

    public RectShape moveRightTo(float newRight){
        return this.moveXBy(newRight - getRight());
    }

    public RectShape moveTopTo(float newTop){
        return this.moveYBy(newTop - getTop());
    }

    public RectShape moveBottomTo(float newBottom){
        return this.moveYBy(newBottom - getBottom());
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

    public float getBorderWidth() {
        return borderWidth;
    }

    public RectShape setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        update();
        return this;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public RectShape setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        update();
        return this;
    }

    public enum Pos {
        CENTER_X,
        CENTER_Y,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }
}
