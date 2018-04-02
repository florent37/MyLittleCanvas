package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.florent37.mylittlecanvas.shape.CircleShape;
import com.github.florent37.mylittlecanvas.shape.RectShape;
import com.github.florent37.mylittlecanvas.shape.TextShape;
import com.github.florent37.mylittlecanvas.touch.EventPos;
import com.github.florent37.mylittlecanvas.touch.ShapeEventManager;
import com.github.florent37.mylittlecanvas.values.Alignment;

import static com.github.florent37.mylittlecanvas.CanvasHelper.dpToPx;

public class SliderView extends View {

    private final RectShape background = new RectShape();
    private final CircleShape indicator = new CircleShape();
    private final TextShape indicatorValue = new TextShape();
    private final TextShape minText = new TextShape();
    private final TextShape maxText = new TextShape();

    private ShapeEventManager shapeEventManager = new ShapeEventManager(this);

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
                .setTop(0);

        indicator.setBorderColor(Color.parseColor("#3F51B5"))
                .setBorderWidth(dpToPx(this, 2))
                .setColor(Color.WHITE)
                .setRadius(background.getHeight() / 2f + dpToPx(this, 4))
                .setVariable("original_radius", indicator.getRadius())
                .setMinX(background.getLeft())
                .setCenterX(background.getLeft() + indicator.getRadius());

        indicatorValue.setText("0")
                .setTypeface(Typeface.DEFAULT_BOLD)
                .setTextSizePx(dpToPx(this, 11))
                .setColor(Color.parseColor("#3E3E3E"))
                .setVerticalAlignment(Alignment.VERTICAL.BOTTOM)
                .setHorizontalAlignment(Alignment.HORIZONTAL.CENTER)
                .setVariable("distance_with_bar", dpToPx(this, 8))
                .setTop(0);

        shapeEventManager.onTouchAywhere((touchSetup) ->
                touchSetup.move(indicator, CircleShape.Pos.CENTER_X, EventPos.X)
                        .onDownAnimate((event) -> indicator.animate().radiusTo(background.getHeight()))
                        .onDownAnimate((event) -> indicatorValue.animate().bottomPlus(-1 * dpToPx(this, 10)))
                        .onMove(event -> {
                            final int percent = (int)((indicator.getCenterX() - background.getLeft()) / background.getWidth() * 100f);
                            indicatorValue.setText(String.valueOf(percent))
                                    .setLeft(indicator.getCenterX() - 50)
                                    .setRight(indicator.getCenterX() + 50);
                        })
                        .onUpAnimate((event) -> indicatorValue.animate().bottomTo(background.getTop() - indicatorValue.<Float>getVariable("distance_with_bar")))
                        .onUpAnimate((event) -> indicator.animate().radiusTo(indicator.<Float>getVariable("original_radius")).setDuration(500))
        );
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

        indicatorValue
                .setLeft(indicator.getCenterX() - 50)
                .setRight(indicator.getCenterX() + 50)
                .setBottom(background.getTop() - indicatorValue.<Float>getVariable("distance_with_bar"));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        background.onDraw(canvas);
        indicator.onDraw(canvas);
        indicatorValue.onDraw(canvas);
        minText.onDraw(canvas);
        maxText.onDraw(canvas);
    }
}
