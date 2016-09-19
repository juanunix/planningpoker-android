package com.beeva.planningpoker.ui.settings;

import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.model.DataRepository;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 19/9/16.
 */
public class SettingsPresenter extends Presenter<SettingsPresenter.View> {

  @Inject DataRepository dataRepository;
  private View view;

  @Inject public SettingsPresenter() {

  }

  @Override public void initialize() {
    super.initialize();
    view = getView();

    view.setCheckKeepScreenOn(dataRepository.isKeepScreenOn());
    view.setCheckPressToShow(dataRepository.isPressToShow());
    view.setCheckShakeToShow(dataRepository.isShakeToShow());
  }

  public void onClickKeepScreenOn(boolean checked) {
    dataRepository.setKeepScreenOn(checked);
  }

  public void onClickPressToShow(boolean checked) {
    dataRepository.setPressToShow(checked);
  }

  public void onClickShakeToShow(boolean checked) {
    dataRepository.setShakeToShow(checked);
  }

  //TODO: Implement
  public void onLanguageChanged() {

  }

  public interface View extends Presenter.View {
    void setCheckKeepScreenOn(boolean state);

    void setCheckPressToShow(boolean state);

    void setCheckShakeToShow(boolean state);
  }
}
