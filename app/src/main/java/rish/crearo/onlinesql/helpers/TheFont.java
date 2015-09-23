package rish.crearo.onlinesql.helpers;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by rish on 13/9/15.
 */
public class TheFont {

    public static Typeface getPrimaryTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/roboto.ttf");
    }

    public static Typeface getPrimaryTypefaceBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
    }
}