package com.github.florent37.mylittlecanvas.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.github.florent37.mylittlecanvas.animation.ArcShapeAnimation;
import com.github.florent37.mylittlecanvas.animation.RectShapeAnimation;

public class ArcShape extends RectShape {

    private float startAngle;
    private float endAngle;

    private float addedAngle = 0;

    public ArcShape setRadius(float radius) {
        final float centerX = getCenterX();
        final float centerY = getCenterY();
        setLeft(centerX - radius);
        setRight(centerX + radius);
        setTop(centerY - radius);
        setBottom(centerY + radius);
        return this;
    }

    public ArcShape setAddedAngle(float addedAngle) {
        this.addedAngle = addedAngle;
        return this;
    }

    public float getAddedAngle() {
        return addedAngle;
    }

    public ArcShape setCenterX(float centerX) {
        return (ArcShape) super.moveCenterXTo(centerX);
    }

    public ArcShape setCenterY(float centerY) {
        return (ArcShape) super.moveCenterYTo(centerY);
    }

    public float getStartAngle() {
        return startAngle;
    }

    public ArcShape setStartAngle(float startAngle) {
        this.startAngle = startAngle;
        update();
        return this;
    }

    public float getEndAngle() {
        return endAngle;
    }

    public ArcShape setEndAngle(float endAngle) {
        this.endAngle = endAngle;
        update();
        return this;
    }

    @Override
    protected void update() {
        super.update();

        path.reset();
        path.addArc(new RectF(0, 0, getWidth(), getHeight()), startAngle + addedAngle, endAngle + addedAngle);
    }

    @NonNull
    @Override
    public ArcShapeAnimation animate() {
        if (!(shapeShapeAnimation instanceof ArcShapeAnimation)) {
            shapeShapeAnimation = new ArcShapeAnimation(this);
        }
        return (ArcShapeAnimation) shapeShapeAnimation;
    }

    protected void draw(Canvas canvas) {
        final int save = canvas.save();

        canvas.translate(rectF.left, rectF.top);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(super.path, paint);

        canvas.restoreToCount(save);
    }

    @Override
    public ArcShape setColor(int color) {
        return (ArcShape) super.setColor(color);
    }

    @Override
    public ArcShape setStrokeWidth(float width) {
        return (ArcShape) super.setStrokeWidth(width);
    }
}
