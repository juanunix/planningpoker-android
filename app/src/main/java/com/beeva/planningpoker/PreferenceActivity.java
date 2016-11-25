package com.beeva.planningpoker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by david.gonzalez on 21/10/16.
 */

public class PreferenceActivity extends android.preference.PreferenceActivity implements
    SharedPreferences.OnSharedPreferenceChangeListener {

  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    if (key.equals("pref_language")) {
      restartActivity();
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
  }

  @Override
  protected void onStop() {
    super.onStop();
    getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
  }

  private void restartActivity() {
    Intent intent = getIntent();
    finish();
    startActivity(intent);
  }
}