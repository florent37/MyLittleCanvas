package com.github.florent37.mylittlecanvas;

import android.graphics.Path;
import android.graphics.RectF;

public class RoundRect {

    public static Path generatePath(float width, float height, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius){
        final Path path = new Path();

        final float left = 0f;
        final float top = 0f;
        final float bottom = height;
        final float right = width;

        final float minSize = Math.min(width / 2f, height / 2f);

        topLeftRadius = topLeftRadius < 0 ? 0 : topLeftRadius;
        topRightRadius = topRightRadius < 0 ? 0 : topRightRadius;
        bottomLeftRadius = bottomLeftRadius < 0 ? 0 : bottomLeftRadius;
        bottomRightRadius = bottomRightRadius < 0 ? 0 : bottomRightRadius;

        if(topLeftRadius > minSize){
            topLeftRadius = minSize;
        }
        if(topRightRadius > minSize){
            topRightRadius = minSize;
        }
        if(bottomLeftRadius > minSize){
            bottomLeftRadius = minSize;
        }
        if(bottomRightRadius > minSize){
            bottomRightRadius = minSize;
        }

        path.moveTo(left + topLeftRadius, top);
        path.lineTo(right - topRightRadius, top);
        path.quadTo(right, top, right, top + topRightRadius);
        path.lineTo(right, bottom - bottomRightRadius);
        path.quadTo(right, bottom, right - bottomRightRadius, bottom);
        path.lineTo(left + bottomLeftRadius, bottom);
        path.quadTo(left, bottom, left, bottom - bottomLeftRadius);
        path.lineTo(left, top + topLeftRadius);
        path.quadTo(left, top, left + topLeftRadius, top);
        path.close();

        return path;
    }

    public static Path generatePath(RectF rect, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius){
        final Path path = new Path();

        final float minSize = Math.min(rect.width() / 2f, rect.height() / 2f);

        topLeftRadius = topLeftRadius < 0 ? 0 : topLeftRadius;
        topRightRadius = topRightRadius < 0 ? 0 : topRightRadius;
        bottomLeftRadius = bottomLeftRadius < 0 ? 0 : bottomLeftRadius;
        bottomRightRadius = bottomRightRadius < 0 ? 0 : bottomRightRadius;

        if(topLeftRadius > minSize){
            topLeftRadius = minSize;
        }
        if(topRightRadius > minSize){
            topRightRadius = minSize;
        }
        if(bottomLeftRadius > minSize){
            bottomLeftRadius = minSize;
        }
        if(bottomRightRadius > minSize){
            bottomRightRadius = minSize;
        }

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
