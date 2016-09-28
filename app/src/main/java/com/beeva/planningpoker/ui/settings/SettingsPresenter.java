package com.beeva.planningpoker.ui.settings;

import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.manager.language.LanguageEnum;
import com.beeva.planningpoker.manager.language.LanguageManager;
import com.beeva.planningpoker.model.DataRepository;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 19/9/16.
 */
public class SettingsPresenter extends Presenter<SettingsPresenter.View> {

  @Inject DataRepository dataRepository;
  @Inject LanguageManager languageManager;
  private View view;

  @Inject public SettingsPresenter() {

  }

  @Override public void initialize() {
    super.initialize();
    view = getView();

    view.setCheckKeepScreenOn(dataRepository.isKeepScreenOn());
    view.setCheckPressToShow(dataRepository.isPressToShow());
    view.setCheckShakeToShow(dataRepository.isShakeToShow());
    view.setRadioButtonLanguage(dataRepository.getAppLanguage());
  }

  public void onClickKeepScreenOn(boolean checked) {
    dataRepository.setKeepScreenOn(checked);
  }

  public void onClickPressToShow(boolean checked) {
    view.checkDefaultPressed();
    dataRepository.setPressToShow(checked);
  }

  public void onClickShakeToShow(boolean checked) {
    view.checkDefaultPressed();
    dataRepository.setShakeToShow(checked);
  }

  public void onLanguageChanged(LanguageEnum language) {
    languageManager.changeLocale(language);
    dataRepository.setAppLanguage(language);
  }

  public interface View extends Presenter.View {
    void setCheckKeepScreenOn(boolean state);

    void setCheckPressToShow(boolean state);

    void setCheckShakeToShow(boolean state);

    void setRadioButtonLanguage(LanguageEnum language);

    void checkDefaultPressed();
  }
}
