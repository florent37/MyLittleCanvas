package com.github.florent37.mylittlecanvas;

import android.graphics.Path;
import android.graphics.RectF;

public class RoundRect {

    public static Path generatePath(float width, float height, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        return generatePath(false, width, height, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);
    }

    public static Path generatePath(boolean useBezier, float width, float height, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
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

        if (topLeftRadius > minSize) {
            topLeftRadius = minSize;
        }
        if (topRightRadius > minSize) {
            topRightRadius = minSize;
        }
        if (bottomLeftRadius > minSize) {
            bottomLeftRadius = minSize;
        }
        if (bottomRightRadius > minSize) {
            bottomRightRadius = minSize;
        }

        path.moveTo(left + topLeftRadius, top);
        path.lineTo(right - topRightRadius, top);

        //float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo
        if (useBezier) {
            path.quadTo(right, top, right, top + topRightRadius);
        } else {
            path.arcTo(new RectF(right - topRightRadius * 2f, top, right, top + topRightRadius * 2f), -90, 90);
        }
        path.lineTo(right, bottom - bottomRightRadius);
        if (useBezier) {
            path.quadTo(right, bottom, right - bottomRightRadius, bottom);
        } else {
            path.arcTo(new RectF(right - bottomRightRadius * 2f, bottom - bottomRightRadius * 2f, right, bottom), 0, 90);
        }
        path.lineTo(left + bottomLeftRadius, bottom);
        if (useBezier) {
            path.quadTo(left, bottom, left, bottom - bottomLeftRadius);
        } else {
            path.arcTo(new RectF(left, bottom - bottomLeftRadius * 2f, left + bottomLeftRadius * 2f, bottom), 90, 90);
        }
        path.lineTo(left, top + topLeftRadius);
        if (useBezier) {
            path.quadTo(left, top, left + topLeftRadius, top);
        } else {
            path.arcTo(new RectF(left, top, left + topLeftRadius * 2f, top + topLeftRadius * 2f), 180, 90);
        }
        path.close();

        return path;
    }

    public static Path generatePath(RectF rect, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        final Path path = new Path();

        final float minSize = Math.min(rect.width() / 2f, rect.height() / 2f);

        topLeftRadius = topLeftRadius < 0 ? 0 : topLeftRadius;
        topRightRadius = topRightRadius < 0 ? 0 : topRightRadius;
        bottomLeftRadius = bottomLeftRadius < 0 ? 0 : bottomLeftRadius;
        bottomRightRadius = bottomRightRadius < 0 ? 0 : bottomRightRadius;

        if (topLeftRadius > minSize) {
            topLeftRadius = minSize;
        }
        if (topRightRadius > minSize) {
            topRightRadius = minSize;
        }
        if (bottomLeftRadius > minSize) {
            bottomLeftRadius = minSize;
        }
        if (bottomRightRadius > minSize) {
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
