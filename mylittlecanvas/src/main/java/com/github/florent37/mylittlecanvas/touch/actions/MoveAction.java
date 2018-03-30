package com.github.florent37.mylittlecanvas.touch.actions;

import android.view.MotionEvent;

import com.github.florent37.mylittlecanvas.shape.Shape;
import com.github.florent37.mylittlecanvas.touch.EventPos;

public abstract class MoveAction<S extends Shape, POS> {
    protected final S shape;
    protected final POS pos;
    private final EventPos event_pos;

    public MoveAction(EventPos event_pos, S shape, POS pos) {
        this.event_pos = event_pos;
        this.shape = shape;
        this.pos = pos;
    }

    public final void move(MotionEvent event) {
        switch (event_pos) {
            case X:
                move(event.getX());
                break;
            case Y:
                move(event.getY());
                break;
        }
    }

    protected abstract void move(float value);
}
