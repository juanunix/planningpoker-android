package com.beeva.planningpoker.model;

import com.beeva.planningpoker.manager.SharedPreferences;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 19/9/16.
 */

interface DataRepositoryAppConfigInput {
  void setKeepScreenOn(boolean state);
  void setPressToShow(boolean state);
  void setShakeToShow(boolean state);
}

interface DataRepositoryAppConfigOutput {
  boolean isKeepScreenOn();
  boolean isPressToShow();
  boolean isShakeToShow();
}

public class DataRepositoryAppConfig implements DataRepositoryAppConfigInput, DataRepositoryAppConfigOutput {

  @Inject SharedPreferences sharedPreferences;

  public DataRepositoryAppConfig(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  public boolean isKeepScreenOn() {
    return sharedPreferences.getBooleanPreference(SharedPreferences.SETTINGS_KEEP_SCREEN_ON);
  }

  public void setKeepScreenOn(boolean state) {
    sharedPreferences.setBooleanPreference(SharedPreferences.SETTINGS_KEEP_SCREEN_ON, state);
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
}