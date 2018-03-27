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

public class SliderView extends View {

    private int sliderMargin;
    private int sliderHeight;

    final RoundRectShape background = new RoundRectShape();

    final CircleShape indicator = new CircleShape();

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
        sliderHeight = ViewUtils.doToPx(getContext(), 20);
        sliderMargin = ViewUtils.doToPx(getContext(), 20);

        background
                .setCorderRadius(sliderHeight / 2f)
                .setColor(Color.parseColor("#3F51B5"))
                .setLeft(sliderMargin)
                .setHeight(sliderHeight);

        indicator
                .setBorderColor(Color.parseColor("#3F51B5"))
                .setBorderWidth(ViewUtils.doToPx(getContext(), 2))
                .setColor(Color.WHITE)
                .setRadius(sliderHeight / 2f);

        indicator.setCenterX(background.getLeft() + indicator.getRadius());
    }

    @Override
    protected void onSizeChanged(int width, int h, int oldw, int oldh) {
        super.onSizeChanged(width, h, oldw, oldh);

        background.setRight(width - sliderMargin);

        background.centerVertical(h);

        indicator.setCenterY(h / 2f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                indicator.setCenterX(limit(background.getLeft() + indicator.getRadius() / 2f, event.getX(), background.getRight() - indicator.getRadius() / 2f));
                postInvalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }

    private float limit(float min, float value, float max) {
        if(value < min){
            value = min;
        }
        if(value > max){
            value = max;
        }
        return value;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        background.onDraw(canvas);
        indicator.onDraw(canvas);
    }
}
