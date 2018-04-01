package canvastoolbox.florent37.github.com.canvastoolbox.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.florent37.mylittlecanvas.shape.RectShape;

import java.util.ArrayList;
import java.util.List;

public class AvailableMemorySpace extends View {
    //TODO like available space in android
    //[ ][    ][   ][]

    private final List<RectShape> blocs = new ArrayList<>();

    public AvailableMemorySpace(Context context) {
        super(context);
        init();
    }

    public AvailableMemorySpace(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AvailableMemorySpace(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(Color.parseColor("#EEEEEE"));

        //lets imagine we have 8go as memory
        final float memory = 8;

        //we have 3.2 go of apps
        blocs.add(new RectShape()
                .setColor(Color.parseColor("#039BE5"))
                .setVariable("percent", 3.2f * memory / 100f));

        //we have 1.3 go of pictures
        blocs.add(new RectShape()
                .setColor(Color.parseColor("#D32F2F"))
                .setVariable("percent", 1.3f * memory / 100f));

        //we have 2.4 go of musics
        blocs.add(new RectShape()
                .setColor(Color.parseColor("#FFB300"))
                .setVariable("percent", 2.4f * memory / 100f));
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        for (int index = 0; index < blocs.size(); index++) {
            final RectShape bloc = blocs.get(index);

            if (index == 0) {
                bloc.setLeft(0);
            } else {
                bloc.toRightOf(blocs.get(index - 1));
            }

            bloc.setTop(0)
                    .setBottom(height)
                    .setWidth(bloc.<Float>getVariable("percent") * width);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (RectShape bloc : blocs) {
            bloc.onDraw(canvas);
        }
    }
}
