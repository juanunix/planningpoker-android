package com.beeva.planningpoker.ui.splash;

import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.manager.language.LanguageManager;
import com.beeva.planningpoker.model.DataRepository;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 20/9/16.
 */

public class SplashPresenter extends Presenter<SplashPresenter.View> {

  @Inject DataRepository dataRepository;
  @Inject LanguageManager languageManager;
  private View view;

  @Inject public SplashPresenter() {

  }

  @Override public void initialize() {
    super.initialize();
    view = getView();

    languageManager.changeLocale(dataRepository.getAppLanguage());
  }

  public void onStart() {
    view.startAnimation();
    view.startTimer();
  }

  public interface View extends Presenter.View {
    void startAnimation();

    void startTimer();

    void navigateToMainActivity();
  }
}
