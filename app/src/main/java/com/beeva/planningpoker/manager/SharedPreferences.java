package com.beeva.planningpoker.manager;

import android.content.Context;
import com.beeva.planningpoker.application.PlanningPokerAplication;

/**
 * Created by david.gonzalez on 19/9/16.
 */

public class SharedPreferences {

  public static final String SETTINGS_LANGUAGE = "language";
  public static final String SETTINGS_PUSH_TO_SHOW = "pushToShow";
  public static final String SETTINGS_SHAKE_TO_SHOW = "shakeToShow";
  private static final String SHARED_PREFERENCES_ENVIROMENT = "SharedPrefsEnviroment";
  android.content.SharedPreferences sharedPreferences = getSharedPreferences();

  private android.content.SharedPreferences getSharedPreferences() {
    if (sharedPreferences == null) {
      sharedPreferences = PlanningPokerAplication.getContext()
          .getSharedPreferences(SHARED_PREFERENCES_ENVIROMENT, Context.MODE_PRIVATE);
    }
    return sharedPreferences;
  }

  public boolean getBooleanPreference(String keyPreference) {
    return sharedPreferences.getBoolean(keyPreference, false);
  }

  public void setBooleanPreference(String keyPreference, Boolean bool) {
    sharedPreferences.edit().putBoolean(keyPreference, bool).apply();
  }

  public String getStringPreference(String keyPreference) {
    return sharedPreferences.getString(keyPreference, null);
  }

  public void setStringPreference(String keyPreference, String string) {
    sharedPreferences.edit().putString(keyPreference, string).apply();
  }
}