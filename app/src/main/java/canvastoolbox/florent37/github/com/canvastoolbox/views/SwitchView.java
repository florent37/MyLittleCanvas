package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.florent37.mylittlecanvas.animation.ShapeAnimator;
import com.github.florent37.mylittlecanvas.shape.CircleShape;
import com.github.florent37.mylittlecanvas.shape.RectShape;

import static com.github.florent37.mylittlecanvas.CanvasHelper.dpToPx;

public class SwitchView extends View {
    private final RectShape background = new RectShape();
    private final CircleShape indicator = new CircleShape();

    private final ShapeAnimator shapeAnimator = new ShapeAnimator(this);

    private boolean checked = false;

    public SwitchView(Context context) {
        super(context);
        init();
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(!isChecked());
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        background
                .setCornerRadius(h / 2f)
                .setColor(Color.parseColor("#DEDEDE"))
                .setRect(0, 0, w, h);

        indicator
                .setColor(Color.WHITE)
                .setBorderWidth(dpToPx(getContext(), 1))
                .setBorderColor(Color.parseColor("#DDDDDD"))
                .setRadius(h / 2f)
                .centerVertical(h)
                .setCenterX(h / 2f);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        if(checked != this.checked) {
            this.checked = checked;

            final float newCenterX = checked ? getWidth() - indicator.getRadius() : indicator.getRadius();
            shapeAnimator.clear()
                    .play(indicator.animate().centerXTo(newCenterX))
                    .play(indicator.animate().radiusBy(1f, 0.9f, 1f))
                    .start();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        background.onDraw(canvas);
        indicator.onDraw(canvas);
    }
}
