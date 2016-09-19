package com.beeva.planningpoker.model;

import javax.inject.Inject;

/**
 * Created by david.gonzalez on 19/9/16.
 */
public class DataRepository implements DataRepositoryAppConfigInput, DataRepositoryAppConfigOutput {

  @Inject DataRepositoryAppConfig dataRepositoryAppConfig;

  public DataRepository(DataRepositoryAppConfig dataRepositoryAppConfig) {
    this.dataRepositoryAppConfig = dataRepositoryAppConfig;
  }

  @Override public boolean isKeepScreenOn() {
    return dataRepositoryAppConfig.isKeepScreenOn();
  }

  @Override public void setKeepScreenOn(boolean state) {
    dataRepositoryAppConfig.setKeepScreenOn(state);
  }

  @Override public boolean isPressToShow() {
    return dataRepositoryAppConfig.isPressToShow();
  }

  @Override public void setPressToShow(boolean state) {
    dataRepositoryAppConfig.setPressToShow(state);
  }

  @Override public boolean isShakeToShow() {
    return dataRepositoryAppConfig.isShakeToShow();
  }

  @Override public void setShakeToShow(boolean state) {
    dataRepositoryAppConfig.setShakeToShow(state);
  }
}