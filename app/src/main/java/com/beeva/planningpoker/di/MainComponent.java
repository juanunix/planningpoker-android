package com.beeva.planningpoker.di;

import com.beeva.planningpoker.MainActivity;
import com.beeva.planningpoker.ui.decks.feature.detail.DeckDetailFragment;
import com.beeva.planningpoker.ui.decks.feature.mainDeck.DeckChooseFragment;
import com.beeva.planningpoker.ui.decks.views.DeckActivity;
import com.beeva.planningpoker.ui.login.signup.SignUpActivity;
import com.beeva.planningpoker.ui.settings.SettingsFragment;
import com.beeva.planningpoker.ui.share.ShareFragment;
import com.beeva.planningpoker.ui.splash.SplashActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by David González on 13/4/16.
 */
@Singleton @Component(modules = MainModule.class) public interface MainComponent {
  void inject(SplashActivity splashActivity);

  void inject(MainActivity mainActivity);

  void inject(DeckActivity deckActivity);

  void inject(ShareFragment shareFragment);

  void inject(SignUpActivity signUpActivity);

  void inject(SettingsFragment settingsFragment);

  void inject(DeckChooseFragment deckChooseFragment);

  void inject(DeckDetailFragment deckDetailFragment);
}
