package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.florent37.mylittlecanvas.listeners.ClickedListener;
import com.github.florent37.mylittlecanvas.shape.LineShape;
import com.github.florent37.mylittlecanvas.shape.RectShape;
import com.github.florent37.mylittlecanvas.shape.TextShape;
import com.github.florent37.mylittlecanvas.touch.EventPos;
import com.github.florent37.mylittlecanvas.touch.ShapeEventManager;
import com.github.florent37.mylittlecanvas.values.Alignment;

public class MyTreeView extends View {

    final RectShape parent = new RectShape();
    final TextShape textParent = new TextShape();

    final RectShape childLeft = new RectShape();
    final TextShape textChildLeft = new TextShape();
    final LineShape lineParentChildLeft = new LineShape();

    final RectShape childRight = new RectShape();
    final TextShape textChildRight = new TextShape();
    final LineShape lineParentChildRight = new LineShape();

    private ShapeEventManager shapeEventManager = new ShapeEventManager(this);

    public MyTreeView(Context context) {
        this(context, null);
    }

    public MyTreeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parent.setCornerRadius(10)
                .setColor(Color.parseColor("#3F51B5"));
        textParent.setColor(Color.WHITE)
                .setTextSizePx(40);

        childLeft.setCornerRadius(10)
                .setColor(Color.parseColor("#2196F3"));
        textChildLeft.setColor(Color.WHITE)
                .setTextSizePx(40);
        lineParentChildLeft.setStrokeWidth(3)
                .setColor(Color.parseColor("#3E3E3E"));

        childRight.setCornerRadius(10)
                .setColor(Color.parseColor("#9575CD"));
        textChildRight.setColor(Color.WHITE)
                .setTextSizePx(40);
        lineParentChildRight.setStrokeWidth(3)
                .setColor(Color.parseColor("#3E3E3E"));

        handleMoving();
    }

    private void handleMoving() {
        shapeEventManager
                .ifTouched(childLeft, (touchSetup) -> touchSetup
                        .move(childLeft, RectShape.Pos.CENTER_X, EventPos.X)
                        .move(childLeft, RectShape.Pos.TOP, EventPos.Y)
                        .onMove((event) -> textChildLeft.copyPosition(childLeft))
                        .onMove((event) -> lineParentChildLeft.end(childLeft.getCenterX(), childLeft.getTop()))
                )
                //.ifClicked(childLeft, shape -> shape.setColor(Color.BLACK))
                .ifTouched(childRight, (touchSetup) -> touchSetup
                        .move(childRight, RectShape.Pos.CENTER_X, EventPos.X)
                        .move(childRight, RectShape.Pos.TOP, EventPos.Y)
                        .onMove((event) -> textChildRight.copyPosition(childRight))
                        .onMove((event) -> lineParentChildRight.end(childRight.getCenterX(), childRight.getTop()))
                );
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
    }
}
