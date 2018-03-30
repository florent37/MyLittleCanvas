package com.github.florent37.mylittlecanvas.touch;

import android.view.MotionEvent;

import com.github.florent37.mylittlecanvas.TouchHelper;

public interface DownAction {
    void onDown(TouchHelper touchHelper, MotionEvent event);
}
