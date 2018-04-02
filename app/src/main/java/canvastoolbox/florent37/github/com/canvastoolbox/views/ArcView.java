package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.florent37.mylittlecanvas.animation.ShapeAnimator;
import com.github.florent37.mylittlecanvas.shape.ArcShape;

import java.util.Arrays;
import java.util.List;

import static com.github.florent37.mylittlecanvas.CanvasHelper.dpToPx;

public class ArcView extends View {

    private final ArcShape arcShapeBackground = new ArcShape();
    private final ArcShape arcShapeFooting = new ArcShape();
    private final ArcShape arcShapeCycle = new ArcShape();
    private final ArcShape arcShapeSwimming = new ArcShape();

    private final ShapeAnimator shapeAnimator = new ShapeAnimator(this);

    private final List<ArcShape> arcs = Arrays.asList(arcShapeBackground, arcShapeCycle, arcShapeFooting, arcShapeSwimming);

    private final AnimationHandler animationHandler = new AnimationHandler();

    public ArcView(Context context) {
        super(context);
        init();
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        arcShapeBackground
                .setColor(Color.parseColor("#EEEEEE"))
                .setStartAngle(0)
                .setEndAngle(360);

        arcShapeSwimming
                .setColor(Color.parseColor("#6599FF"))
                .setStartAngle(-90)
                .setEndAngle(30);

        arcShapeFooting
                .setColor(Color.parseColor("#FF4444"))
                .setStartAngle(-90)
                .setEndAngle(120);

        arcShapeCycle
                .setColor(Color.parseColor("#FF8800"))
                .setStartAngle(-90)
                .setEndAngle(280);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animationHandler.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animationHandler.cancel();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        final float centerX = width / 2f;
        final float centerY = height / 2f;
        final float radius = Math.min(width, height) / 4f;
        final float strokeWidth = dpToPx(this, 10);

        for (ArcShape arc : arcs) {
            arc.setCenterX(centerX)
                    .setStrokeWidth(strokeWidth)
                    .setCenterY(centerY)
                    .setRadius(radius);
        }
    }

    private void beginAnimation() {

        //for the animation
        shapeAnimator
                .clear()
                .play(
                        arcShapeSwimming.animate().endAngleBy(0f, 1f),
                        arcShapeFooting.animate().endAngleBy(0f, 1f),
                        arcShapeCycle.animate().endAngleBy(0f, 1f)
                )
                .withInitAction(() -> {
                    arcShapeSwimming.setEndAngle(0);
                    arcShapeFooting.setEndAngle(0);
                    arcShapeCycle.setEndAngle(0);
                })
                .setDuration(1400)
                .onAnimationEnd(() -> animationHandler.next())
                .start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ArcShape arc : arcs) {
            arc.onDraw(canvas);
        }
    }

    private class AnimationHandler extends android.os.Handler {
        private final int CONTINUE_FIRST_ANIM = 1;

        public AnimationHandler() {
            super(Looper.getMainLooper());
        }

        public void start() {
            sendEmptyMessage(CONTINUE_FIRST_ANIM);
        }

        public void next() {
            sendEmptyMessageDelayed(CONTINUE_FIRST_ANIM, 4000);
        }

        public void cancel() {
            removeMessages(CONTINUE_FIRST_ANIM);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CONTINUE_FIRST_ANIM:
                    beginAnimation();
                    break;
                default:
                    break;
            }
        }
    }
}
