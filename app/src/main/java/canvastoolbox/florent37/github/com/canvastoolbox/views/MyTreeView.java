package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.florent37.mylittlecanvas.TouchEventDetector;
import com.github.florent37.mylittlecanvas.shape.DrawableShape;
import com.github.florent37.mylittlecanvas.shape.LineShape;
import com.github.florent37.mylittlecanvas.shape.RectShape;
import com.github.florent37.mylittlecanvas.shape.RoundRectShape;
import com.github.florent37.mylittlecanvas.shape.TextShape;
import com.github.florent37.mylittlecanvas.values.Alignment;

import java.util.concurrent.atomic.AtomicReference;

public class MyTreeView extends View {

    final RoundRectShape parent = new RoundRectShape();
    final TextShape textParent = new TextShape();

    final RoundRectShape childLeft = new RoundRectShape();
    final TextShape textChildLeft = new TextShape();
    final LineShape lineParentChildLeft = new LineShape();

    final RoundRectShape childRight = new RoundRectShape();
    final TextShape textChildRight = new TextShape();
    final LineShape lineParentChildRight = new LineShape();

    final DrawableShape drawableShape = new DrawableShape();
    private TouchEventDetector touchEventDetector = new TouchEventDetector();

    public MyTreeView(Context context) {
        this(context, null);
    }

    public MyTreeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parent.setCorderRadius(10)
                .setColor(Color.parseColor("#3F51B5"));
        textParent.setColor(Color.WHITE)
                .setTextSizePx(40);

        childLeft.setCorderRadius(10)
                .setColor(Color.parseColor("#2196F3"));
        textChildLeft.setColor(Color.WHITE)
                .setTextSizePx(40);
        lineParentChildLeft.setStrokeWidth(3)
                .setColor(Color.parseColor("#3E3E3E"));

        childRight.setCorderRadius(10)
                .setColor(Color.parseColor("#9575CD"));
        textChildRight.setColor(Color.WHITE)
                .setTextSizePx(40);
        lineParentChildRight.setStrokeWidth(3)
                .setColor(Color.parseColor("#3E3E3E"));

        drawableShape.setDrawable(getResources().getDrawable(android.R.drawable.ic_delete));

        handleMoving();
    }

    private void handleMoving() {
        final AtomicReference<RectShape> movingShape = new AtomicReference<>();
        touchEventDetector.setListener(new TouchEventDetector.Listener() {
            @Override
            public void onTouched(float x, float y) {
                if (childLeft.containsTouch(x, y)) {
                    movingShape.set(childLeft);
                } else if (childRight.containsTouch(x, y)) {
                    movingShape.set(childRight);
                }
            }

            @Override
            public void onMoved(float differenceX, float differenceY, float newX, float newY) {
                final RectShape moving = movingShape.get();
                if (moving != null) {
                    moving.moveBy(differenceX, differenceY);
                    update();
                    invalidate();
                }
            }

            @Override
            public void onRelease(float x, float y) {
                movingShape.set(null);
            }
        });
    }

    private void init() {
        final int parentWidth = 200;
        parent.setTop(50)
                .setHeight(100)
                .setWidth(parentWidth)
                .centerHorizontal(getWidth());

        textParent.setText("parent")
                .setVerticalAlignment(Alignment.VERTICAL.CENTER)
                .setHorizontalAlignment(Alignment.HORIZONTAL.CENTER)
                .copyPosition(parent);

        final int childWidth = 200;

        childLeft.setLeft(40)
                .setWidth(childWidth)
                .setMinY(parent.getBottom())
                .below(parent)
                .setMinX(0)
                .setMaxX(getWidth())
                .marginTop(250)
                .setHeight(100);

        textChildLeft
                .setText("childLeft")
                .setVerticalAlignment(Alignment.VERTICAL.CENTER)
                .setHorizontalAlignment(Alignment.HORIZONTAL.CENTER)
                .copyPosition(childLeft);

        lineParentChildLeft
                .start(parent.getCenterX(), parent.getBottom())
                .end(childLeft.getCenterX(), childLeft.getTop());

        childRight
                .setLeft(getWidth() - childWidth - 40)
                .setWidth(childWidth)
                .setMinX(0)
                .setMaxX(getWidth())
                .setMinY(parent.getBottom())
                .alignTop(childLeft)
                .setHeight(100);

        textChildRight
                .setText("childRight")
                .setVerticalAlignment(Alignment.VERTICAL.CENTER)
                .setHorizontalAlignment(Alignment.HORIZONTAL.CENTER)
                .copyPosition(childRight);

        lineParentChildRight
                .start(parent.getCenterX(), parent.getBottom())
                .end(childRight.getCenterX(), childRight.getTop());

        drawableShape.setLeft(0)
                .setTop(0)
                .setRight(100)
                .setBottom(100);
    }

    private void update() {
        textChildLeft.copyPosition(childLeft);
        lineParentChildLeft
                .end(childLeft.getCenterX(), childLeft.getTop());

        textChildRight.copyPosition(childRight);
        lineParentChildRight
                .end(childRight.getCenterX(), childRight.getTop());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchEventDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        parent.onDraw(canvas);
        textParent.onDraw(canvas);

        childLeft.onDraw(canvas);
        textChildLeft.onDraw(canvas);
        lineParentChildLeft.onDraw(canvas);

        childRight.onDraw(canvas);
        textChildRight.onDraw(canvas);
        lineParentChildRight.onDraw(canvas);

        drawableShape.onDraw(canvas);
    }
}
