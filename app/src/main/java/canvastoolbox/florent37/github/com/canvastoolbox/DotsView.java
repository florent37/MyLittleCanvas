package canvastoolbox.florent37.github.com.canvastoolbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.github.florent37.mylittlecanvas.ShapeAnimator;
import com.github.florent37.mylittlecanvas.shape.CircleShape;

import java.util.ArrayList;
import java.util.List;


public class DotsView extends FrameLayout {

    private int colors[] = new int[]{
            //Color.parseColor("#ED1B2F"),
            //Color.parseColor("#006A4F"),
            Color.parseColor("#039BE5"),
            Color.parseColor("#D32F2F"),
            Color.parseColor("#FFB300"),
            Color.parseColor("#6D4C41")
    };

    private int numberOfCircles = 4;
    private List<CircleShape> circles = new ArrayList<>();

    private AnimationHandler animationHandler = new AnimationHandler();

    public DotsView(Context context) {
        super(context);
        init(context);
    }

    public DotsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DotsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setWillNotDraw(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        update();
    }

    private void update() {
        if(circles.isEmpty()){
            for (int i = 0; i < numberOfCircles; i++) {
                circles.add(new CircleShape());
            }
        }
        final int h = getHeight();
        final int w = getWidth();

        final int radius = doToPx(6);
        final int distanceBetweenCircles = doToPx(16);

        for (int i = 0; i < circles.size(); i++) {
            final CircleShape circle = circles.get(i);
            circle.setRadius(radius);
            circle.setColor(colors[i % colors.length]);
            circle.setCenterY((int) (h / 2f));
            circle.setCenterX((w / 2f) + ((radius + distanceBetweenCircles) * (i - circles.size() / 2)));
        }

        //circles[0].setCenterX((int) ((w / 2f) - (radius + distanceBetweenCircles));
        //circles[1].setCenterX((int) (w / 2f));
        //circles[2].setCenterX((int) ((w / 2f) + radius) + distanceBetweenCircles);
    }

    public void animateView() {
        animationHandler.start();
    }

    public void cancelAnimaton() {
        animationHandler.start();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(() -> animateView());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelAnimaton();
    }

    private void animationLoop(@NonNull final AnimationHandler handler, int animateCircleIndex) {
        final int saut = doToPx(15);

        final CircleShape firstCircle = circles.get(animateCircleIndex);
        final CircleShape nextCircle = circles.get(animateCircleIndex + 1);

        new ShapeAnimator(this)
                .playTogether(
                        firstCircle.animateCenterX(firstCircle.getCenterX(), nextCircle.getCenterX()),
                        firstCircle.animateCenterY(firstCircle.getCenterY(), firstCircle.getCenterY() - saut, firstCircle.getCenterY()),
                        nextCircle.animateCenterX(nextCircle.getCenterX(), firstCircle.getCenterX())
                )
                .onAnimationEnd(() -> {
                    //intervert circles
                    circles.set(animateCircleIndex, nextCircle);
                    circles.set(animateCircleIndex + 1, firstCircle);

                    handler.next();
                })
                .start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (CircleShape circle : circles) {
            circle.onDraw(canvas);
        }
    }

    private int doToPx(float dp) {
        return (int) (dp * this.getContext().getResources().getDisplayMetrics().density);
    }

    private class AnimationHandler extends Handler {
        private final int CONTINUE_FIRST_ANIM = 1;
        private int animateCircleIndex = 0;

        public AnimationHandler() {
            super(Looper.getMainLooper());
        }

        public void start() {
            sendEmptyMessage(CONTINUE_FIRST_ANIM);
        }

        public void next() {
            animateCircleIndex++;
            if (animateCircleIndex == circles.size() - 1) {
                animateCircleIndex = 0;
            }
            sendEmptyMessage(CONTINUE_FIRST_ANIM);
        }

        public void cancel() {
            removeMessages(CONTINUE_FIRST_ANIM);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CONTINUE_FIRST_ANIM:
                    animationLoop(this, animateCircleIndex);
                    break;
                default:
                    break;
            }
        }
    }
}
