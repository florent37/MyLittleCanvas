package com.github.florent37.mylittlecanvas.touch.actions;

import com.github.florent37.mylittlecanvas.shape.CircleShape;
import com.github.florent37.mylittlecanvas.touch.EventPos;

public class MoveActionCircle extends MoveAction<CircleShape, CircleShape.Pos> {

    public MoveActionCircle(EventPos event_pos, CircleShape shape, CircleShape.Pos pos) {
        super(event_pos, shape, pos);
    }

    @Override
    protected void move(float value) {
        switch (pos) {
            case CENTER_X:
                shape.setCenterX(value);
                break;
            case CENTER_Y:
                shape.setCenterY(value);
                break;
            default:
                break;
        }
    }
}
