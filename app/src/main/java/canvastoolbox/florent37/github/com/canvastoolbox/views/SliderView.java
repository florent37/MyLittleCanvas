package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.florent37.mylittlecanvas.ShapeAnimator;
import com.github.florent37.mylittlecanvas.shape.CircleShape;
import com.github.florent37.mylittlecanvas.shape.RoundRectShape;
import com.github.florent37.mylittlecanvas.shape.TextShape;
import com.github.florent37.mylittlecanvas.values.Alignment;

import static canvastoolbox.florent37.github.com.canvastoolbox.views.ViewUtils.doToPx;
import static com.github.florent37.mylittlecanvas.CanvasHelper.dpToPx;

public class SliderView extends View {

    final RoundRectShape background = new RoundRectShape();
    final CircleShape indicator = new CircleShape();
    final TextShape minText = new TextShape();
    final TextShape maxText = new TextShape();

    private int sliderMargin;

    private ShapeAnimator shapeAnimator;

    public SliderView(Context context) {
        super(context);
        init();
    }

    public SliderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SliderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        shapeAnimator = new ShapeAnimator(this);
        sliderMargin = doToPx(getContext(), 20);

        background.setHeight(doToPx(getContext(), 15))
                .setCorderRadius(background.getHeight() / 2f)
                .setColor(Color.parseColor("#3F51B5"))
                .setLeft(sliderMargin);

        minText.setText("0")
                .setTextSizePx(doToPx(getContext(), 11))
                .setColor(Color.parseColor("#3E3E3E"))
                .setVerticalAlignment(Alignment.VERTICAL.BOTTOM)
                .setHorizontalAlignment(Alignment.HORIZONTAL.LEFT)
                .setLeft(background.getLeft())
                .setTop(0);

        maxText.setText("100")
                .setTextSizePx(doToPx(getContext(), 11))
                .setColor(Color.parseColor("#3E3E3E"))
                .setVerticalAlignment(Alignment.VERTICAL.BOTTOM)
                .setHorizontalAlignment(Alignment.HORIZONTAL.RIGHT)
                .setLeft(background.getLeft())
                .setTop(0)
                .setBottom(background.getBottom());

        indicator.setBorderColor(Color.parseColor("#3F51B5"))
                .setBorderWidth(doToPx(getContext(), 2))
                .setColor(Color.WHITE)
                .setRadius(background.getHeight() / 2f + doToPx(getContext(), 4))
                .setMinX(background.getLeft())
                .setCenterX(background.getLeft() + indicator.getRadius());
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        background
                .setRight(width - sliderMargin)
                .centerVertical(height);

        minText
                .setBottom(background.getTop() - dpToPx(getContext(), 5))
                .setRight(background.getRight());

        maxText
                .setBottom(background.getTop() - dpToPx(getContext(), 5))
                .setRight(background.getRight());

        indicator
                .setMaxX(background.getRight())
                .centerVertical(height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                shapeAnimator
                        .clear()
                        .play(indicator.animateRadius(indicator.getRadius(), background.getHeight()))
                        .start();
            case MotionEvent.ACTION_MOVE:
                indicator.setCenterX(event.getX());
                postInvalidate();
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                shapeAnimator
                        .clear()
                        .play(indicator.animateRadius(indicator.getRadius(), background.getHeight() / 2f + doToPx(getContext(), 4)))
                        .start();
                postInvalidate();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        background.onDraw(canvas);
        indicator.onDraw(canvas);
        minText.onDraw(canvas);
        maxText.onDraw(canvas);
    }
}
