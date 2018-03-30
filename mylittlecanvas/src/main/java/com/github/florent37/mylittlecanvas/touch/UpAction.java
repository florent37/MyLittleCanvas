package com.github.florent37.mylittlecanvas.touch;

import android.view.MotionEvent;

import com.github.florent37.mylittlecanvas.TouchHelper;

public interface UpAction {
    void onUp(TouchHelper touchHelper, MotionEvent event);
}
