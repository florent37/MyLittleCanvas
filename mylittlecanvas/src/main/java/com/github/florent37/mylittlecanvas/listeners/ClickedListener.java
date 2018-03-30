package com.github.florent37.mylittlecanvas.listeners;

import com.github.florent37.mylittlecanvas.shape.Shape;

public interface ClickedListener<S extends Shape> {
    void onClick(S shape);
}
