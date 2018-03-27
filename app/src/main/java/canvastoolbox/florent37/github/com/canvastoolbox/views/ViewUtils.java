package canvastoolbox.florent37.github.com.canvastoolbox.views;

import android.content.Context;

public class ViewUtils {
    public static int doToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
