package com.github.florent37.mylittlecanvas;

import android.content.Context;
import android.view.View;

import static android.view.View.LAYER_TYPE_HARDWARE;
import static android.view.View.LAYER_TYPE_SOFTWARE;

public class CanvasHelper {
    public static float dpToPx(Context context, int dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(View view, int size) {
        return dpToPx(view.getContext(), size);
    }

    public static float pxToDp(Context context, int size) {
        return size / context.getResources().getDisplayMetrics().density;
    }

    public static void enableShadow(View view){
        view.setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    public static void disableShadow(View view){
        view.setLayerType(LAYER_TYPE_HARDWARE, null);
    }
}
