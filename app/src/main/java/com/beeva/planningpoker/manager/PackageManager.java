package com.beeva.planningpoker.manager;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import java.util.List;

/**
 * Created by david.gonzalez on 16/9/16.
 */
public class PackageManager {

  public PackageManager() {
  }

  public boolean isApplicationInstalled(String packageName) {
    android.content.pm.PackageManager pm = PlanningPokerAplication.getContext().getPackageManager();
    try {
      pm.getPackageInfo(packageName, android.content.pm.PackageManager.GET_ACTIVITIES);
      return true;
    } catch (android.content.pm.PackageManager.NameNotFoundException e) {
      return false;
    }
  }

  public List<ResolveInfo> getApplicationListAvailable(Intent intent) {
    android.content.pm.PackageManager packManager =
        PlanningPokerAplication.getContext().getPackageManager();
    return packManager.queryIntentActivities(intent,
        android.content.pm.PackageManager.MATCH_DEFAULT_ONLY);
  }
}
