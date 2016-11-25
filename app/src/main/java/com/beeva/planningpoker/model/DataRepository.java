package com.beeva.planningpoker.model;

import com.beeva.planningpoker.manager.SharedPreferences;

/**
 * Created by david.gonzalez on 19/9/16.
 */
public class DataRepository extends DataRepositoryAppConfig {

  public DataRepository(SharedPreferences sharedPreferences) {
    super(sharedPreferences);
  }
}