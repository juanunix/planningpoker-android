package com.beeva.planningpoker.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.di.DaggerMainComponent;
import com.beeva.planningpoker.di.MainComponent;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by david.gonzalez on 15/9/16.
 */
public class PlanningPokerAplication extends Application {

  private static Context sContext;
  private MainComponent mainComponent;
  private Tracker tracker;

  public static Context getContext() {
    return sContext;
  }

  @Override public void onCreate() {
    super.onCreate();
    sContext = getApplicationContext();
    mainComponent = DaggerMainComponent.create();
  }

  synchronized public Tracker getDefaultTracker() {
    if (tracker == null) {
      GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
      // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
      tracker = analytics.newTracker(R.xml.global_tracker);
    }
    return tracker;
  }

  public MainComponent getMainComponent() {
    return mainComponent;
  }

  @VisibleForTesting public void setComponent(MainComponent mainComponent) {
    this.mainComponent = mainComponent;
  }
}