package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.florent37.mylittlecanvas.shape.CircleShape;
import com.github.florent37.mylittlecanvas.shape.RoundRectShape;
import com.github.florent37.mylittlecanvas.shape.TextShape;

public class SliderView extends View {

    final RoundRectShape background = new RoundRectShape();
    final CircleShape indicator = new CircleShape();
    final TextShape minText = new TextShape();
    final TextShape maxText = new TextShape();

    private int sliderMargin;

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
        sliderMargin = ViewUtils.doToPx(getContext(), 20);

        background.setHeight(ViewUtils.doToPx(getContext(), 20))
                .setCorderRadius(background.getHeight() / 2f)
                .setColor(Color.parseColor("#3F51B5"))
                .setLeft(sliderMargin);

        indicator.setBorderColor(Color.parseColor("#3F51B5"))
                .setBorderWidth(ViewUtils.doToPx(getContext(), 2))
                .setColor(Color.WHITE)
                .setRadius(background.getHeight() * 0.4f)
                .setMinX(background.getLeft())
                .setCenterX(background.getLeft() + indicator.getRadius());
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        background
                .setRight(width - sliderMargin)
                .centerVertical(height);

        indicator
                .setMaxX(background.getRight())
                .centerVertical(height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                indicator.setCenterX(event.getX());
                postInvalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        background.onDraw(canvas);
        indicator.onDraw(canvas);
    }
}
