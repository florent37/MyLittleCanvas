package com.github.florent37.mylittlecanvas.touch.scrollable;

import android.view.ViewGroup;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class ScrollableViewGroup implements Scrollable {

    private final Reference<ViewGroup> viewReference;

    public ScrollableViewGroup(ViewGroup view){
        viewReference = new WeakReference<>(view);
    }

    @Override
    public void disableScroll() {
        final ViewGroup view = viewReference.get();
        if (view != null) {
            view.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override
    public void enableScroll() {
        final ViewGroup view = viewReference.get();
        if (view != null) {
            view.requestDisallowInterceptTouchEvent(true);
        }
    }
}
