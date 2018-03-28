package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.florent37.mylittlecanvas.ShapeAnimator;
import com.github.florent37.mylittlecanvas.shape.CircleShape;
import com.github.florent37.mylittlecanvas.shape.RoundRectShape;

public class RadioView extends View {

    private final RoundRectShape background = new RoundRectShape();
    private final CircleShape indicator = new CircleShape();

    private final ShapeAnimator shapeAnimator = new ShapeAnimator(this);

    public RadioView(Context context) {
        super(context);
        init();
    }

    public RadioView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadioView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        background
                .setCorderRadius(h / 2f)
                .setColor(Color.parseColor("#3E3E3E"))
                .setTop(0)
                .setHeight(h)
                .setLeft(0)
                .setWidth(w);

        indicator
                .setRadius(h / 2f)
                .centerVertical(h)
                .setCenterX(h / 2f);
    }
}
