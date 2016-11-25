package com.beeva.planningpoker.utils;

import android.content.Context;
import android.graphics.Typeface;
import com.beeva.planningpoker.R;

/**
 * Created by david.gonzalez on 28/9/16.
 */

public class FontUtils {

  public static Typeface getDefaultTypeface(Context context) {
    String font = context.getString(R.string.font_avenir_heavy);
    Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + font);

    return typeface;
  }
}
