package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.florent37.mylittlecanvas.shape.CircleShape;
import com.github.florent37.mylittlecanvas.shape.LineShape;
import com.github.florent37.mylittlecanvas.shape.RectShape;

public class SampleView extends View {

    private final RectShape roundRectShape = new RectShape();
    private final CircleShape circleShape = new CircleShape();
    private final LineShape lineShape = new LineShape();

    public SampleView(Context context) {
        super(context);
    }

    public SampleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SampleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        roundRectShape.setColor(Color.BLUE)

                .setBorderColor(Color.parseColor("#AAAAAA"))
                .setBorderWidth(15)

                .setCornerRadius(15)
                .setLeft(50)
                .setTop(50)
                .setWidth(200)
                .setHeight(200);

        circleShape.setColor(Color.BLUE)
                .setRadius(50)
                .setCenterX(400)
                .setCenterY(400);

        lineShape
                .setColor(Color.BLUE)
                .setStrokeWidth(10)
                .start(100, 800)
                .end(400, 800);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        roundRectShape.onDraw(canvas);
        circleShape.onDraw(canvas);
        lineShape.onDraw(canvas);
    }
}
