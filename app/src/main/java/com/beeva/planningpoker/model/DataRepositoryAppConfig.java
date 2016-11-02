package com.beeva.planningpoker.model;

import com.beeva.planningpoker.manager.SharedPreferences;
import com.beeva.planningpoker.manager.language.LanguageEnum;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 19/9/16.
 */

interface DataRepositoryAppConfigInput {
  void setPressToShow(boolean state);

  void setShakeToShow(boolean state);

  void setAppLanguage(LanguageEnum language);
}

interface DataRepositoryAppConfigOutput {
  boolean isPressToShow();

  boolean isShakeToShow();

  LanguageEnum getAppLanguage();
}

class DataRepositoryAppConfig
    implements DataRepositoryAppConfigInput, DataRepositoryAppConfigOutput {

  @Inject SharedPreferences sharedPreferences;

  DataRepositoryAppConfig(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  public boolean isPressToShow() {
    return sharedPreferences.getBooleanPreference(SharedPreferences.SETTINGS_PUSH_TO_SHOW);
  }

  public void setPressToShow(boolean state) {
    sharedPreferences.setBooleanPreference(SharedPreferences.SETTINGS_PUSH_TO_SHOW, state);
  }

  public boolean isShakeToShow() {
    return sharedPreferences.getBooleanPreference(SharedPreferences.SETTINGS_SHAKE_TO_SHOW);
  }

  public void setShakeToShow(boolean state) {
    sharedPreferences.setBooleanPreference(SharedPreferences.SETTINGS_SHAKE_TO_SHOW, state);
  }

  @Override public LanguageEnum getAppLanguage() {
    String language = sharedPreferences.getStringPreference(SharedPreferences.SETTINGS_LANGUAGE);

    if (language != null) {
      return LanguageEnum.valueOf(language);
    } else {
      return LanguageEnum.getDefaultLanguage();
    }
  }

  @Override public void setAppLanguage(LanguageEnum language) {
    sharedPreferences.setStringPreference(SharedPreferences.SETTINGS_LANGUAGE, language.toString());
  }
}