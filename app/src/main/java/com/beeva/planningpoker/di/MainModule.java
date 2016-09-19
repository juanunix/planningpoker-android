package com.beeva.planningpoker.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by David González on 13/4/16.
 */
@Module public class MainModule {

  @Provides @Singleton public com.beeva.planningpoker.manager.PackageManager providePackageManager() {
    return new com.beeva.planningpoker.manager.PackageManager();
  }
}
