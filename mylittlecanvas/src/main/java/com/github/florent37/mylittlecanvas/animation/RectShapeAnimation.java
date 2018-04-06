package com.github.florent37.mylittlecanvas.animation;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;

import com.github.florent37.mylittlecanvas.shape.RectShape;

public class RectShapeAnimation<S extends RectShape> extends ShapeAnimation<S> {

    public RectShapeAnimation(S shape) {
        super(shape);
    }

    //region left

    /**
     * Change the left of the rect
     * Warning /!\ it change the width of the view
     * Use .moveLeft to keep the view width
     */
    public ValueAnimator left(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.setLeft((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator leftTo(final float... values) {
        return left(insertAtFirst(shape.getLeft(), values));
    }

    public ValueAnimator leftBy(float... values) {
        final float[] newValues = new float[values.length];
        final float left = shape.getLeft();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * left;
        }
        return left(newValues);
    }

    public ValueAnimator leftPlus(float... values) {
        final float[] newValues = new float[values.length];
        final float left = shape.getLeft();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + left;
        }
        return leftTo(newValues);
    }

    /**
     * Move the rect to the left, keeping his width
     */
    public ValueAnimator moveLeftTo(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.getLeft(), values));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.moveLeftTo((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }
    //endregion

    //region right

    /**
     * Change the right of the rect
     * Warning /!\ it change the width of the view
     * Use .moveRight to keep the view width
     */
    public ValueAnimator right(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.setRight((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator rightTo(final float... values) {
        return right(insertAtFirst(shape.getRight(), values));
    }

    public ValueAnimator rightPlus(float... values) {
        final float[] newValues = new float[values.length];
        final float right = shape.getRight();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + right;
        }
        return rightTo(newValues);
    }

    public ValueAnimator rightBy(float... values) {
        final float[] newValues = new float[values.length];
        final float right = shape.getRight();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * right;
        }
        return right(newValues);
    }

    /**
     * Move the rect to the right, keeping his height
     */
    public ValueAnimator moveRightTo(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.getRight(), values));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.moveRightTo((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    //endregion

    //region top

    /**
     * Change the top of the rect
     * Warning /!\ it change the height of the view
     * Use .moveTop to keep the view height
     */
    public ValueAnimator top(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.setTop((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator topTo(final float... values) {
        return top(insertAtFirst(shape.getTop(), values));
    }

    public ValueAnimator topBy(float... values) {
        final float[] newValues = new float[values.length];
        final float top = shape.getTop();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * top;
        }
        return top(newValues);
    }

    public ValueAnimator topPlus(float... values) {
        final float[] newValues = new float[values.length];
        final float top = shape.getTop();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + top;
        }
        return topTo(newValues);
    }

    /**
     * Move the rect to the top, keeping his height
     */
    public ValueAnimator moveTopTo(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.getTop(), values));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.moveTopTo((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    //endregion

    //region bottom

    /**
     * Change the bottom of the rect
     * Warning /!\ it change the height of the view
     * Use .moveBottom to keep the view height
     */
    public ValueAnimator bottom(float... values) {
        final float[] newValues = new float[values.length+1];
        newValues[0] = shape.getBottom();
        for (int i = 0; i < values.length; i++) {
            newValues[i+1] = values[i];
        }

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(newValues);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.setBottom((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator bottomTo(final float... values) {
        return bottom(insertAtFirst(shape.getBottom(), values));
    }

    public ValueAnimator bottomBy(float... values) {
        final float[] newValues = new float[values.length];
        final float bottom = shape.getBottom();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] * bottom;
        }
        return bottom(newValues);
    }

    public ValueAnimator bottomPlus(float... values) {
        final float[] newValues = new float[values.length];
        final float bottom = shape.getBottom();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + bottom;
        }
        return bottomTo(newValues);
    }

    /**
     * Move the rect to the bottom, keeping his height
     */
    public ValueAnimator moveBottomTo(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.getBottom(), values));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.moveBottomTo((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    //endregion

    public ValueAnimator borderColorTo(final int... color) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(insertAtFirst(shape.getBorderColor(), color));
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                shape.setBorderColor((int) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator borderWidthTo(final float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.getBorderWidth(), values));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                shape.setBorderWidth((int) valueAnimator.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator centerX(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.moveCenterXTo((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator centerXTo(float ... values) {
        return centerX(insertAtFirst(shape.getCenterX(), values));
    }

    public ValueAnimator centerXPlus(float... values) {
        final float[] newValues = new float[values.length];
        final float centerX = shape.getCenterX();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + centerX;
        }
        return centerXTo(newValues);
    }

    public ValueAnimator centerY(float... values) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shape.moveCenterYTo((Float) animation.getAnimatedValue());
            }
        });
        return valueAnimator;
    }

    public ValueAnimator centerYTo(float... values) {
        return centerY(insertAtFirst(shape.getCenterY(), values));
    }

    public ValueAnimator centerYPlus(float... values) {
        final float[] newValues = new float[values.length];
        final float centerY = shape.getCenterY();
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] + centerY;
        }
        return centerYTo(newValues);
    }
}
