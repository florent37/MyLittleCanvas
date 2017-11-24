package com.github.florent37.mylittlecanvas;

import android.graphics.Path;
import android.graphics.RectF;

public class RoundRect {

    public static Path generatePath(RectF rect, float topLeftDiameter, float topRightDiameter, float bottomRightDiameter, float bottomLeftDiameter){
        final Path path = new Path();

        topLeftDiameter = topLeftDiameter < 0 ? 0 : topLeftDiameter;
        topRightDiameter = topRightDiameter < 0 ? 0 : topRightDiameter;
        bottomLeftDiameter = bottomLeftDiameter < 0 ? 0 : bottomLeftDiameter;
        bottomRightDiameter = bottomRightDiameter < 0 ? 0 : bottomRightDiameter;

        path.moveTo(rect.left + topLeftDiameter/2 ,rect.top);
        path.lineTo(rect.right - topRightDiameter/2,rect.top);
        path.quadTo(rect.right, rect.top, rect.right, rect.top + topRightDiameter/2);
        path.lineTo(rect.right ,rect.bottom - bottomRightDiameter/2);
        path.quadTo(rect.right ,rect.bottom, rect.right - bottomRightDiameter/2, rect.bottom);
        path.lineTo(rect.left + bottomLeftDiameter/2,rect.bottom);
        path.quadTo(rect.left,rect.bottom,rect.left, rect.bottom - bottomLeftDiameter/2);
        path.lineTo(rect.left,rect.top + topLeftDiameter/2);
        path.quadTo(rect.left,rect.top, rect.left + topLeftDiameter/2, rect.top);
        path.close();

        return path;
    }

}
