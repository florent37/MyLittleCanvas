package com.github.florent37.mylittlecanvas.touch;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.github.florent37.mylittlecanvas.listeners.ClickedListener;
import com.github.florent37.mylittlecanvas.listeners.InvalidateListener;
import com.github.florent37.mylittlecanvas.listeners.ViewInvalidateListener;
import com.github.florent37.mylittlecanvas.shape.Shape;
import com.github.florent37.mylittlecanvas.touch.listeners.UpListener;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ShapeEventManager {
    private final Reference<View> viewReference;
    private final InvalidateListener invalidateListener;
    private final List<EventHelper> touchHelpers = new ArrayList<>();

    public ShapeEventManager(@NonNull final View view) {
        viewReference = new WeakReference<>(view);
        view.setOnTouchListener(onTouchListener);
        invalidateListener = new ViewInvalidateListener(view);
    }

    public ShapeEventManager ifTouched(@Nullable final Shape shape, @Nullable final TappedSetup tappedSetup){
        if(shape != null && tappedSetup != null){
            final EventHelper touchHelper = new EventHelper(invalidateListener, EventHelper.Event.TOUCH, shape);
            touchHelpers.add(touchHelper);
            tappedSetup.onTouched(touchHelper);
        }
        return this;
    }

    public <S extends Shape> ShapeEventManager ifClicked(@Nullable final S shape, @Nullable final ClickedListener<S> clickedListener){
        if(shape != null && clickedListener != null){
            final EventHelper touchHelper = new EventHelper(invalidateListener, EventHelper.Event.CLICK, shape);
            touchHelper.onUp(new UpListener() {
                @Override
                public void onUp(MotionEvent event) {
                    clickedListener.onClick(shape);
                }
            });
            touchHelpers.add(touchHelper);
        }
        return this;
    }

    @Deprecated
    public ShapeEventManager ifDoubleClicked(@Nullable final Shape shape, @Nullable final TappedSetup tappedSetup){
        if(shape != null && tappedSetup != null){
            final EventHelper touchHelper = new EventHelper(invalidateListener, EventHelper.Event.DOUBLE_CLICK, shape);
            touchHelpers.add(touchHelper);
            tappedSetup.onTouched(touchHelper);
        }
        return this;
    }

    public ShapeEventManager onTouchAywhere(@Nullable final TappedSetup tappedSetup){
        if(tappedSetup != null){
            final EventHelper touchHelper = new EventHelper(invalidateListener);
            touchHelpers.add(touchHelper);
            tappedSetup.onTouched(touchHelper);
        }
        return this;
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Nullable
        private MotionEvent lastEvent;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    for (EventHelper touchHelper : touchHelpers) {
                        touchHelper.handleDown(lastEvent, event);
                    }
                case MotionEvent.ACTION_MOVE:
                    for (EventHelper touchHelper : touchHelpers) {
                        touchHelper.handleMove(lastEvent, event);
                    }
                    this.lastEvent = event;
                    return true;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    for (EventHelper touchHelper : touchHelpers) {
                        touchHelper.handleUp(lastEvent, event);
                    }
            }
            this.lastEvent = event;
            return false;
        }
    };

    public interface TappedSetup {
        void onTouched(EventHelper eventHelper);
    }

}
