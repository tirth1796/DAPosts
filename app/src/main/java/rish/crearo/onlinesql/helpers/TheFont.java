package rish.crearo.onlinesql.helpers;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by rish on 13/9/15.
 */
public class TheFont {

    public static Typeface getPrimaryTypeface(Context context) {
//        return Typeface.createFromAsset(context.getAssets(), "fonts/goodhandwriting.ttf");
//        return Typeface.createFromAsset(context.getAssets(), "fonts/roboto.ttf");
        return Typeface.createFromAsset(context.getAssets(), "fonts/animeace2_reg.otf");
    }

}
