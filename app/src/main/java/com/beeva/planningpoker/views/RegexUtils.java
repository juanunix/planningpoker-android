package com.beeva.planningpoker.views;

/**
 * Created by david.gonzalez on 5/10/16.
 */

public class RegexUtils {

  public final static boolean isValidEmail(CharSequence charSequence){
    return android.util.Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
  }
}
