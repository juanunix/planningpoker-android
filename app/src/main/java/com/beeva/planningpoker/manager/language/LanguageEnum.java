package com.beeva.planningpoker.manager.language;

/**
 * Created by david.gonzalez on 19/9/16.
 */
public enum LanguageEnum {
  ENGLISH,
  SPANISH;

  public static LanguageEnum getDefaultLanguage() {
    return ENGLISH;
  }
}
