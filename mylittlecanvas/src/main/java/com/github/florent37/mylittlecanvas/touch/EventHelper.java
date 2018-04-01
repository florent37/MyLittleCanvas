package com.github.florent37.mylittlecanvas.touch;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.github.florent37.mylittlecanvas.animation.ShapeAnimator;
import com.github.florent37.mylittlecanvas.listeners.InvalidateListener;
import com.github.florent37.mylittlecanvas.shape.CircleShape;
import com.github.florent37.mylittlecanvas.shape.RectShape;
import com.github.florent37.mylittlecanvas.shape.Shape;
import com.github.florent37.mylittlecanvas.touch.actions.MoveAction;
import com.github.florent37.mylittlecanvas.touch.actions.MoveActionCircle;
import com.github.florent37.mylittlecanvas.touch.actions.MoveActionRect;
import com.github.florent37.mylittlecanvas.touch.listeners.DownListener;
import com.github.florent37.mylittlecanvas.touch.listeners.MoveListener;
import com.github.florent37.mylittlecanvas.touch.listeners.UpListener;

import java.util.ArrayList;
import java.util.List;

public class EventHelper {

    private final InvalidateListener invalidateListener;
    private final List<MoveAction> moveActions = new ArrayList<>();
    private final ShapeAnimator shapeAnimator;
    private final List<EventAnimator> downAnimators = new ArrayList<>();
    private final List<DownListener> downListeners = new ArrayList<>();
    private final List<MoveListener> moveListeners = new ArrayList<>();
    private final List<EventAnimator> upAnimators = new ArrayList<>();
    private final List<UpListener> upListeners = new ArrayList<>();
    boolean startedDown = false;
    @Nullable
    private Shape listeningShape;
    private EventHelper.Event waitedEvent = Event.TOUCH;

    public EventHelper(InvalidateListener invalidateListener) {
        this.invalidateListener = invalidateListener;
        shapeAnimator = new ShapeAnimator(invalidateListener);
    }

    //if tapped
    public EventHelper(InvalidateListener invalidateListener, EventHelper.Event waitedEvent, Shape listeningShape) {
        this(invalidateListener);
        this.listeningShape = listeningShape;
        this.waitedEvent = waitedEvent;
    }

    private void postInvalidate() {
        invalidateListener.invalidate();
    }

    public EventHelper onDownAnimate(EventAnimator eventAnimator) {
        downAnimators.add(eventAnimator);
        return this;
    }

    public EventHelper onDown(DownListener listener) {
        downListeners.add(listener);
        return this;
    }

    public EventHelper onMove(MoveListener listener) {
        moveListeners.add(listener);
        return this;
    }

    public EventHelper onUpAnimate(EventAnimator eventAnimator) {
        upAnimators.add(eventAnimator);
        return this;
    }

    public EventHelper onUp(UpListener listener) {
        upListeners.add(listener);
        return this;
    }

    public EventHelper move(CircleShape shape, CircleShape.Pos pos, EventPos eventPos) {
        moveActions.add(new MoveActionCircle(eventPos, shape, pos));
        return this;
    }

    public EventHelper move(RectShape shape, RectShape.Pos pos, EventPos eventPos) {
        moveActions.add(new MoveActionRect(eventPos, shape, pos));
        return this;
    }

    void handleDown(@Nullable final MotionEvent lastEvent, @NonNull final MotionEvent event) {
        if (accept(lastEvent, event)) {
            startedDown = true;
            for (DownListener downListener : downListeners) {
                downListener.onDown(event);
            }
            if (!downAnimators.isEmpty()) {
                shapeAnimator
                        .clear()
                        .play(toAnimators(event, downAnimators))
                        .start();
            }
            postInvalidate();
        }
    }

    void handleMove(@Nullable final MotionEvent lastEvent, @NonNull final MotionEvent event) {
        if (accept(lastEvent, event)) {
            for (MoveAction moveAction : moveActions) {
                moveAction.move(event);
            }
            for (MoveListener moveListener : moveListeners) {
                moveListener.onMove(event);
            }
            postInvalidate();
        }
    }

    private List<ValueAnimator> toAnimators(@NonNull final MotionEvent event, @NonNull final List<EventAnimator> eventAnimators) {
        final List<ValueAnimator> valueAnimators = new ArrayList<>();
        for (EventAnimator eventAnimator : eventAnimators) {
            valueAnimators.add(eventAnimator.animate(event));
        }
        return valueAnimators;
    }

    void handleUp(@Nullable final MotionEvent lastEvent, @NonNull final MotionEvent event) {
        if (accept(lastEvent, event)) {
            for (UpListener upListener : upListeners) {
                upListener.onUp(event);
            }
            if (!upAnimators.isEmpty()) {
                shapeAnimator
                        .clear()
                        .play(toAnimators(event, upAnimators))
                        .start();
            }
            postInvalidate();
            startedDown = false;
        }
    }

    private boolean accept(MotionEvent lastEvent, MotionEvent event) {
        if (listeningShape == null) {
            return true;
        } else {
            switch (waitedEvent) {
                case TOUCH:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        return listeningShape.containsTouch(event.getX(), event.getY());
                    }
                    return startedDown;
                case CLICK:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        return true;
                    }
                    return startedDown && event.getAction() == MotionEvent.ACTION_UP && listeningShape.containsTouch(event.getX(), event.getY());
                default:
                    return false;
            }
        }
    }

    public enum Event {
        TOUCH,
        CLICK,
        DOUBLE_CLICK
    }
}
