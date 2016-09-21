package com.beeva.planningpoker.di;

import com.beeva.planningpoker.manager.SharedPreferences;
import com.beeva.planningpoker.manager.language.LanguageManager;
import com.beeva.planningpoker.model.DataRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by David González on 13/4/16.
 */
@Module public class MainModule {

  @Provides @Singleton
  public com.beeva.planningpoker.manager.PackageManager providePackageManager() {
    return new com.beeva.planningpoker.manager.PackageManager();
  }

  @Singleton public com.beeva.planningpoker.manager.SharedPreferences provideSharedPreferences() {
    return new SharedPreferences();
  }

  @Provides @Singleton public LanguageManager provideLanguageManager() {
    return new LanguageManager();
  }

  @Provides @Singleton public DataRepository provideDataRepository() {
    return new DataRepository(provideSharedPreferences());
  }
}
