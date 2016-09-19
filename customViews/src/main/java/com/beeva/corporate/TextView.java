package com.beeva.corporate;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class TextView extends android.widget.TextView {

  public TextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextView, defStyle, 0);
    String font = a.getString(R.styleable.TextView_font);
    boolean underline = a.getBoolean(R.styleable.TextView_underline, false);

    if (font != null) {
      Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + font);
      if (tf != null) setTypeface(tf);
    }
    if (underline) setPaintFlags(getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    a.recycle();
  }

  public TextView(Context context, AttributeSet attrs) {
    this(context, attrs, android.R.attr.textViewStyle);
  }

  public TextView(Context context) {
    this(context, null, android.R.attr.textViewStyle);
  }
}