package com.github.florent37.mylittlecanvas.shape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class DrawableShape extends RectShape {

    private Drawable drawable;

    public DrawableShape setDrawable(final Drawable drawable) {
        this.drawable = drawable;
        return this;
    }

    public DrawableShape setBitmap(final Bitmap bitmap) {
        this.drawable = new BitmapDrawable(bitmap);
        return this;
    }

    @Override
    protected void draw(final Canvas canvas) {
        drawable.setBounds(
                (int) rectF.left,
                (int) rectF.top,
                (int) rectF.right,
                (int) rectF.bottom
        );
        drawable.draw(canvas);
    }
}
