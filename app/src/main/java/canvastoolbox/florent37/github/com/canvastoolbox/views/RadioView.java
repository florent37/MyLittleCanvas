package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.github.florent37.mylittlecanvas.animation.ShapeAnimator;
import com.github.florent37.mylittlecanvas.shape.CircleShape;

import static com.github.florent37.mylittlecanvas.CanvasHelper.dpToPx;

public class RadioView extends View {

    private final CircleShape background = new CircleShape();
    private final CircleShape indicator = new CircleShape();

    private final ShapeAnimator shapeAnimator = new ShapeAnimator(this);

    private boolean checked = false;

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

        final float radius = Math.min(h, w) / 2f;

        background
                .setBorderWidth(dpToPx(this, 2))
                .setBorderColor(Color.parseColor("#DDDDDD"))
                .setColor(Color.parseColor("#DEDEDE"))
                .setRadius(radius)
                .centerVertical(h)
                .centerHorizontal(w);

        //add a "variable" named radius_percent into indicator
        indicator.setVariable("radius_percent", 0.75f);

        indicator
                .setColor(Color.parseColor("#2E7D32"))
                .setBorderWidth(dpToPx(this, 1))
                .setBorderColor(Color.parseColor("#1B5E20"))
                //retrieve the "variable" named radius_percent ``into indicator
                .setRadius(radius * indicator.<Float>getVariable("radius_percent"))
                .centerVertical(h)
                .centerHorizontal(w);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        if (checked != this.checked) {
            this.checked = checked;

            //retrieve the "variable" named radius_percent ``into indicator
            final float newRadius = checked ? 0 : background.getRadius() * indicator.<Float>getVariable("radius_percent");
            shapeAnimator.clear()
                    .play(indicator.animate().radiusTo(newRadius))
                    .setDuration(200)
                    .setInterpolator(new AccelerateInterpolator())
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
