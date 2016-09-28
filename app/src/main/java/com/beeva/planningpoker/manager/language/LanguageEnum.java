package com.beeva.planningpoker.manager.language;

import java.util.Locale;

/**
 * Created by david.gonzalez on 19/9/16.
 */
public enum LanguageEnum {
  ENGLISH,
  SPANISH;

  public static LanguageEnum getDefaultLanguage() {
    String language = Locale.getDefault().getLanguage();

    switch (language) {
      case "es":
        return SPANISH;
      default:
        return ENGLISH;
    }
  }
}
