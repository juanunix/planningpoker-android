package com.beeva.corporate

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class RadioButton : android.widget.RadioButton {
  @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.radioButtonStyle)
  : super(context, attrs, defStyleAttr) {

    isClickable = true

    val a = context.obtainStyledAttributes(attrs, R.styleable.TextView, defStyleAttr, 0)
    val font = a.getString(R.styleable.TextView_font)
    if (font != null) {
      val tf = Typeface.createFromAsset(getContext().assets, "fonts/" + font)
      if (tf != null) typeface = tf
    }
    a.recycle()
  }
}
