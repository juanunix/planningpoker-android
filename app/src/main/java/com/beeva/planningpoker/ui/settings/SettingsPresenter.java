package com.beeva.planningpoker.ui.settings;

import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.manager.PackageManager;
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
  @Inject PackageManager packageManager;

  private boolean isPressToShowChecked = false;
  private boolean isShakeToShowChecked = false;
  private boolean isGyroscopeAvailable = false;

  private View view;

  @Inject SettingsPresenter() {

  }

  //We don't need to check if Gyroscope is available on the device when settings are changed, because by default shakeToShow is false
  @Override public void initialize() {
    super.initialize();
    view = getView();

    isPressToShowChecked = dataRepository.isPressToShow();
    isShakeToShowChecked = dataRepository.isShakeToShow();
    isGyroscopeAvailable = packageManager.existsGyroscopeInDevice();

    view.setCheckPressToShow(isPressToShowChecked);
    view.setCheckShakeToShow(isShakeToShowChecked);
    view.setRadioButtonLanguage(dataRepository.getAppLanguage());

    if (!isGyroscopeAvailable) {
      view.removeShakeToShowOption();
    }
  }

  void onClickPressToShow(boolean checked) {
    isPressToShowChecked = checked;
    if (isChecksFollowingPolicy()) {
      dataRepository.setPressToShow(checked);
    }
  }

  void onClickShakeToShow(boolean checked) {
    isShakeToShowChecked = checked;
    if (isChecksFollowingPolicy()) {
      dataRepository.setShakeToShow(checked);
    }
  }

  void onLanguageChanged(LanguageEnum language) {
    languageManager.changeLocale(language);
    dataRepository.setAppLanguage(language);
    view.forceChangeLanguage();
  }

  private boolean isChecksFollowingPolicy() {
    boolean isCkecksNotFollowingPolicy = (!isPressToShowChecked && !isShakeToShowChecked);

    if (isCkecksNotFollowingPolicy) {
      setChecksDefaultState();
      return false;
    }

    return true;
  }

  private void setChecksDefaultState() {
    view.setChecksDefaultState();
    dataRepository.setPressToShow(true);
    dataRepository.setShakeToShow(false);
  }

  public interface View extends Presenter.View {

    void removeShakeToShowOption();

    void setCheckPressToShow(boolean state);

    void setCheckShakeToShow(boolean state);

    void setRadioButtonLanguage(LanguageEnum language);

    void setChecksDefaultState();

    void forceChangeLanguage();
  }
}
