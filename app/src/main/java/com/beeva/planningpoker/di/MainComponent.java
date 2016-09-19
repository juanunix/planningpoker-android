package com.beeva.planningpoker.di;

import com.beeva.planningpoker.MainActivity;
import com.beeva.planningpoker.ui.share.ShareFragment;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by David González on 13/4/16.
 */
@Singleton @Component(modules = MainModule.class) public interface MainComponent {
  void inject(MainActivity mainActivity);

  void inject(ShareFragment shareFragment);
}
