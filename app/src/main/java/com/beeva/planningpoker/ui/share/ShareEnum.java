package com.beeva.planningpoker.ui.share;

import android.content.res.Resources;
import com.beeva.planningpoker.R;
import com.beeva.planningpoker.application.PlanningPokerAplication;

/**
 * Created by david.gonzalez on 15/9/16.
 */
public enum ShareEnum {
  LINKEDIN,
  FACEBOOK,
  TWITTER,
  GOOGLE,
  MAIL,
  WHATSAPP;

  String packageName;

  public String getPackageName(ShareEnum shareEnum) {

    Resources resources = PlanningPokerAplication.getContext().getResources();
    switch (shareEnum) {
      case LINKEDIN:
        return resources.getString(R.string.packageName_share_linkedin);
      case FACEBOOK:
        return resources.getString(R.string.packageName_share_facebook);
      case TWITTER:
        return resources.getString(R.string.packageName_share_twitter);
      case GOOGLE:
        return resources.getString(R.string.packageName_share_google);
      case MAIL:
        return resources.getString(R.string.packageName_share_gmail);
      case WHATSAPP:
        return resources.getString(R.string.packageName_share_whatsapp);
    }

    return packageName;
  }
}
