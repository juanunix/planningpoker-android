package com.beeva.planningpoker.ui.decks.feature.detail;

import com.beeva.planningpoker.Presenter;
import com.beeva.planningpoker.model.DataRepository;
import javax.inject.Inject;

/**
 * Created by david.gonzalez on 27/9/16.
 */

public class DeckDetailPresenter extends Presenter<DeckDetailPresenter.View> {

  @Inject DataRepository dataRepository;
  private View view;

  @Inject public DeckDetailPresenter() {

  }

  @Override public void initialize() {
    super.initialize();
    view = getView();

    view.loadAnimations();
    view.changeCameraDistance();

    if (dataRepository.isPressToShow()) view.initializationPressOnClick();
    if (dataRepository.isShakeToShow()) view.initializationShakeDetector();
  }

  public interface View extends Presenter.View {
    void loadAnimations();

    void changeCameraDistance();

    void initializationPressOnClick();

    void initializationShakeDetector();
  }
}
