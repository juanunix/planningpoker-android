package com.beeva.planningpoker.manager.language;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import java.util.Locale;

/**
 * Created by david.gonzalez on 19/9/16.
 */

public class LanguageManager {

  private final String LOCALE_SPANISH = "es";

  public void changeLocale(LanguageEnum languageEnum) {
    Configuration configuration;
    Resources resources = PlanningPokerAplication.getContext().getResources();
    configuration = new Configuration(resources.getConfiguration());

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      configuration = getConfigurationLocalized(configuration, languageEnum);
    } else {
      configuration = getConfigurationLocalized_Old(configuration, languageEnum);
    }

    resources.updateConfiguration(configuration, resources.getDisplayMetrics());
  }

  @SuppressWarnings("deprecation")
  private Configuration getConfigurationLocalized_Old(Configuration configuration,
      LanguageEnum languageEnum) {
    switch (languageEnum) {
      case SPANISH:
        configuration.locale = new Locale(LOCALE_SPANISH);
        break;
      default:
        configuration.locale = Locale.ENGLISH;
        break;
    }

    return configuration;
  }

  @TargetApi(Build.VERSION_CODES.N)
  private Configuration getConfigurationLocalized(Configuration configuration,
      LanguageEnum languageEnum) {
    switch (languageEnum) {
      case SPANISH:
        configuration.getLocales().indexOf(new Locale(LOCALE_SPANISH));
        break;
      default:
        configuration.getLocales().indexOf(Locale.ENGLISH);
        break;
    }

    return configuration;
  }
}
