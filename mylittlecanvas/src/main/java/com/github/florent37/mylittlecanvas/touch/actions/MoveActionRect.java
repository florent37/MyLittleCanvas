package com.github.florent37.mylittlecanvas.touch.actions;

import com.github.florent37.mylittlecanvas.shape.RectShape;
import com.github.florent37.mylittlecanvas.touch.EventPos;

public class MoveActionRect extends MoveAction<RectShape, RectShape.Pos> {

    public MoveActionRect(EventPos event_pos, RectShape shape, RectShape.Pos pos) {
        super(event_pos, shape, pos);
    }

    @Override
    protected void move(float value) {
        switch (pos) {
            case CENTER_X:
                shape.moveCenterXTo(value);
                break;
            case CENTER_Y:
                shape.moveCenterYTo(value);
                break;
            case LEFT:
                shape.moveLeftTo(value);
                break;
            case TOP:
                shape.moveTopTo(value);
                break;
            case BOTTOM:
                shape.moveBottomTo(value);
                break;
            case RIGHT:
                shape.moveRightTo(value);
                break;
            default:
                break;
        }
    }
}
