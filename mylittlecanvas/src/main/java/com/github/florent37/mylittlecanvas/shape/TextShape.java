package com.github.florent37.mylittlecanvas.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

public class TextShape extends RectShape {

    private CharSequence text = "";
    @Nullable
    private StaticLayout staticLayout;

    private boolean centerVertical = false;

    private Layout.Alignment alignment = Layout.Alignment.ALIGN_CENTER;

    public TextShape() {
    }

    @Override
    public TextShape setColor(@ColorInt int color) {
        super.setColor(color);
        this.paint.setColor(color);
        update();
        return this;
    }

    @Override
    protected void update() {
        super.update();
        staticLayout = new StaticLayout(text, paint, (int) getWidth(), alignment, 1.0f, 0, false);
    }

    public void setTextSizePx(float textSize) {
        paint.setTextSize(textSize);
        update();
    }

    public void setTypeface(Typeface typeface) {
        paint.setTypeface(typeface);
        update();
    }

    @Override
    protected void draw(Canvas canvas) {
        if(staticLayout == null){
            return;
        }
        final int saveState = canvas.save();
        if(centerVertical){
            final float height = calculateHeight();
            canvas.translate(getLeft(), getCenterY() - height / 2f);
        } else {
            canvas.translate(getLeft(), getTop());
        }

        staticLayout.draw(canvas);

        canvas.restoreToCount(saveState);
    }

    @Override
    public boolean containsTouch(float x, float y) {
        return false;
    }

    public TextShape setAlignment(Layout.Alignment alignment) {
        this.alignment = alignment;
        update();
        return this;
    }

    public CharSequence getText() {
        return text;
    }

    public TextShape setText(CharSequence text) {
        this.text = text;
        update();
        return this;
    }

    public float calculateHeight() {
        if (text == null || ("" + text).trim().isEmpty()) {
            return 0;
        } else {
            final StaticLayout sl = new StaticLayout(text, paint, (int) getWidth(), alignment, 1, 1, false);
            return sl.getHeight();
        }
    }

    public TextShape setCenterVertical(boolean centerVertical) {
        this.centerVertical = centerVertical;
        update();
        return this;
    }
}
