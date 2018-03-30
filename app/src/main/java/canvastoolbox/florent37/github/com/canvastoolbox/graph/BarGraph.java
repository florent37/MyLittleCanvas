package canvastoolbox.florent37.github.com.canvastoolbox.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.florent37.mylittlecanvas.shape.LineShape;
import com.github.florent37.mylittlecanvas.shape.RectShape;
import com.github.florent37.mylittlecanvas.shape.TextShape;

import java.util.ArrayList;
import java.util.List;

import static com.github.florent37.mylittlecanvas.CanvasHelper.dpToPx;

public class BarGraph extends View {

    private final List<Column> columns = new ArrayList<>();
    private final LineShape axis = new LineShape();

    public BarGraph(Context context) {
        super(context);
        init();
    }

    public BarGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //lets imagine we have 3 bars, and the second bar has 2 setps

        //axis
        axis.setColor(Color.parseColor("#3E3E3E"))
                .setStrokeWidth(dpToPx(this, 2));

        //1st bar
        final Column firstColumn = new Column();

        firstColumn.label
                .setColor(Color.parseColor("#3E3E3E"))
                .setTextSizePx(dpToPx(this, 12))
                .setText("2016");

        firstColumn.rows.add(new RectShape()
                .setColor(Color.parseColor("#039BE5"))
                //we save a variable in the shape,
                //it will be used on `onSizeChanged`
                //because we need the view height/width to set Left/Right/Top/Bottom

                //eg: the value displayed by this bar
                .setVariable("value_percent", 0.20f));

        //2nd bar
        final Column secondColumn = new Column();

        secondColumn.label
                .setColor(Color.parseColor("#3E3E3E"))
                .setTextSizePx(dpToPx(this, 12))
                .setText("2017");
        secondColumn.rows.add(new RectShape()
                .setColor(Color.parseColor("#FFB300"))
                .setVariable("value_percent", 0.40f));
        secondColumn.rows.add(new RectShape()
                .setColor(Color.parseColor("#6D4C41"))
                .setVariable("value_percent", 0.30f));

        //3rd bar
        final Column thirdColumn = new Column();

        thirdColumn.label
                .setColor(Color.parseColor("#3E3E3E"))
                .setTextSizePx(dpToPx(this, 12))
                .setText("2018");
        thirdColumn.rows.add(new RectShape()
                .setColor(Color.parseColor("#D32F2F"))
                .setVariable("value_percent", 0.40f));

        columns.add(firstColumn);
        columns.add(secondColumn);
        columns.add(thirdColumn);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        //here we have the width & height of the view
        //se we can initialise (or update) the rect positions

        final float numberOfColumns = columns.size();
        if (numberOfColumns > 0) {
            final float distanceBetweenColumns = dpToPx(this, 14);
            final float availableWidthForDraw = width - (getPaddingLeft() + getPaddingRight());
            final float widthOfColumn = (availableWidthForDraw - (distanceBetweenColumns * (numberOfColumns + 1))) / numberOfColumns;

            final float heightOfLabels = columns.get(0).label.getTextSize();

            final float availableHeightForDraw = height - (getPaddingBottom() + getPaddingTop()) - heightOfLabels;

            float x = getPaddingLeft() + distanceBetweenColumns;
            float bottomLabel = height - getPaddingBottom();
            float topLabel = bottomLabel - heightOfLabels;

            float bottomRow = topLabel - dpToPx(this, 4) /* a small margin */;

            axis
                    .start(dpToPx(this, 4), bottomRow)
                    .end(width - dpToPx(this, 4), bottomRow);


            for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
                final Column column = columns.get(columnIndex);

                //setup the label
                column.label
                        .setBottom(bottomLabel)
                        .setTop(topLabel)
                        .setLeft(x)
                        .setWidth(widthOfColumn);

                //setup the rows
                float y = bottomRow;
                for (int rowIndex = 0; rowIndex < column.rows.size(); rowIndex++) {
                    final RectShape rectShape = column.rows.get(rowIndex);

                    final float blocTop = y - (availableHeightForDraw * rectShape.<Float>getVariable("value_percent"));
                    rectShape
                            .setLeft(x)
                            .setWidth(widthOfColumn)
                            .setBottom(y)
                            .setTop(blocTop);

                    y = blocTop;
                }

                x += (widthOfColumn + distanceBetweenColumns);
            }
            postInvalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Column column : columns) {
            column.label.onDraw(canvas);
            for (RectShape row : column.rows) {
                row.onDraw(canvas);
            }
        }
        axis.onDraw(canvas);
    }

    private class Column {
        private final TextShape label = new TextShape();
        private final List<RectShape> rows = new ArrayList<>();
    }
}
