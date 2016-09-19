package com.beeva.corporate;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class RadioButton extends android.widget.RadioButton {

  public RadioButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    setClickable(true);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextView, defStyle, 0);
    String font = a.getString(R.styleable.TextView_font);
    if (font != null) {
      Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + font);
      if (tf != null) setTypeface(tf);
    }
    a.recycle();
  }

  public RadioButton(Context context, AttributeSet attrs) {
    this(context, attrs, android.R.attr.radioButtonStyle);
  }

  public RadioButton(Context context) {
    this(context, null, android.R.attr.radioButtonStyle);
  }
}