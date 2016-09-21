package com.beeva.corporate

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet

class TextView : android.widget.TextView {
  @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.textViewStyle)
  : super(context, attrs, defStyleAttr) {

    val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextView, defStyleAttr, 0)
    val font = typedArray.getString(R.styleable.TextView_font)
    val underline = typedArray.getBoolean(R.styleable.TextView_underline, false)

    if (font != null) {
      val tf = Typeface.createFromAsset(getContext().assets, "fonts/" + font)
      if (tf != null) typeface = tf
    }
    if (underline) paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG

    typedArray.recycle()
  }
}