package com.github.florent37.mylittlecanvas;

import android.support.annotation.Nullable;
import android.view.MotionEvent;

import static android.view.MotionEvent.INVALID_POINTER_ID;

public class TouchEventDetector {
    private float mLastTouchX;
    private float mLastTouchY;
    private int mActivePointerId;
    private float mPosX;
    private float mPosY;
    @Nullable
    private Listener listener;

    public void setListener(@Nullable Listener listener) {
        this.listener = listener;
    }

    public void onTouchEvent(MotionEvent event) {
        final int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int pointerIndex = event.getActionIndex();
                final float x = event.getX(pointerIndex);
                final float y = event.getY(pointerIndex);

                // Remember where we started (for dragging)
                mPosX = x;
                mPosY = y;

                mLastTouchX = x;
                mLastTouchY = y;
                // Save the ID of this pointer (for dragging)
                mActivePointerId = event.getPointerId(0);

                if (listener != null) {
                    listener.onTouched(mPosX, mPosY);
                }

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position
                final int pointerIndex = event.findPointerIndex(mActivePointerId);

                final float x = event.getX(pointerIndex);
                final float y = event.getY(pointerIndex);

                // Calculate the distance moved
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                mPosX += dx;
                mPosY += dy;

                if (listener != null) {
                    listener.onMoved(dx, dy, mPosX, mPosY);
                }

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {
                if (listener != null) {
                    listener.onRelease(mPosX, mPosY);
                }
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                if (listener != null) {
                    listener.onRelease(mPosX, mPosY);
                }
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }
        }
    }

    public interface Listener {
        void onTouched(float x, float y);

        void onMoved(float differenceX, float differenceY, float newX, float newY);

        void onRelease(float x, float y);
    }
}
