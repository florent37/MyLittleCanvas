package com.github.florent37.canvastoolbox;

import android.content.Context;

public class CanvasHelper {
    public static float dpToPx(Context context, int size) {
        return size * context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(Context context, float size) {
        return size * context.getResources().getDisplayMetrics().density;
    }

    public static float pxToDp(Context context, int size) {
        return size / context.getResources().getDisplayMetrics().density;
    }

}
