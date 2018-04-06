package com.github.florent37.mylittlecanvas.shape;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;

import com.github.florent37.mylittlecanvas.values.Alignment;

public class TextShape extends RectShape {

    private CharSequence text = "";
    @Nullable
    private StaticLayout staticLayout;

    private Alignment.VERTICAL verticalAlignment = Alignment.VERTICAL.CENTER;
    private Alignment.HORIZONTAL horizontalAlignment = Alignment.HORIZONTAL.CENTER;

    public TextShape() {
    }

    @Override
    public TextShape setColor(@ColorInt int color) {
        super.setColor(color);
        this.paint.setColor(color);
        update();
        return this;
    }

    public TextShape setVariable(String key, Object value) {
        return (TextShape) super.setVariable(key, value);
    }

    private Layout.Alignment toAlignment(Alignment.HORIZONTAL align) {
        switch (align) {
            case LEFT:
                return Layout.Alignment.ALIGN_NORMAL;
            case RIGHT:
                return Layout.Alignment.ALIGN_OPPOSITE;
            case CENTER:
            default:
                return Layout.Alignment.ALIGN_CENTER;
        }
    }

    @Override
    protected void update() {
        super.update();
        staticLayout = new StaticLayout(text, paint, (int) getWidth(), toAlignment(horizontalAlignment), 1.0f, 0, false);
    }

    public TextShape setTextSizePx(float textSize) {
        paint.setTextSize(textSize);
        update();
        return this;
    }

    public float getTextSize() {
        return paint.getTextSize();
    }

    public float computeTextHeight() {
        Rect rect = new Rect();
        paint.getTextBounds(text.toString(), 0, text.length(), rect);
        return rect.height();
    }

    public TextShape setTypeface(@NonNull Typeface typeface) {
        paint.setTypeface(typeface);
        update();
        return this;
    }

    @Override
    protected void draw(Canvas canvas) {
        if (staticLayout == null) {
            return;
        }
        final int saveState = canvas.save();
        final float textHeight = calculateHeight();
        if (verticalAlignment == Alignment.VERTICAL.CENTER) {
            canvas.translate(getLeft(), getCenterY() - textHeight / 2f);
        } else if (verticalAlignment == Alignment.VERTICAL.TOP) {
            canvas.translate(getLeft(), getTop());
        } else if (verticalAlignment == Alignment.VERTICAL.BOTTOM) {
            canvas.translate(getLeft(), getBottom() - textHeight);
        }

        staticLayout.draw(canvas);

        canvas.restoreToCount(saveState);
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return false;
    }

    public TextShape setHorizontalAlignment(final Alignment.HORIZONTAL alignment) {
        this.horizontalAlignment = alignment;
        update();
        return this;
    }

    public CharSequence getText() {
        return text;
    }

    public TextShape setText(` text) {
        this.text = text;
        update();
        return this;
    }

    public float calculateHeight() {
        if (staticLayout == null || text == null || ("" + text).trim().isEmpty()) {
            return 0;
        } else {
            return staticLayout.getHeight();
        }
    }

    public TextShape setVerticalAlignment(final Alignment.VERTICAL verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        update();
        return this;
    }
}
