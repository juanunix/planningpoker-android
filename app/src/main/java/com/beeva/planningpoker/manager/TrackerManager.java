package com.beeva.planningpoker.manager;

import android.app.Activity;
import android.util.Log;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by david.gonzalez on 29/11/16.
 */

public class TrackerManager {

  private static Tracker tracker;

  public static void sendEvent(Activity activity, String category, String action){
    PlanningPokerAplication application = (PlanningPokerAplication) activity.getApplication();
    tracker = application.getDefaultTracker();

    tracker.send(new HitBuilders.EventBuilder()
        .setCategory(category)
        .setAction(action)
        .build());

    Log.i("TrackerManager", String.format("Sending: %s - %s", category, action));
  }
}
