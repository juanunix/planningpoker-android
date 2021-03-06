package com.beeva.planningpoker;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beeva.planningpoker.application.PlanningPokerAplication;
import com.beeva.planningpoker.di.MainComponent;
import com.beeva.planningpoker.manager.TrackerManager;
import com.beeva.planningpoker.utils.DialogsUtils;

/**
 * Created by david.gonzalez on 15/9/16.
 */
public abstract class BaseFragment extends Fragment implements Presenter.View {

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  protected abstract int getHeaderTitle();

  private void setHeaderTitle(View view) {
    if (getHeaderTitle() != 0) {
      try {
        ((TextView) view.findViewById(R.id.txtHeaderTitle)).setText(
            getActivity().getString(getHeaderTitle()));
      } catch (NullPointerException nullPointerException) {
        System.err.println(getActivity().getString(R.string.error_missing_header));
      }
    }
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    initializeDagger(((PlanningPokerAplication) getActivity().getApplication()).getMainComponent());
    initializePresenter();

    setHeaderTitle(view);

    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void onDestroyView() {
    unbindButterknife();
    super.onDestroyView();
  }

  @Override public void showProgress(int resourceMessage) {
    DialogsUtils.showLoadingMessage(getActivity(), resourceMessage);
  }

  @Override public void hideLoading() {
    DialogsUtils.hideLoading();
  }

  @Override public void showToast(int resourceMessage) {
    DialogsUtils.showToast(getActivity(), resourceMessage);
  }

  @Override public void sendEventAnalytics(String category, String action) {
    TrackerManager.sendEvent(getActivity(), category, action);
  }

  //Abstract Classes
  protected abstract void initializePresenter();

  protected abstract void initializeDagger(MainComponent mainComponent);

  protected abstract void unbindButterknife();

}
