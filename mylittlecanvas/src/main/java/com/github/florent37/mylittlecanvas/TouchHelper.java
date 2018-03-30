package com.github.florent37.mylittlecanvas;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.github.florent37.mylittlecanvas.shape.CircleShape;
import com.github.florent37.mylittlecanvas.shape.RectShape;
import com.github.florent37.mylittlecanvas.shape.Shape;
import com.github.florent37.mylittlecanvas.touch.CirclePos;
import com.github.florent37.mylittlecanvas.touch.DownAction;
import com.github.florent37.mylittlecanvas.touch.EventPos;
import com.github.florent37.mylittlecanvas.touch.RectPos;
import com.github.florent37.mylittlecanvas.touch.UpAction;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TouchHelper {

    private final Reference<View> viewReference;
    private final List<MoveAction> moveActions = new ArrayList<>();
    private final ShapeAnimator shapeAnimator;

    private final List<ValueAnimator> downAnimators = new ArrayList<>();
    private final List<DownAction> downActions = new ArrayList<>();
    private final List<ValueAnimator> upAnimators = new ArrayList<>();
    private final List<UpAction> upActions = new ArrayList<>();

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    for (DownAction downAction : downActions) {
                        downAction.onDown(TouchHelper.this, event);
                    }
                    if(!downAnimators.isEmpty()){
                        shapeAnimator
                                .clear()
                                .play(downAnimators)
                                .start();
                    }
                    postInvalidate();
                case MotionEvent.ACTION_MOVE:
                    for (MoveAction moveAction : moveActions) {
                        moveAction.move(event);
                    }
                    postInvalidate();

                    return true;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    for (UpAction upAction : upActions) {
                        upAction.onUp(TouchHelper.this, event);
                    }
                    if(!upAnimators.isEmpty()){
                        shapeAnimator
                                .clear()
                                .play(upAnimators)
                                .start();
                    }
                    postInvalidate();
            }
            return false;
        }
    };

    private TouchHelper(@NonNull final View view) {
        viewReference = new WeakReference<>(view);
        shapeAnimator = new ShapeAnimator(view);
        view.setOnTouchListener(onTouchListener);
    }

    public static TouchHelper onTouch(@NonNull View view) {
        return new TouchHelper(view);
    }

    private void postInvalidate() {
        final View view = viewReference.get();
        if (view != null) {
            view.postInvalidate();
        }
    }

    public TouchHelper onDownAnimate(ValueAnimator... animators) {
        downAnimators.addAll(Arrays.asList(animators));
        return this;
    }

    public TouchHelper onDown(DownAction action) {
        downActions.add(action);
        return this;
    }

    public TouchHelper onUpAnimate(ValueAnimator... animators) {
        upAnimators.addAll(Arrays.asList(animators));
        return this;
    }

    public TouchHelper onUp(UpAction action) {
        upActions.add(action);
        return this;
    }

    public TouchHelper move(CircleShape shape, CirclePos pos, EventPos eventPos) {
        moveActions.add(new MoveActionCircle(eventPos, shape, pos));
        return this;
    }

    public TouchHelper move(RectShape shape, RectPos pos, EventPos eventPos) {
        moveActions.add(new MoveActionRect(eventPos, shape, pos));
        return this;
    }

    private abstract static class MoveAction<S extends Shape, POS> {
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

    private static class MoveActionCircle extends MoveAction<CircleShape, CirclePos> {

        public MoveActionCircle(EventPos event_pos, CircleShape shape, CirclePos pos) {
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

    private static class MoveActionRect extends MoveAction<RectShape, RectPos> {

        public MoveActionRect(EventPos event_pos, RectShape shape, RectPos pos) {
            super(event_pos, shape, pos);
        }

        @Override
        protected void move(float value) {
            switch (pos) {
                case CENTER_X: /* TODO */
                    ;
                    break;
                case CENTER_Y: /* TODO */
                    ;
                    break;
                case LEFT:
                    shape.setLeft(value);
                    break;
                case TOP:
                    shape.setTop(value);
                    break;
                case BOTTOM:
                    shape.setBottom(value);
                    break;
                case RIGHT:
                    shape.setRight(value);
                    break;
                default:
                    break;
            }
        }
    }
}
