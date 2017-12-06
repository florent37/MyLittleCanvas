package com.github.florent37.mylittlecanvas.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

public class TextShape extends Shape {

    private CharSequence text = "";

    private float x;
    private float y;
    private float minXLeft;
    private float maxXRight;
    private Layout.Alignment alignment = Layout.Alignment.ALIGN_CENTER;

    public TextShape() {
    }

    @Override
    public TextShape setColor(@ColorInt int color) {
        super.setColor(color);
        this.paint.setColor(color);
        return this;
    }
    
    public void setTextSizePx(float textSize) {
        paint.setTextSize(textSize);
    }

    public void setTypeface(Typeface typeface) {
        paint.setTypeface(typeface);
    }

    @Override
    protected void draw(Canvas canvas) {
        final float lineHeight = paint.getTextSize();
        float lineY = y;
        final String text = this.text.toString();
        for (CharSequence line : text.split("\n")) {
            canvas.save();
            {
                final float lineWidth = (int) paint.measureText(line.toString());
                float lineX = x;
                //if (alignment == Layout.Alignment.ALIGN_CENTER) {
                //    lineX -= lineWidth / 2f;
                //}
                if (lineX < minXLeft) {
                    lineX = minXLeft;
                }

                final float right = lineX + lineWidth;
                if (right > maxXRight) {
                    lineX = maxXRight - lineWidth;
                }

                final float width = maxXRight - lineX;

                canvas.translate(lineX, lineY);
                final StaticLayout staticLayout = new StaticLayout(line, paint, (int) width, alignment, 1.0f, 0, false);
                staticLayout.draw(canvas);

                lineY += lineHeight;
            }
            canvas.restore();
        }
    }

    @Override
    public int getCenterX() {
        return (int) ((minXLeft + maxXRight) / 2f);
    }

    @Override
    public int getCenterY() {
        return (int) ((y + calculateHeight()) / 2f);
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return false;
    }

    public float getY() {
        return y;
    }

    public float getYMinusTextHeightBy2() {
        return calculateHeight() / 2f;
    }

    public void configure(float x, float y, float minXLeft, float maxXRight, Layout.Alignment alignment) {
        this.alignment = alignment;
        this.x = x;
        this.y = y;
        this.minXLeft = minXLeft;
        this.maxXRight = maxXRight;
    }

    public TextShape configureH(float minXLeft, float maxXRight) {
        this.minXLeft = minXLeft;
        this.maxXRight = maxXRight;
        return this;
    }

    public TextShape setAlignment(Layout.Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public TextShape centerVertical(float minY, float maxY) {
        this.y = minY + (maxY - minY) / 2f - (calculateHeight()) / 2f;
        return this;
    }

    public CharSequence getText() {
        return text;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

    public float calculateHeight() {
        if (text == null || ("" + text).trim().isEmpty()) {
            return 0;
        } else {
            final StaticLayout sl = new StaticLayout(text, paint, (int) (maxXRight - minXLeft), alignment, 1, 1, false);
            return sl.getHeight();
        }
    }

    public TextShape centerIn(RectShape rectShape) {
        this.configureH(rectShape.getLeft(), rectShape.getRight());
        this.centerVertical(rectShape.getTop(), rectShape.getBottom());
        return this;
    }
}
