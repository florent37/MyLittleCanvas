package com.github.florent37.mylittlecanvas;

import android.graphics.Path;
import android.graphics.RectF;

public class RoundRect {

    public static Path generatePath(RectF rect, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius){
        final Path path = new Path();

        topLeftRadius = topLeftRadius < 0 ? 0 : topLeftRadius;
        topRightRadius = topRightRadius < 0 ? 0 : topRightRadius;
        bottomLeftRadius = bottomLeftRadius < 0 ? 0 : bottomLeftRadius;
        bottomRightRadius = bottomRightRadius < 0 ? 0 : bottomRightRadius;

        path.moveTo(rect.left + topLeftRadius, rect.top);
        path.lineTo(rect.right - topRightRadius, rect.top);
        path.quadTo(rect.right, rect.top, rect.right, rect.top + topRightRadius);
        path.lineTo(rect.right, rect.bottom - bottomRightRadius);
        path.quadTo(rect.right, rect.bottom, rect.right - bottomRightRadius, rect.bottom);
        path.lineTo(rect.left + bottomLeftRadius, rect.bottom);
        path.quadTo(rect.left, rect.bottom, rect.left, rect.bottom - bottomLeftRadius);
        path.lineTo(rect.left, rect.top + topLeftRadius);
        path.quadTo(rect.left, rect.top, rect.left + topLeftRadius, rect.top);
        path.close();

        return path;
    }

}
