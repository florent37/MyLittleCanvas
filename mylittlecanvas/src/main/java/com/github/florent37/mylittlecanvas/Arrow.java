package com.github.florent37.mylittlecanvas;

import android.graphics.Path;

public class Arrow {
    public static Path generatePath(float width, float height) {
        final Path path = new Path();

        path.rewind();
        path.moveTo(0, height / 2);
        path.lineTo(width / 2, 0);
        path.lineTo(width, height / 2);
        path.lineTo(width * 3 / 4, height / 2);
        path.lineTo(width * 3 / 4, height);
        path.lineTo(width / 4, height);
        path.lineTo(width / 4, height / 2);
        path.lineTo(0, height / 2);

        return path;
    }
}
