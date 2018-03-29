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
import com.github.florent37.mylittlecanvas.shape.RectShape;
import com.github.florent37.mylittlecanvas.shape.TextShape;
import com.github.florent37.mylittlecanvas.values.Alignment;

import static com.github.florent37.mylittlecanvas.CanvasHelper.dpToPx;

public class SliderView extends View {

    private final RectShape background = new RectShape();
    private final CircleShape indicator = new CircleShape();
    private final TextShape minText = new TextShape();
    private final TextShape maxText = new TextShape();

    private ShapeAnimator shapeAnimator = new ShapeAnimator(this);

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
        //configurations that does not depends on the view size
        background.setHeight(dpToPx(this, 15))
                .setCornerRadius(background.getHeight() / 2f)
                .setColor(Color.parseColor("#3F51B5"))
                .setVariable("margin", dpToPx(this, 20))
                .setLeft(background.<Float>getVariable("margin"));

        minText.setText("0")
                .setTextSizePx(dpToPx(this, 11))
                .setColor(Color.parseColor("#3E3E3E"))
                .setVerticalAlignment(Alignment.VERTICAL.BOTTOM)
                .setHorizontalAlignment(Alignment.HORIZONTAL.LEFT)
                .setLeft(background.getLeft())
                .setTop(0)
                .setVariable("distance", dpToPx(this, 5));

        maxText.setText("100")
                .setTextSizePx(dpToPx(this, 11))
                .setColor(Color.parseColor("#3E3E3E"))
                .setVerticalAlignment(Alignment.VERTICAL.BOTTOM)
                .setHorizontalAlignment(Alignment.HORIZONTAL.RIGHT)
                .setLeft(background.getLeft())
                .setTop(0)
                .setBottom(background.getBottom());

        indicator.setBorderColor(Color.parseColor("#3F51B5"))
                .setBorderWidth(dpToPx(this, 2))
                .setColor(Color.WHITE)
                .setRadius(background.getHeight() / 2f + dpToPx(this, 4))
                .setVariable("original_radius", indicator.getRadius())
                .setMinX(background.getLeft())
                .setCenterX(background.getLeft() + indicator.getRadius());
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        //configurations that depends on the view size

        background
                .setRight(width - background.<Float>getVariable("margin"))
                .centerVertical(height);

        minText
                .setBottom(background.getTop() - minText.<Float>getVariable("distance"))
                .setRight(background.getRight());

        maxText
                .setBottom(background.getTop() - minText.<Float>getVariable("distance"))
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
                        .play(indicator.animateRadiusTo(background.getHeight()))
                        .start();
            case MotionEvent.ACTION_MOVE:
                indicator.setCenterX(event.getX());
                postInvalidate();
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                shapeAnimator
                        .clear()
                        .play(indicator.animateRadiusTo(indicator.<Float>getVariable("original_radius")))
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
