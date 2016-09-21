package com.beeva.corporate

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class Button : android.widget.Button {
  @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.buttonStyle)
  : super(context, attrs, defStyleAttr) {


    val a = context.obtainStyledAttributes(attrs, R.styleable.TextView, defStyleAttr, 0)
    val font = a.getString(R.styleable.TextView_font)
    if (font != null) {
      val tf = Typeface.createFromAsset(getContext().assets, "fonts/" + font)
      if (tf != null) typeface = tf
    }
    a.recycle()
  }
}
