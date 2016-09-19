package com.beeva.planningpoker.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import com.beeva.planningpoker.di.DaggerMainComponent;
import com.beeva.planningpoker.di.MainComponent;

/**
 * Created by david.gonzalez on 15/9/16.
 */
public class PlanningPokerAplication extends Application {

  private static Context sContext;
  private MainComponent mainComponent;

  public static Context getContext() {
    return sContext;
  }

  @Override public void onCreate() {
    super.onCreate();
    sContext = getApplicationContext();
    mainComponent = DaggerMainComponent.create();
  }

  public MainComponent getMainComponent() {
    return mainComponent;
  }

  @VisibleForTesting public void setComponent(MainComponent mainComponent) {
    this.mainComponent = mainComponent;
  }
}