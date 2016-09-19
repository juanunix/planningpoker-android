package com.beeva.planningpoker.ui;

import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.manager.language.LanguageManager;
import com.beeva.planningpoker.model.DataRepository;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 19/9/16.
 */
public class MainPresenter extends Presenter<MainPresenter.View> {

  @Inject DataRepository dataRepository;
  @Inject LanguageManager languageManager;

  private View view;

  @Inject public MainPresenter() {

  }

  @Override public void initialize() {
    super.initialize();
    view = getView();
    languageManager.changeLocale(dataRepository.getAppLanguage());
  }

  public interface View extends Presenter.View {
  }
}
