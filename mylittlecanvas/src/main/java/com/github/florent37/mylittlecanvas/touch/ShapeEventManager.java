package com.github.florent37.mylittlecanvas.touch;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.ScrollView;

import com.github.florent37.mylittlecanvas.listeners.ClickedListener;
import com.github.florent37.mylittlecanvas.listeners.InvalidateListener;
import com.github.florent37.mylittlecanvas.listeners.ViewInvalidateListener;
import com.github.florent37.mylittlecanvas.shape.Shape;
import com.github.florent37.mylittlecanvas.touch.listeners.UpListener;
import com.github.florent37.mylittlecanvas.touch.scrollable.Scrollable;
import com.github.florent37.mylittlecanvas.touch.scrollable.ScrollableViewGroup;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ShapeEventManager {
    private final Reference<View> viewReference;
    private final InvalidateListener invalidateListener;
    private final List<EventHelper> touchHelpers = new ArrayList<>();
    private boolean disableParentScrollOnTouch = true;
    private Scrollable parentScroll; //nonNull when searchedForParent
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Nullable
        private MotionEvent lastEvent;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (parentScroll == null) {
                parentScroll = findParentScroll();
            }
            assert (parentScroll != null);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if(isDisableParentScrollOnTouch()) {
                        parentScroll.disableScroll();
                    }
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
                    if(isDisableParentScrollOnTouch()) {
                        parentScroll.enableScroll();
                    }
                    for (EventHelper touchHelper : touchHelpers) {
                        touchHelper.handleUp(lastEvent, event);
                    }
            }
            this.lastEvent = event;
            return false;
        }
    };

    public boolean isDisableParentScrollOnTouch() {
        return disableParentScrollOnTouch;
    }

    public ShapeEventManager disableParentScrollOnTouch(boolean disableParentScrollOnTouch) {
        this.disableParentScrollOnTouch = disableParentScrollOnTouch;
        return this;
    }

    public ShapeEventManager(@NonNull final View view) {
        viewReference = new WeakReference<>(view);
        view.setOnTouchListener(onTouchListener);
        invalidateListener = new ViewInvalidateListener(view);
    }

    public ShapeEventManager ifTouched(@Nullable final Shape shape, @Nullable final TouchSetup touchSetup) {
        if (shape != null && touchSetup != null) {
            final EventHelper touchHelper = new EventHelper(invalidateListener, EventHelper.Event.TOUCH, shape);
            touchHelpers.add(touchHelper);
            touchSetup.setupTouch(touchHelper);
        }
        return this;
    }

    public <S extends Shape> ShapeEventManager ifClicked(@Nullable final S shape, @Nullable final ClickedListener<S> clickedListener) {
        if (shape != null && clickedListener != null) {
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
    public ShapeEventManager ifDoubleClicked(@Nullable final Shape shape, @Nullable final TouchSetup touchSetup) {
        if (shape != null && touchSetup != null) {
            final EventHelper touchHelper = new EventHelper(invalidateListener, EventHelper.Event.DOUBLE_CLICK, shape);
            touchHelpers.add(touchHelper);
            touchSetup.setupTouch(touchHelper);
        }
        return this;
    }

    public ShapeEventManager onTouchAywhere(@Nullable final TouchSetup touchSetup) {
        if (touchSetup != null) {
            final EventHelper touchHelper = new EventHelper(invalidateListener);
            touchHelpers.add(touchHelper);
            touchSetup.setupTouch(touchHelper);
        }
        return this;
    }

    @NonNull
    private Scrollable findParentScroll() {
        final View view = viewReference.get();
        Scrollable scrollable = null;
        if (view != null) {
            final ViewParent parent = view.getParent();
            if(parent instanceof ViewGroup) {
                scrollable = findParentScroll(((ViewGroup) parent));
            }
        }
        if (scrollable == null) {
            scrollable = new ScrollableViewGroup(null);
        }
        return scrollable;
    }

    /**
     * If another Scroll container exists, add it into this switch
     */
    private Scrollable findParentScroll(ViewGroup view) {
        if (view instanceof ScrollView) {
            return new ScrollableViewGroup(view);
        } else if (view instanceof NestedScrollView) {
            return new ScrollableViewGroup(view);
        } else if (view instanceof ListView) {
            return new ScrollableViewGroup(view);
        } else if (view instanceof RecyclerView) {
            return new ScrollableViewGroup(view);
        }
        final ViewParent parent = view.getParent();
        if(parent instanceof ViewGroup) {
            return findParentScroll((ViewGroup) parent);
        } else { //root view
            return null;
        }
    }

    public interface TouchSetup {
        void setupTouch(EventHelper eventHelper);
    }

}
